package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _08_08 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.permutation("qqe");

        }

        List<String> list = new ArrayList<>();

        public String[] permutation(String S) {
            char[] chas = S.toCharArray();
            Arrays.sort(chas);
            dfs(chas, 0);
            String[] res = new String[list.size()];
            return list.toArray(res);
        }


        private void dfs(char[] chas, int idx) {
            if (idx == chas.length - 1) {
                list.add(new String(chas));
                System.out.printf("%s\n", Arrays.toString(chas));
                return;
            }
            for (int i = idx; i < chas.length; i++) {
                if (i > idx && (chas[i] == chas[i - 1] || chas[i] == chas[idx])) continue;
                swap(chas, i, idx);
                dfs(chas, idx + 1);
                swap(chas, i, idx);
            }
        }


        private void swap(char[] chas, int i, int j) {
            char t = chas[i];
            chas[i] = chas[j];
            chas[j] = t;
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
