package com.frankcooper.bank._1001_2000;

import java.util.*;
import java.util.concurrent.Semaphore;

import org.junit.Assert;

public class _1279 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        class TrafficLight {

            private Semaphore semaphore = new Semaphore(1);
            boolean isAGreen = true;

            public void carArrived(
                    int carId,           // ID of the car
                    int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
                    int direction,       // Direction of the car
                    Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
                    Runnable crossCar    // Use crossCar.run() to make car cross the intersection
            ) throws InterruptedException {
                semaphore.acquire();
                if (roadId == 2 && isAGreen || roadId == 1 && !isAGreen) {
                    turnGreen.run();
                    isAGreen = !isAGreen;
                }
                crossCar.run();
                semaphore.release();
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
