package com.frankcooper.bank._101_200;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/7/9
 * @Author Frank Cooper
 * @Description
 */
public class _113 {
    static _113 handler = new _113();

    public static void main(String[] args) {
        handler.testOne();
    }


    public void testOne() {
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode13 = new TreeNode(13);
        TreeNode treeNode4_ = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode5_ = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1);

        treeNode5.left = treeNode4;
        treeNode5.right = treeNode8;
        treeNode4.left = treeNode11;
        treeNode11.left = treeNode7;
        treeNode11.right = treeNode2;
        treeNode8.left = treeNode13;
        treeNode8.right = treeNode4_;
        treeNode4_.left = treeNode5_;
        treeNode4_.right = treeNode1;

        handler.pathSum(treeNode5, 22);

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) return resultList;
        dfs(root, sum, resultList, new ArrayList<>());
        return resultList;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> resultList, List<Integer> subList) {
        if (root == null) return;
        subList.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) resultList.add(new ArrayList<>(subList));
        dfs(root.left, sum - root.val, resultList, subList);
        dfs(root.right, sum - root.val, resultList, subList);
        subList.remove(subList.size() - 1);
    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
            int targetSum = 22;
            handler.pathSum(root,targetSum);
        }


        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            if (root == null) return res;
            dfs(root, sum, 0, new ArrayList<>());
            return res;
        }

        private void dfs(TreeNode root, int sum, int total, List<Integer> sub) {
            if (root == null) return;
            System.out.printf("%d->%d\n", root.val, total);
            sub.add(root.val);
            total += root.val;
            if (root.left == null && root.right == null) {
                if (sum == total) {
                    res.add(new ArrayList<>(sub));
                }
                //这一步是为了移除这个叶子节点的值，不管这个叶子节点是否满足条件
                sub.remove(sub.size() - 1);
                return;
            }
            dfs(root.left, sum, total, sub);
            dfs(root.right, sum, total, sub);
            sub.remove(sub.size() - 1);
        }

    }

}
