package com.frankcooper.bank._1001_2000;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import com.frankcooper.struct.pri.IntConsumer;
import org.junit.Assert;

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

        class FizzBuzz {
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
                    if (i % 3 != 0 && i % 5 != 0) {
                        printNumber.accept(i);
                        number.release();
                    } else if (i % 3 == 0 && i % 5 != 0) {
                        fizz.release();
                    } else if (i % 3 != 0 && i % 5 == 0) {
                        buzz.release();
                    } else {
                        fizzbuzz.release();
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
}
