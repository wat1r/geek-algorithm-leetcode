package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import com.alibaba.fastjson.JSONObject;

public class _336 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
            handler.palindromePairs(words);

        }

        class TrieNode {
            TrieNode[] next;
            List<Integer> words;
            List<Integer> suffixs;

            public TrieNode() {
                this.next = new TrieNode[26];
                this.words = new ArrayList<>();
                this.suffixs = new ArrayList<>();
            }

            public TrieNode[] getNext() {
                return next;
            }

            public void setNext(TrieNode[] next) {
                this.next = next;
            }

            public List<Integer> getWords() {
                return words;
            }

            public void setWords(List<Integer> words) {
                this.words = words;
            }

            public List<Integer> getSuffixs() {
                return suffixs;
            }

            public void setSuffixs(List<Integer> suffixs) {
                this.suffixs = suffixs;
            }

            @Override
            public String toString() {
                return "TrieNode{" +
                        "next=" + Arrays.toString(next) +
                        ", words=" + words +
                        ", suffixs=" + suffixs +
                        '}';
            }
        }

        TrieNode root = new TrieNode();
        int N;

        public List<List<Integer>> palindromePairs(String[] words) {
            N = words.length;
            insert(words);

            // 用以存放答案的列表
            List<List<Integer>> ans = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String word = words[i];
                TrieNode curr = root;
                int j = 0;
                for (; j < word.length(); j++) {
                    // 到j位置，后续字符串若是回文对，则在该节点位置上所有单词都可以与words[i]构成回文对
                    // 因为我们插入的时候是用每个单词的逆序插入的:)
                    if (isPalindrome(word.substring(j)))
                        for (int k : curr.words)
                            if (k != i) ans.add(Arrays.asList(i, k));

                    char ch = word.charAt(j);
                    if (curr.next[ch - 'a'] == null) break;
                    curr = curr.next[ch - 'a'];

                }
                // words[i]遍历完了，现在找所有大于words[i]长度且符合要求的单词，suffixs列表就派上用场了:)
                if (j == word.length())
                    for (int k : curr.suffixs)
                        if (k != i) ans.add(Arrays.asList(i, k));

            }
            return ans;

        }


        private void insert(String[] words) {
            for (int i = 0; i < N; i++) {
                String oppo = new StringBuilder(words[i]).reverse().toString();//翻转后的词
                TrieNode curr = root;
                if (isPalindrome(oppo.substring(0))) curr.suffixs.add(i);
                for (int j = 0; j < oppo.length(); j++) {
                    char ch = oppo.charAt(j);
                    if (curr.next[ch - 'a'] == null) curr.next[ch - 'a'] = new TrieNode();
                    curr = curr.next[ch - 'a'];
                    if (isPalindrome(oppo.substring(j + 1))) curr.suffixs.add(i);
                }
                curr.words.add(i);
//                System.out.println(JSONObject.toJSONString(curr));
            }
        }


        private boolean isPalindrome(String s) {
            int l = 0, r = s.length() - 1;
            while (l < r) {
                if (s.charAt(l++) != s.charAt(r--)) return false;
            }
            return true;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String[] words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
            words = new String[]{"zyx", "llxyz"};
            handler.palindromePairs(words);
        }


        class TrieNode {
            TrieNode[] next = new TrieNode[26]; //当前节点的下一层节点
            int idx = -1;//当前节点是否是一个单词的索引，默认是-1 表示不是一个单词最后一个节点，不是单词的索引
            List<Integer> restIsPalindrome;//跑出这个当前节点，一直往后撸的节点，是否形成了一个回文，记录下当前的这个单词的索引
            char c;//辅助的，调试时打印当前的节点


//            public TrieNode() {
//                restIsPalindrome = new ArrayList<>();
//            }
//
//
//            public TrieNode(char c) {
//                restIsPalindrome = new ArrayList<>();
//                this.c = c;
//            }
//
//            public TrieNode[] getNext() {
//                return next;
//            }
//
//            public void setNext(TrieNode[] next) {
//                this.next = next;
//            }
//
//            public int getIdx() {
//                return idx;
//            }
//
//            public void setIdx(int idx) {
//                this.idx = idx;
//            }
//
//            public List<Integer> getRestIsPalindrome() {
//                return restIsPalindrome;
//            }
//
//            public void setRestIsPalindrome(List<Integer> restIsPalindrome) {
//                this.restIsPalindrome = restIsPalindrome;
//            }
//
//            public char getC() {
//                return c;
//            }
//
//            public void setC(char c) {
//                this.c = c;
//            }
//
//            @Override
//            public String toString() {
//                return "TrieNode{" +
//                        "next=" + Arrays.toString(next) +
//                        ", idx=" + idx +
//                        ", restIsPalindrome=" + restIsPalindrome +
//                        ", c=" + c +
//                        '}';
//            }
//
//            private String printNext() {
//                for (TrieNode tn : next) {
//                    if (tn != null) System.out.println(tn);
//                }
//                return "";
//            }
        }

        TrieNode root = new TrieNode();
        int N;
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> palindromePairs(String[] words) {
            N = words.length;

            for (int i = 0; i < N; i++) {
                insert(words[i], i);
            }
            System.out.println(JSONObject.toJSONString(root));
            for (int i = 0; i < N; i++) {
                search(words[i], i);
            }
            return res;
        }

        private void search(String word, int idx) {
            char[] chas = word.toCharArray();
            TrieNode curr = root;
            for (int i = 0; i < chas.length; i++) {
                //当前不是单词的结尾，且当前位置到这个单词的结尾部分是回文 加入
                if (curr.idx != -1 && isPalindrome(chas, i, chas.length - 1)) {//注意这里的回文区间
                    res.add(Arrays.asList(idx, curr.idx));
                }
                //如果当前当初已经搜不到下一个节点了，这个树的路径已经没有意义
                if (curr.next[chas[i] - 'a'] == null) return;
                //跳到下一个节点
                curr = curr.next[chas[i] - 'a'];
            }
            //在外层循环，当前节点是一个单词的结束节点，且不是当前当前单词，加入
            if (curr.idx != -1 && curr.idx != idx) {
                res.add(Arrays.asList(idx, curr.idx));
            }
            //找到当前节点可以形成回文的节点，加入
            for (int rest : curr.restIsPalindrome) {
                res.add(Arrays.asList(idx, rest));
            }
        }

        private void insert(String word, int idx) {
            char[] chas = word.toCharArray();
            TrieNode curr = root;
            for (int i = chas.length - 1; i >= 0; i--) {
                if (isPalindrome(chas, 0, i)) {//注意这里的回文区间
                    curr.restIsPalindrome.add(idx);
                }
                if (curr.next[chas[i] - 'a'] == null) curr.next[chas[i] - 'a'] = new TrieNode();
                curr = curr.next[chas[i] - 'a'];
            }
            curr.idx = idx;//末尾节点，该节点是个单词
        }


        private boolean isPalindrome(char[] chas, int l, int r) {
            while (l < r) if (chas[l++] != chas[r--]) return false;
            return true;
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
