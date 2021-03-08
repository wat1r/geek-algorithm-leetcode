package com.frankcooper.bank;

import com.frankcooper.struct.ListNode;

public class _143 {


    static class _1st {


        public static void main(String[] args) {
            ListNode l0 = new ListNode(1);
            ListNode l1 = new ListNode(2);
            ListNode l2 = new ListNode(3);
            ListNode l3 = new ListNode(4);
            ListNode l4 = new ListNode(5);
            l0.next = l1;
            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
            _1st handler = new _1st();
            handler.reorderList(l0);
        }


        public void reorderList(ListNode head) {
            if (head == null || head.next == null) return;
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode slow = head, fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            ListNode second = reverse(tmp);
            ListNode first = dummy.next;
            while (second != null) {
                ListNode l2 = second.next;
                second.next = first.next;
                first.next = second;
                first = second.next;
                second = l2;
            }
        }


        private ListNode reverse(ListNode head) {
            ListNode cur = head, pre = null, next;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }
}
