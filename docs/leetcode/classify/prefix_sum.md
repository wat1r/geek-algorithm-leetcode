

> 



![image-20220422073930902](/Users/frankcooper/Library/Application Support/typora-user-images/image-20220422073930902.png)







## [303. 区域和检索 - 数组不可变](https://leetcode-cn.com/problems/range-sum-query-immutable/)



```java
class NumArray {

    int[] pre;

    public NumArray(int[] nums) {
        pre = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) pre[i + 1] = pre[i] + nums[i];
    }

    public int sumRange(int left, int right) {
        return pre[right + 1] - pre[left];
    }
}
```



## [304. 二维区域和检索 - 矩阵不可变](https://leetcode-cn.com/problems/range-sum-query-2d-immutable/)

### 方法1：二维前缀和

```java
class NumMatrix {

    int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        preSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1]
                - preSum[row1][col2 + 1] + preSum[row1][col1];
    }
}
```



## 325.和等于 k 的最长子数组长度

```java
public int maxSubArrayLen(int[] nums, int k) {
    int n = nums.length;
    int[] pre = new int[n + 1];
    //k: 存储当前的前缀和的值pre[i], v:存储当前前缀和下最小的下标索引
    Map<Integer, Integer> map = new HashMap<>();
    map.put(pre[0], 0);
    for (int i = 1; i <= n; i++) {
        pre[i] = pre[i - 1] + nums[i - 1];
        if (!map.containsKey(pre[i])) {
            map.put(pre[i], i);
        }
    }
    int maxLen = 0;
    //遍历到的为 pre[i] ,如果在 map 中存在 pre[i]-k ，说明存在一个长度为 k 的子数组，
    //现在我们得找到这个子数组的起始索引，即 map.get(pre[i]-k)，
    // 于是我们统计从 map.get(sum[i]-k) 到 i-1 长度,并更新 maxLen
    //倒序遍历 [0...i]的长度如果比maxLen小，没必要继续遍历
    for (int i = n; i > maxLen; i--) {
        int t = pre[i] - k;
        if (map.containsKey(t)) {
            maxLen = Math.max(maxLen, i - map.get(t));
        }
    }
    return maxLen;
}
```







## [525. 连续数组](https://leetcode-cn.com/problems/contiguous-array/)



```java
        public int findMaxLength(int[] nums) {
            //0 相当于-1 -1 +1 cnt值维持在0附近波动
            //k 表示计数0的次数，v表示当前计数下，0的最早的下标索引
            //哈希表表示每一个前缀和第一次出现时的下标索引
            Map<Integer, Integer> map = new HashMap<>();
            int res = 0, cnt = 0;
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) cnt++;
                else if (nums[i] == 0) cnt--;
                if (map.containsKey(cnt)) {
                    int prev = map.get(cnt);
                    res = Math.max(res, i - prev);
                } else {
                    map.put(cnt, i);
                }
            }
            return res;
        }
```



```java
public int findMaxLength(int[] nums) {
    int res = 0, n = nums.length;
    int[] preSum = new int[n + 1];
    for (int i = 0; i < n; i++) {
        preSum[i + 1] = preSum[i] + (nums[i] == 0 ? -1 : 1);
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i <= n; i++) {
        if (map.containsKey(preSum[i])) {
            res = Math.max(res, i - map.get(preSum[i]));
        } else {
            map.put(preSum[i], i);
        }
    }
    return res;
}
```









## [560. 和为 K 的子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k/)

#### 方法1:前缀和+Hash

```java
public int subarraySum(int[] nums, int k) {
    int res = 0;
    //k：存储的是前缀和 [0...i]的元素的前缀和，v:该前缀和出现的次数
    Map<Integer, Integer> dict = new HashMap<>();
    dict.put(0, 1);
    int preSum = 0;
    for (int i = 0; i < nums.length; i++) {
        preSum += nums[i];
        //preSum[i]-preSum[j] =k 相当于在[0...i]中找有多少个j满足条件
        int t = preSum - k;
        if (dict.containsKey(t)) res += dict.get(t);
        dict.put(preSum, dict.getOrDefault(preSum, 0) + 1);
    }
    return res;
}
```



