



## [4. 寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)

### 方法1:双指针

- 双指针merge两个数组，用多余的数组来存储最终的结果

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] arr = mergeArray(nums1, nums2);
    int n = arr.length;
    if (n % 2 == 0) {
        return (arr[(n - 1) / 2] + arr[n / 2]) / 2.0;
    } else {
        return arr[n / 2];
    }
}

private int[] mergeArray(int[] nums1, int[] nums2) {
    int m = nums1.length, n = nums2.length;
    int[] arr = new int[m + n];
    int i = 0, j = 0, k = 0;
    while (i < m && j < n) {
        arr[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
    }
    while (i < m) {
        arr[k++] = nums1[i++];
    }
    while (j < n) {
        arr[k++] = nums2[j++];
    }
    return arr;
}
```

#### 复杂度分析

- 时间复杂度：`O(m+n)`其中`m`和`n`是两个数组的长度
- 空间复杂度：`O(m+n)`其中`m`和`n`是两个数组的长度

### 方法2:求Kth

![](/imgs/leetcode/classify/image-20220403111627121.png)



![](/imgs/leetcode/classify/image-20220403111642166.png)

![](/imgs/leetcode/classify/image-20220403111716802.png)

![](/imgs/leetcode/classify/image-20220403111729304.png)



**关于` if (len1 == 0) return nums2[start2+k-1];`的判断**

- 拿`nums1=[1,2]`和`nums2=[3,4]`举例，当`nums1`用完的时候，`start1`已经跳到`len(nums1)`的位置，开始发生越界，这时候说明`nums1`已经用完了，按`nums2`的数组下标计算目标索引，也就是`nums2[start2+k-1]`,因为一开始保证了`nums1`比`nums2`长度小

```java
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            //各自上下取整，错开一个位置
            int left = (m + n + 1) / 2;
            int right = (m + n + 2) / 2;
            //计算上下取整的位置的Kth，免去判断奇偶数
            int leftKthNum = getKthNum(nums1, 0, m - 1, nums2, 0, n - 1, left);
            int rightKthNum = getKthNum(nums1, 0, m - 1, nums2, 0, n - 1, right);
            return (leftKthNum + rightKthNum) / 2.0;
        }

        /**
         * @param nums1  数组1
         * @param start1 数组1的开始位置
         * @param end1   数组1的结束位置
         * @param nums2  数组2
         * @param start2 数组2的开始位置
         * @param end2   数组2的结束位置
         * @param k      第k个
         * @return
         */
        public int getKthNum(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            //当前[start1..end1] 和 [start2...end2]的长度
            int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1;
            //始终保持nums1的长度短
            if (len1 > len2) return getKthNum(nums2, start2, end2, nums1, start1, end1, k);
            //拿`nums1=[1,2]`和`nums2=[3,4]`举例，当`nums1`用完的时候，`start1`已经跳到`len(nums1)`的位置，开始发生越界，这时候说明`nums1`已经用完了，按`nums2`的数组下标计算目标索引，也就是`nums2[start2+k-1]`,因为一开始保证了`nums1`比`nums2`长度小
            if (len1 == 0) return nums2[start2 + k - 1];
            //k经剩下1个时，说明找到了
            if (k == 1) return Math.min(nums1[start1], nums2[start2]);
            //每次取k的一半 计算新的i和j开始二分
            int i = start1 + Math.min(len1, k / 2) - 1;
            int j = start2 + Math.min(len2, k / 2) - 1;
            //如果nums1[i] > nums2[j]去掉[start2...j]的部分，反之，去掉[start1...i]的部分
            if (nums1[i] > nums2[j]) {
                return getKthNum(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
            } else {
                return getKthNum(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }
```

#### 复杂度分析

- 时间复杂度：`O(log(m+n))`其中`m`和`n`是两个数组的长度
- 空间复杂度：`O(1`没有使用到多余的空间



### 方法3:二分

![](/imgs/leetcode/classify/image-20220403124538448.png)

.

```java
            public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if (m > n) return findMedianSortedArrays(nums2, nums1);
            //true：奇数 false：偶数
            boolean isOdd = ((m + n) & 1) == 1;
            int start = 0, end = m;
            int cut1, cut2;
            while (start <= end) {
                cut1 = (start + end) / 2;
                cut2 = (m + n + 1) / 2 - cut1;
                //nums1左半部分和右半部分的最小值和最大值，结合边界考虑
                int nums1LeftMax = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
                int nums1RightMin = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
                //nums2左半部分和右半部分的最小值和最大值，结合边界考虑
                int nums2LeftMax = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
                int nums2RightMin = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];
                // nums1左半部分最大值比nums2右半部分的最小值大，说明需要缩减[start...cut1]这个范围
                if (nums1LeftMax > nums2RightMin) end = cut1 - 1;
                 // nums1右半部分最小值比nums2左半部分的最大值小，说明需要扩大[cut1...end]这个范围
                else if (nums2LeftMax > nums1RightMin) start = cut1 + 1;
                //开始返回结果，结合奇偶性
                else {
                    if (isOdd) return Math.max(nums1LeftMax, nums2LeftMax);
                    else
                        return ((double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
                }

            }
            return 0.0;
        }
```

#### 复杂度分析

- 时间复杂度：`O(log(m+n))`其中`m`和`n`是两个数组的长度
- 空间复杂度：`O(1`没有使用到多余的空间

### Follow Up1:给定两个排序数组，找第Kth大的元素

- 代码可以参考上面方法2

### Follow Up2:给定一个无序数组，找第Kth大的元素

#### [215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

##### 方法1:优先队列

```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++) {
        if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
        if (pq.size() > k) pq.poll();
    }
    return pq.peek();
}
```

##### 方法2:快排

```java
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            int target = n - k, l = 0, r = n - 1;
            while (true) {
                //找到partition的索引
                int idx = partition(nums, l, r);
                //如果找到，返回
                if (idx == target) return nums[idx];
                else if (idx > target) r = idx - 1;
                else l = idx + 1;
            }
        }


        public int partition(int[] nums, int l, int r) {
            //随机化一个pivot
            if (l < r) {
                int rand = l + 1 + new Random().nextInt(r - l);
                swap(nums, l, rand);
            }
            int pivot = nums[l];
            int j = l;
            for (int i = l + 1; i <= r; i++) {
                //小于pivot的元素都移动到左侧
                if (nums[i] < pivot) {
                    j++;
                    swap(nums, j, i);
                }
            }
            //[l...j-1]的元素都小于 pivot， [j]=pivot [j+1...r]的元素>=pivot
            swap(nums, j, l);
            return j;
        }

        public void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }

```

##### 方法3:堆排序

```java
public int findKthLargest(int[] nums, int k) {
    int n = nums.length;
    heapInsert(nums);
    for (int i = 0; i < k - 1; i++) {
        int tmp = nums[0];
        nums[0] = nums[n - 1 - i];
        nums[n - 1 - i] = tmp;
        heapify(nums, 0, n - 1 - i - 1);
    }
    return nums[0];
}


public void heapInsert(int[] nums) {
    int n = nums.length;
    for (int root = n / 2; root > -1; root--) {
        heapify(nums, root, n - 1);
    }
}

public void heapify(int[] nums, int root, int hi) {
    if (root > hi)
        return;
    int t = nums[root];
    int child = 2 * root + 1;
    while (child <= hi) {
        if (child + 1 <= hi && nums[child] < nums[child + 1])
            child++;
        if (t > nums[child])
            break;
        nums[root] = nums[child];
        root = child;
        child = 2 * root + 1;
    }
    nums[root] = t;
}
```









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







## [475. 供暖器](https://leetcode-cn.com/problems/heaters/)

### 方法1

```java
//这个case表示在1 2 3 4 位置都有热水器 但是只有1位置有房子，结果为0
//[1]
//[1,2,3,4]
public int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(houses);
    Arrays.sort(heaters);
    int l = 0, r = (int) 1e9;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (check(houses, heaters, mid)) r = mid;
        else l = mid + 1;
    }
    return l;
}

