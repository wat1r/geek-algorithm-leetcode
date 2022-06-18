package com.frankcooper.platform.leetcode.swordoffer;

import com.frankcooper.struct.ListNode;

public class Sword_24 {

    static class _1st {
        public ListNode reverseList(ListNode head) {
            ListNode curr = null, pre = head;
            while (pre != null) {
                ListNode next = pre.next;
                pre.next = curr;
                curr = pre;
                pre = next;
            }
            return curr;
        }
    }


    static class _2nd {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode last = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return last;
        }
    }
}
