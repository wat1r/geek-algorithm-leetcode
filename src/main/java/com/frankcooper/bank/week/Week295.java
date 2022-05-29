package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.bank._1001_1500._1368;
import org.junit.Assert;

public class Week295 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int rearrangeCharacters(String s, String target) {
            int[] cnt = new int[26];
            for (char c : s.toCharArray()) cnt[c - 'a']++;
            int[] arr = new int[26];
            for (char c : target.toCharArray()) arr[c - 'a']++;
            int replica = 110;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    replica = Math.min(replica, cnt[i] / arr[i]);
                }
            }
            return replica;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String sentence = "there are $1 $2 and 5$ candies in the shop";
            int discount = 50;
            sentence = "1 2 $3 4 $5 $6 7 8$ $9 $10$";
            discount = 100;
            //"$39577.72 ab $3.12 $"
            sentence = "706hzu76jjh7yufr5x9ot60v149k5 $7651913186 pw2o $6";
            discount = 28;
            handler.discountPrices(sentence, discount);
        }

        public String discountPrices(String sentence, int discount) {
            StringBuilder sb = new StringBuilder();
            String[] arr = sentence.split("\\s+");
            for (String a : arr) {
                if (a.startsWith("$")) {
                    long t = 0;
                    boolean f = true;
                    for (int i = 1; i < a.length(); i++) {
                        char c = a.charAt(i);
                        if (!(c - '0' >= 0 && c - '0' <= 9)) {
                            sb.append(a);
                            f = false;
                            break;
                        }
                        t = t * 10 + (c - '0');
                    }
                    if (f && a.length() > 1) {
                        double k = (1.0 - discount * 1.0 / 100) * t;
                        sb.append("$").append(String.format("%.2f", k));
                    }
                    if (a.length() == 1) {
                        sb.append("$");
                    }
                } else {
                    sb.append(a);
                }
                sb.append(" ");
            }
            return sb.toString().trim();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


    }


    //UC
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;

        public int minimumObstacles(int[][] grid) {
            R = grid.length;
            C = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
            grid[0][0] = -1;
            int count = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] p = q.poll();
                    for (int[] d : dirs) {
                        int nr = p[0] + d[0], nc = p[1] + d[1];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C) {

                        }
                    }
                }
            }
            return 0;
        }
    }

    //01-BFS
    static class _4th_1 {

        int m, n;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minimumObstacles(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[] dist = new int[m * n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            boolean[] vis = new boolean[m * n];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offerFirst(0);
            while (!deque.isEmpty()) {
                int cur_pos = deque.pollFirst();
                if (vis[cur_pos]) {
                    continue;
                }
                vis[cur_pos] = true;
                int x = cur_pos / n, y = cur_pos % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0], ny = y + dirs[i][1];
                    int new_pos = nx * n + ny;
                    //1:右 2:左 3:下 4:上
                    //当前的[x,y]的值如果是符合1 2 3 4 的值，则不需要修改，也就是 0
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    int g = grid[x][y];
                    if (dist[cur_pos] + g < dist[new_pos]) {
                        dist[new_pos] = dist[cur_pos] + g;
                        //01广度优先搜索，将0的那个点加入到队首，1的那个点加入到对尾
                        if (g == 0) {
                            deque.offerFirst(new_pos);
                        } else {
                            deque.offerLast(new_pos);
                        }
                    }
                }
            }
            return dist[m * n - 1];
        }

    }

    static class _4th_2 {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int minimumObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dis = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(dis[i], Integer.MAX_VALUE);
            }
            dis[0][0] = 0;
            Deque<int[]> q = new ArrayDeque<>();
            q.addFirst(new int[]{0, 0});
            while (!q.isEmpty()) {
                int[] p = q.pollFirst();
                int x = p[0], y = p[1];
                for (int[] d : dirs) {
                    int nx = x + d[0], ny = y + d[1];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                        int g = grid[nx][ny];
                        if (dis[x][y] + g < dis[nx][ny]) {
                            dis[nx][ny] = dis[x][y] + g;
                            if (g == 0) q.addFirst(new int[]{nx, ny});
                            else q.addLast(new int[]{nx, ny});
                        }
                    }
                }
            }
            return dis[m - 1][n - 1];
        }


    }

    static class _4th_3 {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int minimumObstacles(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] dist = new int[m][n];
            boolean[][] vis = new boolean[m][n];
            //按照队列中的坐标，在dist中的升序排列，这样计算的一定是最小的值
            Queue<int[]> queue = new PriorityQueue<int[]>((a, b) -> dist[a[0]][a[1]] - dist[b[0]][b[1]]);
            queue.offer(new int[]{0, 0});
            while (!queue.isEmpty()) {
                int[] p = queue.poll();
                int x = p[0], y = p[1];
                for (int[] d : dirs) {
                    int nx = x + d[0], ny = y + d[1];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || vis[nx][ny]) continue;
                    vis[nx][ny] = true;
                    //当前点的值等于上一个过来点的值 + 当前点的值
                    dist[nx][ny] = dist[x][y] + grid[nx][ny];
                    queue.offer(new int[]{nx, ny});
                }
            }
            return dist[m - 1][n - 1];
        }
    }

    static class _4th_4 {
        class Point {
            int value;
            int pos;

            Point() {
            }

            public Point(int value, int pos) {
                this.value = value;
                this.pos = pos;
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minimumObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dist = new int[m][n];
            for (int r = 0; r < m; r++) {
                Arrays.fill(dist[r], Integer.MAX_VALUE);
            }
            //按value 从小到大排序
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
            pq.offer(new Point(grid[0][0], 0));
            dist[0][0] = 0;
            while (!pq.isEmpty()) {
                Point cur = pq.poll();
                int r = cur.pos / n, c = cur.pos % n;
                int value = cur.value;
                if (dist[r][c] < value) {
                    continue;
                }
                if (r == m - 1 && c == n - 1) {
                    return value;
                }
                for (int k = 0; k < 4; k++) {
                    int nr = r + dirs[k][0], nc = c + dirs[k][1];
                    int cost = (k + 1 == grid[r][c] ? 0 : 1);
                    if (0 <= nr && nr < m && 0 <= nc && nc < n) {
                        int next_value = grid[nr][nc] + value;
                        if (dist[nr][nc] > next_value) {
                            dist[nr][nc] = next_value;
                            pq.offer(new Point(next_value, nr * n + nc));
                        }
                    }
                }
            }
            return dist[m - 1][n - 1];
        }
    }
}
