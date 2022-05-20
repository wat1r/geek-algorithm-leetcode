package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class Week292 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public String largestGoodInteger(String num) {
            String res = "";
            for (int i = 0; i < num.length() - 2; i++) {
                if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)) {
                    String t = num.substring(i, i + 3);
                    if (t.compareTo(res) > 0) res = t;
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode root = TreeNodeIOUtils.transform("[4,8,5,0,1,null,6]");
            handler.averageOfSubtree(root);
//            handler.dfs(root);
            System.out.println(handler.ans);
//            System.out.println("");
        }


        int ans = 0;

        public int averageOfSubtree(TreeNode root) {
            if (root == null) return 0;
            sum = 0;
            dfs(root);
            int cnt = count(root);
            System.out.printf("sum:%d,cnt:%d, root:%d\n", sum, cnt, root.val);
            if (sum / cnt == root.val) {
//                return cnt;
                ans++;
//                System.out.printf("*sum:%d,cnt:%d, root:%d\n", sum, cnt, root.val);
            }
            averageOfSubtree(root.left);
            averageOfSubtree(root.right);
            return ans;
        }

        int sum = 0;

        private void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            sum += node.val;
            dfs(node.left);
            dfs(node.right);
        }


        private int count(TreeNode node) {
            if (node == null) return 0;
            return count(node.left) + count(node.right) + 1;
        }


    }

    static class _2nd_1 {

        int res = 0;

        public int averageOfSubtree(TreeNode root) {
            dfs(root);
            return res;
        }


        private Pair dfs(TreeNode node) {
            if (node == null) return new Pair(0, 0);
            Pair lp = dfs(node.left), rp = dfs(node.right);
            int sum = lp.sum + rp.sum + node.val, cnt = lp.cnt + rp.cnt + 1;
            if (sum / cnt == node.val) res++;
            return new Pair(sum, cnt);
        }


        class Pair {
            private int sum;
            private int cnt;

            public Pair(int sum, int cnt) {
                this.sum = sum;
                this.cnt = cnt;
            }
        }


    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        long ans = 0;
        int MOD = (int) 1e9 + 7;

        Map<Character, List<String>> dict = new HashMap<Character, List<String>>() {{
            put('2', Arrays.asList("a", "b", "c"));
            put('3', Arrays.asList("d", "e", "f"));
            put('4', Arrays.asList("g", "h", "i"));
            put('5', Arrays.asList("j", "k", "l"));
            put('6', Arrays.asList("m", "n", "o"));
            put('7', Arrays.asList("p", "q", "r", "s"));
            put('8', Arrays.asList("t", "u", "v"));
            put('9', Arrays.asList("w", "x", "y", "z"));
        }};


        public int countTexts(String pressedKeys) {


            return (int) (ans % MOD);
        }

//        private void dfs()


    }

    static class _3rd_1 {


        public static void main(String[] args) {
            _3rd_1 handler = new _3rd_1();
            String pressedKeys = "22233";
            Assert.assertEquals(8, handler.countTexts(pressedKeys));
        }

        public int countTexts(String pressedKeys) {
            int mod = (int) 1e9 + 7;
            int[] p = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
            char[] ch = pressedKeys.toCharArray();
            int n = ch.length;
            //dp[i]表示ch[0...i-1]之间可以形成的文字信息的组合数
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = i; j >= 0 && i - j < p[ch[i] - '0']; j--) {
                    if (ch[i] == ch[j]) {//
                        dp[i + 1] += dp[j];
                        dp[i + 1] %= mod;
                    } else {
                        break;
                    }
                }
            }
            // System.out.println(Arrays.toString(dp));
            return dp[n];
        }
    }

    static class _3rd_2 {
        public int countTexts(String pressedKeys) {
            int MOD = (int) 1e9 + 7;
            int[] p = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
            int n = pressedKeys.length();
            char[] ch = pressedKeys.toCharArray();
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = i; j >= 0 && i - j < p[ch[i] - '0']; --j) {
                    if (ch[i] == ch[j]) {
                        dp[i + 1] += dp[j];
                        dp[i + 1] %= MOD;
                    } else {
                        break;
                    }
                }
            }
            return dp[n];
        }


        //
    }

    static class _3rd_3 {

        public static void main(String[] args) {
            _3rd_3 handler = new _3rd_3();
            String pressedKeys = "22233";
            Assert.assertEquals(8, handler.countTexts(pressedKeys));
        }


        static int N = 100010;
        static long[] three = new long[N];
        static long[] four = new long[N];

        static int MOD = (int) 1e9 + 7;

        static {
            three[0] = 1;
            three[1] = 1;
            three[2] = 2;
            three[3] = 4;
            four[0] = 1;
            four[1] = 1;
            four[2] = 2;
            four[3] = 4;
            for (int i = 4; i < N; i++) {
                three[i] = three[i - 1] + three[i - 2] + three[i - 3];
                three[i] %= MOD;
                four[i] = four[i - 1] + four[i - 2] + four[i - 3] + four[i - 4];
                four[i] %= MOD;
            }
        }


        public int countTexts(String pressedKeys) {
            char[] cs = pressedKeys.toCharArray();
            int n = cs.length, i = 0;
            long ans = 1;
            while (i < n) {
                int j = i;
                while (j + 1 < n && cs[j] == cs[j + 1]) j++;
                boolean isFour = cs[i] == '7' | cs[i] == '9';
                long cur = isFour ? four[j - i + 1] : three[j - i + 1];
                ans = (ans * cur) % MOD;
                i = j + 1;
            }
            return (int) ans;
        }

    }

    static class _3rd_4 {
        public static void main(String[] args) {
            _3rd_4 handler = new _3rd_4();
            String pressedKeys = "22233";
            Assert.assertEquals(8, handler.countTexts(pressedKeys));
        }


        public int countTexts(String pressedKeys) {
            int MOD = (int) 1e9 + 7;
            int n = pressedKeys.length();
            //f[i]表示把连续i个数字分成长度不超过3的若干段的方案数
            //f[i] =f[i-1]+f[i-2]+f[i-3]
            int[] f = new int[n + 1], g = new int[n + 1];
            f[0] = 1;
            g[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 3 && j <= i; j++) {
                    f[i] = (f[i] + f[i - j]) % MOD;
                }
                for (int j = 1; j <= 4 && j <= i; j++) {
                    g[i] = (g[i] + g[i - j]) % MOD;
                }
            }
            char[] ch = pressedKeys.toCharArray();
            long res = 1;
            char last = '0';
            int cnt = 0;
            for (char c : ch) {
                if (c != last) {
                    if (last == '7' || last == '9') {
                        res = res * g[cnt] % MOD;
                    } else {
                        res = res * f[cnt] % MOD;
                    }
                    cnt = 0;
                    last = c;
                }
                cnt++;
            }
            if (last == '7' || last == '9') {
                res = res * g[cnt] % MOD;
            } else {
                res = res * f[cnt] % MOD;
            }
            return (int) (res % MOD);
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            char[][] grid = {{'(', '(', '('}, {')', '(', ')'}, {'(', '(', ')'}, {'(', '(', ')'}};
            handler.hasValidPath(grid);

        }


