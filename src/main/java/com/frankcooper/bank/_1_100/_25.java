package com.frankcooper.bank._1_100;

import com.frankcooper.struct.ListNode;

import java.util.LinkedList;
import java.util.List;

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

    static class _2nd {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy, end = dummy;
            while (end.next != null) {
                for (int i = 0; i < k && end != null; i++) {
                    end = end.next;
                }
                if (end == null) break;
                ListNode start = prev.next;
                ListNode nxt = end.next;
                end.next = null;
                prev.next = reverse(start);
                start.next = nxt;
                prev = start;
                end = start;
            }
            return dummy.next;
        }

        /**
         * 翻转链表，并返回翻转后的头结点
         *
         * @param head
         * @return
         */
        private ListNode reverse(ListNode head) {
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }
            return prev;
        }
    }

    static class _3rd {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                //当数量不足k的时候，返回head节点，不需要翻转了
                if (tail == null) return head;
                tail = tail.next;
            }
            //开始翻转[head,tail)范围内的链表，并返回翻转后的新的节点，也就是tail节点的上一个节点
            ListNode new_head = reverse(head, tail);
            //递归翻转以tail为头节点的如下部分链表
            head.next = reverseKGroup(tail, k);
            return new_head;//返回翻转后的新的头节点
        }

        private ListNode reverse(ListNode head, ListNode tail) {
            ListNode prev = null, cur = null;
            while (head != tail) {
                cur = head.next;//step1
                head.next = prev;//step2
                prev = head;//step3
                head = cur;//step4
            }
            return prev;
        }
    }

}
