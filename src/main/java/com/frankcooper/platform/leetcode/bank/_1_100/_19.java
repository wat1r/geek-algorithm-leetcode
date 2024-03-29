package com.frankcooper.platform.leetcode.bank._1_100;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _19 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode head = ListNodeIOUtils.transform("[1,2,3,4,5]");
            int n = 2;
            handler.removeNthFromEnd(head, n);

        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode fast = dummy;
            for (int i = 0; i <= n; i++) fast = fast.next;
            ListNode slow = dummy;
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
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
