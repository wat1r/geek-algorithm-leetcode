package com.frankcooper.bank;

import com.frankcooper.struct.ListNode;

import java.util.List;

public class _92 {


    public static void main(String[] args) {
        _2nd handler = new _2nd();
        ListNode head = null;
        ListNode pre = new ListNode(1);
        for (int i = 1; i <= 5; i++) {
            if (i == 1) head = pre;
            ListNode curr = i == 5 ? null : new ListNode(i + 1);
            pre.next = curr;
            pre = curr;
        }
        handler.reverseBetween(head, 2, 4);
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode/
     */

    static class _1st {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode curr = head, pre = null;
            for (int i = 1; i < m; i++) {
                pre = curr;
                curr = curr.next;
            }
            System.out.printf("%d\n", curr.val);
            ListNode con = pre, tail = curr;
            for (int i = 0; i < n - m + 1; i++) {
                ListNode third = curr.next;
                curr.next = pre;
                pre = curr;
                curr = third;
            }
            System.out.printf("%d\n", pre.val);
            // System.out.printf("%d\n",con.val);
            if (con != null) con.next = pre;
            else head = pre;
            tail.next = curr;
            return head;


        }
    }


    static class _2nd {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = dummy;
            ListNode cur = pre.next;
            for (int i = 1; i < m; i++) {
                pre = pre.next;
                cur = cur.next;
            }
            for (int i = 0; i < n - m; i++) {
                ListNode tmp = cur.next;
                cur.next = tmp.next;
                tmp.next = pre.next;
                pre.next = tmp;
            }
            return dummy.next;
        }
    }
}
