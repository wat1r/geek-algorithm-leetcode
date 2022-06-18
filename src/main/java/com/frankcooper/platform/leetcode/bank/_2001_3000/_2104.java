package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

public class _2104 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] nums = {-31372, 86677, 70463, 37727, -91683, -41347, -90576, -82174, -84198, -5148, -12591, -34156, 49770, 9666, -77075, -57678, -31101, -47531, -86306, -91337, -89507, -24917, -87692, -39171, 98075, 17787, -42549, 34352, -70752, 71832, 70055, -1026, 3784, 2190, -36669, 959, 50619, 97129, 77088, 54049, 51707, 72052, 59230, -96834, -14048, -9619, 84853, 99362, 69885, 74086, -28737, 23060, -63323, 13156, -72998, 94336, -75409, 58266, -86800, -54564, 80773, 40687, -47207, 43609, -56556, 21192, -48024, -58907, 1629, -65561, -68397, 31862, -2201, -34966, 43542, -59201, -3637, -21936, -93559, 49435, 23249, -54299, 70508, -90795, -3620, -33894, 43927, 10208, -7390, 86931, 48175, 81859, 95058, -16614, 38066, -99361, 63621, -99285, -47111, 29933, 73901, 60455, 46586, -84117, 35256, -89853, 33383, -91662, 82979, -48835, -93877, -80929, -98904, -47773, 69451, 85183, -14449, -51496, 75765, 35062, 12456, 35254, -16363, 80792, -3414, 9244, 62961, -52057, 56344, -50277, -26870, -63323, 54993, 75596, -93637, -78526, -3058, -30560, 82233, -50795, -5290, -641, -83040, 13524, 86725, 23735, 29280, 43938, -43995, -8992, -83717, -62090, 74538, 58682, -56550, -8638, 61528, -87974};
            handler.subArrayRanges(nums);
        }

        /**
         * 暴力双层循环
         *
         * @param nums
         * @return
         */
        public long subArrayRanges(int[] nums) {
            int n = nums.length;
            long res = 0;
            for (int i = 0; i < n; i++) {
                long minn = nums[i];
                long maxx = nums[i];
                for (int j = i; j < n; j++) {
                    minn = Math.min(minn, nums[j]);
                    maxx = Math.max(maxx, nums[j]);
                    res += maxx - minn;
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {1, 2, 3};
            handler.subArrayRanges(nums);
        }

        int n;

        public long subArrayRanges(int[] nums) {
            n = nums.length;
            // min[i] 为 nums[i] 作为区间最小值的次数；max[i] 为 nums[i] 作为区间最大值的次数
            long[] min = getCnt(nums, true), max = getCnt(nums, false);
            long ans = 0;
            for (int i = 0; i < n; i++) ans += (max[i] - min[i]) * nums[i];
            return ans;
        }

        long[] getCnt(int[] nums, boolean isMin) {
            int[] left = new int[n], right = new int[n];
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() &&
                        (isMin ? nums[stk.peek()] >= nums[i] : nums[stk.peek()] <= nums[i]))
                    stk.pop();
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);
            }
            stk.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!stk.isEmpty() &&
                        (isMin ? nums[stk.peek()] > nums[i] : nums[stk.peek()] < nums[i]))
                    stk.pop();
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }
            long[] ans = new long[n];
            for (int i = 0; i < n; i++) ans[i] = (i - left[i]) * 1L * (right[i] - i);
            return ans;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {1, 2, 3};
            handler.subArrayRanges(nums);
        }

        public long subArrayRanges(int[] nums) {
            int n = nums.length;
            //计算nums[i]在 nums中 作为最大值和最小值 所出现的最大区间大小
            //换言之，需要找到nums[i] 作为最大值，找到左边第一个比nums[i]小的索引j 找到右边第一个比nums[i]小的索引k 区间范围为[k-j]
            //同理，需要找到nums[i] 作为最小值，找到左边第一个比nums[i]大的索引j 找到右边第一个比nums[i]大的索引k 区间范围为[k-j]
            //这里因为做了两遍的比较，需要特别注意 在 严格相等的时候，只能取一边，另外一边如果重复取，则会重复
            long[] maxx = getCnt(nums, false);
            long[] minn = getCnt(nums, true);
            long res = 0;
            //计算当前元素作为最大值和最小值时，出现的次数，计算出「贡献值」
            for (int i = 0; i < n; i++) {
                res += nums[i] * (maxx[i] - minn[i]);
            }
            return res;
        }

        public long[] getCnt(int[] nums, boolean flag) {
            int n = nums.length;
            int[] left = new int[n], right = new int[n];
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                //为true时维持单调递增栈，遇到非严格递减趋势时，弹出栈顶元素「下标」
                //这时候找的是左边比小于等于nums[i]的索引，如果左边没有符合条件的下标则设置为-1，即索引0往左的一个位置
                while (!stk.isEmpty() && (flag ? nums[stk.peek()] >= nums[i] : nums[stk.peek()] <= nums[i])) {
                    stk.pop();
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);
            }
            stk.clear();
            //为true时维持单调递增栈，遇到非严格递减趋势时，弹出栈顶元素「下标」
            //这时候找的是右边比小于(此处没有等于)nums[i]的索引，如果左边没有符合条件的下标则设置为n，即索引「len(nums)」往右的一个位置
            for (int i = n - 1; i >= 0; i--) {
                while (!stk.isEmpty() && (flag ? nums[stk.peek()] > nums[i] : nums[stk.peek()] < nums[i])) {
                    stk.pop();
                }
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }
            long[] res = new long[n];
            //左侧有 (i - left[i]) 和选择，右侧有(right[i] - i)个选择
            //根据乘法原理,(i - left[i])*(right[i] - i)
            for (int i = 0; i < n; i++) {
                res[i] = 1L * (i - left[i]) * (right[i] - i);
            }
            return res;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