## [523. 连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum/)



```java
public boolean checkSubarraySum(int[] nums, int k) {
    int n = nums.length;
    int[] preSum = new int[n + 1];
    //k存储的是preSum[i]%k的值，v是下标索引
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 1; i <= n; i++) {
        preSum[i] = preSum[i - 1] + nums[i - 1];
        map.put(preSum[i] % k, i - 1);
    }
    for (int i = 0; i <= n; i++) {
        int t = preSum[i] % k;
        //存的是下标 [0,1]距离为2 可能下标的结果小
        if (map.containsKey(t) && Math.abs(map.get(t) - i + 1) >= 2) return true;
    }
    return false;
}
```



## [724. 寻找数组的中心下标](https://leetcode-cn.com/problems/find-pivot-index/)



```java
public int pivotIndex(int[] nums) {
    int n = nums.length;
    int[] pre = new int[n + 1];
    for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];
    for (int i = 0; i < n; i++) {
        if (pre[i] == pre[n] - pre[i + 1]) return i;
    }
    return -1;
}
```



## [926. 将字符串翻转到单调递增](https://leetcode.cn/problems/flip-string-to-monotone-increasing/)

### 方法1：前缀和

```java
       public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //zero 统计0的前缀和 one 统计1的前缀和
            int[] zero = new int[n + 1], one = new int[n + 1];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    zero[i + 1] = zero[i] + 1;
                    one[i + 1] = one[i];
                } else {
                    one[i + 1] = one[i] + 1;
                    zero[i + 1] = zero[i];
                }
            }
            int res = s.length();
            for (int i = 1; i <= n; i++) {
                //前部分1的个数  后部分0的个数
                int pre_one = one[i], suc_zero = zero[n] - zero[i];
                //将前部分的1都变成0 后部分的0都变成1 形如00000111这样的结果
                res = Math.min(res, pre_one + suc_zero);
                //前部分0的个数
                int pre_zero = zero[i];
                //将前部分的0都变成1 后部分的0都变成1 形如111111111这样的结果
                res = Math.min(res, pre_zero + suc_zero);
            }
            return res;
        }
```

- 另，下面的是官方的解，说不上来哪里不一样，官方解执行了上面代码的前一种case，即将前部分的1都变成0 后部分的0都变成1 形如00000111这样的结果

```java
    public int minFlipsMonoIncr(String S) {
        int N = S.length();
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= N; ++j) {
            ans = Math.min(ans, P[j] + N-j-(P[N]-P[j]));
        }

        return ans;
    }

```

### 方法2：朴素版DP

```java
        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //dp[i][0]表示前i个元素，最后一个元素为0的最小翻转次数；
            //dp[i][1]表示前i个元素，最后一个元素为1的最小翻转次数
            int[][] dp = new int[n][2];
            //初始化
            char c0 = s.charAt(0);
            //第一个字符本身是'0'的话，不需要翻转，如果是'1'需要执行一次翻转
            dp[0][0] = c0 == '0' ? 0 : 1;
            //第一个字符是'1'的话，不需要翻转，如果是'0'的话，需要执行一次翻转
            //鉴于下面的转移会拿dp[i-1][0] 和 dp[i-1][1]的最小值，这个最小值肯定为0，对于[0]这个元素来说
            //下面的这段初始化可以省略，设置为默认值0
//            dp[0][1] = c0 == '1' ? 0 : 1;
            for (int i = 1; i < n; i++) {
                char c = s.charAt(i);
                //注意优先级
                //前[i-1]个元素必须要都是0，c如果是'1'要执行一次翻转
                dp[i][0] = dp[i - 1][0] + (c == '0' ? 0 : 1);
                //前[i-1]可以都是0 可以以'1'结束，c如果是'0'，要进行一次翻转
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + (c == '1' ? 0 : 1);
            }
            //总的区间，取最终变成 000000这种格式还是000011这种格式
            return Math.min(dp[n - 1][0], dp[n - 1][1]);
        }
```

