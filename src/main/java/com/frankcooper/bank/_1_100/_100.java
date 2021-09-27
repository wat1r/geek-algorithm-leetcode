package com.frankcooper.bank._1_100;

import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/8/7
 * @Author Frank Cooper
 * @Description
 */
public class _100 {


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty()) {
            TreeNode currP = queue1.poll();
            TreeNode currQ = queue2.poll();
            if (currP == null && currQ == null) continue;
            if (currP.val != currQ.val) return false;
            TreeNode currPLeft = currP.left;
            TreeNode currPRight = currP.right;
            TreeNode currQLeft = currQ.left;
            TreeNode currQRight = currQ.right;
            if (currPLeft == null ^ currQLeft == null) return false;
            if (currPRight == null ^ currQRight == null) return false;
            if (currPLeft != null) queue1.offer(currPLeft);
            if (currPRight != null) queue1.offer(currPRight);
            if (currQLeft != null) queue2.offer(currQLeft);
            if (currQRight != null) queue2.offer(currQRight);
        }
        return true;
    }


    public boolean isSameTree1st(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree1st(p.left, q.left) && isSameTree1st(p.right, q.right);
    }

    static class _1st {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    static class _2nd {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

}
