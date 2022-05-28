package com.frankcooper.bank._901_1000;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _919 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        class CBTInserter {


            List<TreeNode> arr = new ArrayList<>();


            public CBTInserter(TreeNode root) {
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    TreeNode cur = q.poll();
                    arr.add(cur);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
            }

            public int insert(int v) {
                TreeNode node = new TreeNode(v);
                arr.add(node);
                int parent = arr.size() / 2 - 1;
                if (arr.size() % 2 == 0) arr.get(parent).left = node;
                else arr.get(parent).right = node;
                return arr.get(parent).val;
            }

            public TreeNode get_root() {
                return arr.isEmpty() ? null : arr.get(0);
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
