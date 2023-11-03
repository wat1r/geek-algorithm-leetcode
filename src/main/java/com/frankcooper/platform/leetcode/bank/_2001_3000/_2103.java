package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2103 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String rings = "B0B6G0R6R0R6G9";
            handler.countPoints(rings);

        }


        public int countPoints(String rings) {
            Set<Character>[] sets = new HashSet[10];
            Arrays.setAll(sets, e -> new HashSet<>());
            Set<Integer> ans = new HashSet<>();
            for (int i = 0; i < rings.toCharArray().length; i += 2) {
                char c = rings.charAt(i);
                int j = rings.charAt(i + 1) - '0';
                sets[j].add(c);
                if (sets[j].size() == 3) {
                    ans.add(j);
                }
            }
            return ans.size();
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String rings = "B0B6G0R6R0R6G9";
            handler.countPoints(rings);
        }

        public int countPoints(String s) {
            int n = s.length(), ans = 0;
            int[] map = new int[128];
            for (int i = 0; i < n; i += 2) map[s.charAt(i) - 'B'] |= 1 << (s.charAt(i + 1) - '0');
            for (int i = 0; i < 10; i++) {
                int tot = 0;
                for (char c : new char[]{'R', 'G', 'B'}) tot += (map[c - 'B'] >> i) & 1;
                if (tot == 3) ans++;
            }
            return ans;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int countPoints(String s) {
            int n = s.length(), ans = 0;
            int[] cnt = new int[10];
            for (int i = 0; i < n; i += 2) {
                char c = s.charAt(i);
                int idx = -1, t = s.charAt(i + 1) - '0';
                if (c == 'R') idx = 0;
                else if (c == 'G') idx = 1;
                else idx = 2;
                cnt[t] |= 1 << idx;
            }
            for (int i = 0; i < 10; i++) {
                if (cnt[i] == (1 << 3) - 1) ans++;
            }
            return ans;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
