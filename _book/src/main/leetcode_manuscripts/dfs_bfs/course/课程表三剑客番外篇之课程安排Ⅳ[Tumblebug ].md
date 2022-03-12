## 课程表三剑客番外篇之课程安排Ⅳ[Tumblebug ]







![scarab-160646_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客番外篇之课程安排Ⅳ[Tumblebug ].assets\scarab-160646_640.png)





![scripts](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客番外篇之课程安排Ⅳ[Tumblebug ].assets\scripts.jpg)

### 方法1：BFS

```java
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        //生成有向图，p[0]表示源，p[1]表示终
        // Map<Integer, Set<Integer>> key是p[0] value是p[1]，但可能存在多个
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : prerequisites) {
            Set<Integer> set = map.getOrDefault(p[0], new HashSet<>());
            set.add(p[1]);
            map.put(p[0], set);
        }
        //定义两个顶点是否能形成路径，可达，候选列表
        Set<String> candidates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            bfs(map, i, n, candidates);
        }
        //开始进行筛选
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            if (candidates.contains(q[0] + "->" + q[1])) res.add(true);
            else res.add(false);
        }
        return res;
    }

    /**
     * @param map        有向图的map
     * @param i          当前遍历到的节点，或者说课程，节点索引与课程 一一对应
     * @param n          总的课程数
     * @param candidates 候选列表
     */
    private void bfs(Map<Integer, Set<Integer>> map, int i, int n, Set<String> candidates) {
        Queue<Integer> queue = new LinkedList<>();//queue
        boolean[] visited = new boolean[n];//所有课程是否被访问过
        if (!map.containsKey(i)) return;//如果没有以当前课程作为源的，不会形成
        queue.offer(i);//加入到queue中
        visited[i] = true;//标记被访问过
        while (!queue.isEmpty()) {
            int curr = queue.poll();//弹出当前的i并生成它的邻接的点
            Set<Integer> nexts = map.get(curr);
            if (nexts != null) {//没有邻接的点时，不遍历
                for (int next : nexts) {
                    if (!visited[next]) {//被访问过，也不再遍历
                        candidates.add(i + "->" + next);//生成
                        visited[next] = true;//标记被访问
                        queue.offer(next);//进queue
                    }
                }
            }
        }
    }
```

### 方法2：Floyd

> TODO