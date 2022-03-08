package com.frankcooper.bank._1_100;

import java.util.Arrays;

/*import java.util.*;
import org.junit.Assert;*/
public class _16 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int threeSumClosest(int[] nums, int target) {
            //边界判断
            if (nums == null || nums.length < 3) return -1;
            //保持数组有序，使用双指针
            Arrays.sort(nums);
            int n = nums.length;
            //目前的三数之和的值
            int t = nums[0] + nums[1] + nums[2];
            //初始的t 与 target之间的差值，取绝对值
            int baseDelta = Math.abs(t - target);
            //开始遍历，i到n-3止
            for (int i = 0; i < n - 2; i++) {
                int l = i + 1, r = n - 1;//左右指针
                while (l < r) {
                    //三数之和
                    int sum = nums[i] + nums[l] + nums[r];
                    //新的 sum 与 target 之间的差值
                    int newDelta = Math.abs(sum - target);
                    //差值因为是绝对值，不可能比0还小
                    if (newDelta == 0) return sum;
                    //比较新旧差值，更新
                    if (newDelta < baseDelta) {
                        t = sum;
                        baseDelta = newDelta;
                    }
                    //双指针
                    if (sum > target) r--;
                    else l++;
                }
            }
            return t;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
