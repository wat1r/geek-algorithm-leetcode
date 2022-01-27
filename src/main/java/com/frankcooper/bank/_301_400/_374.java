package com.frankcooper.bank._301_400;

/*import java.util.*;
import org.junit.Assert;*/
public class _374 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int l = 0, r = 2147483647;
            int mid = l + (r - l + 1) / 2;
            System.out.println();

        }

        public int guessNumber(int n) {
            int l = 1, r = n;//l从1开始
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                // System.out.printf("%d ", mid);
                int t = guess(mid);
                if (t == 0) return mid;
                else if (t == 1) l = mid + 1;
                else if (t == -1) r = mid - 1;
            }
            return l;
        }

        private int guess(int mid) {
            return 0;
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
