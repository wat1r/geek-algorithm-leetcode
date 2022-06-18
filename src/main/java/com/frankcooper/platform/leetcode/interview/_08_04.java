package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _08_04 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 3};
            handler.subsets(nums);

        }

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            res.add(new ArrayList<>());
            dfs(nums, 0, new ArrayList<>());
            return res;
        }

        private void dfs(int[] nums, int idx, List<Integer> sub) {
            res.add(new ArrayList<>(sub));
            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                dfs(nums, i + 1, sub);
                sub.remove(sub.size() - 1);
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{1, 2, 3};
            handler.subsets(nums);
        }


        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            int N = 1 << nums.length;
            for (int i = 0; i < N; i++) {
                List<Integer> sub = new ArrayList<>();
                for (int b = 0; b < nums.length; b++) {
                    if (((i >> b) & 1) == 1) {
                        sub.add(nums[b]);
                    }
                }
                res.add(new ArrayList<>(sub));
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
