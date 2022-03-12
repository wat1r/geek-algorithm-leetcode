package com.frankcooper.bank._1501_2000;

/**
 * Created by FrankCooper
 * Date 2020/10/4 10:32
 * Description
 */
public class _5531 {

    static _5531 handler = new _5531();

    public static void main(String[] args) {
        int[] nums = new int[]{0, 4, 3, 0, 4};
        nums = new int[]{3, 5};
        nums = new int[]{3, 6, 7, 7, 0};
        handler.specialArray(nums);

    }


    public int specialArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        for (int i = 0; i <= nums.length; i++) {
            int cnt = 0;
            for (int num : nums) {
                if (num >= i) {
                    cnt++;
                }
            }
            if (cnt == i) {
                return i;
            }
        }
        return -1;
    }
}
