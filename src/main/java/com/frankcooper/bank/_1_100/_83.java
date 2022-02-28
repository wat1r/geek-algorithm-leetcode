package com.frankcooper.bank._1_100;

import com.frankcooper.struct.ListNode;

public class _83 {
    static class _1st {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, curr = head;
            while (curr != null && curr.next != null) {
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                pre.next = curr;
                pre = pre.next;
                curr = curr.next;
            }
            return dummy.next;
        }
    }

    static class _2nd {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-101);
            dummy.next = head;
            ListNode prev = dummy, cur = head;
            while (cur != null && cur.next != null) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                prev.next = cur;
                prev = prev.next;
                cur = cur.next;

            }
            return dummy.next;
        }
    }
}
