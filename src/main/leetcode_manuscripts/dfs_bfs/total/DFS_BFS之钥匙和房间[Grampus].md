## DFS_BFS之钥匙和房间[Grampus]



![dolphin-2329165_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\total\DFS_BFS之钥匙和房间.assets\dolphin-2329165_640.jpg)







![image-20200831100222405](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\total\DFS_BFS之钥匙和房间.assets\image-20200831100222405.png)



### 方法1:BFS

- 利用$set$记录被访问的房间的个数，如果刚好等于所有被访问的房间，为$true$，否则为$false$
- 当房间被访问过，就不再添加房间进$set$，初始化时，将$0$号房间加入到$set$中

```java
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int n = rooms.size();
        List<Integer> zeroRoom = rooms.get(0);
        set.add(0);
        for (int i : zeroRoom) {
            queue.offer(i);
            set.add(i);
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> currRoom = rooms.get(curr);
            for (int c : currRoom) {
                if (!set.contains(c)) {
                    queue.offer(c);
                    set.add(c);
                }

            }
        }
        return set.size() == n;
    }
```



另外一种写法：

```java
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        List<Integer> zeroRoom = rooms.get(0);
        visited[0] = true;
        for (int i : zeroRoom) queue.offer(i);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            visited[curr] = true;
            List<Integer> currRoom = rooms.get(curr);
            for (int c : currRoom) {
                if (!visited[c]) {
                    queue.offer(c);
                    visited[c] = true;
                }
            }
        }
        for (boolean b : visited) if (!b) return false;
        return true;
    }
```

### 方法2:DFS

- $dfs$ 的三个参数：$rooms$ 房间，$idx$ 当前房间的索引号，$visited$ 被访问的房间列表
- 出口条件：当改党建被范访问，返回
- 计算：房间都被访问，返回$true$,有一个房间未被访问，返回$false$

```java
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, 0, visited);
        for (boolean b : visited) if (!b) return false;
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int idx, boolean[] visited) {
        if (visited[idx]) return;
        visited[idx] = true;
        for (int i : rooms.get(idx)) dfs(rooms, i, visited);
    }
```

