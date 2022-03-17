package com.frankcooper.bank._1_100;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/8/6
 * @Author Frank Cooper
 * @Description
 */
public class _15 {
    static _15 handler = new _15();

    public static void main(String[] args) {
        handler.threeSum(new int[]{1, 0, 1, 2, -1, -4});

        //[-1,0,1,2,-1,-4]
        //[[-1,-1,2],[-1,0,1]]
//
//        Assert.e
        System.out.println("dnd");

    }

    /*
     *[-1,0,1,2,-1,-4]
     *[[-1,-1,2],[-1,0,1]]
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 3) return results;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    results.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l + 1] == nums[l]) l++;
                    while (l < r && nums[r - 1] == nums[r]) r--;
                    l++;
                    r--;
                } else if (sum < 0) l++;
                else if (sum > 0) r--;
            }
        }
        return results;
    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {-1, 0, 1, 2, -1, -4};
            handler.threeSum(nums);
        }

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            int n = nums.length;
            Arrays.sort(nums);
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) break;
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int l = i + 1, r = n - 1;
                while (l < r) {
                    int t = nums[i] + nums[l] + nums[r];
                    if (t == 0) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (t > 0) {
                        r--;
                    } else if (t < 0) {
                        l++;
                    }
                }
            }
            return res;
        }
    }


    static class _2nd {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length <= 2) return res;
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) break;
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int l = i + 1, r = n - 1;
                while (l < r) {
                    int t = nums[i] + nums[l] + nums[r];
                    if (t == 0) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (t < 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
            return res;
        }

    }
}
