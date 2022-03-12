package com.frankcooper.nowcoder.swordoffer;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;
public class JZ34 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int targetSum) {
            dfs(root, targetSum, new ArrayList<>());
            return res;
        }

        private void dfs(TreeNode root, int sum, ArrayList<Integer> sub) {
            if (root == null) {
                return;
            }
            sub.add(root.val);
            if (root.left == null && root.right == null && sum == root.val) res.add(new ArrayList<>(sub));
            dfs(root.left, sum - root.val, sub);
            dfs(root.right, sum - root.val, sub);
            sub.remove(sub.size() - 1);
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
