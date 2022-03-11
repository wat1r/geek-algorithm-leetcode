package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _17_17 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            String big = "mississippi";
            String[] smalls = {"is", "ppi", "hi", "sis", "i", "ssippi"};
            handler.multiSearch(big, smalls);
        }


        public int[][] multiSearch(String big, String[] smalls) {
            TrieNode root = new TrieNode();
            TrieNode cur = root;
            for (int i = 0; i < big.toCharArray().length; i++) {
                char c = big.charAt(i);
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieNode();
                }
                cur = cur.next[c - 'a'];
                cur.idx = i;
            }

            for (String s : smalls) {

            }

            return null;
        }


        class TrieNode {
            TrieNode[] next = new TrieNode[26];
            int idx;
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