### 方法3：空间压缩DP

```java
        //[i]的状态只和[i-1]即前一个状态有关，可以进行空间优化
        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //当前下标i下字符为0和1的情况下，使得s[0...i]单调递增的最小翻转次数
            int f0 = 0, f1 = 0;
            for (int i = 0; i < n; i++) {
                //下一轮的 f0 f1
                int _f0 = f0, _f1 = Math.min(f0, f1);
                if (s.charAt(i) == '1') {//当前下标i的字符是'1'
                    _f0++;
                } else {
                    _f1++;
                }
                f0 = _f0;
                f1 = _f1;
            }
            return Math.min(f0, f1);
        }
```

### 方法4：LIS+二分

#### 一些名词

- 最长上升子序列($LIS$):`Longest Increasing Subsequence `
- 最长连续序列($LCS$):`Longest Consecutive Sequence `
- 最长连续递增序列($LCIS$):`Longest Continuous Increasing Subsequence`
- 最长公共子序列($LCS$):`Longest Common Subsequence`

> 子串与子序列区别：子串不可跳跃，子序列可以跳跃，如 “AC”是“ABCDEFG”的子序列，而不是子串。 而“ABC”则是其子串

- 模板题是300题

```java
        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //维护一个单调递增的tails数组 形如 00011
            char[] tails = new char[n];
            int index = 0;//tails数组当前的处理到的位置
            for (char x : s.toCharArray()) {
                int i = 0, j = index;//
                while (i < j) {
                    int mid = i + (j - i) / 2;
                    //如果当前的x比tails[mid]大，说明应该放在mid的右侧位置，否则放在左侧位置
                    if (tails[mid] <= x) i = mid + 1;
                    else j = mid;
                }
                tails[i] = x;//x放置的位置
                //如果放在末尾，index要偏移一个值
                if (j == index) index++;
            }
            //index表示 单调递增的长度(恰好在上面的操作中+1了)，剩下的n -index就是要翻转的字符，即翻转的操作数
            return n - index;
        }
```









## [974. 和可被 K 整除的子数组](https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/)





```java
    public int subarraysDivByK(int[] A, int K) {

        int sum = 0, ans = 0, n = A.length;
        int[] map = new int[K];
        map[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum += A[i - 1];
            int key = (sum % K + K) % K;
            ans += map[key];
            map[key]++;
        }
        return ans;
    }
```

- TLE

```java
    public int subarraysDivByK(int[] A, int K) {
        int n = A.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + A[i];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if ((pre[i] - pre[j]) % K == 0) ans++;
            }
        }
        return ans;
    }
```

## 差分数组

### 定义

> **对于一个长度大小为n的数组arr[n] 我们可以建立他的差分数组f[n]。其中f[i] = arr[i] - arr[i-1]。  例如 f[2] = arr[2] - arr[1]。**

### 性质

(1) 可通过差分数组计算arr[i]的值：
	arr[i] = f[i] + f[i-1] + ... + f[0]  或 arr[i] = f[i] + arr[i-1]
(2) 计算数组每一项的前缀和：
	这里简单解释一下： 如果要求sum2 则sum2 = arr[0] + arr[1] + arr[2] 
	而每个arr[i] = f[i] + f[i-1] + ... + f[0]  故 sum2的求解中 f[0]出现3次 f[1]出现2次 f[2] 1次
	故sum[2] = 3 * f[0] + 2 * f[1] + 1 * f[2]

### 用途

