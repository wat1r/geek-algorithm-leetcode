package com.frankcooper.bank._501_700;

import com.frankcooper.struct.TreeNode;

import java.util.*;

public class _652 {

    /**
     * https://leetcode-cn.com/problems/find-duplicate-subtrees/solution/xun-zhao-zhong-fu-de-zi-shu-by-leetcode/
     */
    static class _1st {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            if (root == null) return res;
            dfs(root);
            return res;
        }

        private String dfs(TreeNode root) {
            if (root == null) return "";
            String serial = root.val + "," + dfs(root.left) + "," + dfs(root.right);
            map.put(serial, map.getOrDefault(serial, 0) + 1);
            if (map.get(serial) == 2) res.add(root);
            return serial;
        }
    }
}
