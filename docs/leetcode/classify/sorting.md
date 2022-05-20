# 排序

> 





## [56. 合并区间](https://leetcode.cn/problems/merge-intervals/)

```java
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int i = 0, n = intervals.length;
        while(i< n ){
            int l = intervals[i][0] ,r = intervals[i][1];
            while(i < n-1 && r >=intervals[i+1][0]){
                r = Math.max(r,intervals[i+1][1]);
                i++;
            }
            list.add(new int[]{l,r});
            i++;
        }
        return list.toArray(new int[0][]);
    }
```









## [453. 最小操作次数使数组元素相等](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/)

### 方法1：找最小值

```java
public int minMoves(int[] nums) {
    int minn = (int) 1e9 + 10;
    for (int x : nums) minn = Math.min(minn, x);
    int res = 0;
    for (int x : nums) res += x - minn;
    return res;
}
```







## [462. 最少移动次数使数组元素相等 II](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/)

### 方法1：排序+找中位数

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220519080213.png)

```java
        //排序后求中位数
        public int minMoves2(int[] nums) {
            Arrays.sort(nums);
            int res = 0, t = nums[nums.length / 2];
            for (int i = 0; i < nums.length; i++) {
                res += Math.abs(nums[i] - t);
            }
            return res;
        }
```

### 方法2：排序+双指针

- 设x在区间[a,b]范围内，将a变成x需要x-a步，将b变成x需要b-x步，总的步数是x-a+(b-x) = b-a即区间的差，x其实可以在这个区间内任意取

```java
public int minMoves2(int[] nums) {
    Arrays.sort(nums);
    int res = 0, t = nums[nums.length / 2];
    for (int i = 0; i < nums.length; i++) {
        res += Math.abs(nums[i] - t);
    }
    return res;
}
```

### 方法3：快排+找中位数

```java
Random random = new Random();

public int minMoves2(int[] nums) {
    int n = nums.length;
    int t = quickSelect(nums, 0, n - 1, n / 2);
    int res = 0;
    for (int i = 0; i < n; ++i) {
        res += Math.abs(nums[i] - t);
    }
    return res;
}

//在[left,right]区间上找index下标的数
public int quickSelect(int[] nums, int l, int r, int index) {
    int p = randomPartition(nums, l, r);
    if (p == index) {
        return nums[p];
    } else {
        //p是分割点的下标，比index小，说明在右半部分找[p+1,right]
        //否则在左半部分找[left, p-1]
        return p < index ?
                quickSelect(nums, p + 1, r, index)
                : quickSelect(nums, l, p - 1, index);
    }
}

public int randomPartition(int[] nums, int l, int r) {
    int pivotIndex = random.nextInt(r - l + 1) + l;
    swap(nums, pivotIndex, r);
    int pivot = nums[r], i = l - 1;
    for (int j = l; j < r; ++j) {
        if (nums[j] <= pivot) {
            ++i;
            swap(nums, i, j);
        }
    }
    swap(nums, i + 1, r);
    return i + 1;
}

public void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}
```



## [912. 排序数组](https://leetcode-cn.com/problems/sort-an-array/)

### 方法1:快排

```java
  public int[] sortArray(int[] nums) {
            return quickSort(nums, 0, nums.length - 1);

        }


        private int[] quickSort(int[] nums, int left, int right) {
            if (left < right) {
                int pId = parition(nums, left, right);
                quickSort(nums, left, pId - 1);
                quickSort(nums, pId + 1, right);
            }
            return nums;
        }

        private int parition(int[] nums, int left, int right) {
            int pivot = left;
            int index = pivot + 1;
            for (int i = index; i <= right; i++) {
                if (nums[i] < nums[pivot]) {
                    swap(nums, i, index);
                    index++;
                }
            }
            swap(nums, pivot, index - 1);
            return index - 1;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
```





## [937. 重新排列日志文件](https://leetcode-cn.com/problems/reorder-data-in-log-files/)

```java
        public String[] reorderLogFiles(String[] logs) {
            Arrays.sort(logs, (a, b) -> {
                //limit表示分割的份数 dig1 8 1 5 1 -> "dig1"与"8 1 5 1"
                //let1 art can -> "let1"与"art can"
                String[] splitA = a.split(" ", 2);
                String[] splitB = b.split(" ", 2);
                //判断日志的类型
                boolean aIsDigit = Character.isDigit(splitA[1].charAt(0));
                boolean bIsDigit = Character.isDigit(splitB[1].charAt(0));
                //a和b都是数字日志，保留原来的相对顺序 return 0;
                if (aIsDigit && bIsDigit) return 0;
                    //a是字母日志，b是数字日志 字母日志在数字日志之前 return -1;
                else if (!aIsDigit && bIsDigit) return -1;
                    //a是数字日志，b是字母日志 字母日志在数字日志之前 return 1;
                else if (aIsDigit & !bIsDigit) return 1;
                    //a和b都是字母日志 内容不同，按内容排序
                    //内容相同，按标识符来排序
                else if (!aIsDigit && !bIsDigit) {
                    return !splitA[1].equals(splitB[1]) ? splitA[1].compareTo(splitB[1])
                            : splitA[0].compareTo(splitB[0]);
                }
                return 0;

            });
            return logs;

        }
```







### Reference

- [动画阐释各种排序算法](https://www.bilibili.com/video/BV13Y4y1H7j5)
