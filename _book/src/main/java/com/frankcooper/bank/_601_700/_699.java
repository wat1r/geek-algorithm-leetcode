package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _699 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] ps = new int[][]{{1, 2}, {2, 3}, {6, 1}};
            handler.fallingSquares(ps);

        }

        public List<Integer> fallingSquares(int[][] ps) {
            int n = ps.length;
            List<Integer> heights = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                int left1 = ps[i][0], right1 = ps[i][0] + ps[i][1] - 1;
                int height = ps[i][1];
                for (int j = 0; j < i; j++) {
                    int left2 = ps[j][0], right2 = ps[j][0] + ps[j][1] - 1;
                    if (right1 >= left2 && right2 >= left1) {
                        height = Math.max(height, heights.get(j) + ps[i][1]);
                    }
                }
                heights.add(height);
            }
            for (int i = 1; i < n; i++) {
                heights.set(i, Math.max(heights.get(i), heights.get(i - 1)));
            }
            return heights;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<Integer> fallingSquares(int[][] ps) {
            List<Integer> res = new ArrayList<>();
            Node root = null;
            int t = 0;
            for (int[] p : ps) {
                int l = p[0], r = p[0] + p[1], h = p[1];
                int b = query(root, l, r);
                root = insert(root, l, r, h + b);
                t = Math.max(t, h + b);
                res.add(t);
            }
            return res;

        }

        //插入节点
        private Node insert(Node root, int l, int r, int h) {
            if (root == null) return new Node(l, r, h);
            if (l <= root.l) root.left = insert(root.left, l, r, h);
            else root.right = insert(root.right, l, r, h);
            return root;
        }

        private int query(Node root, int l, int r) {
            if (root == null) return 0;
            int height = 0;
            //如果区间[l,r]与root有交集，高度可以叠加
            if (!(r <= root.l || l >= root.r)) {
                height = root.h;
            }
            height = Math.max(height, query(root.left, l, r));
            height = Math.max(height, query(root.right, l, r));
            return height;
        }


        class Node {
            int l, r, h;//左边界，有边界，高度
            Node left, right;//全称的是节点

            public Node(int l, int r, int h) {
                this.l = l;
                this.r = r;
                this.h = h;
            }
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
