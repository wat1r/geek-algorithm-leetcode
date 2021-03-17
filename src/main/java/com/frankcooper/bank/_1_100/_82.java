package com.frankcooper.bank._1_100;

import com.frankcooper.struct.ListNode;

public class _82 {

    static class _1st {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, curr;
            while (pre.next != null) {
                curr = pre.next;
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                if (curr != pre.next) {
                    pre.next = curr.next;
                } else {
                    pre = pre.next;
                }
            }
            return dummy.next;
        }
    }

    static class _2nd {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            if (head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                return deleteDuplicates(head.next);
            } else {
                head.next = deleteDuplicates(head.next);
                return head;
            }
        }
    }
}
