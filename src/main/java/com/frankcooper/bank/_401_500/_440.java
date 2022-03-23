package com.frankcooper.bank._401_500;

/*import java.util.*;
import org.junit.Assert;*/
public class _440 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int findKthNumber(int n, int k) {
            int curr = 1;
            k--;
            while (k > 0) {
                int steps = getSteps(curr, n);
                if (steps <= k) {
                    k -= steps;
                    curr++;
                } else {
                    curr = curr * 10;
                    k--;
                }
            }
            return curr;
        }

        public int getSteps(int curr, long n) {
            int steps = 0;
            long first = curr;
            long last = curr;
            while (first <= n) {
                steps += Math.min(last, n) - first + 1;
                first = first * 10;
                last = last * 10 + 9;
            }
            return steps;
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
