package com.frankcooper.bank;

public class _189 {


    public static void main(String[] args) {

//        static _2nd handler = new _2nd();
        _1st handler = new _1st();

//        handler.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 0, 6);
        handler.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    }


    static class _1st {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int[] tmp = new int[n];
            for (int i = 0; i < n; i++) tmp[(i + k) % n] = nums[i];
            for (int i = 0; i < n; i++) nums[i] = tmp[i];
        }

    }


    static class _2nd {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n;
            reverse(nums, 0, n - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, n - 1);
        }

        public void reverse(int[] A, int l, int r) {
            while (l < r) {
                int m = A[l];
                A[l++] = A[r];
                A[r--] = m;
            }
        }
    }

}


