package com.frankcooper.platform.nowcoder.swordoffer;

public class JZ85 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int[] FindGreatestSumOfSubArray(int[] arr) {
            if (arr == null || arr.length == 0) return new int[]{};
            int n = arr.length;
            //f[i]表示以arr[i]这个数为结尾的，连续子数组的最大和
            int[] f = new int[n];
            f[0] = arr[0];
            int maxSum = f[0];
            int l = 0, r = 0;//当前的滑窗
            int resl = 0, resr = 0;//结果集滑窗
            for (int i = 1; i < n; i++) {
                r++;//当前的滑窗的右区间
                //更新这个f[i]的值
                f[i] = Math.max(f[i - 1] + arr[i], arr[i]);
                //如果区间发生变化，重新移动当前的滑窗
                if (f[i - 1] + arr[i] < arr[i]) {//重新
                    l = r;
                }
                //maxSum遇到更大的，更新resl resr区间
                //如果maxSum相同，更新一个最长的resl resr区间
                if (f[i] > maxSum || (f[i] == maxSum && (resr - resl + 1) < (r - l + 1))) {
                    maxSum = f[i];
                    resl = l;
                    resr = r;
                }
            }
            //组装结果
            int[] res = new int[resr - resl + 1];
            for (int i = resl; i <= resr; i++) {
                res[i - resl] = arr[i];
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] arr = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
            handler.FindGreatestSumOfSubArray(arr);
        }

        public int[] FindGreatestSumOfSubArray(int[] arr) {
            if (arr == null || arr.length == 0) return new int[]{};
            int n = arr.length;
            int prev = arr[0], cur = 0;
            int maxSum = prev;
            int l = 0, r = 0;//当前的滑窗
            int resl = 0, resr = 0;//结果集滑窗
            for (int i = 1; i < n; i++) {
                r++;//当前的滑窗的右区间
                //更新这个f[i]的值
                cur = Math.max(prev + arr[i], arr[i]);
                //如果区间发生变化，重新移动当前的滑窗
                if (prev + arr[i] < arr[i]) {//重新
                    l = r;
                }
                //maxSum遇到更大的，更新resl resr区间
                //如果maxSum相同，更新一个最长的resl resr区间
                if (cur > maxSum || (cur == maxSum && (resr - resl + 1) < (r - l + 1))) {
                    maxSum = cur;
                    resl = l;
                    resr = r;
                }
                prev = cur;
            }
            //组装结果
            int[] res = new int[resr - resl + 1];
            for (int i = resl; i <= resr; i++) {
                res[i - resl] = arr[i];
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
