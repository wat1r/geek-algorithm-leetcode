package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _118 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> sub = helper(i);
                res.add(sub);
            }
            return res;
        }


        public List<Integer> helper(int row) {
            List<Integer> cur = new ArrayList<>();
            if (row == 0) {
                cur.add(1);
                return cur;
            }
            List<Integer> prev = helper(row - 1);
            cur.add(1);
            for (int i = 0; i < prev.size() - 1; i++) {
                cur.add(prev.get(i) + prev.get(i + 1));
            }
            cur.add(1);
            return cur;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> sub = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) sub.add(1);
                    else {
                        sub.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                    }
                }
                res.add(sub);
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            if (numRows == 0) return res;
            if (numRows == 1) {
                List<Integer> cur = new ArrayList<>();
                cur.add(1);
                res.add(cur);
                return res;
            }
            res = generate(numRows - 1);
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < numRows - 1; j++) {
                cur.add(res.get(numRows - 2).get(j - 1) + res.get(numRows - 2).get(j));
            }
            cur.add(1);
            res.add(cur);
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
