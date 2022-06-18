package com.frankcooper.platform.lintcode;

import com.frankcooper.struct.ListNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _174 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode fast = head;
            ListNode slow = head;
            ListNode prev = dummy;
            for (int i = 0; i < n; i++) fast = fast.next;
            while (fast != null) {
                prev = prev.next;
                slow = slow.next;
                fast = fast.next;
            }
            prev.next = slow.next;
            slow.next = null;
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
