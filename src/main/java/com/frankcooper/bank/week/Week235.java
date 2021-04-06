package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.swordoffer.utils.PrintUtils;
import org.junit.Assert;

public class Week235 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public String truncateSentence(String s, int k) {
            Deque<String> q = new LinkedList<>(Arrays.asList(s.split("\\s+")));
            StringBuilder res = new StringBuilder();
            k--;
            while (k >= 0 && !q.isEmpty()) {
                res.append(q.pollFirst());
                if (k != 0) res.append(" ");
                k--;
            }
            return res.toString();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] logs = PrintUtils.processSymbol("[[1,1],[2,2],[2,3]]");
            int k = 4;
            handler.findingUsersActiveMinutes(logs, k);
        }

        public int[] findingUsersActiveMinutes(int[][] logs, int k) {
            HashMap<Integer, Set<Integer>> map = new HashMap<>();//k为用户ID ，v为time v的size 为UAM
            for (int[] log : logs) {
                Set<Integer> set = map.getOrDefault(log[0], new HashSet<>());
                set.add(log[1]);
                map.put(log[0], set);
            }
            int[] res = new int[k];
            for (Set<Integer> times : map.values()) {
                int uam = times.size();
                res[uam - 1] += 1;
            }
            return res;

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
