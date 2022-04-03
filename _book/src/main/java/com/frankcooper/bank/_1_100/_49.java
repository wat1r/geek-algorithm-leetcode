package com.frankcooper.bank._1_100;

import java.util.*;
import java.util.stream.Collectors;

public class _49 {


    static class _0th {
        public static void main(String[] args) {
            _0th handler = new _0th();
        }

        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs == null || strs.length == 0) return new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            for (String s : strs) {
                char[] ca = new char[26];
                for (char c : s.toCharArray()) ca[c - 'a']++;
                String keyStr = String.valueOf(ca);
                if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
                map.get(keyStr).add(s);
            }
            return new ArrayList<>(map.values());
        }
    }

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> result = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                int[] helper = new int[26];
                char[] chas = str.toCharArray();
                for (char c : chas) {
                    helper[c - 'a']++;
                }
                StringBuilder sb = new StringBuilder();
                for (int h : helper) {
                    sb.append(h);
                }
                map.putIfAbsent(sb.toString(), new ArrayList<>());
                map.get(sb.toString()).add(str);
            }
            result = new ArrayList<>(map.values());
            return result;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<List<String>> groupAnagrams(String[] strs) {
            return new ArrayList<>(Arrays.stream(strs)
                    .collect(Collectors.groupingBy(str -> {
                        int[] counter = new int[26];
                        for (int i = 0; i < str.length(); i++) {
                            counter[str.charAt(i) - 'a']++;
                        }
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < 26; i++) {
                            if (counter[i] != 0) {
                                sb.append((char) ('a' + i));
                                sb.append(counter[i]);
                            }
                        }
                        return sb.toString();
                    })).values());
        }
    }




    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
