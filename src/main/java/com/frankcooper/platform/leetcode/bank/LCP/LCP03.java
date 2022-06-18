package com.frankcooper.platform.leetcode.bank.LCP;

import java.util.*;

public class LCP03 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String command = "RRU";
            int[][] o = {{5, 5}, {9, 4}, {9, 7}, {6, 4}, {7, 0}, {9, 5}, {10, 7}, {1, 1}, {7, 5}};
            int x = 1486;
            int y = 743;
//            command = "URR";
//            o = new int[][]{};
//            x = 3;
//            y = 2;
            handler.robot(command, o, x, y);
        }


        public boolean robot(String command, int[][] obstacles, int x, int y) {
            HashMap<Integer, Set<Integer>> map = new HashMap<>();
            for (int[] o : obstacles) {
                Set<Integer> set = map.getOrDefault(o[0], new HashSet<>());
                set.add(o[1]);
                map.put(o[0], set);
            }
            int curx = 0, cury = 0;
            while (true) {
                for (int i = 0; i < command.length(); i++) {
                    if (curx > x && cury > y) return false;
//                    System.out.printf("curx:%d,cury:%d\n", curx, cury);
                    if (curx == x && cury == y) return true;
                    if (map.containsKey(curx) && map.get(curx).contains(cury)) return false;
                    if (command.charAt(i) == 'U') cury += 1;
                    if (command.charAt(i) == 'R') curx += 1;

                }
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
