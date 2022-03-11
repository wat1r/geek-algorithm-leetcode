package com.frankcooper.interview;

/*import java.util.*;
import org.junit.Assert;*/
public class _05_02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.printBin(0.625);
        }


        public String printBin(double num) {
            String res = "0.";
            while (num != 0) {
                num *= 2;
                if (num >= 1) {
                    res = res + "1";
                    num -= 1;
                } else {
                    res = res + "0";
                }
                if (res.length() > 32) return "ERROR";
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.printBin(0.625);
        }


        public String printBin(double num) {
            String res = "0.";
            double b = 0.5;
            while (num != 0) {
                if (res.length() > 32) return "ERROR";
                if (num >= b) {
                    res = res + "1";
                    num -= b;
                } else {
                    res = res + "0";
                }
                b /= 2;
            }
            return res;
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
