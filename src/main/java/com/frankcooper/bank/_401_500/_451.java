package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _451 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "tree";
            handler.frequencySort(s);
        }


        public String frequencySort(String s) {

//
            int[] arr = new int[126];
            for (char c : s.toCharArray()) {
                arr[c]++;
            }
            //[0]是出现的次数，[1]是字符
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    pq.offer(new int[]{arr[i], i});
                }
            }
            StringBuilder res = new StringBuilder();
            while (!pq.isEmpty()) {
                int[] x = pq.poll();
                int t = x[0];
                while (t-- > 0) res.append((char) x[1]);
            }
            return res.toString();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
        //hashmap 排序

        public String frequencySort(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            final Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char chas = s.charAt(i);
                map.put(chas, map.getOrDefault(chas, 0) + 1);
            }
            ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<Character, Integer> entry : list) {
                for (int i = 0; i < entry.getValue(); i++) {
                    sb.append(entry.getKey());
                }
            }
            return sb.toString();
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
