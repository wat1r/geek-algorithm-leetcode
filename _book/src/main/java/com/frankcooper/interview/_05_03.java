package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _05_03 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(8, handler.reverseBits(1775));
        }


        public int reverseBits(int num) {
            int res = 0, prev = -1;//上一个位位0的位置
            int cnt = 0;
            for (int i = 0; i <= 32; i++) {
//                PrintUtils.toBinaryString(num, 11);
                if ((num & 1) == 1) cnt++;//当前位是1
                else {
                    res = Math.max(res, cnt);//取最大值
                    cnt = i - prev;//计算当前位与上一个0之间有多少个1
                    prev = i;//更新上一个位0的位置
                }
                num >>>= 1;
            }
            return res;
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
