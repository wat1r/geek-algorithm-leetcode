## 【重温经典】Pow(x, n)

![image-20211110210455718](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211110210455718.png)

二进制可以转化成十进制，假设十进制正整数n，其二进制的表示方式是：「$b_m...b_i...b_2b_1b_0$​​​」

则 $n=2^0b_0+2^1b_1+2^2b_2+...+2^ib_i+...2^mb_m$​

则$x^n=x^{(2^0b_0+2^1b_1+2^2b_2+...+2^ib_i+...2^mb_m)}$​

由以上的结果，可以将$x^n$转化为如下的两个问题:

- 计算$x^1,x^2...x^{(2^m)}$​的结果，需要叠加$x$的结果
- 获取二进制的各位$b_m...b_i...b_2b_1b_0$​的值

![image-20211110210440593](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211110210440593.png)

### 方法1：位运算

```java
    public double myPow(double x, int n) {
            //转化成long类型，进行移位操作
            long N = n;
            //当n为负数的时候，x^n = 1/(x^(-n))
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double res = 1.0;
            while (N != 0) {
                if ((N & 1) == 1) res *= x;//多余一位x累乘到res
                x *= x;//底数x翻倍
                N >>= 1;//N减半
            }
            return res;
        }
```

### 方法2：二分

```java
    public double myPow(double x, int n) {
            double res = 1.0;
            for (int i = n; i != 0; i /= 2) {
                if ((i & 1) == 1) {
                    res *= x;
                }
                x *= x;
            }
            //如 x = 2.00000   n=-2这样的case，当n是负数的时候，取倒数
            return n < 0 ? 1 / res : res;
        }
```



### 方法3：递归

注意：对于`Integer.MIN_VALUE`这个值来说即`-2147483648` 取相反数的时候还是`-2147483648`,而将其+1后取相反数即`-(-2147483648+1) `得到 `2147483647`即`Integer.MAX_VALUE`

```java
       public double myPow(double x, int n) {
            if (n == 0) return 1.0;
            //对n<0的情况单独判断处理 当n为负数的时候，取相反数 -(n+1)
            if (n < 0) {
                return 1 / x * myPow(1 / x, -(n + 1));
            }
            return ((n % 2) == 1) ? x * myPow(x * x, n / 2) : myPow(x * x, n / 2);
        }
```

> **另外一种写法**

```java
        public double myPow(double x, int n) {
            if (n == 0) return 1;
            //对于MIN_VALUE这个数时，特判
            if (n == Integer.MIN_VALUE) {
                x = x * x;
                n = n / 2;
            }
            //n为负数的时候，取相反数
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }
            return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
        }
```









