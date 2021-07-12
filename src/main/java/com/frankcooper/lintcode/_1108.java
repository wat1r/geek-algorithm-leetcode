package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1108 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * 将一棵二叉树的所有结点作为根节点进行序列化，记录该前序序列化字符串出现的次数。
         * 1、如果出现的次数大于1，那么就说明该序列重复出现。
         * 2、如果等于1，说明在这之前遇到过一次节点。
         * 最后统计完重复的后，返回结果；如果是空结点的话，返回一个任意非空字符串。
         */
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            // write your code here
            dfs(root);
            return res;

        }

        private String dfs(TreeNode root) {
            if (root == null) return "#";
            String serial = root.val + "," + dfs(root.left) + "," + dfs(root.right);
            map.put(serial, map.getOrDefault(serial, 0) + 1);
            if (map.get(serial) == 2) res.add(root);
            return serial;
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
