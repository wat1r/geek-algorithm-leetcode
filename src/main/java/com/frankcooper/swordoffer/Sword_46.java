package com.frankcooper.swordoffer;

import java.util.HashSet;
import java.util.Set;

public class Sword_46 {


    public static void main(String[] args) {
//        _1st handler = new _1st();
        _2nd handler = new _2nd();
        handler.translateNum(12258);
//        handler.translateNum(25);
    }

    static class _1st {
        public int translateNum(int num) {
            String s = String.valueOf(num);
            int N = s.length();
            int[] f = new int[N + 1];
            f[0] = 1;
            f[1] = 1;
            for (int i = 2; i <= N; i++) {
                String curr = s.substring(i - 2, i);
                boolean flag = curr.compareTo("10") >= 0 && curr.compareTo("25") <= 0;
                if (flag) f[i] = f[i - 2] + f[i - 1];
                else f[i] = f[i - 1];
            }
            return f[N];
        }
    }

    static class _2nd {
        public int translateNum(int num) {
            String s = String.valueOf(num);
            int N = s.length();
            int pre = 1, curr = 1;
            for (int i = 2; i <= N; i++) {
                String tmp = s.substring(i - 2, i);
                boolean flag = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0;
                int next;
                if (flag) next = curr + pre;
                else next = curr;
                pre = curr;
                curr = next;
            }
            return curr;
        }

    }

}
