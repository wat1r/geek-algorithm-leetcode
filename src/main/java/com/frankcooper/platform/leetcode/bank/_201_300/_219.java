package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;
import org.junit.Assert;
public class _219 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1,2,3,1};
            int k = 3;
            Assert.assertTrue( handler.containsNearbyDuplicate(nums,k));
            nums = new int[]{1,0,1,1};
            k = 1;
            Assert.assertTrue( handler.containsNearbyDuplicate(nums,k));
            nums = new int[]{1,2,3,1,2,3};
            k =2;
            Assert.assertFalse( handler.containsNearbyDuplicate(nums,k));

        }
        //not complete
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            int i = 0, j = 0 ;
            while (j<nums.length){
                while (j-i <=k ){
                    if(i!=j && nums[i] ==nums[j] ) return true;
                    j++;
                }
                i++;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * 219. 存在重复元素 II Easy
         *
         * @param nums
         * @param k
         * @return 采用滑动数组，k是i j两个索引的绝对值差值
         */
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if (nums == null || nums.length == 0 ) {
                return false;
            }
            Set<Integer> helpSet = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                //当i 不大于k 事表示滑动窗口还未生成，i-k-1是需要code一下
                if (i > k) {
                    helpSet.remove(nums[i - k - 1]);
                }
                //有重复数据，add返回false的返回值
                if (!helpSet.add(nums[i])) {
                    return true;
                }
            }
            return false;
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
