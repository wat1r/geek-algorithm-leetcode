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



### T4.[1987. 不同的好子序列数目](https://leetcode-cn.com/problems/number-of-unique-good-subsequences/)

```java
          int MOD = (int) 1e9 + 7;
          public int numberOfUniqueGoodSubsequences(String binary) {
            int[][] dp = new int[2][2];
            for (char c : binary.toCharArray()) {
                if (c == '0') {
                    dp[0][0] = 1;
                    dp[1][0] = (dp[1][0] + dp[1][1]) % MOD;
                } else {
                    dp[1][1] = (dp[1][0] + dp[1][1] + 1) % MOD;
                }
            }
            return (dp[0][0] + dp[0][1] + dp[1][0] + dp[1][1]) % MOD;
        }
```





```java
class Solution {
private:
    static constexpr int mod = 1000000007;

public:
    int numberOfUniqueGoodSubsequences(string binary) {
        int even = 0, odd = 0;
        for (char ch: binary) {
            if (ch == '0') {
                even = (even + odd) % mod;
            }
            else {
                odd = (even + odd + 1) % mod;
            }
        }

        int ans = (even + odd + (binary.find('0') != string::npos)) % mod;
        return ans;
    }
};


```







### 扩展:[940. 不同的子序列 II](https://leetcode-cn.com/problems/distinct-subsequences-ii/)

```java
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "zchmliaqdgvwncfatcfivphddpzjkgyyerrr";
//            1st-> f[35]=   334116041,   f[last[17]-1]=   333529012
//            2nd-> f[35]=      587029,   f[last[17]-1]=   333529012
//            1st-> f[36]=     1174058,   f[last[17]-1]=   667058024
//            2nd-> f[36]=  -665883966,   f[last[17]-1]=   667058024
            handler.distinctSubseqII(s);

        }


        //f[i]表示s[0...i]之间的字符组成的不同子序列的数量
        public int distinctSubseqII(String s) {
            int MOD = (int) 1e9 + 7;
            int N = s.length();
            int[] f = new int[N + 1];
            f[0] = 1;
            int[] last = new int[26];
            Arrays.fill(last, -1);
            for (int i = 1; i <= N; i++) {
                int x = s.charAt(i - 1) - 'a';
                f[i] = 2 * f[i - 1] % MOD;//前一个需序列的每一个末尾追加一个当前字符
                if (last[x] >= 0) {
                    System.out.printf("1st-> f[%d]=%12d,   f[last[%d]-1]=%12d\n", i, f[i], x, f[last[x] - 1]);
                    f[i] -= f[last[x] - 1];//有重复的字符需要移除到上一个出现该字符时的数量
                    System.out.printf("2nd-> f[%d]=%12d,   f[last[%d]-1]=%12d\n", i, f[i], x, f[last[x] - 1]);
                }
                f[i] %= MOD;
                last[x] = i;
            }
            f[N]--;
            if (f[N] < 0) f[N] += MOD;
            return f[N];
        }
```





## [Week257](https://leetcode-cn.com/contest/weekly-contest-257/)

### T1.[1995. 统计特殊四元组](https://leetcode-cn.com/problems/count-special-quadruplets/)

```java
    public int countQuadruplets(int[] nums) {
        if(nums.length<4) return 0;
        int res = 0;
        for(int a= 0;a<nums.length-3;a++){
            for(int b= a+1;b<nums.length-2;b++){
                for(int c = b+1 ;c< nums.length-1;c++){
                    for(int d=c;d<nums.length;d++){
                        int sum = nums[a]+nums[b]+nums[c];
                        if(sum == nums[d]){
                                res++;
                        }
                    }
                }
            }
        }
        return res;
    }
```





```java
public int countQuadruplets(int[] nums) {
    int n = nums.length, ans = 0;
    int[] cnt = new int[10010];
    for (int c = n - 2; c >= 2; c--) {
        cnt[nums[c + 1]]++;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < c; b++) {
                ans += cnt[nums[a] + nums[b] + nums[c]];
            }
        }
    }
    return ans;
}
```







```java
public int countQuadruplets(int[] nums) {
    int n = nums.length;
    int res = 0;
    int[] cnt = new int[5050];
    for (int b = n - 3; b >= 1; b--) {
        for (int d = b + 2; d < n; d++) {
            int c = b + 1;
            cnt[nums[d] - nums[c] + 100]++;
        }
        for (int a = 0; a < b; a++) {
            res += cnt[nums[a] + nums[b] + 100];
        }
    }
    return res;
}
```





```java
public int countQuadruplets(int[] nums) {
    int n = nums.length;
    int res = 0;
    int[] cnt = new int[5050];
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            res += cnt[nums[j] - nums[i] + 100];
        }
        for (int k = 0; k < i; k++) {
            cnt[nums[k] + nums[i] + 100]++;
        }
    }
    return res;
}
```







- 多维背包

```java
       public int countQuadruplets(int[] nums) {
            int n = nums.length;
//            定义 f[i][j][k] 为考虑前 i 个物品（下标从 1 开始），凑成数值恰好 j，使用个数恰好为 k 的方案数
            int[][][] f = new int[n + 1][110][4];
            f[0][0][0] = 1;
            for (int i = 1; i <= n; i++) {
                int t = nums[i - 1];
                for (int j = 0; j < 110; j++) {
                    for (int k = 0; k < 4; k++) {
                        f[i][j][k] += f[i - 1][j][k];
                        if (j - t >= 0 && k - 1 >= 0) {
                            f[i][j][k] += f[i - 1][j - t][k - 1];
                        }
                    }
                }
            }
            int res = 0;
            for (int i = 3; i < n; i++) {
                res += f[i][nums[i]][3];
            }
            return res;
        }
```









### T2.[1996. 游戏中弱角色的数量](https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/)

```java
public int numberOfWeakCharacters(int[][] ps) {
    int res = 0;
    Arrays.sort(ps, ((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]));
    Deque<int[]> stk = new ArrayDeque<>();
    for (int i = 0; i < ps.length; i++) {
        while (i < ps.length && !stk.isEmpty() && stk.peek()[0] >= ps[i][0] && stk.peek()[1] >= ps[i][1]) {
            for (int[] c : stk) {
                if (c[0] > ps[i][0] && c[1] > ps[i][1]) {
                    res++;
                    //break 参考这个case
                    //{{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5}, {1, 5}};
                    break;
                }
            }
            i++;
        }
        if (i < ps.length) stk.push(ps[i]);
    }
    return res;
}
```





### T3.[1997. 访问完所有房间的第一天](https://leetcode-cn.com/problems/first-day-where-you-have-been-in-all-the-rooms/)

```java
//https://leetcode-cn.com/problems/first-day-where-you-have-been-in-all-the-rooms/solution/c-si-wei-ti-xu-lie-dong-tai-gui-hua-by-e-q902/
public int firstDayBeenInAllRooms(int[] nextVisit) {
    int MOD = (int) 1e9 + 7;
    int n = nextVisit.length;
    //f[i]表示第一次到达i房间的天数
    long[] f = new long[n];
    f[0] = 0;
    //f[i] = f[i-1]+1  偶数时从前一个房间来
    //奇数时
    for (int i = 1; i < n; i++) {
        f[i] = (MOD + f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1) % MOD;
    }
    return (int) f[n - 1] % MOD;
}
```





### T4.

