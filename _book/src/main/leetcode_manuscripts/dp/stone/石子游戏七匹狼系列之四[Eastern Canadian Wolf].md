## 石子游戏七匹狼系列之四[Eastern Canadian Wolf]



![image-20201228204150857](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之四[Eastern Canadian Wolf].assets\image-20201228204150857.png)





![abstract-762994_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之四[Eastern Canadian Wolf].assets\abstract-762994_640.jpg)

### 方法1：DP

- **`f[i]`表示，当石子剩下`i`个的时候，当前玩家能否赢得这个阶段的比赛**

```java
public boolean winnerSquareGame(int n) {
    boolean[] f = new boolean[n + 1];
    for (int i = 1; i <= n; i++) {
        for (int k = 1; k * k <= i; k++) {
            if (!f[i - k * k]) {
                //当前玩家拿走k*k的石子后，留给另一玩家的石子数量是（i-k*k），另外一玩家如果输了，当前玩家便能赢
                f[i] = true;
                break;
            }
        }
    }
    return f[n];
}
```



### 方法2：递归

- 图参考国际站[链接](https://leetcode.com/problems/stone-game-iv/discuss/730490/Java-or-Heavily-Commented-or-Subproblems-Visualised)

- n=7的时候，没有一棵子树能让`Alice`赢，最后都是`Bob`拿完后不剩下石子，结束游戏

![image-20201225100734871](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之四[Eastern Canadian Wolf].assets\image-20201225100734871.png)

- n=8的时候，当`Alice`先拿走1个石子的时候，变成了一棵与上面`n=7`的时候一样的子树，但这时候顺序发生了反转，让`Bob`开始拿了，最后`Alice`可以拿完后，不剩下石子，结束游戏，`Alice`是能赢的

![image-20201228200403899](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之四[Eastern Canadian Wolf].assets\image-20201228200403899.png)

按每人最优策略来做：需要在拿完石子后，留下的子树最终能当自己赢得比赛

```java
    Map<Integer, Boolean> cache = new HashMap<>();

    public boolean winnerSquareGame(int n) {
        //石子只剩下1个的时候，当前玩家拿走这1个石子，不剩下石子，当前玩家赢得比赛
        if (n == 1) return true;
        //如果n被记录过，说明这棵n的子树被搜索过，对于当前玩家的结果是value
        if (cache.containsKey(n)) return cache.get(n);
        for (int i = 1, j = 1; j <= n; i++, j = i * i) {
            //当前玩家拿走j,另外一个玩家则变成 (n-j) 如果 另外一个玩家输掉游戏，说明当前玩家赢得游戏，返回true
            if (!winnerSquareGame(n - j)) {
                cache.put(n, true);
                return cache.get(n);
            }
        }
        cache.put(n, false);
        return cache.get(n);
    }
```



