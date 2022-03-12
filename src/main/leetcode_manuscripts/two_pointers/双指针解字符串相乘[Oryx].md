## 双指针解字符串相乘[Oryx]



![art-4837482_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解字符串相乘[Tibetan Antelope].assets\art-4837482_640.jpg)

![image-20200813082826213](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解字符串相乘[Tibetan Antelope].assets\image-20200813082826213.png)

### 方法1：竖式挨个相乘

#### 思路

- 用$num2$的每一个数与$num1$挨个相乘，记录$carry$值，将每一轮结束的值与$res$进行字符串相加，思路是415题字符串相加，方法直接拷贝来

- 难点有两个

  - 如何在第二轮以上进行末尾补零

  ```java
  int k = len2 - 1 - i;
  while (k > 0) {
      sb.append("0");
      k--;
  }
  ```

  - 部分$case$的不过，如$9$*$9$

  ```java
  || carry != 0
  int n1 = j < 0 ? 0 : num1.charAt(j) - '0';  
  ```

#### 完整代码

```java
    public String multiply(String num1, String num2) {
        String res = "0";
        if (num1.equals("0") || num2.equals("0")) return res;
        int len1 = num1.length(), len2 = num2.length();
        for (int i = len2 - 1; i >= 0; i--) {
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            int k = len2 - 1 - i;
            while (k > 0) {
                sb.append("0");
                k--;
            }
            int n2 = num2.charAt(i) - '0';
            for (int j = len1 - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int seg = n2 * n1 + carry;
                sb.append(seg % 10);
                carry = seg / 10;
            }
            res = addStrings(res, sb.reverse().toString());
        }
        return res;
    }


    public String addStrings(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int i = m - 1, j = n - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            int tmp = 0;
            int first = i < 0 ? 0 : num1.charAt(i) - '0';
            int second = j < 0 ? 0 : num2.charAt(j) - '0';
            tmp = first + second + carry;
            carry = tmp / 10;
            int remain = tmp % 10;
            res.append(remain);
            i--;
            j--;
        }
        return res.reverse().toString();
    }
```

