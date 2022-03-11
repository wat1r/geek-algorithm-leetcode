## 课程表三剑客之课程表II[Dung Beetle]

![beetle-2655575_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表II[Ladybird].assets\beetle-2655575_640.png)

![image-20200820204320060](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表II[Ladybird].assets\image-20200820204320060.png)

### 方法1:BFS+入度

> 这题完全就是207题的姐妹题，207要求是否能学完课程，返回布尔，这里是返回一个学完课程的路径

> 下面是207的代码

```java
        public boolean canFinish(int numCourses, int[][] prerequisites) {
        //1.计算入度表，[u,v] v->u
        //入度(indegree)就是有向图中指向这个点的边的数量，即有向图的某个顶点作为终点的次数和
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        //2.将入度为0的点放入queue中,queue中装的是点的下标索引，即是入度表中的索引
        //题意：你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。课程名称与索引是对应的
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        //3.轮转这个queue，这个queue中弹出的节点其实是v->u中的v
        //弹出一个节点，将课程数-1，在prerequisites中找到这个节点的指向节点，即通过v->u
        //如果u找到了，将其入度-1，如果入度为0了，将其加到queue中
        while (!queue.isEmpty()) {
            int pre = queue.poll();//这个是v->u的v
            numCourses--;
            for (int[] p : prerequisites) {
                if (p[1] != pre) continue;
                indegrees[p[0]]--;
                if (indegrees[p[0]] == 0) queue.offer(p[0]);
            }
        }
        //判断有没有剩余的课程数
        return numCourses == 0;
    }
```

只需要添加记录路径的逻辑

- 需要一个$array$来记录路径的大小，大小等于$numCourse$，因为要是能学完的话，是需要$numCourse=0$的
- $index$记录加入路径的游标

#### 完整代码

```java
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        int[] res = new int[numCourses];//添加的code
        for (int[] p : prerequisites) indegrees[p[0]]++;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        int index = 0;//添加的code
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            res[index++] = pre;//添加的code
            numCourses--;
            for (int[] p : prerequisites) {
                if (p[1] != pre) continue;
                indegrees[p[0]]--;
                if (indegrees[p[0]] == 0) queue.offer(p[0]);
            }
        }
        return numCourses == 0 ? res : new int[0];//添加的code
    }

```











