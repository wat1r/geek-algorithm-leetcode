package com.frankcooper.platform.leetcode.bank._201_300;

public class _283 {

    //方法1：双指针
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int index = 0, i = 0; i < nums.length; i++) {
            if (nums[i] != 0) swap(nums, index++, i);
        }
    }

    public void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
//        arr[m] = arr[m] ^ arr[n];
//        arr[n] = arr[m] ^ arr[n];
//        arr[m] = arr[m] ^ arr[n];
    }


    /**
     * **方法2：辅助指针**
     * <p>
     * #### 解题思路
     * <p>
     * - 解法简单，准备一个$index$，第一遍遍历，将不是0的数字前置
     * <p>
     * - 第二遍的时候从$index$开始，到结尾的数字置为0
     */
    public void moveZeroes2nd(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    static class _1st {

        public static void main(String[] args) {
            _1st hander = new _1st();
            int[] nums = {0, 1, 0, 3, 12};
            hander.moveZeroes(nums);
        }

        public void moveZeroes(int[] nums) {

            for (int i = 0, j = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    swap(nums, i, j++);
                }
            }
        }

        public void swap(int[] arr, int m, int n) {
            int temp = arr[m];
            arr[m] = arr[n];
            arr[n] = temp;
        }
    }

    static class _2nd {
        public void moveZeroes(int[] nums) {
            // if(nums == null ||)
            int idx = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    swap(nums, idx++, i);
                }
            }
        }


        public void swap(int[] arr, int m, int n) {
            int temp = arr[m];
            arr[m] = arr[n];
            arr[n] = temp;
//        arr[m] = arr[m] ^ arr[n];
//        arr[n] = arr[m] ^ arr[n];
//        arr[m] = arr[m] ^ arr[n];
        }
    }
}
