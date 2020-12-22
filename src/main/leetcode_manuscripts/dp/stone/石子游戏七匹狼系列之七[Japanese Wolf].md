## 石子游戏七匹狼系列之七[Japanese Wolf]

![wolf-2289952_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之七[Japanese Wolf].assets\wolf-2289952_640.jpg)



![image-20201222202824950](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之七[Japanese Wolf].assets\image-20201222202824950.png)



### 方法1:DP(Bottom-up)

#### 定义状态

**`f[i][j]`表示在区间`[i...j]`，当前玩家（不一定是先手玩家`Alice`），与另一玩家所能得到得分的最大分差**

如`stones = [5,3,1,4,2]` ,当`Alice`取走2,得5+3+1+4 = 13分，`Bob`取走4，得5+3+1= 9分，其分差为13-9=4

#### 转移方程

- 当`i==j`时，当前玩家拿走后，不剩任何石子了，也就是`f[i][i]=0`,移除一个石子后，不剩石子
- 当`j-i=1`时，需要考虑，`max{stones[i],stones[j]}`,想要最大的得分，需要删除小的，留下大的，因为只有两个石子，当前玩家选完后，得到这一轮的最大得分，下一轮玩家再选的时候，选完就没石子了，得分为0
- 一般情况，当`j-i>1`
  - 如果当前玩家（假设为A）从左侧拿走了`i`这个石子，剩下`[i+1...j]`，这个区间的所有分数的和为`sum[i+1:j+1]`，当到这个区间的时候，轮到另一玩家（假设为B）选了，他最大能获得的最大分差为`f[i+1][j]`,  而`f[i+1][j]= `  B所能获取的分数-A所能获取的分数，A所能得到的最大分差为`f[i][j]` = A所能获取的分数+`sum[i+1:j+1]`-B获取的分数， 可以写成`f[i][j]`  = `sum[i+1:j+1]`-（B所能获取的分数-A所能获取的分数）  等价于：`f[i][j]` = `sum[i+1:j+1]` - `f[i+1][j]`
  - 如果当前玩家（假设为A）从右侧拿走了`j`这个石子，`f[i][j]` = `sum[i:j]` - `f[i][j-1]`
  - 取上面的最大值，每一轮都尽力扩大最大分差

> 区间DP模板

```java
for (int len = 2; len <= n; len++) {//区间长度
    for (int i = 1; i <= n; i++) { //枚举起点
        int j = i + len - 1; //区间终点
        if (j >= n) break; //防止越界
        for (int k = i+1; k < j; k++) { //枚举分割点，开始转移
            dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + w[i][j]);
        }
    }
}
```

> 正文

```java
public int stoneGameVII(int[] stones) {
    int n = stones.length;
    int[] sum = new int[n + 1];
    for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + stones[i];
    int[][] f = new int[n][n];
    for (int len = 2; len <= n; len++) {
        for (int i = 0; i <= n - len; i++) {
            int j = i + len - 1;
            f[i][j] = Math.max(
                    sum[j + 1] - sum[i + 1] - f[i + 1][j],
                    sum[j] - sum[i] - f[i][j - 1]
            );
        }
    }
    return f[0][n - 1];
}
```

![image-20201222212904277](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之七[Japanese Wolf].assets\image-20201222212904277.png)

打印的DP表,上图是从小区间慢慢扩充到从0到n-1的大区间

```java
  0   5   3   7   6 
  0   0   3   1   7 
  0   0   0   4   2 
  0   0   0   0   4 
  0   0   0   0   0 
```

#### 另外一种写法

打印的i，j

```java
i:3,j:4
i:2,j:3
i:2,j:4
i:1,j:2
i:1,j:3
i:1,j:4
i:0,j:1
i:0,j:2
i:0,j:3
i:0,j:4
```

```java
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[][] f = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                System.out.printf("i:%d,j:%d\n", i, j);
                if (i + 1 == j) {
                    f[i][j] = Math.max(stones[i], stones[j]);
                } else {
                    int left = Math.min(stones[i + 1] + f[i + 2][j], stones[j] + f[i + 1][j - 1]);
                    int right = Math.min(stones[i] + f[i + 1][j - 1], stones[j - 1] + f[i][j - 2]);
                    f[i][j] = Math.max(left, right);
                }
            }
        }
//        PrintUtils.printMatrix(f);
        return f[0][n - 1];
    }
```

























### 方法2:递归



