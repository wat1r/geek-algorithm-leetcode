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

    static class _3rd {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) return null;
            ListNode cur = head;
            while (cur.next != null) {
                if (cur.val == cur.next.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
    }

    static class _4th {


        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            head.next = deleteDuplicates(head.next);
            return head.val == head.next.val ? head.next : head;
        }
    }

    static class _5th {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode cur = head, nxt = head.next;
            while (nxt != null) {
                if (cur.val != nxt.val) {
                    cur = cur.next;
                } else {
                    cur.next = nxt.next;
                }
                nxt = nxt.next;
            }
            return head;
        }
    }

}
