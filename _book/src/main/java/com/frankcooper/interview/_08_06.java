package com.frankcooper.platform.leetcode.interview;

import com.google.common.collect.Lists;

import java.util.*;

public class _08_06 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            List<Integer> A = Lists.newArrayList(2, 1, 0);
            List<Integer> B = new ArrayList<>();
            List<Integer> C = new ArrayList<>();
            handler.hanota(A, B, C);
        }


        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            int remain = A.size();
            move(remain, A, B, C);
        }


        public void move(int remain, List<Integer> A, List<Integer> B, List<Integer> C) {
            if (remain == 1) {
                int t = A.remove(A.size() - 1);
                C.add(t);
                return;
            }
            move(remain - 1, A, C, B);
            int t = A.remove(A.size() - 1);
            C.add(t);
            move(remain - 1, B, A, C);

        }


        public void test() {
//            while ()


            System.out.printf("%s", "DD");


            return;
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
