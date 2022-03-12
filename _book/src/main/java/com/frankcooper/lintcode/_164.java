package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _164 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public List<TreeNode> generateTrees(int n) {
            return helper(1, n);
        }

        private List<TreeNode> helper(int start, int end) {
            List<TreeNode> res = new ArrayList<>();
            if (start > end) {
                res.add(null);
                return res;
            }
            for (int k = start; k <= end; k++) {
                List<TreeNode> left = helper(start, k - 1);
                List<TreeNode> right = helper(k + 1, end);
                for (int i = 0; i < left.size(); i++) {
                    TreeNode l = left.get(i);
                    for (int j = 0; j < right.size(); j++) {
                        TreeNode r = right.get(j);
                        TreeNode root = new TreeNode(k);
                        root.left = l;
                        root.right = r;
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
