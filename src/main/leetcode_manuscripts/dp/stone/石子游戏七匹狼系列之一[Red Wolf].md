## 石子游戏七匹狼系列之一[Red Wolf]

 ![dog-3059346_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一.assets\dog-3059346_640.jpg)

![image-20201215204430318](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一.assets\image-20201215204430318.png)



### 方法1：三维DP

![image-20201215204408201](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一.assets\image-20201215204408201.png)

> 思路来自油管大神

下面解释上图，`f[i][j]`表示下标索引在`[i...j]`范围内，即`piles[i]`到`piles[j]`这个范围内，先手为了利益最大化，拿到的最多的石子的数量，以及后手为了利益最大化，拿到的最多的石子的数量`(x,y)`，其中`x`表示先手拿到的，`y`表示后手拿到的

- 当在区间`[0...0]`,`[1...1]`,`[2...2]`,`[3...3]` 这个范围内，因为只有一个石子，先手拿走后，不剩石子了，所以`f[i][i]` 分别为`(5,0),(3,0),(4,0)`,`(5,0)`

- 情况二
  - 当在区间`[0...1]`范围内：先手如果拿走左边的`[0]=5`留给后手的是`[1]=3`,当然，也可以从右边开始拿，但是没从左边拿好，即`f[0][1]=(5,3)`
  - 当在区间`[1...2]`范围内：先手如果拿走左边的`[1]=3`留给后手的是`[2]=4`,当然，也可以从右边开始拿，比从左边拿好，即`f[1][2]=(4,3)`
  - 当在区间`[2...3]`范围内：先手如果拿走左边的`[2]=4`留给后手的是`[3]=5`,当然，也可以从右边开始拿，比从左边拿好，即`f[2][3]=(5,4)`

- 情况三

  - 当在区间`[0...2]`范围内：先手如果拿走左边的`[0]=5`,留给后手的是下标索引`[1...2]`这个范围，这时候按图上去找的话，得到`f[1][2]=(4,3)`

  这时候，当到了这一轮的时候，此前的先手变成了这一轮的后手，只能去拿`(4,3)`中的`3`,也就是说先手能拿到5+3=8,后手只能拿到4，即`f[0][2]=(8,4)`

  - 同样的道理可以得到`f[1][3]=(8,4)`

- 情况四

  - 当在区间`[0...3]`范围内：先手如果拿走左边的`[0]=5`,留给后手的是下标索引`[1...3]`这个范围，这时候按图上去找的话，得到`f[1][3]=(8,4)`

  这时候，当到了这一轮的时候，此前的先手变成了这一轮的后手，只能去拿`(8,3)`中的`4`,也就是说先手能拿到5+4=9,后手只能拿到8，即`f[0][3]=(9,8)`，这时候相当于从头到尾的区间都拿完了，先手拿了9，后手拿了8，先手拿的比后手多

```java
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        //f[i][j][0] 表示 从[i...j]这个区间，先手能拿到的最多的石子，f[i][j][1]是后者能拿到的最多的石子
        int[][][] f = new int[n][n][2];
        //初始化对角线
        for (int i = 0; i < n; i++) f[i][i][0] = piles[i];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                //开始斜向遍历
                int x = j, y = i + j;
                int left = piles[x] + f[x + 1][y][1];
                int right = piles[y] + f[x][y - 1][1];
                if (left >= right) {
                    f[x][y][0] = left;
                    f[x][y][1] = f[x + 1][y][0];
                } else {
                    f[x][y][0] = right;
                    f[x][y][1] = f[x][y - 1][0];
                }
            }
        }
        return f[n - 1][n - 1][0] > f[n - 1][n - 1][1];
    }
```

> 番外：对角线遍历

![image-20201216212622509](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一.assets\image-20201216212622509.png)

```java
private void print() {
    int[][] a = {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}};
    int n = a.length;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - i; j++) {
            System.out.print("(" + j + "," + (i + j) + ")");
        }
        System.out.println();
    }
}
```

- 这个思路很容易帮我们理解，但是效率不高，毕竟开了三维

### 方法2：二维DP

- 方法1记录了最终，先手和后手的石子数量，这个信息很详细，本题只需要判断拿完这对石子，先手是否比后手的石子多（即差值），如果先手多于后手，则先手赢，反之则后手赢

#### 定义状态

`f[i][j]`表示石子在`[i...j]`这个区间范围内，当前玩家与另外一个玩家的石子数量的差值，（当前玩家并不一定是先手玩家`Alex`）,如果差值大于0，表示当前玩家处在当前这个节点上，比另外一个玩家拿的石子多

#### 转移方程

当在`[i...j]`这个范围内上，当前的玩家可以从左边拿走`[i]`,可以从右边拿走`[j]`

- 如果拿了`[i]`,当前玩家获取到的石子数量是`piles[i]`,而`f[i+1][j]`表示在`[i+1....j]`这个范围内，另外一个玩家比当前玩家多的石子数量，即`f[i+1][j]`（注意此时发生了反转），而相反数`-f[i+1][j]`表示是的当前玩家比另外一个玩家多的数量，也就是说`f[i][j]=piles[i]+(-f[i+1][j])`,
- 如果拿了`[j]`,类似可得到`f[i][j]=piles[j]+(-f[i][j-1])`
- 取`max`,`f[i][j]=max(piles[i]+(-f[i+1][j]),piles[j]+(-f[i][j-1]))`

#### 初始化边界

- 这时的初始化边界是当`[i...j]` 只有一个石子的时，即`i==j`的时候，当前的玩家拿走即可，留给另外一个玩家的是0

#### 思路

- 目标是要拿到`[0...n-1]`这个大区间上的结果集，即`f[0][n-1]`的结果，我们从小的结果集出发，可以选择从最右边开始做扩散

```java
   public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) f[i][i] = piles[i];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] > 0;
    }
```

### 方法3：一维DP

- `f[i][j]`只与`f[i+1][j]`和`f[i][j-1]`有关系，在计算时只用到`i`和`i+1`行的值，可以用一维来解决

```java
public boolean stoneGame2nd(int[] piles) {
    int n = piles.length;
    int[] f = new int[n];
    for (int i = 0; i < n; i++) f[i] = piles[i];
    for (int i = n - 2; i >= 0; i--) {
        for (int j = i + 1; j < n; j++) {
            f[j] = Math.max(piles[i] - f[j], piles[j] - f[j - 1]);
        }
    }
    return f[n - 1] > 0;
}
```

### 方法4：数学分析

![image-20201217100229805](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一.assets\image-20201217100229805.png)

从0到`n-1`这个区间范围内，分布着等量的奇数和偶数的下标索引的石子，我们提前有一个结论，题意说的是，石子的总量不等，也就是说，`sum(piles[even])!=sum(piles[odd])`,只要先测试一轮，`Alex`选偶数下标的索引或者奇数下标的索引的石子，即可得到答案

```java
public boolean stoneGame(int[] piles) {
 	return true;   
}
```



