package com.frankcooper.bank._1001_1500;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import com.frankcooper.struct.pri.IntConsumer;

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
                    latchZero.countDown();//
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
                    while (state != 2) {//当state不为2的时候，为就绪状态
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

    static class _5th_1 {
        class ZeroEvenOdd {
            private int n;
            private Semaphore zeroSema = new Semaphore(1);
            private Semaphore oddSema = new Semaphore(0);//奇数
            private Semaphore evenSema = new Semaphore(0);//偶数

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    zeroSema.acquire();
                    printNumber.accept(0);
                    if ((i & 1) == 1) {//奇数
                        oddSema.release();
                    } else {
                        evenSema.release();
                    }
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if ((i & 1) == 0) {//偶数 打印偶数 并释放zero的线程
                        evenSema.acquire();
                        printNumber.accept(i);
                        zeroSema.release();
                    }
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if ((i & 1) == 1) {//奇数，打印奇数，并释放zero的线程
                        oddSema.acquire();
                        printNumber.accept(i);
                        zeroSema.release();
                    }
                }
            }
        }
    }


    static class _5th_2 {
        class ZeroEvenOdd {
            private int n;

            private CountDownLatch zeroLatch = new CountDownLatch(0);
            private CountDownLatch evenLatch = new CountDownLatch(1);//偶数
            private CountDownLatch oddLatch = new CountDownLatch(1);//奇数

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    zeroLatch.await();
                    printNumber.accept(0);//打印0
                    zeroLatch = new CountDownLatch(1);
                    if ((i & 1) == 1) oddLatch.countDown();//如果是奇数，就打印奇数
                    else evenLatch.countDown();

                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if ((i & 1) == 0) {
                        evenLatch.await();//开始打印偶数
                        printNumber.accept(i);
                        evenLatch = new CountDownLatch(1);
                        zeroLatch.countDown();//是否zero线程
                    }
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if ((i & 1) == 1) {
                        oddLatch.await();//开始打印奇数
                        printNumber.accept(i);
                        oddLatch = new CountDownLatch(1);
                        zeroLatch.countDown();//是否zero线程
                    }
                }
            }
        }
    }


    static class _5th_3 {
        class ZeroEvenOdd {
            private int n;
            private Map<String, Thread> map = new ConcurrentHashMap<>();
            volatile int state = 0;//0 打印 0 ， 1 打印奇数， 2 打印偶数


            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            public void zero(IntConsumer printNumber) throws InterruptedException {
                map.put("zero", Thread.currentThread());
                for (int i = 1; i <= n; i++) {
                    while (state != 0) {
                        LockSupport.park();
                    }
                    printNumber.accept(0);
                    if ((i & 1) == 0) {//偶数
                        state = 2;
                    } else {
                        state = 1;
                    }
                    map.forEach((k, v) -> LockSupport.unpark(v));//通知其他两个线程
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                map.put("even", Thread.currentThread());
                for (int i = 2; i <= n; i += 2) {
                    while (state != 1) {//当为2的时候，一直在这里阻塞着
                        LockSupport.park();
                    }
                    printNumber.accept(i);
                    state = 0;
                    LockSupport.unpark(map.get("zero"));//通知zero线程
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                map.put("odd", Thread.currentThread());
                for (int i = 1; i <= n; i += 2) {
                    while (state != 2) {
                        LockSupport.park();
                    }
                    printNumber.accept(i);
                    state = 0;
                    LockSupport.unpark(map.get("zero"));
                }
            }
        }
    }

    static class _5th_4 {
        class ZeroEvenOdd {
            private int n;
            private ReentrantLock lock = new ReentrantLock();
            private Condition zeroCon = lock.newCondition();
            private Condition evenCon = lock.newCondition();//偶数
            private Condition oddCon = lock.newCondition();//奇数

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            public void zero(IntConsumer printNumber) throws InterruptedException {
                try {
                    lock.lock();
                    for (int i = 0; i < n; i++) {
                        printNumber.accept(0);
                        if ((i & 1) == 0)//偶数
                        {
                            evenCon.signal();
                        } else {
                            oddCon.signal();
                        }
                        zeroCon.await();
                    }
                } finally {
                    lock.unlock();
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                try {
                    lock.lock();
                    for (int i = 2; i <= n; i += 2) {
                        evenCon.await();
                        printNumber.accept(i);
                        zeroCon.signal();
                    }
                } finally {
                    lock.unlock();
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                try {
                    lock.lock();
                    for (int i = 1; i <= n; i += 2) {
                        oddCon.await();
                        printNumber.accept(i);
                        zeroCon.signal();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    static class _5th_5 {
        class ZeroEvenOdd {
            private int n;
            private AtomicInteger ai = new AtomicInteger(0);

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 0; i < n; i++) {
                    while (ai.get() != 0 && ai.get() != 2) {
                        Thread.yield();
                    }
                    printNumber.accept(0);
                    ai.incrementAndGet();
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 2; i <= n; i += 2) {
                    while (ai.get() != 3) {
                        Thread.yield();
                    }
                    printNumber.accept(i);
                    ai.set(0);
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    while (ai.get() != 1) {
                        Thread.yield();
                    }
                    printNumber.accept(i);
                    ai.set(2);
                }
            }
        }
    }


    static class _5th_6 {
        class ZeroEvenOdd {
            private int n;

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            public void zero(IntConsumer printNumber) throws InterruptedException {

            }

            public void even(IntConsumer printNumber) throws InterruptedException {

            }

            public void odd(IntConsumer printNumber) throws InterruptedException {

            }
        }
    }


    static class _5th_7 {
        class ZeroEvenOdd {
            private int n;

            private volatile int start = 1;

            private volatile int state;
            private Lock lock = new ReentrantLock();
            private Condition zero = lock.newCondition();
            private Condition even = lock.newCondition();
            private Condition odd = lock.newCondition();

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                lock.lock();
                try {
                    while (start <= n) {
                        if (state != 0) {
                            zero.await();
                        }
                        printNumber.accept(0);
                        if (start % 2 == 0) {
                            state = 2;
                            even.signal();
                        } else {
                            state = 1;
                            odd.signal();
                        }
                        zero.await();
                    }
                    odd.signal();
                    even.signal();
                } finally {
                    lock.unlock();
                }
            }

            //偶数
            public void even(IntConsumer printNumber) throws InterruptedException {
                lock.lock();
                try {
                    while (start <= n) {
                        if (state != 2) {
                            even.await();
                        } else {
                            printNumber.accept(start++);
                            state = 0;
                            zero.signal();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }

            //基数
            public void odd(IntConsumer printNumber) throws InterruptedException {
                lock.lock();
                try {
                    while (start <= n) {
                        if (state != 1) {
                            odd.await();
                        } else {
                            printNumber.accept(start++);
                            state = 0;
                            zero.signal();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

    }


    static class _6th {
        public static void main(String[] args) {
            int n = 10;
            while (n-- > 0) {
                print();
                System.out.println();
            }

        }

        public static void print() {
            ZeroEvenOdd zeo = new ZeroEvenOdd(6);
            new Thread(() -> {
                try {
                    zeo.zero(new IntConsumer() {
                        public void accept(int value) {
                            System.out.print(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    zeo.odd(new IntConsumer() {
                        public void accept(int value) {
                            System.out.print(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    zeo.even(new IntConsumer() {
                        public void accept(int value) {
                            System.out.print(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        static class ZeroEvenOdd {
            private int n;
            private Semaphore zero = new Semaphore(1);
            private Semaphore odd = new Semaphore(0);
            private Semaphore even = new Semaphore(0);
            private boolean flag = true;
            private AtomicInteger atomicInteger = new AtomicInteger(1);

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                while (true) {
                    zero.acquire();
                    if (atomicInteger.intValue() > n) {
//                        System.exit(0);
                        break;
                    }
                    printNumber.accept(0);
                    if (flag) {
                        odd.release();
                    } else {
                        even.release();
                    }
                    flag = !flag;
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                while (true) {
                    even.acquire();
                    int intValue = atomicInteger.intValue();
                    if (intValue > n) break;
                    printNumber.accept(intValue);
                    atomicInteger.incrementAndGet();
                    zero.release();
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                while (true) {
                    odd.acquire();
                    int intValue = atomicInteger.intValue();
                    if (intValue > n) break;
                    printNumber.accept(intValue);
                    atomicInteger.incrementAndGet();
                    zero.release();
                }
            }
        }
    }
}
