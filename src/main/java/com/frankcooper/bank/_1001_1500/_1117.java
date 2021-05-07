package com.frankcooper.bank._1001_1500;

import java.util.*;
import java.util.concurrent.Semaphore;

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
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
