

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

