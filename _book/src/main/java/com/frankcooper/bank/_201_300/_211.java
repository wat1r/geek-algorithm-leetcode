package com.frankcooper.bank._201_300;

/**
 * @Date 2020/7/23
 * @Author Frank Cooper
 * @Description
 */
public class _211 {


    static class WordDictionary {
        static WordDictionary handler = new WordDictionary();

        public static void main(String[] args) {
            handler.test();
        }


        private void test() {
            addWord("bad");
            addWord("dad");
            addWord("mad");
            search("pad");
            search("bad");
            search(".ad");
            search("b..");
        }


        static class TrieNode {
            private TrieNode[] next;
            private boolean isEnd;

            public TrieNode() {
                this.next = new TrieNode[26];
                this.isEnd = false;
            }
        }


        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode curr = this.root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.isEnd = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return dfs(root, word, 0);
        }

        /**
         * @param root
         * @param word
         * @param index
         * @return
         */
        private boolean dfs(TrieNode root, String word, int index) {
            if (index == word.length()) {
                return root.isEnd;
            }
            char c = word.charAt(index);
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (root.next[i] != null && dfs(root.next[i], word, index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (root.next[c - 'a'] == null) return false;
                return dfs(root.next[c - 'a'], word, index + 1);
            }
        }
    }

}
