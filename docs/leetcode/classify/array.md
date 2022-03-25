# 数组

>



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