(1) 区间快速加减操作：
	假如现在对数列中区间[L,R]上的数加上x，我们通过性质(1)知道，第一个受影响的差分数组中的元素为f[L],
即令f[L]+=x，那么后面数列元素在计算过程中都会加上x；最后一个受影响的差分数组中的元素为f[R],
所以令f[R+1]-=x，即可保证不会影响到R以后数列元素的计算。这样我们不必对区间内每一个数进行处理，
只需处理两个差分后的数即可。
(2) 询问区间和
	求区间[l, r]的和即为 sum[R] - sum[L-1] 



### 差分

差分是一种和前缀和相对的策略，可以当做是求和的逆运算。

这种策略的定义是令  $ b_i=\begin{cases}
a_i-a_{i-1},\quad i\in [2,n]\\
1, \quad i=1
\end{cases} $

简单性质：

-  $a_i$的值是$b_i$的前缀和，即 $ a_i= \sum_{i=1}^n b_i $
-  计算 的前缀和 $ sum= \sum_{i=1}^n a_i =\sum_{i=1}^n\sum_{j=1}^i b_i =\sum_{i=1}^n (n-i+1)b_i  $

它可以维护多次对序列的一个区间加上一个数，并在最后询问某一位的数或是多次询问某一位的数。注意修改操作一定要在查询操作之前。









## [1094. 拼车](https://leetcode-cn.com/problems/car-pooling/)

- 差分

```java
        public boolean carPooling(int[][] trips, int capacity) {
            int n = 1010;
            int[] d = new int[n];
            for (int[] t : trips) {
                d[t[1]] += t[0];
                d[t[2]] -= t[0];
            }
            for (int i = 0; i < n - 1; i++) {
                if (d[i] > capacity) return false;
                d[i + 1] += d[i];
            }
            return true;
        }
```





## [1109. 航班预订统计](https://leetcode-cn.com/problems/corporate-flight-bookings/)

- 差分数组模板题

```java
public int[] corpFlightBookings(int[][] bookings, int n) {
    //差分数组
    int[] d = new int[n + 1];
    for (int[] b : bookings) {
        int l = b[0] - 1, r = b[1] - 1, v = b[2];
        d[l] += v;
        d[r + 1] -= v;
    }
    int[] res = new int[n];
    res[0] = d[0];
    for (int i = 1; i < n; i++) res[i] = res[i - 1] + d[i];
    return res;
}
```





## [1310. 子数组异或查询](https://leetcode-cn.com/problems/xor-queries-of-a-subarray/)

```java
public int[] xorQueries(int[] arr, int[][] queries) {
    int n = arr.length;
    int[] pre = new int[n + 1];
    for (int i = 0; i < n; i++) pre[i + 1] = pre[i] ^ arr[i];
    int m = queries.length;
    int[] ans = new int[m];
    for (int i = 0; i < m; i++) {
        int[] q = queries[i];
        ans[i] = pre[q[1] + 1] ^ pre[q[0]];
    }
    return ans;
}
```









## [1480. 一维数组的动态和](https://leetcode-cn.com/problems/running-sum-of-1d-array/)

### 方法1：前缀和

```java
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        pre[0] = nums[0];
        for(int i =1;i<n;i++) pre[i] =pre[i-1]+nums[i];
        return pre;
    }
```

## [1524. 和为奇数的子数组数目](https://leetcode-cn.com/problems/number-of-sub-arrays-with-odd-sum/)

### 方法1：前缀和+数学

- 选出一个奇数的前缀和一个偶数的前缀和，相减一定得到一个奇数，多少个奇数 * 多少个偶数 = 方案数
- 加上单独拉拉出来的奇数的前缀和的数量



```java
public int numOfSubarrays(int[] arr) {
    int MOD = (int) 1e9 + 7;
    long ans = 0;
    int n = arr.length;
    int[] pre = new int[n + 1];
    long odd = 0, even = 0;//奇数的个数，偶数的个数
    for (int i = 0; i < n; i++) {
        pre[i + 1] = pre[i] + arr[i];
        if ((pre[i + 1] % 2) == 1) odd++;
        else even++;
    }
    ans = (odd * even) % MOD + odd;
    return (int) ans % MOD;
}
```

