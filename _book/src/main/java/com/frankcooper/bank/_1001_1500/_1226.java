package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;

public class _1226 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        class DiningPhilosophers {
            //一个叉子一个lock
            private final ReentrantLock[] lockList = {
                    new ReentrantLock(),
                    new ReentrantLock(),
                    new ReentrantLock(),
                    new ReentrantLock(),
                    new ReentrantLock()
            };
            private Semaphore semaphore = new Semaphore(4);//最多4个哲学家持有叉子，才有一个哲学家吃上面


            public DiningPhilosophers() {

            }

            // call the run() method of any runnable to execute its code
            public void wantsToEat(int philosopher,
                                   Runnable pickLeftFork,
                                   Runnable pickRightFork,
                                   Runnable eat,
                                   Runnable putLeftFork,
                                   Runnable putRightFork) throws InterruptedException {
                int leftFork = (philosopher + 1) % 5;//左边叉子编号
                int rightFork = philosopher;//右边叉子编号
                semaphore.acquire();//限制的人数-1
                lockList[leftFork].lock();    //拿起左边的叉子
                lockList[rightFork].lock();    //拿起右边的叉子
                pickLeftFork.run();//拿起左边的叉子
                pickRightFork.run(); //拿起右边的叉子
                eat.run();//吃面
                putLeftFork.run();//放下左边的叉子
                putRightFork.run();//放下右边的叉子
                lockList[leftFork].unlock();  //放下左边的叉子 释放
                lockList[rightFork].unlock(); //放下右边的叉子 释放
                semaphore.release(); //限制的人数+1

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
