package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;

public class _1117 {

    static class _1st {
        public static void main(String[] args) {
            H2O h2O = new H2O();
            int n = 5;
            new Thread(() -> {
                try {
                    for (int i = 0; i < n; i++) {
                        h2O.hydrogen(() -> System.out.print("H"));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    for (int i = 0; i < n; i++) {
                        h2O.hydrogen(() -> System.out.print("H"));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    for (int i = 0; i < n; i++) {
                        h2O.oxygen(() -> System.out.print("O"));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        static class H2O {

            private Semaphore hSema = new Semaphore(2);
            private Semaphore oSema = new Semaphore(0);

            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                hSema.acquire();
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                releaseHydrogen.run();
                oSema.release();
                //release会让permits+1，两次o.release()后，o.acquire(2)才能通过
                System.out.println(oSema.availablePermits());
            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                oSema.acquire(2);
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseOxygen.run();
                hSema.release(2);
            }
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class H2O {


            private Semaphore hSema = new Semaphore(2);
            private Semaphore oSema = new Semaphore(0);


            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                hSema.acquire();
                releaseHydrogen.run();
                oSema.release();
            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                oSema.acquire(2);
                releaseOxygen.run();
                hSema.release(2);
            }
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();


        }

        class H2O {

            private int oCnt = 0;
            private int hCnt = 0;
            private ReentrantLock lock = new ReentrantLock();
            private Condition con = lock.newCondition();

            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                lock.lock();
                try {
                    while (hCnt == 2) {
                        con.await();
                    }
                    hCnt++;
                    if (hCnt == 2 && oCnt == 1) {
                        hCnt = 0;
                        oCnt = 0;
                    }
                    releaseHydrogen.run();
                    con.signalAll();
                } finally {
                    lock.unlock();
                }

            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                lock.lock();
                try {
                    while (oCnt == 1) {
                        con.await();
                    }
                    oCnt++;
                    if (hCnt == 2 && oCnt == 1) {
                        hCnt = 0;
                        oCnt = 0;
                    }
                    releaseOxygen.run();
                    con.signalAll();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class H2O {
            private Semaphore hSema = new Semaphore(2);
            private Semaphore oSema = new Semaphore(1);
            private CyclicBarrier cb = new CyclicBarrier(3);

            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                hSema.acquire();
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                releaseHydrogen.run();
                hSema.release();
            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                oSema.acquire();
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                releaseOxygen.run();
                oSema.release();
            }
        }
    }

    static class _5th_1 {
        class H2O {

            private volatile int state = 0;
            private Object obj = new Object();

            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                synchronized (obj) {
                    while (state == 2) {
                        obj.wait();
                    }
                    state++;
                    releaseHydrogen.run();
                    obj.notifyAll();
                }

            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                synchronized (obj) {
                    while (state != 2) {
                        obj.wait();
                    }
                    state = 0;
                    releaseOxygen.run();
                    obj.notifyAll();
                }

            }
        }
    }


    static class _5th_2 {
        class H2O {


            private Semaphore hSema = new Semaphore(2);
            private Semaphore oSema = new Semaphore(0);

            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                hSema.acquire(1);
                releaseHydrogen.run();
                oSema.release(1);//执行一次H就释放一次O，下面的O到达2个触发
            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                oSema.acquire(2);//需要有两个H后才 执行O
                releaseOxygen.run();
                hSema.release(2);//释放2个
            }
        }
    }


    static class _5th_3 {
        class H2O {

            private int cnt = 0;
            private BlockingQueue<Integer> hQ = new LinkedBlockingDeque<>(2);
            private BlockingQueue<Integer> oQ = new LinkedBlockingDeque<>(1);

            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                hQ.put(1);
                releaseHydrogen.run();
                cnt++;
                if (cnt == 3) {
                    cnt = 0;
                    hQ.clear();
                    oQ.clear();
                }
            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                oQ.put(1);
                releaseOxygen.run();
                cnt++;
                if (cnt == 3) {
                    cnt = 0;
                    hQ.clear();
                    oQ.clear();
                }
            }
        }
    }


    static class _5th_4 {
        class H2O {


            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                releaseHydrogen.run();
            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                releaseOxygen.run();
            }
        }
    }
}
