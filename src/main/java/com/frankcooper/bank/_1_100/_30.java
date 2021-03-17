package com.frankcooper.bank._1_100;

import java.util.*;

public class _30 {


    static _30 handler = new _30();

    public static void main(String[] args) {

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
