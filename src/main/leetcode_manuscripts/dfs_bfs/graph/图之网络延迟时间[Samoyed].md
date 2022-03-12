## 图之网络延迟时间[Samoyed]

> 一道模板题，详见文末推荐阅读

![istockphoto-882484506-1024x1024](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\graph\图之网络延迟时间.assets\istockphoto-882484506-1024x1024.jpg)



### 方法1：优先队列BFS

```java
public int networkDelayTime(int[][] times, int N, int K) {
    //建图，key是当前节点的 value的0 只是其邻居节点，1 是距离 即u->v  花费w
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    for (int[] t : times) {
        graph.putIfAbsent(t[0], new HashMap<>());
        graph.get(t[0]).put(t[1], t[2]);
    }
    //采用优先队列存储， 0指的是当前走的距离，1指的是走到当前的节点Node 按距离从小到大排序，这个很关键
    Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
    pq.offer(new int[]{0, K});//起始从K 节点开始，走到K节点花了0的距离
    boolean[] visited = new boolean[N + 1]; //下标从1开始，多放一个
    int ans = 0;//结果集
    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        //0 是走到当前节点的距离 1 是
        int currNode = curr[1], currDist = curr[0];
        if (visited[currNode]) continue;//当前点如果访问过，不需要再访问其邻居节点
        visited[currNode] = true;
        ans = currDist;
        N--;//所有的节点被遍历完，可以提前结束
        if (N == 0) return ans;
        if (graph.containsKey(currNode)) {//访问邻居节点，并更新当前的dist距离
            for (int next : graph.get(currNode).keySet()) {
                pq.offer(new int[]{currDist + graph.get(currNode).get(next), next});
            }
        }
    }
    return N == 0 ? ans : -1;
}
```

### 方法2：普通队列BFS

```java
        public int networkDelayTime(int[][] times, int N, int K) {
            int INF = Integer.MAX_VALUE / 2;
            //构建graph
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int[] t : times) {
                graph.putIfAbsent(t[0], new HashMap<>());
                graph.get(t[0]).put(t[1], t[2]);
            }
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);//需要初始化成INF，防止溢出，取INF/2
            Queue<Integer> q = new LinkedList<>();
            q.offer(K);
            dist[K] = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                if (graph.containsKey(u)) {
                    for (Map.Entry<Integer, Integer> e : graph.get(u).entrySet()) {
                        int v = e.getKey(), w = e.getValue();
                        if (dist[u] + w < dist[v]) {//松弛
                            dist[v] = dist[u] + w;
                            q.offer(v);
                        }
                    }
                }
            }
            int ans = 0;
            //跳过0这个不存在的节点
            for (int i = 1; i <= N; i++) {
                ans = Math.max(ans, dist[i]);
            }
            return ans == INF ? -1 : ans;
        }
```

### 方法3：DFS

```java
        Map<Integer, Map<Integer, Integer>> graph;
        int[] dist;

        public int networkDelayTime(int[][] times, int N, int K) {
            int INF = Integer.MAX_VALUE / 2;
            //构建graph
            graph = new HashMap<>();
            for (int[] t : times) {
                graph.putIfAbsent(t[0], new HashMap<>());
                graph.get(t[0]).put(t[1], t[2]);
            }
            dist = new int[N + 1];
            Arrays.fill(dist, INF);//需要初始化成INF，防止溢出，取INF/2
            dist[K] = 0;
            dfs(K);
            int ans = 0;
            //跳过0这个不存在的节点
            for (int i = 1; i <= N; i++) {
                ans = Math.max(ans, dist[i]);
            }
            return ans == INF ? -1 : ans;
        }

        private void dfs(int u) {
            if (graph.containsKey(u)) {
                for (Map.Entry<Integer, Integer> e : graph.get(u).entrySet()) {
                    int v = e.getKey(), w = e.getValue();
                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        dfs(v);
                    }
                }
            }
        }
```











### 方法4:Dijkstra

