## 回溯之重新安排行程[Chihuahua]



![animal-3417350_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\journey\回溯之重新安排行程[Chihuahua].assets\animal-3417350_640.jpg)



![image-20200916083220705](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\journey\回溯之重新安排行程[Chihuahua].assets\image-20200916083220705.png)

### 0.回溯模板

- 全局变量
- 参数设计
- 完成条件
- 递归过程

```python
res = []    # 定义全局变量保存最终结果
state = []  # 定义状态变量保存当前状态
p,q,r       # 定义条件变量（一般条件变量就是题目直接给的参数）
def back(状态，条件1，条件2，……):
    if # 不满足合法条件（可以说是剪枝）
        return
    elif # 状态满足最终要求
        res.append(state)   # 加入结果
        return 
    # 主要递归过程，一般是带有 循环体 或者 条件体
    for # 满足执行条件
    if  # 满足执行条件
        back(状态，条件1，条件2，……)
back(状态，条件1，条件2，……)
return res
```











### 方法1:回溯

```java
    //收集结果返回
    List<String> resList = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        //生成graph
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }
        //每个机场指向的机场按字典序排序
        //如 JFK->[LGB,LGA] 变成 JFK->[LGA,LGB]
        for (ArrayList<String> values : graph.values()) {
            Collections.sort(values);
        }
        dfs("JFK", graph);
        return resList;

    }

    /**
     *dfs
     * @param candidate 当前去的机场
     * @param graph 整个机场的图
     */
    private void dfs(String candidate, Map<String, ArrayList<String>> graph) {
        ArrayList<String> nexts = graph.get(candidate);
        //当前机场如果还是有机场可以，一直去目标机场
        while (nexts != null && !nexts.isEmpty()) {
            //去过这个机场，就将其移除掉，因为排序了，每次取第一个
            String next = nexts.remove(0);
            dfs(next, graph);
        }
        System.out.println(candidate);
        //最先开始往resList加入的是哪些以当前机场为from，没有to可去的
        resList.add(0, candidate);
    }

```





### Reference

- [回溯模板](https://www.jianshu.com/p/2784b2e84ff8)