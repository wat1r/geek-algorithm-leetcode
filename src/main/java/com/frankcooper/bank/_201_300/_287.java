package com.frankcooper.bank._201_300;

/*import java.util.*;
import org.junit.Assert;*/
public class _287 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int findDuplicate(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) return nums[i];
            }
            return -1;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
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
