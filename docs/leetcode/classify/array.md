# 数组

>



## [41. 缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/)

### 方法1:交换

```java
public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        //前两个条件是为了判断nums[i]是否在0-n范围内，防止index越界
        //后一个是判断i 这个index（当前的值）和nums[i]-1这个index（标准位置）是否相等，
        //标准位置:某个数nums[i] 落整个array的i-1的位置上
        while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
            swap(nums, i, nums[i] - 1);
        }
    }
    //判断第一个出现异常的数字
    for (int i = 0; i < n; i++) {
        if (nums[i] - 1 != i) {
            return i + 1;
        }
    }
    return n + 1;
    //[3, 4, 4, -1, 1]->[1,4,3,4,-1] 异常的时index为1的数nums[1] 其值约为 （1+1=2）但是实际是4
    //[3, 4, -1, 1]  -->[1,-1,3,4]
    //[3,4,-1,-2,1,5,16,0,2,0]-->[1,2,3,4,5,-1,16,0,-2,0]
}

private void swap(int[] nums, int m, int n) {
    int temp = nums[m];
    nums[m] = nums[n];
    nums[n] = temp;
}
```



### 方法2:BitSet

```java
public int firstMissingPositive(int[] nums) {
    BitSet bitSet = new BitSet();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        if (nums[i] >= 1 && nums[i] <= n + 1) {
            bitSet.set(nums[i]);
        }
    }
    for (int i = 1; i <= n; i++) {
        if (!bitSet.get(i)) return i;
    }
    return n + 1;
}
```







## [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

- 翻转方向和标记已经访问的点，是本题的关键

```java
int R, C;
//右 下 左 上
int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

public List<Integer> spiralOrder(int[][] matrix) {
    R = matrix.length;
    C = matrix[0].length;
    int r = 0, c = 0, d = 0;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < R * C; i++) {
        res.add(matrix[r][c]);
        matrix[r][c] = -101;//标记已经访问的点
        int nr = r + dirs[d][0], nc = c + dirs[d][1];
        //出边界或者遇到已经访问过点，翻转方向
        if (!inArea(nr, nc) || matrix[nr][nc] == -101) {
            d = (d + 1) % 4;//翻转方向，灵魂
            nr = r + dirs[d][0];
            nc = c + dirs[d][1];
        }
        r = nr;
        c = nc;

    }
    return res;
}
```



## [56. 合并区间](https://leetcode-cn.com/problems/merge-intervals/)

```java
         public int[][] merge(int[][] arr) {
            List<int[]> res = new ArrayList<>();
            if (arr == null || arr.length == 0) return res.toArray(new int[0][]);
            Arrays.sort(arr, (a, b) -> a[0] - b[0]);
            int n = arr.length, i = 0;
            while (i < n) {
                int start = arr[i][0], end = arr[i][1];
                while (i < n - 1 && end >= arr[i + 1][0]) {
                    end = Math.max(end, arr[i + 1][1]);
                    i++;
                }
                res.add(new int[]{start, end});
                i++;
            }
            return res.toArray(new int[0][]);
        }
```









## [215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

### 方法1:优先队列

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

### 方法2:快排

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

### 方法3:堆排序

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







## [217. 存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/)

### 方法1:Set

```java
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x : nums){
            if(set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }
```

### 方法2:lambda

```java
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    return Arrays.stream(nums).anyMatch(num -> !seen.add(num));
}
```

### 方法3:排序

```java
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    return Arrays.stream(nums).anyMatch(num -> !seen.add(num));
}
```



## [442. 数组中重复的数据](https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/)

### 方法1:标记

#### 思路

我们也可以给`nums[i]` 加上「负号」表示数` i+1` 已经出现过一次。具体地，我们首先对数组进行一次遍历。当遍历到位置 `i` 时，我们考虑 `nums[nums[i]−1]` 的正负性：

- 如果 `nums[nums[i]−1]` 是正数，说明`nums[i] `还没有出现过，我们将 `nums[nums[i]−1]`加上负号；
- 如果 `nums[nums[i]−1]`是负数，说明 `nums[i] `已经出现过一次，我们将  放入答案。

#### 细节

- 由于`nums[i] ` 本身可能已经为负数，因此在将`nums[i] `作为下标或者放入答案时，需要取绝对值。

```java
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int x = Math.abs(nums[i]);
                if (nums[x - 1] > 0) {
                    nums[x - 1] = -nums[x - 1];
                } else {
                    res.add(x);
                }
            }
            return res;
        }
```

### 方法2:交换

```java
        public List<Integer> findDuplicates(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != nums[nums[i] - 1]) {
                    int t = nums[i];
                    nums[i] = nums[nums[i] - 1];
                    nums[t - 1] = t;
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) res.add(nums[i]);
            }
            return res;
        }
```





## [448. 找到所有数组中消失的数字](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/)

- 将442题的方法2修改下

```java
        public List<Integer> findDisappearedNumbers(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != nums[nums[i] - 1]) {
                    int t = nums[i];
                    nums[i] = nums[nums[i] - 1];
                    nums[t - 1] = t;
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) res.add(i + 1);//缺失的数是i+1
            }
            return res;
        }
```



## [498. 对角线遍历](https://leetcode.cn/problems/diagonal-traverse/)

- [链接](https://leetcode-cn.com/problems/diagonal-traverse/solution/xiao-bai-kan-guo-lai-zui-zhi-bai-yi-li-jie-ban-ben/)

```java
public int[] findDiagonalOrder(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[]{};
    int R = matrix.length, C = matrix[0].length;
    int[] ans = new int[R * C];
    int r = 0, c = 0, idx = 0;
    for (int i = 0; i < R + C - 1; i++) {
        if (i % 2 == 0) {
            while (r >= 0 && c < C) {
                ans[idx++] = matrix[r][c];
                r--;
                c++;
            }
            if (c < C) {
                r++;
            } else {
                r += 2;
                c--;
            }

        } else {
            while (r < R && c >= 0) {
                ans[idx++] = matrix[r][c];
                r++;
                c--;
            }
            if (r < R) {
                c++;
            } else {
                c += 2;
                r--;
            }
        }
    }
    return ans;
}
```









## [599. 两个列表的最小索引总和](https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/)

```java
public String[] findRestaurant(String[] list1, String[] list2) {
    //map k:list1的string v:string的索引，因为题目中没有指出来list1的string是否有重复，忽略了重复的情况
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
    //结果集list
    List<String> list = new ArrayList<>();
    //索引和的最小值，根据数据范围取
    int minn = 2005;
    //遍历list2
    for (int i = 0; i < list2.length; i++) {
        //list2中有这个string
        if (map.containsKey(list2[i])) {
            int j = map.get(list2[i]);
            //case1:比minn还要小，重新更新
            if (i + j < minn) {
                minn = i + j;
                list.clear();
                list.add(list2[i]);
            } else if (i + j == minn) {//case2:和minn相等，添加
                list.add(list2[i]);
            }
        }
    }
    //收集结果
    String[] res = new String[list.size()];
    for (int i = 0; i < res.length; i++) res[i] = list.get(i);
    return res;
}
```



## [954. 二倍数对数组](https://leetcode-cn.com/problems/array-of-doubled-pairs/)

