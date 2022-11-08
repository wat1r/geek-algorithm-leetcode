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



## [347. 前 K 个高频元素](https://leetcode.cn/problems/top-k-frequent-elements/)

### 方法1：HashMap+桶排

```java
public int[] topKFrequent(int[] nums, int k) {
    //k:nums的每一个数，v:nums中每一个数出现的次数
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int x : nums) {
        freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
    }
    //bucket[freq]出现的次数，哪些数出现了freq次
    List<Integer>[] bucket = new List[nums.length + 1];
    for (int x : freqMap.keySet()) {
        int freq = freqMap.get(x);
        if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
        bucket[freq].add(x);
    }
    List<Integer> res = new ArrayList<>();
    //从高到低freq开始收集res
    for (int i = bucket.length - 1; i >= 0; --i) {
        if (bucket[i] != null) {
            for (int j = 0; j < bucket[i].size() && res.size() < k; j++) {
                res.add(bucket[i].get(j));
            }
        }
    }
    int[] ans = new int[res.size()];
    for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
    return ans;
}
```

### 方法2：HashMap+大根堆

```java
 public int[] topKFrequent(int[] nums, int k) {
            //k:nums的每一个数，v:nums中每一个数出现的次数
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int x : nums) {
                freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
            }
            //做一个大根堆
            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
            for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
                pq.offer(e);
            }
            List<Integer> res = new ArrayList<>();
            while (res.size() < k) {
                res.add(pq.poll().getKey());
            }
            int[] ans = new int[res.size()];
            for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
            return ans;
        }
```

### 方法3：HashMap+TreeMap

```java
        public int[] topKFrequent(int[] nums, int k) {
            //k:nums的每一个数，v:nums中每一个数出现的次数
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int x : nums) {
                freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
            }
            TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
            for (int x : freqMap.keySet()) {
                int freq = freqMap.get(x);
                treeMap.putIfAbsent(freq, new ArrayList<>());
                treeMap.get(freq).add(x);
            }
            List<Integer> res = new ArrayList<>();
            while (res.size() < k) {
                Map.Entry<Integer, List<Integer>> e = treeMap.pollLastEntry();
                res.addAll(e.getValue());
            }
            int[] ans = new int[res.size()];
            for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
            return ans;
        }
```

### 方法4：快速排序

```java
public int[] topKFrequent(int[] nums, int k) {
    //k: 元素num v:出现的频次
    Map<Integer, Integer> map = new HashMap<>();
    for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
    List<int[]> freqList = new ArrayList<>();
    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
        int num = e.getKey(), freq = e.getValue();
        freqList.add(new int[]{num, freq});
    }
    int[] res = new int[k];
    quickSort(freqList, 0, freqList.size() - 1, res, 0, k);
    return res;

}

/**
 * @param freqList 频次的list [0]为num [1]为freq
 * @param start    list的开始位置
 * @param end      list的结束位置
 * @param res      结果数组
 * @param resIndex 结果数组的当前待添加的下标索引
 * @param k        k
 */
private void quickSort(List<int[]> freqList, int start, int end,
                       int[] res, int resIndex, int k) {
    int pivotIndex = (int) (Math.random() * (end - start + 1)) + start;//随机选择哨兵
    Collections.swap(freqList, start, pivotIndex);//交换哨兵与start的位置
    int pivotFreq = freqList.get(start)[1];//当前的频次
    int index = start;
    for (int i = start + 1; i <= end; i++) {
        if (freqList.get(i)[1] >= pivotFreq) {//将频次高的放在左侧，频次低的放在右侧
            Collections.swap(freqList, index + 1, i);
            index++;
        }
    }
    Collections.swap(freqList, start, index);//将哨兵的位置放置在正确的位置
    if (index - start >= k) {//[start...index]段的元素比k多，需要在[start...index]段继续缩小范围
        quickSort(freqList, start, index - 1, res, resIndex, k);
    } else {
        for (int i = start; i <= index; i++) {//左侧部分即[start...index]都是需要的，开始收集
            res[resIndex++] = freqList.get(i)[0];
        }
        if (index - start + 1 < k) { // 当pivot和起点间的个数小于k时，则从pivot到end再继续找剩下的前（k - （pivot - start + 1））大的元素
            quickSort(freqList, index + 1, end, res, resIndex, k - (index - start + 1));
        }
    }

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

## [1051. 高度检查器](https://leetcode.cn/problems/height-checker/)

### 方法1：辅助数组+排序

```java
public int heightChecker(int[] heights) {
    int n = heights.length;
    int[] arr = new int[n];
    System.arraycopy(heights, 0, arr, 0, n);
    Arrays.sort(arr);
    int res = 0;
    for (int i = 0; i < n; i++) {
        if (heights[i] != arr[i]) res++;
    }
    return res;
}
```

### 方法2：桶排序

```java
public int heightChecker(int[] heights) {
    int[] buckets = new int[110];
    for (int h : heights) buckets[h]++;
    int res = 0;
    //i为buckets的自增下标，j为heights的自增下标
    for (int i = 1, j = 0; i < buckets.length; i++) {
        //桶里还有元素的时候一直循环
        while (buckets[i]-- > 0) {
            //如果出现当前的元素和桶里的元素（i）不一致，说明该元素heights[j]位置错了，需要统计
            if (heights[j++] != i) res++;
        }
    }
    return res;
}
```

## [1460. 通过翻转子数组使两个数组相等](https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays/)

```java
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < target.length; i++) {
            if (i > arr.length) return false;
            if (arr[i] != target[i]) return false;
        }
        return true;
    }
```









### Reference

- [动画阐释各种排序算法](https://www.bilibili.com/video/BV13Y4y1H7j5)
