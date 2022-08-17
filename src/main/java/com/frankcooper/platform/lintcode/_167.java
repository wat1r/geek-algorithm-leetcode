package com.frankcooper.platform.lintcode;

import java.util.*;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _167 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode l1 = ListNodeIOUtils.transform("[9,9]");
            ListNode l2 = ListNodeIOUtils.transform("[9]");
            handler.addLists(l1, l2);

        }

        //        167 · 链表求和
        public ListNode addLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            int carry = 0, value = 0, sum = 0;
            while (l1 != null && l2 != null) {
                sum = l1.val + l2.val + carry;
                value = sum % 10;
                cur.next = new ListNode(value);
                cur = cur.next;
                carry = sum / 10;
                l1 = l1.next;
                l2 = l2.next;
            }
            while (l1 != null) {
                sum = l1.val + carry;
                value = sum % 10;
                carry = sum / 10;
                cur.next = new ListNode(value);
                cur = cur.next;
                l1 = l1.next;
            }

            while (l2 != null) {
                sum = l2.val + carry;
                value = sum % 10;
                carry = sum / 10;
                cur.next = new ListNode(value);
                cur = cur.next;
                l2 = l2.next;
            }
            if (carry != 0) cur.next = new ListNode(carry);
            return dummy.next;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
