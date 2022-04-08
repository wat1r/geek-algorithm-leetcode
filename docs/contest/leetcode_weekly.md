> 



## [Week256](https://leetcode-cn.com/contest/weekly-contest-256/)

### T1.[1984. 学生分数的最小差值](https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/)

```java
//1984. 学生分数的最小差值
public int minimumDifference(int[] nums, int k) {
    int i = 0, j = k - 1;
    Arrays.sort(nums);//排序后[i...i+k]段的首位两个数便是最小值和最大值
    int res = 100_005;
    while (j < nums.length) {
        res = Math.min(res, nums[j++] - nums[i++]);
    }
    return res;
}
```

### T2.[1985. 找出数组中的第 K 大整数](https://leetcode-cn.com/problems/find-the-kth-largest-integer-in-the-array/)

```java
//1985. 找出数组中的第 K 大整数
public String kthLargestNumber(String[] nums, int k) {
    //按字典序的数字从大到小排列，返回k-1个数，即k大的数
    Arrays.sort(nums, ((o1, o2) -> {
        if (o1.length() > o2.length()) return -1;
        else if (o1.length() < o2.length()) return 1;
        return o2.compareTo(o1);
    }));
    return nums[k - 1];
}
```

### T3.[1986. 完成任务的最少工作时间段](https://leetcode-cn.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/)



#### 方法1:朴素版递归

```java
static class _3rd_3 {

    //朴素版dfs TLE
    //TLE case
    //    [1,1,1,1,1,1,1,1,1,1,1,1,1,1]
    //            14

    //sessions.get(i)表示在完成ith 的session的最工作时长
    //当遍历到tasks[i]的时候，有两种选择：
    //1.加入到sessions的其中一个session中，参与贡献
    //2.作为新的session加入到sessions中
    //上面的两种选择的最小值是当前轮的结果
    List<Integer> sessions = new ArrayList<>();
    int n;

    public int minSessions(int[] tasks, int sessionTime) {
        this.n = tasks.length;
        return helper(tasks, sessionTime, 0);
    }


    private int helper(int[] tasks, int sessionTime, int pos) {
        if (pos >= n) return 0;
        //将当前的task加入到sessions
        sessions.add(tasks[pos]);
        int ans = 1 + helper(tasks, sessionTime, pos + 1);
        sessions.remove(sessions.size() - 1);
        //尝试将其加入到之前的可以加入的session
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i) + tasks[pos] <= sessionTime) {
                sessions.set(i, sessions.get(i) + tasks[pos]);
                ans = Math.min(ans, helper(tasks, sessionTime, pos + 1));
                sessions.set(i, sessions.get(i) - tasks[pos]);
            }
        }
        return ans;
    }

}
```



#### 方法2:记忆化递归

```java
static class _3rd_4 {

    //记忆化dfs

    //sessions.get(i)表示在完成ith 的session的最工作时长
    //当遍历到tasks[i]的时候，有两种选择：
    //1.加入到sessions的其中一个session中，参与贡献
    //2.作为新的session加入到sessions中
    //上面的两种选择的最小值是当前轮的结果
    List<Integer> sessions = new ArrayList<>();
    int n;
    Map<String, Integer> cache = new HashMap<>();

    public int minSessions(int[] tasks, int sessionTime) {
        this.n = tasks.length;
        return helper(tasks, sessionTime, 0);
    }


    private int helper(int[] tasks, int sessionTime, int pos) {
        if (pos >= n) return 0;
        String key = encode(pos, sessions);
        if (cache.containsKey(key)) return cache.get(key);
        //将当前的task加入到sessions
        sessions.add(tasks[pos]);
        int ans = 1 + helper(tasks, sessionTime, pos + 1);
        sessions.remove(sessions.size() - 1);
        //尝试将其加入到之前的可以加入的session
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i) + tasks[pos] <= sessionTime) {
                sessions.set(i, sessions.get(i) + tasks[pos]);
                ans = Math.min(ans, helper(tasks, sessionTime, pos + 1));
                sessions.set(i, sessions.get(i) - tasks[pos]);
            }
        }
        cache.put(key, ans);
        return ans;
    }

	//只需要对当前处理到的session和pos做一个key，存入到cache中
    private String encode(int pos, List<Integer> sessions) {
        List<Integer> tmp = new ArrayList<>(sessions);
        Collections.sort(tmp);
        StringBuilder key = new StringBuilder(pos + "$");
        for (int i = 0; i < tmp.size(); i++) {
            key.append(tmp.get(i)).append("$");
        }
        return key.toString();
    }

}
```

