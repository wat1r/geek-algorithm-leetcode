package com.frankcooper.interview;

import com.frankcooper.utils.PrintUtils;

/*import java.util.*;
import org.junit.Assert;*/
public class _05_04 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int num = 13948;
            num = 10115;
            handler.findClosedNumbers(num);
        }

        /**
         * 比 num 大的数：从右往左，找到第一个 01 位置，然后把 01 转为 10，右侧剩下的 1 移到右侧的低位，右侧剩下的位清0。
         * 比 num 小的数：从右往左，找到第一个 10 位置，然后把 10 转为 01，右侧剩下的 1 移到右侧的高位，右侧剩下的位置0。
         * <p>
         * 常规上，低位在右侧，bitset 注意方向相反
         */

        /**
         * https://leetcode-cn.com/problems/closed-number-lcci/solution/javawei-yun-suan-xiang-xi-tu-jie-by-wond-vknt/
         *
         * @param num
         * @return
         */
        public int[] findClosedNumbers(int num) {
            PrintUtils.toBinaryString(num, 14);
//            getNextMax(num);
            getPrevMin(num);
            return null;

        }


        public int getNextMax(int n) {
            int t = n, c0 = 0, c1 = 0;
            while ((t & 1) == 0 && (t != 0)) {
                c0++;
                t >>= 1;
                PrintUtils.toBinaryString(t, 14);
            }
            while ((t & 1) == 1) {
                c1++;
                t >>= 1;
                PrintUtils.toBinaryString(t, 14);
            }
            int p = c0 + c1;
            PrintUtils.toBinaryString(n, 14);
            n |= (1 << p);
            PrintUtils.toBinaryString(n, 14);
            n &= -(1 << p);
            PrintUtils.toBinaryString(n, 14);
            n |= (1 << (c1 - 1)) - 1;
            PrintUtils.toBinaryString(n, 14);
            return n;
        }


        public int getPrevMin(int n) {
            int t = n, c0 = 0, c1 = 0;
            while ((t & 1) == 1) {
                c1++;
                t >>= 1;
            }
            while ((t & 1) == 0 && t != 0) {
                c0++;
                t >>= 1;
            }
            PrintUtils.toBinaryString(n, 14);
            int p = c0 + c1;
            n &= ((~0) << (p + 1));
//            n &= -(1 << (p + 1));
            PrintUtils.toBinaryString(n, 14);
            int mask = (1 << (c1 + 1)) - 1;
            PrintUtils.toBinaryString(n, 14);
            n |= mask << (c0 - 1);
            PrintUtils.toBinaryString(n, 14);
            System.out.println(n);
            return n;
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
