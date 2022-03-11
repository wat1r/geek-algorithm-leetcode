## 01背包问题之最后一块石头的重量[Pelican]

![pelican-2075752_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解最后一块石头的重量[Pelican].assets\pelican-2075752_640.jpg)

![image-20200916202954298](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解最后一块石头的重量[Pelican].assets\image-20200916202954298.png)

### 1.最后一块石头的重量I

- 最大堆，每次弹出两个

#### 方法1：优先队列

```java
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int stone : stones) pq.offer(stone);
        while (!pq.isEmpty() && pq.size() >= 2) {
            int first = pq.poll(), second = pq.poll();
            if (first != second) {
                pq.offer(first - second);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
```

### 2.最后一块石头的重量II

![image-20200916203106226](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解最后一块石头的重量[Pelican].assets\image-20200916203106226.png)

#### 思路

将$stones$的总和分成两堆

- 当两堆的数量恰好相等，每个堆的石头的数量恰好是$sum/2$($sum$是偶数)，这两堆石头碰撞，想象成正负离子碰撞，稀碎后，剩下$0$，也就是渣
- 当两堆的数量不相等的时候，有堆大，有堆小，但是要尽可能地缩小两个堆的差，拿到那堆数量和小的那堆，做成负离子，然后放进去掉那堆负离子的堆中，正负离子碰撞，剩下的正离子就是要求的，核心点是求出那堆找出若干块石头，似的其种类接近$sum/2$,上限是这个$sum/2$

#### 方法1:朴素版01背包

##### 定义状态

$f(i,j)$表示当遇到第i个物品时，背包容量为j时，能获得的最大价值

来到本题：**$f(i,j)$表示遇到第i个石头时，j的背包容量（初始时从1开始到sum/2结束），能装的石头的重量**

##### 转移方程

对于第i个石头时，有两种决定，选与不选：

- 不选，依赖前一个石头的情况:$f[i-1][j]$
- 选，减去当前的背包的容量，并且加上当前的价值$v[i]$,  得到$f[i-1][j-stone[i-1]]+-stone[i-1]$

注：下标的对应情况，初始化时$f[n+1][sum/2+1]$, 对应的$stones$的下标要相差一位

```java
    public int lastStoneWeightII(int[] stones) {

        int n = stones.length;
        int sum = 0;
        for (int stone : stones) sum += stone;
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int i = 1; i <= n; ++i) {
            int currStone = stones[i - 1];
            for (int j = 1; j <= sum / 2; ++j) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                if (j >= currStone) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - currStone] + currStone);
                }
            }
        }
        int res = sum - 2 * dp[n][sum / 2];
        return res;
    }
```

#### 方法2:压缩版01背包

压缩空间，倒序遍历，详解文后01背包链接

```java
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) sum += stone;
        int m = sum / 2;
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; ++i) {
            int currStone = stones[i];
            for (int j = m; j >= currStone; j--) {
                dp[j] = Math.max(dp[j], dp[j - currStone] + currStone);
            }
        }
        int res = sum - 2 * dp[m];
        return res;
    }
```







