package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1344 {
    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int hour = 12;
            int minutes = 30;
            handler.angleClock(hour, minutes);
        }

        public double angleClock(int hour, int minutes) {
            int minAngle = 6, hourAngle = 30;
            double d1 = minutes * minAngle;
            double d2 = (hour % 12 + minutes / 60.0) * hourAngle;
            return Math.min(Math.abs(d1 - d2), 360 - Math.abs(d1 - d2));
        }
    }

    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int hour = 12;
            int minutes = 30;
            handler.angleClock(hour, minutes);
        }

        public double angleClock(int hour, int minutes) {
            return getAngle(hour, minutes);
        }


//        public double getAngle(int hour, int minutes, int second) {
//            double hourAngle = (hour * 3600 + minutes * 60 + second) /( 12 * 3600.0);
//            double minAngle = (minutes * 60 + second) / 3600.0;
//            double angle = Math.abs(hourAngle - minAngle);
//            return Math.min(angle, 1 - angle) * 360;
//        }

        public double getAngle(int hour, int minutes) {
            double hourAngle = (hour * 60 + minutes) / (12 * 60.0);
            double minAngle = (minutes) / 60.0;
            double angle = Math.abs(hourAngle - minAngle);
            return Math.min(angle, 1 - angle) * 360;
        }


    }

    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int hour = 12;
            int minutes = 30;
            handler.angleClock(hour, minutes);
        }

        public double angleClock(int hour, int minutes) {
            double h = hour % 12 * (360.0 / 12) + minutes * (30.0 / 60);
            double m = minutes * (360.0 / 60);
            double angle = Math.abs(h - m);
            return Math.min(angle, 360.0 - angle);
        }

    }


    static class _4th {

        public static void main(String[] args) {
            _4th handler = new _4th();
            int hour = 12;
            int minutes = 30;
            handler.angleClock(hour, minutes, 0);
        }


        public double angleClock(int hour, int minutes, int second) {
            double m = minutes * 720 + second * 12;
            double h = hour * 3600 + minutes * 60 + second;
            double diff = Math.abs(m - h);
            return Math.min(diff / 120.0, 360.0 - diff / 120.0);
        }
    }
}
