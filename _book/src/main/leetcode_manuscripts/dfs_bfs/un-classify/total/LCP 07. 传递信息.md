## [LCP 07. 传递信息](https://leetcode-cn.com/problems/chuan-di-xin-xi/)

### 方法1：BFS

```java
  public int numWays(int n, int[][] relation, int k) {
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) edges.add(new ArrayList<>());
            for (int[] x : relation) edges.get(x[0]).add(x[1]);
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            int step = 0;
            while (!q.isEmpty() && step < k) {
                step++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int u = q.poll();
                    List<Integer> vs = edges.get(u);
                    for (int v : vs) {
                        q.offer(v);
                    }
                }
            }
            int res = 0;
            if (step == k) {//到达指定层数
                while (!q.isEmpty()) {
                    if (q.poll() == n - 1) res++;
                }
            }
            return res;
        }
```

### 方法2：DFS

```java
 List<List<Integer>> edges = new ArrayList<>();
        int res = 0;
        int k;
        int n;

        public int numWays(int n, int[][] relation, int k) {
            this.k = k;
            this.n = n;
            for (int i = 0; i < n; i++) edges.add(new ArrayList<>());
            for (int[] x : relation) edges.get(x[0]).add(x[1]);
            dfs(0, 0);
            return res;
        }

        private void dfs(int u, int step) {
            if (step == k) {
                if (u == n - 1) res++;
                return;
            }
            List<Integer> vs = edges.get(u);
            for (int v : vs) dfs(v, step + 1);
        }

```

### 方法3：DP

```java
public int numWays(int n, int[][] relation, int k) {
    /**
     * f[i][j] 经过i轮传递，到待j这个小朋友这里时的方案数
     */
    int[][] f = new int[k + 1][n];
    f[0][0] = 1; //不经过传递，自己到自己，只有一种方案数
    for (int i = 1; i <= k; i++) {
        for (int j = 1; j <= relation.length; j++) {
            int u = relation[j - 1][0], v = relation[j - 1][1];
            f[i][v] += f[i - 1][u];
        }
    }
    return f[k][n - 1];
}
```

- 空间压缩$O(1)$

```java
        public int numWays(int n, int[][] relation, int k) {
            /**
             * f[j] 经过i轮传递，到达j这个小朋友这里时的方案数
             */
            int[] f = new int[n];
            f[0] = 1; //不经过传递，自己到自己，只有一种方案数
            for (int i = 1; i <= k; i++) {
                int[] t = new int[n];
                for (int j = 1; j <= relation.length; j++) {
                    int u = relation[j - 1][0], v = relation[j - 1][1];
                    t[v] += f[u];
                }
                f = t;
            }
            return f[n - 1];
        }
```

