package com.frankcooper.bank._101_200;

import java.util.*;

public class _140 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "pineapplepenapple";
            List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
            handler.wordBreak(s, wordDict);
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
                    List<String> subList = dfs(s.substring(word.length()));//当前的word是一种可能切分，
                    for (String sub : subList) {//组装word 和sub， 如果是两个单词，隔开
                        res.add(word + (sub.isEmpty() ? "" : " ") + sub);
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
            String s = "catsanddog";
            List<String> wl = Arrays.asList("cat", "cats", "and", "sand", "dog");
            handler.wordBreak(s, wl);
        }

        //记忆化hashmap
        Map<Integer, List<String>> cache = new HashMap<>();
        List<String> wordDict;//可以做成HashSet
        int maxLen = 0;

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            for (String word : wordDict) {
                //最大单词长度，下面的dfs做切分的时候，超过这个单词的长度变得没有意义，一个小的优化点
                if (word.length() > maxLen) maxLen = word.length();
            }
            return dfs(s, 0);
        }

        /**
         * @param s
         * @param start 当前处理到单词的下标索引
         * @return
         */
        private List<String> dfs(String s, int start) {
            //注意用start索引做key，s做key的时候不可行
            if (cache.containsKey(start)) return cache.get(start);
            List<String> res = new ArrayList<>();
            if (start == s.length()) res.add("");//当前的下标到达s的末尾
            for (int i = start; i < start + maxLen && i < s.length(); i++) {//探测式的切分
                if (wordDict.contains(s.substring(start, i + 1))) {
                    //剩下的的单词列表
                    List<String> remainList = dfs(s, i + 1);
                    for (String remain : remainList) {
                        //加上当前切分的单词
                        if ("".equals(remain)) res.add(s.substring(start, i + 1));
                        else res.add(s.substring(start, i + 1) + " " + remain);
                    }
                }
            }
            cache.put(start, res);
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        List<String> resList = new ArrayList<>();
        boolean[] dp;
        List<String> wordDict;

        public List<String> wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            this.wordDict = wordDict;
            //dp[i] 表示「长度」为 i 的 s 前缀子串可以拆分成 wordDict 中的单词
            this.dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    //[0-j]部分和[j-i]部分都可以，[0-i]部分也必然可以，i这个结束
                    if (dp[j] && wordDict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            //保证到[0-n]可以被拆分
            if (dp[n]) {
                dfs(s, n, new ArrayList<>());
                return resList;
            }
            return resList;
        }

        /**
         * @param s
         * @param index     当前处理到s的下标索引
         * @param levelList
         */
        private void dfs(String s, int index, List<String> levelList) {
            if (index == 0) {//s全部探测完，开始组装leveList
                StringBuilder sb = new StringBuilder();
                for (int i = levelList.size() - 1; i >= 0; i--) {
                    sb.append(levelList.get(i)).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                resList.add(sb.toString());//收集结果
                return;
            }
            for (int i = 0; i < index; i++) {
                if (dp[i]) {//当前的[0-i]是可以被拆分的
                    String sub = s.substring(i, index);
                    if (wordDict.contains(sub)) {
                        levelList.add(sub);//添加
                        dfs(s, i, levelList);
                        levelList.remove(levelList.size() - 1);//回溯
                    }
                }
            }
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        List<String> res = new ArrayList<>();
        Set<String> wordSet;

        public List<String> wordBreak(String s, List<String> wordDict) {
            wordSet = new HashSet<>(wordDict);
            dfs(s, 0, new StringBuilder());
            return res;
        }

        private void dfs(String s, int idx, StringBuilder cur) {
            int n = s.length();
            //出口函数
            if (idx == n) {
                res.add(cur.toString());
                return;
            }
            for (int i = idx; i < n; i++) {
                //取头不取尾
                String can = s.substring(idx, i + 1);
                if (wordSet.contains(can)) {
                    //存cur要追加单词的前的位置
                    int j = cur.length();
                    //如果是第一个单词，不用加空格
                    if (j == 0) {
                        cur.append(can);
                    } else {
                        cur.append(" ").append(can);
                    }
                    //从i+1开始
                    dfs(s, i + 1, cur);
                    //移除j之后的添加单词，回溯
                    cur.delete(j, cur.length());
                }
            }
        }


    }
}
