## 石子游戏七匹狼系列之三[Mexican Wolf]





![wolf-2082333_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之三[Mexican Wolf].assets\wolf-2082333_640.jpg)

![image-20201224195410851](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之三[Mexican Wolf].assets\image-20201224195410851.png)





### 方法1：DP

> 思路来自官方

#### 定义状态

对于动态规划的题，一般题目问什么，定义的状态就是什么

**`f[i]`表示从`[i,i+1....n-1]`  即从`i`到末尾，当前玩家最多能从剩下的石子中拿到的石子的数量，剩下指的是 如果在这个区间`[i,i+1...n-1]`范围内，石子的总数是`t`,`Alice`拿走了`x`,  则`Bob`拿走`t-x`**

#### 转移方程

![image-20201224204223551](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之三[Mexican Wolf].assets\image-20201224204223551.png)

```java
public String stoneGameIII(int[] stones) {
    int n = stones.length;
    int[] suffixSum = new int[n];
    suffixSum[n - 1] = stones[n - 1];
    //后缀和
    for (int i = n - 2; i >= 0; i--) suffixSum[i] = suffixSum[i + 1] + stones[i];
    //多冗余一位 f[n] 表示在区间 [n...n]范围内当前玩家所能拿到的最多的石子的数量，这个区间不存在
    int[] f = new int[n + 1];
    f[n] = 0;
    for (int i = n - 1; i >= 0; --i) {
        int bestJ = f[i + 1];//找到拿走1 2 3 堆石子，另外一个玩家所能拿到的最小的石子数量
        for (int j = i + 2; j <= i + 3 && j <= n; j++) {
            bestJ = Math.min(bestJ, f[j]);
        }
        f[i] = suffixSum[i] - bestJ;
    }
    int total = suffixSum[0];
    return f[0] * 2 == total ? "Tie" : (f[0] * 2 > total ? "Alice" : "Bob");
}
```

### 方法2：DP

#### 定义状态

**`f[i]` 表示是一个比较值，表示在区间`[i,i+1...n-1]`内，当前玩家比另一位玩家最多能多拿的石子的数量**,如果这个差值是正数，说明当前玩家比另一位玩家拿的多，差值为0则表示一样多，负数则表示拿的少

#### 转移方程

`f[i]= max(sum[i:j-1]-f[j])   j∈[i+1:i+3]`

```java
public String stoneGameIII(int[] stones) {
    int n = stones.length;
    int[] suffixSum = new int[n];
    suffixSum[n - 1] = stones[n - 1];
    for (int i = n - 2; i >= 0; --i) suffixSum[i] = suffixSum[i + 1] + stones[i];
    int[] f = new int[n + 1];
    Arrays.fill(f, Integer.MIN_VALUE);
    f[n] = 0;
    for (int i = n - 1; i >= 0; i--) {
        int sum = 0;
        for (int j = i + 1; j <= i + 3 && j <= n; j++) {
            sum += stones[j - 1];
            f[i] = Math.max(f[i], sum - f[j]);
        }
    }
    return f[0] == 0 ? "Tie" : (f[0] > 0 ? "Alice" : "Bob");
}
```

### 方法3：DP

> 下面的两种方法，参考国际站大佬`lee215`,一句很犀利评论道出一切
>
> *Alex and Lee continue their games with piles of stones... lee215 wins.*

#### 思路

- 当当前玩家拿走`stones[i]`的时候，这个玩家可以赢得`take-f[i+1]`
- 当当前玩家拿走`stones[i,i+1]`的时候，这个玩家可以赢得`take-f[i+2]`
- 当当前玩家拿走`stones[i,i+1,i+2]`的时候，这个玩家可以赢得`take-f[i+3]`	
- take的变量定义精髓

```java
public String stoneGameIII(int[] stones) {
    int n = stones.length;
    int[] f = new int[n + 1];
    for (int i = n - 1; i >= 0; --i) {
        f[i] = Integer.MIN_VALUE;
        for (int k = 0, take = 0; k < 3 && i + k < n; ++k) {
            take += stones[i + k];
            f[i] = Math.max(f[i], take - f[i + k + 1]);
        }
    }
    return f[0] == 0 ? "Tie" : (f[0] > 0 ? "Alice" : "Bob");
}
```



### 方法4：DP(O(1))

- 只依赖 0 1 2 3 这几个状态，滚动可以做到空间复杂度`O(1)`

![image-20201225090854181](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之三[Mexican Wolf].assets\image-20201225090854181.png)

```java
    public String stoneGameIII(int[] stones) {
        int n = stones.length, f[] = new int[4];
        for (int i = n - 1; i >= 0; i--) {
            f[i % 4] = Integer.MIN_VALUE;
            for (int k = 0, take = 0; k < 3 && k + i < n; k++) {
                System.out.println(JSONObject.toJSONString(f));
                System.out.printf("%d,%d,%d,%d,%d,%d,%d\n", i, k, i % 4, i + k, i + k + 1, take, f[i % 4]);
                take += stones[i + k];
                f[i % 4] = Math.max(f[i % 4], take - f[(i + k + 1) % 4]);
            }
        }
        return f[0] == 0 ? "Tie" : (f[0] > 0 ? "Alice" : "Bob");
    }
```