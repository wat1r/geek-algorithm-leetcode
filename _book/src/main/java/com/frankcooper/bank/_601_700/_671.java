package com.frankcooper.bank._601_700;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _671 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int findSecondMinimumValue(TreeNode root) {
            return dfs(root, root.val);
        }

        //返回以root为根节点的子树的 第二小的值
        private int dfs(TreeNode root, int val) {
            if (root == null) return -1;//root节点为null 返回-1
            if (root.val > val) return root.val;//当前节点大于根节点的值，返回当前值
            //下面是当前节点 == 根节点的值 ，递归找到左右孩子为根的子树 的第二小的值
            int l = dfs(root.left, val);
            int r = dfs(root.right, val);
            if (l > val && r > val) return Math.min(l, r);//两个都是合法值，取小的
            return Math.max(l, r);

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //返回以root节点为根节点的子树的第二小点的值
        public int findSecondMinimumValue(TreeNode root) {
            //当前节点为空或者当前节点为叶子节点 说明以root为根节点的子树 没有第二小的值 返回-1
            if (root == null || (root.left == null && root.right == null)) return -1;
            int l = root.left.val;
            int r = root.right.val;
            if (root.val == root.left.val) l = findSecondMinimumValue(root.left);//找root左孩子的子树的第二小的值
            if (root.val == root.right.val) r = findSecondMinimumValue(root.right);//找root右孩子的子树的第二小的值
            if (l != -1 && r != -1) return Math.min(l, r);//左右孩子都有值 返回最小的
            if (l != -1) return l;//左孩子有值 但是右孩子没有 返回左孩子
            else return r;//左孩子没有值，返回右孩子的值 或者r =-1
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
