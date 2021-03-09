package com.frankcooper.bank;

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
}
