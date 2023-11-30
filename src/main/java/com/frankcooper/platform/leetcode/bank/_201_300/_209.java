package com.frankcooper.platform.leetcode.bank._201_300;

/*import java.util.*;
import org.junit.Assert;*/
public class _209 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{2, 3, 1, 2, 4, 3};
            int target = 7;
            handler.minSubArrayLen(target, nums);

        }

        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            int left = 0, ans = n + 1;
            int s = 0;
            for (int right = 0; right < n; right++) {
                s += nums[right];
                while (s - nums[left] >= target) {
                    s -= nums[left];
                    left++;
                }
                if (s >= target) {
                    ans = Math.min(ans, right - left + 1);
                }
            }
            return ans <= n ? ans : 0;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

//        时间复杂度O(n)
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            int left = 0, ans = n + 1;
            int s = 0;
            for (int right = 0; right < n; right++) {
                s += nums[right];
                while (s >= target) {
                    ans = Math.min(ans, right - left + 1);
                    s -= nums[left];
                    left++;
                }

            }
            return ans <= n ? ans : 0;
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
