package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/7/16
 * @Author Frank Cooper
 * @Description
 */
public class _208 {


    static class Trie2 {


        Trie2[] next = new Trie2[26];
        boolean isEnd = false;


        /**
         * Initialize your data structure here.
         */
        public Trie2() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie2 curr = this;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new Trie2();
                }
                curr = curr.next[c - 'a'];
            }
            curr.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie2 curr = this;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) return false;
                curr = curr.next[c - 'a'];
            }
            return curr.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie2 curr = this;
            for (char c : prefix.toCharArray()) {
                if (curr.next[c - 'a'] == null) return false;
                curr = curr.next[c - 'a'];
            }
            return true;
        }
    }


    static class Trie {

        class TrieNode {
            Map<Character, TrieNode> next = new HashMap<>();
            boolean isEnd = false;
        }


        TrieNode root = new TrieNode();

        public Trie() {
        }

        public void insert(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.next.containsKey(c)) {
                    TrieNode tmp = new TrieNode();
                    curr.next.put(c, tmp);
                    curr = tmp;
                } else {
                    curr = curr.next.get(c);
                }
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.next.containsKey(c)) return false;
                curr = curr.next.get(c);
            }
            return curr.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                if (!curr.next.containsKey(c)) return false;
                curr = curr.next.get(c);
            }
            return true;
        }

    }


    static class _3rd {
        static class Trie {

            Trie[] next;
            boolean isEnd;


            /**
             * Initialize your data structure here.
             */
            public Trie() {
                next = new Trie[26];
                isEnd = false;
            }

            /**
             * Inserts a word into the trie.
             */
            public void insert(String word) {
                Trie cur = this;
                char[] chas = word.toCharArray();
                for (char ch : chas) {
                    if (cur.next[ch - 'a'] == null) cur.next[ch - 'a'] = new Trie();
                    cur = cur.next[ch - 'a'];
                }
                cur.isEnd = true;
            }

            /**
             * Returns if the word is in the trie.
             */
            public boolean search(String word) {
                Trie cur = this;
                char[] chas = word.toCharArray();
                for (char ch : chas) {
                    if (cur.next[ch - 'a'] == null) return false;
                    cur = cur.next[ch - 'a'];
                }
                return cur.isEnd;
            }

            /**
             * Returns if there is any word in the trie that starts with the given prefix.
             */
            public boolean startsWith(String prefix) {
                Trie cur = this;
                char[] chas = prefix.toCharArray();
                for (char ch : chas) {
                    if (cur.next[ch - 'a'] == null) return false;
                    cur = cur.next[ch - 'a'];
                }
                return true;
            }


        }

        public static void main(String[] args) {
            /**
             * ["Trie","insert","search","search","startsWith","insert","search"]
             * [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
             */
            Trie trie = new Trie();
            trie.insert("apple");
            trie.search("apple");
            trie.search("apple");
            trie.startsWith("app");
            trie.insert("app");
            trie.search("app");
        }
    }


    static class _3rd_1 {
        class Trie {

            Trie[] next = new Trie[26];
            boolean isEnd = false;

            public Trie() {

            }

            public void insert(String word) {
                Trie cur = this;
                for (char c : word.toCharArray()) {
                    if (cur.next[c - 'a'] == null) {
                        cur.next[c - 'a'] = new Trie();
                    }
                    cur = cur.next[c - 'a'];
                }
                cur.isEnd = true;
            }

            public boolean search(String word) {
                Trie cur = this;
                for (char c : word.toCharArray()) {
                    if (cur.next[c - 'a'] == null) return false;
                    cur = cur.next[c - 'a'];
                }
                return cur.isEnd;
            }

            public boolean startsWith(String prefix) {
                Trie cur = this;
                for (char c : prefix.toCharArray()) {
                    if (cur.next[c - 'a'] == null) return false;
                    cur = cur.next[c - 'a'];
                }
                return true;
            }
        }
    }


}