- 另一种写法

```java
public int numOfSubarrays(int[] arr) {
    final int MOD = (int) 1e9 + 7;
    int odd = 0, even = 1;
    int res = 0;
    int sum = 0;
    int length = arr.length;
    for (int i = 0; i < length; i++) {
        sum += arr[i];
        res = (res + (sum % 2 == 0 ? odd : even)) % MOD;
        if (sum % 2 == 0) {
            even++;
        } else {
            odd++;
        }
    }
    return res;
}
```





## 1674. 使数组互补的最少操作数

- 差分





## [1685. 有序数组中差绝对值之和](https://leetcode-cn.com/problems/sum-of-absolute-differences-in-a-sorted-array/)

- 2121题的简化版本

```java
public int[] getSumAbsoluteDifferences(int[] nums) {
    int n = nums.length;
    int[] pre = new int[n];
    int t = 0;
    for (int i = 0; i < n; i++) {
        t += nums[i];
        pre[i] = t;
    }
    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
        int left = (i + 1) * nums[i] - pre[i];
        int right = pre[n - 1] - pre[i] - (n - i - 1) * nums[i];
        res[i] = left + right;
    }
    return res;
}
```





## [1738. 找出第 K 大的异或坐标值](https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/)

### 方法1：前缀和+排序

```java
public int kthLargestValue(int[][] matrix, int k) {
    int R = matrix.length, C = matrix[0].length;
    int[][] arr = new int[R + 1][C + 1];
    PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
            arr[i][j] = arr[i - 1][j - 1] ^ arr[i - 1][j] ^ arr[i][j - 1] ^ matrix[i - 1][j - 1];
            pq.offer(arr[i][j]);
        }
    }
    while (k-- > 1) pq.poll();
    return pq.peek();
}
```

- 另外一种写法

```java
public int kthLargestValue(int[][] matrix, int k) {
    int m = matrix.length, n = matrix[0].length;
    int[][] pre = new int[m + 1][n + 1];
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
            list.add(pre[i][j]);
        }
    }
    Collections.sort(list, (a, b) -> b - a);
    return list.get(k - 1);
}
```

### 方法2：前缀和+快排

```java
public int kthLargestValue(int[][] matrix, int k) {
    int m = matrix.length, n = matrix[0].length;
    int[][] pre = new int[m + 1][n + 1];
    List<Integer> results = new ArrayList<Integer>();
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
            results.add(pre[i][j]);
        }
    }

    nthElement(results, 0, k - 1, results.size() - 1);
    return results.get(k - 1);
}

public void nthElement(List<Integer> results, int left, int kth, int right) {
    if (left == right) {
        return;
    }
    int pivot = (int) (left + Math.random() * (right - left + 1));
    swap(results, pivot, right);
    // 三路划分（three-way partition）
    int sepl = left - 1, sepr = left - 1;
    for (int i = left; i <= right; i++) {
        if (results.get(i) > results.get(right)) {
            swap(results, ++sepr, i);
            swap(results, ++sepl, sepr);
        } else if (results.get(i) == results.get(right)) {
            swap(results, ++sepr, i);
        }
    }
    if (sepl < left + kth && left + kth <= sepr) {
        return;
    } else if (left + kth <= sepl) {
        nthElement(results, left, kth, sepl);
    } else {
        nthElement(results, sepr + 1, kth - (sepr - left + 1), right);
    }
}

public void swap(List<Integer> results, int index1, int index2) {
    int temp = results.get(index1);
    results.set(index1, results.get(index2));
    results.set(index2, temp);
}
```



## [2121. 相同元素的间隔之和](https://leetcode-cn.com/problems/intervals-between-identical-elements/)



