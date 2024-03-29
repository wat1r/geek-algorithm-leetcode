package com.frankcooper.platform.lintcode;

/*import java.util.*;
import org.junit.Assert;*/
public class _868 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public double findMaxAverage(int[] nums, int k) {
            int i = 0, j = 0;
            int sum = 0;
            double res = 0.0;
            while (j < nums.length) {
                while (j - i + 1 <= k) {
                    sum += nums[j++];
                }
                res = Math.max(res, sum * 1.0 / k);
                sum -= nums[i++];
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
