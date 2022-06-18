package com.frankcooper.platform.leetcode.bank._1501_2000;

import com.frankcooper.struct.ListNode;
import com.frankcooper.utils.ListNodeUtils;

public class _1669 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            ListNode list1 = ListNodeUtils.buildListNodeList(new int[]{0, 1, 2, 3, 4, 5});
            int a = 3, b = 4;
            ListNode list2 = ListNodeUtils.buildListNodeList(new int[]{1000000, 1000001, 1000002});
            ListNode expected = ListNodeUtils.buildListNodeList(new int[]{0, 1, 2, 1000000, 1000001, 1000002, 5});
            handler.mergeInBetween(list1, a, b, list2);
            /**
             * [0,3,2,1,4,5]
             * 3
             * 4
             * [1000000,1000001,1000002]
             */

       /*     list1 = ListNodeUtils.buildListNodeList(new int[]{0, 3, 2, 1, 4, 5});
            a = 3;
            b = 4;
            list2 = ListNodeUtils.buildListNodeList(new int[]{1000000, 1000001, 1000002});
            handler.mergeInBetween(list1, a, b, list2);*/
        }


        public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
            ListNode dummy = new ListNode(-1);
            dummy.next = list1;
            int i = 0;
            while (list1 != null && ++i != a) {
                list1 = list1.next;
            }
            ListNode prev = list1;//拿到a之前的节点
            list1 = list1.next;//这一步画个图debug下
            while (list1 != null && i++ != b) {
                list1 = list1.next;
            }
            ListNode successor = list1.next;//拿到b之后的节点
            ListNode cur = prev;
            while (list2 != null) {//组装list2
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
            cur.next = successor;
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
