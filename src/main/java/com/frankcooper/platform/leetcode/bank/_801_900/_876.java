package com.frankcooper.platform.leetcode.bank._801_900;

import com.frankcooper.struct.ListNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _876 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode middleNode(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow.next;
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