```java
   Integer INF = Integer.MAX_VALUE;

        public int networkDelayTime(int[][] times, int N, int K) {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            //初始化邻接表
            for (int[] time : times) {
                graph.computeIfAbsent(time[0], t -> new ArrayList<>()).add(new int[]{time[1], time[2]});
            }
            int[] dis = new int[N + 1];
            Arrays.fill(dis, INF);
            boolean[] vis = new boolean[N + 1];
            //多设置了0这个索引的冗余，距离设置为0
            dis[K] = 0;
            dis[0] = 0;
            //优先队列，按距离从小到大排列
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> dis[o1] - dis[o2]));
            pq.offer(K);
            while (!pq.isEmpty()) {
                //弹出
                Integer curr = pq.poll();
                //访问过不用再执行下面的逻辑
                if (vis[curr]) continue;
                //标记访问
                vis[curr] = true;
                //获取到当前节点的邻居节点
                List<int[]> neighbours = graph.getOrDefault(curr, new ArrayList<>());
                for (int[] neighbour : neighbours) {
                    int to = neighbour[0];
                    if (vis[to]) continue;
                    //如果当前dis[to]大，则更新之
                    dis[to] = Math.min(dis[to], dis[curr] + neighbour[1]);
                    pq.offer(to);
                }
            }
            for (int d : dis) System.out.print(d + " ");
            int max = 0;
            for (int d : dis) max = Math.max(max, d);
            return max == INF ? -1 : max;
        }
```

### 方法5:Bellman-Ford

```java
        Integer INF = Integer.MAX_VALUE;

        /**
         * @param edges 边
         * @param N     顶点数
         * @param K     其实顶点
         * @return
         */
        public int networkDelayTime(int[][] edges, int N, int K) {

            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[K] = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < edges.length; j++) {
                   	//松弛
                    if (dist[edges[j][0]] != INF && dist[edges[j][1]] > dist[edges[j][0]] + edges[j][2]) {
                        dist[edges[j][1]] = dist[edges[j][0]] + edges[j][2];
                    }
                }
            }
            int max = 0;
            for (int i = 1; i <= N; ++i) max = Math.max(max, dist[i]);
            return max == INF ? -1 : max;
        }
```

### 方法6:SPFA

```java
        Integer INF = Integer.MAX_VALUE;

        public int networkDelayTime(int[][] edges, int N, int K) {
            List<Integer> queue = new ArrayList<>();
            long[] dist = new long[N + 1];
            boolean[] used = new boolean[N + 1];
            int[] nums = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[K] = 0;
            used[K] = true;
            queue.add(K);
            while (!queue.isEmpty()) {
                int a = queue.get(0);
                queue.remove(0);
                for (int i = 0; i < edges.length; i++) {
                    int u = edges[i][0], v = edges[i][1], w = edges[i][2];
                    System.out.printf("u:%d,v:%d,w:%d\n", u, v, w);
                    if (a == u && dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        if (!used[v]) {
                            queue.add(v);
                            nums[v]++;
                            if (nums[v] >= N) continue;
                            used[v] = true;
                        }
                    }
                }
                used[a] =false;
            }
            long max = 0;
            for (int i = 1; i <= N; ++i) max = Math.max(max, dist[i]);
            return max == INF ? -1 : (int) max;
        }
```

### 方法7:Floyd

```java
        Integer INF = Integer.MAX_VALUE;

        public int networkDelayTime(int[][] edges, int N, int K) {
            int[][] dist = new int[N + 1][N + 1];
            for (int i = 1; i <= N; ++i) {
                for (int j = 1; j <= N; ++j) {
                    dist[i][j] = i == j ? 0 : INF;
                }
            }
            for (int[] edge : edges) {
                dist[edge[0]][edge[1]] = edge[2];
            }
            for (int a = 1; a <= N; ++a) {
                for (int i = 1; i <= N; ++i) {
                    for (int j = 1; j <= N; ++j) {
                        int tmp = (dist[i][a] == INF || dist[a][j] == INF) ? INF : dist[i][a] + dist[a][j];
                        if (dist[i][j] > tmp) {
                            dist[i][j] = tmp;
                        }
                    }
                }
            }
            int max = -1;
            //找K出发的点，K->i，最长的路径
            for (int i = 1; i <= N; ++i) {
                max = Math.max(max, dist[K][i]);
            }
            return max == INF ? -1 : max;
        }
```

### 推荐阅读

| #    | github.io地址                                                | 关键词                    |
| ---- | ------------------------------------------------------------ | ------------------------- |
| 1    | [一文掌握Dijkstra算法](https://wat1r.github.io/2020/09/23/dijkstra-algorithm-master/) | Graph,Shortest Path,Model |
| 2    | [一文掌握Bellman-Ford算法](https://wat1r.github.io/2020/09/22/bellman-ford-algorithm-master/) | Graph,Shortest Path,Model |
| 3    | [一文掌握SPFA算法](https://wat1r.github.io/2020/09/21/spfa-algorithm-master/) | Graph,Shortest Path,Model |
| 4    | [一文掌握Floyd算法](https://wat1r.github.io/2020/09/23/floyd-algorithm-master/) | Graph,Shortest Path,Model |

