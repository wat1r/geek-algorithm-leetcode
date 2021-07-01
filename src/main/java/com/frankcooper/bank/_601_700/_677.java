package com.frankcooper.bank._601_700;

import org.junit.Assert;

public class _677 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            MapSum mapSum = new MapSum();
            mapSum.insert("apple", 3);
            Assert.assertEquals(mapSum.sum("ap"), 3);       // return 3 (apple = 3)
            mapSum.insert("app", 2);
            Assert.assertEquals(mapSum.sum("ap"), 5);       // return 3 (apple = 3)


        }


        static class TrieNode {
            public TrieNode[] next = new TrieNode[26];
            public int val;

            public TrieNode() {

            }
        }


        static class MapSum {

            TrieNode root;

            /**
             * Initialize your data structure here.
             */
            public MapSum() {
                root = new TrieNode();
            }

            public void insert(String key, int val) {
                TrieNode curr = root;
                for (char ch : key.toCharArray()) {
                    if (curr.next[ch - 'a'] == null) {
                        curr.next[ch - 'a'] = new TrieNode();
                    }
                    curr = curr.next[ch - 'a'];
                }
                curr.val = val;
            }

            public int sum(String prefix) {
                int sum = 0;
                TrieNode curr = root;
                for (char ch : prefix.toCharArray()) {
                    if (curr.next[ch - 'a'] == null) return sum;
                    curr = curr.next[ch - 'a'];
                }
                return dfs(curr);
            }

            /**
             * 计算curr当前节点树上的val
             * @param curr
             * @return
             */
            private int dfs(TrieNode curr) {
                if (curr == null) return 0;
                int sum = curr.val;
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    sum += dfs(curr.next[ch - 'a']);
                }
                return sum;
            }
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
