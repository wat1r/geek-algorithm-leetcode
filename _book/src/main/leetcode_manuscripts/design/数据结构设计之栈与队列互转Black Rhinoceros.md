1

## 数据结构设计之栈与队列互转[Black Rhinoceros]

> **Black Rhinoceros**
>
>  黑犀牛，又叫尖吻犀、黑犀，是犀科黑犀属的唯一一种，产于非洲的肯尼亚、坦桑尼亚、喀麦隆、南非、纳米比亚和津巴布韦。尽管名叫黑犀，它们的体表颜色实际上更接近于灰白色，这个名字一般被用来区别于白犀，事实上这两种犀牛的区别不在于颜色，而主要在于体型，黑犀要比白犀小许多，吻部尖且能伸缩卷曲，头抬起，以树叶为食。
> 世界自然保护联盟红色名录列为：极危（CR） 

### 0.前言

- 栈：先进后出$FILO$
- 队列：先进先出$FIFO$

### 1.用栈实现队列

![1589032755526](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之栈与队列互转Black Rhinoceros.assets\1589032755526.png)

- 准备两个栈，一个数据栈$data$,一个辅助栈$help$
  - $push$时，只需要向$help$栈中推
  - $pop$时，只要去$data$的栈顶元素，即$data.pop()$,但是$data$栈没有元素了需要怎样?将$help$栈的元素不断往$data$栈推，推完为止，如果$data$,$help$栈均无元素，报错
  - $peek$时，与$pop$的过程一样，只是在返回的时候，$data.peek()$
  - $empty$，当$data$,$help$均为空时，返回$true$

  ```java
   class MyQueue {
          Stack<Integer> data;
          Stack<Integer> help;
   
          public MyQueue() {
              data = new Stack<>();
              help = new Stack<>();
          }
  
          public void push(int x) {
              help.push(x);
          }
  
          public int pop() {
              if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty queue");
              if (data.isEmpty()) {
                  while (!help.isEmpty()) data.push(help.pop());
              }
              return data.pop();
          }
  
          public int peek() {
              if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty queue");
              if (data.isEmpty()) {
                  while (!help.isEmpty()) data.push(help.pop());
              }
              return data.peek();
          }
  
          public boolean empty() {
              return data.isEmpty() && help.isEmpty();
          }
      }
  ```

  

### 2.用队列实现栈

![1589032826594](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之栈与队列互转Black Rhinoceros.assets\1589032826594.png)





#### 方法1:两个队列

![img](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之栈与队列互转Black Rhinoceros.assets\clipboard.png)
- 队列是先进先出，栈是先进后出，在$top()$方法中，为了避免，数据拷贝，有一个$swap()$的动作，$data$与$help$的存储顺序是一样的
- 因为$data$队列的元素始终存在，判断栈是否为空的时候，只需要关注$size(data)==0$
- $top()$与$pop()$方法只是在弹出$data$的最后一个元素的时候是否要继续放回$help$队列

```java
 class MyStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            data.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            while (data.size() != 1) help.add(data.poll());
            int res = data.poll();
            swap();
            return res;
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            while (data.size() != 1) help.add(data.poll());
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }


        private void swap() {
            Queue tmp = help;
            help = data;
            data = tmp;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return data.size() == 0;
        }
    }
```

#### 方法2:一个队列

- $shift()$方法是为$top()$ 与$pop()$方法准备的，做一件事：就是弹出队头到倒数第$2$个队尾元素的，将其送到队列的尾部，在执行$top()$ 与$pop()$，弹出$queue$的队头元素，如果是$top()$，继续保留这个元素，$pop()$时扔掉

```java
class MyStack {

    private Queue<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        shift();
        int res = queue.poll();
        return res;
    }

    /**
     * Get the top element.
     */
    public int top() {
        shift();
        int res = queue.poll();
        queue.offer(res);
        return res;
    }


    public void shift() {
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }

    }


    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
```

> 本文完



**PS**-->**github项目地址**:[**geek-algorithm-leetcode**](https://github.com/wat1r/geek-algorithm-leetcode )





