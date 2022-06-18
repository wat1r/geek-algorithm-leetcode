package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.frankcooper.struct.pri.IntConsumer;
import org.junit.Assert;

import javax.swing.*;

public class _1195 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * 4个线程,只有一个线程能输出值，其他三个线程相当于计数，所有线程中的只要有任意一个线程未走到await其他线程就一直阻塞，等四个线程都走到await后，栅栏释放，继续下一轮，4个一轮
         */
        class FizzBuzz {

            CyclicBarrier cb = new CyclicBarrier(4);

            private int n;

            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 == 0 && i % 5 != 0) {
                        printFizz.run();
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 != 0 && i % 5 == 0) {
                        printBuzz.run();
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 == 0 && i % 5 == 0) {
                        printFizzBuzz.run();
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 != 0 && i % 5 != 0) {
                        printNumber.accept(i);
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class FizzBuzz {
            private int n;

            private Semaphore semaphore = new Semaphore(1);
            private int cur = 1;

            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {
                while (true) {
                    semaphore.acquire(1);
                    try {
                        // 原因就在这里，循环过程中如果打印的字符串个数已经满足要求，那么会使用return来返回，终止该方法的执行。
                        // 但是咱们已经获取了信号量，那么在方法返回前需要释放该信号量，否则会导致其它线程一直等待，整个程序一直不结束。
                        // Java语言中try-finally可以做到这一点，try-finally代码块也是常用的一种释放资源（IO流、数据库连接等）的方式。
                        // 不是程序死循环，而是其它线程在wait，导致无法退出。
                        if (cur > n) return;
                        if (cur % 3 == 0 && cur % 5 != 0) {
                            cur++;
                            printFizz.run();
                        }
                    } finally {
                        semaphore.release(1);
                    }
                }
            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {
                while (true) {
                    semaphore.acquire(1);
                    try {
                        if (cur > n) return;
                        if (cur % 3 != 0 && cur % 5 == 0) {
                            cur++;
                            printBuzz.run();
                        }
                    } finally {
                        semaphore.release(1);
                    }
                }
            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                while (true) {
                    semaphore.acquire(1);
                    try {
                        if (cur > n) return;
                        if (cur % 3 == 0 && cur % 5 == 0) {
                            cur++;
                            printFizzBuzz.run();
                        }
                    } finally {
                        semaphore.release(1);
                    }
                }
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {
                while (true) {
                    semaphore.acquire(1);
                    try {
                        if (cur > n) return;
                        if (cur % 3 != 0 && cur % 5 != 0) {
                            printNumber.accept(cur);
                            cur++;
                        }
                    } finally {
                        semaphore.release(1);
                    }
                }
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        static class FizzBuzz {
            private int n;

            private Semaphore number = new Semaphore(1);
            private Semaphore fizz = new Semaphore(0);
            private Semaphore buzz = new Semaphore(0);
            private Semaphore fizzbuzz = new Semaphore(0);


            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 == 0 && i % 5 != 0) {
                        fizz.acquire();
                        printFizz.run();
                        number.release();
                    }
                }
            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 != 0 && i % 5 == 0) {
                        buzz.acquire();
                        printBuzz.run();
                        number.release();
                    }
                }
            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 == 0 && i % 5 == 0) {
                        fizzbuzz.acquire();
                        printFizzBuzz.run();
                        number.release();
                    }
                }
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    number.acquire();
                    if (i % 3 != 0 && i % 5 != 0) {//开始打印
                        printNumber.accept(i);
                        number.release();
                    } else if (i % 3 == 0 && i % 5 != 0) {//fizz开始打印
                        fizz.release();
                    } else if (i % 3 != 0 && i % 5 == 0) {//buzz开始打印
                        buzz.release();
                    } else {
                        fizzbuzz.release();//fizzbuzz开始打印
                    }
                }
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }


    static class _5th_1 {

        public static void main(String[] args) {
            Runnable printFizz = () -> {
                System.out.printf("%s", "fizz");
            };
            Runnable printBuzz = () -> {
                System.out.printf("%s", "buzz");
            };
            Runnable printFizzBuzz = () -> {
                System.out.printf("%s", "fizzbuzz");
            };
            IntConsumer intConsumer = new IntConsumer();
            FizzBuzz fb = new FizzBuzz(15);
            new Thread(() -> {
                try {
                    fb.fizz(printFizz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    fb.buzz(printBuzz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    fb.fizzbuzz(printFizzBuzz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    fb.number(intConsumer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        static class FizzBuzz {
            private int n;
            private CyclicBarrier cb = new CyclicBarrier(4);

            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 == 0 && i % 5 != 0) {
                        printFizz.run();
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 != 0 && i % 5 == 0) {
                        printBuzz.run();
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 == 0 && i % 5 == 0) {
                        printFizzBuzz.run();
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    if (i % 3 != 0 && i % 5 != 0) {
                        printNumber.accept(i);
                    }
                    try {
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class _5th_2 {
        class FizzBuzz {
            private int n;

            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {

            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {

            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {

            }
        }
    }


    static class _5th_3 {
        class FizzBuzz {
            private int n;
            private volatile int state = 0;

            public FizzBuzz(int n) {
                this.n = n;
            }

            public void fizz(Runnable printFizz) throws InterruptedException {
                for (int i = 3; i <= n; i += 3) {   //只输出3的倍数(不包含15的倍数)
                    if (i % 15 == 0) continue;   //15的倍数不处理，交给fizzbuzz()方法处理
                    while (state != 3)
                        Thread.sleep(1);

                    printFizz.run();
                    state = 0;
                }
            }

            public void buzz(Runnable printBuzz) throws InterruptedException {
                for (int i = 5; i <= n; i += 5) {   //只输出5的倍数(不包含15的倍数)
                    if (i % 15 == 0)    //15的倍数不处理，交给fizzbuzz()方法处理
                        continue;
                    while (state != 5)
                        Thread.yield();
                    printBuzz.run();
                    state = 0;
                }
            }

            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                for (int i = 15; i <= n; i += 15) {   //只输出15的倍数
                    while (state != 15)
                        Thread.yield();
                    printFizzBuzz.run();
                    state = 0;
                }
            }

            public void number(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; ++i) {
                    while (state != 0)
                        Thread.yield();
                    if (i % 3 != 0 && i % 5 != 0)
                        printNumber.accept(i);
                    else {
                        if (i % 15 == 0)
                            state = 15;    //交给fizzbuzz()方法处理
                        else if (i % 5 == 0)
                            state = 5;    //交给buzz()方法处理
                        else
                            state = 3;    //交给fizz()方法处理
                    }
                }
            }
        }
    }


    static class _5th_4 {

        public static void main(String[] args) {
            Runnable printFizz = () -> {
                System.out.printf("%s", "fizz");
            };
            Runnable printBuzz = () -> {
                System.out.printf("%s", "buzz");
            };
            Runnable printFizzBuzz = () -> {
                System.out.printf("%s", "fizzbuzz");
            };
            IntConsumer intConsumer = new IntConsumer();
            _5th_1.FizzBuzz fb = new _5th_1.FizzBuzz(15);
            new Thread(() -> {
                try {
                    fb.fizz(printFizz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    fb.buzz(printBuzz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    fb.fizzbuzz(printFizzBuzz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    fb.number(intConsumer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        static class FizzBuzz {
            private int n;
            private ReentrantLock lock = new ReentrantLock();
            int state = 0;
            private Condition condition = lock.newCondition();

            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {
                for (int i = 3; i <= n; i += 3) {
                    try {
                        if (i % 3 == 0 && i % 5 == 0) continue;
                        lock.lock();
                        while (state != 3) {
                            condition.await();
                        }
                        printFizz.run();
                        state = 0;
                        condition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {
                for (int i = 5; i <= n; i += 5) {
                    try {
                        if (i % 3 == 0 && i % 5 == 0) continue;
                        lock.lock();
                        while (state != 5) {
                            condition.await();
                        }
                        printBuzz.run();
                        state = 0;
                        condition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                for (int i = 15; i <= n; i += 15) {
                    try {
                        lock.lock();
                        while (state != 15) {
                            condition.await();
                        }
                        printFizzBuzz.run();
                        state = 0;
                        condition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i++) {
                    try {
                        lock.lock();
                        while (state != 0) {
                            condition.await();
                        }
                        if (i % 3 != 0 && i % 5 != 0) {
                            printNumber.accept(i);
                        } else {
                            if (i % 3 == 0 && i % 5 == 0) state = 15;
                            else if (i % 3 == 0) state = 3;
                            else if (i % 5 == 0) state = 5;
                            condition.signalAll();
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }


    static class _5th_5 {
        class FizzBuzz {
            private int n;

            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {

            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {

            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {

            }
        }
    }
}
