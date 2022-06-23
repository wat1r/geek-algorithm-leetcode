package com.frankcooper.platform.leetcode.bank._1_100;

import org.junit.Assert;

import java.util.*;

public class _30 {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "barfoothefoobarman";
            String[] words = {"foo", "bar"};
            words = new String[]{"word", "good", "best", "word"};
            handler.findSubstring(s, words);

        }


        public List<Integer> findSubstring(String s, String[] words) {


            HashMap<String, Integer> map = new HashMap<>();
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);

            }
            int step = words[0].length();
            int l = 0, r = step;
            while (l < words.length) {
                String d = s.substring(l, r);
                if (map.containsKey(d) && map.get(d) > 0) {
                    r += step;
                }
            }


            return null;
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "barfoothefoobarman";
            String[] words = new String[]{"foo", "bar"};
            Assert.assertEquals(handler.findSubstring(s, words), Arrays.asList(0, 9));
            s = "wordgoodgoodgoodbestword";
            words = new String[]{"word", "good", "best", "good"};
            Assert.assertEquals(handler.findSubstring(s, words), Arrays.asList(8));
        }

        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            HashMap<String, Integer> map = new HashMap<>();
            int w = 0, k = 0;
            for (String word : words) {
                w += word.length();
                k = word.length();
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (int i = 0; i <= s.length() - w; i++) {//注意 =
                String curr = s.substring(i, i + w);
                HashMap<String, Integer> sub = new HashMap<>();
                for (int j = 0; j < curr.length(); j += k) {
                    String seg = curr.substring(j, j + k);
                    sub.put(seg, sub.getOrDefault(seg, 0) + 1);
                }
                if (map.equals(sub)) res.add(i);
            }
            return res;
        }
    }


    static class _3rd {
        public List<Integer> findSubstring(String s, String[] words) {
            Map<String, Integer> dict = new HashMap<>();//words的字典map，k为当前单词word v为出现的次数
            int len = 0, w_len = 0; //总的长度，当个单词的长度，每个单词的长度都是相等的，只需要记录一个即可
            for (String word : words) {
                len += word.length();
                w_len = word.length();
                dict.put(word, dict.getOrDefault(word, 0) + 1);
            }
            int n = s.length();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i + len - 1 < n; i++) {
                Map<String, Integer> can = new HashMap<>();//候选的map
                String sub = s.substring(i, i + len);
                // System.out.println(sub);
                //以每次w_len的步长切分单词
                for (int j = 0; j < len; j += w_len) {
                    String seg = sub.substring(j, j + w_len);
                    can.put(seg, can.getOrDefault(seg, 0) + 1);
                }
                //dict与can一致的时候，说明可以形成
                if (dict.equals(can)) {
                    res.add(i);
                }
            }
            return res;
        }
    }
}
