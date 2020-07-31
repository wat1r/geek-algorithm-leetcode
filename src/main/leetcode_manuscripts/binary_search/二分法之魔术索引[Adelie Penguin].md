## 二分法之魔术索引[Adelie Penguin]



![penguins-295133_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_search\二分法之魔术索引[Adelie Penguin].assets\penguins-295133_960_720.png)





![image-20200731075414030](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_search\二分法之魔术索引[Adelie Penguin].assets\image-20200731075414030.png)

### 方法1:线性扫描

线性扫描，因为数组有序，第一个符合条件的返回，扫描完了都没有符合条件的，返回$-1$

```java
    public int findMagicIndex(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) return i;
        }
        return -1;
    }
```

### 方法2:二分+剪枝

准备一个函数$helper(nums,left,right)$,这个函数求$nums[left...right]$区间范围内第一个符合条件的值（值与索引相等时返回），没有符合的值时，返回$-1$,

**一个前提：数组是有序的，从左到右是上升的**

- 每次优先搜索$[left...mid-1]$部分，如果这部分的返回值不为$-1$,返回这个值即可（注意$helper$函数的定义），如果这个值为$-1$，说明$[left...mid-1]$部分没有符合条件的值，判断下$nums[mid]$与$mid$的值是否相等，相等则返回，不相等？进入下一步
- 搜索$[mid+1...right]$,逻辑和搜索左半部分的逻辑相同，在这个大区间上分出左右部分

![image-20200731081707111](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_search\二分法之魔术索引[Adelie Penguin].assets\image-20200731081707111.png)

```java
    public int findMagicIndex(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left > right) return -1;
        int mid = (right - left) / 2 + left;
        int leftRes = helper(nums, left, mid - 1);
        if (leftRes != -1) return leftRes;
        else if (nums[mid] == mid) return mid;
        return helper(nums, mid + 1, right);
    }
```

