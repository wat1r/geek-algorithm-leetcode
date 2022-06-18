package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1387 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

//            Assert.assertEquals(handler.getWeight(3, 0), 7);
//            Assert.assertEquals(handler.getWeight(12, 0), 9);

            Assert.assertEquals(13, handler.getKth(12, 15, 2));

        }


        public int getKth(int lo, int hi, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);//[0]是weight，[1]是数
            for (int i = lo; i <= hi; i++) {
                pq.offer(new int[]{getWeight(i), i});
            }
            while (--k > 0) pq.poll();
            return pq.peek()[1];
        }

        public int getWeight(int num) {
            if (num == 1) return 0;
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = 3 * num + 1;
            }
            return getWeight(num) + 1;
        }

/*        public int getWeight(int num, int step) {
            if (num == 1) return step;
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = 3 * num + 1;
            }
            return getWeight(num, step + 1);
        }*/


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
