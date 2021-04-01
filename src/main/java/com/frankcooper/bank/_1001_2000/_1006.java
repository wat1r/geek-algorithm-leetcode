package com.frankcooper.bank._1001_2000;

import java.util.*;

import org.junit.Assert;

public class _1006 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(handler.clumsy(4), 7);
            Assert.assertEquals(handler.clumsy(10), 12);
            Assert.assertEquals(handler.clumsy(1), 1);
            Assert.assertEquals(handler.clumsy(9999), 9998);
            Assert.assertEquals(handler.clumsy(5), 7);

        }

        public int clumsy(int N) {
            if (N == 1) return 1;
            Map<Integer, Character> symbols = new HashMap<Integer, Character>() {{
                put(0, '*');
                put(1, '/');
                put(2, '+');
                put(3, '-');

            }};
            int i = 0;
            Stack<Integer> stk = new Stack<>();
            int sub = 0;
            while (N > 0) {
                if (sub == 0) {
                    sub = N;
                    N--;
                }
                if (N < 0) break;
                char ch = symbols.get(i % 4);
                if (ch == '-') {
                    sub = -sub;
                } else if (ch == '+') {
                    stk.push(sub);
                    sub = 0;
                    stk.push(N--);
                } else {
                    if (ch == '*') sub *= N;
                    else if (ch == '/') sub /= N;
                    N--;
                }
                i++;
            }
            if (sub != 0) stk.push(sub);
            int res = 0;
            while (!stk.isEmpty()) res += stk.pop();
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertEquals(handler.clumsy(4), 7);
            Assert.assertEquals(handler.clumsy(10), 12);
            Assert.assertEquals(handler.clumsy(1), 1);
            Assert.assertEquals(handler.clumsy(9999), 9998);
            Assert.assertEquals(handler.clumsy(5), 7);
        }

        public int clumsy(int N) {
            Deque<Integer> stk = new LinkedList<>();
            stk.push(N--);
            int i = 0;
            while (N > 0) {
                if (i % 4 == 0) stk.push(stk.pop() * N);
                else if (i % 4 == 1) stk.push(stk.pop() / N);
                else if (i % 4 == 2) stk.push(N);
                else if (i % 4 == 3) stk.push(-N);
                i++;
                N--;
            }
            int res = 0;
            while (!stk.isEmpty()) res += stk.pop();
            return res;
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
