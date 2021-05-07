package com.frankcooper.bank._1001_1500;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import com.frankcooper.struct.pri.IntConsumer;
import org.junit.Assert;

public class _1116 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class ZeroEvenOdd {
            private int n;

            private Semaphore zero = new Semaphore(1);
            private Semaphore even = new Semaphore(0);
            private Semaphore odd = new Semaphore(0);


            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    zero.acquire();
                    printNumber.accept(0);
                    if ((i & 1) == 1) {
                        odd.release();
                    } else {
                        even.release();
                    }
                }

            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (((i & 1) == 0)) {
                        even.acquire();
                        printNumber.accept(i);
                        zero.release();
                    }
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (((i & 1) == 1)) {
                        odd.acquire();
                        printNumber.accept(i);
                        zero.release();
                    }
                }
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * 超时
         */
        @Deprecated
        class ZeroEvenOdd {
            private int n;

            CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

            volatile int sign = 0;

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (sign == 0) {
                        printNumber.accept(0);
                        if (i % 2 == 0) sign = 2;
                        else sign = 1;
                        try {
                            cyclicBarrier.await();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    while (sign != 2) {
                        Thread.yield();
                    }
                    sign = 0;
                    try {
                        cyclicBarrier.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    while (sign != 1) {
                        Thread.yield();
                    }
                    if (i % 2 == 0) {
                        printNumber.accept(i);
                    }
                    sign = 0;
                    try {
                        cyclicBarrier.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        class ZeroEvenOdd {
            private int n;

            CountDownLatch latchZero = new CountDownLatch(0);
            CountDownLatch latchEven = new CountDownLatch(1);//偶数
            CountDownLatch latchOdd = new CountDownLatch(1);


            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    latchZero.await();
                    printNumber.accept(0);
                    latchZero = new CountDownLatch(1);
                    if (i % 2 == 0) {
                        latchEven.countDown();
                    } else {
                        latchOdd.countDown();
                    }
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 2; i <= n; i += 2) {
                    latchEven.await();
                    printNumber.accept(i);
                    latchEven = new CountDownLatch(1);
                    latchZero.countDown();
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i += 2) {
                    latchOdd.await();
                    printNumber.accept(i);
                    latchOdd = new CountDownLatch(1);
                    latchZero.countDown();
                }
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class ZeroEvenOdd {

            private int n;

            private volatile int state;

            private volatile boolean control = true;

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 0; i < n; i++) {
                    while (state != 0) {
                        Thread.yield();
                    }
                    printNumber.accept(0);
                    if (control) {
                        state = 1;
                    } else {
                        state = 2;
                    }
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 2; i <= n; i += 2) {
                    while (state != 2) {
                        Thread.yield();
                    }
                    printNumber.accept(i);
                    control = true;
                    state = 0;
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i += 2) {
                    while (state != 1) {
                        Thread.yield();
                    }
                    printNumber.accept(i);
                    control = false;
                    state = 0;
                }
            }
        }

    }
}
