package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.ParentTreeNode;

public class _472 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
            if (root == null) return res;
            List<ParentTreeNode> nodeList = new ArrayList<>();
            inOrder(root, nodeList);
            for (ParentTreeNode node : nodeList) {
                Set<ParentTreeNode> set = new HashSet<>();
                dfs(node,set,new ArrayList<>(),target);
            }
            return res;
        }


        private void inOrder(ParentTreeNode root, List<ParentTreeNode> nodeList) {
            if (root == null) return;
            inOrder(root.left, nodeList);
            nodeList.add(root);
            inOrder(root.right, nodeList);
        }

        private void dfs(ParentTreeNode root, Set<ParentTreeNode> set, List<Integer> path, int target) {
            if (root == null || set.contains(root)) return;
            set.add(root);
            path.add(root.key);
            target -= root.key;
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            dfs(root.left, set, path, target);
            dfs(root.right, set, path, target);
            dfs(root.parent, set, path, target);
            set.remove(root);
            path.remove(path.size() - 1);
            target += root.key;
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
