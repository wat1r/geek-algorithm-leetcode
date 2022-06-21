package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _2181 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode head = ListNodeIOUtils.transform("[0,3,1,0,4,5,2,0]");
            handler.mergeNodes(head);


        }


        public ListNode mergeNodes(ListNode head) {
            ListNode dummy = new ListNode(-1);
            ListNode prev = dummy;
            ListNode cur = null;
            while (head != null) {
                //最后的0不放
                if (head.val == 0 && head.next != null) {
                    cur = new ListNode(0);
                    prev.next = cur;
                    prev = cur;
                }
                cur.val += head.val;
                head = head.next;
            }
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
