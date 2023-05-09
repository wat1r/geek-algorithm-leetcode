package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2437 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.countTime("0?:0?");
            handler.countTime("?5:00");

        }

        //https://leetcode.cn/problems/number-of-valid-clock-times/solutions/2261803/you-xiao-shi-jian-de-shu-mu-by-leetcode-j7gqz/
        public int countTime(String time) {
            int cntHour = 0, cntMin = 0;
            for (int i = 0; i < 24; i++) {
                int hiHour = i / 10, loHour = i % 10;
                if ((time.charAt(0) == '?' || time.charAt(0) == hiHour + '0') &&
                        (time.charAt(1) == '?' || time.charAt(1) == loHour + '0')) {
                    cntHour++;
                }
            }
            for (int i = 0; i < 60; i++) {
                int hiMin = i / 10, loMin = i % 10;
                if ((time.charAt(3) == '?' || time.charAt(3) == hiMin + '0') &&
                        (time.charAt(4) == '?' || time.charAt(4) == loMin + '0')) {
                    cntMin++;
                }
            }
            return cntHour * cntMin;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        int res = 0;

        public int countTime(String time) {
            dfs(time.toCharArray(), 0);
            return res;
        }

        /**
         * @param arr
         * @param pos 当前dfs时，处在arr的位置
         */
        public void dfs(char[] arr, int pos) {
            if (pos == arr.length) {
                if (check(arr)) res++;
                return;
            }
            if (arr[pos] == '?') {
                for (int i = 0; i <= 9; i++) {
                    arr[pos] = (char) (i + '0');
                    dfs(arr, pos + 1);
                    arr[pos] = '?';
                }
            } else {
                dfs(arr, pos + 1);
            }
        }

        //判断是否合法
        public boolean check(char[] arr) {
            int h = (arr[0] - '0') * 10 + (arr[1] - '0');
            int m = (arr[3] - '0') * 10 + (arr[4] - '0');
            return h < 24 && m < 60;
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
