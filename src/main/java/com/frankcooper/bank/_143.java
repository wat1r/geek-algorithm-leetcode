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
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            ListNode cur = tmp, pre = null, next;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            ListNode first = dummy.next;
            ListNode second = cur;
            ListNode l1 = first.next;
            ListNode l2 = second.next;
            while (second != null) {
                first.next = l2;
                second.next = l1;
                first = l1;
                second = l2;
            }
        }
    }
}
