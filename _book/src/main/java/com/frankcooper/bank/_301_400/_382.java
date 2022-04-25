package com.frankcooper.bank._301_400;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _382 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class Solution {
            ListNode head;
            Random random;

            public Solution(ListNode head) {
                this.head = head;
                this.random = new Random();
            }

            public int getRandom() {
                ListNode cur = head;
                int cnt = 0;
                int reserve = 0;
                while (cur != null) {
                    cnt++;
                    int rand = random.nextInt(cnt) + 1;
                    if (rand == cnt) {
                        reserve = cur.val;
                    }
                    cur = cur.next;
                }
                return reserve;
            }
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
