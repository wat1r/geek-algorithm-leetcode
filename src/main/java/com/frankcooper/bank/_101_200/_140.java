package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _140 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        Map<String, List<String>> cache = new HashMap<>();
        List<String> wordDict;

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            return dfs(s);
        }


        private List<String> dfs(String s) {
            if (cache.containsKey(s)) {//如果cache中有s的映射，返回
                return cache.get(s);
            }
            List<String> res = new ArrayList<>();
            if (s.length() == 0) {//已经到s的末尾，返回
                res.add("");
                return res;
            }
            for (String word : wordDict) {//遍历wordDict
                if (s.startsWith(word)) {
                    List<String> remainList = dfs(s.substring(word.length()));//当前的word是一种可能切分，
                    for (String remain : remainList) {//组装word 和remain， 如果是两个单词，隔开
                        res.add(word + (remain.isEmpty() ? "" : " ") + remain);
                    }
                }
            }
            cache.put(s, res);//更新cache，避免重复搜索
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
