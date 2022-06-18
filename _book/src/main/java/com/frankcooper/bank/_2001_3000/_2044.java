package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2044 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 3};
            handler.countMaxOrSubsets(nums);

        }

        //k:子集或后的值，v:该值出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        public int countMaxOrSubsets(int[] nums) {
            int maxx = -1;
            subsets(nums);
            for (List<Integer> sub : res) {
                int t = 0;
                for (int x : sub) t |= x;//是或「|」 不是异或 「^」
                maxx = Math.max(maxx, t);
                map.put(t, map.getOrDefault(t, 0) + 1);
            }
            return map.get(maxx);
        }


        List<List<Integer>> res = new ArrayList<>();
        //枚举所有子集
        public List<List<Integer>> subsets(int[] nums) {
            if (nums == null || nums.length == 0) return res;
            dfs(new ArrayList<>(), nums, 0);
            return res;
        }

        private void dfs(List<Integer> sub, int[] nums, int idx) {
            if (idx == nums.length) {
                res.add(new ArrayList<>(sub));
                return;
            }
            sub.add(nums[idx]);
            dfs(sub, nums, idx + 1);
            sub.remove(sub.size() - 1);
            dfs(sub, nums, idx + 1);
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
