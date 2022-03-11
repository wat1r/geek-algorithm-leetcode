package com.frankcooper.bank._1001_1500;

public class _1248 {

    static _1248 handler = new _1248();

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        handler.numberOfSubarrays2nd(nums, k);
//        nums = new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
//        k = 2;
//        handler.numberOfSubarrays(nums, k);

    }

    /**
     * 当达到k的值后，后面有偶数就不断right++
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length, res = 0, index = 0, arr[] = new int[len + 2];
        for (int i = 0; i < len; i++) {
            // 奇数
            if ((nums[i] & 1) == 1) {
                arr[++index] = i;
            }
        }
        // 左边界
        arr[0] = -1;
        // 右边界
        arr[index + 1] = len;
        for (int i = 1; i + k < index + 2; i++) {
            res += (arr[i] - arr[i - 1]) * (arr[i + k] - arr[i + k - 1]);
        }
        return res;
    }


    public int numberOfSubarrays2nd(int[] nums, int k) {
        int n = nums.length;
        int[] counter = new int[n + 1];
        counter[0] = 1;
        int res = 0, odd = 0;
        for (int i = 0; i < n; i++) {
            odd += nums[i] & 1;
            if (odd >= k)res += counter[odd - k];
            counter[odd]++;
        }
        return res;
    }

}
