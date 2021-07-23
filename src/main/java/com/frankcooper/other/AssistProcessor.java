package com.frankcooper.other;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

import com.frankcooper.bank._1001_1500._1114;
import javafx.beans.binding.ObjectExpression;
import org.junit.Assert;

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

        public static void main(String[] args) throws InterruptedException {
            Object a = new Object();
            Object b = new Object();
            Object c = new Object();
            MyThread t1 = new MyThread("1", c, a);
            MyThread t2 = new MyThread("2", a, b);
            MyThread t3 = new MyThread("3", b, c);

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
                int cnt = 10;
                while (cnt > 0) {
                    synchronized (prev) {//获取prev的锁
                        synchronized (cur) {//获取cur的锁
                            System.out.printf("%s", name);//打印
                            cnt--;
                            cur.notifyAll();//唤醒其他线程竞争cur锁，注意此时cur锁并未立即释放。
                        }
                        //执行完cur的同步块，cur的锁会释放
                        try {
                            prev.wait();//立即释放 prev锁，当前线程休眠，等待唤醒
                            //JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
