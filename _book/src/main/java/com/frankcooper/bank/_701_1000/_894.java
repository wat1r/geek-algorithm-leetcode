package com.frankcooper.bank._901_1000;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _894 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        //返回所有满足条件的root的列表
        public List<TreeNode> allPossibleFBT(int n) {
            List<TreeNode> res = new ArrayList<>();
            if (n % 2 == 0) return res;//偶数个节点，无法做成满二叉树
            if (n == 1) {//一个节点的时候返回
                res.add(new TreeNode(0));
                return res;
            }
            for (int i = 1; i <= n - 2; i += 2) {//左右子树均为偶数个 +=2
                int left_num = i, right_num = n - i - 1;
                List<TreeNode> leftTree = allPossibleFBT(left_num);
                List<TreeNode> rightTree = allPossibleFBT(right_num);
                for (TreeNode left : leftTree) {
                    for (TreeNode right : rightTree) {
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
            return res;
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
