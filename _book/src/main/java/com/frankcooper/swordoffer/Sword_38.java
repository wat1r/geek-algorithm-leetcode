package com.frankcooper.platform.leetcode.swordoffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sword_38 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "abc";
            handler.permutation(s);
        }

        List<String> res = new ArrayList<>();
        char[] ch;
        int n;


        public String[] permutation(String s) {
            n = s.length();
            ch = s.toCharArray();
            dfs(0);
            return res.toArray(new String[res.size()]);

        }


        private void dfs(int idx) {
            if (idx == n - 1) {
                res.add(String.valueOf(ch));
                return;
            }
            Set<Character> vis = new HashSet<>();
            for (int i = idx; i < n; i++) {
                if (vis.contains(ch[i])) continue;
                vis.add(ch[i]);
                swap(i, idx);
                dfs(idx + 1);
                swap(i, idx);
            }
        }


        private void swap(int x, int y) {
            char t = ch[x];
            ch[x] = ch[y];
            ch[y] = t;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

            String s = "abc";
//            handler.permutation(s);
            s = "aab";
            handler.permutation(s);


        }


        Set<String> res = new HashSet<>();
        boolean[] vis;

        public String[] permutation(String s) {
            vis = new boolean[s.length()];
            dfs(s, new StringBuilder());
            String[] arr = new String[res.size()];
            return res.toArray(arr);
        }


        private void dfs(String s, StringBuilder cur) {
            if (cur.length() == s.length()) {
                res.add(cur.toString());
                return;
            }
            for (int i = 0; i < s.length(); i++) {
                if (vis[i]) continue;
                vis[i] = true;
                cur.append(s.charAt(i));
                dfs(s, cur);
                cur.deleteCharAt(cur.length() - 1);
                vis[i] = false;
            }
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
