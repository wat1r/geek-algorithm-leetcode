## 双指针解四数之和[Topi]

![antelope-5200114_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解四数之和[Topi].assets\antelope-5200114_640.jpg)



### 1.四数之和I

![image-20200806211809453](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解四数之和[Topi].assets\image-20200806211809453.png)

- 和三数之和的逻辑极度类似，难点是需要去重



```java
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 4) return results;
        Arrays.sort(nums);
        int n = nums.length;
        for (int a = 0; a < n; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) continue;
            for (int b = a + 1; b < n; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;
                int c = b + 1, d = n - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum == target) {
                        results.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c + 1] == nums[c]) c++;
                        while (c < d && nums[d - 1] == nums[d]) d--;
                        c++;
                        d--;
                    } else if (sum < target) c++;
                    else if (sum > target) d--;
                }
            }
        }
        return results;
    }

```















### 2.四数之和II