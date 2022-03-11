## DFS_BFS之递增子序列[Haski]



![husky-3451026_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之递增子序列[Haski]md.assets\husky-3451026_640.jpg)

### 方法1:DFS

#### 思路

- $dfs(nums, currIdx)$ 传入当前的$idx$,以此为出发点，开始$dfs$
- 进入$dfs$的条件，后面的元素大于上一轮传进啦的$currIdx$
- 出口条件：当子列表的数量大于等于2的时候，这一个为一个新的
- 去重：$set$去重

```java
//记录结果    
List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, -1);
        return resList;
    }

    List<Integer> subList = new ArrayList<>();

    private void dfs(int[] nums, int currIdx) {
        if (subList.size() >= 2) resList.add(new ArrayList<>(subList));
        Set<Integer> set = new HashSet<>();
        for (int i = currIdx + 1; i < nums.length; ++i) {
            if (set.contains(nums[i])) continue;
            set.add(nums[i]);
            if (currIdx == -1 || nums[i] >= nums[currIdx]) {
                subList.add(nums[i]);
                dfs(nums, i);
                subList.remove(subList.size() - 1);
            }
        }
    }
```





