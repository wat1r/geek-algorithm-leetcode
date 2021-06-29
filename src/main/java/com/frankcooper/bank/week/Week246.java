package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week246 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        //找到最靠右的奇数位
        public String largestOddNumber(String num) {
            int i = 0, end = -1;
            for (i = 0; i < num.length(); i++) {
                if ((num.charAt(i) - '0') % 2 == 1) end = i;
            }
            return end == -1 ? "" : num.substring(0, end + 1);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String startTime = "00:47", finishTime = "00:57";
            handler.numberOfRounds(startTime, finishTime);
        }


        public int numberOfRounds(String startTime, String finishTime) {
            int s = transform(startTime), e = transform(finishTime);
            if (s > e) e += 24 * 60;
            s = (s + 14) / 15;//上取整
            e /= 15;//下取整
            return s > e ? 0 : e - s;

        }


        private int transform(String s) {
            String[] arr = s.split(":");
            int h = Integer.parseInt(arr[0]), m = Integer.parseInt(arr[1]);
            return h * 60 + m;
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
