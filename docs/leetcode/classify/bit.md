

> 

## 汉明距离







## [面试题 01.01. 判定字符是否唯一](https://leetcode-cn.com/problems/is-unique-lcci/)

### 方法1:数组

```java
public boolean isUnique(String astr) {
    char[] ch = new char[26];
    for (char c : astr.toCharArray()) {
        if (ch[c - 'a'] != 0) return false;
        ch[c - 'a']++;
    }
    return true;
}
```

### 方法2:位运算

![](/imgs/leetcode/classify/image-20220322194544584.png)

```java
        public boolean isUnique(String astr) {
            int mark = 0;
            for (char ch : astr.toCharArray()) {
                //拿到每个字符的移动的位数 [0-25]开始
                int bit = ch - 'a';
                //如果这一位被对冲掉，返回false
                if ((mark & (1 << bit)) != 0) {
                    return false;
                } else {
                    //拼凑mark
                    mark |= (1 << bit);
                }
//                PrintUtils.toBinaryString(mark, 26);
            }
            return true;
        }
```

> **延伸阅读：[位运算操作常见技巧](https://blog.csdn.net/wat1r/article/details/114298873)**





## [693. 交替位二进制数](https://leetcode-cn.com/problems/binary-number-with-alternating-bits/)

### 方法1:基本位运算

```java
public boolean hasAlternatingBits(int n) {
    int t = -1;//初始值
    while (n > 0) {
        //拿到最右边的一位
        int bit = n & 1;
        //如果当前的bit位于上一位t是相同的，则返回false
        //异或（ ^ ）每一位进行比较，相同为0，不同为1
        if ((t ^ bit) == 0) return false;
        //当前位bit 赋值给上一位 t
        t = bit;
        //右移一位
        n >>= 1;
    }
    return true;
}
```

### 方法2:高阶位运算

 ![](/imgs/leetcode/classify/image-20220328191800195.png)

```java
public boolean hasAlternatingBits(int n) {
    n = n ^ (n >> 1);
    return (n & n + 1) == 0;
}
```

### 方法3:库函数

```java
public boolean hasAlternatingBits(int n) {
  	//调用库函数
    char[] bits = Integer.toBinaryString(n).toCharArray();
    for (int i = 0; i < bits.length - 1; i++) {
        if (bits[i] == bits[i + 1]) {
            return false;
        }
    }
    return true;
}
```





## [762. 二进制表示中质数个计算置位](https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/)

### 方法1:模拟

- 数据范围不大，模拟即可，两步走，
  - step1.统计每一个数二进制数中1的个数，记为`bits`
  - step2.判断`bits`是否是质数

- 其中step1的操作:

![](/imgs/leetcode/classify/image-20220405094541146.png)

```java
统计二进制1的个数可以分别获取每个二进制位数，然后再统计其1的个数，此方法效率比较低。这里介绍另外一种高效的方法，同样以 34520 为例，我们计算其 a &= (a-1)的结果：
方法1：
- 第一次：计算前：1000 0110 1101 1000 计算后：1000 0110 1101 0000
- 第二次：计算前：1000 0110 1101 0000 计算后：1000 0110 1100 0000
- 第三次：计算前：1000 0110 1100 0000 计算后：1000 0110 1000 0000 我们发现，每计算一次二进制中就少了一个 1，则我们可以通过下面方法去统计：
    
count = 0  
while(a){  
  a = a & (a - 1);  
  count++;  
}  
方法2：见下面的「返回i的二进制最低位位1的权值」
count = 0  
while(a){  
  a -= a & (-a);  
  count++;  
}  
方法3：
count = 0  
while(a){  
  count+=a&1;  
  a >>>= 1;//无符号右移 相当于 /2 
}
```

**更加详细的位操作技巧，阅读[位运算操作常见技巧](https://blog.csdn.net/wat1r/article/details/114298873)（100+收藏，5K阅读）**

```java
public int countPrimeSetBits(int left, int right) {
    int res = 0;
    for (int a = left; a <= right; a++) {
        if (isPrime(count(a))) res++;
    }
    return res;
}


public int count(int a) {
    int cnt = 0;
    while (a > 0) {
        cnt += a & 1;
        a >>= 1;
    }
    return cnt;
}


/**
 * 判断一个数是否是素数
 */
public boolean isPrime(int a) {
    if (a < 2) return false;
    for (int i = 2; i * i <= a; i++) {
        if (a % i == 0) return false;
    }
    return true;
}
```

### 方法2:打表

- 注意到数据的范围是100000，转成二进制是11110100001001000000，20位，也就是说每一位都为1的话，也只有20位，需要拿到20位以下质数打表

```java
public int countPrimeSetBits(int left, int right) {
    List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
    int cnt = 0;
    for (int i = left; i <= right; i++) {
        int bits = 0;
        for (int x = i; x > 0; x >>= 1) {
            bits += x & 1;
        }
        if (primes.contains(bits)) cnt++;
    }
    return cnt;
}
```

### 方法3:DP打表

- 质数的结果可以通过打表的方式获取，每个数的位数也可以通过打表的方式获取
- `f[i]`表示当前数`i`的二进制1的个数
  - 初始时0的二进制1的个数是0,1的二进制1的个数是1个
  - 一般情况下`f[i]`与`i>>1`的结果有关，判断下当前位是否是1即可`f[i & 1]`，

```java
public int countPrimeSetBits(int left, int right) {
    List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
    int[] f = count(right);
    int cnt = 0;
    for (int i = left; i <= right; i++) {
        if (primes.contains(f[i])) cnt++;
    }
    return cnt;
}


public int[] count(int x) {
    if (x == 0) return new int[]{};
    int[] f = new int[x + 1];
    f[0] = 0;
    f[1] = 1;
    for (int i = 2; i <= x; i++) {
        f[i] = f[i >> 1] + f[i & 1];
    }
    return f;
}
```





### Follow Up

> **如果`left`和`right`的数据范围不是10^6而扩大到10亿，如何优化算法**

- 我能想到的是通过埃筛的方式快速的筛质数，然后打表
- 你会如何做呢？欢迎评论区留言~





## [868. 二进制间距](https://leetcode-cn.com/problems/binary-gap/)

![](/imgs/leetcode/classify/image-20220424070227853.png)

```java
public int binaryGap(int n) {
    //前一个为1的位置的索引，当前的索引
    int prev = -1, cur = 0;
    int gap = 0;
    while (n > 0) {
        //记录下当前位为1的索引，如果满足更新gap的条件，开始更新
        if ((n & 1) == 1) {
            if (prev != -1) gap = Math.max(gap, cur - prev);
            prev = cur;
        }
        n >>= 1;//向右移位，去掉一位
        PrintUtils.toBinaryString(n,8);
        cur++;//当前位+1
    }
    return gap;
}
```





### 扩展阅读


- 更加详细的位操作技巧，阅读[**位运算操作常见技巧**](https://blog.csdn.net/wat1r/article/details/114298873)（100+收藏，5K阅读）

- [【阿飞算法】图解804唯一摩尔斯密码词(Hash/位运算)](https://leetcode-cn.com/problems/unique-morse-code-words/solution/by-a-fei-8-1p1x/)
- [【阿飞算法】图解762.二进制表示中质数个计算置位](https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/solution/by-a-fei-8-ucsg)
- [【阿飞算法】图解693交替位二进制数](https://leetcode-cn.com/problems/binary-number-with-alternating-bits/solution/a-fei-suan-fa-by-a-fei-8-es27/)
- [【阿飞算法】面试题 01.01. 判定字符是否唯一（位运算图解，附位运算链接）](https://leetcode-cn.com/problems/is-unique-lcci/solution/by-a-fei-8-pqij/)
