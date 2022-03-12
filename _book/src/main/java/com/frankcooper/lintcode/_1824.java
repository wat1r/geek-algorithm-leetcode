package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _1824 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "abcde";
            int minLength = 2, maxLength = 5, maxUnique = 3;
            handler.getMaxOccurrences(s, minLength, maxLength, maxUnique);

        }


        public int getMaxOccurrences(String s, int minLength, int maxLength, int maxUnique) {
            int[] arr = new int[26];
            int j = 0;
            int unique_char = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                while (j < s.length() && j - i + 1 <= minLength && unique_char <= maxUnique) {
                    arr[s.charAt(j) - 'a']++;
                    if (arr[s.charAt(j) - 'a'] == 1) unique_char++;
                    j++;
                }
                if (j < s.length() && minLength == j - i && unique_char <= maxUnique) {
                    String k = s.substring(i, j);
                    map.put(k, map.getOrDefault(k, 0) + 1);
                }
                arr[s.charAt(i) - 'a']--;
                if (arr[s.charAt(i) - 'a'] == 0) unique_char--;
            }
            int res= 0;
            for (String k : map.keySet()) {
                res = Math.max(res,map.get(k));
            }
            return res;
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
