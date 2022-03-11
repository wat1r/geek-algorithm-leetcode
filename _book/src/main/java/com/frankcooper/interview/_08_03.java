package com.frankcooper.interview;

/**
 * @Date 2020/7/31
 * @Author Frank Cooper
 * @Description
 */
public class _08_03 {

    public static void main(String[] args) {


    }


    public int findMagicIndex1st(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) return i;
        }
        return -1;
    }


    public int findMagicIndex(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left > right) return -1;
        int mid = (right - left) / 2 + left;
        int leftRes = helper(nums, left, mid - 1);
        if (leftRes != -1) return leftRes;
        else if (nums[mid] == mid) return mid;
        return helper(nums, mid + 1, right);
    }
}
