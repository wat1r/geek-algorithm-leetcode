package com.frankcooper.platform.leetcode.interview;

import com.frankcooper.struct.ListNode;

public class _02_05 {
    static class _1st {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


            int carry = 0;
            int value = 0;
            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;
            int sum = 0;
            while (l1 != null && l2 != null) {
                sum = l1.val + l2.val + carry;
                value = sum % 10;
                carry = sum / 10;
                curr.next = new ListNode(value);
                curr = curr.next;
                l1 = l1.next;
                l2 = l2.next;
            }
            while (l1 != null) {
                sum = l1.val + carry;
                value = sum % 10;
                carry = sum / 10;
                curr.next = new ListNode(value);
                curr = curr.next;
                l1 = l1.next;
            }
            while (l2 != null) {
                sum = l2.val + carry;
                value = sum % 10;
                carry = sum / 10;
                curr.next = new ListNode(value);
                curr = curr.next;
                l2 = l2.next;
            }
            if (carry != 0) curr.next = new ListNode(carry);
            return dummy.next;
        }
    }
}
