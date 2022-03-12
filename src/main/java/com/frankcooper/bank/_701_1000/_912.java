package com.frankcooper.bank._701_1000;

public class _912 {

    static _912 handler = new _912();

    public static void main(String[] args) {
        int[] nums = new int[]{5, 1, 1, 2, 0, 0};
        handler.sortArray(nums);
    }


    /**
     * https://leetcode-cn.com/problems/sort-an-array/solution/fu-xi-ji-chu-pai-xu-suan-fa-java-by-liweiwei1419/
     *
     * @param nums
     * @return
     */

    public int[] sortArray(int[] nums) {
        int len = nums.length;
        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
        for (int i = 1; i < len; i++) {
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;
            // 注意边界 j > 0
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }


    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.sortArray(new int[]{5, 2, 3, 1});
        }

        public int[] sortArray(int[] nums) {
            return quickSort(nums, 0, nums.length - 1);

        }


        private int[] quickSort(int[] nums, int left, int right) {
            if (left < right) {//注意此处的if
                int pId = parition(nums, left, right);
                quickSort(nums, left, pId - 1);
                quickSort(nums, pId + 1, right);
            }
            return nums;
        }

        private int parition(int[] nums, int left, int right) {
            int pivot = left;
            System.out.printf("%d\n",pivot);
            int index = pivot + 1;
            for (int i = index; i <= right; i++) {
                if (nums[i] < nums[pivot]) {
                    swap(nums, i, index);
                    index++;
                }
            }
            swap(nums, pivot, index - 1);
            return index - 1;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

}
