package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _270 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            TreeNode root = new TreeNode(4);
            TreeNode r1 = new TreeNode(2);
            TreeNode r2 = new TreeNode(5);
            root.left = r1;
            root.right = r2;
            r1.left = new TreeNode(1);
            r1.right = new TreeNode(3);
            double target = 3.714286;
            handler.closestValue(root, target);


        }


        public int closestValue(TreeNode root, double target) {
            int closest = root.val;
            while (root != null) {
                int val = root.val;
                //比较当前节点与目标值的差
                if (Math.abs(val - target) < Math.abs(closest - target)) {
                    closest = val;
                }
                //二分法向下遍历
                root = target < root.val ? root.left : root.right;
            }
            return closest;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
