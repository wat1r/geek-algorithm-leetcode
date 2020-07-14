349/350

### 1.两个数组的交集I



















### 2.两个数组的交集II

![image-20200714091701384](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\nums\多解法解两个数组的交集[Persian Leopard].assets\image-20200714091701384.png)

#### 方法1：Hash

```java
public int[] intersect(int[] nums1, int[] nums2) {
    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums1) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (int num : nums2) {
        if (map.containsKey(num) && map.get(num) > 0) {
            list.add(num);
            map.put(num, map.get(num) - 1);
        }
    }
    int[] res = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
        res[i] = list.get(i);
    }
    return res;
}
```



#### 方法2：Sort

- 一个小函数：

```
Arrays.copyOfRange(T[ ] original,int from,int to)
将一个原始的数组original，从下标from开始复制，复制到上标to，生成一个新的数组。
注意这里包括下标from，不包括上标to。
```

- 对$nums1$ , $nums2$排序，准备两个指针 $i$，游走$nums1$，$j$游走$nums2$，在准备一个索引$index$，存放相同的元素
- 新建一个$tmp$数组，其长度是两个源数组的最小值，用于存放相同的元素，用$index$来设置索引
- 比较两个指针$i$与$j$的值:
  - $[i]$>$[j]$, $j$++
  - $[i]$<$[j]$, $i$++
  - $[i]$=$[j]$, $i$++ , $j$++ ,同时收集目标的数
- 返回`Arrays.copyOfRange(tmp,0,index)`

```java
public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0, m = nums1.length, n = nums2.length;
    int index = 0;
    int[] tmp = new int[Math.min(m,n)];
    while (i < m && j < n) {
        if (nums1[i] < nums2[j]) i++;
        else if (nums1[i] > nums2[j]) j++;
        else if (nums1[i] == nums2[j]) {
            tmp[index++] = nums1[i];
            i++;
            j++;
        }
    }
    return Arrays.copyOfRange(tmp,0,index);
}
```





