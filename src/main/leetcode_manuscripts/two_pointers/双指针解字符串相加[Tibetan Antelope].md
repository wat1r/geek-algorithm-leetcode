## 双指针解字符串相加[Tibetan Antelope]

![antelope-5423703_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解字符串相加[Tibetan Antelope].assets\antelope-5423703_640.jpg)

![image-20200803212249096](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解字符串相加[Tibetan Antelope].assets\image-20200803212249096.png)

采用双指针遍历的方式模拟加法的原理

#### 核心思想

- $carry$表示进位，大于等于10的时候回产生进位   = $tmp$%$10$

- $remian$表示当前计算下留下来的值 = $tmp$/$10$

#### 边界处理

- 当双指针走到最左边的位置时，这时候发现如果$nums1$与$nums2$其中一个出现空置的情况下，给其补位$0$
- 最后游走结束时，$carry$需要被追加，即$carry != 0$这个条件，当然也可与不在$while$写此条件，在结尾进行追加

```java
res.append(carry);
```

- 最后的结果要进行翻转，我们从最右侧开始的



![image-20200803211536262](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解字符串相加[Tibetan Antelope].assets\image-20200803211536262.png)

```java
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

