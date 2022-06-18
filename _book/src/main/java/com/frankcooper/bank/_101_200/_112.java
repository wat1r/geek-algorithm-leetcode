package com.frankcooper.platform.leetcode.bank._101_200;

import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Date 2020/7/7
 * @Author Frank Cooper
 * @Description
 */
public class _112 {
    static _112 handler = new _112();

    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root.left == null && root.right == null && root.val == sum) return true;
        boolean res = false;
        if (root.left != null) res = res || dfs(root.left, sum - root.val);
        if (root.right != null) res = res || dfs(root.right, sum - root.val);
        return res;
    }


    public boolean hasPathSum2nd(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum2nd(root.left, sum - root.val) || hasPathSum2nd(root.right, sum - root.val);
    }


    public boolean hasPathSum3rd(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        nodeStack.push(root);
        numStack.push(sum - root.val);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            Integer curNum = numStack.pop();
            if (curNode.left == null && curNode.right == null && curNum == 0) return true;
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                numStack.push(curNum - curNode.left.val);
            }
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                numStack.push(curNum - curNode.right.val);
            }
        }
        return false;
    }


    public boolean hasPathSum4th(TreeNode root, int sum) {
        if (root == null) return false;
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.pop();
            Integer curNum = numQueue.pop();
            if (curNode.left == null && curNode.right == null && curNum == sum) return true;
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                numQueue.offer(curNum + curNode.left.val);
            }
            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                numQueue.offer(curNum + curNode.right.val);
            }
        }
        return false;
    }


    static class _1st {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) return false;
            return dfs(root, targetSum);
        }

        public boolean dfs(TreeNode root, int sum) {
            if (root.left == null && root.right == null && sum == root.val) return true;
            boolean f = false;
            if (root.left != null) f = f || dfs(root.left, sum - root.val);
            if (root.right != null) f = f || dfs(root.right, sum - root.val);
            return f;
        }
    }


    static class _2nd {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) return false;
            Queue<TreeNode> nodeQ = new LinkedList<>();
            Queue<Integer> sumQ = new LinkedList<>();
            nodeQ.add(root);
            sumQ.add(root.val);
            while (!nodeQ.isEmpty()) {
                TreeNode curNode = nodeQ.poll();
                int curSum = sumQ.poll();
                if (curNode.left == null && curNode.right == null && curSum == targetSum) return true;
                if (curNode.left != null) {
                    nodeQ.add(curNode.left);
                    sumQ.add(curSum + curNode.left.val);
                }
                if (curNode.right != null) {
                    nodeQ.add(curNode.right);
                    sumQ.add(curSum + curNode.right.val);
                }
            }
            return false;
        }
    }

    static class _3rd {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) return false;
            //当前的节点是叶子节点，比较剩下的sum值是否相等
            if (root.left == null && root.right == null) return root.val == sum;
            //左右两棵子树只要一个符合即可
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }
}
