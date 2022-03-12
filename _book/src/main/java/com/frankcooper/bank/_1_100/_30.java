package com.frankcooper.bank._1_100;

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

}
