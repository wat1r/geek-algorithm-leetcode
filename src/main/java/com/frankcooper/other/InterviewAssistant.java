package com.frankcooper.other;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

public class InterviewAssistant {


    //三个线程顺序打印123 123
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
                        System.out.println();
                        s1.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }


    /**
     * 交替打印A1B2C3-Java多线程实现方式
     */
    static class _5th_1 {

        static int MAX = 100;
        static List<Integer> numList;
        static List<String> charList;

        public static void main(String[] args) {
            int idx = 1;
//  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99]
//["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U"]
            numList = new ArrayList<>();
            charList = new ArrayList<>();
            while (idx < MAX) {
                numList.add(idx);
                charList.add(String.valueOf((char) (idx % 26 == 0 ? 'Z' : idx % 26 + 'A' - 1)));
                idx++;
            }
            process();
//            System.out.println(JSON.toJSONString(numList));
//            System.out.println(JSON.toJSONString(charList));
        }


        /**
         * LockSupport类的核心方法其实就两个：park()和unpark()，其中park()方法用来阻塞当前调用线程，unpark()方法用于唤醒指定线程。
         * 这其实和Object类的wait()和signal()方法有些类似，但是LockSupport的这两种方法从语意上讲比Object类的方法更清晰，而且可以针对指定线程进行阻塞和唤醒。
         * LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒线程的功能，可以把许可看成是一种(0,1)信号量（Semaphore），但与 Semaphore 不同的是，许可的累加上限是1。
         * 初始时，permit为0，当调用unpark()方法时，线程的permit加1，当调用park()方法时，如果permit为0，则调用线程进入阻塞状态。
         */


        /**
         * 打印的结果：A1B2C3D4E5F6G7H8I9J10K11L12M13N14O15P16Q17R18S19T20U21V22W23X24Y25Z26A27B28
         */
        static Thread numThread;
        static Thread charThread;

        public static void process() {
            numThread = new Thread(() -> {
                for (int num : numList) {
                    LockSupport.park();
                    System.out.printf("%d", num);
                    LockSupport.unpark(charThread);
                }
            }, "numThread");
            charThread = new Thread(() -> {
                for (String ch : charList) {
                    System.out.printf("%s", ch);
                    LockSupport.unpark(numThread);
                    LockSupport.park();

                }
            }, "charThread");

            numThread.start();
            charThread.start();
        }
    }

}
