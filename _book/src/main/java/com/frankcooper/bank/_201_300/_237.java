package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _237 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * 237. 删除链表中的节点
         *
         * @param node 题目给的是删除节点，那说明这个节点可以舍弃了，我们把下一个节点的值拷贝给当前要删除的节点，再删除下一个节点。
         *             大致过程如下（删除3）：
         *             1->2->3->4->5
         *             1->2->4->4->5
         *             1->2->4->5
         */
        public void deleteNode(ListNode node) {
            if (node == null) {
                return;
            }
            node.val = node.next.val;
            node.next = node.next.next;
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
