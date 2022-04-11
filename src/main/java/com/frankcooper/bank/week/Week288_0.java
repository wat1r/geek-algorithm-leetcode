package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

//https://leetcode-cn.com/contest/cmbchina-2022spring
public class Week288_0 {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            /*
            "e RSg c R cf"
            10
            "Singing dancing in the rain"
            10
            "Hello World"
            2
            "Hello World"
            5
            "Hello"
            0
            ===
            "e RSg c R"
            "Singing in the rain"
            "World"
            "Hello World"
            ""
             */

        }

        public String deleteText(String s, int index) {
            if (s.charAt(index) == ' ') return s;
            int l = index;
            while (l - 1 >= 0 && s.charAt(l - 1) != ' ') l--;
            int r = index;
            int n = s.length();
            while (r + 1 <= n - 1 && s.charAt(r + 1) != ' ') r++;
            if (l == 0 && r == n - 1) return "";
            if (l == 0) return s.substring(r + 2);
            if (r == n - 1) return s.substring(0, l - 1);
            //  System.out.printf("%d" ,r );
            return s.substring(0, l - 1) + " " + s.substring(r + 2);
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
