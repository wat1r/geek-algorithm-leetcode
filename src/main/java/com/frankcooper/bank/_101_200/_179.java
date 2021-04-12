package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _179 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public String largestNumber(int[] nums) {
            List<String> list = new ArrayList<>();
            for (int x : nums) list.add(String.valueOf(x));
            list.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));
            if (list.get(0).equals("0")) return "0";
            StringBuilder res = new StringBuilder();
            for (String s : list) res.append(s);
            return res.toString();
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
