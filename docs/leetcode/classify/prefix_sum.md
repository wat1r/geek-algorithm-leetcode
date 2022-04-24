

> 



![image-20220422073930902](/Users/frankcooper/Library/Application Support/typora-user-images/image-20220422073930902.png)







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





### Reference

- [STUACM-算法入门-前缀和与差分(含二维)](https://www.bilibili.com/video/BV1pi4y1j7si?p=2)

- [其他「区间求和」问题](https://leetcode-cn.com/problems/corporate-flight-bookings/solution/gong-shui-san-xie-yi-ti-shuang-jie-chai-fm1ef/)
