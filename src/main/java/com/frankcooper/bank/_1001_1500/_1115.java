package com.frankcooper.bank._1001_1500;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;

public class _1115 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            FooBar fooBar = new FooBar(2);
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fooBar.foo(new Runnable() {
                            @Override
                            public void run() {
                                System.out.print("foo");
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
                        fooBar.bar(new Runnable() {
                            @Override
                            public void run() {
                                System.out.print("bar");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            t2.start();

        }

        static class FooBar {
            private int n;

            Semaphore foo = new Semaphore(1);
            Semaphore bar = new Semaphore(0);


            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    foo.acquire();
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    bar.release();
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    bar.acquire();
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    foo.release();
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class FooBar {
            private int n;
            CyclicBarrier cb = new CyclicBarrier(2);
            volatile boolean fooin = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    while (!fooin) {

                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    fooin = false;
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    fooin = true;

                }
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        class FooBar {
            private int n;

            volatile boolean f = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; ) {
                    if (f) {
                        printFoo.run();
                        f = false;
                        i++;
                    } else {
                        Thread.yield();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.

                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; ) {
                    if (!f) {
                        // printBar.run() outputs "bar". Do not change or remove this line.
                        printBar.run();
                        f = true;
                        i++;
                    } else {
                        Thread.yield();
                    }

                }
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class FooBar {
            private int n;

            ReentrantLock lock = new ReentrantLock(true);
            volatile boolean foo = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; ) {
                    lock.lock();
                    try {
                        if (foo) {
                            printFoo.run();
                            i++;
                            foo = false;
                        }
                    } finally {
                        lock.unlock();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.

                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; ) {
                    lock.lock();
                    try {
                        if (!foo) {
                            printBar.run();
                            i++;
                            foo = true;
                        }
                    } finally {
                        lock.unlock();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.

                }
            }
        }
    }
}
