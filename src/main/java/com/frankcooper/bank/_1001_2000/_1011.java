package com.frankcooper.bank._1001_2000;


import java.util.*;

import org.junit.Assert;

public class _1011 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int D = 5;
            Assert.assertEquals(15, handler.shipWithinDays(weights, D));
            weights = new int[]{3, 2, 2, 4, 1, 4};
            D = 3;
            Assert.assertEquals(6, handler.shipWithinDays(weights, D));
            weights = new int[]{1, 2, 3, 1, 1};
            D = 4;
            Assert.assertEquals(3, handler.shipWithinDays(weights, D));
        }


        public int shipWithinDays(int[] weights, int D) {
            int lo = 1, hi = 0;//二分上界
            for (int w : weights) hi += w;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;//左移左动，向下取整
                //判断这个数是否满足要求
                int t = 0; //目标的可能天数
                int cur = 0;//当前的weight的值
                for (int w : weights) {
                    if (w > mid) {
                        /**
                         *  weights = new int[]{1, 2, 3, 1, 1};
                         *      D = 4;
                         *      对应这个case
                         */
                        t = D + 1;//
                        break;
                    }
                    if (cur + w > mid) {
                        cur = w;
                        t++;
                        continue;
                    }
                    if (t > D) {
                        break;
                    }
                    cur += w;
                }
                if (cur <= mid) t++;
                if (t <= D) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return lo;
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
