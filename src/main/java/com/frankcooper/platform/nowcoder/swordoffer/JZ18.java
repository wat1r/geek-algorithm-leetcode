package com.frankcooper.platform.nowcoder.swordoffer;

import com.frankcooper.struct.ListNode;

public class JZ18 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode deleteNode(ListNode head, int val) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy, cur = head;
            //遍历获取当前节点和当前节点的前一个节点
            while (cur != null) {
                if (cur.val == val) break;
                prev = prev.next;
                cur = cur.next;
            }
            //删除cur节点
            prev.next = prev.next.next;
            return dummy.next;
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
