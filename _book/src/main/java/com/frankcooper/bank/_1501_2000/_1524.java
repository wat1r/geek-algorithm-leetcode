package com.frankcooper.bank._1501_2000;

/*import java.util.*;
import org.junit.Assert;*/
public class _1524 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }


        public int numOfSubarrays(int[] arr) {
            int MOD = (int) 1e9 + 7;
            long ans = 0;
            int n = arr.length;
            int[] pre = new int[n + 1];
            long odd = 0, even = 0;//奇数的个数，偶数的个数
            for (int i = 0; i < n; i++) {
                pre[i + 1] = pre[i] + arr[i];
                if ((pre[i + 1] % 2) == 1) odd++;
                else even++;
            }
            ans = (odd * even) % MOD + odd;
            return (int) ans % MOD;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int numOfSubarrays(int[] arr) {
            final int MOD = (int) 1e9 + 7;
            int odd = 0, even = 1;
            int res = 0;
            int sum = 0;
            int length = arr.length;
            for (int i = 0; i < length; i++) {
                sum += arr[i];
                res = (res + (sum % 2 == 0 ? odd : even)) % MOD;
                if (sum % 2 == 0) {
                    even++;
                } else {
                    odd++;
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
