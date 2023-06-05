package com.frankcooper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//            _1st handler = new _1st();
        int date;
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        date = sc.nextInt();
        for (int i = date + 1; ; i++) {
            if (check(i)) {//合法的日期
                String s = String.valueOf(i);
                if (check1(s) && !flag) {//输出回文日期
                    System.out.println(i);
                    flag = true;
                }
                if (check2(s)) {
                    System.out.println(i);
                    break;
                }
            }
        }

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

    //判断字符s是否是回文
    static boolean check1(String s) {
        int n = s.length();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    //判断是否是ABABBABA 型的回文日期
    static boolean check2(String s) {
        if (check1(s)) {
            if (s.charAt(0) != s.charAt(2) || s.charAt(1) != s.charAt(3) || s.charAt(0) == s.charAt(1)) {
                return false;
            }
            return true;
        }
        return false;
    }


}



