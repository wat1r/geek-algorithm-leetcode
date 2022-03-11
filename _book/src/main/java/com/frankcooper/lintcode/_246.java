package com.frankcooper.lintcode;

import com.frankcooper.struct.TreeNode;

import java.util.*;

import org.junit.Assert;

public class _246 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<List<Integer>> res = new ArrayList<>();
        int target;

        public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
            // write your code here
            this.target = target;
            dfs(root, new ArrayList<>());
            return res;
        }


        private void dfs(TreeNode root, List<Integer> sub) {
            if (root == null) return;
            sub.add(root.val);
            int sum = 0;
            for (int i = sub.size() - 1; i >= 0; --i) {
                sum += sub.get(i);
                if (sum == target) res.add(new ArrayList<>(sub.subList(i, sub.size())));
            }
            dfs(root.left, sub);
            dfs(root.right, sub);
            sub.remove(sub.size() - 1);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        List<List<Integer>> res = new ArrayList<>();
        int target;

        public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
            this.target = target;
            dfs(root, 0, new ArrayList<>(), false);
            return res;
        }

        /**
         *
         * @param root
         * @param sum
         * @param path
         * @param needRestart 是否需要断开path，从当前的节点的left 或 right节点开始
         */
        private void dfs(TreeNode root, int sum, List<Integer> path, boolean needRestart) {
            //0.出口
            if (root == null) return;
            //1.加入到路径
            sum += root.val;
            path.add(root.val);
            //2.找到目标
            if (sum == target) res.add(new ArrayList<>(path));
            //3.从root节点开始
            dfs(root.left, sum, path, true);
            dfs(root.right, sum, path, true);
            //4.从当前节点的left 或 right节点开始
            if (!needRestart) {
                dfs(root.left,0,new ArrayList<>(),false);
                dfs(root.right,0,new ArrayList<>(),false);
            }
            //5.移除当前节点
            path.remove(path.size()-1);
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
