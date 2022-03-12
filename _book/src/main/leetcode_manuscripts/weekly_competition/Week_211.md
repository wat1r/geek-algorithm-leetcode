## Week_211











#### 1625.执行操作后字典序最小的字符串







#### 1626. 无矛盾的最佳球队

> 贪心+dp （类似300题最长上升子序列）

#### 思路：

- 将分数与年龄的数组转成二维数组`arr`, [0] 存年龄，[1]存分数
- 将`arr`排序，按年龄从小到大排序，如果年龄相同，再按分数从小到大排序
- 初始化一个`dp`数组，`dp[i]`表示以`i`结尾的球员结束，所能拿到的的最大的分数
- 贪心：从当前`i`前一个`i-1`开始，向前遍历，找到`dp[i]`值最大的，里面的一层`for loop`结束后，加上`arr[i][1]`的值，记录遍历过程的的最大值`max`，最后返回这个`max`

```java
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{ages[i], scores[i]};
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][1] <= arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += arr[i][1];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
```

#### 另外一种方法

```java
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) arr[i] = new int[]{ages[i], scores[i]};
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i][1];
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j][1] <= arr[i][1]) dp[i] = Math.max(dp[i], dp[j] + arr[i][1]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

```

#### 1627.带阈值的图连通性

#### 思路

- 在[1,n]之间存在 n/z个z的倍数，这么多个倍数的数之间存在一条边，如果两两添加后，需要( n/z)^2条边，但可以不用考虑两两相连，只需要第0个点与第一个点 第1个点与第2个点相连，一次跳跃相连即可，有(n/z  -1 )条边

>  并查集

```java
   class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public boolean unoin(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
            if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
            if (ranks[rootX] == ranks[rootY]) {
                parents[rootY] = rootX;
                ranks[rootY]++;
            }
            return true;
        }

        public boolean connect(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            return rootX == rootY;
        }

    }
```

#### 方法1

```java
public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
    UnionFind uf = new UnionFind(n);
    for (int i = threshold + 1; i <= n; i++) {
        int times = 2;
        while (i * times <= n) {
            int x = i - 1;
            int y = i * times - 1;
            uf.unoin(x, y);
            times += 1;
        }
    }
    List<Boolean> res = new ArrayList<>();
    for (int[] q : queries) {
        int x = q[0] - 1, y = q[1] - 1;
        res.add(uf.connect(x, y));
    }
    return res;
}
```

##### 另外一种枚举方式1

```java
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        for (int i = threshold + 1; i <= n; i++)
            for (int j = i * 2; j <= n; j += i)
                uf.unoin(i-1, j-1); //注意uf从0开始的
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int x = q[0] - 1, y = q[1] - 1;
            res.add(uf.connect(x, y));
        }
        return res;
    }
```

##### 另外一种枚举方式2

```java
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        for (int z = threshold + 1; z <= n; z++)
            for (int x = z, y = z * 2; y <= n; x += z, y += z)
                uf.unoin(x - 1, y - 1);
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int x = q[0] - 1, y = q[1] - 1;
            res.add(uf.connect(x, y));
        }
        return res;
    }

```



