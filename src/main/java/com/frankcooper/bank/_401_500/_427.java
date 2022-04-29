package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _427 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public Node construct(int[][] grid) {
            return construct(grid, 0, 0, grid.length - 1, grid[0].length - 1);
        }

        private Node construct(int[][] grid, int r1, int c1, int r2, int c2) {
            if (r1 > r2 || c1 > c2) return null;
            if (isLeafNode(grid, r1, c1, r2, c2)) {
                return new Node(grid[r1][c1] == 1, true, null, null, null, null);
            }
            int rowMid = r1 + (r2 - r1) / 2;
            int colMid = c1 + (c2 - c1) / 2;
            return new Node(false, false,
                    construct(grid, r1, c1, rowMid, colMid),
                    construct(grid, r1, colMid + 1, rowMid, c2),
                    construct(grid, rowMid + 1, c1, r2, colMid),
                    construct(grid, rowMid + 1, colMid + 1, r2, c2)
            );
        }


        private boolean isLeafNode(int[][] grid, int r1, int c1, int r2, int c2) {
            int val = grid[r1][c1];
            for (int r = r1; r <= r2; r++) {
                for (int c = c1; c <= c2; c++) {
                    if (grid[r][c] != val) return false;
                }
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public Node construct(int[][] grid) {
            return dfs(grid, 0, 0, grid.length);
        }


        private Node dfs(int[][] grid, int r, int c, int offset) {
            if (offset == 1) {
                return new Node(grid[r][c] != 0, true, null, null, null, null);
            }
            int half = offset / 2;
            Node newNode = new Node();
            Node topLeft = dfs(grid, r, c, half);
            Node topRight = dfs(grid, r, c + half, half);
            Node bottomLeft = dfs(grid, r + half, c, half);
            Node bottomRight = dfs(grid, r + half, c + half, half);
            if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                    && topLeft.val == topRight.val && topRight.val == bottomLeft.val
                    && bottomLeft.val == bottomRight.val) {
                newNode.isLeaf = true;
                newNode.val = topLeft.val;
            } else {
                newNode.topLeft = topLeft;
                newNode.topRight = topRight;
                newNode.bottomLeft = bottomLeft;
                newNode.bottomRight = bottomRight;
            }
            return newNode;
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

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
