package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

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
            TreeNode root = TreeNodeIOUtils.transform("[5,3,6,2,4,null,7]");
            int k = 11;
            handler.findTarget(root, k);
        }

        //[5,3,6,2,4,null,7]
        //k =11
        public boolean findTarget(TreeNode root, int k) {
            //左右两个子树的queue
            Deque<TreeNode> lq = new ArrayDeque<>();
            Deque<TreeNode> rq = new ArrayDeque<>();
            TreeNode t = root;
            //一直压左子树 [2,3,5]
            while (t != null) {
                lq.addLast(t);
                t = t.left;
            }
            t = root;
            //一直压右子树 [7,6,5]
            while (t != null) {
                rq.addLast(t);
                t = t.right;
            }
            //第一次进来 ln =2 ,rn = 7 一个是lq的最小值， 一个是rq的最大值
            TreeNode ln = lq.peekLast();
            TreeNode rn = rq.peekLast();
            while (ln.val < rn.val) {
                //模拟双指针
                int sum = ln.val + rn.val;
                if (sum == k) return true;
                //如果sum小了，挪动左指针 sum大了 挪动有右指针
                if (sum < k) ln = getNext(lq, true);
                else rn = getNext(rq, false);
            }
            return false;
        }

        public TreeNode getNext(Deque<TreeNode> q, boolean f) {
            //如果是左子树，拿到左子树的右节点，如果是右子树，拿到右子树的左节点
            TreeNode t = f ? q.pollLast().right : q.pollLast().left;
            //一直添加该节点的左 / 右 子树的节点
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
