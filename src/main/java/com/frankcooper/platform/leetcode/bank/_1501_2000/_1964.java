package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;
import org.junit.Assert;
public class _1964 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {

            List<Integer> cur = new ArrayList<>();
            List<Integer> ans = new ArrayList<>();


            for (int ob : obstacles) {
                int p = getPosition(cur, ob);
                if (cur.size() == p) {
                    cur.add(ob);
                } else {
                    cur.set(p, ob);
                }
                ans.add(p + 1);
            }

            int[] res = new int[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                res[i] = ans.get(i);
            }
            return res;
        }

        private int getPosition(List<Integer> cur, int num) {

            int left = 0, right = cur.size() - 1;
            if (cur.size() == 0 || num >= cur.get(right)) {
                left = right + 1;
                right = -1;
            }

            while (left < right) {
                int mid = ((right - left) >> 1) + left;
                if (cur.get(mid) > num) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
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
