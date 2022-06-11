package com.frankcooper.bank._1001_1500;

import com.frankcooper.struct.ListNode;
import com.frankcooper.struct.TreeNode;

public class _1367 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isSubPath(ListNode head, TreeNode root) {
            if (head == null) return true;
            if (root == null) return false;
            //根节点，是不是能找到一条路径
            //根节点的左右子树能不能找到一条路径
            return checkEqual(head, root) ||
                    isSubPath(head, root.left) ||
                    isSubPath(head, root.right);
        }


        private boolean checkEqual(ListNode head, TreeNode root) {
            if (head == null) return true;//链表已经遍历结束，说明找到一个路径
            if (root == null) return false;//链表未遍历结束，但树遍历完了，返回F
            if (head.val != root.val) return false;//值不一样，返回F
            //找当前链表的下一个与当前节点的左右子树分别对比
            return checkEqual(head.next, root.left) || checkEqual(head.next, root.right);
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
