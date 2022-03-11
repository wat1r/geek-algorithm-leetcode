## 双指针解三数之和[Takin]

![kudu-4690056_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解三数之和[Takin].assets\kudu-4690056_640.png)

![image-20200806210358818](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解三数之和[Takin].assets\image-20200806210358818.png)





![image-20200806204740759](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解三数之和[Takin].assets\image-20200806204740759.png)

- 先将$nums$排序
- 三个指针变量，$i$ 指向数组的最左侧，初始化时为$0$位置，$r$指向数组的最右侧，初始化时为$n-1$的位置，$l$指针指向$i+1$为位置
- 要求$sum$= $nums[i]$+$nums[l]$+$nums[r]$=$0$
- $nums[l]$>$0$说明$l$，$i$,$r$三个位置的数都大于$0$，结果不可能为$0$，结束，返回
  - $sum$<$0$时，说明，总和小了，$l$++
  - $sum$>$0$s时，说明总和大了，$r$--
- 去重：
  - 当$i$与前一个$i-1$的值相同，跳过
  - 当$sum=0$,$l+1$ 与前前一个$l$的值相同，跳过
  - 当$sum=0$,$r-1$与后一个$r$的值相同，跳过

```java
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 3) return results;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    results.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l + 1] == nums[l]) l++;
                    while (l < r && nums[r - 1] == nums[r]) r--;
                    l++;
                    r--;
                } else if (sum < 0) l++;
                else if (sum > 0) r--;
            }
        }
        return results;
    }
```







