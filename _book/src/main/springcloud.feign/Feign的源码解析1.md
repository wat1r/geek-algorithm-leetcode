## Feign的源码解析1



- 在服务调用的场景中，我们经常调用基于Http协议的服务，而我们经常使用到的框架可能有HttpURLConnection、Apache HttpComponnets、OkHttp3 、Netty等等，这些框架在基于自身的专注点提供了自身特性。而从角色划分上来看，他们的职能是一致的提供Http调用服务。具体流程如下：

![image-20200713195650432](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\springcloud.feign\Feign的源码解析1.assets\image-20200713195650432.png)







![Fegin](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\springcloud.feign\Feign的源码解析1.assets\Fegin.jpg)





`ReflectiveFeign.FeignInvocationHandler`

```java
   @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if ("equals".equals(method.getName())) {
        try {
          Object
              otherHandler =
              args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
          return equals(otherHandler);
        } catch (IllegalArgumentException e) {
          return false;
        }
      } else if ("hashCode".equals(method.getName())) {
        return hashCode();
      } else if ("toString".equals(method.getName())) {
        return toString();
      }
      return dispatch.get(method).invoke(args);
    }
```

dispatch调用SynchronousMethodHandler.invoke()方法

`SynchronousMethodHandler`

```java
  @Override
  public Object invoke(Object[] argv) throws Throwable {
    //将args添加到RequestTemplate中
    RequestTemplate template = buildTemplateFromArgs.create(argv);
    Retryer retryer = this.retryer.clone();
    while (true) {
      try {
        //根据RequestTemplate生成request对象，交给Client实例，发送请求
        return executeAndDecode(template);
      } catch (RetryableException e) {
        //如果Response#status()为503，即重试请求，调用该方法重试，如果超过最大重试次数maxAttempts，抛出异常
        retryer.continueOrPropagate(e);
        if (logLevel != Logger.Level.NONE) {
          logger.logRetry(metadata.configKey(), logLevel);
        }
        continue;
      }
    }
  }
```



```java
Object executeAndDecode(RequestTemplate template) throws Throwable {
    Request request = targetRequest(template);//构建Request
    if (logLevel != Logger.Level.NONE) {
      logger.logRequest(metadata.configKey(), logLevel, request);
    }

    Response response;
    long start = System.nanoTime();
    try {
	   // 调用Client.execute()方法发送网络请求
      response = client.execute(request, options);
      // ensure the request is set. TODO: remove in Feign 10
      response.toBuilder().request(request).build();
    } catch (IOException e) {
      if (logLevel != Logger.Level.NONE) {
        logger.logIOException(metadata.configKey(), logLevel, e, elapsedTime(start));
      }
      throw errorExecuting(request, e);
    }
    long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

    boolean shouldClose = true;
    try {
      if (logLevel != Logger.Level.NONE) {
        response =
            logger.logAndRebufferResponse(metadata.configKey(), logLevel, response, elapsedTime);
        // ensure the request is set. TODO: remove in Feign 10
        response.toBuilder().request(request).build();
      }
      if (Response.class == metadata.returnType()) {
        if (response.body() == null) {
          return response;
        }
        if (response.body().length() == null ||
                response.body().length() > MAX_RESPONSE_BUFFER_SIZE) {
          shouldClose = false;
          return response;
        }
        // Ensure the response body is disconnected
        byte[] bodyData = Util.toByteArray(response.body().asInputStream());
        return response.toBuilder().body(bodyData).build();
      }
      if (response.status() >= 200 && response.status() < 300) {
        if (void.class == metadata.returnType()) {
          return null;
        } else {
          return decode(response);
        }
      } else if (decode404 && response.status() == 404 && void.class != metadata.returnType()) {
        return decode(response);
      } else {
        throw errorDecoder.decode(metadata.configKey(), response);
      }
    } catch (IOException e) {
      if (logLevel != Logger.Level.NONE) {
        logger.logIOException(metadata.configKey(), logLevel, e, elapsedTime);
      }
      throw errorReading(request, response, e);
    } finally {
      if (shouldClose) {
        ensureClosed(response.body());
      }
    }
  }
```



