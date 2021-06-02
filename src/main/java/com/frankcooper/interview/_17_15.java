package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_15 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] words = new String[]{""};
//            handler.longestWord(words);
            words = new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
            Assert.assertEquals("dogwalker", handler.longestWord(words));


        }


        public String longestWord(String[] words) {
            String res = "";
            if (words.length == 0) return res;
            List<String> base = Arrays.asList(words);
            for (String target : words) {
                List<String> list = new ArrayList<>();
                Collections.addAll(list, new String[base.size()]);
                Collections.copy(list, base);
                list.remove(target);
                if (check(target, list)) {
                    if (target.length() > res.length()) res = target;
                    else if (target.length() == res.length() && target.compareTo(res) < 0) {
                        res = target;
                    }
                }
            }
            return res;
        }


        public boolean check(String target, List<String> list) {
            if (target.length() == 0) return true;
            for (int i = 0; i <= target.length(); i++) {//注意 =号 可以取到末尾 substring 取头不取尾 ，取到len-1位置，取完整个字符
                System.out.printf("%s--->%s\n", target, target.substring(0, i));
                if (list.contains(target.substring(0, i)) && check(target.substring(i), list)) return true;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String[] words = new String[]{""};
//            handler.longestWord(words);
            words = new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
//            Assert.assertEquals("dogwalker", handler.longestWord(words));
            words = new String[]{"ttaaaa", "pp", "tpa", "kpaqkt", "tktpqq", "aqppatp"};//自身重复的case
            Assert.assertEquals("", handler.longestWord(words));
        }


        public String longestWord(String[] words) {
            String res = "";
            List<String> wordList = Arrays.asList(words);
            //按字符长度从大到小排列，相同长度的字符，按字典序正序排列
            wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
            for (String target : wordList) {
                if (dfs(target, 0, wordList)) return target;
            }
            return res;

        }

        private boolean dfs(String target, int start, List<String> wordList) {
            if (start == target.length()) return true;
            for (int end = start; end < target.length(); end++) {
                if (end - start + 1 == target.length()) continue;
                String prev = target.substring(start, end + 1);
                if (wordList.contains(prev) && dfs(target, end + 1, wordList)) return true;
            }
            return false;
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
