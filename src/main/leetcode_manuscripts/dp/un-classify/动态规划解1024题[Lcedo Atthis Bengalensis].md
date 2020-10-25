## 动态规划解1024题[Lcedo Atthis Bengalensis]

![1603589968169](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1603589968169.png)

#### 思路

##### 定义状态

**`dp[i]`表示从`[0,i)` （左闭右开）这段区间范围 ，将其用给定的 `clip`完全覆盖，需要的最少的 `clip`的数量**

- 如上图，需要考虑的时是当前的区间`[start,end]`是否包含`i=3`这个范围，如果包含，如`B1`,`B2`,`B3`这三个区间，如果不包含，有两段可能，一段在`i`的前面，如`A`段，一段在`i`的后面，如`C`段，但是`A`,`C`两段孤立地看，对于获取`dp[i]`没有作用，因为其不能覆盖`i`，也就达到`dp[i]`的这个先决条件
- 在分析`B`段的时候，要找的是`B`段的开始`start`这个点结尾的`dp[B(start)`，即对于`B1`,`B2`，`B3`这三段的开始，即 1,1,2，获取到`dp[B(start)`这个值再加上`B`段本身

##### 转移方程

- 枚举`[0...i...T]`，
  - 枚举`clips`：找到`i`处于这些片段中小片段，每一轮`dp[i]` =`min{dp[i],dp[j]+1}`，`j`是符合条件的片段的开始坐标`start`

##### 初始化边界

- 因为求最小值，初始化时`INF`
- `dp[0]`表示`[0,0)`也就是完全不存在的空集合，形成这个集合需要的区间片段，也就是不需要任何片段，`dp[0]=0`

```java
    int INF = Integer.MAX_VALUE >> 1;

    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] c : clips) {
                if (c[0] <= i && i <= c[1]) {
                    dp[i] = Math.min(dp[i], dp[c[0]] + 1);
                }
            }
        }
        return dp[T] == INF ? -1 : dp[T];
    }
```

##### 打印

```java
clips = new int[][]{{0, 2}, {1, 3}, {1, 4}, {2, 5}, {4, 6}};
T = 6;
---
1--->[0,1,INF,INF,INF,INF,INF]
2--->[0,1,1,INF,INF,INF,INF]
3--->[0,1,1,2,INF,INF,INF]
4--->[0,1,1,2,2,INF,INF]
5--->[0,1,1,2,2,2,INF]
6--->[0,1,1,2,2,2,3]
```





