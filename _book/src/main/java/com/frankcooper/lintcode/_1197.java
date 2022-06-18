package com.frankcooper.platform.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1197 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int findBottomLeftValue(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            int res = 0;
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    res = cur.val;
                    if (cur.right != null) q.offer(cur.right);
                    if (cur.left != null) q.offer(cur.left);
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
