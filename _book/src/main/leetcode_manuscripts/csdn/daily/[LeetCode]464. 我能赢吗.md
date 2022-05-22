## [LeetCode]464. 我能赢吗

[464. 我能赢吗](https://leetcode.cn/problems/can-i-win/)



## 题目

```java
464. 我能赢吗
在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。

如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？

例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。

给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家是否能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。

 

示例 1：

输入：maxChoosableInteger = 10, desiredTotal = 11
输出：false
解释：
无论第一个玩家选择哪个整数，他都会失败。
第一个玩家可以选择从 1 到 10 的整数。
如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
示例 2:

输入：maxChoosableInteger = 10, desiredTotal = 0
输出：true
示例 3:

输入：maxChoosableInteger = 10, desiredTotal = 1
输出：true
 

提示:

1 <= maxChoosableInteger <= 20
0 <= desiredTotal <= 300
```

## 解法

### 方法0：朴素版回溯

> TLE，这种回溯的思路是值得借鉴的

```java
     
        //TLE
        //41/57
        //case:
        //20
        //210
        boolean[] vis;
        int maxChoosableInteger, desiredTotal;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            this.maxChoosableInteger = maxChoosableInteger;
            this.desiredTotal = desiredTotal;
            for (int i = 1; i <= maxChoosableInteger; i++) {
                vis = new boolean[21];
                vis[i] = true;
                if (dfs(i)) return true;
            }
            return false;
        }


        /**
         * @param curSum 当前已经选的数的总和
         * @return
         */
        private boolean dfs(int curSum) {
            //已经达到目标数desiredTotal，返回true
            if (curSum >= desiredTotal) return true;
            //遍历从1 到 maxChoosableInteger的每一个数
            for (int i = 1; i <= maxChoosableInteger; i++) {
                if (vis[i]) continue;//被访问过，不需要继续
                vis[i] = true;//标记
                //如果curSum+i 即对手能赢得游戏，意味着当前玩家失败
                if (dfs(curSum + i)) {
                    return vis[i] = false;
                }
                vis[i] = false;//恢复
            }
            return true;
        }
```



### 方法1:回溯+状态压缩

- `1 <= maxChoosableInteger <= 20`数据范围，想到可能需要状态压缩

```java
       Boolean[] dp;
        int maxChoosableInteger;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            //最大的可以选的数(maxChoosableInteger)大于等于最终目标数(desiredTotal),A先抽，直接拿desiredTotal，胜出
            if (desiredTotal <= maxChoosableInteger) return true;
            //[1...maxChoosableInteger]之间的总数的和小于最终目标数(desiredTotal),A和By将数选完，也不能凑成(desiredTotal),A赢不了，返回false
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            this.maxChoosableInteger = maxChoosableInteger;
            dp = new Boolean[1 << maxChoosableInteger];
            return dfs(desiredTotal, 0);
        }

        /**
         * @param desiredTotal 当前离达成目标的原始desiredTotal还差多少
         * @param state        当前从1到maxChoosableInteger之间的每个数被选中的状态，在二进制下，1为选中，0为未选中
         * @return
         */
        private boolean dfs(int desiredTotal, int state) {
            //如果该state被标记过，返回
            if (dp[state] != null) return dp[state];
            //从 1 到 maxChoosableInteger 判断每一位是否被选中过
            for (int i = 1; i <= maxChoosableInteger; i++) {
                //当前位 需要判断下当前位是否是1（二进制下）
                int cur = 1 << (i - 1);
                if ((cur & state) == 0) {//当前位不是1是0，表示当前位可以选
                    //case1: 选中当前的数i后，可以达成最终的目标
                    //cas2:  选中当前的数i后，暂时不能达成目标，但是对手最终不能达成目标，意味着当前玩家最终可以达成目标
                    //cur | state 表示将当前数i选中 送到下一轮的状态中
                    if (desiredTotal - i <= 0 || !dfs(desiredTotal - i, cur | state)) {
                        return dp[state] = true;
                    }
                }
            }
            return dp[state] = false;
        }
```

### 方法2：回溯+HashMap

> 国际站的高赞的解法，[链接](https://leetcode.com/problems/can-i-win/discuss/95277/Java-solution-using-HashMap-with-detailed-explanation)

- `format`的技巧有点类似: [将矩阵当成二进制转化成十进制](https://cnwangzhou.gitbook.io/algorithm/zhuan-lan/er-wei-ju-zhen-de-chang-jian-zhuan-huan-ji-qiao#ji-qiao-2-jiang-ju-zhen-dang-cheng-er-jin-zhi-zhuan-hua-cheng-shi-jin-zhi)

```java
Map<Integer, Boolean> memo = new HashMap();
boolean[] vis;

public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    //最大的可以选的数(maxChoosableInteger)大于等于最终目标数(desiredTotal),A先抽，直接拿desiredTotal，胜出
    if (desiredTotal <= maxChoosableInteger) return true;
    //[1...maxChoosableInteger]之间的总数的和小于最终目标数(desiredTotal),A和By将数选完，也不能凑成(desiredTotal),A赢不了，返回false
    if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
    vis = new boolean[maxChoosableInteger + 1];
    return helper(desiredTotal);
}

public boolean helper(int desiredTotal) {
    if (desiredTotal <= 0) return false;
    int key = format(vis);
    if (!memo.containsKey(key)) {
        //遍历vis中的数，尝试没有被选中的
        for (int i = 1; i < vis.length; i++) {
            if (!vis[i]) {//i没有被选择
                vis[i] = true;//标记
                // 当前i被选中，留给对手的desiredTotal - i的空间，但是对手没法胜出
                if (!helper(desiredTotal - i)) {
                    memo.put(key, true);
                    vis[i] = false;//恢复
                    return true;
                }
                vis[i] = false;//恢复
            }
        }
        memo.put(key, false);
    }
    return memo.get(key);
}

// 遍历vis数组，每一位true的设置为1，相当于压缩
public int format(boolean[] vis) {
    int res = 0;
    for (boolean b : vis) {
        res <<= 1;
        if (b) res |= 1;
    }
    return res;
}
```

- 另，使用数组做key

> TLE

```java
        int[] state;
        Map<String, Boolean> memo = new HashMap<>();

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            if (desiredTotal <= maxChoosableInteger) return true;
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            state = new int[maxChoosableInteger + 1];
            return dfs(desiredTotal);
        }

        private boolean dfs(int desiredTotal) {
            String key = Arrays.toString(state);
            if (memo.containsKey(key)) return memo.get(key);
//            System.out.printf("key:%s ", key);
            for (int i = 1; i < state.length; i++) {
                if (state[i] == 0) {
                    state[i] = 1;
//                    System.out.printf(" %d\n", desiredTotal);
                    if (desiredTotal - i <= 0 || !dfs(desiredTotal - i)) {
                        state[i] = 0;
                        memo.put(key, true);
                        return true;
                    }
                    state[i] = 0;
                }
            }
            memo.put(key, false);
            return false;
        }
```



