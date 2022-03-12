package com.frankcooper.bank._1_100;

public class _31 {
    static _31 handler = new _31();

    public static void main(String[] args) {
        handler.nextPermutation(new int[]{1, 2, 3});
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        //最左边第一个最大的索引
        int leftIndex = -1;
        int n = nums.length, i = n - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) {
                leftIndex = i;
                break;
            }
            i--;
        }
        //如果没找到，翻转整个nums
        if (leftIndex == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        //右边稍大的索引
        int rightIndex = -1;
        int j = n - 1;
        while (j >= 0) {
            if (nums[j] > nums[leftIndex]) {
                rightIndex = j;
                break;
            }
            j--;
        }
        //交换，翻转
        swap(nums, leftIndex, rightIndex);
        reverse(nums, leftIndex + 1, n - 1);

    }

    //翻转i...j之间的数
    private void reverse(int[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }
    //交换索引为ij的两个数
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
