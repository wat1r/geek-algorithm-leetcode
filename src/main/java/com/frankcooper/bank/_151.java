package com.frankcooper.bank;

public class _151 {


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

        
    }

}
