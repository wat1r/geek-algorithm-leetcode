package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1496 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean isPathCrossing(String path) {
            int si = 0, sj = 0;
            Set<Integer> vis = new HashSet<>();
            vis.add(hash(si,sj));
            for (char c : path.toCharArray()) {
                switch (c){
                    case 'N':--si;break;
                    case 'S':++si;break;
                    case 'W':--sj;break;
                    case 'E':++sj;break;
                }
                // System.out.printf("%d  ", hash(si,sj));
                if(!vis.add(hash(si,sj)))return true;
            }
            return false;
        }

        private int hash(int i, int j) {
            return i * 10001 + j;
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
