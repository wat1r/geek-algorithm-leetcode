package com.frankcooper.platform.leetcode.swordoffer_II;

import java.util.*;

import com.frankcooper.platform.leetcode.bank._401_500._421;
import org.junit.Assert;

public class Sword_II_067 {

    //同 421
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int findMaximumXOR(int[] nums) {
            for (int x : nums) insert(x);
            int maxx = 0;
            for (int x : nums) {
                maxx = Math.max(maxx, query(x));
            }
            return maxx;
        }


        Trie root = new Trie();

        public void insert(int x) {
            Trie cur = root;
            for (int i = 30; i >= 0; i--) {
                int u = x >> i & 1;//找打二进制的第i位上判断是0还是1
                if (cur.children[u] == null) {
                    cur.children[u] = new Trie();
                }
                cur = cur.children[u];
            }
        }


        public int query(int x) {
            Trie cur = root;
            int res = 0;
            for (int i = 30; i >= 0; i--) {
                int u = x >> i & 1;//找打二进制的第i位上判断是0还是1
                if (cur.children[u ^ 1] != null) {//如果当前位u的另外一个分支可以走的，那就走这个分支
                    res += (1 << i);//相当于当前的值左移i位叠加到res上
                    cur = cur.children[u ^ 1];
                } else {//另外一个分支是空，只能和当前的分支一起前行
                    cur = cur.children[u];
                }
            }
            return res;
        }


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
