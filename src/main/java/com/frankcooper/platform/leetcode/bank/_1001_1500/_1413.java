package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1413 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int minStartValue(int[] nums) {
            int l = 1, r = 10010;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (valid(nums, mid)) r = mid;
                else l = mid + 1;
            }
            return l;
        }


        private boolean valid(int[] nums, int startValue) {
            for (int x : nums) {
                startValue += x;
                if (startValue < 1) return false;
            }
            return true;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //记录最小的累加和,最后满足 accSumMin + startValue >=1
        public int minStartValue(int[] nums) {
            int accSum = 0, accSumMin = 0;
            for (int x : nums) {
                accSum += x;
                accSumMin = Math.min(accSumMin, accSum);
            }
            return -accSumMin + 1;
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
