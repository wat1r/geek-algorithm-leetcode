package com.frankcooper.other;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AssistProcessor {


    /**
     * 多线程按顺序打印 123 123 123
     */
    static class _1st {
        public static void main(String[] args) throws InterruptedException {
            _1st handler = new _1st();
            for (int i = 0; i < 50; i++) {
                CountDownLatch countDownLatch = new CountDownLatch(3);
                run(countDownLatch);
                countDownLatch.await();
                System.out.println();
            }


        }


        private static void run(CountDownLatch countDownLatch) {
            Foo foo = new Foo();
            Thread t1 = new Thread(() -> {
                try {
                    foo.first(() -> {
                        System.out.print("1");
                        countDownLatch.countDown();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    foo.second(() -> {
                        System.out.print("2");
                        countDownLatch.countDown();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t3 = new Thread(() -> {
                try {
                    foo.third(() -> {
                        System.out.print("3");
                        countDownLatch.countDown();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t3.start();
            t2.start();
            t1.start();
        }


        static class Foo {
            CountDownLatch latch12 = new CountDownLatch(1);
            CountDownLatch latch23 = new CountDownLatch(1);

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                printFirst.run();
                latch12.countDown();
            }

            public void second(Runnable printSecond) throws InterruptedException {
                latch12.await();
                printSecond.run();
                latch23.countDown();
            }

            public void third(Runnable printThird) throws InterruptedException {
                latch23.await();
                printThird.run();
            }
        }


    }


    /**
     * 多线程按顺序打印 1231 1231 1231
     */
    static class _2nd {
        public static void main(String[] args) throws InterruptedException {
            _2nd handler = new _2nd();
            for (int i = 0; i < 50; i++) {
                CountDownLatch countDownLatch = new CountDownLatch(4);
                run(countDownLatch);
                countDownLatch.await();
                System.out.println();
            }


        }


        private static void run(CountDownLatch countDownLatch) {
            Foo foo = new Foo();
            Thread t1 = new Thread(() -> {
                try {
                    foo.first(() -> {
                        System.out.print("1");
                        countDownLatch.countDown();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    foo.second(() -> {
                        System.out.print("2");
                        countDownLatch.countDown();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t3 = new Thread(() -> {
                try {
                    foo.third(() -> {
                        System.out.print("3");
                        countDownLatch.countDown();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t4 = new Thread(() -> {
                try {
                    foo.fourth(() -> {
                        System.out.print("1");
                        countDownLatch.countDown();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t4.start();
            t3.start();
            t2.start();
            t1.start();
        }


        static class Foo {
            CountDownLatch latch12 = new CountDownLatch(1);
            CountDownLatch latch23 = new CountDownLatch(1);
            CountDownLatch latch34 = new CountDownLatch(1);

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                printFirst.run();
                latch12.countDown();
            }

            public void second(Runnable printSecond) throws InterruptedException {
                latch12.await();
                printSecond.run();
                latch23.countDown();
            }

            public void third(Runnable printThird) throws InterruptedException {
                latch23.await();
                printThird.run();
                latch34.countDown();
            }

            public void fourth(Runnable printFourth) throws InterruptedException {
                latch34.await();
                printFourth.run();
            }

        }
    }


    /**
     * 顺序打印123 123
     */


    static class _3rd {
        public static void main(String[] args) throws InterruptedException {
            FIFOMutex mutex = new FIFOMutex();
            MyThread t1 = new MyThread("1", mutex);
            MyThread t2 = new MyThread("2", mutex);
            MyThread t3 = new MyThread("3", mutex);

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();
            assert MyThread.count == 50;
            System.out.print("Finished");
        }

        public static class FIFOMutex {
            private final AtomicBoolean locked = new AtomicBoolean(false);
            private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

            public void lock() {
                Thread current = Thread.currentThread();
                waiters.add(current);
                // 如果当前线程不在队首，或锁已被占用，则当前线程阻塞
                // NOTE：这个判断的意图其实就是：锁必须由队首元素拿到
                while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
                    LockSupport.park(this);
                }
                waiters.remove(); // 删除队首元素
            }

            public void unlock() {
                locked.set(false);
                LockSupport.unpark(waiters.peek());
            }
        }

        static class MyThread extends Thread {
            private String name;
            private FIFOMutex mutex;
            public static int count;

            public MyThread(String name, FIFOMutex mutex) {
                this.name = name;
                this.mutex = mutex;
            }

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    mutex.lock();
                    count++;
                    System.out.println(name);
                    mutex.unlock();
                }
            }
        }
    }

    /**
     * 顺序打印1231 1231
     */
    static class _4th {
        public static void main(String[] args) throws InterruptedException {
            _4th handler = new _4th();
            for (int i = 0; i < 50; i++) {
                Thread t1 = new Thread(() -> System.out.printf("%d", 1));
                t1.start();
                t1.join();
                Thread t2 = new Thread(() -> System.out.printf("%d", 2));
                t2.start();
                t2.join();
                Thread t3 = new Thread(() -> System.out.printf("%d", 3));
                t3.start();
                t3.join();
                Thread t4 = new Thread(() -> System.out.printf("%d", 1));
                t4.start();
                t4.join();
                System.out.println();
            }
        }
    }


    static class _4th_1 {


        private static Integer num = 1;

        static class Thread1 implements Runnable {
            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        if (num % 3 == 1) {
                            System.out.println("Thread1: 1");
                            num++;
                            if (num > 49) break;
                        }
                    }
                }
            }
        }

        static class Thread2 implements Runnable {
            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        if (num % 3 == 2) {
                            System.out.println("Thread2: 2");
                            num++;
                            if (num > 50) break;
                        }
                    }
                }
            }
        }

        static class Thread3 implements Runnable {
            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        if (num % 3 == 0) {
                            System.out.println("Thread3: 3");
                            num++;
                            if (num > 50) break;
                        }
                    }
                }
            }
        }

        public static void main(String[] args) {
            Thread thread1 = new Thread(new Thread1());
            Thread thread2 = new Thread(new Thread2());
            Thread thread3 = new Thread(new Thread3());
            thread1.start();
            thread2.start();
            thread3.start();
        }
    }


    static class _4th_2 {

        /**
         * 定义了_1 _2 _3三个对象锁，分别对应的是t1 t2 t3 三个线程
         * t1线程申请了_3 _1 的对象锁，打印操作结束后，按顺序释放_3 _1 对象锁，通过notify唤醒t2
         * t2线程申请了_1 _2 的对象锁，打印操作结束后，按顺序释放_2 _1 对象锁，通过notify唤醒t3
         * t3线程申请了_2 _3 的对象锁，打印操作结束后，按顺序释放_2 _3 对象锁，通过notify唤醒t1
         */
        public static void main(String[] args) throws InterruptedException {
            Object _1 = new Object();
            Object _2 = new Object();
            Object _3 = new Object();
            MyThread t1 = new MyThread("1", _3, _1);
            MyThread t2 = new MyThread("2", _1, _2);
            MyThread t3 = new MyThread("3", _2, _3);

            new Thread(t1).start();
            Thread.sleep(100);//保证初始123线程的启动顺序
            new Thread(t2).start();
            Thread.sleep(100);
            new Thread(t3).start();
            Thread.sleep(100);

        }

        //        https://www.jianshu.com/p/f79fa5aafb44
        static class MyThread implements Runnable {

            private String name;
            private Object prev;
            private Object cur;

            public MyThread(String name, Object prev, Object cur) {
                this.name = name;
                this.prev = prev;
                this.cur = cur;
            }

            @Override
            public void run() {
                int cnt = 50;
                while (cnt > 0) {
                    synchronized (prev) {//获取prev的锁
                        synchronized (cur) {//获取cur的锁
                            System.out.printf("%s", name);//打印
                            cnt--;
                            cur.notifyAll();//唤醒其他线程竞争cur锁，注意此时cur锁并未立即释放。
                        }
                        //执行完cur的同步块，cur的锁会释放
                        try {
                            //注意这里的操作，不然线程结束不了，最后一次无法唤醒，cnt=0的时候无法进入while块
                            //cnt==0时为最后一次打印，notifyAll操作释放对象锁
                            if (cnt == 0) {
                                prev.notifyAll();
                            } else {
                                prev.wait();//立即释放 prev锁，当前线程休眠，等待唤醒
                            }
                            //JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    static class _4th_3 {

        public static void main(String[] args) {
            new Thread(new Thread1()).start();
            new Thread(new Thread2()).start();
            new Thread(new Thread3()).start();
        }

        private static ReentrantLock lock = new ReentrantLock();
        private static int signal = 0;//控制打印

        static class Thread1 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; ) {
                    try {
                        lock.lock();
                        while (signal % 3 == 0) {
                            System.out.printf("%s", "1");
                            signal++;
                            i++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        static class Thread2 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; ) {
                    try {
                        lock.lock();
                        while (signal % 3 == 1) {
                            System.out.printf("%s", "2");
                            signal++;
                            i++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        static class Thread3 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; ) {
                    try {
                        lock.lock();
                        while (signal % 3 == 2) {
                            System.out.printf("%s", "3");
                            signal++;
                            i++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

    }


    static class _4th_4 {


        public static void main(String[] args) {
            new Thread1().start();
            new Thread2().start();
            new Thread3().start();
        }

        private static Semaphore s1 = new Semaphore(1);
        private static Semaphore s2 = new Semaphore(0);
        private static Semaphore s3 = new Semaphore(0);

        static class Thread1 extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        s1.acquire();
                        System.out.printf("%s", "1");
                        s2.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }


        static class Thread2 extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        s2.acquire();
                        System.out.printf("%s", "2");
                        s3.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }


        static class Thread3 extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        s3.acquire();
                        System.out.printf("%s", "3");
                        s1.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }


    static class _4th_5 {
        public static void main(String[] args) {
            new Thread(new Thread1()).start();
            new Thread(new Thread2()).start();
            new Thread(new Thread3()).start();
        }

        private static ReentrantLock lock = new ReentrantLock();
        private static int signal = 0;//控制打印
        private static Condition c1 = lock.newCondition();
        private static Condition c2 = lock.newCondition();
        private static Condition c3 = lock.newCondition();

        static class Thread1 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        lock.lock();
                        //signal % 3 != 0 这个条件不满足，当前线程会一直阻塞
                        while (signal % 3 != 0) {
                            c1.await();//线程t1释放锁
                        }
                        System.out.printf("%s", "1");
                        signal++;
                        c2.signal();//t1 线程执行完，唤醒t2
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        static class Thread2 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        lock.lock();
                        while (signal % 3 != 1) {
                            c2.await();
                        }
                        System.out.printf("%s", "2");
                        signal++;
                        c3.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }


        static class Thread3 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        lock.lock();
                        while (signal % 3 != 2) {
                            c3.await();
                        }
                        System.out.printf("%s", "3");
                        signal++;
                        c1.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }




}
