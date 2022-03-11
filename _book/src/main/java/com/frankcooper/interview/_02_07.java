package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _02_07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * need once more
         * @param headA
         * @param headB
         * @return
         */


        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode pA = headA, pB = headB;
            while (pA != pB) {
                if (pA == null) pA = headB;
                else pA = pA.next;
                if (pB == null) pB = headA;
                else pB = pB.next;
            }
            return pA;
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
