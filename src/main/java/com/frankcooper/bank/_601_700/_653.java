package com.frankcooper.bank._601_700;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _653 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        Set<Integer> set = new HashSet<>();

        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;
            if (set.contains(k - root.val)) return true;
            set.add(root.val);
            return findTarget(root.left, k) || findTarget(root.right, k);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean findTarget(TreeNode root, int k) {
            Deque<TreeNode> lq = new ArrayDeque<>();
            Deque<TreeNode> rq = new ArrayDeque<>();
            TreeNode t = root;
            while (t != null) {
                lq.addLast(t);
                t = t.left;
            }
            t = root;
            while (t != null) {
                rq.addLast(t);
                t = t.right;
            }
            TreeNode ln = lq.peekLast();
            TreeNode rn = rq.peekLast();
            while (ln.val < rn.val) {
                int sum = ln.val + rn.val;
                if (sum == k) return true;
                if (sum < k) ln = getNext(lq, true);
                else rn = getNext(rq, false);
            }
            return false;
        }

        public TreeNode getNext(Deque<TreeNode> q, boolean f) {
            TreeNode t = f ? q.pollLast().right : q.pollLast().left;
            while (t != null) {
                q.addLast(t);
                t = f ? t.left : t.right;
            }
            return q.peekLast();
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
