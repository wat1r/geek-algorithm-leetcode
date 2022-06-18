package com.frankcooper.platform.leetcode.bank._1_100;


import java.util.HashMap;

public class _87 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s1 = "great", s2 = "rgeat";
            handler.isScramble(s1, s2);

        }

        HashMap<String, HashMap<String, Boolean>> memo = new HashMap<>();


        public boolean isScramble(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
            if (s1.equals(s2)) return true;
            int[] arr = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                arr[s1.charAt(i) - 'a']++;
                arr[s2.charAt(i) - 'a']--;
            }
            for (int i = 0; i < 26; i++) if (arr[i] != 0) return false;
            if (s1.length() <= 3) return true;
            if (memo.containsKey(s1) && memo.get(s1).containsKey(s2)) return memo.get(s1).get(s2);
            for (int i = 1; i < s1.length(); i++) {
                if ((isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
                        || (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))
                ) {
                    HashMap<String, Boolean> inner = memo.getOrDefault(s1, new HashMap<>());
                    inner.put(s2, true);
                    memo.put(s1, inner);
                    return true;
                }
            }
            HashMap<String, Boolean> inner = memo.getOrDefault(s1, new HashMap<>());
            inner.put(s2, false);
            memo.put(s1, inner);
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
