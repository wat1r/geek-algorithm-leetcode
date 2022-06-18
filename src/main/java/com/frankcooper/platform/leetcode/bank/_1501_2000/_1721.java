package com.frankcooper.platform.leetcode.bank._1501_2000;

import com.frankcooper.struct.ListNode;

public class _1721 {

    static class _1st {


        public static void main(String[] args) {
            ListNode l0 = new ListNode(1);
            ListNode l1 = new ListNode(2);
            ListNode l2 = new ListNode(3);
            ListNode l3 = new ListNode(4);
            ListNode l4 = new ListNode(5);
            l0.next = l1;
            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
            _1st handler = new _1st();
            handler.swapNodes(l0, 2);

        }


        //TODO
        public ListNode swapNodes(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode first = head, fPre = null;
            for (int i = 1; i < k; i++) {
                fPre = first;
                first = first.next;
            }
            ListNode sPre = null, slow = head, fast = first;
            while (fast.next != null) {
                sPre = slow;
                slow = slow.next;
                fast = fast.next;
            }
            ListNode second = slow;
            swap(first, fPre, second, sPre);
            return dummy.next;

        }

        private void swap(ListNode first, ListNode fPre, ListNode second, ListNode sPre) {
            ListNode f = first.next;
            fPre.next = second;
            ListNode s = second.next;
            sPre.next = first;
            second.next = f;
            first.next = s;
        }
    }
}
