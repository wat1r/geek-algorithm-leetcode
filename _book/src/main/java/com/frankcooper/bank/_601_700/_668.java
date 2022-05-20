package com.frankcooper.bank._601_700;

/*import java.util.*;
import org.junit.Assert;*/
public class _668 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int findKthNumber(int m, int n, int k) {
            int l = 1, r = m * n;
            while (l < r) {
                int mid = l + (r - l) / 2;//下取整
                int count = check(m, n, mid);//当前小于等于（不大于）mid的数的个数
                //count的数与k比较
                if (count < k) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }

        //返回m*n的乘法表中，小于等于（不大于）target的数的个数
        public int check(int m, int n, int target) {
            int count = 0;
            for (int i = 1; i <= m; i++) {
                //每一行的个数都是n个，target/i 表示当前行强制匹配到第一行，与第一行的n进行同基准比较
                count += Math.min(target / i, n);
            }
            return count;
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
