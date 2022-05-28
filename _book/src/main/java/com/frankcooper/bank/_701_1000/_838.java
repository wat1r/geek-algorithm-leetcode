package com.frankcooper.bank._901_1000;

import java.util.*;

import org.junit.Assert;

public class _838 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public String pushDominoes(String dominoes) {
            dominoes = "L" + dominoes + "R";
            int left = 0;
            StringBuilder res = new StringBuilder();
            for (int right = left; right < dominoes.length(); right++) {
                if (dominoes.charAt(right) == '.') continue;
                if (left != 0) res.append(dominoes.charAt(left));
                int count = right - left - 1;
                if (dominoes.charAt(left) == dominoes.charAt(right)) {
                    for (int i = 0; i < count; i++) {
                        res.append(dominoes.charAt(left));
                    }
                } else if (dominoes.charAt(left) == 'L' && dominoes.charAt(right) == 'R') {
                    for (int i = 0; i < count; i++) {
                        res.append('.');
                    }
                } else {
                    for (int i = 0; i < count / 2; i++) {
                        res.append("R");
                    }
                    if (count % 2 == 1) res.append(".");
                    for (int i = 0; i < count / 2; i++) {
                        res.append("L");
                    }
                }
                left = right;
            }
            return res.toString();
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
