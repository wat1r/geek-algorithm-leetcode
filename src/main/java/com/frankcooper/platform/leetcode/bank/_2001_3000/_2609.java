package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;
import org.junit.Assert;
public class _2609 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int findTheLongestBalancedSubstring(String s) {
            int n = s.length(), i = 0, ans = 0;
            while (i < n) {
                int zero = 0, one = 0;
                while (i < n && s.charAt(i) == '0') {
                    zero++;
                    i++;
                }
                while (i < n && s.charAt(i) == '1') {
                    one++;
                    i++;
                }
                ans = Math.max(ans, Math.min(zero, one) * 2);
            }
            return ans;
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
