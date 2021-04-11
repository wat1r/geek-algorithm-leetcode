package com.frankcooper.bank.bi_weekly;

import com.frankcooper.swordoffer.utils.PrintUtils;

import java.util.*;

public class BiWeek40 {


    static class _1st {
        public int maxRepeating(String sequence, String word) {
            int ans = 0;
            StringBuilder sb = new StringBuilder();
            while (sequence.contains(sb.append(word).toString())) ans++;
            return ans;
        }
    }

    static class _2nd {


        public static class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        static _2nd handler = new _2nd();


        public static void main(String[] args) {
//            list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
            ListNode l1_0 = new ListNode(0);
            ListNode l1_1 = new ListNode(1);
            ListNode l1_2 = new ListNode(2);
            ListNode l1_3 = new ListNode(3);
            ListNode l1_4 = new ListNode(4);
            ListNode l1_5 = new ListNode(5);
            l1_0.next = l1_1;
            l1_1.next = l1_2;
            l1_2.next = l1_3;
            l1_3.next = l1_4;
            l1_4.next = l1_5;
            int a = 3, b = 4;
            ListNode l2_0 = new ListNode(1000000);
            ListNode l2_1 = new ListNode(1000001);
            ListNode l2_2 = new ListNode(1000002);
            l2_0.next = l2_1;
            l2_1.next = l2_2;
            handler.mergeInBetween(l1_0, a, b, l2_0);

        }


        public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
            int idx = 0;
            ListNode start = null, end = null;
            ListNode dummy = new ListNode(-1);
            ListNode pre = new ListNode(-1);
            dummy.next = list1;
            pre.next = list1;
            ListNode curr = list1;
            while (curr != null) {
                idx++;
                pre = pre.next;
                curr = curr.next;
                if (idx == a) start = pre;
                if (idx == b) end = curr.next;
            }

