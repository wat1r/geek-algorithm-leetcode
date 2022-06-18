package com.frankcooper.platform.leetcode.swordoffer;

import com.frankcooper.struct.TreeNode;
import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.Map;

public class Sword_07 {

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }


    public TreeNode dfs(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int mid = map.get(preorder[preStart]);
        root.left = dfs(preorder, preStart + 1, preStart + (mid - inStart),
                inorder, inStart, mid - 1);
        root.right = dfs(preorder, preStart + (mid - inStart) + 1, preEnd,
                inorder, mid + 1, inEnd);
        return root;
    }

}
