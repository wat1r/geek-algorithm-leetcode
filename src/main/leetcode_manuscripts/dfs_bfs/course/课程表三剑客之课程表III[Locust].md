## 课程表三剑客之课程表III[Locust]



![istockphoto-923812854-1024x1024](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表III[Locust].assets\istockphoto-923812854-1024x1024.jpg)

![image-20200821083042457](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表III[Locust].assets\image-20200821083042457.png)



### 方法1：贪心+优先队列

> 思路来自官方题解

> 前置定义

$t$:每一门课程有一定的持续上课时间（课程时间），即课程需要耗费的时间，$duration$

$d$:关闭时间第$ d$ 天,即完成该课程时的截止时间，$deadline$

---

> 分析

**结论1：如果两门课 $(t1, d1)$ 和 $(t2, d2)$ 满足 $d1 <= d2$，即后者的结束时间不晚于前者，那么我们先学习前者再学习后者总是最优的**

$Plan1$:**如果先学习前者（如图的Plan1）**，需要满足下面两个等式：（即其耗费的时间加上再次学这门课程的时间的和应该小于这门课程的截止时间）

$x + t1 <= d1$ $and$  $x + t1 + t2 <= d2$

其中$x$是在学习$t1$课程前耗费的总时间，可能已经学了很多门了

$Plan2$:**如果先学习后者（如图的Plan2）**，需要满足下面两个等式：

$x + t2 <= d2$ $and$  $x + t1 + t2 <= d1$

这时候我们带上结论之前带的预设条件$d1 <= d2$，很容易得到学习后者时：$x + t1 + t2 <= d1<=d2$  

而本身$x + t1 + t2 <= d1$ 因为所有数都是正数，我们在等式左边抹掉一个$t2$,也是成立的，即 $x + t1 <= d1$ 

合并而来即： $x + t1 <= d1$  $and$  $x + t1 + t2 <= d1<=d2$   这个条件和$Plan1$:的条件一致，也就是说，执行$Plan2$:也能得到$Plan1$的结论，执行$Plan1$最优，因为反过来，不一定可以：执行$Plan2$后，就不一定走得通了，如下图：

![image-20200821084410757](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表III[Locust].assets\image-20200821084410757.png)

在$Plan1$中，我们故意设置的这些$d$都是严格按截至时间点来设置的（考虑边界，250,251这种细节比较麻烦，没有区分的那么细），$Plan1$中先学完前面的所有课程耗费了$x=250$，接着学$t1$,$t2$，很完美

在$Plan2$中，如果先学$t2$课程时，没问题，其$d2=510$，满足条件，但是当我们学完后再学$t1$时，我们发现我们学不了，因为$t1$的截止时间$d1=330$,学完$t2$后，时间都已经来到$250+180=430$,这时候$t1$的课程已经结束了

**综上分析：我们需要在每一步，找到$Plan1$的方案，也就是局部最优解**

---

基于上面的分析，可以将课程按截止时间$d$进行递增排序，然后**我们在前$i$门课程中最多可以选择$k$门课，并且这$k$门课的总时长最短(称为最优方案)**，得到下面的等式：(注意这个表述的结论)

```java
t1 + t2 <= d2
t1 + t2 + t3 <= d3
...
t1 + t2 + ... + tk <= dk
```

注意：这里的第$k$门课程是经过重新排序后的，其与题目一开始的给出的顺序不同了

这时候如果第$i+1$门课$(t0,d0)$进来了，这个课程我们学不学，取决于$k$门课程($t1,t2...tk$)的总时长$t$与$t0$之间的大小，

```java
如果
t1+t2+...tk+t0<=d0
这时候
(t0,d0)这个课程是可选的，此时前i+1门课程的最优方案变成了t1,t2...tk,t0 这k+1门课程
```

上面的这个为**结论2**

**反证法证明此结论**

情况1：选取了$k+1$门课程 and 总时长$y$小于 $t1+t2+...tk+t0$, 我们去除 $(t0,d0)$这门课程时，这时候的最优方案耗费的时长是$z$即：

```java
y<(t1+t2+...tk+t0)
去除t0后
z=y-t0<(t1+t2+...tk)
```

即此时方案的耗费时长比$t1+t2+...tk$短，这个与我们每一步的最优解，**预设的条件，$t1+t2+...tk$是前$i$门课程的最优解，相悖，反证法得证**

