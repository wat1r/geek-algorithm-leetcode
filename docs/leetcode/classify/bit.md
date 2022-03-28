

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

![](/imgs/leetcode/classify/image-20220328092831779.png)

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
