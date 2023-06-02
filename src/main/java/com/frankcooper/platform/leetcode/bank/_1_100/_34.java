package com.frankcooper.platform.leetcode.bank._1_100;

public class _34 {

    static class _1st {


        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{5, 7, 7, 8, 8, 10};
            int target = 8;
//            handler.getFirst(nums, target);
            handler.getLast(nums, target);
        }


        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) return new int[]{-1, -1};
            int[] ans = new int[2];
            ans[0] = getFirst(nums, target);
            ans[1] = getLast(nums, target);
            return ans;
        }

        private int getFirst(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] >= target) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            return nums[l] == target ? l : -1;
        }

        private int getLast(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l + 1) / 2;
                if (nums[m] <= target) {
                    l = m;
                } else {
                    r = m - 1;
                }
            }
            return nums[l] == target ? l : -1;
        }
    }

    static class _2nd {
        /**
         * [1]
         * 1
         * []
         * 0
         * 注意上面的两个边界case
         **/
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) return new int[]{-1, -1};
            int[] res = new int[2];
            res[0] = getFirst(nums, target);
            res[1] = getLast(nums, target);
            return res;
        }


        private int getFirst(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] >= target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return nums[l] == target ? l : -1;
        }

        private int getLast(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (nums[mid] <= target) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return nums[l] == target ? l : -1;

        }
    }

    static class _3rd {
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[2];
            result[0] = findFirst(nums, target);
            result[1] = findLast(nums, target);
            return result;
        }

        private int findFirst(int[] nums, int target) {
            int idx = -1;
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nums[mid] >= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                if (nums[mid] == target) idx = mid;
            }
            return idx;
        }

        private int findLast(int[] nums, int target) {
            int idx = -1;
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nums[mid] <= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                if (nums[mid] == target) idx = mid;
            }
            return idx;
        }
    }

    static class _3rd_1 {
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) return new int[]{-1, -1};
            int[] ans = new int[2];
            ans[0] = findFirst(nums, target);
            ans[1] = findLast(nums, target);
            return ans;
        }


        private int findFirst(int[] nums, int t) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;//左
                if (nums[mid] >= t) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return nums[l] == t ? l : -1;
        }


        private int findLast(int[] nums, int t) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (nums[mid] <= t) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return nums[l] == t ? l : -1;
        }
    }


    static class _2nd_1 {
        public int[] searchRange(int[] nums, int target) {
            int[] res = new int[]{-1, -1};
            if (nums == null || nums.length == 0) return res;
            int n = nums.length;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            if (nums[l] == target) res[0] = l;

            l = 0;
            r = n - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            if (nums[l] == target) res[1] = l;
            return res;
        }
    }

    static class _4th {


        //- 核心要素
        //    - 注意区间开闭，三种都可以
        //    - 循环结束条件：当前区间内没有元素
        //    - 下一次二分查找区间：不能再查找(区间不包含)mid，防止死循环
        //    - 返回值：大于等于target的第一个下标（注意循环不变量）
        //
        //- 有序数组中二分查找的四种类型（下面的转换仅适用于数组中都是整数）
        //    1. 第一个大于等于x的下标： low_bound(x)
        //    2. 第一个大于x的下标：可以转换为`第一个大于等于 x+1 的下标` ，low_bound(x+1)
        //    3. 最后一个一个小于x的下标：可以转换为`第一个大于等于 x 的下标` 的`左边位置`, low_bound(x) - 1;
        //    4. 最后一个小于等于x的下标：可以转换为`第一个大于等于 x+1 的下标` 的 `左边位置`, low_bound(x+1) - 1;


        public int[] searchRange(int[] nums, int target) {
            int start = lower_bound(nums, target);
            if (start == nums.length || nums[start] != target) {
                return new int[]{-1, -1};
            }
            int end = lower_bound(nums, target + 1) - 1;
            return new int[]{start, end};
        }

        public int lower_bound(int[] nums, int target) {
            int l = 0, r = nums.length - 1;//闭区间
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < target) l = mid + 1;//[mid+1,r]
                else r = mid - 1;//[l,mid-1]
            }
            return l;
        }


        public int lower_bound1(int[] nums, int target) {
            int l = 0, r = nums.length;//左闭右开区间 [l,r)
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < target) l = mid + 1; //[mid+1,r)
                else r = mid;//[l,mid)
            }
            return l;
        }


        public int lower_bound2(int[] nums, int target) {
            int l = -1, r = nums.length;//开区间 (l,r)
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < target) l = mid; //(mid,r)
                else r = mid;//(l,mid)
            }
            return r;
        }
    }
}
