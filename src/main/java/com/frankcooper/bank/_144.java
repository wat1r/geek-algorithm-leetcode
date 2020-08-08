package com.frankcooper.bank;

import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2020/8/8 21:20
 * Description
 */
public class _144 {


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                result.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    System.out.print(curr.val + " ");
                    result.add(curr.val);
                    curr = curr.left;
                } else if (predecessor.right == curr) {
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
        return result;
    }






    public void preOrderByMorris(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    System.out.print(curr.val + " ");
                    curr = curr.left;
                } else if (predecessor.right == curr) {
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    private TreeNode getPredecessor(TreeNode curr) {
        TreeNode predecessor = curr;
        if (curr.left != null) {
            predecessor = curr.left;
            while (predecessor.right != null && predecessor.right != curr) {
                predecessor = predecessor.right;
            }
        }
        return predecessor;
    }


}
