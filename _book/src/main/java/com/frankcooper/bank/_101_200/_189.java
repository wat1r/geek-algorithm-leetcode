package com.frankcooper.bank._101_200;

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

    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {1, 2, 3, 4, 5, 6, 7};
            int k = 3;
            handler.rotate(nums, k);
        }

        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n;
            reverse(nums, 0, n - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, n - 1);
        }

        private void reverse(int[] nums, int l, int r) {
            while (l < r) {
                int t = nums[l];
                nums[l++] = nums[r];
                nums[r--] = t;
            }
        }
    }

    static class _4th {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[(i + k) % n] = nums[i];
            for (int i = 0; i < n; i++) nums[i] = arr[i];
        }
    }

    static class _5th {

        public static void main(String[] args) {
            _5th handler = new _5th();
            int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
            int k = 3;
            handler.rotate(arr, k);
        }

        /**
         * https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-yuan-di-huan-wei-xiang-xi-tu-jie/
         * @param nums
         * @param k
         */
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            int cnt = 0;//交换的次数 交换的次数是n次
            for (int i = 0; cnt < n; i++) {
                int curIdx = i;//当前的元素下标
                int curV = nums[curIdx];//当前元素
                do {
                    int nxtIdx = (curIdx + k) % n;//下一个元素
                    int t = nums[nxtIdx];//暂存下一个元素
                    nums[nxtIdx] = curV;//当前元素占据下一个元素的位置
                    curV = t;//移动 元素 下标
                    curIdx = nxtIdx;
                    cnt++;//移动次数+1
                } while (curIdx != i);//放在后面
            }

        }
    }

}


