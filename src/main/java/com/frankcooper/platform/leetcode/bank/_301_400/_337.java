package com.frankcooper.platform.leetcode.bank._301_400;

import com.frankcooper.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/8/5
 * @Author Frank Cooper
 * @Description
 */
public class _337 {

    public static void main(String[] args) {

    }


    class _3rd {


        //f[0]表示选当前节点所能得到的最大值，f[1]表示不选当前节点所能得到的最大值
        public int rob(TreeNode root) {
            int[] f = dfs(root);
            return Math.max(f[0], f[1]);
        }

        private int[] dfs(TreeNode node) {
            if (node == null) return new int[]{0, 0};
            int[] fLeft = dfs(node.left);
            int[] fRight = dfs(node.right);
            int[] f = new int[2];
            f[0] = Math.max(fLeft[0], fLeft[1]) + Math.max(fRight[0], fRight[1]);
            f[1] = fLeft[0] + fRight[0] + node.val;
            return f;
        }

    }


    public int rob2nd(TreeNode root) {
        if (root == null) return 0;
        int first = root.val;
        if (root.left != null) first += rob2nd(root.left.left) + rob2nd(root.left.right);
        if (root.right != null) first += rob2nd(root.right.left) + rob2nd(root.right.right);
        int second = rob2nd(root.left) + rob2nd(root.right);
        return Math.max(first, second);
    }


    public int rob1st(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        return Math.max(dfs(root, true), dfs(root, false));

    }

    private int dfs(TreeNode root, boolean visit) {
        if (root.left == null && root.right == null) return visit ? root.val : 0;
        int leftValue = 0, rightValue = 0;
        if (visit) {
            if (root.left != null) leftValue = dfs(root.left, false);
            if (root.right != null) rightValue = dfs(root.right, false);
            return root.val + leftValue + rightValue;
        } else {
            if (root.left != null) leftValue = Math.max(dfs(root.left, true), dfs(root.left, false));
            if (root.right != null) rightValue = Math.max(dfs(root.right, true), dfs(root.right, false));
            return leftValue + rightValue;
        }
    }


    static class _4th {

        Map<TreeNode, Integer> memo = new HashMap<>();

        public int rob(TreeNode root) {
            return Math.max(dfs(root, false), dfs(root, true));
        }

        private int dfs(TreeNode root, boolean steal) {
            if (root.left == null && root.right == null) return steal ? root.val : 0;
            if (memo.containsKey(root)) {
                return memo.get(root);
            }
            int lv = 0, rv = 0;
            int x = 0;
            int y = 0;
            if (steal) {
                if (root.left != null) lv = dfs(root.left, false);
                if (root.right != null) rv = dfs(root.right, false);
                x = root.val + lv + rv;
            } else {
                if (root.left != null) lv = Math.max(dfs(root.left, false), dfs(root.left, true));
                if (root.right != null) rv = Math.max(dfs(root.right, false), dfs(root.right, true));
                y = lv + rv;
            }
            int res = Math.max(x, y);
            memo.put(root, res);
            return res;
        }


    }

    static class _5th {
        Map<TreeNode, Integer> memo = new HashMap<>();

        public int rob(TreeNode root) {
            if (root == null) return 0;
            if (memo.containsKey(root)) return memo.get(root);
            int val = 0;
            if (root.left != null) {
                val += rob(root.left.left) + rob(root.left.right);
            }
            if (root.right != null) {
                val += rob(root.right.left) + rob(root.right.right);
            }
            int steal = root.val + val;
            int non_steal = rob(root.left) + rob(root.right);
            int res = Math.max(steal, non_steal);
            memo.put(root, res);
            return res;
        }
    }


    static class _6th {
        public int rob(TreeNode root) {
            int[] t = rob_sub(root);
            return Math.max(t[0], t[1]);
        }

        private int[] rob_sub(TreeNode root) {
            if (root == null) return new int[2];
            int[] t = new int[2];
            //当前节点的左孩子节点所能带来的偷|不偷带来的最大金额 left_values -> lvs
            int[] lvs = rob_sub(root.left);
            //当前节点的右孩子节点所能带来的偷|不偷带来的最大金额 right_values -> rvs
            int[] rvs = rob_sub(root.right);
            //[0]表示当前节点不偷，[1]表示当前节点偷了
            t[0] = Math.max(lvs[0], lvs[1]) + Math.max(rvs[0], rvs[1]);
            t[1] = root.val + lvs[0] + rvs[0];
            return t;
        }
    }

    static class _7th {

        //记忆化，按steal和non_steal存两份，写法丑陋~
        Map<TreeNode, Integer> steal_map = new HashMap<>();//steal
        Map<TreeNode, Integer> non_steal_map = new HashMap<>();//non_steal

        public int rob(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return root.val;
            return Math.max(dfs(root, true), dfs(root, false));

        }

        private int dfs(TreeNode root, boolean visit) {
            if (root.left == null && root.right == null) return visit ? root.val : 0;
            //这里如果两个map都有值，需要结合visit状态来选，只有一个map有值的情况也是同样处理
            if (steal_map.containsKey(root) && non_steal_map.containsKey(root))
                return visit ? steal_map.get(root) : non_steal_map.get(root);
            if (visit && steal_map.containsKey(root)) return steal_map.get(root);
            if (!visit && non_steal_map.containsKey(root)) return non_steal_map.get(root);
            int leftValue = 0, rightValue = 0;
            if (visit) {
                if (root.left != null) leftValue = dfs(root.left, false);
                if (root.right != null) rightValue = dfs(root.right, false);
                int res = root.val + leftValue + rightValue;
                steal_map.put(root, res);
                return res;
            } else {
                if (root.left != null) leftValue = Math.max(dfs(root.left, true), dfs(root.left, false));
                if (root.right != null) rightValue = Math.max(dfs(root.right, true), dfs(root.right, false));
                int res = leftValue + rightValue;
                non_steal_map.put(root, res);
                return res;
            }
        }
    }

}
