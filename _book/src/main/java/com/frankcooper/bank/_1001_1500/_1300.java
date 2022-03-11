package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1300 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            int[] arr = new int[]{2, 3, 5};
            int target = 5;
//            Assert.assertEquals(10, handler.cal(arr, target ));
            arr = new int[]{60864, 25176, 27249, 21296, 20204};
            target = 11361;
//            Assert.assertEquals(56805, handler.cal(arr, target ));
            arr = new int[]{4, 9, 3};
//            Arrays.sort(arr);
            target = 10;
//            Assert.assertEquals(16, handler.cal(arr, target ));

//            handler.findBestValue(arr,target);

            arr = new int[]{60864, 25176, 27249, 21296, 20204};
            target = 56803;
//            Assert.assertEquals(11361, handler.findBestValue(arr, target));
            arr = new int[]{15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            target = 50;
            Assert.assertEquals(15, handler.findBestValue(arr, target));
        }

        public int findBestValue(int[] arr, int target) {
            int n = arr.length;
            Arrays.sort(arr);
            int lo = 0, hi = arr[n - 1];
//            int lo = 1, hi = 100000;
            while (lo <= hi) {
                int mid = lo + hi >> 1;
                int t = cal(arr, mid);
                if (t == target) {
                    return mid;
                } else if (t > target) {
                    hi = mid - 1;//排除掉了mid
                } else {
                    lo = mid + 1;
                }
            }
            if (Math.abs(cal(arr, lo) - target) < Math.abs(cal(arr, hi) - target)) {
                return lo;
            }
            return hi;
        }


        /**
         * 计算排序数组arr中 将大于等于value的值全部变成value后，arr的和
         *
         * @param arr
         * @param value
         * @return
         */
        private int cal(int[] arr, int value) {
            int n = arr.length;
            int[] preSum = new int[n + 1];
            //计算前缀和
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + arr[i - 1];
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;//下取整
                if (arr[mid] < value) l = mid + 1;
                else r = mid;
            }
            //退出条件l = r 都在数组范围内，判断下是不是最后一个元素，并且是否大于value，大于的话，说明所有的元素都大于value
            if (l == n - 1 && arr[l] < value) l++;
            int res = (n - l) * value;
            res += preSum[l];
            return res;
        }


//         private int[] find(int[] )

        //小于等于target的最大的值
        //大于等于target的最小的值
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * Arrays.binarySearch(arr[],val)
         * 如果找到val的话 ，返回arr数组中val的下标
         * 如果找不到val的话，返回val应该返回 -(插入点 + 1)
         */
        public int findBestValue(int[] arr, int target) {
            Arrays.sort(arr);
            int n = arr.length;
            int[] preSum = new int[n + 1];
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + arr[i - 1];
            int lo = 0, hi = arr[n - 1];
            int diff = target, res = 0;
            for (int i = lo; i <= hi; i++) {
                int idx = Arrays.binarySearch(arr, i);
                //这里处理很关键，如果没找到i这个数，返回 -(插入点 + 1)
                if (idx < 0) idx = -idx - 1;
                //计算和
                int t = preSum[idx] + (n - idx) * i;
                if (Math.abs(t - target) < diff) {
                    res = i;
                    diff = Math.abs(t - target);
                }
            }
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int findBestValue(int[] arr, int target) {
            int n = arr.length;
            Arrays.sort(arr);
            int lo = 0, hi = arr[n - 1];
            while (lo < hi) {
                int mid = lo + hi >> 1;
                int t = cal(arr, mid);
                if (t > target) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            int x = Math.abs(cal(arr, lo) - target);
            int y = Math.abs(cal(arr, lo - 1) - target);
            return x < y ? lo : lo - 1;
        }


        /**
         * 计算排序数组arr中 将大于等于value的值全部变成value后，arr的和
         *
         * @param arr
         * @param value
         * @return
         */
        private int cal(int[] arr, int value) {
            int n = arr.length;
            int[] preSum = new int[n + 1];
            //计算前缀和
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + arr[i - 1];
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;//下取整
                if (arr[mid] < value) l = mid + 1;
                else r = mid;
            }
            //退出条件l = r 都在数组范围内，判断下是不是最后一个元素，并且是否大于value，大于的话，说明所有的元素都大于value
            if (l == n - 1 && arr[l] < value) l++;
            int res = (n - l) * value;
            res += preSum[l];
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
