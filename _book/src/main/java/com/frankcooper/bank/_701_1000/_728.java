package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _728 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public List<Integer> selfDividingNumbers(int left, int right) {
            List<Integer> res = new ArrayList<>();
            for (int x = left; x <= right; x++) {
                if (check(x)) res.add(x);
            }
            return res;
        }


        private boolean check(int x) {
            int t = x;
            while (x > 0) {
                int b = x % 10;
                if (b == 0 || (t % b) != 0) return false;
                x /= 10;
            }
            return true;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //
        static List<Integer> list = new ArrayList<>();

        static {
            out:
            for (int i = 1; i <= 10000; i++) {
                int cur = i;
                while (cur != 0) {
                    int u = cur % 10;
                    if (u == 0 || i % u != 0) continue out;
                    cur /= 10;
                }
                list.add(i);
            }
        }

        public List<Integer> selfDividingNumbers(int left, int right) {
            List<Integer> ans = new ArrayList<>();
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (list.get(mid) >= left) r = mid;
                else l = mid + 1;
            }
            while (r < list.size() && list.get(r) <= right) ans.add(list.get(r++));
            return ans;
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
