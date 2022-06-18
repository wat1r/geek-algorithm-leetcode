package com.frankcooper.platform.leetcode.swordoffer;

import com.frankcooper.struct.ListNode;

public class Sword_22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (k-- > 0) fast = fast.next;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