- 另外一种写法

```java
Integer[][] cache;
int n;

public int minSessions(int[] tasks, int sessionTime) {
    this.n = tasks.length;
    cache = new Integer[1 << n][16];
    return dfs(tasks, sessionTime, 0, 0);
}

private int dfs(int[] tasks, int sessionTime, int state, int sum) {
    if (state == (1 << n) - 1) return 1;
    if (cache[state][sum] != null) return cache[state][sum];
    int res = n;
    for (int i = 0; i < n; i++) {
        if ((state & (1 << i)) == 0) {
            if (sum + tasks[i] <= sessionTime) {
                res = Math.min(res, dfs(tasks, sessionTime, state | (1 << i), sum + tasks[i]));
            } else {
                res = Math.min(res, 1 + dfs(tasks, sessionTime, state | (1 << i), tasks[i]));
            }
        }
    }
    return cache[state][sum] = res;
}
```





#### 方法3:状压DP

- 枚举子集

```java
        public int minSessions(int[] tasks, int sessionTime) {
            int n = tasks.length;
            boolean[] valid = new boolean[1 << n];
            for (int mask = 1; mask < (1 << n); mask++) {
                int needTime = 0;
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        needTime += tasks[i];
                    }
                }
                if (needTime <= sessionTime) valid[mask] = true;
            }
            int[] f = new int[1 << n];
            Arrays.fill(f, Integer.MAX_VALUE / 2);
            f[0] = 0;
            for (int mask = 1; mask < (1 << n); mask++) {
                for (int subset = mask; subset > 0; subset = (subset - 1) & mask) {
                    if (valid[subset]) {
                        f[mask] = Math.min(f[mask], f[subset ^ mask] + 1);
                    }
                }
            }
            return f[(1 << n) - 1];
        }
```



- 状压dp

```java
        public int minSessions(int[] tasks, int sessionTime) {
            int n = tasks.length, m = 1 << n;
            final int INF = 20;
            int[] dp = new int[m];
            Arrays.fill(dp, INF);

            // 预处理每个状态，合法状态预设为 1
            for (int i = 1; i < m; i++) {
                int state = i, idx = 0;
                int spend = 0;
                while (state > 0) {
                    int bit = state & 1;
                    if (bit == 1) {
                        spend += tasks[idx];
                    }
                    state >>= 1;
                    idx++;
                }
                if (spend <= sessionTime) {
                    dp[i] = 1;
                }
            }

            // 对每个状态枚举子集，跳过已经有最优解的状态
            for (int i = 1; i < m; i++) {
                if (dp[i] == 1) {
                    continue;
                }
                //method 1
                for (int j = 1; j <= i; j++) {
                    if ((j | i) == i) {
                        dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
                    }
                }
                //method 2
//                for (int j = i; j > 0; j = (j - 1) & i) {
//                    dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
//                }


//                int split = i >> 1;
//                // 由于转移是由子集与补集得来，因此可以将子集分割为两块，避免重复枚举
//                for (int j = (i - 1) & i; j > split; j = (j - 1) & i) {
//                    // i 状态的最优解可能由当前子集 j 与子集 j 的补集得来
//                    dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
//                }
            }

            return dp[m - 1];
        }
```





