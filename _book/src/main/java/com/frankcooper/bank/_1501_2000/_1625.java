package com.frankcooper.platform.leetcode.bank._1501_2000;

public class _1625 {


    static _1625 handler = new _1625();

    public static void main(String[] args) {
        String s = "5525";
        int a = 9, b = 2;

        s = "12345";

        System.out.println();


        handler.findLexSmallestString(s, a, b);
    }


    public String findLexSmallestString(String s, int a, int b) {
        String ans;
        for (int i = 0; i < s.length(); ++i) {
            //轮转
            s = s.substring(b) + s.substring(0, b);
            //修改奇数的位置
            for (int j = 0; j < 10; j++) {
                for (int k = 1; k < s.length(); k += 2) {
//                    s+=a;
                }
            }


        }

        return null;
    }

}
