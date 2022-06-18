package com.frankcooper.platform.leetcode.swordoffer_II;

import java.util.*;

import org.junit.Assert;

public class Sword_II_087 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "25525511135";
            s = "1111";
            handler.restoreIpAddresses(s);
        }


        List<String> res = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            backtracing(s, "", 0);
            return res;
        }


        /**
         * @param s     当前字符还剩下的字符
         * @param seg   拼接的ip地址的字符
         * @param count 段数
         */
        public void backtracing(String s, String seg, int count) {
            //出口条件：先|| 再 && 确保 空字符串s不会进入到下面的循环
            if (s.isEmpty() || count == 4) {
                //比如1111 到这里是 .1.1.1.1 因为一开始在头部追加了 . 此处在第二个字符开始往后切
                if (s.isEmpty() && count == 4) res.add(seg.substring(1));
                return;
            }
            //最多3个 包含前导0的话，就是1个
            int n = s.charAt(0) == '0' ? 1 : 3;
            for (int i = 1; i <= n && i <= s.length(); i++) {
                String t = s.substring(0, i);
                if (Integer.parseInt(t) <= 255) {
                    backtracing(s.substring(i), seg + "." + t, count + 1);
                }
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