情况2：选取超过$k+1$门课程 and 总时长$y$小于 $t1+t2+...tk+t0$，证明思路类似

**结论3：找出前$i$门课程中的最优解$t1,t2...tk$中的最大值，也就是耗费时长最长的那门课程,记为$tj$,如果第$i+1$门课程$(t0,d0)$，进来，$tj>t0$,则$tj$可以被$t0$替换，从而产生新的最优解$t1,t2...tj-1,tj+1...tk,t0$*，如果$tj<=0$,则$tj$不需要被替换，新的最优解维持不变$t1,t2...tj-1,tj,tj+1...tk$**

**反证法证明此结论**

```
todo
```

> 思路

- 先将课程按截至时间$d$递增排序
- 准备一个优先队列，为大跟堆，存储是$t$,即学习课程耗费的时间，当新来一门课程$(t0,d0)$时
  - 当优先队列的所有课程之和$t$+$t0$<=$d0$时，$(t0,d0)$加入到队列中，并更新总时长$t$
  - 当优先队列的所有课程之和$t$+$t0$>$d0$时，找到优先队列中的最长耗费时长的课程$max(t)$,记为$(tj,dj)$,如果$tj$>$t0$,移除$tj$，并将$t0$加入优先队列
- 返回优先队列的大小，即最后可以学的课程

#### 完整代码

```java
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));//递增排序
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);//大根队
        int time = 0;
        for (int[] course : courses) {
            int duration = course[0];
            int deadline = course[1];
            if (time + duration <= deadline) {
                pq.offer(duration);
                time += duration;
            } else {
                if (!pq.isEmpty() && pq.peek() > duration) {
                    time += duration - pq.poll();
                    pq.offer(duration);
                }
            }
        }
        return pq.size();
    }
```

#### **复杂度分析**

- **时间复杂度**：$O(NlogN)$，其中 $N$ 是课程的数目。
- **空间复杂度**：$O(N)$。



### 方法2：递归

> 动态规划的几大要素：状态，选择以及边界条件

#####  参数定义：

- $N$件物品
- $V$背包的总容量
- $Ci$放入第$i$件物品耗费的费用
- $Wi$放入第$i$件物品得到的价值

##### **定义状态**

$F[i,v]$表示前$i$件物品恰好放入一个容量为$v$的背包可以获得的最大价值，而转移方程应为是：$F[i,v]=max(F[i-1,v],F[i-1,v-Ci]+Wi)$

- 解释：将第$i$件物品放入容量为$v$的背包中，只需要基于$i-1$件物品的基础上做第$i$件物品的放与不放的问题
  - 不放入第$i$件物品，获得的最大价值是$F[i-1,v]$
  - 放入第$i$件物品，获得的最大价值是$F[i-1,v-Ci]+Wi$,因为第$i$件物品已经放进去背包了，留给前$i-1$件物品的背包容量只有$v-Ci$,而通过放入第$i$件物品，获取的价值是$Wi$

##### 边界条件

- $dp[0][0]$表示当选择是$0$个物品时，在没有物品，背包体积为$0$时，不装任何东西的时候$dp[0][0]=0$
- $F[i-1,v-Ci]$其中$v>=Ci$，不然为负数，没有意义

##### 核心代码

```
dp[N+1][V+1]
dp[0][0...V]=0
dp[0...N][0]=0
for i in range(1,N+1)
	for j in range(1,V+1)
    	dp[i][j]=max(dp[i-1][j],dp[i-1][j-v[i]]+w[i])
```



> 此方法超时

```java
        public int scheduleCourse(int[][] courses) {
            Arrays.sort(courses, Comparator.comparing(a -> a[1]));
            return backtrack(courses, 0, 0);
        }

        private int backtrack(int[][] courses, int duration, int index) {
            if (index == courses.length) return 0;

            int[] currCourse = courses[index];
            int currDuration = currCourse[0];
            int currDeadline = currCourse[1];
            int choose = 0;
            if (duration + currDuration <= currDeadline) {
                choose = 1 + backtrack(courses, duration + currDuration, index + 1);
            }
            int notChoose = backtrack(courses, duration, index + 1);
            return Math.max(choose, notChoose);
        }
```













