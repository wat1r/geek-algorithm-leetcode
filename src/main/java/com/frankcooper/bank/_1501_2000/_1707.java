package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1707 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int[] maximizeXor(int[] nums, int[][] _queries) {
            Arrays.sort(nums);
            int n = _queries.length;
            int[][] queries = new int[n][3];
            for (int i = 0; i < n; i++) {
                queries[i][0] = _queries[i][0];
                queries[i][1] = _queries[i][1];
                queries[i][2] = i;
            }
            Arrays.sort(queries, (a, b) -> a[1] - b[1]);
            int[] res = new int[n];
            int idx = 0;
            n = nums.length;
            for (int[] q : queries) {
                int x = q[0], m = q[1], qid = q[2];
                while (idx < n && nums[idx] <= m) {
                    insert(nums[idx]);
                    idx++;
                }
                if (idx == 0) {
                    res[qid] = -1;
                } else {
                    res[qid] = query(x);
                }
            }
            return res;
        }


        public int query(int x) {
            int res = 0;
            Trie cur = root;
            for (int i = 30; i >= 0; --i) {
                int u = x >> i & 1;
                if (cur.children[u ^ 1] != null) {
                    res |= 1 << i;
                    u ^= 1;
                }
                cur = cur.children[u];
            }
            return res;
        }


        public void insert(int x) {
            Trie cur = root;
            for (int i = 30; i >= 0; --i) {
                int u = x >> i & 1;
                if (cur.children[u] == null) {
                    cur.children[u] = new Trie();
                }
                cur = cur.children[u];
            }
        }

        Trie root = new Trie();


        class Trie {
            Trie[] children = new Trie[2];
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
