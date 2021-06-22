package com.frankcooper.swordoffer;

import java.util.HashSet;
import java.util.Set;

public class Sword_38 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

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
