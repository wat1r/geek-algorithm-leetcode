package com.frankcooper.models;

public class MathModel {

    static MathModel handler = new MathModel();

    public static void main(String[] args) {
//        int a = 25, b = 10;
        int b = 25, a = 10;
        handler.gcd(a, b);
    }


    /**
     * 求两个数的最大公约数 greatest common divisor
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
        System.out.printf("a:%d,b:%d\n", a, b);
        return b == 0 ? a : gcd(b, a % b);
    }


}
