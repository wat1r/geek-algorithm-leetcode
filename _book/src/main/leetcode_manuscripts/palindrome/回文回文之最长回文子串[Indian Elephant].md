## 回文回文之最长回文子串[Indian Elephant]





### 方法1:DP

#### 定义状态

- $dp[i][j]$表示字符串$s[i...j]$范围内的子串是否是回文，$s[i...j]$指的是从$s[i]$到$s[j]$

#### 转移方程

![2020-05-21_082740](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\palindrome\回文回文之最长回文子串[Indian Elephant].assets\2020-05-21_082740.png)

- 如图，注意红色虚框与绿色虚框
- 当$s[i]!=s[j]$时，表示当前$s[i...j]$无法形成回文，$dp[i][j]=F$
- 当$s[i]==s[j]$时，表示当前$s[i...j]$是否可以形成回文要看其前状态，即$dp[i+1][j-1]$

#### 边界条件

- 写的时候需要注意下子串长度只有一个时，其自身可以形成回文，$dp[i][j]=T$,在做$dp$表时，是对角线上的的位置
- 子串长度有两个或者三个时，其只依赖$s[i]$是否与$s[j]$相等

#### 其它

- 在过程中记录最长的子串的长度，并且截取子串的整体

```java
public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chas = s.toCharArray();
        int n = chas.length;
        boolean[][] dp = new boolean[n][n];
        String res = "";
        int max = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                //判断当前的dp[i][j]是否是T，结合边界条件
                if (chas[i] == chas[j] && ((j - i) <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
                //如果是回文，并且当前的是最长回文，记录子串并且更新最长的子串长度s
                if (dp[i][j] && max < (j - i + 1)) {
                    max = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
```











### 方法2:中心扩展



