package com.frankcooper.bank._1001_1500;

import java.util.Arrays;
import java.util.List;

public class _1189 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int maxNumberOfBalloons(String text) {
            int[] arr = new int[128];
            List<Character> list = Arrays.asList('a', 'b', 'l', 'l', 'n', 'o', 'o');
            for (char c : text.toCharArray()) {
                if (list.contains(c)) arr[c]++;
            }
            int cnt = 0;
            while (true) {
                for (Character c : list) {
                    arr[c]--;
                    if (arr[c] < 0) {
                        return cnt;
                    }
                }
                cnt++;
            }
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
