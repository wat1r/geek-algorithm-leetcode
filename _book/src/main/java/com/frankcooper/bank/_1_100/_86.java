package com.frankcooper.platform.leetcode.bank._1_100;

import com.frankcooper.struct.ListNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _86 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode partition(ListNode head, int x) {
            if (head == null) return head;
            ListNode dummyBefore = new ListNode(0);
            ListNode before = dummyBefore;
            ListNode dummyAfter = new ListNode(0);
            ListNode after = dummyAfter;
            while (head != null) {
                if (head.val < x) {
                    before.next = head;
                    before = before.next;
                } else {
                    after.next = head;
                    after = after.next;
                }
                head = head.next;
            }
            before.next = dummyAfter.next;
            after.next = null;
            return dummyBefore.next;
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
