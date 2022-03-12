package com.frankcooper.bank._1001_1500;

import com.frankcooper.struct.TreeNode;

public class _1448 {


    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);

    }

    private int dfs(TreeNode root, int maxv) {
        if (root == null) return 0;
        int res = maxv <= root.val ? 1 : 0;
        maxv = Math.max(maxv, root.val);
        return res + dfs(root.left, maxv)+dfs(root.right, maxv);
    }


}
