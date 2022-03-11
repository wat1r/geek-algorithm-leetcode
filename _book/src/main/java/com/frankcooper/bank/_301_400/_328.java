package com.frankcooper.bank._301_400;

import java.util.*;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _328 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode head = ListNodeIOUtils.transform("[1,2,3,4,5]");
            handler.oddEvenList(head);
        }

        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
            return head;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode odd = head;//奇数
            ListNode even = head.next;//偶数
            ListNode adj = even;//偶数的开头
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = adj;
            return head;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
