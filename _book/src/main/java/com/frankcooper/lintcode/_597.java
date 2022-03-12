package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _597 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }



        /**
         * 步骤如下：
         *
         * 1.对于一个根结点root，递归的遍历左右子树，返回左右子树的权值和与节点数目，从而得到根结点root以及所有子孙节点作为子树的权值sum和与节点数目num。
         * sum = left_sum + right_sum + root.val
         * num = left_num + right_num + 1
         * 2.比较该子树的平均值和已知最大平均值子树的平均值。若最大平均值子树不存在，或者该子树的平均值>已知最大平均值子树的平均值，那么更新最大平均值子树。
         * 3.最后返回最大平均值子树。
         * 为了避免出现精度问题，我们可以把
         * root.sum / root.num > ans.sum / ans.num 的判断移项成乘法：
         * root.sum *  ans.num > ans.sum * root.num
         * @param root
         * @return
         */


        TreeNode maxTreeNode = null;
        Result maxResult = null;

        public TreeNode findSubtree2(TreeNode root) {
            dfs(root);
            return maxTreeNode;
        }



        private Result dfs(TreeNode root) {
            if (root == null) return new Result(0, 0);

            Result leftResult = dfs(root.left);
            Result rightResult = dfs(root.right);
            Result rootResult = new Result(leftResult.sum + rightResult.sum + root.val,
                    leftResult.num + rightResult.num + 1);
            if (maxResult == null || rootResult.sum * maxResult.num > rootResult.num * maxResult.sum) {
                maxTreeNode = root;
                maxResult = rootResult;
            }
            return rootResult;
        }


        class Result {
            private int sum;
            private int num;

            public Result(int sum, int num) {
                this.sum = sum;
                this.num = num;
            }
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
