



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