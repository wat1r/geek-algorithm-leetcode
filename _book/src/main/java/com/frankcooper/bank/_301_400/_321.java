package com.frankcooper.platform.leetcode.bank._301_400;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Date 2020/9/10
 * @Author Frank Cooper
 * @Description
 */
public class _321 {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums1 = {3, 4, 6, 5};
            int[] nums2 = {9, 1, 2, 5, 8, 3};
            int k = 5;
            //[9, 8, 6, 5, 3]
            handler.maxNumber(nums1, nums2, k);
            nums1 = new int[]{6, 7};
            nums2 = new int[]{6, 0, 4};
            k = 5;
            //[6, 7, 6, 0, 4]
            handler.maxNumber(nums1, nums2, k);
        }


        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int m = nums1.length, n = nums2.length;
            int[] res = null;
            for (int i = 0; i <= k; i++) {
                int[] arr1 = getMaxKArray(nums1, Math.min(i, m));
                int[] arr2 = getMaxKArray(nums2, Math.min(k - i, n));
                int[] arr = null;
                if (arr1.length + arr2.length == k) {
                    arr = merge(arr1, arr2);
                    System.out.println(Arrays.toString(arr));
                }
                if (res == null || arr != null && arr.length == k && compare(arr, 0, res, 0)) {
                    res = arr;
                }
            }
            return res;
        }

        //在数组nums中拿到k个数，该数是单调递减的
        private int[] getMaxKArray(int[] nums, int k) {
            //维护一个单调递减的单调栈
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < nums.length; i++) {
                while (!stk.isEmpty() && stk.peek() < nums[i] && (k - stk.size()) < nums.length - i) {
                    stk.pop();
                }
                if (stk.size() < k) stk.push(nums[i]);
            }

            int[] res = new int[k];
            int idx = k - 1;
            while (!stk.isEmpty() && idx >= 0) res[idx--] = stk.pop();
            return res;
        }

        //合并A和B数组，生成一个新的数组
        private int[] merge(int[] A, int[] B) {
            int m = A.length, n = B.length;
            int[] res = new int[m + n];
            int idx = 0, i = 0, j = 0;
            while (i < m || j < n) {
                res[idx++] = compare(A, i, B, j) ? A[i++] : B[j++];
            }
            return res;
        }



        //比较A和B数组从idx1和idx2分别开始的字典序的大小
        private boolean compare(int[] A, int idx1, int[] B, int idx2) {
            while (idx1 < A.length && idx2 < B.length && A[idx1] == B[idx2]) {
                idx1++;
                idx2++;
            }
            return idx2 == B.length || (idx1 < A.length && A[idx1] > B[idx2]);
        }


    }

}
