package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _376 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<List<Integer>> res = new ArrayList<>();
        int target;

        public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
            this.target = target;
            dfs(root, new ArrayList<>(), 0);
            return res;
        }


        private void dfs(TreeNode root, List<Integer> list, int sum) {
            if (root == null) return;
            sum += root.val;
            list.add(root.val);
            //当前节点是叶子节点
            if (root.left == null && root.right == null) {
                if (sum == target) {
                    res.add(new ArrayList<>(list));
                }
                sum -= list.get(list.size() - 1);
                list.remove(list.size() - 1);
                return;
            }
            //非叶子节点
            dfs(root.left, list, sum);
            dfs(root.right, list, sum);
            list.remove(list.size() - 1);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
            dfs(root, new ArrayList<>(), target);
            return res;
        }

        private void dfs(TreeNode root, List<Integer> list, int cur) {
            if (root == null) return;
            //当前节点是叶子节点
            list.add(root.val);
            if (root.left == null && root.right == null) {
                if (cur == root.val) {
                    res.add(new ArrayList<>(list));
                }
                list.remove(list.size() - 1);
                return;
            }
            dfs(root.left, list, cur - root.val);
            dfs(root.right, list, cur - root.val);
            list.remove(list.size() - 1);
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
