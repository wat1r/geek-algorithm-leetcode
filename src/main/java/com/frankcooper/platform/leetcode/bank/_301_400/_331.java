package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.Deque;
import java.util.LinkedList;

public class _331 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
            System.out.printf("%s\n", handler.isValidSerialization(preorder));
            preorder = "1,#";
            System.out.printf("%s\n", handler.isValidSerialization(preorder));
            preorder = "9,#,#,0";
            System.out.printf("%s\n", handler.isValidSerialization(preorder));
            preorder = "1,#,#,#,#";
            System.out.printf("%s\n", handler.isValidSerialization(preorder));
            preorder = "9,#,92,#,#";
            System.out.printf("%s\n", handler.isValidSerialization(preorder));
            preorder = "1";
            System.out.printf("%s\n", handler.isValidSerialization(preorder));
            preorder = "#";
            System.out.printf("%s\n", handler.isValidSerialization(preorder));
        }


        public boolean isValidSerialization(String preorder) {
            if (preorder == null || preorder.length() == 0) return false;
            Deque<Integer> q = new LinkedList<>();
            q.push(1);
            String[] arr = preorder.split(",");
            for (int i = 0; i < arr.length; i++) {
                if (q.isEmpty()) return false;
                q.push(q.pop() - 1);
                if (q.peek() == 0) q.pop();
                if (!arr[i].equals("#")) q.push(2);
            }
            return q.isEmpty();
        }
    }

}
