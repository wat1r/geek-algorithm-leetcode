## 双向BFS之单词搜索[Shih Tzu]

### 0.背景

> 双向bfs适用于知道起点和终点的状态下使用，从起点和终点两个方向开始进行搜索，可以非常大的提高单个bfs的搜索效率

#### 伪代码

```c++
while(!queue.empty()) {
    t=queue.front();
    queue.pop();
    for(每个t的儿子s) {
        if(正向搜索)
            if(vis1[s]==0) {//找到答案
                if(vis2[s]==1)
                    over;
                vis1[s]=1;
                queue.push(s);
            }
        else//反向搜索
            if(vis2[s]==0) {//找到答案
                if(vis1[s]==1)
                    over;
                vis2[s]=1;
                queue.push(s);
            }
    }
}
```

#### 逐层扩展

```c++
void bfs()
{
    for(int i = 1; i <= max; i++)
      {
        正向bfs算式;
        if(产生状态在反向曾产生过)  输出结果，结束程序;
      }
    for(int i = 1; i <= max; i++
      {
        反向bfs算式;
        if(产生状态在正向曾产生过)  输出结果，结束程序;
      }
}
```

#### 逐节点扩展

```c++
void bfs()
{
    for(int i = 1; i <= max; i++)
      {
        正向bfs算式;
        if(产生状态在反向曾产生过)  输出结果，结束程序;
        反向bfs算式;
        if(产生状态在正向曾产生过)  输出结果，结束程序;
      }
}
```





![image-20200928100421704](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\双向BFS之单词搜索[].assets\image-20200928100421704.png)



```java
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return 0;
            Set<String> beginSet = new HashSet<>();
            Set<String> endSet = new HashSet<>();
            Set<String> visitdSet = new HashSet<>();
            beginSet.add(beginWord);
            endSet.add(endWord);
            int steps = 1;
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                if (beginSet.size() > endSet.size()) {
                    Set<String> tmpSet = beginSet;
                    beginSet = endSet;
                    endSet = tmpSet;
                }
                Set<String> tmpSet = new HashSet<>();
                System.out.printf("beginSet:%s\n", JSON.toJSONString(beginSet));
                System.out.printf("endSet:%s\n", JSON.toJSONString(endSet));
                for (String word : beginSet) {
                    char[] chas = word.toCharArray();
                    for (int i = 0; i < chas.length; i++) {
                        char src = chas[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == src) continue;
                            chas[i] = c;
                            String nextWord = String.valueOf(chas);
                            //已经找到了
                            if (endSet.contains(nextWord)) {
                                return steps + 1;
                            }
                            //没有被访问过，且单词在wordSet中，添加进tmpSet ，本轮结束后赋值给beginSet
                            if (!visitdSet.contains(nextWord) && wordSet.contains(nextWord)) {
                                tmpSet.add(nextWord);
                                visitdSet.add(nextWord);
                            }
                        }
                        chas[i] = src;
                    }
                }
                beginSet = tmpSet;
                steps++;
            }
            return 0;
        }
```





### Reference

- https://blog.csdn.net/weixin_43501684/article/details/90147421
- https://my.oschina.net/u/4160637/blog/4359629

- https://blog.csdn.net/mashiro_ylb/article/details/78261639
- https://leetcode.com/problems/open-the-lock/discuss/110237/Regular-java-BFS-solution-and-2-end-BFS-solution-with-improvement



> 1284
>
> 752
>
> Solitaire