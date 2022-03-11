package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _10_02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
            handler.groupAnagrams(strs);

        }


        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> res = new ArrayList<>();
            HashMap<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chas = str.toCharArray();
                Arrays.sort(chas);//排序key，做成统一的key
                String k = String.valueOf(chas);
                map.putIfAbsent(k, new ArrayList<>());
                map.get(k).add(str);
            }
            for (List<String> v : map.values()) {
                res.add(new ArrayList<>(v));
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
