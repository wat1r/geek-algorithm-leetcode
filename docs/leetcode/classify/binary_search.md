



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



## [69. x 的平方根 ](https://leetcode-cn.com/problems/sqrtx/)

```java
//上取整
public int mySqrt(int x) {
    long l = 0, r = x;
    while (l < r) {
        long mid = l + r + 1 >> 1;
        if (mid * mid <= x) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return (int) l;
}
```



- 牛顿迭代法

```java

        int y;

        public int mySqrt(int x) {
            y = x;
            if (x == 0) return 0;
            return (int) helper(x);
        }

        private double helper(double x) {
            double res = (x + y / x) / 2;
            return res == x ? x : helper(res);
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





## [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)

### 方法1:双指针

```java
     public boolean searchMatrix(int[][] mat, int target) {
            int R = mat.length, C = mat[0].length;
            int r = 0, c = C - 1;
            while (r < R && c >= 0) {
                if (mat[r][c] == target) return true;
                else if (mat[r][c] < target) r++;
                else if (mat[r][c] > target) c--;
            }
            return false;
        }
```





### 方法2:二分

```java
    public boolean searchMatrix(int[][] matrix, int target) {
       int m = matrix.length, n = matrix[0].length;
       for(int i =0;i<m;i++){
           int l = 0, r = n-1;
           while(l<r){
               int mid = l+ (r-l)/2;
               if(matrix[i][mid]<target) l =mid+1;
               else r= mid;
           }
           if(matrix[i][l]==target) return true;
       } 
       return false;
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



## [436. 寻找右区间](https://leetcode.cn/problems/find-right-interval/)

### 方法1:二分

```java
       public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            //[0]存储原区间的左端点 [1]存储原区间的索引
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                arr[i][0] = intervals[i][0];
                arr[i][1] = i;
            }
            //arr按[0]即左端点从小到大排序
            Arrays.sort(arr, (a, b) -> a[0] - b[0]);
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int l = 0, r = n - 1;
                int t = -1;//终值
                while (l < r) {
                    int mid = l + (r - l) / 2;//上取整
                    //当前的[mid...r]区间比处理区间的右端点要大，说明mid是个可能最终值，mid的这个值需要暂时保留
                    if (arr[mid][0] >= intervals[i][1]) {
                        r = mid;
                        t = arr[i][1];
                    } else {
                        //[l...mid]这个区间的值要比处理区间的右端点小，说明这一段可以完整的排除
                        l = mid + 1;
                    }
                }
                //最后需要拦截下[l,r] 即[l]这个值是不是大于等于当前区间右端点
                if (arr[l][0] >= intervals[i][1]) res[i] = arr[l][1];
                else res[i] = t;
            }
            return res;
        }
```

- 另

```java
  public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            Map<int[], Integer> map = new HashMap<>();
            //排序后的idx变掉了，提前存下来
            for (int i = 0; i < n; i++) {
                map.put(intervals[i], i);
            }
            //start值唯一，没有重复，按start排序，二分时单调性
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int l = 0, r = n - 1, t = -1;
                while (l < r) {
                    //下取整：要求j最小化
                    //j满足要求时，说明当前的mid是可能的值，[l,mid]是我们想要的区间，因为要确定mid是否是最小化的j
                    //j不满足要求时，说明当前的mid一定不是我们想要的 [mid+1,r]是我们想要的区间
                    int mid = l + (r - l) / 2;
                    if (intervals[mid][0] >= intervals[i][1]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                int index = map.get(intervals[i]);
                if (intervals[l][0] >= intervals[i][1]) {
                    t = map.get(intervals[l]);
                }
                res[index] = t;
            }
            return res;
        }
```



### 方法2：双指针

> 思路参考官解

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220520080256.png)

```java
       public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            int[][] start = new int[n][2], end = new int[n][2];
            for (int i = 0; i < n; i++) {
                start[i][0] = intervals[i][0];
                start[i][1] = i;
                end[i][0] = intervals[i][1];
                end[i][1] = i;
            }
            Arrays.sort(start, Comparator.comparingInt(o -> o[0]));
            Arrays.sort(end, Comparator.comparingInt(o -> o[0]));
            int[] res = new int[n];
            for (int i = 0, j = 0; i < n; i++) {
                while (j < n && end[i][0] > start[j][0]) {
                    j++;
                }
                res[end[i][1]] = j < n ? start[j][1] : -1;
            }
            return res;
        }
```

### 方法3：TreeMap

```java
        public int[] findRightInterval(int[][] intervals) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            int n = intervals.length;
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                treeMap.put(intervals[i][0], i);
            }
            for (int i = 0; i < n; i++) {
                //返回大于或等于给定键的最小键，如果没有这样的键则返回null。
                Integer k = treeMap.ceilingKey(intervals[i][1]);
                res[i] = k == null ? -1 : treeMap.get(k);
            }
            return res;
        }
```

