## DFS_BFS之火柴拼正方形[Poodle]

![dog-2638189_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\Untitled.assets\dog-2638189_640.jpg)

![image-20200917090628019](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\Untitled.assets\image-20200917090628019.png)

### 方法1:DFS

#### 思路

能拼出正方形需要满足两个条件：

- 用完数组里的每一个元素，每一边长度相等，等于总长度的1/4
- 分成四组，所有火柴落到四组中

```java
    //目标正方形的边长
    int maxEdgeLen = 0;


    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        boolean[] visited = new boolean[n];
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;
        this.maxEdgeLen = sum / 4;
        //从0这条边开始的，也可以从3这条边开始
        return dfs(nums, 0, visited, 0, 0);
    }


    /**
     * @param nums        源数组
     * @param edgeIdx     当前边的索，一共4条边，0,1,2,3 
     * @param visited     数组元素是否被访问
     * @param start       nums数组的位置索引
     * @param currEdgeLen 当前这条边已经累计的边长，不能超过maxEdgeLen
     * @return true/fasle 能形成一条边，返回T，否则F
     */
    private boolean dfs(int[] nums, int edgeIdx, boolean[] visited, int start, int currEdgeLen) {
        if (edgeIdx == 4) return true;
        //开始一条新的边，start 和 currEdgeLen 需要重置为0
        if (currEdgeLen == maxEdgeLen) {
            return dfs(nums, edgeIdx + 1, visited, 0, 0);
        }
        System.out.printf("edgeIdx:%d,start:%d,currEdgeLen:%d\n", edgeIdx, start, currEdgeLen);
        //从当前的start位置开始，start之前的已经用掉了，防止重复访问
        for (int i = start; i < nums.length; i++) {
            if (!visited[i] && currEdgeLen + nums[i] <= maxEdgeLen) {
                visited[i] = true;
                if (dfs(nums, edgeIdx, visited, i + 1, currEdgeLen + nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
```



