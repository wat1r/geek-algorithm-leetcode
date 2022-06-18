package com.frankcooper.platform.lintcode;

import java.util.*;

import com.frankcooper.struct.ListNode;

public class _1371 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int numComponents(ListNode head, int[] G) {

            Set<Integer> set = new HashSet<>();
            for (int x : G) set.add(x);
            int res = 0;
            while (head != null) {
                if (set.contains(head.val) && (head.next == null || !set.contains(head.next.val))) res++;
                head = head.next;
            }
            return res;

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
