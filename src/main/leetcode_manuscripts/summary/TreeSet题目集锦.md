# TreeSet题目集锦





### [220. 存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/)

```java
public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    TreeSet<Long> ts = new TreeSet<>();//注意long型
    for (int i = 0; i < nums.length; i++) {
        if (i > k) ts.remove((long) nums[i - k - 1]);//k的滑窗
        Long ceil = ts.ceiling(((long) nums[i] - t));//返回一个最小且大于等于这个元素的元素
        if (ceil != null && ceil <= (long) nums[i] + t) {
            return true;
        }
        ts.add((long) nums[i]);
    }
    return false;
}
```





### [861 · K个空的位置](https://www.lintcode.com/problem/861/)

> 同lc 683

```java
        public int kEmptySlots(int[] flowers, int k) {
            TreeSet<Integer> ts = new TreeSet<>();
            for (int i = 0; i < flowers.length; i++) {
                Integer lower = ts.lower(flowers[i]);
                Integer higher = ts.higher(flowers[i]);
                ts.add(flowers[i]);
                if (lower != null && flowers[i] - lower - 1 == k) return i + 1;
                if (higher != null && higher - flowers[i] - 1 == k) return i + 1;
            }
            return -1;
        }
```



### [1818. 绝对差值和](https://leetcode-cn.com/problems/minimum-absolute-sum-difference/)

```java
int MOD = (int) 1e9 + 7;

public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
    int n = nums1.length;
    TreeSet<Integer> ts = new TreeSet<>();
    long sum = 0;
    for (int i = 0; i < n; i++) {
        ts.add(nums1[i]);
        sum += Math.abs(nums1[i] - nums2[i]);
    }
    int maxDecrease = 0;
    for (int i = 0; i < n; i++) {
        if (nums1[i] != nums2[i]) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            int max = 0;
            Integer ceil = ts.ceiling(nums2[i]);
            Integer floor = ts.floor(nums2[i]);
            if (ceil != null) {
                max = Math.max(max, diff - Math.abs(nums2[i] - ceil));
            }
            if (floor != null) {
                max = Math.max(max, diff - Math.abs(nums2[i] - floor));
            }
            maxDecrease = Math.max(maxDecrease, max);

        }
    }
    return (int) ((sum - maxDecrease) % MOD);

}
```

