package com.frankcooper.bank._201_300;

/*import java.util.*;
import org.junit.Assert;*/
public class _258 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int addDigits(int num) {
            while (num >= 10) {
                int t = 0;
                while (num > 0) {
                    t += num % 10;
                    num /= 10;
                }
                num = t;
            }
            return num;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int addDigits(int num) {
            return (num - 1) % 9 + 1;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int addDigits(int num) {
            if (num == 0) return 0;
            if (num % 9 == 0) return 9;
            return num % 9;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
