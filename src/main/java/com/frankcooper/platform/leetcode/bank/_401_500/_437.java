package com.frankcooper.platform.leetcode.bank._401_500;

import com.frankcooper.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/7/9
 * @Author Frank Cooper
 * @Description
 */
public class _437 {

    static class _1st {
        static _1st handler = new _1st();

        public static void main(String[] args) {

            handler.testOne();


        }


        public void testOne() {
            TreeNode treeNode10 = new TreeNode(10);
            TreeNode treeNode5 = new TreeNode(5);
            TreeNode treeNode_3 = new TreeNode(-3);
            TreeNode treeNode3 = new TreeNode(3);
            TreeNode treeNode2 = new TreeNode(2);
            TreeNode treeNode11 = new TreeNode(11);
            TreeNode treeNode3_ = new TreeNode(3);
            TreeNode treeNode_2 = new TreeNode(-2);
            TreeNode treeNode1 = new TreeNode(1);

            treeNode10.left = treeNode5;
            treeNode10.right = treeNode_3;
            treeNode5.left = treeNode3;
            treeNode3.right = treeNode2;
            treeNode_3.right = treeNode11;
            treeNode3.left = treeNode3_;
            treeNode3.right = treeNode_2;
            treeNode2.right = treeNode1;
            treeNode11.left = null;
            treeNode11.right = null;
            treeNode3_.left = null;
            treeNode3_.right = null;
            treeNode_2.left = null;
            treeNode_2.right = null;
            treeNode1.left = null;
            treeNode1.right = null;
            handler.pathSum(treeNode10, 8);
        }


        Map<Long, Integer> map = new HashMap<>();

        public int pathSum(TreeNode root, int sum) {
            map.put(0L, 1);
            return helper(root, sum, 0);
        }

        private int helper(TreeNode root, int sum, long pathSum) {
            int res = 0;
            if (root == null) return res;
            pathSum += root.val;//路径和加上当前节点的值
            //路径和-目标sum的值 的节点，这个节点是多少个，在此基础上累加
            res += map.getOrDefault(pathSum - sum, 0);
            //将从根节点到当前节点，有多少的路径和等于pathSum存入map
            map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
            //探索左右子树
            res += helper(root.left, sum, pathSum) + helper(root.right, sum, pathSum);
            //回溯过程需要将map的数量-1
            map.put(pathSum, map.get(pathSum) - 1);
            return res;
        }
    }


    static class _2nd {
        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            return helper(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
        }

        public int helper(TreeNode root, long targetSum) {
            if (root == null) {
                return 0;
            }
            int res = 0;
            if (targetSum == root.val) {
                res += 1;
            }
            res += helper(root.left, targetSum - root.val);
            res += helper(root.right, targetSum - root.val);
            return res;
        }
    }
}
