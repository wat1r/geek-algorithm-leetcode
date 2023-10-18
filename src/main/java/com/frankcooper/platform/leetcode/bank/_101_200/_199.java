package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _199 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        //BFS
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> resList = new ArrayList<Integer>();
            if (root == null) return resList;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curNode = queue.peek();
                    queue.poll();
                    if (i == (size - 1)) resList.add(curNode.val);
                    if (curNode.left != null) queue.add(curNode.left);
                    if (curNode.right != null) queue.add(curNode.right);
                }
            }
            return resList;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,null,5,null,4]");
            handler.rightSideView(root);
        }


        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> resList = new ArrayList<>();
            if (root == null) return resList;
            dfs(root, resList, 0);
            return resList;
        }


        public void dfs(TreeNode root, List<Integer> resList, int depth) {
            if (root == null) return;
            if (depth >= resList.size()) resList.add(root.val);
            if (root.right != null) dfs(root.right, resList, depth + 1);
            if (root.left != null) dfs(root.left, resList, depth + 1);


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
