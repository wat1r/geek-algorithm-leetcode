package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _04_06 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //中序遍历
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            boolean f = false;
            Deque<TreeNode> stk = new ArrayDeque<>();
            while (!stk.isEmpty() || root != null) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                if (f) return root;
                if (root.val == p.val) f = true;
                root = root.right;
            }
            return null;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode root = TreeNodeIOUtils.transform("[5,3,7,2,4,6,8,1,null]");
            TreeNode p = root.right;//7
            p = root.left.left;//2
            handler.inorderSuccessor(root, p);
        }

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (root == null || p == null) {
                return null;
            }
            //p节点的值比当前节点的值大或者相等，说明p节点的后继不可能在当前节点的左子树上（因为中序遍历的左子树的值比当前节点的值小）
            //要设法去当前节点的右子树上找
            if (root.val <= p.val) {
                return inorderSuccessor(root.right, p);
            } else {
                //p节点的值比当前节点小了
                //case1 left为null,说明在左子树上没找到，此时返回当前节点root
                //case2 left不为null，说明找到了返回left
                TreeNode left = inorderSuccessor(root.left, p);
                return left == null ? root : left;
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            Deque<TreeNode> stk = new ArrayDeque<>();
            TreeNode prev = null, cur = root;
            while (!stk.isEmpty() || cur != null) {
                while (cur != null) {
                    stk.push(cur);
                    cur = cur.left;
                }
                cur = stk.pop();
                if (prev == p) return cur;
                prev = cur;
                cur = cur.right;
            }
            return null;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            TreeNode root = TreeNodeIOUtils.transform("[5,3,7,2,4,6,8,1,null]");
            TreeNode p = root.right;//7
            p = root.left.left;//2
            handler.inorderSuccessor(root, p);
        }


        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode res = null;
            while (root != null) {
                //当前节点的值比p节点的值大，在当前节点的左子树上找，当前节点有可能是p节点的下一个节点，保存该节点
                if (root.val > p.val) {
                    res = root;
                    root = root.left;
                } else {//当前节点的值比p节点的值小或者相等（说明当前节点不是p节点的后继），在当前节点的右子树上继续找
                    root = root.right;
                }
            }
            return res;
        }
    }
}
