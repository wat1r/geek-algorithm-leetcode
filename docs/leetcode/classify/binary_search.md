





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



## [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

```java
public int[] searchRange(int[] nums, int target) {
    int[] res = new int[]{-1, -1};
    if (nums == null || nums.length == 0) return res;
    int n = nums.length;
    int l = 0, r = n - 1;
    while (l < r) {
        int mid = l + (r - l) / 2;//下取整
        if (nums[mid] < target) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    if (nums[l] == target) res[0] = l;

    l = 0;
    r = n - 1;
    while (l < r) {
        int mid = l + (r - l + 1) / 2;//上取整
        if (nums[mid] > target) {
            r = mid - 1;
        } else {
            l = mid;
        }
    }
    if (nums[l] == target) res[1] = l;
    return res;
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





## [162. 寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)

```java
    public int findPeakElement(int[] nums) {
        int n = nums.length , l = 0, r= n-1;
        while(l<r){
            int mid = l +(r-l)/2;
            if(nums[mid]<nums[mid+1]){
                l= mid+1;
            }else{
                r= mid;
            }
        }
        return l; 
    }
```



## [278. 第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/)

```java
    public int firstBadVersion(int n) {
        int l = 1 , r = n;
        while(l<r){
            int mid = l+(r-l)/2;
            if(isBadVersion(mid)) r = mid;
            else l = mid+1;
        }
        return l;
    }
```









## [704. 二分查找](https://leetcode-cn.com/problems/binary-search/)

```java
public int search(int[] nums, int target) {
    int n = nums.length, l = 0, r = n - 1;
    while (l < r) {
        int mid = l + (r - l) / 2;
        int t = nums[mid];
        if (t == target) return mid;
        else if (t > target) r = mid - 1;
        else l = mid + 1;
    }
    return nums[l] == target ? l : -1;
}
```