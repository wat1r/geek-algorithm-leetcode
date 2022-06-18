package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _21 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
            if (l1.val > l2.val) {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            } else {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 == null ? l2 : l1;
            }
            ListNode head = new ListNode(0);
            ListNode node = head;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }
            if (l1 != null) {
                node.next = l1;
            } else {
                node.next = l2;
            }
            return head.next;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
            if (list1.val > list2.val) {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            } else {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
    }


    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            ListNode l1 = ListNodeIOUtils.transform("[1,2,4]");
            ListNode l2 = ListNodeIOUtils.transform("[1,3,4]");
            handler.mergeSortedListWithoutDuplicates(l1, l2);

        }

        public ListNode mergeSortedListWithoutDuplicates(ListNode node1, ListNode node2) {
            //1.首先，根据两个结点情况判空，高效返回
            if (node1 == null) return node2;
            if (node2 == null) return node1;
            //2. 新建一个结点，指向结点值较小的那个
            ListNode head = (node1.val <= node2.val) ? node1 : node2;
            //3. 创建两个指针，动态的指向 node1 和 node2
            ListNode prev = head;
            ListNode point = null;

            while (node1 != null && node2 != null) {
                if (node1.val <= node2.val) {
                    point = node1;
                    node1 = node1.next;
                } else {
                    point = node2;
                    node2 = node2.next;
                }
                if (prev.val != point.val) {
                    prev.next = point;
                    prev = point;
                }
            }

            //处理多余结点
            while (node2 != null) {
                if (prev.val != node2.val) {
                    prev.next = node2;
                    prev = node2;
                }
                node2 = node2.next;
            }

            while (node1 != null) {
                if (prev.val != node1.val) {
                    prev.next = node1;
                    prev = node1;
                }
                node1 = node1.next;
            }
//        将最后一个结点的下一个结点置位 null
            prev.next = null;
            return head;
        }

    }
}
