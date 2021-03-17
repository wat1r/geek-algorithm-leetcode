package com.frankcooper.bank._1_100;

import com.frankcooper.struct.ListNode;

public class _25 {

    static class _1st {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, end = dummy;
            while (end.next != null) {
                for (int i = 0; i < k && end != null; i++) {
                    end = end.next;
                }
                if (end == null) break;
                ListNode start = pre.next;
                ListNode next = end.next;
                end.next = null;
                pre.next = reverse(start);
                start.next = next;
                pre = start;
                end = pre;
            }
            return dummy.next;
        }


        private ListNode reverse(ListNode head) {
            ListNode pre = null, curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            return pre;
        }
    }
}