public boolean check(int[] houses, int[] heaters, int target) {
    int n = houses.length, m = heaters.length;
    for (int i = 0, j = 0; i < n; i++) {
        while (j < m && houses[i] > heaters[j] + target) {
            j++;
        }
        if (j < m && heaters[j] - target <= houses[i] && houses[i] <= heaters[j] + target) {
            continue;
        }
        return false;
    }
    return true;
}
```

### 方法2

```java
public int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(heaters);
    int m = houses.length, n = heaters.length;
    int res = Integer.MIN_VALUE;
    for (int house : houses) {
        int idx = Arrays.binarySearch(heaters, house);
        if (idx < 0) {
            idx = ~idx;
            int dist1 = idx - 1 >= 0 ? house - heaters[idx - 1] : Integer.MAX_VALUE;
            int dist2 = idx < n ? heaters[idx] - house : Integer.MAX_VALUE;
            res = Math.max(res, Math.min(dist1, dist2));
        }
    }
    return res;
}
```



### 方法3

```java
    public int findRadius(int[] houses, int[] heaters) {
      Arrays.sort(houses);
      Arrays.sort(heaters);
      
      int i = 0, res = 0;
      for(int house: houses){
        while(i + 1 < heaters.length && Math.abs(heaters[i] - house) >= Math.abs(heaters[i + 1] - house)){
          i++;    
        }
        res = Math.max(res, Math.abs(heaters[i] - house));
      }
      return res;
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

