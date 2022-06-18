package com.frankcooper.platform.leetcode.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _167 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int[] twoSum(int[] numbers, int target) {
            int n = numbers.length, l = 0, r = n - 1;
            while (l < r) {
                int t = numbers[l] + numbers[r];
                if (t == target) return new int[]{l + 1, r + 1};
                else if (t > target) r--;
                else l++;
            }
            return new int[]{};
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
