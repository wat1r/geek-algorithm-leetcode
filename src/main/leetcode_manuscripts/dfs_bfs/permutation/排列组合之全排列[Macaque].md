## 排列组合之全排列[Macaque]

![monkey-1179368_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之全排列[Macaque].assets\monkey-1179368_960_720.jpg)

> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。**

### 0.基础框架

- $DFS$: `Depth First Search`  深度优先搜索，简称深搜
- $BFS$：`Breadth First Search`  广度优先搜索，简称广搜

#### 0.1.$DFS$算法框架

```python
def dfs(n){                         //可以描述阶段的状态
	if(valid) {收集结果，返回}	        //出口条件
	if(pruning) return;             //剪枝，这一步是为了加快回溯过程，降低程序执行时间
	for(i:1~p){                      //选择该阶段的所有决策
		选择可行决策;                   //剪枝的一种 
		add;						  //标记已访问该点
		DFS(n+1);                     //进入下一阶段
		recover;                      //还原
	}
}
```

#### 0.2.$BFS$算法框架

```python
def bfs(){
    q.push(head);//一般为q这种优先队列来处理bfs问题
    while(!q.empty()){
        temp=q.front;//弹出元素
        q.pop(); 
        if(temp为目标状态)输出解 
        if(temp不合法)continue;
        if(temp合法)q.push(temp+Δ);
    }
}
一般也会设置一些visit[] 来记录元素访问与否，做剪枝
```

>  举个例子，假如你在学校操场，老师叫你去国旗那集合，你会怎么走？ 假设你是瞎子，你看不到周围，那如果你运气差，那你可能需要把整个操场走完才能找到国旗。这便是盲目式搜索，即使知道目标地点，你可能也要走完整个地图。 假设你眼睛没问题，你看得到国旗，那我们只需要向着国旗的方向走就行了，我们不会傻到往国旗相反反向走，那没有意义。 这种有目的的走法，便被称为启发式的 

- 下面左边图是$bfs$，右边图是$dfs$

- 有趣的链接：https://qiao.github.io/PathFinding.js/visual/ 

  ![dfs](C:\Users\lwh\Desktop\dfs.gif)

### 1.全排列

![1591629370557](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之全排列[Macaque].assets\1591629370557.png)

#### 方法1：$DFS$  + $visit$数组

> 一般使用visited，used等数组来记录当前元素是否被访问过，

```java
   public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) return resList;
        boolean[] visited = new boolean[nums.length];
        dfs1st(nums, resList, new ArrayList<>(), visited);
        return resList;
    }

    /**
     * @param nums      源数组
     * @param resList   结果集
     * @param levelList 每一层的子结果集
     * @param visited   记录当前的元素是否被访问过
     */
    private void dfs1st(int[] nums, List<List<Integer>> resList, List<Integer> levelList, boolean[] visited) {
        //当子结果集的大小等于源数组的长度时，即源数组整个已经访问结束，排列结束，开始收集结果
        if (levelList.size() == nums.length) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        //for loop 整个源数组
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;//当前元素被访问过，跳过
            visited[i] = true;//记录被访问过
            levelList.add(nums[i]);//添加到子结果集
            dfs1st(nums, resList, levelList, visited);//进入下一层深搜
            levelList.remove(levelList.size() - 1);//从当前的子结果集移除
            visited[i] = false;//从被访问过的列表中移除
        }
    }
```



#### 方法2：$DFS$  $original$

- 题意说$nums$数组无重复元素，可以利用`levelList.contains(num)`来做剪枝，不需要借助$visited$数组

```java
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) return resList;
        dfs2nd(nums, resList, new ArrayList<>());
        return resList;
    }

    private void dfs2nd(int[] nums, List<List<Integer>> resList, List<Integer> levelList) {
        if (levelList.size() == nums.length) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int num : nums) {
            if (levelList.contains(num)) continue;
            levelList.add(num);
            dfs2nd(nums, resList, levelList);
           levelList.remove(levelList.size()-1);
        }
    }
```

#### 方法3：回溯

- [回溯法](https://baike.baidu.com/item/回溯算法/9258495) 是一种通过探索所有可能的候选解来找出所有的解的算法。如果候选解被确认 *不是* 一个解的话（或者至少不是 *最后一个* 解），回溯算法会通过在上一步进行一些变化抛弃该解，即 *回溯* 并且再次尝试。

- 这里有一个回溯函数，使用第一个整数的索引作为参数$backtrack(first)$。

- 如果第一个整数有索引 $n$，意味着当前排列已完成。
- 遍历索引 $first$ 到索引 $n - 1$ 的所有整数。`Iterate over the integers from index first to index n - 1`.

- - 在排列中放置第 $i$个整数， 即$swap(nums[first], nums[i])$.
  - 继续生成从第$i$个整数开始的所有排列: $backtrack(first + 1)$.
  - 现在回溯，即通过$ swap(nums[first], nums[i])$ 还原.

```java
 public List<List<Integer>> permute3rd(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) return resList;
        List<Integer> levelList = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) levelList.add(num);
        backtrace(resList, levelList, n, 0);
        return resList;
    }

    private void backtrace(List<List<Integer>> resList, List<Integer> levelList, int n, int index) {
        if (index == n) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = index; i < n; i++) {
            Collections.swap(levelList, index, i);
            backtrace(resList, levelList, n, index + 1);
            Collections.swap(levelList, index, i);
        }
    }
```

### 2.全排列II

![1591714144264](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之全排列[Macaque].assets\1591714144264.png)

#### 方法1：$DFS$

- 由于数字重复，与$46$题主体代码相似，这里需要做一次剪枝
- 需要将$nums$进行排序，在$visited[i]$是$true$时，需要加上前一个元素被访问过，当前元素与前一个元素相等，这两种情况需要$continue$掉
- 注意条件$(i - 1)>=0$ 注意等于号

```java
 public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) return resList;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(nums, resList, new ArrayList<>(), visited);
        return resList;
    }

    private void dfs(int[] nums, List<List<Integer>> resList, List<Integer> levelList, boolean[] visited) {
        if (levelList.size() == nums.length) {
//            System.out.println(String.format("collect:%s",JSON.toJSONString(levelList)));
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || ((i - 1) >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
//                System.out.println(JSON.toJSONString(levelList));
                continue;
            }
            visited[i] = true;
            levelList.add(nums[i]);
            dfs(nums, resList, levelList, visited);
            levelList.remove(levelList.size() - 1);
            visited[i] = false;
        }
    }
```

### 关联阅读









