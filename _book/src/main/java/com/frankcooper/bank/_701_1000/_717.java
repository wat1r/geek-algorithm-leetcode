package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _717 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public boolean isOneBitCharacter(int[] bits) {
            int n = bits.length, i = 0;
            while (i < n-1) {
                if (bits[i] == 0) i++;
                else i += 2;
            }
            return i == n - 1;
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
