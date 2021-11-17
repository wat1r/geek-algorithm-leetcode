## [掷骰子的N种方法](https://leetcode-cn.com/problems/number-of-dice-rolls-with-target-sum/)



### 方法1：朴素DP

> **分组背包的一道题，也有点类似求硬币组合数量那道题**

定义$dp[i][j]$​​为在持有$i$​个骰子的情况下，能组成$j$​的点数的组合数

$i$的上限是$d$即$d$个骰子，$j$的上限是$target$​即目标的点数，初始化时`new int[d+1][target+1]`，让下标从1开始

从边界条件考虑到一般条件，$dp[0][0]$​​表示当前有0个骰子

- 能掷出0的点数的方案，为1个方案
- 即不选任何骰子，能掷出1到$target$​的方案的数量是0，因为手里没有骰子，永远也掷不出点数

考虑$dp[i][j]$​​​的一般情况，需要考虑其前序的$dp[i-1][j-k]$​​​的情况，对于第$i$​​​个骰子，可以掷出$1...f$​​​的点数

- 当第$i$个骰子掷出了$1$,则$dp[i][j]$=$dp[i-1][j-1]$​​
  - 此时表示前$i-1$个骰子能掷出$j-1$的点数，因为第$i$个骰子掷出的点数是1，需要减掉
- 当第$i$​个骰子掷出了$2$​,则$dp[i][j]$​=$dp[i-1][j-2]$​​
- ...
- 当第$i$​​个骰子掷出了$k$​​,则$dp[i][j]$​​=$dp[i-1][j-k]$​​

- ...
- 当第$i$​​​​个骰子掷出了$f$​​​​,则$dp[i][j]$​​​​=$dp[i-1][j-f]$​​​​

如上：当第$i$个骰子晒出的点数比$j$还要大的时候，已经没有意义了，$j-f$小于0的时候没有意义

```java
        int MOD = (int) 1e9 + 7;

        /**
         * @param d d个一样的骰子
         * @param f 每个骰子上有f个面
         * @param t 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int t) {
            int[][] dp = new int[d + 1][t + 1];
            //初始化，0个骰子形成0的点数，只有一种组合数
            dp[0][0] = 1;
            //枚举i个骰子，上限是d个骰子
            for (int i = 1; i <= d; i++) {
                //枚举j的点数，上限是t的点数
                for (int j = 1; j <= t; j++) {
                    //开始枚举第i个骰子能掷出的点数k，范围是[1,f]，j的点数小于k没有意义
                    for (int k = 1; k <= f && j >= k; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
            //返回d个骰子掷出t的点数的方案数量
            return dp[d][t];
        }
```

### 方法2：空间优化DP

- 有点像01背包中的空间优化，倒序遍历

```java
  int MOD = (int) 1e9 + 7;

        /**
         * @param d d个一样的骰子
         * @param f 每个骰子上有f个面
         * @param t 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int t) {
            int[] dp = new int[t + 1];
            //初始化，0个骰子形成0的点数，只有一种组合数
            dp[0] = 1;
            //枚举i个骰子，上限是d个骰子
            for (int i = 1; i <= d; i++) {
                //枚举j的点数，上限是t的点数 倒序遍历
                for (int j = t; j >= 0; j--) {
                    dp[j] = 0;//注意，每一轮遍历到j的时候，需要置为0
                    //开始枚举第i个骰子能掷出的点数k，范围是[1,f]，j的点数小于k没有意义
                    for (int k = 1; k <= f && j >= k; k++) {
                        dp[j] = (dp[j] + dp[j - k]) % MOD;
                    }
                }
            }
            //返回d个骰子掷出t的点数的方案数量
            return dp[t];
        }
```



### 方法3：记忆化DFS

- 可以从$d$个骰子往前想，如果我们手里有$d-1$枚骰子，直到只有0枚骰子，应该有多少种组合的数量呢？

> **出口条件**

- 当手里没有骰子即$d$为0的时候，点数也是0，这时候，$0$枚骰子要形成$0$的点数，组合数为$1$
- 当手里没有骰子即$d$为0的时候，点数不为0的时候，因为没有骰子，无法掷出大于$1$的点数，此时返回$0$

- 这个过程中，需要记忆化，否则会超时，使用$map$来记录，只要记住「骰子数量+要形成的点数」这个信息做为$key$,就很容易得到要记录的信息

```java
 int MOD = (int) 1e9 + 7;
        Map<String, Integer> cache = new HashMap<>();

        /**
         * @param d      d个一样的骰子
         * @param f      每个骰子上有f个面
         * @param target 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int target) {
            if (d == 0 && target == 0) return 1;
            if (d == 0 || target == 0) return 0;
            //骰子数量+形成的点数
            String key = d + "#" + target;
            if (cache.containsKey(key)) return cache.get(key);
            int res = 0;//记录结果
            //当前能掷出的点数
            for (int i = 1; i <= f; i++) {
                //只有target的数量大于i才有意义
                if (target >= i) {
                    //用掉一个骰子，当前的骰子掷出来的点数是i，相应地，target数量也要减少
                    res = (res + numRollsToTarget(d - 1, f, target - i)) % MOD;
                } else {
                    break;
                }
            }
            //记忆化
            cache.put(key, res);
            return res;
        }
```

此处还可以有优化：

```java
 int MOD = (int) 1e9 + 7;
        Map<String, Integer> cache = new HashMap<>();

        /**
         * @param d      d个一样的骰子
         * @param f      每个骰子上有f个面
         * @param target 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int target) {
            if (d == 0 && target == 0) return 1;
            //优化点：
            // 1.当前的骰子的数量为d，大于target，也就是d个骰子，每个骰子掷出1，都凑不出target
            // 2.当前的骰子的数量为d，每个骰子都能掷出f的点数，d*f<target 都凑不出target
            if (d > target || d * f < target) return 0;
            //骰子数量+形成的点数
            String key = d + "#" + target;
            if (cache.containsKey(key)) return cache.get(key);
            int res = 0;//记录结果
            //当前能掷出的点数
            for (int i = 1; i <= f; i++) {
                //只有target的数量大于i才有意义
                if (target >= i) {
                    //用掉一个骰子，当前的骰子掷出来的点数是i，相应地，target数量也要减少
                    res = (res + numRollsToTarget(d - 1, f, target - i)) % MOD;
                } else {
                    break;
                }
            }
            //记忆化
            cache.put(key, res);
            return res;
        }
```

