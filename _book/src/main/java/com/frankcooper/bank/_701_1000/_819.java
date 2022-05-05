package com.frankcooper.bank._701_1000;

import java.util.HashMap;

import java.util.*;

import org.junit.Assert;

public class _819 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
            String[] banned = {"hit"};
            Assert.assertEquals("ball", handler.mostCommonWord(paragraph, banned));


        }

        public String mostCommonWord(String paragraph, String[] banned) {
            Set<String> set = new HashSet<>(Arrays.asList(banned));
            Map<String, Integer> map = new HashMap<>();
            int n = paragraph.length();
            int maxx = 0;//最大的次数
            String res = "";//结果集
            for (int i = 0; i < n; ) {
                while (i < n && !Character.isLetter(paragraph.charAt(i))) {
                    i++;
                }
                int j = i;
                while (j < n && Character.isLetter(paragraph.charAt(j))) {
                    j++;
                }
                String sub = paragraph.substring(i, j).toLowerCase();
//                System.out.println(sub);
                i = j;
                if (set.contains(sub)) continue;
                map.put(sub, map.getOrDefault(sub, 0) + 1);
                if (map.get(sub) > maxx) {
                    maxx = map.get(sub);
                    res = sub;
                }

            }
            return res;
        }


        private String toLowercase(String s) {

            return "";
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
