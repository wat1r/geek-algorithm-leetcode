package com.frankcooper.platform.acwing;

import java.util.*;

import org.junit.Assert;

public class _466 {

    static class _1st {
        public static void main(String[] args) {
//            _1st handler = new _1st();
            Scanner sc = new Scanner(System.in);
            int date1 = sc.nextInt(), date2 = sc.nextInt();
            int res = 0;
            for (int i = 0; i < 10000; i++) {
//                if (i == 2011) {
//                    System.out.println();
//                }
                int x = i, r = i;
                for (int j = 0; j < 4; j++) {
                    r = r * 10 + x % 10;
                    x /= 10;
                }
                if (r >= date1 && r <= date2 && check(r)) {
                    res++;
                }
            }
            System.out.println(res);
        }


        static int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        static boolean check(int date) {
            int year = date / 10000, month = date % 10000 / 100, day = date % 100;
            if (month == 0 || month >= 13 || day == 0) return false;
            if (month != 2 && day > months[month]) return false;
            if (month == 2) {
                int leap = 0;
                //注意这里的条件
                //闰年：
                //（1）四年一闰百年不闰：即如果year能够被4整除，但是不能被100整除，则year是闰年。
                //（2）每四百年再一闰：如果year能够被400整除，则year是闰年。
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    leap = 1;
                }
                if (day > 28 + leap) return false;
            }
            return true;
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
