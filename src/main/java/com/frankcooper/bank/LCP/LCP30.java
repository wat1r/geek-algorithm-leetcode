package com.frankcooper.bank.LCP;

import java.util.*;

import org.junit.Assert;

public class LCP30 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{100, 100, 100, -250, -60, -140, -50, -50, 100, 150};
            Assert.assertEquals(handler.magicTower(nums), 1);

        }


        /**
         * @param nums
         * @return
         */
        public int magicTower(int[] nums) {
            int n = nums.length;
            int sum1 = 0, sum2 = 0;
            for (int x : nums) {
                if (x > 0) sum1 += x;//先判断所有正数大于负数
                else sum2 += x;
            }
            if (sum1 + sum2 < 0) return -1;//正数小于负数，返回-1
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);//存大根堆 负数的相反数
            long sum = 0;
            int res = 0;
            for (int x : nums) {
                sum += x;
                if (x < 0) pq.offer(-x);//负数，推入堆中
                while (sum < 0) {// sum小于0的时候，说明要执行一次移动负数到末尾操作，移动最大的负数
                    int cur = pq.poll();
                    sum += cur;
                    res++;
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int magicTower(int[] nums) {
            int n = nums.length;
            int sum1 = 0, sum2 = 0;
            for (int x : nums) {
                if (x > 0) sum1 += x;//先判断所有正数大于负数
                else sum2 += x;
            }
            if (sum1 + sum2 < 0) return -1;//正数小于负数，返回-1
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            long sum = 0;//注意long型
            int res = 0;
            for (int x : nums) {
                sum += x;
                if (x < 0) pq.offer(x);
                while (sum < 0) {
                    int cur = pq.poll();
                    sum -= cur;
                    res++;
                }
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
