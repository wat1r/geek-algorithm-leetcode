package com.frankcooper.platform.acwing;

import java.util.Scanner;

public class _1229 {

    static class _1st {

        public static void main(String[] args) {
            int a, b, c;
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            String[] arr = line.split("/");
            a = Integer.parseInt(arr[0]);
            b = Integer.parseInt(arr[1]);
            c = Integer.parseInt(arr[2]);
            for (int date = 19600101; date <= 20591231; date++) {
                int year = date / 10000, month = date % 10000 / 100, day = date % 100;
//            if (year == 2002 && month == 3 && day == 4) {
//                System.out.println("");
//            }
                if (check_valid(year, month, day)) {
                    if (((year % 100) == a && month == b && day == c)//年/月/日
                            || (month == a && day == b && year % 100 == c)   //月/日/年
                            || ((day == a && month == b && year % 100 == c))  //日/月/年
                    ) {
                        System.out.printf("%d-%02d-%02d\n", year, month, day);  //补前导0
                    }
                }
            }
        }

        static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        static boolean check_valid(int year, int month, int day) {
            if (month == 0 || month > 12) return false;
            if (day == 0) return false;
            if (month != 2) {
                if (day > days[month]) return false;
            } else {
                //闰年：
                //（1）四年一闰百年不闰：即如果year能够被4整除，但是不能被100整除，则year是闰年。
                //（2）每四百年再一闰：如果year能够被400整除，则year是闰年。
                int leap = 0;
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    leap = 1;
                }
                if (day > days[month] + leap) return false;
            }
            return true;
        }

    }


}
