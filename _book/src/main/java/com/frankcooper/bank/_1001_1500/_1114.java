package com.frankcooper.bank._1001_1500;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;


public class _1114 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class Foo {

            Boolean first = false;
            Boolean second = false;
            final Object lock = new Object();

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                synchronized (lock) {
                    printFirst.run();
                    first = true;
                    lock.notifyAll();
                }
                // printFirst.run() outputs "first". Do not change or remove this line.

            }

            public void second(Runnable printSecond) throws InterruptedException {
                synchronized (lock) {
                    while (!first) {
                        lock.wait();
                    }
                    printSecond.run();
                    second = true;
                    lock.notifyAll();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.

            }

            public void third(Runnable printThird) throws InterruptedException {
                synchronized (lock) {
                    while (!second) {
                        lock.wait();
                    }
                    printThird.run();
                }
                // printThird.run() outputs "third". Do not change or remove this line.

            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

//            Foo foo = new Foo();
//            Thread t1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                   foo.first(new Runnable() {
//                       @Override
//                       public void run() {
//
//                       }
//                   });
//                }
//            });
        }


        static class Foo {

            Semaphore s12 = new Semaphore(0);
            Semaphore s23 = new Semaphore(0);

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                printFirst.run();
                s12.release();//释放后s12的值会变成1
            }

            public void second(Runnable printSecond) throws InterruptedException {
                s12.acquire();//没有会阻塞  当为1的时候，说明线程2可以拿到s12了
                printSecond.run();
                s23.release();//释放后s23的值会变成1
            }

            public void third(Runnable printThird) throws InterruptedException {
                s23.acquire();//0的时候拿不到，1的时候可以拿到
                printThird.run();
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) throws InterruptedException {
//            _3rd handler = new _3rd();
            for (int i = 0; i < 50; i++) {
                CountDownLatch countDownLatch = new CountDownLatch(4);
                run(countDownLatch);
                countDownLatch.await();
            }

        }


        private static void run(CountDownLatch countDownLatch) {
            Foo foo = new Foo();

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        foo.first(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("first");
                                countDownLatch.countDown();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        foo.second(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("second");
                                countDownLatch.countDown();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        foo.third(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("third");
                                countDownLatch.countDown();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t4 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        foo.first(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("first");
                                countDownLatch.countDown();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                printFirst.run();
                latch12.countDown();//唤醒线程2
            }

            public void second(Runnable printSecond) throws InterruptedException {
                latch12.await();//latch12的值为0会执行下面的语句，否则会在此次阻塞
                printSecond.run();
                latch23.countDown();//准备唤醒线程3
            }

            public void third(Runnable printThird) throws InterruptedException {
                latch23.await();//latch23的值为0会执行下面的语句，否则会在此次阻塞
                printThird.run();
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class Foo {
            //阻塞队列         //同步队列,没有容量，进去一个元素，必须等待取出来以后，才能再往里面放一个元素
            BlockingQueue<Integer> block12 = new SynchronousQueue<Integer>();
            BlockingQueue<Integer> block23 = new SynchronousQueue<Integer>();

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                printFirst.run();
                block12.put(1);
            }

            public void second(Runnable printSecond) throws InterruptedException {
                block12.take();//
                printSecond.run();
                block23.put(1);
            }

            public void third(Runnable printThird) throws InterruptedException {
                block23.take();
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
            }
        }
    }

    static class _5th {
        class Foo {

            int num;
            Lock lock;
            //精确的通知和唤醒线程
            Condition condition1, condition2, condition3;

            public Foo() {
                num = 1;
                lock = new ReentrantLock();
                condition1 = lock.newCondition();
                condition2 = lock.newCondition();
                condition3 = lock.newCondition();
            }

            public void first(Runnable printFirst) throws InterruptedException {
                lock.lock();
                try {
                    while (num != 1) {//不是1的时候，阻塞
                        condition1.await();
                    }
                    // printFirst.run() outputs "first". Do not change or remove this line.
                    printFirst.run();
                    num = 2;
                    condition2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            public void second(Runnable printSecond) throws InterruptedException {
                lock.lock();
                try {
                    while (num != 2) {//不是2的时候，阻塞
                        condition2.await();
                    }
                    // printSecond.run() outputs "second". Do not change or remove this line.
                    printSecond.run();
                    num = 3;
                    condition3.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            public void third(Runnable printThird) throws InterruptedException {
                lock.lock();
                try {
                    while (num != 3) {//不是3的时候，阻塞
                        condition3.await();
                    }
                    // printThird.run() outputs "third". Do not change or remove this line.
                    printThird.run();
                    num = 1;
                    condition1.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }


    static class _6th {


        class Foo {

            volatile boolean first = false;
            volatile boolean second = false;

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {

                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                first = true;
            }

            public void second(Runnable printSecond) throws InterruptedException {
                while (!first) {

                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                second = true;
            }

            public void third(Runnable printThird) throws InterruptedException {
                while (!second) {

                }
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
            }
        }
    }


    static class _7th {


        public static void main(String[] args) {

        }


        class Foo {

            public Foo() {

            }

            private int signal = 1;
            private Object obj = new Object();

            public void first(Runnable printFirst) throws InterruptedException {
                synchronized (obj) {
                    while (signal != 1) {
                        obj.wait();
                    }
                    printFirst.run();
                    signal = 2;
                    obj.notifyAll();
                }

            }

            public void second(Runnable printSecond) throws InterruptedException {
                synchronized (obj) {
                    while (signal != 2) {
                        obj.wait();
                    }
                    printSecond.run();
                    signal = 3;
                    obj.notifyAll();
                }

            }

            public void third(Runnable printThird) throws InterruptedException {
                synchronized (obj) {
                    while (signal != 3) {
                        obj.wait();
                    }
                    printThird.run();
                    signal = 1;
                    obj.notifyAll();
                }
            }
        }


    }


    static class _8th {
        class Foo {

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {

                printFirst.run();
            }

            public void second(Runnable printSecond) throws InterruptedException {

                printSecond.run();
            }

            public void third(Runnable printThird) throws InterruptedException {

                printThird.run();
            }
        }
    }

    static class _9th {
        class Foo {

            boolean first = false;
            boolean second = false;
            Object obj = new Object();

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                synchronized (obj) {
                    first = true;
                    printFirst.run();
                    obj.notifyAll();
                }
            }

            public void second(Runnable printSecond) throws InterruptedException {
                synchronized (obj) {
                    while (!first) {
                        obj.wait();
                    }
                    printSecond.run();
                    second = true;
                    obj.notifyAll();
                }

            }

            public void third(Runnable printThird) throws InterruptedException {
                synchronized (obj) {
                    while (!second) {
                        obj.wait();
                    }
                    printThird.run();
                    obj.notifyAll();
                }

            }
        }
    }


    static class _10th {
        class Foo {
            private AtomicInteger counter = new AtomicInteger(0);
            private Map<String, Thread> threads = new HashMap<>();


            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                while (counter.get() != 0) {
                    threads.put("first", Thread.currentThread());
                    LockSupport.park();
                }
                printFirst.run();
                counter.getAndIncrement();
                threads.forEach((k, v) -> LockSupport.unpark(v));
            }

            public void second(Runnable printSecond) throws InterruptedException {
                while (counter.get() != 1) {
                    threads.put("second", Thread.currentThread());
                    LockSupport.park();
                }
                printSecond.run();
                counter.getAndIncrement();
                threads.forEach((k, v) -> LockSupport.unpark(v));
            }

            public void third(Runnable printThird) throws InterruptedException {
                while (counter.get() != 2) {
                    threads.put("third", Thread.currentThread());
                    LockSupport.park();
                }
                printThird.run();
                counter.getAndIncrement();
                threads.forEach((k, v) -> LockSupport.unpark(v));
            }
        }
    }


    static class _11th {
        class Foo {

            Semaphore s12 = new Semaphore(0);
            Semaphore s23 = new Semaphore(0);

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                printFirst.run();
                s12.release();
            }

            public void second(Runnable printSecond) throws InterruptedException {
                s12.acquire();
                printSecond.run();
                s23.release();
            }

            public void third(Runnable printThird) throws InterruptedException {
                s23.acquire();
                printThird.run();
                s12.release();
            }
        }
    }

    static class _13th {
        class Foo {

            CountDownLatch l12 = new CountDownLatch(1);
            CountDownLatch l23 = new CountDownLatch(1);

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                printFirst.run();
                l12.countDown();
            }

            public void second(Runnable printSecond) throws InterruptedException {
                l12.await();
                printSecond.run();
                l23.countDown();
            }

            public void third(Runnable printThird) throws InterruptedException {
                l23.await();
                printThird.run();
                l12.countDown();
            }
        }
    }
}
