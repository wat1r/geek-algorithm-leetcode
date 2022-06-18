package com.frankcooper.platform.leetcode.swordoffer;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Sword_62 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * 最后只剩下一个元素，假设这个最后存活的元素为 num, 这个元素最终的的下标一定是0 （因为最后只剩这一个元素），
         * 所以如果我们可以推出上一轮次中这个num的下标，然后根据上一轮num的下标推断出上上一轮num的下标，
         * 直到推断出元素个数为n的那一轮num的下标，那我们就可以根据这个下标获取到最终的元素了。推断过程如下：
         * <p>
         * 首先最后一轮中num的下标一定是0， 这个是已知的。
         * 那上一轮应该是有两个元素，此轮次中 num 的下标为 (0 + m)%n = (0+3)%2 = 1; 说明这一轮删除之前num的下标为1；
         * 再上一轮应该有3个元素，此轮次中 num 的下标为 (1+3)%3 = 1；说明这一轮某元素被删除之前num的下标为1；
         * 再上一轮应该有4个元素，此轮次中 num 的下标为 (1+3)%4 = 0；说明这一轮某元素被删除之前num的下标为0；
         * 再上一轮应该有5个元素，此轮次中 num 的下标为 (0+3)%5 = 3；说明这一轮某元素被删除之前num的下标为3；
         * ....
         * <p>
         * 因为我们要删除的序列为0-n-1, 所以求得下标其实就是求得了最终的结果。比如当n 为5的时候，num的初始下标为3，
         * 所以num就是3，也就是说从0-n-1的序列中， 经过n-1轮的淘汰，3这个元素最终存活下来了，也是最终的结果。
         * <p>
         * 总结一下推导公式：(此轮过后的num下标 + m) % 上轮元素个数 = 上轮num的下标
         */

        public int lastRemaining(int n, int m) {
            int res = 0;
            for (int i = 2; i <= n; i++) {
                res = (res + m) % i;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            ListNode head = ListNodeIOUtils.transform("[0,1,2,3,4]");
            ListNode tail = head.next.next.next.next;
            tail.next = head;
            int m = 3;
            handler.delNodeInCircle(head, m);

        }


        private int delNodeInCircle(ListNode head, int m) {
            int idx = 0;//计数索引
            //如果只剩下一个节点，因为节点是环状的，所以自己指向自己即可退出
            while (head.next != head) {
                if (idx == m - 2) {//m表示个数 idx是索引，差2个
                    head.next = head.next.next;//删除要删除的节点
                    idx = 0;
                } else {
                    idx++;//计数
                }
                //跳到下一个节点
                head = head.next;
            }

            return head.val;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        private int delNodeInCircle(ListNode head, int m, int size) {
            int idx = 0;//计数索引
            //如果只剩下一个节点，因为节点是环状的，所以自己指向自己即可退出
            while (head.next != head) {
                if (idx == (m - 2) % size) {//m表示个数 idx是索引，差2个
                    head.next = head.next.next;//删除要删除的节点
                    idx = 0;
                    size--;
                } else {
                    idx++;//计数
                }
                //跳到下一个节点
                head = head.next;
            }
            return head.val;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int lastRemaining(int n, int m) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) list.add(i);
            int idx = 0;
            while (list.size() > 1) {
                idx = (idx + m - 1) % list.size();
                list.remove(idx);
            }
            return list.get(0);
        }
    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            handler.lastRemaining(5, 3);
        }

        public int lastRemaining(int n, int m) {
            int[] f = new int[n + 1];
            f[0] = 0;
            for (int i = 1; i <= n; i++) {
                f[i] = (f[i - 1] + m) % i;
            }
            return f[n];
        }
    }
}
