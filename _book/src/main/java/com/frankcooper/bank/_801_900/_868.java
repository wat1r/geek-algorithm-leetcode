package com.frankcooper.bank._801_900;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _868 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            PrintUtils.toBinaryString(67, 8);
            Assert.assertEquals(5, handler.binaryGap(67));
//            Assert.assertEquals(2, handler.binaryGap(1900));
        }

        public int binaryGap(int n) {
            //前一个为1的位置的索引，当前的索引
            int prev = -1, cur = 0;
            int gap = 0;
            while (n > 0) {
                //记录下当前位为1的索引，如果满足更新gap的条件，开始更新
                if ((n & 1) == 1) {
                    if (prev != -1) gap = Math.max(gap, cur - prev);
                    prev = cur;
                }
                n >>= 1;//向右移位，去掉一位
                PrintUtils.toBinaryString(n,8);
                cur++;//当前位+1
            }
            return gap;
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
