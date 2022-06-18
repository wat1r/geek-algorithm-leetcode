package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import com.alibaba.fastjson.JSON;
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
            handler.numSquares(13);
        }

        public int numSquares(int n) {
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> vis = new HashSet<>();
            q.offer(n);
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int cur = q.poll();
                    if (cur == 0) return level;
                    for (int i = 1; i * i <= n; i++) {
                        int next = cur - i * i;
                        if (!vis.contains(next)) {
                            vis.add(next);
                            q.offer(next);
                        }
                    }
                }
                level++;
            }
            return level;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
//            System.out.printf("%d\n", );

            Assert.assertEquals(2, handler.numSquares(13));

        }

        public int numSquares(int n) {
            int[] f = new int[n + 1];
            int INF = Integer.MAX_VALUE >> 1;
            Arrays.fill(f, INF);
            f[0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j * j <= i; j++) {
                    f[i] = Math.min(f[i], f[i - j * j] + 1);
                }
            }
            return f[n];
        }
    }

    static class _5th {


        public static void main(String[] args) {
            _5th handler = new _5th();
            System.out.println(handler.numSquares(8935));
        }

        public int numSquares(int n) {

            Queue<Integer> q = new LinkedList();
            HashSet<Integer> hs = new HashSet();
            int depth = 0;
            q.add(0);
            while (!q.isEmpty()) {
                int size = q.size();
                depth++;
                while (size-- > 0) {
                    int num = q.remove();

                    for (int i = 1; i * i <= n; i++) {
                        int u = i * i;
                        if (num + u == n)
                            return depth;
                        if (num + u > n)
                            break;
                        if (!hs.contains(num + u)) {
                            q.add(num + u);
                            hs.add(num);
                        }

                    }
                }

            }
            return depth;
        }
    }


    static class _6th {
        public static void main(String[] args) {
            _6th handler = new _6th();
            handler.numSquares(13);

        }


        List<List<Integer>> res = new ArrayList<>();
        List<Integer> squares;

        public int numSquares(int n) {
            this.squares = generateSquares(n);
            dfs(n, new ArrayList<>());
            for (List<Integer> r : res) {
                System.out.println(JSON.toJSONString(r));
            }
            return -1;
        }


        private void dfs(int n, List<Integer> sub) {
            if (n < 0) return;
            if (n == 0) {
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int x : squares) {
                sub.add(x);
                dfs(n - x, sub);
                sub.remove(sub.size() - 1);
            }
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
}
