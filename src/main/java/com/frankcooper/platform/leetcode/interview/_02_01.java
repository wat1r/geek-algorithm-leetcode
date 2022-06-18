package com.frankcooper.platform.leetcode.interview;

import com.frankcooper.struct.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _02_01 {

    static class _1st {

        public static void main(String[] args) {
            ListNode l0 = new ListNode(1);
            ListNode l1 = new ListNode(2);
            ListNode l2 = new ListNode(3);
            ListNode l3 = new ListNode(3);
            ListNode l4 = new ListNode(2);
            ListNode l5 = new ListNode(1);
            l0.next = l1;
            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
            l4.next = l5;
            _1st handler = new _1st();
            handler.removeDuplicateNodes(l0);
        }


        public ListNode removeDuplicateNodes(ListNode head) {

            Set<Integer> set = new HashSet<>();
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, curr = head;
            while (curr != null) {
                while (curr != null && set.contains(curr.val)) {
                    curr = curr.next;
                }
                if (curr != null) {
                    set.add(curr.val);
                    pre.next = curr;
                    pre = curr;
                    curr = curr.next;
                } else {
                    pre.next = null;//此处要添加
                }
            }
            return dummy.next;

        }
    }
}
