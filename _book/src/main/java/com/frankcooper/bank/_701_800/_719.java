package com.frankcooper.bank._701_800;

import java.util.Arrays;

public class _719 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int smallestDistancePair(int[] nums, int k) {
            //排序
            Arrays.sort(nums);
            //[lo,hi]表示距离 最大的距离为首尾元素的差值
            int n = nums.length, lo = 0, hi = nums[n - 1] - nums[0];
            while (lo <= hi) {
                //下取整
                int mid = lo + (hi - lo) / 2;
                int cnt = 0;
                //枚举每一个j
                for (int j = 0; j < n; j++) {
                    //当前j的值往前推mid的距离，得到i的位置，查找i在nums中符合条件的最左的位置
                    int target = nums[j] - mid;
                    //查询到当前i能到的最左的位置的下标
                    int i = binarySearch(nums, j, target);
                    //个数累加
                    cnt += j - i;
                }
                //个数比k小，说明当前的距离mid小了，还够使得数量到达k
                if (cnt < k) lo = mid + 1;
                //这里需要考虑 cnt=k 这种情况下排除 当前的mid可以被排除 因为 二分返回的时候 ，lo = hi+1
                //会覆盖到这种情况，哪怕当前的场景下 cnt=k 只有一个值
                else hi = mid - 1;//个数比k大，说明当前的距离mid大了

            }
            return lo;
        }

        //在nums[0...right]区间内找比大于等于target的最左的下标
        private int binarySearch(int[] nums, int right, int target) {
            int lo = 0, hi = right;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] < target) lo = mid + 1;
                else hi = mid;
            }
            return lo;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int n = nums.length, lo = 0, hi = nums[n - 1] - nums[0];
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                int cnt = 0;
                for (int i = 0, j = 0; j < n; j++) {
                    while (nums[j] - nums[i] > mid) {
                        i++;
                    }
                    cnt += j - i;
                }
                if (cnt >= k) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            return lo;
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
