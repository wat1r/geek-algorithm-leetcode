package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _720 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] words = new String[]{"w", "wo", "wor", "worl", "world"};
//            Assert.assertEquals(handler.longestWord(words), "world");
            words = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
            Assert.assertEquals(handler.longestWord(words), "apple");
        }

        class TrieNode {
            public TrieNode[] next = new TrieNode[26];
            public int idx = 0;
        }

        TrieNode root = new TrieNode();
        String[] words;

        public String longestWord(String[] words) {
            int idx = 0;
            for (String word : words) {
                insert(word, ++idx);
            }
            this.words = words;
            return dfs();

        }


        public String dfs() {
            String ans = "";
            Stack<TrieNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TrieNode curr = stack.pop();
                if (curr.idx > 0 || curr == root) {
                    if (curr != root) {
                        String word = words[curr.idx - 1];
                        if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                            ans = word;
                        }
                    }
                    for (TrieNode nxt : curr.next) {
                        if (nxt != null) stack.push(nxt);
                    }
                }
            }
            return ans;

        }


        public void insert(String word, int idx) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                if (curr.next[ch - 'a'] == null) {
                    curr.next[ch - 'a'] = new TrieNode();
                }
                curr = curr.next[ch - 'a'];
            }
            curr.idx = idx;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        class TrieNode {
            public TrieNode[] next = new TrieNode[26];
            int idx = 0;
        }

        TrieNode root = new TrieNode();
        String[] words;

        public void insert(String word, int idx) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.next[c - 'a'] == null) cur.next[c - 'a'] = new TrieNode();
                cur = cur.next[c - 'a'];
            }
            cur.idx = idx;
        }


        public String longestWord(String[] words) {
            if (words == null || words.length == 0) return null;
            int idx = 0;
            this.words = words;
            for (String w : words) insert(w, ++idx);
            return helper();
        }

        private String helper() {
            String res = "";
            Stack<TrieNode> stk = new Stack<>();
            stk.push(root);
            while (!stk.isEmpty()) {
                TrieNode cur = stk.pop();
                if (cur.idx > 0 || cur == root) {
                    if (cur != root) {
                        String word = words[cur.idx - 1];
                        if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                            res = word;
                        }
                    }
                    for (TrieNode node : cur.next) {
                        if (node != null) stk.push(node);
                    }
                }
            }
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        class TrieNode {
            char ch;
            Map<Character, TrieNode> next = new HashMap<>();
            int end;

            public TrieNode(char ch) {
                this.ch = ch;
            }
        }

        TrieNode root = new TrieNode('0');
        String[] words;

        public String longestWord(String[] words) {
            int index = 0;
            for (String word : words) {
                insert(word, ++index);
            }
            this.words = words;
            return dfs();
        }


        public String dfs() {
            String ans = "";
            Stack<TrieNode> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                TrieNode node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > ans.length() ||
                                word.length() == ans.length() && word.compareTo(ans) < 0) {
                            ans = word;
                        }
                    }
                    for (TrieNode nxt : node.next.values()) {
                        stack.push(nxt);
                    }
                }
            }
            return ans;
        }

        public void insert(String word, int idx) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                curr.next.putIfAbsent(ch, new TrieNode(ch));
                curr = curr.next.get(ch);
            }
            curr.end = idx;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
