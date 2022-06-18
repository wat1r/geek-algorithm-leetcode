package com.frankcooper.platform.leetcode.bank._501_600;

/**
 * Created by FrankCooper
 * Date 2020/3/20 21:43
 * Description
 */
//516. 最长回文子序列 516. Longest Palindromic Subsequence Medium
public class _516 {
    public static void main(String[] args) {

    }

    /*
        ##### 方法1：`DP`

- 定义`dp[i][j]`:表示`s[i...j]`之间的最长子序列的长度，**注意是子序列，不是子串，子序列是可以跳跃的，子串不可以**
  - 当`s[i]==s[j]`时，说明`i`与`j`位置的字符可以形成一个回文，这个回文的长度为`2`,根据`dp`的思想，其结果应该是依赖前面的结果,也就是`s[i+1 .... j-1]`这个范围的字符回文个数，也就是`dp[i+1][j-1]`，即$dp[i][j]=dp[i+1][j-1]+2$
  - 当`s[i]!=s[j]`时，说明`i`与`j`位置的字符不能形成一个回文，这个时候要看$s[i+1...j]$与$s[i...j-1]$这两段，因为$s[i+1]$可能与$s[i+2...j]$范围内的某个字符相同，拼凑出回文，因为$s[i]!=s[j]$,同理可得$s[i...j-1]$这段，故此，$dp[i][j]=max{dp[i][j-1],dp[i+1][j]}$

- `base case`:
  - 很容想到的是`i==j`时，说明`s[i...j]`只有一个字符，此时其自身可以形成一个回文，长度为`1`
  - 当`i>j`时，此时是不存在的，因为我们规定了`s[i...j]`起始位置`i`要小于结束位置`j`的，此时初始化为`0`
- 有两种遍历方式
  - 斜着遍历
  - 倒着遍历
- 返回结果$dp[0][n-1]$其实就是$s[0...n-1]$的最长回文子序列的长度
     */
    public int longestPalindromeSubseq1st(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }


    /*
        ##### 方法2：记忆化递归

- 准备一个$ helper(String s, int i, int j, Integer[][] memo)$函数，其中`s`是字符串本身,`i`与`j`是起始位置，`memo`记录的是字符的最长子序列长度,$memo[0][n-1]$为所求
- 记忆化：当`memo[i][j]`不为`null`的时候，说明不是初始化的值，被求解过，直接返回
- 出口条件：
  - 当`i>j`时，返回`0`，因为没有意义，我们要求的起始位置`i`要小于`j`的
  - 当`i==j`时，返回`1`，只有一个字符，可以形成回文，即是其本身，长度为`1`

- 递归逻辑：

  - 当`s[i]==s[j]`时，说明需要在$helper(i+1,j-1)$基础上`+2`
  - 当`s[i]!=s[j]`时，要取$max[helper(i+1,j),helper(i,j-1)]$

     */
    public int longestPalindromeSubseq2nd(String s) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return helper(s, 0, n - 1, memo);
    }

    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        if (i > j) return 0;
        if (i == j) return 1;
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }


    static class _1st {


        public int longestPalindromeSubseq(String s) {
            return findLPSLengthRecursive(s, 0, s.length() - 1);
        }


        private int findLPSLengthRecursive(String s, int start, int end) {
            if (start == end) return 1;
            if (start > end) return 0;
            int ans = 0;
            if (s.charAt(start) == s.charAt(end)) {
                ans = findLPSLengthRecursive(s, start + 1, end - 1) + 2;
            } else {
                ans = Math.max(findLPSLengthRecursive(s, start + 1, end),
                        findLPSLengthRecursive(s, start, end - 1));
            }
            return ans;
        }


    }


    static class _5th {


        public static void main(String[] args) {
            _5th handler = new _5th();
            handler.minDelBuildPalindrome("bbbab");
        }

        Integer[][] memo;

        public int minDelBuildPalindrome(String s) {
            memo = new Integer[s.length()][s.length()];
            return s.length() - helper(s, 0, s.length() - 1);
        }

        private int helper(String s, int start, int end) {
            if (memo[start][end] != null) return memo[start][end];
            if (start == end) return 1;
            if (start > end) return 0;
            int ans;
            if (s.charAt(start) == s.charAt(end)) {
                ans = helper(s, start + 1, end - 1) + 2;
            } else {
                ans = Math.max(helper(s, start, end - 1), helper(s, start + 1, end));
            }
            System.out.println(ans);
            return memo[start][end] = ans;
        }
    }


    static class _7th {

        public static void main(String[] args) {
            _7th handler = new _7th();
            handler.minDelBuildPalindrome("bbbab");
        }

        public int minDelBuildPalindrome(String s) {
            int n = s.length();
            int[][] f = new int[n][n];
            for (int i = n - 1; i >= 0; i--) {
                f[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) f[i][j] = f[i + 1][j - 1] + 2;
                    else f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }

            return n - f[0][n - 1];
        }
    }
}
