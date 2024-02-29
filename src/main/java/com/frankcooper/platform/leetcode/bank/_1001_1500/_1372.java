package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1372 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            TreeNode root = TreeNodeIOUtils.transform("[1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]");
            handler.longestZigZag(root);

        }


        Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
        Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
        Queue<TreeNode[]> q = new LinkedList<TreeNode[]>();

        public int longestZigZag(TreeNode root) {
            dp(root);
            int maxAns = 0;
            for (TreeNode u : f.keySet()) {
                maxAns = Math.max(maxAns, Math.max(f.get(u), g.get(u)));
            }
            return maxAns;
        }

        public void dp(TreeNode o) {
            f.put(o, 0);
            g.put(o, 0);
            q.offer(new TreeNode[]{o, null});
            while (!q.isEmpty()) {
                TreeNode[] y = q.poll();
                TreeNode u = y[0], x = y[1];
                f.put(u, 0);
                g.put(u, 0);
                if (x != null) {
                    if (x.left == u) {
                        f.put(u, g.get(x) + 1);
                    }
                    if (x.right == u) {
                        g.put(u, f.get(x) + 1);
                    }
                }
                if (u.left != null) {
                    q.offer(new TreeNode[]{u.left, u});
                }
                if (u.right != null) {
                    q.offer(new TreeNode[]{u.right, u});
                }
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
