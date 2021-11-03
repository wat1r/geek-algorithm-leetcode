package com.frankcooper.practise;


public class FreqPractiseOne {
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
            int target = 9;
            handler.search(nums, target);

        }

        public int search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] == target) return mid;
                else if (nums[mid] > target) r = mid;
                else l = mid + 1;
            }
            return nums[l] == target ? l : -1;
        }


        public int mySqrt(int x) {
            long l = 1, r = x / 2;
            while (l < r) {
                long mid = l + r >> 1;
                if (mid * mid == x) return (int) mid;
                else if (mid * mid > x) r = mid;
                else l = mid + 1;
            }
            return (int) (l * l == x ? l : l - 1);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int mySqrt(int x) {
            long l = 0, r = x;
            while (l < r) {
                long mid = l + r + 1 >> 1;
                if (mid * mid <= x) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return (int) l;
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
