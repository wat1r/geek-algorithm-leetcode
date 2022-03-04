package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;
import com.frankcooper.utils.ListNodeUtils;
import org.junit.Assert;

public class _206 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode head = ListNodeUtils.buildListNodeList(new int[]{1, 2, 3, 4, 5});
//            handler.reverseList(head);
        }

/*        public ListNode reverseList(ListNode head) {
            ListNode pre = head;
            ListNode cur = pre.next;
            if (cur.next == null) cur.next = head;
            ListNode later = null;
            while (cur!= null) {
                later = cur.next;
                cur.next = pre;
                pre = cur;
                cur = later;
            }
            head.next = null;
            return pre;

        }*/


    }

    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public ListNode reverseList(ListNode head) {
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
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            ListNode head = ListNodeIOUtils.transform("[1,2,3,4,5]");
            handler.reverseList(head);
        }


        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode newNode = reverseList(head.next);
            //如果链表是 1->2->3->4->5，那么此时的cur就是5
            //而head是4，head的next是5，next.next是空
            //所以head.next.next 就是5->4
            head.next.next = head;
            //head的next需要断开
            head.next = null;
            return newNode;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public ListNode reverseList(ListNode head) {
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode t = cur.next;
                cur.next = prev;
                prev = cur;
                cur = t;
            }
            return prev;

        }
    }

    static class _5th {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode node = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return node;
        }
    }

    static class _6th {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode node = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return node;
        }
    }
}
