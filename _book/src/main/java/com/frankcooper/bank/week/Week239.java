package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week239 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int getMinDistance(int[] nums, int target, int start) {
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    res = Math.min(res, Math.abs(i - start));
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.splitString("1234");
        }


        public boolean splitString(String s) {
            long t = 0;
            //因为必须要分割成两个子串，所以最后一个字符不可能是组成第一个数字的字符，我们这里也是为了防止刚好20位导致long也会溢出的情况
            for (int i = 0; i < s.length() - 1; i++) {
                t = t * 10 + s.charAt(i) - '0';
                if (backtrace(s, t, i + 1)) return true;
            }
            return false;
        }


        //s要分割的字符串；prev前面一个数的值；idx当前字符串已经用到了哪个位置
        public boolean backtrace(String s, long prev, int idx) {
            if (idx == s.length()) {
                return true;
            }
            long t = 0;
            for (int i = idx; i < s.length(); i++) {
                t = t * 10 + s.charAt(i) - '0';
                if (prev - 1 == t && backtrace(s, t, i + 1)) return true;
                if (t >= prev) return false;
            }
            return false;
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
