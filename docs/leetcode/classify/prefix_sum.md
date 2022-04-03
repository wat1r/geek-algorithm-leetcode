

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