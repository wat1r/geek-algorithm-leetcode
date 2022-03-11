package com.frankcooper.bank._401_500;

/**
 * @Date 2020/9/15
 * @Author Frank Cooper
 * @Description
 */
public class _467 {
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) return 0;
        int[] dp = new int[26];
        char[] chas = (" " + p).toCharArray();
        int count = 1;
        for (int i = 1; i < chas.length; ++i) {
            int idx = chas[i] - 'a';
            if (isContinue(chas[i - 1], chas[i])) count++;
            else count = 1;
            dp[idx] = Math.max(dp[idx], count);
        }
        int res = 0;
        for (int num : dp) res += num;
        return res;
    }


    public boolean isContinue(char a, char b) {
        if (a == 'z' && b == 'a') return true;
        return b - a == 1;
    }
}
