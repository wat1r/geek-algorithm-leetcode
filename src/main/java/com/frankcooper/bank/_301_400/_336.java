package com.frankcooper.bank._301_400;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import sun.text.normalizer.Trie;

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
