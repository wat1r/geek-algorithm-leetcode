package com.frankcooper.interview;

import com.frankcooper.struct.ListNode;

import java.util.List;

/*import java.util.*;
import org.junit.Assert;*/
public class _02_08 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public ListNode detectCycle(ListNode head) {

            ListNode fast = head, slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
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
