package com.frankcooper.platform.leetcode.bank._601_700;

public class _650 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.minSteps(12);
        }


        public int minSteps(int n) {
            int[] f = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                f[i] = i;//该处值为最大值
                for (int j = i - 1; j > 1; --j) {
                    if (i % j == 0) {//从底部到顶部往回遍历，遇到整除的时候，最大化地缩减了打印的步骤
                        f[i] = f[j] + (i / j);
                        break;//此处是最好的情况，需要提前break掉
                    }
                }
            }
            return f[n];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int minSteps(int n) {
            int res = 0;
            for (int i = n, j = 0; i > 1; i = j) {
                for (j = i >> 1; i % j != 0; j--) ;
                res += i / j;
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
