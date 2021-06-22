package com.frankcooper.bank._201_300;

import java.util.*;

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
