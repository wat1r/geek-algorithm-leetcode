

## [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

```java
public int search(int[] nums, int target) {
    int n = nums.length, l = 0, r = n - 1;
    while (l < r) {
        int m = l + (r - l) / 2;//下取整
        if (nums[m] == target) return m;
        else if (nums[m] >= nums[l]) {//与左端点比较后再与target值比较
            if (target >= nums[l] && target <= nums[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        } else if (nums[m] < nums[l]) {
            if (target > nums[m] && target <= nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
    }
    return nums[l] == target ? l : -1;
}
```

## [81. 搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)

```java
public boolean search(int[] nums, int target) {
    int l = 0, r = nums.length - 1;
    while (l <= r) {
        int m = l + (r - l) / 2;
        if (nums[m] == target) return true;
        if (nums[m] > nums[l]) {
            if (nums[m] >= target && target >= nums[l]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        } else if (nums[m] < nums[l]) {
            if (nums[r] >= target && target >= nums[m]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        } else if (nums[m] == nums[l]) {
            l++;
        }
    }

    return false;
}
```



## [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

```java
public int findMin(int[] nums) {
    int l = 0, r = nums.length - 1;
    while (l < r) {
        int m = l + (r - l) / 2;//和最右端点r比较
        if (nums[m] == nums[r]) return nums[m];
        else if (nums[m] > nums[r]) {
            l = m + 1;
        } else if (nums[m] < nums[r]) {
            r = m;
        }
    }
    return nums[l];
}
```



## [154. 寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)

```java
public int findMin(int[] nums) {
    int l = 0, r = nums.length - 1;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] > nums[r]) l = mid + 1;
        else if (nums[mid] < nums[r]) r = mid;
        else r = r - 1;
    }
    return nums[r];
}
```