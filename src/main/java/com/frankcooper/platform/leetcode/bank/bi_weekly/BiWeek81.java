package com.frankcooper.platform.leetcode.bank.bi_weekly;

import java.util.*;
//
import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class BiWeek81 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "l|*e*et|c**o|*de|";
            handler.countAsterisks(s);
        }


        public int countAsterisks(String s) {
            char[] ch = s.toCharArray();
            int count = 0;
            int i = 0, first = -1;
            while (i < ch.length) {
                if (first != -1 && ch[i] == '|') {
                    first = -1;
                } else if (first == -1 && ch[i] == '|') {
                    first = i;
                }
                if (first == -1 && ch[i] == '*') {
                    count++;
                }
                i++;
            }
            return count;

        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public long countPairs(int n, int[][] edges) {
// 4 + 4*2 + 2 = 14

            return 0L;
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
