package com.frankcooper.platform.leetcode.bank._301_400;

import org.junit.Assert;

/*import java.util.*;
import org.junit.Assert;*/
public class _342 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            Assert.assertTrue(handler.isPowerOfFour(16));
        }


        public boolean isPowerOfFour(int n) {
            while (n > 0) {
                if (n == 1) return true;//1是4的幂
                if (n % 4 != 0) return false;//不能被在
                n /= 4;
            }
            return false;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        /**
         * 4 的幂 只能有一个二进制的1
         * <p>
         * a &= (a-1) 统计a的二进制位上1的个数
         * <p>
         * n 是一个 3232 位的有符号整数，因此我们可以构造一个整数
         * \textit{mask}mask，它的所有偶数二进制位都是 00，
         * 所有奇数二进制位都是 11。这样一来，我们将 nn 和 \textit{mask}mask 进行按位与运算，如果结果为 00，说明 nn 二进制表示中的 11 出现在偶数的位置，否则说明其出现在奇数的位置。
         * <p>
         * 根据上面的思路，\textit{mask}mask 的二进制表示为：
         * <p>
         * \textit{mask} = (10101010101010101010101010101010)_2
         * mask=(10101010101010101010101010101010)
         * 2
         * ​
         * <p>
         * <p>
         * 我们也可以将其表示成 1616 进制的形式，使其更加美观：
         * <p>
         * \textit{mask} = (\text{AAAAAAAA})_{16}
         * mask=(AAAAAAAA)
         * 16
         * ​
         *
         * @param n
         * @return
         */
        public boolean isPowerOfFour(int n) {
            return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        /**
         * 对3取模 一定是1
         * @param n
         * @return
         */
        public boolean isPowerOfFour(int n) {
            return n > 0 && (n & (n - 1)) == 0 && (n % 3) == 1;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