//        https://leetcode-cn.com/problems/check-if-there-is-a-valid-parentheses-string-path/solution/by-tsreaper-wbub/

        public boolean hasValidPath(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            //dp[i][j][k]表示到达格子[i][j]处时，值为k的路径是否可达
            //k 是在遇到( +1 遇到 ) -1 最后的格子的位置[m-1][n-1]的位置，k值一定为0，否则不可达
            //[0][0]的格子一定是 ( 否则没法开启路径
            if (grid[0][0] == ')') return false;
            boolean[][][] dp = new boolean[m][n][m + n];
            dp[0][0][1] = true;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0 || j > 0) {
                        int t = grid[i][j] == '(' ? 1 : -1;
                        for (int k = 0; k < m + n; k++) {
                            int kk = k - t;
                            if (kk < 0 || kk >= m + n) continue;
                            if (i > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i - 1][j][kk];
                            }
                            if (j > 0) {
                                dp[i][j][k] = dp[i][j][k] || dp[i][j - 1][kk];
                            }
                        }
                    }
                }
            }
            return dp[m - 1][n - 1][0];
        }
    }

    static class _4th_1 {
        public static void main(String[] args) {

        }

//        https://leetcode-cn.com/problems/check-if-there-is-a-valid-parentheses-string-path/solution/tian-jia-zhuang-tai-hou-dfscpythonjavago-f287/

        int m, n;
        char[][] grid;
        boolean[][][] vis;


        public boolean hasValidPath(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            if ((m + n) % 2 == 0 || grid[0][0] != '(' || grid[m - 1][n - 1] != ')') return false;
            this.grid = grid;
            vis = new boolean[m][n][(m + n + 1) / 2];
            return dfs(0, 0, 0);
        }

        private boolean dfs(int x, int y, int c) {
            if (x < 0 || x >= m || y < 0 || y >= n) return false;//越界
            if (c > m - x + n - y - 1) return false;
            if (x == m - 1 && y == n - 1) return c == 1;
            if (vis[x][y][c]) return false;
            vis[x][y][c] = true;
            c += grid[x][y] == '(' ? 1 : -1;
            boolean res = true;
            res = res && c >= 0;
            res = res && (x < m - 1 && dfs(x + 1, y, c) || y < n - 1 && dfs(x, y + 1, c));
            return res;
        }
    }

    static class _4th_2 {
        int m, n;
        char[][] grid;
        boolean[][][] vis;


        public boolean hasValidPath(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            if ((m + n) % 2 == 0 || grid[0][0] != '(' || grid[m - 1][n - 1] != ')') return false;
            this.grid = grid;
            vis = new boolean[m][n][m + n];
            return dfs(0, 0, 0);
        }

        private boolean dfs(int i, int j, int c) {
            if (i == m - 1 && j == n - 1) return c == 1;
//            if (i < 0 || i >= m || j < 0 || j >= n) return false;//越界
            if (c > m - i + n - j - 1 || vis[i][j][c]) return false;
            vis[i][j][c] = true;
            c += grid[i][j] == '(' ? 1 : -1;
            return c >= 0 && (i < m - 1 && dfs(i + 1, j, c) || j < n - 1 && dfs(i, j + 1, c));
        }
    }

    static class _4th_3 {

        int[][] dirs = {{1, 0}, {0, 1}};
        int N = 110;

        public boolean hasValidPath(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            boolean[][][] vis = new boolean[N][N][N];
            if ((m + n) % 2 == 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
            q.offer(new int[]{0, 0, 1});
            vis[0][0][1] = true;
            while (!q.isEmpty()) {
                int[] t = q.poll();
                int x = t[0], y = t[1];
                for (int[] d : dirs) {
                    int c = t[2];
                    int nx = x + d[0], ny = y + d[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (grid[nx][ny] == '(') c++;
                        else c--;
                        if (c < 0 || vis[nx][ny][c] || m - nx + n - ny < c) {
                            continue;
                        }
                        vis[nx][ny][c] = true;
                        if (nx == m - 1 && ny == n - 1 && c == 0) {
                            return true;
                        }
                        q.offer(new int[]{nx, ny, c});
                    }
                }
            }
            return false;
        }


    }

}
