## 极小化极大之预测赢家[Polar Bear]





![teddy-2649131_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\min_max\极小化极大之预测赢家[Polar Bear].assets\teddy-2649131_640.jpg)

![image-20200901080807049](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\min_max\极小化极大之预测赢家[Polar Bear].assets\image-20200901080807049.png)

### 方法0:记忆化+递归

- $dfs$表示的是先手玩家在$nums[left...right]$范围内能获取的最大分数

```java
    int[][] memo;

    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        memo = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], Integer.MIN_VALUE);
        return dfs(nums, 0, n - 1) >= 0;
    }

    private int dfs(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        if (memo[left][right] != Integer.MIN_VALUE) return memo[left][right];
        int pickLeft = nums[left] + (-dfs(nums, left + 1, right));
        int pickRight = nums[right] + (-dfs(nums, left, right - 1));
        memo[left][right] = Math.max(pickLeft, pickRight);
        return memo[left][right];
    }

```



### 方法1:DP

#### 定义状态

**$dp[i][j]$表示先手玩家与后手玩家在$nums[i...j]$之间互相拿，先手玩家比后手玩家多的最大分数，注意：这是个差值(delta)，而且是个最大差值$**

#### 转移方程

![image-20200901084811790](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\min_max\极小化极大之预测赢家[Polar Bear].assets\image-20200901084811790.png)

- 对于先手玩家，在$nums[i...j]$之间，有两种拿法：
  - 拿开头的,也就是$nums[i]$,现在要求$dp[i][j]$,先手玩家手里有了$nums[i]$了，$dp[i+1][j]$表示什么呢？因为在**$nums[i+1...j]$,只能由后手玩家来选，其表示的是后手玩家在这个区间内，比先手玩家多的最大分数**，反过来$-dp[i+1][j]$表示在这个区间内，先手玩家比后手玩家多的最大分数，那么，$dp[i][j]$ = $nums[i]+(-dp[i+1][j])$
  - 拿结尾的,也就是$nums[j]$,现在要求$dp[i][j]$,先手玩家手里有了$nums[j]$了，$dp[i][j-1]$表示什么呢？因为在$nums[i...j-1]$，**只能由后手玩家来选，其表示的是后手玩家在这个区间内，比先手玩家多的最大分数**,反过来$-dp[i][j-1]$表示在这个区间内，先手玩家比后手玩家多的最大分数，那么,$dp[i][j-1]$= $nums[j]$+$(-dp[i][j-1])$
  - 而每一步，都是最优解，那全局最优解，即先手玩家在每一步都想要最大化，即$dp[i][j]$ = $max[nums[i]+(-dp[i+1][j]),nums[j]+(-dp[i][j-1])]$
- 返回$dp[0][n-1]$表示在$nums[0...n-1]$范围内，先手玩家与后手玩家互相拿，先手玩家比后手玩家多的最大分数，只需要判断这个值是否大于等于0

#### 边界

- $dp[i][i]$表示只存在一个数$nums[i..i]$,显然，先手玩家拿走$nums[i]$,其利益最大化，即$dp[i][i]$ = $nums[i]$

#### 思路

- 将$i$从$n-1$往前扩充到$0$，而$j$从$i$位置往后跑

```java
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] + (-dp[i + 1][j]), nums[j] + (-dp[i][j - 1]));
            }
        }
        return dp[0][n - 1] >= 0;
    }
```



### 方法2:DP

![image-20200901133529849](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\min_max\极小化极大之预测赢家[Polar Bear].assets\image-20200901133529849.png)

- 如上图所示：$dp[0][3]$ 依赖于$dp[1][3]$和 $dp[0][2]$, 这两个值已经在$step=3$的时候被计算出来了

- 思路同方法1，只是遍历的顺序不同，

- 举例：$nums$=$[1,5,233,7]$

![img](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\min_max\极小化极大之预测赢家[Polar Bear].assets\clipboard.png)

```java
public boolean PredictTheWinner(int[] nums) {
    if (nums == null || nums.length == 0) return false;
    int n = nums.length;
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) dp[i][i] = nums[i];
    for (int step = 2; step <= n; step++) {
        for (int i = 0; i < n - step + 1; i++) {
            int j = i + step - 1;
            dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
        }
    }
    return dp[0][n - 1] >= 0;
}
```

如果想从$step=1$开始：

```java
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
//        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int step = 1; step <= n; step++) {
            for (int i = 0; i < n - step + 1; i++) {
                int j = i + step - 1;
                if (i == j) {
                    dp[i][i] = nums[i];
                    continue;
                }
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
```







### 方法3:DP

> 思路来源于 wzc1995大神

#### 定义状态

- **$dp[i][j]$表示先手玩家在$nums[i...j]$范围内，能获得的最大分数**,区别于方法1的 差值，这个只是单纯的分数

#### 转移方程

- 如果本轮是先手玩家$pick$，其当然希望最大化地获取到分数，也就是$dp[i][j]$=$max[nums[i]+dp[i+1][j],nums[j]+dp[i][j-1]]$
- 如果本轮是后手玩家$pick$，其当然希望自己的分数最大化而留给先手玩家的分数越少越好，即$dp[i][j]$ = $min[dp[i+1][j],dp[i][j-1]]$,因为$dp[i][j]$表示的是先手玩家拿到的最大分数，后手玩家在自己的轮次已经拿走了$nums[i]$或者$nums[j]$

#### 边界

- $dp[i][i]$表示只存在一个数$nums[i..i]$

#### 思路

- 通过$turn$来轮换先手玩家和后手玩家，初始化时$turn$=$n$&$1$
- 记录下总的分数$sum$,如果先手玩家最后得到的分数$dp[0][n-1]$*2大于两倍的$sum$，说明先手玩家赢了

$nums=[1,5,233,7]$

![img](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\min_max\极小化极大之预测赢家[Polar Bear].assets\clipboard-1598921546839.png)

$nums=[1,233,5,7,230]$

![img](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\min_max\极小化极大之预测赢家[Polar Bear].assets\clipboard-1598921546840.png)

```java
public boolean PredictTheWinner3rd(int[] nums) {
    if (nums == null || nums.length == 0) return false;
    int n = nums.length;
    int[][] dp = new int[n][n];
    int turn = n & 1;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        if (turn == 1) dp[i][i] = nums[i];
        sum += nums[i];
    }
    for (int step = 2; step <= n; step++) {
        turn ^= 1;
        for (int i = 0; i < n - step + 1; i++) {
            int j = i + step - 1;
            if (turn == 1) dp[i][j] = Math.max(nums[i] + dp[i + 1][j], nums[j] + dp[i][j - 1]);
            else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
        }
    }
    return 2 * dp[0][n - 1] >= sum;
```

