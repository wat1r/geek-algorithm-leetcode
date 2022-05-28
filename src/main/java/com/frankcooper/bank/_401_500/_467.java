package com.frankcooper.bank._401_500;

/**
 * @Date 2020/9/15
 * @Author Frank Cooper
 * @Description
 */
public class _467 {
    static class _0th {
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

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            String p = "zab";
            handler.findSubstringInWraproundString(p);
        }

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

    static class _2nd {
        //需要知道的性质：
        //性质1：
        //  以字母结尾的唯一子字符串的最大数目等于以该字母结尾的最大连续子字符串的长度。例如“abcd”，
        // 以“d”结尾的唯一子字符串的最大数目是4，显然它们是“abcd”、“bcd”、“cd”和“d”。
        //性质2：
        //如果有重叠，我们只需要考虑最长的一个，因为它覆盖了所有可能的子字符串。示例：“abcdbcd”，
        // 以“d”结尾的唯一子字符串的最大数目为4，并且由第二个“bcd”部分形成的所有子字符串都已包含在4个子字符串中。
        public int findSubstringInWraproundString(String p) {
            char[] ch = (" " + p).toCharArray();
            //dp[k] 表示 p 中以字符字符 k+'a' 结尾且在 s 中的子串的最长长度
            int[] dp = new int[26];
            int count = 1;
            for (int i = 1; i < ch.length; i++) {
                int k = ch[i] - 'a';
                if (check(ch[i - 1], ch[i])) {
                    count++;
                } else {
                    count = 1;
                }
                dp[k] = Math.max(dp[k], count);

            }
            int res = 0;
            for (int x : dp) res += x;
            return res;
        }

        private boolean check(char prev, char cur) {
            if (prev == 'z' && cur == 'a') return true;
            return cur - prev == 1;
        }
    }

    static class _2nd_1 {
        public int findSubstringInWraproundString(String p) {
            int[] count = new int[26];
            int maxLen = 0;
            for (int i = 0; i < p.length(); i++) {
                if (i > 0 && (p.charAt(i) - 'a' == (p.charAt(i - 1) - 'a' + 1) % 26)) {
                    maxLen++;
                } else {
                    maxLen = 1;
                }
                int index = p.charAt(i) - 'a';
                count[index] = Math.max(count[index], maxLen);
            }
            int res = 0;
            for (int i = 0; i < 26; i++) {
                res += count[i];
            }
            return res;
        }
    }
}
