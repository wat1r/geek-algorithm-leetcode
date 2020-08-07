package com.frankcooper.bank;

import com.frankcooper.struct.TreeNode;

/**
 * @Date 2020/8/7
 * @Author Frank Cooper
 * @Description
 */
public class _100 {


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
