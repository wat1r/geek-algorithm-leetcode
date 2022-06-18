package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _508 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int[] findFrequentTreeSum(TreeNode root) {
            if (root == null) return new int[]{};
            calSum(root);
            List<Integer> resList = new ArrayList<>();
            for (int k : map.keySet()) {
                if (maxv == map.get(k)) resList.add(k);
            }
            int[] res = new int[resList.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = resList.get(i);
            }
            return res;
        }


        int maxv = 0;
        Map<Integer, Integer> map = new HashMap<>();

        private int calSum(TreeNode root) {
            if (root == null) return 0;
            int left = calSum(root.left);
            int right = calSum(root.right);
            int sum = left + root.val + right;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            maxv = Math.max(maxv, map.get(sum));
            return sum;
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
