package com.frankcooper.bank._1_100;

/*import java.util.*;
import org.junit.Assert;*/
public class _26 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            [1,1,2]  ->[1,2]
//[0,0,1,1,1,2,2,3,3,4] -> [0,1,2,3,4]
//[1] -> [1]

        }

        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 1) return nums.length;
            int i = 0, j = 1;
            for (; i < nums.length && j < nums.length; ) {
                while (j < nums.length && nums[i] == nums[j]) {
                    j++;
                }
                i++;
                if (j < nums.length) nums[i] = nums[j];
            }
            return i;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int i = 0, j = 1;
            while (j < nums.length) {
                if (nums[i] == nums[j]) j++;
                else {
                    int t = nums[++i];
                    nums[i] = nums[j];
                    nums[j++] = t;
                }
            }
            return i + 1;
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
