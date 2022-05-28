package com.frankcooper.bank._901_1000;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _958 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isCompleteTree(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            boolean hasNullNode = false;
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur == null) hasNullNode = true;
                else {
                    if (hasNullNode) return false;
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public boolean isCompleteTree(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            TreeNode prev = root;
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if(prev == null && cur !=null) return false;
                if(cur!=null){
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
                prev = cur;
            }
            return true;
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
