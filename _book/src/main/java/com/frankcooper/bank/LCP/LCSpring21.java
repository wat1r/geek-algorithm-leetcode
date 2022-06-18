package com.frankcooper.platform.leetcode.bank.LCP;

import java.util.*;

import org.junit.Assert;

public class LCSpring21 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {2, 2, 1, 9};
            int target = 10;
            nums = new int[]{2, 5, 3, 5};
            target = 6;
            handler.purchasePlans(nums, target);

        }

        int MOD = (int) 1e9 + 7;
        long res = 0;

        public int purchasePlans(int[] nums, int target) {
            dfs(nums, 0, new ArrayList<>(), target);
            return (int) res % MOD;
        }

        public void dfs(int[] nums, int idx, List<Integer> list, int target) {
            if (sum(list) > target) return;
            if (sum(list) <= target && list.size() == 2) {
                res++;
                return;
            }
            for (int i = idx; i < nums.length; i++) {
                if (nums[i] > target) continue;
                list.add(nums[i]);
                dfs(nums, i + 1, list, target);
                list.remove(list.size() - 1);
            }
        }

        private int sum(List<Integer> list) {
            int res = 0;
            for (int x : list) {
                res += x;
            }
            return res;
        }
    }

    static class _1st_1 {
        public static void main(String[] args) {
            _1st_1 handler = new _1st_1();

        }

        public int purchasePlans(int[] nums, int target) {
            Arrays.sort(nums);
            int l = 0, r = nums.length - 1;
            long res = 0L;
            while (l < r) {
//                while (nums[l] + nums[r] <= target)
            }

            return 0;
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