```java
public long[] getDistances(int[] arr) {
    // k: arr[i] v: i
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
        List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
        list.add(i);
        map.put(arr[i], list);
    }
    long[] intervals = new long[arr.length];
    for (List<Integer> ls : map.values()) {
        long sum = 0;
        for (int index : ls) sum += index - ls.get(0);
        intervals[ls.get(0)] = sum;
        for (int i = 1; i < ls.size(); i++) {
            sum += (2 * i - ls.size()) * (ls.get(i) - ls.get(i - 1));
            intervals[ls.get(i)] = sum;
        }
    }
    return intervals;
}
```



- [题解](https://leetcode-cn.com/problems/intervals-between-identical-elements/solution/java-qian-zhui-hou-zhui-he-by-merickbao-mtcpy/)

```java
public long[] getDistances(int[] arr) {
    // k: arr[i] v: i
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
        List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
        list.add(i);
        map.put(arr[i], list);
    }
    long[] intervals = new long[arr.length];
    for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
        List<Integer> ls = e.getValue();
        int m = ls.size();
        long[] left = new long[m];
        long[] right = new long[m];
        for (int i = 1, j = m - 2; i < m; i++, j--) {
            left[i] = left[i - 1] + i * (ls.get(i) - ls.get(i - 1));
            right[j] = right[j + 1] + (m - j - 1) * (ls.get(j + 1) - ls.get(j));
        }
        for (int i = 0; i < m; i++) {
            intervals[ls.get(i)] = left[i] + right[i];
        }
    }
    return intervals;
}
```



## [2222. 选择建筑的方案数](https://leetcode.cn/problems/number-of-ways-to-select-buildings/)

### 方法1：前缀和

- 本题类似926

```java
       public long numberOfWays(String s) {
            int n = s.length();
            long[] one = new long[n + 1];//记录'1'的数量，剩下的便是'0'的数量
            for (int i = 1; i <= n; i++) {
                one[i] = one[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    //形成101这样的结构
                    long a = one[i + 1], b = one[n] - one[i + 1];
                    res += a * b;
                } else {
                    //形成010这样的结构
                    long a = i + 1 - one[i + 1], b = n - i - (one[n] - one[i]);
                    res += a * b;
                }
            }
            return res;
        }
```

- 另，不用可以统计前缀和

```java
        public long numberOfWays(String s) {
            long tot_zero = 0, cur_zero = 0;//统计所有'0'的数量 当前所有'0'的数量
            int n = s.length();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '0') tot_zero++;
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '1') {//010
                    res += cur_zero * (tot_zero - cur_zero);
                } else {//101
                    long cur_one = i - cur_zero;
                    // n - 所有的0 剩下所有的1 再减去当前的1 得到剩下的1 
                    res += cur_one * (n - tot_zero - cur_one);
                    cur_zero++;
                }
            }
            return res;

        }
```











## [2256. 最小平均差](https://leetcode-cn.com/problems/minimum-average-difference/)

```java
//注意使用long类型
public int minimumAverageDifference(int[] nums) {
    int n = nums.length;
    long[] pre = new long[n + 1];
    for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];
    long minn = 100010;
    int index = 0;
    for (int i = 0; i < n; i++) {
        long front = (int) (pre[i + 1] / (i + 1));
        long back = i == n - 1 ? 0 : (long) ((pre[n] - pre[i + 1]) / (n - i - 1));
        long diff = Math.abs(front - back);
        if (diff < minn) {
            minn = diff;
            index = i;
        }
    }
    return index;
}
```



### Reference

- [STUACM-算法入门-前缀和与差分(含二维)](https://www.bilibili.com/video/BV1pi4y1j7si?p=2)

- [其他「区间求和」问题](https://leetcode-cn.com/problems/corporate-flight-bookings/solution/gong-shui-san-xie-yi-ti-shuang-jie-chai-fm1ef/)
