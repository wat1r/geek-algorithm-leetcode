package com.frankcooper.platform.leetcode.bank._101_200;

public class _151 {


    public static void main(String[] args) {
        _2nd handler = new _2nd();
        String s = "the sky is blue";
        handler.reverseWords(s);
    }

    static class _1st {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) return null;
            char[] chas = s.toCharArray();
            reverse(chas, 0, chas.length - 1);
            String t = String.valueOf(chas);
            String[] arr = t.trim().split("\\s+");
            StringBuilder ans = new StringBuilder();
            for (String a : arr) {
                char[] tmp = a.toCharArray();
                reverse(tmp, 0, a.length() - 1);
                ans.append(tmp);
                ans.append(" ");
            }
            return ans.toString().trim();
        }


        private void reverse(char[] chas, int l, int r) {
            while (l < r) {
                char tmp = chas[l];
                chas[l++] = chas[r];
                chas[r--] = tmp;
            }
        }
    }


    static class _2nd {
        /**
         * https://leetcode.com/problems/reverse-words-in-a-string/discuss/47720/Clean-Java-two-pointers-solution-(no-trim(-)-no-split(-)-no-StringBuilder
         */
        public String reverseWords(String s) {
            char[] chas = s.toCharArray();
            int n = chas.length;
            reverse(chas, 0, chas.length - 1);
            reverseWords(chas, n);
            return cleanSpaces(chas, n);
        }


        public String cleanSpaces(char[] chas, int n) {
            int l = 0, r = 0;
            while (r < n) {
                while (r < n && chas[r] == ' ') r++;
                while (r < n && chas[r] != ' ') chas[l++] = chas[r++];
                while (r < n && chas[r] == ' ') r++;
                if (r < n) chas[l++] = ' ';
            }
            return new String(chas).substring(0, l);
        }

        public void reverseWords(char[] chas, int n) {
            int l = 0, r = 0;
            while (l < chas.length) {
                while (l < r || l < n && chas[l] == ' ') l++;
                while (r < l || r < n && chas[r] != ' ') r++;
                reverse(chas, l, r - 1);
            }
        }


        public void reverse(char[] chas, int l, int r) {
            while (l < r) {
                char t = chas[l];
                chas[l++] = chas[r];
                chas[r--] = t;
            }
        }

    }

}
