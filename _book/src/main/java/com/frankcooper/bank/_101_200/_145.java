package com.frankcooper.bank._101_200;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.*;

import org.junit.Assert;

public class _145 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[5,1,4,null,null,3,6]");
            root = TreeNodeIOUtils.transform("[1,null,2,3]");
            handler.postOrder(root);

        }


        public void postOrder(TreeNode root) {

            LinkedList<TreeNode> stack = new LinkedList<>();
            TreeNode current = root;
            TreeNode rightNode = null;

            while (null != current || !stack.isEmpty()) {
                while (null != current) {
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                while (current.right == null || current.right == rightNode) {
                    System.out.println(current.val);
                    rightNode = current;
                    if (stack.isEmpty()) {
                        return;
                    }
                    current = stack.poll();
                }
                stack.push(current);
                current = current.right;
            }

        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            LinkedList<TreeNode> stack = new LinkedList<>();
            TreeNode current = root;
            TreeNode rightNode = null;
            flag:
            while (null != current || !stack.isEmpty()) {
                while (null != current) {
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                while (current.right == null || current.right == rightNode) {
//                    System.out.println(current.val);
                    res.add(current.val);
                    rightNode = current;
                    if (stack.isEmpty()) {
                        break flag;
                    }
                    current = stack.poll();
                }
                stack.push(current);
                current = current.right;
            }
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stk = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stk.isEmpty()) {
                if (cur != null) {
                    res.add(0, cur.val);
                    stk.push(cur);
                    cur = cur.right;
                } else {
                    TreeNode tmp = stk.pop();
                    cur = tmp.left;
                }
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
