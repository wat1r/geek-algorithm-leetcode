package com.frankcooper.bank._301_400;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class _376 {
    static _376 handler = new _376();

    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        nums = new int[]{0, 0};
        nums = new int[]{0, 0, 0};
        nums = new int[]{1, 1, 7, 4, 9, 2, 5};
        handler.wiggleMaxLength(nums);
    }


    public int wiggleMaxLength1st(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int prevDiff = nums[1] - nums[0];
        int res = prevDiff == 0 ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            int nextDiff = nums[i] - nums[i - 1];
            if ((prevDiff <= 0 && nextDiff > 0) || (prevDiff >= 0 && nextDiff < 0)) {
                res++;
                prevDiff = nextDiff;
            }
        }
        return res;
    }


    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (i < nums.length) list.add(nums[i]);//
        }
        int res = 2;
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);
        if (arr.length <= 2) return arr.length;
        for (int i = 1; i + 1 < arr.length; i++) {
            int prev = arr[i - 1], curr = arr[i], next = arr[i + 1];
            if ((prev < curr && curr > next) || (prev > curr && curr < next)) {
                res++;
            }
        }
        return res;
    }

}
