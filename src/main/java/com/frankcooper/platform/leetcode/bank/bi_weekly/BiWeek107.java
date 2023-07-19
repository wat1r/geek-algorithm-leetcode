package com.frankcooper.platform.leetcode.bank.bi_weekly;

import java.util.*;
//
//import com.frankcooper.utils.PrintUtils;
//import org.junit.Assert;

public class BiWeek107 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maximumNumberOfStringPairs(String[] words) {
            int res = 0;
            for (int i = 0; i < words.length - 1; i++) {
                String s = words[i];
                for (int j = i + 1; j < words.length; j++) {
                    String t = reverse(words[j]);
                    if (s.equals(t)) {
                        res++;
                    }
                }
            }
            return res;
        }

        public String reverse(String s) {
            String t = "";
            for (int i = s.length() - 1; i >= 0; i--) {
                t += s.charAt(i);
            }
            return t;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int longestString(int x, int y, int z) {
            return Math.max(dfs(x, y, z, 0), dfs(x, y, z, 1));
        }

        public int dfs(int x, int y, int z, int k) {
            if (k == 0) {
                if (y > 0) {
                    return dfs(x, y - 1, z, 1) + 2;
                }
                return 0;
            }
            int res1 = x > 0 ? dfs(x - 1, y, z, 0) + 2 : 0;
            int res2 = z > 0 ? dfs(x, y, z - 1, 2) + 2 : 0;
            return Math.max(res1, res2);

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
