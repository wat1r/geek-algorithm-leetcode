package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1110 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        Set<Integer> set = new HashSet<>();
        List<TreeNode> res = new ArrayList<>();

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            for (int x : to_delete) {
                set.add(x);
            }
            dfs(root, true);
            return res;
        }


        public TreeNode dfs(TreeNode node, boolean isRoot) {
            if (node == null) {
                return null;
            }
            //该节点需要删除
            boolean needDelete = set.contains(node.val);
            node.left = dfs(node.left, needDelete);//node节点是要被删除的，那么node节点的left节点变成潜在的根节点，isRoot设置为true，如果不是潜在的根节点，isRoot设置为false
            node.right = dfs(node.right, needDelete);//node的right节点同理
            if (needDelete) {//该节点需要被移除
                return null;
            } else {//该节点不需要被移除
                //该节点是根节点，收集结果集
                if (isRoot) {
                    res.add(node);
                }
                return node;
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
