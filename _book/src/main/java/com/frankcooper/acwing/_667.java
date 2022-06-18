package com.frankcooper.platform.acwing;

import java.util.*;

import org.junit.Assert;

public class _667 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Scanner in = new Scanner(System.in);
            int A = in.nextInt(), B = in.nextInt();
            String s = "O JOGO DUROU %d HORA(S)";
            if (A == B) System.out.println(String.format(s, 24));
            else {
                if (A > B) B += 24;
                System.out.println(String.format(s, B - A));
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
