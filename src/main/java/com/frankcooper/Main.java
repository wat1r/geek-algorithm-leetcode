package com.frankcooper;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        double n = in.nextDouble();
        double l = -1000.0, r = 10000;
        while ((r - l) > Math.pow(10, -8)) {
            double mid = (l + r) / 2;
            if (mid * mid * mid > n) r = mid;
            else l = mid;
        }
        System.out.printf("%.6f", l);
    }


}
