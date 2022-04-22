

> 



![image-20220422073930902](/Users/frankcooper/Library/Application Support/typora-user-images/image-20220422073930902.png)





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