### 方法4：TreeSet

```java
        public int[] findRightInterval(int[][] intervals) {
            //[0]是区间的左端点，[1]是区间的索引,按[0]从小到大排序
            TreeSet<int[]> ts = new TreeSet<>((a, b) -> a[0] - b[0]);
            int n = intervals.length;
            for (int i = 0; i < n; i++) {
                ts.add(new int[]{intervals[i][0], i});
            }
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                //找大于等于当前区间的右端点的key
                int[] t = ts.ceiling(new int[]{intervals[i][1], i});
                res[i] = t == null ? -1 : t[1];
            }
            return res;
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



## [668. 乘法表中第k小的数](https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/)

```java
public int findKthNumber(int m, int n, int k) {
    int l = 1, r = m * n;
    while (l < r) {
        int mid = l + (r - l) / 2;//下取整
        int count = check(m, n, mid);//当前小于等于（不大于）mid的数的个数
        //count的数与k比较
        if (count < k) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return l;
}

//返回m*n的乘法表中，小于等于（不大于）target的数的个数
public int check(int m, int n, int target) {
    int count = 0;
    for (int i = 1; i <= m; i++) {
        //每一行的个数都是n个，target/i 表示当前行强制匹配到第一行，与第一行的n进行同基准比较
        count += Math.min(target / i, n);
    }
    return count;
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





## [719. 找出第 K 小的数对距离](https://leetcode.cn/problems/find-k-th-smallest-pair-distance/)

### 方法1：两层二分

```java
public int smallestDistancePair(int[] nums, int k) {
    //排序
    Arrays.sort(nums);
    //[lo,hi]表示距离 最大的距离为首尾元素的差值
    int n = nums.length, lo = 0, hi = nums[n - 1] - nums[0];
    while (lo <= hi) {
        //下取整
        int mid = lo + (hi - lo) / 2;
        int cnt = 0;
        //枚举每一个j
        for (int j = 0; j < n; j++) {
            //当前j的值往前推mid的距离，得到i的位置，查找i在nums中符合条件的最左的位置
            int target = nums[j] - mid;
            //查询到当前i能到的最左的位置的下标
            int i = binarySearch(nums, j, target);
            //个数累加
            cnt += j - i;
        }
        //个数比k小，说明当前的距离mid小了，还够使得数量到达k
        if (cnt < k) lo = mid + 1;
        else hi = mid - 1;//个数比k大，说明当前的距离mid大了

    }
    return lo;
}

//在nums[0...right]区间内找比大于等于target的最左的下标
private int binarySearch(int[] nums, int right, int target) {
    int lo = 0, hi = right;
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] < target) lo = mid + 1;
        else hi = mid;
    }
    return lo;
}
```

### 方法2：二分+双指针

```java
public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    int n = nums.length, lo = 0, hi = nums[n - 1] - nums[0];
    while (lo <= hi) {
        int mid = (lo + hi) / 2;
        int cnt = 0;
        for (int i = 0, j = 0; j < n; j++) {
            while (nums[j] - nums[i] > mid) {
                i++;
            }
            cnt += j - i;
        }
        if (cnt >= k) {
            hi = mid - 1;
        } else {
            lo = mid + 1;
        }
    }
    return lo;
}
```







## [744. 寻找比目标字母大的最小字母](https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/)



```java
//需要临界点判断下
public char nextGreatestLetter(char[] le, char ta) {
    if (le == null || le.length == 0) return ta;
    int l = 0, r = le.length - 1;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (le[mid] <= ta) l = mid + 1;
        else r = mid;
    }
    return le[l] <= ta ? (l == le.length - 1 ? le[0] : le[l + 1]) : le[l];
}
```





```java
public char nextGreatestLetter(char[] le, char ta) {
    if (le == null || le.length == 0) return ta;
    int l = 0, r = le.length - 1;
    while (l < r) {
        int mid = l + (r - l + 1) / 2;
        if (le[mid] <= ta) l = mid;
        else r = mid - 1;
    }
    return le[l] <= ta ? (l == le.length - 1 ? le[0] : le[l + 1]) : le[l];
}
```





```java
   public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length, l =0 , r = n-1;
        while(l< r ){
            int mid = l+(r-l)/2;
            if(letters[mid]<=target) l= mid+1;
            else r =mid;
        }
         return letters[l]> target ? letters[l] : letters[0];
    }
```



## [875. 爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas/)

### 方法1:二分

```java
 public int minEatingSpeed(int[] piles, int h) {
            int l = 0, r = 1_000_000_000;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (!possible(piles, h, m)) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            return l;
        }


        private boolean possible(int[] piles, int h, int t) {
              if (t == 0) return false;
            for (int i = 0; i < piles.length; i++) {
                h -= piles[i] % t == 0 ? piles[i] / t : piles[i] / t + 1;
                if (h < 0) return false;
            }
            return true;
        }
```

- 另

```java
        public int minEatingSpeed(int[] piles, int h) {
            int r = 0;
            for (int x : piles) r = Math.max(r, x);
            int l = 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
//                System.out.printf("%d\n", mid);
                if (check(piles, mid, h)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

        private boolean check(int[] piles, int k, int h) {
            int times = 0;
            for (int i = 0; i < piles.length; i++) {
                times += (piles[i] % k == 0 ? 0 : 1) + piles[i] / k;
                if (times > h) return false;
            }
            return true;
        }
```



## [1413. 逐步求和得到正数的最小值](https://leetcode.cn/problems/minimum-value-to-get-positive-step-by-step-sum/)

### 方法1：贪心

```java
//记录最小的累加和,最后满足 accSumMin + startValue >=1
public int minStartValue(int[] nums) {
    int accSum = 0, accSumMin = 0;
    for (int x : nums) {
        accSum += x;
        accSumMin = Math.min(accSumMin, accSum);
    }
    return -accSumMin + 1;
}
```

### 方法2：二分

```java
        public int minStartValue(int[] nums) {
            int l = 1, r = 10010;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (valid(nums, mid)) r = mid;
                else l = mid + 1;
            }
            return l;
        }


        private boolean valid(int[] nums, int startValue) {
            for (int x : nums) {
                startValue += x;
                if (startValue < 1) return false;
            }
            return true;
        }
```





## [6040. 花园的最大总美丽值](https://leetcode-cn.com/problems/maximum-total-beauty-of-the-gardens/)



```java
    static class _4th_2 {
        public static void main(String[] args) {
            _4th_2 handler = new _4th_2();
            int[] flowers = {1, 3, 1, 1};
            int newFlowers = 7, target = 6, full = 12, partial = 1;
//            Assert.assertEquals(14, handler.maximumBeauty(flowers, newFlowers, target, full, partial));
            flowers = new int[]{10, 9, 16, 14, 6, 5, 11, 12, 17, 2, 11, 15, 1};
            newFlowers = 80;
            target = 14;
            full = 15;
            partial = 1;
            Assert.assertEquals(14, handler.maximumBeauty(flowers, newFlowers, target, full, partial));
        }

        long[] pre;//前缀和


        public long maximumBeauty(int[] f, long newFlowers, int target, int full, int partial) {
            int n = f.length;
            //flowers数组 从大到小排序
            Integer[] flowers = new Integer[n];
            for (int i = 0; i < n; i++) {
                flowers[i] = f[i];
            }
            //新的arr 数组 相当于 f数组
            Integer[] arr = Arrays.copyOf(flowers, n);
            //从大到小排序flowers
            Arrays.sort(flowers, ((o1, o2) -> o2 - o1));
            long res = 0;
            if (flowers[n - 1] >= target) {
                res = (long) n * full;
                return res;
            }
            Arrays.sort(arr);
            pre = new long[n + 1];
            for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + arr[i];
            for (int i = 0; i < n; i++) {
                //flowers按大到小的排序，前i个花园都是完善的状态
                if (flowers[i] >= target) continue;
                //难点在分配那些不完善的花园的数量 也就是mid
                int r = target - 1;
                int l = flowers[n - 1];
                while (l <= r) {
                    //当前应该给多少个花园分配鲜花
                    int mid = l + (r - l) / 2;
                    //如果mid够分，说明mid小了，可以扩大
                    if (judge(arr, newFlowers, mid, n - i)) {
                        l = mid + 1;
                    } else {//如果mid不够份，说明mid大了，要缩小
                        r = mid - 1;
                    }
                }
                //当前有l-1个是不完善的花园，
                res = Math.max(res, (long) i * full + (long) (l - 1) * partial);
                newFlowers -= Math.max(0, target - flowers[i]);
                if (newFlowers < 0) return res;
            }
            /**
             * [10,9,16,14,6,5,11,12,17,2,11,15,1]
             * 80
             * 14
             * 15
             * 1
             */
            //参考上面的case ，当还有花可以分，这时候需要需要 和这种方案比较：
            //flowers 所有的花园全部到定格>=target 没有不完善的花园 即partial的数量为0
            if (newFlowers >= 0) res = Math.max(res, (long) n * full);
            return res;
        }

        /**
         * @param arr        正序数组
         * @param newFlowers 当前剩下的可用花的数量
         * @param mid        给定一个鲜花的数量
         * @param range      查找的范围
         * @return
         */
        private boolean judge(Integer[] arr, long newFlowers, int mid, int range) {
            int p = lower_bound(arr, 0, range, mid);
            long t = (long) p * mid - pre[p];
            return newFlowers >= t;
        }

        //函数 lower_bound() 在 [begin, end) 进行二分查找，返回 大于或等于 target的第一个元素位置。如果所有元素都小于target，则返回 end.
        public static int lower_bound(Integer[] arr, int begin, int end, int target) {
            while (begin < end) {
                int mid = begin + (end - begin) / 2;
                // 当 mid 的元素小于 target 时
                if (arr[mid] < target)
                    // begin 为 mid + 1, arr[begin] 的值会小于或等于 target
                    begin = mid + 1;
                    // 当 mid 的元素大于等于 target 时
                else if (arr[mid] >= target)
                    end = mid;
            }
            return begin;
        }

    }
```





## [luogu]P2440 木材加工

```java
        static class Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt(), k = sc.nextInt();
                int[] L = new int[n];
                int index = 0;
                while (index < n) {
                    L[index++] = sc.nextInt();
                }
                int lo = 0, hi = (int) 1e8;
                while (lo < hi) {
                    int mid = lo + (hi - lo + 1) / 2;
                    if (check(L, k, mid)) lo = mid;
                    else hi = mid - 1;
                }
                System.out.println(lo);
            }

            //给定一个长度为l的木棒,是否能裁剪出超过k段的木棒
            private static boolean check(int[] L, int k, int l) {
                int count = 0;
                for (int x : L) {
                    count += x / l;
                    if (count >= k) return true;
                }
                return count >= k;

            }
        }
```



## [LintCode]437 · 书籍复印

### 方法1：动态规划

```java
        public int copyBooks(int[] pages, int k) {
            int n = pages.length;
            //f[t][i] 前t个人抄前i本书，最少需要的时间
            //转移方程：f[t][i] =min{max{f[k-1][j],pages[j]+...pages[i-1]}}(0<=j<=i)
            int[][] f = new int[k + 1][n + 1];
            int INF = Integer.MAX_VALUE;
            Arrays.fill(f[0], INF);
            f[0][0] = 0;
            //边界条件：
            //1.0个人抄0本书 f[0][0] = 0;
            //2.0个人抄1...n本书，f[0][1]=f[0][2]=...=f[0][n]=INF
            //3.t个人抄0本书 f[t][0] = 0;
            for (int t = 1; t <= k; t++) {
                f[t][0] = 0;
                for (int i = 1; i <= n; i++) {
                    f[t][i] = INF;
                    int sum = 0;
                    for (int j = i; j >= 0; j--) {
                        f[t][i] = Math.min(f[t][i], Math.max(f[t - 1][j], sum));
                        if (j > 0) sum += pages[j - 1];
                    }
                }
            }
            return f[k][n];
        }
```

### 方法2：二分

```java
//437.书籍复印
public int copyBooks(int[] pages, int k) {
    //当人数够的时候，恰好有>=len(pages)的人数，只需要每个人抄写一本书即可，这时候最慢的抄书的人花费的时间恰好是最多的那本书的页数
    //当总数为tot时，这时候如果只有一个人抄书，需要这个人抄完整合pages列表
    int maxx = 0, tot = 0;
    for (int x : pages) {
        maxx = Math.max(maxx, x);
        tot += x;
    }
    int start = maxx, end = tot;
    //退出条件是start+1 =end start比end少一个
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (countWorkers(pages, mid) <= k) {//说明当前的mid可能是需要的值(mid...end]排除
            end = mid;
        } else {
            start = mid + 1;//当前的count>k，说明该count不满足条件，排除[start...mid]这部分
        }
    }
    //判断下start
    if (countWorkers(pages, start) <= k) return start;
    //start不是目标值的话，就返回end
    return end;
}

//给定每个人的工作时长t，每个人都不能超过这个时长，返回需要多少个人能完成所有的工作
public int countWorkers(int[] pages, int t) {
    int sum = 0;
    int count = 0;
    for (int x : pages) {
        if (x + sum > t) {//如果当前的sum+x比t大，证明这个阶段的人抄书已经到顶了，重新开始并计数
            sum = x;
            count++;
        } else {
            sum += x;
        }
    }
    count++;
    return count;
}
```



## Reference

- [二分查找重难点总结（边界条件、循环条件、mid计算方法等）](https://blog.csdn.net/weixin_45727931/article/details/115276255)

- [详解二分查找算法](https://www.cnblogs.com/kyoner/p/11080078.html)
