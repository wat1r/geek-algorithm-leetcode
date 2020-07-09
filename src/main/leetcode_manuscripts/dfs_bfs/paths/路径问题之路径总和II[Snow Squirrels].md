

## 路径问题之路径总和II[Snow Squirrels]

![form-3370956_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和II[Snow Squirrels].assets\form-3370956_640.jpg)

![image-20200709202435234](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和II[Snow Squirrels].assets\image-20200709202435234.png)

> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。**

- 本题可作为$112$题的$follow$ $up$，区别于$112$题的找到是否存在符合题意的路径，$113$题要求收集所有路径的集合

### 方法1：DFS

- 主题框架沿用$112$的方法1，区别于在于准备两个$list$,一个$resultList$用来收集结果集，一个$subList$用来收集每一组路径的集合

![image-20200707194904172](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和II[Snow Squirrels].assets\image-20200707194904172.png)

#### 出口条件

- 当前节前是叶子节点且当前的路径和是目标的$sum$值，开始收集
- 添加/移除当前节点:

```
 subList.add(root.val);
 subList.remove(subList.size() - 1);
```

#### 整体代码

```java
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) return resultList;
        dfs(root, sum, resultList, new ArrayList<>());
        return resultList;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> resultList, List<Integer> subList) {
        if (root == null) return;
        subList.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) resultList.add(new ArrayList<>(subList));
        dfs(root.left, sum - root.val, resultList, subList);
        dfs(root.right, sum - root.val, resultList, subList);
        subList.remove(subList.size() - 1);
    }
```







437

```java
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        int res = 0;
        if (root.val == sum) res++;
        res += helper(root.left, sum - root.val);
        res += helper(root.right, sum - root.val);
        return res;
    }
```





















113/437