            while (list2 != null) {
                start.next = list2;
                list2 = list2.next;
                start = start.next;
            }
            start.next = end;
            ListNode printCurr = dummy;
//            while (printCurr.next != null) {
//                System.out.printf("%d-->", printCurr.next.val);
//                printCurr = printCurr.next;
//            }
            return dummy.next;
        }


    }

    static class _3rd {


        public static void main(String[] args) {
//            FrontMiddleBackQueue q = new FrontMiddleBackQueue();
//            q.pushFront(1);   // [1]
//            q.pushBack(2);    // [1, 2]
//            q.pushMiddle(3);  // [1, 3, 2]
//            q.pushMiddle(4);  // [1, 4, 3, 2]
//            q.popFront();     // 返回 1 -> [4, 3, 2]
//            q.popMiddle();    // 返回 3 -> [4, 2]
//            q.popMiddle();    // 返回 4 -> [2]
//            q.popBack();      // 返回 2 -> []
//            q.popFront();     // 返回 -1 -> [] （队列为空）

            /**
             * ["FrontMiddleBackQueue","pushFront","pushBack","pushMiddle","pushMiddle","popFront","popMiddle","popMiddle","popBack","popFront"]
             * [[],[1],[2],[3],[4],[],[],[],[],[]]
             * ["FrontMiddleBackQueue","pushFront","pushFront","pushFront","pushFront","popBack","popBack","popBack","popBack"]
             * [[],[1],[2],[3],[4],[],[],[],[]]
             * ["FrontMiddleBackQueue","popMiddle","popMiddle","pushMiddle","popBack","popFront","popMiddle"]
             * [[],[],[],[8],[],[],[]]
             * ["FrontMiddleBackQueue","pushMiddle","pushMiddle","pushMiddle","popMiddle","popMiddle","popMiddle"]
             * [[],[1],[2],[3],[],[],[]]
             ["FrontMiddleBackQueue","popMiddle","popMiddle","pushMiddle","pushMiddle","popMiddle","popMiddle","popMiddle","popBack","popMiddle","popFront","pushBack","popFront","pushMiddle","pushMiddle","popMiddle","popBack","pushFront","popMiddle","pushMiddle","pushMiddle","pushMiddle","popMiddle","pushMiddle","popBack","pushMiddle","popMiddle","popMiddle","popMiddle","popMiddle","popFront","pushMiddle","pushMiddle","pushMiddle","pushFront"]
             [[],[],[],[773222],[279355],[],[],[],[],[],[],[448905],[],[168284],[874541],[],[],[15656],[],[803226],[720129],[626048],[],[860306],[],[630886],[],[],[],[],[],[837735],[414354],[404946],[88719]]
             */

            String[] ops = new String[]{
                    "FrontMiddleBackQueue", "pushFront", "pushFront", "pushFront", "pushFront", "popBack", "popBack", "popBack", "popBack"};
            int[][] values = PrintUtils.processSymbol("[[-1],[1],[2],[3],[4],[-1],[-1],[-1],[-1]]");

            ops = new String[]{"FrontMiddleBackQueue", "popMiddle", "popMiddle", "pushMiddle", "popBack", "popFront", "popMiddle"};
            values = PrintUtils.processSymbol("[[-1],[-1],[-1],[8],[-1],[-1],[-1]]");
            ops = new String[]{"FrontMiddleBackQueue", "popMiddle", "popMiddle", "pushMiddle", "pushMiddle", "popMiddle", "popMiddle", "popMiddle", "popBack", "popMiddle", "popFront", "pushBack", "popFront", "pushMiddle", "pushMiddle", "popMiddle", "popBack", "pushFront", "popMiddle", "pushMiddle", "pushMiddle", "pushMiddle", "popMiddle", "pushMiddle", "popBack", "pushMiddle", "popMiddle", "popMiddle", "popMiddle", "popMiddle", "popFront", "pushMiddle", "pushMiddle", "pushMiddle", "pushFront"};
            values = PrintUtils.processSymbol("[[-1],[-1],[-1],[773222],[279355],[-1],[-1],[-1],[-1],[-1],[-1],[448905],[-1],[168284],[874541],[-1],[-1],[15656],[-1],[803226],[720129],[626048]]");

            FrontMiddleBackQueue q = null;
            for (int i = 0; i < ops.length; i++) {
                if (i == 12) {
                    System.out.println(i);
                }
                String currOp = ops[i];
                switch (currOp) {
                    case "FrontMiddleBackQueue":
                        q = new FrontMiddleBackQueue();
                        break;
                    case "pushFront":
                        q.pushFront(values[i][0]);
                        break;
                    case "popBack":
                        System.out.println(q.popBack());
                        break;
                    case "popMiddle":
                        System.out.println(q.popMiddle());
                        break;
                    case "pushBack":
                        q.pushBack(values[i][0]);
                        break;
                    case "popFront":
                        System.out.println(q.popFront());
                        break;
                    default:
                        break;
                }
            }

        }


        static class FrontMiddleBackQueue {

            Deque<Integer> left;
            Deque<Integer> right;


            public FrontMiddleBackQueue() {
                left = new LinkedList<>();
                right = new LinkedList<>();
            }

            public void pushFront(int val) {
                left.addFirst(val);
                balance();
            }

            public void pushMiddle(int val) {
                if (left.size() == right.size()) {
                    right.addFirst(val);
                } else {
                    left.addLast(val);
                }
                balance();
            }

            public void pushBack(int val) {
                right.addLast(val);
                balance();
            }

            public int popFront() {
                if (left.isEmpty()) {
                    if (right.size() > left.size()) return right.pollFirst();
                    return -1;
                }
                int ans = left.pollFirst();
                balance();
                return ans;
            }

            public int popMiddle() {
                int ans;
                ans = right.size() > left.size() ? right.pollFirst() : (left.isEmpty() ? -1 : left.pollLast());
                balance();
                return ans;
            }

            public int popBack() {
                if (right.isEmpty()) return -1;
                int ans = right.pollLast();
                balance();
                return ans;
            }

            public void balance() {
                while ((right.size() - left.size()) != 0 && (right.size() - left.size()) != 1) {
                    if (right.size() < left.size()) right.addFirst(left.pollLast());
                    else left.addLast(right.pollFirst());
                }
            }
        }


    }


}
