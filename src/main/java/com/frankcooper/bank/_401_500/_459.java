package com.frankcooper.bank._401_500;

import org.junit.Assert;

/**
 * @Date 2020/8/24
 * @Author Frank Cooper
 * @Description
 */
public class _459 {


    static class _1st {
        static _459 handler = new _459();


        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "abcabcabcabc";
            Assert.assertTrue(handler.repeatedSubstringPattern(s));
            s = "aba";
            Assert.assertFalse(handler.repeatedSubstringPattern(s));
            s = "abab";
            Assert.assertTrue(handler.repeatedSubstringPattern(s));
            s = "abaababaab";
            Assert.assertTrue(handler.repeatedSubstringPattern(s));
            s = "a";


        }


        /**
         * 错误解法，解不了"abaababaab"
         *
         * @param s
         * @return
         */
        public boolean repeatedSubstringPattern(String s) {
            char[] chas = s.toCharArray();
            int[] arr = new int[26];
            String seed = "";
            int i = 0;
            for (; i < chas.length; i++) {
                if (arr[chas[i] - 'a'] == 1) break;
                arr[chas[i] - 'a']++;
                seed += chas[i];
            }
            int j = i;
            for (; j < chas.length; j++) {
                if (seed.charAt(j % i) != chas[j]) return false;
            }
            return j != i && j % i == 0;
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "abcabcabcabc";
            s = "abaababaab";
            s = "abc";
            Assert.assertTrue(handler.repeatedSubstringPattern(s));
        }


//        public boolean repeatedSubstringPattern(String s) {
//            String S = s + s;
//            return S.substring(1, S.length() - 1).contains(s);
//        }


        public boolean repeatedSubstringPattern(String s) {
            return (s + s).indexOf(s, 1) != s.length();
        }

//        public boolean repeatedSubstringPattern(String s) {
//            int n = s.length();
//            for (int i = 1; i * 2 <= n; ++i) {
//                if (n % i == 0) {
//                    boolean match = true;
//                    for (int j = i; j < n; ++j) {
//                        if (s.charAt(j) != s.charAt(j - i)) {
//                            match = false;
//                            break;
//                        }
//                    }
//                    if (match) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }


//        public boolean repeatedSubstringPattern(String s) {
////            (s+s).in
//
//
//            if (s == null || s.length() < 1) return false;
//            int len = s.length();
//            String str = s;
//            while (len > 1) {
//                str = str.charAt(s.length() - 1) + str.substring(0, s.length() - 1);
//                //if(str.contains(s)) return true;
//                if (str.equals(s)) return true;
//                len--;
//            }
//            return false;
//        }
    }


}
