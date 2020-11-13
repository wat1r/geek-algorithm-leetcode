## 区间DP之奇观的打印机[Parrot]

![ara-4330867_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\interval_dp\区间DP之奇观的打印机[Parrot].assets\ara-4330867_640.jpg)



![image-20201029201656555](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\interval_dp\区间DP之奇观的打印机[Parrot].assets\image-20201029201656555.png)

#### 定义状态

**`dp[i][j]`表示从s[i...j]这个字符区间，满足打印要求，最少的打印步骤**

#### 状态转移

![image-20201113093654625](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\interval_dp\区间DP之奇观的打印机[Parrot].assets\image-20201113093654625.png)

>  **对于上图的情况1**

当考虑`s[0...3]`,即 `abcd`这一段时，因为每个字符都不相等，也就是说每个字符都需要重新打印一遍

`dp[0][0]`  为字符  `a`， 打印1次 1 

`dp[0][1] ` 为字符`ab`  打印2次，2 可以先打印 `aa` 再在[1]位置打印`b` 变成`ab`,当然也可以先打印`a`,再在[2]位置打印`b`

`dp[0][2] `为字符 `abc` 打印3次 , 可以先打印`a` 再打印`b`,再打印`c`, 也可以`aaa` `bb` `c`这样打印

`dp[0][3]`为字符`abcd` 打印4次，同理可以得到打印的方案

>  综上总结，发现这些字符都不相同，所以方案是依赖前一种方案的，每个字符都出现了仅1次，即`dp[i]][j]` = `dp[i][j-1]+1`

---

> **对于上图的情况2.1**

当考虑`s[0...4]`，即`abcda`这一段时，前面的四个字符是各不相同的,`s[0]=s[4]`,依据上面的分析，`dp[0][3]=4`，但是当我们遇到`s[4]`即`a`字符时，这时候没有必要再重新打印一次了，因为在处理`s[0]`的打印的时候，可以顺便打印到[4]这个位置，即：

先打印[0...4]`aaaaa `

打印[1]位置 `b` 变成 `abaaa` 

打印[2]位置`c`变成`abcaa`

打印[3]位置`d`,变成`abcda`

这时候已经全部打印结束了，4次

---

> **对于上图的情况2.2**

当考虑`s[0...4]`,即`gbada`这一段时，前面的四个字符是各不相同的，`dp[0][3]=4`，`s[2]=s[4]`，在处理`s[2]`这个位置的时候，可以顺便处理到[4]这个位置

先打印[0] `g`

打印[1] `b` 变成 `gb`

打印[2..4] `aaa`变成 `gbaaa`

打印[3] `d`b变成 `gbada`

这时候已经全部打印结束了，4次

> 综上总结，这种情况下需要判断[i...j]中的k位置，是否与存在重复的字符



```java
    public int strangePrinter(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n; i++) {
                int j = i + len - 1;//
                if (j >= n) break;
                dp[i][j] = dp[i + 1][j] + 1;
                for (int k = i + 1; k <= j; k++) {
                    if (s.charAt(i) == s.charAt(k)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
                    }
                }

            }
        }
        return dp[0][n - 1];
    }
```

