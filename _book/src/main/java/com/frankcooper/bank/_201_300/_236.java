package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.ParentTreeNode;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class
_236 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[3,5,1,6,2,0,8,null,null,7,4]");
            TreeNode p = root.left;//5
            TreeNode q = root.right;//1
//            TreeNode ancestor = handler.lowestCommonAncestor(root, p, q);
//            System.out.printf("%d", ancestor.val);
        }


        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root;
            return left != null ? left : right;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * 作者：jyd
         * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         *
         * @param root
         * @param p
         * @param q
         * @return
         */

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root;
            return left == null ? right : left;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            TreeNode root = TreeNodeIOUtils.transform("[3,5,1,6,2,0,8,null,null,7,4]");
            TreeNode p = root.left;//5
            TreeNode q = root.right;//1
//            TreeNode ancestor = handler.lowestCommonAncestor(root, p, q);
//            System.out.printf("%d\n", ancestor.val);
            root = TreeNodeIOUtils.transform("[3,5,1,6,null,null,null,7,2,null,null,null,4]");
            p = root.left.left.left;
            q = root.left.left.right.right;
            TreeNode ancestor = handler.lowestCommonAncestor(root, p, q);
            System.out.printf("%d\n", ancestor.val);
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //终止条件
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root;//case 4
            if (left != null) return left;//case 2
            if (right != null) return right;//case 3
            return null;//case 1
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            TreeNode root = TreeNodeIOUtils.transform("[3,5,1,6,null,null,null,7,2,null,null,null,4]");
            TreeNode p = root.left.left.left;
            TreeNode q = root.left.left.right.right;
            TreeNode ancestor = handler.lowestCommonAncestor(root, p, q);
            System.out.printf("%d\n", ancestor.val);
        }


        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //存当前节点的父节点，k:当前节点，v:当前节点的父节点，root节点的父节点为null
            Map<TreeNode, TreeNode> parent = new HashMap<>();
            parent.put(root, null);
            Deque<TreeNode> stk = new ArrayDeque<>();
            stk.push(root);
            //step1:率先找到p或者q，先左节点，后右节点，然后出栈的时候，选右节点优先
            while (!parent.containsKey(p) || !parent.containsKey(q)) {
                TreeNode cur = stk.pop();
                if (cur.left != null) {
                    parent.put(cur.left, cur);
                    stk.push(cur.left);
                }
                if (cur.right != null) {
                    parent.put(cur.right, cur);
                    stk.push(cur.right);
                }
            }
            //step2：set收集的是p节点的所有祖先节点，包括p节点的直系父节点
            Set<TreeNode> set = new HashSet<>();
            while (p != null) {
                set.add(p);
                p = parent.get(p);
            }
            //step3:找q的父节点，第一个出现在set集合中的即是LCA
            while (!set.contains(q)) {
                q = parent.get(q);
            }
            return q;
        }
    }

    static class _5th {
        public static void main(String[] args) {

        }



        public ParentTreeNode insert(ParentTreeNode node, int key) {
            /* If the tree is empty, return a new node */
            if (node == null) return new ParentTreeNode(key);
            /* Otherwise, recur down the tree */
            if (key < node.key) {
                node.left = insert(node.left, key);
                node.left.parent = node;
            } else if (key > node.key) {
                node.right = insert(node.right, key);
                node.right.parent = node;
            }
            /* return the (unchanged) node pointer */
            return node;
        }


        public ParentTreeNode lca(ParentTreeNode node1, ParentTreeNode node2) {
            Map<ParentTreeNode, Boolean> ancestors = new HashMap<>();
            while (node1 != null) {
                ancestors.put(node1, true);
                node1 = node1.parent;
            }
            while (node2 != null) {
                if (ancestors.containsKey(node2)) {
                    return node2;
                }
                node2 = node2.parent;
            }
            return null;
        }

    }
}
