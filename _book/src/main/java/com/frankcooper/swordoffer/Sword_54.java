package com.frankcooper.platform.leetcode.swordoffer;

import com.frankcooper.struct.TreeNode;

import java.util.*;

public class Sword_54 {
    static class _1st {
        public int kthLargest(TreeNode root, int k) {
            List<Integer> list = new ArrayList<>();
            Stack<TreeNode> stk = new Stack<>();
            TreeNode curr = root;
            while (!stk.isEmpty() || curr != null) {
                while (curr != null) {
                    stk.push(curr);
                    curr = curr.right;
                }
                curr = stk.pop();
                if (curr != null) {
                    list.add(curr.val);
                    stk.push(curr.left);
                }
            }
            return list.get(k - 1);
        }
    }



}
