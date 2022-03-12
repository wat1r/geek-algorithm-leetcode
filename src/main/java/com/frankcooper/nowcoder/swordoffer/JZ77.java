package com.frankcooper.nowcoder.swordoffer;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class JZ77 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            if (pRoot == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(pRoot);
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                ArrayList<Integer> sub = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (level % 2 == 0) sub.add(cur.val);
                    else sub.add(0, cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(new ArrayList<>(sub));
                level++;
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
