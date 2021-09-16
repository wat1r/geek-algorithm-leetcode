package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _279 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int numSquares(int n) {
            List<Integer> squares = generateSquares(n);
            Queue<Integer> queue = new LinkedList<>();
            boolean[] marked = new boolean[n + 1];
            queue.add(n);
            marked[n] = true;
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                while (size-- > 0) {
                    int cur = queue.poll();
                    for (int square : squares) {
                        int next = cur - square;
                        if (next < 0) {
                            break;
                        }
                        if (next == 0) {
                            return level;
                        }
                        if (marked[next]) {
                            continue;
                        }
                        marked[next] = true;
                        queue.add(next);
                    }
                }
            }
            return -1;
        }


        private List<Integer> generateSquares(int n) {
            List<Integer> squares = new ArrayList<>();
            int base = (int) Math.sqrt(n);
            for (int i = 1; i <= base; i++) {
                squares.add((int) Math.pow(i, 2));
            }
            return squares;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.numSquares(13);
        }

        public int numSquares(int n) {
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> vis = new HashSet<>();
            q.offer(0);
            vis.add(0);
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                level++;
                while (size-- > 0) {
                    int cur = q.poll();
                    for (int i = 1; i * i <= n; i++) {
                        int next = cur + i * i;
                        if (next == n) return level;
                        if (next > n) break;
                        if (!vis.contains(next)) {
                            vis.add(next);
                            q.offer(next);
                        }
                    }
                }
            }
            return level;
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
