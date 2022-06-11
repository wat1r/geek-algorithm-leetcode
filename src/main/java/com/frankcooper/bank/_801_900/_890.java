package com.frankcooper.bank._801_900;

import java.util.*;

import org.junit.Assert;

public class _890 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> res = new ArrayList<>();
            for (String word : words) {
                if (check(word, pattern) && check(pattern, word)) res.add(word);
            }
            return res;
        }


        public boolean check(String source, String target) {
            Map<Character, Character> map = new HashMap<>();
            for (int i = 0; i < source.length(); i++) {
                //相同字符映射的字符应该是唯一的
                char s = source.charAt(i), t = target.charAt(i);
                if (!map.containsKey(s)) {
                    map.put(s, t);
                } else if (map.get(s) != t) {
                    return false;
                }
            }
            return true;
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
