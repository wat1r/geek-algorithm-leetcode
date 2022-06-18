package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

public class _2100 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] s = {5, 3, 3, 3, 5, 6, 2};
            int time = 2;
            handler.goodDaysToRobBank(s, time);


        }


        public List<Integer> goodDaysToRobBank(int[] security, int time) {
            int n = security.length;
            int[] f = new int[n];
            for (int i = 1; i < n; i++) {
                if (security[i] == security[i - 1]) continue;
                f[i] = security[i] > security[i - 1] ? 1 : -1;
            }
            int[] pre = new int[n + 1];
            int[] suf = new int[n + 1];
            for (int i = 1; i <= n; i++) pre[i] = pre[i - 1] + (f[i - 1] == 1 ? 1 : 0);
            for (int i = 1; i <= n; i++) suf[i] = suf[i - 1] + (f[i - 1] == -1 ? 1 : 0);
            List<Integer> res = new ArrayList<>();
            for (int i = time; i < n - time; i++) {
                int x = pre[i + 1] - pre[i + 1 - time];
                int y = suf[i + 1 + time] - suf[i + 1];
                if (x == 0 && y == 0) res.add(i);
            }
            return res;
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
