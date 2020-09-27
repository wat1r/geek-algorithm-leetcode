## DFS_BFS之打开转盘锁[Saint Bernard]

![dog-2327757_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之打开转盘锁[Saint Bernard].assets\dog-2327757_640.jpg)

![image-20200927202905296](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之打开转盘锁[Saint Bernard].assets\image-20200927202905296.png)

![leet](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之打开转盘锁[Saint Bernard].assets\leet-1601209765817.png)

### 方法1:BFS

```java
    public int openLock(String[] deadends, String target) {
        //当前处理的转盘字符
        Queue<String> queue = new LinkedList<>();
        //死亡转盘字符
        Set<String> deads = new HashSet<>();
        //字符被访问过的列表
        Set<String> vis = new HashSet<>();
        for (String d : deadends) deads.add(d);
        //单个源点触发
        queue.offer("0000");
        vis.add("0000");
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                //跳过死亡转盘字符
                if (deads.contains(curr)) continue;
                //找到了
                if (target.equals(curr)) {
                    return dist;
                }
                //四个数每个位置两种选择up down 4*2 =8 种
                for (int j = 0; j < 4; j++) {
                    String up = getUp(curr, j);
                    //要没被访问过的
                    if (!vis.contains(up)) {
                        queue.offer(up);
                        vis.add(up);
                    }
                    String down = getDown(curr, j);
                    if (!vis.contains(down)) {
                        queue.offer(down);
                        vis.add(down);
                    }
                }
            }
            //层数+1，当前层结束
            dist++;
        }
        return -1;
    }

    /**
     * 生成当前字符往上递增的字符 如 9000-->1000  2000->3000
     * @param base
     * @param idx
     * @return
     */
    private String getUp(String base, int idx) {
        char[] chas = base.toCharArray();
        if (chas[idx] == '9') chas[idx] = '0';
        else chas[idx]++;
        return String.valueOf(chas);
    }

    /**
     * 生成当前字符往下递增的字符 如 9000-->8000  1000->9000
     * @param base
     * @param idx
     * @return
     */
    private String getDown(String base, int idx) {
        char[] chas = base.toCharArray();
        if (chas[idx] == '0') chas[idx] = '9';
        else chas[idx]--;
        return String.valueOf(chas);
    }
```

