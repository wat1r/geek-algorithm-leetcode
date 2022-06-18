package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class _1115 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            FooBar fooBar = new FooBar(3);
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fooBar.foo(new Runnable() {
                            @Override
                            public void run() {
                                System.out.print("foo\n");
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
                                System.out.print("bar\n");
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
                        fooBar.go(new Runnable() {
                            @Override
                            public void run() {
                                System.out.print("go\n");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            t1.start();
            t2.start();
            t3.start();

        }

        static class FooBar {
            private int n;

            Semaphore foo = new Semaphore(0);
            Semaphore bar = new Semaphore(1);
            Semaphore go = new Semaphore(2);


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
                    go.release();
                }
            }

            public void go(Runnable printGo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    go.acquire();
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printGo.run();
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

    /**
     * TLE
     */
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


    static class _5th_1 {
        class FooBar {
            private int n;

            private Semaphore fooSema = new Semaphore(1);
            private Semaphore barSema = new Semaphore(0);

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    fooSema.acquire();//值为1的时候，能拿到，执行下面的操作
                    printFoo.run();
                    barSema.release();//释放许可给barSema这个信号量 barSema 的值+1
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    barSema.acquire();//值为1的时候，能拿到，执行下面的操作
                    printBar.run();
                    fooSema.release();//释放许可给fooSema这个信号量 fooSema 的值+1
                }
            }
        }
    }


    static class _5th_2 {

        //input
        public static void main(String[] args) {
            FooBar fooBar = new FooBar(10);//打印10次foo bar
            Runnable printFoo = () -> {
                System.out.printf("%s\n", "foo");
            };
            Runnable printBar = () -> {
                System.out.printf("%s\n", "bar");
            };
            Thread fooThread = new Thread(() -> {
                try {
                    fooBar.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread barThread = new Thread(() -> {
                try {
                    fooBar.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            fooThread.start();
            barThread.start();

        }

        static class FooBar {
            private int n;

            private CyclicBarrier cb = new CyclicBarrier(2);
            volatile boolean fooExec = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    while (!fooExec) {
                        //false的时候，bar线程在执行，foo线程在这此处空转
                    }
                    printFoo.run();//打印foo
                    fooExec = false;//设置变量
                    try {
                        cb.await();//线程foo到达同步点
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
                    printBar.run();
                    fooExec = true;

                }
            }
        }
    }

    static class _5th_3 {
        class FooBar {
            private int n;

            volatile boolean fooExec = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; ) {
                    if (fooExec) {
                        printFoo.run();
                        fooExec = false;
                        i++;
                    } else {
                        Thread.yield();
                    }

                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; ) {
                    if (!fooExec) {
                        printBar.run();
                        fooExec = true;
                        i++;
                    } else {
                        Thread.yield();
                    }

                }
            }
        }
    }

    static class _5th_4 {


        public static void main(String[] args) {
            FooBar fooBar = new FooBar(5);//打印10次foo bar
            Runnable printFoo = () -> {
                System.out.printf("%s\n", "foo");
            };
            Runnable printBar = () -> {
                System.out.printf("%s\n", "bar");
            };
            Thread fooThread = new Thread(() -> {
                try {
                    fooBar.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread barThread = new Thread(() -> {
                try {
                    fooBar.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            fooThread.start();
            barThread.start();

        }


        static class FooBar {
            private int n;
            private ReentrantLock lock = new ReentrantLock(true);
            volatile boolean fooExec = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {
                for (int i = 0; i < n; ) {
                    lock.lock();
                    try {
                        if (fooExec) {
                            printFoo.run();
                            fooExec = false;
                            i++;
                        }
                    } finally {
                        lock.unlock();
                    }

                }
            }

            public void bar(Runnable printBar) throws InterruptedException {
                for (int i = 0; i < n; ) {
                    lock.lock();
                    try {
                        if (!fooExec) {
                            printBar.run();
                            fooExec = true;
                            i++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }


    static class _5th_5 {
        class FooBar {
            private int n;
            private BlockingQueue<Integer> fooQueue = new LinkedBlockingQueue<Integer>() {{
                add(0);
            }};
            private BlockingQueue<Integer> barQueue = new LinkedBlockingQueue<>();

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    fooQueue.take();
                    printFoo.run();
                    barQueue.add(0);
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    barQueue.take();
                    printBar.run();
                    fooQueue.add(0);
                }
            }
        }
    }


    static class _5th_6 {
        class FooBar {
            private int n;
            private Object obj = new Object();
            private volatile boolean fooExec = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    synchronized (obj) {
                        if (!fooExec) {
                            obj.wait();
                        }
                        printFoo.run();
                        fooExec = false;
                        obj.notifyAll();
                    }

                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    synchronized (obj) {
                        if (fooExec) {
                            obj.wait();
                        }
                        printBar.run();
                        fooExec = true;
                        obj.notifyAll();
                    }

                }
            }
        }
    }


    static class _5th_7 {
        class FooBar {
            private int n;
            private Map<String, Thread> map = new ConcurrentHashMap<>();
            private volatile boolean fooExec = true;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {
                map.put("foo", Thread.currentThread());
                for (int i = 0; i < n; i++) {
                    while (!fooExec) {
                        LockSupport.park();
                    }
                    printFoo.run();
                    fooExec = false;
                    LockSupport.unpark(map.get("bar"));
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {
                map.put("bar", Thread.currentThread());
                for (int i = 0; i < n; i++) {
                    while (fooExec) {
                        LockSupport.park();
                    }
                    printBar.run();
                    fooExec = true;
                    LockSupport.unpark(map.get("foo"));
                }
            }
        }
    }


    static class _5th_8 {
        class FooBar {
            private int n;

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {

                    printFoo.run();
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; i++) {

                    printBar.run();
                }
            }
        }
    }

    static class _6th_1 {

        public static void main(String[] args) {
            FooBar fooBar = new FooBar(5);//打印10次foo bar
            Runnable printFoo = () -> {
                System.out.printf("%s\n", "foo");
            };
            Runnable printBar = () -> {
                System.out.printf("%s\n", "bar");
            };
            Thread fooThread = new Thread(() -> {
                try {
                    fooBar.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread barThread = new Thread(() -> {
                try {
                    fooBar.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            fooThread.start();
            barThread.start();
        }


        /**
         * TLE
         */
        static class FooBar {
            private int n;
            AtomicInteger fooAi = new AtomicInteger(0);
            AtomicInteger barAi = new AtomicInteger(-1);

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    while (fooAi.get() != i) {
                        System.out.printf("fooAi：%d\n", fooAi.get());
                    }
                    printFoo.run();
                    barAi.incrementAndGet();
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {
                for (int i = 0; i < n; i++) {
                    while (barAi.get() != i) {
                        System.out.printf("barAi：%d\n", barAi.get());
                    }
                    ;
                    printBar.run();
                    fooAi.incrementAndGet();
                }
            }
        }
    }
}
