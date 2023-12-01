package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;
import org.junit.Assert;
public class _1670 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class FrontMiddleBackQueue {
            private final Deque<Integer> left = new ArrayDeque<>();
            private final Deque<Integer> right = new ArrayDeque<>();

            // 调整长度，保证 0 <= right.size() - left.size() <= 1
            // 从而保证可以在正中间插入删除元素
            private void balance() {
                if (left.size() > right.size()) {
                    right.addFirst(left.pollLast());
                } else if (right.size() > left.size() + 1) {
                    left.addLast(right.pollFirst());
                }
            }

            public void pushFront(int val) {
                left.addFirst(val);
                balance();
            }

            public void pushMiddle(int val) {
                if (left.size() < right.size()) {
                    left.addLast(val);
                } else {
                    right.addFirst(val);
                }
            }

            public void pushBack(int val) {
                right.addLast(val);
                balance();
            }

            public int popFront() {
                if (right.isEmpty()) { // 整个队列为空
                    return -1;
                }
                int val = left.isEmpty() ? right.pollFirst() : left.pollFirst();
                balance();
                return val;
            }

            public int popMiddle() {
                if (right.isEmpty()) { // 整个队列为空
                    return -1;
                }
                if (left.size() == right.size()) {
                    return left.pollLast();
                }
                return right.pollFirst();
            }

            public int popBack() {
                if (right.isEmpty()) { // 整个队列为空
                    return -1;
                }
                int val = right.pollLast();
                balance();
                return val;
            }
        }

//        作者：灵茶山艾府
//        链接：https://leetcode.cn/problems/design-front-middle-back-queue/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

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
