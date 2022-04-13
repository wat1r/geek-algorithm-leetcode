

> 



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



