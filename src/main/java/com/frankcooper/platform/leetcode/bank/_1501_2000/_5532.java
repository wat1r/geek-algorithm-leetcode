package com.frankcooper.platform.leetcode.bank._1501_2000;

import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by FrankCooper
 * Date 2020/10/4 10:47
 * Description
 */
public class _5532 {

    static _5532 handler = new _5532();

    public static void main(String[] args) {
//        handler.testOne();
//        handler.testTwo();
        handler.testThree();
    }


    private void testOne() {
//        1,10,4,3,null,7,9,12,8,6,null,null,2
        TreeNode _1 = new TreeNode(1);
        TreeNode _10 = new TreeNode(10);
        TreeNode _4 = new TreeNode(4);
        _1.left = _10;
        _1.right = _4;
        TreeNode _3 = new TreeNode(3);
        _10.left = _3;
        _10.right = null;
        TreeNode _7 = new TreeNode(7);
        TreeNode _9 = new TreeNode(9);
        _4.left = _7;
        _4.right = _9;
        TreeNode _12 = new TreeNode(12);
        TreeNode _8 = new TreeNode(8);
        _3.left = _12;
        _3.right = _8;
        TreeNode _6 = new TreeNode(6);
        _7.left = _6;
        _7.right = null;
        TreeNode _2 = new TreeNode(2);
        _9.left = null;
        _9.right = _2;
        isEvenOddTree(_1);
    }


    private void testTwo() {
        TreeNode _5 = new TreeNode(5);
        TreeNode _4 = new TreeNode(4);
        TreeNode _2 = new TreeNode(2);
        _5.left = _4;
        _5.right = _2;
        TreeNode _3_1 = new TreeNode(3);
        TreeNode _3_2 = new TreeNode(3);
        TreeNode _7 = new TreeNode(7);
        _4.left = _3_1;
        _4.right = _3_2;
        _2.left = _7;
        _2.right = null;
        isEvenOddTree(_5);
    }


    private void testThree() {
//        [2,12,8,5,9,null,null,18,16]
        TreeNode _2 = new TreeNode(2);
        TreeNode _12 = new TreeNode(12);
        TreeNode _8 = new TreeNode(8);
        _2.left = _12;
        _2.right = _8;
        TreeNode _5 = new TreeNode(5);
        TreeNode _9 = new TreeNode(9);
        _12.left = _5;
        _12.right = _9;
        TreeNode _18 = new TreeNode(18);
        TreeNode _16 = new TreeNode(16);
        _5.left = _18;
        _5.right = _16;
        handler.isEvenOddTree(_2);
    }


    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.add(root);
        if (root.val % 2 == 0) return false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Stack<Integer> stack = new Stack<>();
            for (int k = 0; k < size; k++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                    stack.push(curr.left.val);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                    stack.push(curr.right.val);
                }
            }
            level++;
//            System.out.println(JSON.toJSONString(stack));
            if (level % 2 == 0) {
                if (!check(stack, false)) return false;
            } else {
                if (!check(stack, true)) return false;
            }
        }
        return true;
    }

    /**
     * @param stack
     * @param isOdd 奇数
     * @return
     */
    private boolean check(Stack<Integer> stack, boolean isOdd) {
        int tmp = 0;
        boolean only = false;
        if (stack.size() == 1) {
            tmp = stack.peek();
            only = true;
        }
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!stack.isEmpty()) {
                if (isOdd) {
                    if (curr >= stack.peek()) return false;
                    if (curr % 2 == 1 || stack.peek() % 2 == 1) return false;
                } else {
                    if (curr <= stack.peek()) return false;
                    if (curr % 2 == 0 || stack.peek() % 2 == 0) return false;
                }
            }
        }
        return !only || (isOdd ? tmp % 2 == 0 : tmp % 2 == 1);
    }


}
