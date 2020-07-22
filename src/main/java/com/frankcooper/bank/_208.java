package com.frankcooper.bank;

/**
 * @Date 2020/7/16
 * @Author Frank Cooper
 * @Description
 */
public class _208 {


    static class Trie {


        Trie[] children = new Trie[26];
        boolean isEnd = false;


        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new Trie();
                }
                curr = curr.children[c - 'a'];
            }
            curr.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null) return false;
                curr = curr.children[c - 'a'];
            }
            return curr.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie curr = this;
            for (char c : prefix.toCharArray()) {
                if (curr.children[c - 'a'] == null) return false;
                curr = curr.children[c - 'a'];
            }
            return true;
        }
    }


}
