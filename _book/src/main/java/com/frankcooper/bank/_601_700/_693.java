package com.frankcooper.bank._601_700;

import java.util.*;

import com.frankcooper.utils.BitOpUtils;
import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _693 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean hasAlternatingBits(int n) {
            int t = -1;//初始值
            while (n > 0) {
                //拿到最右边的一位
                int bit = n & 1;
                //如果当前的bit位于上一位t是相同的，则返回false
                //异或（ ^ ）每一位进行比较，相同为0，不同为1
                if ((t ^ bit) == 0) return false;
                //当前位bit 赋值给上一位 t
                t = bit;
                //右移一位
                n >>= 1;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int n = 11;
            handler.hasAlternatingBits2(n);
        }

        boolean hasAlternatingBits2(int n) {
        /*
        n =         1 0 1 0 1 0 1 0
        n >> 1      0 1 0 1 0 1 0 1
        n ^ n>>1    1 1 1 1 1 1 1 1
        n           1 1 1 1 1 1 1 1
        n + 1     1 0 0 0 0 0 0 0 0
        n & (n+1)   0 0 0 0 0 0 0 0
        */
            PrintUtils.toBinaryString(n,8);
            n = n ^ (n >> 1);
            PrintUtils.toBinaryString(n,8);
            PrintUtils.toBinaryString(n+1,8);
            return (n & n + 1) == 0;
        }

        /**
         *   public boolean hasAlternatingBits(int n) {
         *          n = n ^ (n >> 1);
         *             return (n & n + 1) == 0;
         *     }
         */
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public boolean hasAlternatingBits(int n) {
            char[] bits = Integer.toBinaryString(n).toCharArray();
            for (int i = 0; i < bits.length - 1; i++) {
                if (bits[i] == bits[i + 1]) {
                    return false;
                }
            }
            return true;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
