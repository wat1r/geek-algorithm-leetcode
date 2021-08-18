### [417. 太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)

```java
int m, n;
int[][] matrix;
int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    List<List<Integer>> resList = new ArrayList<>();
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return resList;
    this.m = matrix.length;
    this.n = matrix[0].length;
    this.matrix = matrix;
    boolean[][] visitedP = new boolean[m][n];//到达太平洋的bool[][]
    boolean[][] visitedA = new boolean[m][n];//到达大西洋的bool[][]

    for (int j = 0; j < n; j++) {
        dfs(0, j, visitedP);
        dfs(m - 1, j, visitedA);
    }
    for (int i = 0; i < m; i++) {
        dfs(i, 0, visitedP);
        dfs(i, n - 1, visitedA);
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (visitedP[i][j] && visitedA[i][j]) {//都能到达，开始收集结果
                resList.add(Arrays.asList(i, j));
            }
        }
    }
    return resList;
}


public void dfs(int i, int j, boolean[][] visited) {
    //标记被访问过
    visited[i][j] = true;
    for (int[] dir : directions) {
        int nextI = i + dir[0], nextJ = j + dir[1];
        //下一个坐标需要满足3个条件
        //1.在区域范围内
        //2.比上一个位置(i,j)的值要大，因为我们从外层逆着水流方向找的
        //3.没有被访问过
        if (inArea(nextI, nextJ) &&
                matrix[nextI][nextJ] >= matrix[i][j] && !visited[nextI][nextJ]) {
            dfs(nextI, nextJ, visited);
        }
    }


}

private boolean inArea(int i, int j) {
    return i >= 0 && i < m && j >= 0 && j < n;
}
```

### [11. 盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/)->[双指针]

- 取头尾双指针，木桶效应，短的那块决定了容器的面积

```java
        public int maxArea(int[] h) {
            int n = h.length, l = 0, r = n - 1;
            int res = 0;
            while (l < r) {
                res = Math.max(res, Math.min(h[l], h[r]) * (r - l));
                if (h[l] < h[r]) l++;
                else r--;
            }
            return res;
        }
```



### [139. 单词拆分](https://leetcode-cn.com/problems/word-break/)

#### 方法1:暴力递归

略，TLE

#### 方法2:自顶向下记忆化递归(Top-down)

```java
        //set记录当前字符位置，不能匹配wordDict 的索引，那些返回为false的结果
        Set<Integer> set = new HashSet<>();
        String s;
        List<String> wordDict;

        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            return dfs(0);
        }


        private boolean dfs(int idx) {
            if (idx == s.length()) return true;
            if (set.contains(idx)) return false;
            for (int i = idx + 1; i <= s.length(); i++) {
                if (!wordDict.contains(s.substring(idx, i))) continue;
                if (dfs(i)) return true;
                else set.add(i);
            }
            set.add(idx);
            return false;
        }
```

另外一种写法

```java
Map<Integer, Boolean> cache = new HashMap<>();
String s;
List<String> wordDict;

public boolean wordBreak(String s, List<String> wordDict) {
    this.s = s;
    this.wordDict = wordDict;
    return dfs(0);
}


private boolean dfs(int idx) {
    if (cache.containsKey(idx)) return cache.get(idx);
    if (idx == s.length()) return true;
    for (int i = idx + 1; i <= s.length(); i++) {
        if (!wordDict.contains(s.substring(idx, i))) continue;
        if (dfs(i)) {
            cache.put(i, true);
            return true;
        } else {
            cache.put(i, false);
        }

    }
    cache.put(idx, false);
    return false;
}
```

另外一种写法

```java
Boolean[] cache;
String s;
List<String> wordDict;

public boolean wordBreak(String s, List<String> wordDict) {
    this.s = s;
    this.wordDict = wordDict;
    cache = new Boolean[s.length() + 1];
    return dfs(0);
}


private boolean dfs(int idx) {
    if (cache[idx] != null) return cache[idx];
    if (idx == s.length()) return true;
    for (int i = idx + 1; i <= s.length(); i++) {
        if (!wordDict.contains(s.substring(idx, i))) continue;
        if (dfs(i)) {
            return cache[i] = true;
        } else {
            cache[i] = false;
        }
    }
    return cache[idx] = false;
}
```



#### 方法3:自底向上填表DP(Bottom-up)



```java
        public boolean wordBreak(String s, List<String> wordDict) {
            if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
                return false;
            }
            int n = s.length();
            //f[i]表示以s[i-1]结尾的字符串能否拆分成wordDict
            boolean[] f = new boolean[n + 1];
            f[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    if (f[j] && wordDict.contains(s.substring(j, i))) {
                        f[i] = true;
                        break;
                    }
                }
            }
            return f[n];
        } 
```



#### 方法4:BFS

- 队列中存放字符串的下标索引，想想成一个graph，`leetcode`  可以从0->4->8 也就是[0,3] leet , [4,7] code ，需要判断遍历的下标索引有没有到达s单词的尾部

```java
public boolean wordBreak(String s, List<String> wordDict) {
    Queue<Integer> q = new LinkedList<>();
    Set<Integer> vis = new HashSet<>();
    q.offer(0);
    while (!q.isEmpty()) {
        int curIdx = q.poll();
        for (int i = curIdx; i <= s.length(); i++) {
            if (vis.contains(i)) continue;
            if (wordDict.contains(s.substring(curIdx, i))) {
                if (i == s.length()) return true;
                q.offer(i);
                vis.add(i);
            }
        }
    }
    return false;
}
```







## TODO

### [1889. 装包裹的最小浪费空间](https://leetcode-cn.com/problems/minimum-space-wasted-from-packaging/)

