package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _08_07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.permutation("qwe");
        }


        List<String> list = new ArrayList<>();

        public String[] permutation(String S) {
            dfs(S, new StringBuilder());
            String[] res = new String[list.size()];
            return list.toArray(res);
        }


        private void dfs(String S, StringBuilder cur) {
            if (cur.length() == S.length()) {
                list.add(cur.toString());
                return;
            }
            for (int i = 0; i < S.length(); i++) {
                String t = new String(cur);
                if (t.contains(S.charAt(i) + "")) continue;
                cur.append(S.charAt(i));
                dfs(S, cur);
                cur.deleteCharAt(cur.length() - 1);
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
