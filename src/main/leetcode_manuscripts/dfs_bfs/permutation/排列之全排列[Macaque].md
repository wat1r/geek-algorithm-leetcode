## 排列之全排列[Macaque]

> 欢迎阅读、点赞、转发，你的据说之间，我的动力源泉

### 0.DFS算法框架

```python
def DFS(n){                         //可以描述阶段的状态
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











### 1.全排列

![1591629370557](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列之全排列[Macaque].assets\1591629370557.png)