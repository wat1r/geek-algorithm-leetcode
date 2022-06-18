package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;

public class _587 {
    //Graham
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int[][] outerTrees(int[][] trees) {
            int n = trees.length;
            if (n < 4) {
                return trees;
            }
            int bottom = 0;
            /* 找到 y 最小的点 bottom*/
            for (int i = 0; i < n; i++) {
                if (trees[i][1] < trees[bottom][1]) {
                    bottom = i;
                }
            }
            swap(trees, bottom, 0);
            /* 以 bottom 原点，按照极坐标的角度大小进行排序 */
            Arrays.sort(trees, 1, n, (a, b) -> {
                int diff = cross(trees[0], a, b) - cross(trees[0], b, a);
                if (diff == 0) {
                    return distance(trees[0], a) - distance(trees[0], b);
                } else {
                    return -diff;
                }
            });
            /* 对于凸包最后且在同一条直线的元素按照距离从小到大进行排序 */
            int r = n - 1;
            while (r >= 0 && cross(trees[0], trees[n - 1], trees[r]) == 0) {
                r--;
            }
            for (int l = r + 1, h = n - 1; l < h; l++, h--) {
                swap(trees, l, h);
            }
            Deque<Integer> stack = new ArrayDeque<Integer>();
            stack.push(0);
            stack.push(1);
            for (int i = 2; i < n; i++) {
                int top = stack.pop();
                /* 如果当前元素与栈顶的两个元素构成的向量顺时针旋转，则弹出栈顶元素 */
                while (!stack.isEmpty() && cross(trees[stack.peek()], trees[top], trees[i]) < 0) {
                    top = stack.pop();
                }
                stack.push(top);
                stack.push(i);
            }

            int size = stack.size();
            int[][] res = new int[size][2];
            for (int i = 0; i < size; i++) {
                res[i] = trees[stack.pop()];
            }
            return res;
        }

        public int cross(int[] p, int[] q, int[] r) {
            return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
        }

        public int distance(int[] p, int[] q) {
            return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
        }

        public void swap(int[][] trees, int i, int j) {
            int temp0 = trees[i][0], temp1 = trees[i][1];
            trees[i][0] = trees[j][0];
            trees[i][1] = trees[j][1];
            trees[j][0] = temp0;
            trees[j][1] = temp1;
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
