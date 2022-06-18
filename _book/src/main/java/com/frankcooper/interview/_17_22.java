package com.frankcooper.platform.leetcode.interview;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/8/29 20:01
 * Description
 */
public class _17_22 {


    static _17_22 handler = new _17_22();


    static class _1st {


        static _1st handler = new _1st();

        public static void main(String[] args) {

            String beginWord = "hit";
            String endWord = "cog";
            List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
            handler.findLadders(beginWord, endWord, wordList);
        }


        public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
            Queue<String> q = new LinkedList<>();
            q.offer(beginWord);
            Set<String> words = new HashSet<>(wordList);
            Map<String, String> vis = new HashMap<>();//k 存储cur的下一个字符，v 存储cur，逆向存储，遍历的时候正序
            List<String> res = new ArrayList<>();
            vis.put(beginWord, beginWord);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    if (cur.equals(endWord)) {//找到了endWord
                        res.add(0, endWord);//插入到res的头部位置
                        String t = endWord;
                        while (!t.equals(beginWord)) {//不断遍历直到找到beginWord
                            t = vis.get(t);
                            res.add(0, t);
                        }
                        return res;
                    }
                    List<String> cans = transform(words, cur);//当前cur字符候选的字符
                    for (String can : cans) {
                        if (!vis.containsKey(can)) {//如果没有被访问到
                            vis.put(can, cur);
                            q.offer(can);
                        }

                    }
                }
            }
            return new ArrayList<>();
        }


        /**
         * 生成目标的word的所有潜在的word，
         * 如hit -->ait bit ...zit但是排除了hit本身
         * hit --> hat hbt... hzt但是排除了hit本身
         * words 含有的上面生成的潜在的word进行收集
         *
         * @param words
         * @param word
         * @return
         */
        private List<String> transform(Set<String> words, String word) {
            List<String> resList = new ArrayList<>();
            StringBuffer sb = new StringBuffer(word);
            for (int i = 0; i < sb.length(); i++) {
                char tmp = sb.charAt(i);//记录下索引位置下的char，下面的for loop中会剔除掉这个
                for (char c = 'a'; c <= 'z'; c++) {
                    if (tmp == c) continue;//word本身
                    sb.setCharAt(i, c);//改变i的值
                    String canditate = sb.toString();
                    //如果words含有canditate，将其加入到结果集中
                    if (words.remove(canditate)) resList.add(canditate);
                }
                sb.setCharAt(i, tmp);//结束本轮loop后，恢复原样
            }

            return resList;
        }
    }


    static class _2nd {


        public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> words = new HashSet<>(wordList);
            Map<String, String> vis = new HashMap<>();
            Queue<String> q = new LinkedList<>();
            q.offer(beginWord);
            vis.put(beginWord, beginWord);
            Stack<String> stk = new Stack<>();//逆序存栈
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    if (cur.equals(endWord)) {
                        stk.push(endWord);
                        String t = endWord;
                        while (!t.equals(beginWord)) {
                            t = vis.get(t);
                            stk.push(t);
                        }
                        List<String> res = new ArrayList<>();
                        while (!stk.isEmpty()) res.add(stk.pop());//倒出栈元素
                        return res;
                    }

                    for (String next : words) {
                        if (isNext(cur, next) && !vis.containsKey(next)) {
                            vis.put(next, cur);
                            q.offer(next);
                        }
                    }
                }
            }
            return new ArrayList<>();

        }


        /**
         * 遍历两个相同长度相同的字符，判断是否不相同的字符差1个
         *
         * @param cur  当前字母
         * @param next 候选字母
         * @return
         */
        public boolean isNext(String cur, String next) {
            int cnt = 0;
            for (int i = 0; i < cur.length(); i++) {
                if (cur.charAt(i) != next.charAt(i)) cnt++;
            }
            return cnt == 1;
        }


    }


    static class _3rd {

        public static void main(String[] args) {

        }

        List<String> res = new ArrayList<>();//结果集
        List<String> wordList;//源字母列表
        String endWord;//结尾字母
        Set<String> vis = new HashSet<>();//标记字符是否被访问过

        public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
            this.wordList = wordList;
            this.endWord = endWord;
            res.add(beginWord);
            vis.add(beginWord);
            return dfs(beginWord) ? res : new ArrayList<>();
        }


        public boolean dfs(String cur) {
            if (cur.equals(endWord)) return true;
            for (String next : wordList) {
                //是当前cur字母的候选字母 & 没有被访问过
                if (isNext(cur, next) && !vis.contains(next)) {
                    vis.add(next);//添加结果
                    res.add(next);
                    if (dfs(next)) return true;
                    else {
                        res.remove(res.size() - 1);//该next不可选，移除
                    }
                }
            }
            return false;
        }


        public boolean isNext(String cur, String next) {
            int cnt = 0;
            for (int i = 0; i < cur.length(); i++) {
                if (cur.charAt(i) != next.charAt(i)) cnt++;
            }
            return cnt == 1;
        }
    }
}
