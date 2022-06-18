package com.frankcooper.platform.leetcode.swordoffer;

public class Sword_33 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = new int[]{1, 3, 2, 6, 5};
//            Assert.assertTrue(handler.verifyPostorder(arr));

            arr = new int[]{1, 2, 5, 10, 6, 9, 4, 3};
            handler.verifyPostorder(arr);
        }


        public boolean verifyPostorder(int[] postorder) {
            return dfs(postorder, 0, postorder.length - 1);
        }


        private boolean dfs(int[] postorder, int i, int j) {
            if (i >= j) return true;
            int p = i;
            while (postorder[p] < postorder[j]) p++;
            int m = p;
            while (postorder[p] > postorder[j]) p++;
            return p == j && dfs(postorder, i, m - 1) && dfs(postorder, m, j - 1);
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
