package com.frankcooper.bank;

import com.frankcooper.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/7/9
 * @Author Frank Cooper
 * @Description
 */
public class _437 {
    static _437 handler = new _437();

    public static void main(String[] args) {
        handler.testOne();


    }


    public void testOne() {
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode_3 = new TreeNode(-3);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode11= new TreeNode(11);
        TreeNode treeNode3_= new TreeNode(3);
        TreeNode treeNode_2= new TreeNode(-2);
        TreeNode treeNode1= new TreeNode(1);

        treeNode10.left = treeNode5;
        treeNode10.right = treeNode_3;
        treeNode5.left= treeNode3;
        treeNode3.right = treeNode2;
        treeNode_3.right = treeNode11;
        treeNode3.left = treeNode3_;
        treeNode3.right = treeNode_2;
        treeNode2.right = treeNode1;
        treeNode11.left =null;
        treeNode11.right =null;
        treeNode3_.left =null;
        treeNode3_.right =null;
        treeNode_2.left =null;
        treeNode_2.right =null;
        treeNode1.left =null;
        treeNode1.right =null;
        handler.pathSum(treeNode10,8);
    }


//    Map<Integer, Integer> map = new HashMap<>();
//
//    public int pathSum(TreeNode root, int sum) {
//        map.put(0, 1);
//        return helper(root, sum, 0);
//    }
//
//    int helper(TreeNode root, int sum, int pathSum) {
//        int res = 0;
//        if (root == null) return 0;
//        pathSum += root.val;
//        res += map.getOrDefault(pathSum - sum, 0);
//        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
//        res = helper(root.left, sum, pathSum) + helper(root.right, sum, pathSum) + res;
//        map.put(pathSum, map.get(pathSum) - 1);
//        return res;
//    }


    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        int res = 0;
        if (root.val == sum) res++;
        res += helper(root.left, sum - root.val);
        res += helper(root.right, sum - root.val);
        return res;
    }
}
