package com.frankcooper.binary_tree;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.ConnectTreeNode;
import com.frankcooper.struct.ListNode;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/9/27 14:20
 * @description:
 */
public class BinaryTree {

    static class _1st_1 {
        public static void main(String[] args) {
            _1st_1 handler = new _1st_1();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            Assert.assertEquals(3, handler.maxDepth(root));
        }


        public int maxDepth(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    static class _1st_2 {
        public static void main(String[] args) {
            _1st_2 handler = new _1st_2();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            Assert.assertEquals(3, handler.maxDepth(root));
        }

        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int depth = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            q.offer(null);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur == null) depth++;
                if (cur != null) {
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                } else if (!q.isEmpty()) q.offer(null);
            }
            return depth;
        }
    }

    static class _1st_3 {
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curr = queue.poll();
                    if (curr.left != null) queue.offer(curr.left);
                    if (curr.right != null) queue.offer(curr.right);
                }
                level++;
            }
            return level;
        }
    }


    static class _1st_4 {

        public static void main(String[] args) {
            _1st_4 handler = new _1st_4();
            TreeNode r1 = TreeNodeIOUtils.transform("[26,10,3,4,6,null,3,null,30,null,null,null,null]");
            // TREE 1
//              26
//             /   \
//            10     3
//           /    \     \
//          4      6      3
//           \
//            30

//            // TREE 2
//           10
//         /    \
//         4      6
//          \
//          30
            TreeNode r2 = TreeNodeIOUtils.transform("[10,4,6,null,30,null,null]");
            if (handler.isSubTree(r1, r2))
                System.out.println("Tree 2 is subtree of Tree 1 ");
            else
                System.out.println("Tree 2 is not a subtree of Tree 1");
//
        }

        //判断S是否是T的一棵子树
        public boolean isSubTree(TreeNode T, TreeNode S) {
            if (S == null) return true;//S已经遍历完成
            if (T == null) return false;//T已经遍历完成，但S还有没有走完的节点
            if (areIdentical(T, S)) return true;//是否当前的两棵树是完全一样的
            //T的左子树为根节点和S 对比  || T的右子树为根节点和S 对比
            return isSubTree(T.left, S) || isSubTree(T.right, S);
        }

        //判断r1和r2为根节点的两棵树，是不是完全相同的
        public boolean areIdentical(TreeNode r1, TreeNode r2) {
            if (r1 == null && r2 == null) return true;
            if (r1 == null || r2 == null) return false;
            //判断左右子树是否相同
            return r1.val == r2.val && areIdentical(r1.left, r2.left) && areIdentical(r1.right, r2.right);
        }
    }

    static class _1st_5 {
        public static void main(String[] args) {
            _1st_5 handler = new _1st_5();
            //Constructed binary tree is
//             10
//            /  \
//          8     2
//         /
//        3
//            TreeNode

//            // Let us check the values of nextRight pointers
//            System.out.println("Following are populated nextRight pointers in "
//                    + "the tree"
//                    + "(-1 is printed if there is no nextRight)");
//            int a = tree.root.nextRight != null ? tree.root.nextRight.data : -1;
//            System.out.println("nextRight of " + tree.root.data + " is "
//                    + a);
//            int b = tree.root.left.nextRight != null ? tree.root.left.nextRight.data : -1;
//            System.out.println("nextRight of " + tree.root.left.data + " is "
//                    + b);
//            int c = tree.root.right.nextRight != null ? tree.root.right.nextRight.data : -1;
//            System.out.println("nextRight of " + tree.root.right.data + " is "
//                    + c);
//            int d = tree.root.left.left.nextRight != null ? tree.root.left.left.nextRight.data : -1;
//            System.out.println("nextRight of " + tree.root.left.left.data + " is "
//                    + d);

            ConnectTreeNode root = new ConnectTreeNode(10);
            root.left = new ConnectTreeNode(8);
            root.right = new ConnectTreeNode(2);
            root.left.left = new ConnectTreeNode(3);

            // Populates nextRight pointer in all nodes
            handler.connectII(root);

            // Let us check the values
            // of nextRight pointers
            System.out.print("Following are populated nextRight pointers in the tree"
                    + " (-1 is printed if there is no nextRight)\n");
            System.out.print(
                    "nextRight of " + root.val + " is " + (root.nextRight != null ? root.nextRight.val : -1) + "\n");
            System.out.print("nextRight of " + root.left.val + " is "
                    + (root.left.nextRight != null ? root.left.nextRight.val : -1) + "\n");
            System.out.print("nextRight of " + root.right.val + " is "
                    + (root.right.nextRight != null ? root.right.nextRight.val : -1) + "\n");
            System.out.print("nextRight of " + root.left.left.val + " is "
                    + (root.left.left.nextRight != null ? root.left.left.nextRight.val : -1) + "\n");

        }

        public void connect(ConnectTreeNode root) {
            Queue<ConnectTreeNode> q = new LinkedList<>();
            q.offer(root);
            ConnectTreeNode cur = null;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    ConnectTreeNode prev = cur;
                    cur = q.poll();
                    if (i > 0) prev.nextRight = cur;
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                cur.nextRight = null;
            }
        }


        public void connectII(ConnectTreeNode root) {
            //出口条件
            if (root == null) return;
            //当前节点的左子节点，执行当前节点的右子节点
            if (root.left != null) {
                root.left.nextRight = root.right;
            }
            //当前节点的右子节点指向当前节点的下一个节点的左子节点或者null
            if (root.right != null) {
                root.right.nextRight = (root.nextRight != null ? root.nextRight.left : null);
            }
            //处理当前节点的左右子节点
            connectII(root.left);
            connectII(root.right);
        }
    }

    static class _1st_6 {
        public static void main(String[] args) {
            _1st_6 handler = new _1st_6();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,8,null]");
            int K = 2;
            handler.printKDistant(root, K);

        }

        public void printKDistant(TreeNode root, int K) {
            if (root == null || K < 0) return;
            if (K == 0) {
                System.out.printf("%d ", root.val);
                return;
            }
            printKDistant(root.left, K - 1);
            printKDistant(root.right, K - 1);
        }
    }

    static class _1st_7 {
        public static void main(String[] args) {

        }

        public int minValue(TreeNode node) {
            TreeNode cur = node;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.val;
        }
    }


    static class _1st_8 {
        public static void main(String[] args) {
            _1st_8 handler = new _1st_8();
            TreeNode root = TreeNodeIOUtils.transform("[4,2,5,1,3,null,null]");
            if (handler.isBST_III(root)) {
                System.out.println("is BST");
            } else {
                System.out.println("is not BST");
            }
        }

        public boolean isBST(TreeNode node) {
            if (node == null) return true;//为空
            //左节点比当前节点值大，右节点比当前节点值小，不是BST，返回false
            if (node.left != null && node.left.val > node.val) return false;
            if (node.right != null && node.right.val < node.val) return false;
            //如果node节点的左右子树都不是BST，返回false，不是BST
            if (!isBST(node.left) || !isBST(node.right)) return false;
            return true;
        }


        public boolean isBST_II(TreeNode node) {
            if (node == null) return true;//为空
            //左节点下的最大值比当前节点值大，右节点下的最小值比当前节点值小，不是BST，返回false
            if (node.left != null && maxValue(node.left) > node.val) return false;
            if (node.right != null && minValue(node.right) < node.val) return false;
            //如果node节点的左右子树都不是BST，返回false，不是BST
            if (!isBST_II(node.left) || !isBST_II(node.right)) return false;
            return true;
        }

        private int minValue(TreeNode root) {
            return 0;
        }

        private int maxValue(TreeNode root) {
            return 0;
        }

        public boolean isBST_III(TreeNode node) {
            return isBSTUtil(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean isBSTUtil(TreeNode node, int minn, int maxx) {
            if (node == null) return true;
            if (node.val < minn || node.val > maxx) return false;
            return isBSTUtil(node.left, minn, node.val - 1)
                    && isBSTUtil(node.right, node.val + 1, maxx);

        }

        TreeNode prev = null;

        public boolean isBST_IV(TreeNode node) {
            if (node == null) return true;
            if (!isBST_IV(node.left)) return false;
            if (prev != null && node.val <= prev.val) return false;
            prev = node;
            if (!isBST_IV(node.right)) return false;
            return true;
        }
    }

    static class _1st_9 {
        public static void main(String[] args) {
            _1st_9 handler = new _1st_9();
            TreeNode root1 = TreeNodeIOUtils.transform("[10,8,20,25,null,15,5,3,7]");
            handler.printInOrder(root1);
            System.out.println();
//            handler.correctBST(root1);
            handler.printInOrder(root1);
            System.out.println();
            TreeNode root2 = TreeNodeIOUtils.transform("[10,7,20,5,null,15,25,3,8]");
            handler.printInOrder(root2);
            System.out.println();
            handler.correctBST(root2);
            handler.printInOrder(root2);
            System.out.println();
        }

        TreeNode first, middle, last;
        TreeNode prev;

        public void correctBST(TreeNode root) {
            correctBSTUtil(root);
            if (first != null && last != null) {//case1
                int t = first.val;
                first.val = last.val;
                last.val = t;
            } else if (first != null && middle != null) {//case2 last is null
                int t = first.val;
                first.val = middle.val;
                middle.val = t;
            }

        }

        public void correctBSTUtil(TreeNode root) {
            if (root == null) return;
            correctBSTUtil(root.left);
            if (prev != null && root.val < prev.val) {
                if (first == null) {
                    first = prev;
                    middle = root;
                } else {
                    last = root;
                }
            }
            prev = root;
            correctBSTUtil(root.right);
        }

        public void printInOrder(TreeNode root) {
            if (root == null) return;
            printInOrder(root.left);
            System.out.printf("%d ", root.val);
            printInOrder(root.right);
        }


    }

    static class _2nd_1 {
        public static void main(String[] args) {
            _2nd_1 handler = new _2nd_1();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            handler.printLevelOrder(root);
        }

        public void printLevelOrder(TreeNode root) {
            int height = height(root);
            for (int i = 1; i <= height; i++) {
                printCurrentLevel(root, i);
            }
        }

        private int height(TreeNode root) {
            if (root == null)
                return 0;
            else {
                /* compute  height of each subtree */
                int lheight = height(root.left);
                int rheight = height(root.right);
                /* use the larger one */
                if (lheight > rheight)
                    return (lheight + 1);
                else return (rheight + 1);
            }
        }


        private void printCurrentLevel(TreeNode root, int level) {
            if (root == null)
                return;
            if (level == 1)
                System.out.print(root.val + " ");
            else if (level > 1) {
                printCurrentLevel(root.left, level - 1);
                printCurrentLevel(root.right, level - 1);
            }
        }
    }

    static class _2nd_2 {
        public static void main(String[] args) {
            _2nd_2 handler = new _2nd_2();
            TreeNode node1 = TreeNodeIOUtils.transform("[100,50,300,20,70]");
            TreeNode node2 = TreeNodeIOUtils.transform("[80,40,200]");
            TreeNode resNode = handler.mergeTrees(node1, node2);
            handler.inorderUtil(resNode);

        }

        public List<Integer> storeInorder(TreeNode node) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = storeInorderUtil(node, list1);
            return list2;
        }

        //中序遍历存储BST的结果集
        public List<Integer> storeInorderUtil(TreeNode node, List<Integer> list) {
            if (node == null) return list;
            storeInorderUtil(node.left, list);
            list.add(node.val);
            storeInorderUtil(node.right, list);
            return list;
        }


        //合并list1和list2
        public List<Integer> merge(List<Integer> list1, List<Integer> list2, int m, int n) {
            List<Integer> list3 = new ArrayList<>();
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (list1.get(i) < list2.get(j)) {
                    list3.add(list1.get(i));
                    i++;
                } else {
                    list3.add(list2.get(j));
                    j++;
                }
            }
            while (i < m) {
                list3.add(list1.get(i));
                i++;
            }
            while (j < n) {
                list3.add(list2.get(j));
                j++;
            }
            return list3;
        }

        //将list的start到end的元素转换成TreeNode
        public TreeNode arrayList2BST(List<Integer> list, int start, int end) {
            if (start > end) return null;//越界，返回
            int mid = (start + end) / 2;//中间点
            TreeNode node = new TreeNode(list.get(mid));//新建节点
            //构建左右子树节点
            node.left = arrayList2BST(list, start, mid - 1);
            node.right = arrayList2BST(list, mid + 1, end);
            return node;
        }

        //合并node1和node2的两棵BST
        public TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
            List<Integer> list1 = storeInorder(node1);
            List<Integer> list2 = storeInorder(node2);
            int m = list1.size(), n = list2.size();
            List<Integer> list3 = merge(list1, list2, m, n);
            TreeNode resNode = arrayList2BST(list3, 0, list3.size() - 1);
            return resNode;
        }


        public void inorderUtil(TreeNode node) {
            if (node == null)
                return;
            inorderUtil(node.left);
            System.out.print(node.val + " ");
            inorderUtil(node.right);
        }

    }


    static class _2nd_0 {
        public static void main(String[] args) {

        }

    }

}
