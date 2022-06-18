package com.frankcooper.platform.leetcode.bank._1_100;

import com.frankcooper.struct.ListNode;

/**
 * @Date 2020/8/5
 * @Author Frank Cooper
 * @Description
 */
public class _2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            head.next = new ListNode(sum % 10);
            head = head.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) {
            head.next = new ListNode(carry);
        }
        return dummy.next;
    }


    public ListNode addTwoNumbers1st(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            sum %= 10;
            dummy.next = new ListNode(sum);
            dummy = dummy.next;
        }
        if (carry != 0) {
            dummy.next = new ListNode(carry);
        }
        return head.next;
    }
}
