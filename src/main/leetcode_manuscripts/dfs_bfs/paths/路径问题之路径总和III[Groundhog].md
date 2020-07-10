1098 路径和 IV

## 路径问题之路径总和III[Groundhog]





- 当前$q$的队列头部节点并非是当前遍历的$num$的直接父级关系，而是其伯侄关系，如图中的$A$与$B$是兄弟关系，$B$与$C$是父子关系， $A$与$C$是伯侄关系

```java
                if (q.peek().hasChild) {
                    q.poll();
                    continue;
                }
                sum += q.poll().sum;
```









![image-20200710082436770](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和III[Groundhog].assets\image-20200710082436770.png)