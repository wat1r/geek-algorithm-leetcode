package com.frankcooper.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _611 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int res = 0;
            int n = nums.length, i = 0;
            for (; i <= n - 3; i++) {
                for (int j = i + 1; j <= n - 2; j++) {
                    int k = j + 1;
                    while (k < n && nums[i] + nums[j] > nums[k]) {
                        k++;
                    }
                    res += k - j - 1;
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{2, 2, 3, 4};
            handler.triangleNumber(nums);
        }


        public int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int res = 0;
            int n = nums.length;
            for (int i = 0; i <= n - 3; i++) {
                for (int j = i + 1; j <= n - 2; j++) {
                    int lo = j + 1, hi = n - 1;
                    while (lo < hi) {
                        //找最后一个符合三角形的下标索引k(第三个数)
                        int mid = lo + hi + 1 >> 1;//上取整
                        if (nums[i] + nums[j] > nums[mid]) {
                            lo = mid;
                        } else {
                            hi = mid - 1;
                        }
                    }
                    //再次判断一一轮
                    if (nums[lo] < nums[i] + nums[j]) {
                        res += lo - j;
                    }
                }
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
