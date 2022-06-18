package com.frankcooper.platform.leetcode.bank._501_600;

public class _540 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int singleNonDuplicate(int[] nums) {
            int res = 0;
            for (int x : nums) {
                res ^= x;
            }
            return res;

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int singleNonDuplicate(int[] nums) {
            for (int i = 0; i < nums.length - 1; i += 2) {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
            return nums[nums.length - 1];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

        }

        public int singleNonDuplicate(int[] nums) {
            int n = nums.length;
            int l = 0, r = n - 1;
            while (l < r) {
//                int mid = l + r >> 1;
                int mid = (l + r) / 2;
                if (mid % 2 == 0) {//偶数
                    if (mid + 1 < n && nums[mid] == nums[mid + 1]) l = mid + 1;
                    else r = mid;
                } else {
                    if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) l = mid + 1;
                    else r = mid;
                }
            }
            return nums[l];
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
