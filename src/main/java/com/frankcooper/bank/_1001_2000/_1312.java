package com.frankcooper.bank._1001_2000;

public class _1312 {

    static _1312 handler = new _1312();

    public static void main(String[] args) {
//        System.out.println(handler.getPalindromeI("AB"));
//        Assert.assertEquals("ABA", handler.getPalindromeI("AB"));
        System.out.println(handler.getPalindromeII("A1BCC22DE1F", "1221"));
    }


    /**
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int[][] dp = buildDP(s.toCharArray());
        return dp[0][s.length() - 1];
    }


    public int[][] buildDP(char[] chas) {
        // $dp[i][j]$表示子串$str[i...j]$范围内的最少添加多少个字符后，可以形成回文子串
        int n = chas.length;
        int[][] dp = new int[n][n];
        for (int j = 1; j < n; j++) {
            dp[j - 1][j] = (chas[j - 1] == chas[j]) ? 0 : 1;
            for (int i = j - 2; i >= 0; i--) {
                if (chas[i] == chas[j]) dp[i][j] = dp[i + 1][j - 1];
                else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
            }
        }
        return dp;
    }


    public String getPalindromeI(String s) {
        char[] chas = s.toCharArray();
        int n = chas.length;
        int[][] dp = buildDP(chas);
        int appendLen = dp[0][n - 1];
        char[] res = new char[n + appendLen];
        int i = 0, j = n - 1;
        int left = 0, right = res.length - 1;
        while (i <= j) {
            if (chas[i] == chas[j]) {
                res[left++] = chas[i++];
                res[right--] = chas[j--];
            } else {
                if (dp[i][j - 1] < dp[i + 1][j]) {
                    res[left++] = chas[j];
                    res[right--] = chas[j--];
                } else if (dp[i][j - 1] >= dp[i + 1][j]) {
                    res[left++] = chas[i];
                    res[right--] = chas[i++];
                }
            }
        }
        return String.valueOf(res);
    }

    //res array 的左右指针，定义为全局变量
    int resLeft = 0, resRight = 0;

    public String getPalindromeII(String str, String strlps) {

        char[] chas = str.toCharArray();//str 目标字符串
        char[] lps = strlps.toCharArray();//base 字符串
        int n = chas.length, m = lps.length;//len
        char[] res = new char[2 * n - m];//res字符串 arr
        int chasLeft = 0, chasRight = n - 1;//str 的左右指针
        int lpsLeft = 0, lpsRight = m - 1;//base 的左右指针
        resRight = res.length - 1;
        int tmpLeft = 0, tmpRight = 0;//tmp 左右指针
        while (lpsLeft <= lpsRight) {//循环遍历lps字符
            tmpLeft = chasLeft;
            tmpRight = chasRight;
            while (chas[chasLeft] != lps[lpsLeft]) chasLeft++;//如果lpsLeft在chas未出现,一直chasLeft++
            while (chas[chasRight] != lps[lpsRight]) chasRight--;//如果lpsRight在chas未出现,一直chasRight--
            build(res, chas, chasLeft, chasRight, tmpLeft, tmpRight);//组装
//            int len = chasLeft - tmpLeft + tmpRight - chasRight;
//            resLeft += len;
//            resRight -= len;
            res[resLeft++] = chas[chasLeft++];//添加当前的lpsLeft ==lpsRight对于的字符串
            res[resRight--] = chas[chasRight--];//添加当前的lpsLeft ==lpsRight对于的字符串
            lpsLeft++;//进入下一轮
            lpsRight--;
        }
        return String.valueOf(res);
    }

    /**
     * @param res       结果arr
     *                  //     * @param resLeft
     *                  //     * @param resRight
     * @param chas      str arr
     * @param chasLeft  str arr left pointer
     * @param chasRight str arr right pointer
     * @param tmpLeft   tmp left pointer
     * @param tmpRight  tmp right pointer
     */
    private void build(char[] res,
                       char[] chas, int chasLeft, int chasRight,
                       int tmpLeft, int tmpRight) {

        for (int i = tmpLeft; i < chasLeft; i++) {
            res[resLeft++] = chas[i];
            res[resRight--] = chas[i];
        }
        for (int j = tmpRight; j > chasRight; j--) {
            res[resLeft++] = chas[j];
            res[resRight--] = chas[j];
        }
    }

    static class _1st {
        public int minInsertions(String s) {
            return helper(s, 0, s.length() - 1);
        }

        private int helper(String s, int start, int end) {
            if (start >= end) return 0;
            int ans;
            if (s.charAt(start) == s.charAt(end)) {
                ans = helper(s, start + 1, end - 1);
            } else {
                ans = Math.min(helper(s, start, end - 1), helper(s, start + 1, end)) + 1;
            }
            return ans;
        }

    }

    static class _2nd {
        Integer[][] memo;

        public int minInsertions(String s) {
            int n = s.length();
            memo = new Integer[n][n];
            return helper(s, 0, n - 1);
        }

        private int helper(String s, int start, int end) {
            if (memo[start][end] != null) return memo[start][end];
            if (start >= end) return 0;
            int ans;
            if (s.charAt(start) == s.charAt(end)) {
                ans = helper(s, start + 1, end - 1);
            } else {
                ans = Math.min(helper(s, start, end - 1), helper(s, start + 1, end)) + 1;
            }
            return memo[start][end] = ans;

        }
    }

    static class _3rd {
        public int minInsertions(String s) {
            // $dp[i][j]$表示子串$str[i...j]$范围内的最少添加多少个字符后，可以形成回文子串
            char[] chas = s.toCharArray();
            int n = chas.length;
            int[][] dp = new int[n][n];
            for (int j = 1; j < n; j++) {
                dp[j - 1][j] = (chas[j - 1] == chas[j]) ? 0 : 1;
                for (int i = j - 2; i >= 0; i--) {
                    if (chas[i] == chas[j]) dp[i][j] = dp[i + 1][j - 1];
                    else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
            return dp[0][s.length() - 1];
        }


    }

}