```java
  Request targetRequest(RequestTemplate template) {
      // 调用所有的请求拦截器，为每个请求添加固定的header信息
    for (RequestInterceptor interceptor : requestInterceptors) {
      interceptor.apply(template);
    }
    return target.apply(new RequestTemplate(template));
  }
```

`Target`

```java
@Override
public Request apply(RequestTemplate input) {
  if (input.url().indexOf("http") != 0) {
    input.insert(0, url());
  }
  return input.request();
}
```



`ReflectiveFeign.BuildTemplateByResolvingArgs`

```java
@Override
    public RequestTemplate create(Object[] argv) {
      RequestTemplate mutable = new RequestTemplate(metadata.template());
      if (metadata.urlIndex() != null) {
        int urlIndex = metadata.urlIndex();
        checkArgument(argv[urlIndex] != null, "URI parameter %s was null", urlIndex);
        mutable.insert(0, String.valueOf(argv[urlIndex]));
      }
      Map<String, Object> varBuilder = new LinkedHashMap<String, Object>();
      // 遍历MethodMetaData中所有关于参数的索引及其对应名称的配置信息
      for (Entry<Integer, Collection<String>> entry : metadata.indexToName().entrySet()) {
        int i = entry.getKey();
        Object value = argv[entry.getKey()];
        if (value != null) { // Null values are skipped.
          // indexToExpander保存着将各种类型参数的值转换为string类型的Expander转换器
          if (indexToExpander.containsKey(i)) {
            //value-->str
            value = expandElements(indexToExpander.get(i), value);
          }
          for (String name : entry.getValue()) {
            varBuilder.put(name, value);
          }
        }
      }
	//生成RequestTemplate
      RequestTemplate template = resolve(argv, mutable, varBuilder);
      if (metadata.queryMapIndex() != null) {
        // add query map parameters after initial resolve so that they take
        // precedence over any predefined values
        //设置queryMap
        template = addQueryMapQueryParameters(argv, template);
      }
	//设置headermap
      if (metadata.headerMapIndex() != null) {
        template = addHeaderMapHeaders(argv, template);
      }

      return template;
    }
```



`Client.execute`(此处以OkHttp的方法举例，OkHttpClient)

```java
@Override
  public feign.Response execute(feign.Request input, feign.Request.Options options)
      throws IOException {
    okhttp3.OkHttpClient requestScoped;
    if (delegate.connectTimeoutMillis() != options.connectTimeoutMillis()
        || delegate.readTimeoutMillis() != options.readTimeoutMillis()) {
      requestScoped = delegate.newBuilder()
          .connectTimeout(options.connectTimeoutMillis(), TimeUnit.MILLISECONDS)
          .readTimeout(options.readTimeoutMillis(), TimeUnit.MILLISECONDS)
          .followRedirects(options.isFollowRedirects())
          .build();
    } else {
      requestScoped = delegate;
    }
    // 将feign.Request转换为OkHttp的Request对象
    Request request = toOkHttpRequest(input);
    // 使用OkHttp的同步操作发送网络请求
    Response response = requestScoped.newCall(request).execute();
    // 将OkHttp的Response转换为feign.Response
    return toFeignResponse(response, input).toBuilder().request(input).build();
  }
```































`@EnableFeignClients`

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FeignClientsRegistrar.class)
public @interface EnableFeignClients {
	//包扫描
	String[] value() default {};
	String[] basePackages() default {};
	Class<?>[] basePackageClasses() default {};
    //自定义feign client配置，可以配置Encoder Decoder Contract等
    //FeignClientsConfiguration是默认的
	Class<?>[] defaultConfiguration() default {};
    //指定被@FeignClient修饰的类，如果不为空，那么路径自动检测机制会被关闭
	Class<?>[] clients() default {};
}
```





















### Reference

- https://my.oschina.net/u/3768341/blog/3125397
- https://blog.csdn.net/u010145219/article/details/94437363
- https://blog.csdn.net/luanlouis/article/details/82821294





