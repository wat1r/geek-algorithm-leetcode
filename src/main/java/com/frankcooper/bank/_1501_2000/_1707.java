package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1707 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int[] maximizeXor(int[] nums, int[][] queries) {


            int[] res = new int[nums.length];

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
