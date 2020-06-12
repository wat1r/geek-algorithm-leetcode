## 排列组合之第K个排列[Golden Monkey]

### 1.下一个排列

![1591798391535](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之第K个排列[].assets\1591798391535.png)

1.从后向前，第一个最大的索引$i$,满足$nums[i]<nums[i+1]$,如果不能找到这样的索引，直接翻转数组

2.找出比当前索引$i$大稍大的索引$j$,满足$nums[k]>nums[i]$

3.$swap(i,j)$

4.翻转$nums[k+1::]$

举例：$123654$

- 首先找到第一个最大的索引$i$,$nums[i]<nums[i+1]$,其中$i=2$，$nums[2]<nums[3]$等价于$3<6$,
- 找到比$i$稍大的索引，$j=5$即$nums[j]=nums[5]=4$, 此时的$4>3$,但是$4<6$

- $swap(i,j)$ 得到$124653$
- 翻转后部分$653$，得到$124356$

#### 方法1：推导

```java
 public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        //最左边第一个最大的索引
        int leftIndex = -1;
        int n = nums.length, i = n - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) {
                leftIndex = i;
                break;
            }
            i--;
        }
        //如果没找到，翻转整个nums
        if (leftIndex == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        //右边稍大的索引
        int rightIndex = -1;
        int j = n - 1;
        while (j >= 0) {
            if (nums[j] > nums[leftIndex]) {
                rightIndex = j;
                break;
            }
            j--;
        }
        //交换，翻转
        swap(nums, leftIndex, rightIndex);
        reverse(nums, leftIndex + 1, n - 1);

    }

    //翻转i...j之间的数
    private void reverse(int[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }
    //交换索引为ij的两个数
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
```



### 2.第K个排列

![image-20200612092636196](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之第K个排列[Golden Monkey].assets\image-20200612092636196.png)

- 举例$n=4,k=9$的场景，下面的代码用到阶乘加速剪枝的过程
  - 当首先选$1$时，剩下的可选的数有$234$ 这三个，阶乘$f(4-1)=f(3)=3*2*1=6$，也就是说$1$后面与$234$组合最多有$6$种组合，但是题目要求的是$k=9$，这一层选完了也不可能到到$k$，需要跳过，并将$k-6=9-6=3$，下一层需要找到第$3$个排列
  - 当再次选$2$时，这时候$2$与后面的$134$也有$6$种组合，但是这次要选的是上面留下来的$3$，需要再进一层，选$1$，也就是前面是$21$，留下了$34$，阶乘$f(4-2)=f(2)=2*1=2$, 这里$-2$是因为用掉了两层$21$这两个，也就是两层，现在很明显了，$21$与剩下的$34$组合只有两种，$2134$和$2143$，而$2$又小于上一步留下来的$k=3$，需要跳过，$k-2=3-2=1$，下一层需要找到第$1$个排列
  - 退回上一层选$3$,也就是当前是$23$，剩下了$14$，这时候有$f(4-2)=f(2)=2*1=2$种选择，目标出现了，因为$2>k=1$,选$1$得到$231$,这时$f(4-3)=f(1)=1$, 也就是说$2314$即所求，到底此时，因为长度已经到达$n$，开始收集结果

![第K个排列](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\permutation\排列组合之第K个排列[Golden Monkey].assets\第K个排列.png)

- 其中的$depth$可以理解成层级，当到达$n$层，即达到出口条件，每一层$dfs$需要在当前的$depth+1$,进入下一层

```java
public String getPermutation(int n, int k) {
        int[] nums = new int[n];//生成nums数组
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        boolean[] used = new boolean[n];//记录当前的索引的数是否被使用过
        return dfs(nums, new ArrayList<String>(), used, 0, n, k);
    }

    /**
     * @param nums      源数组
     * @param levelList 每一层的选择
     * @param used      数组的元素是否被使用过
     * @param depth     深度，也就是当前使用的元素的索引
     * @param n         上限值
     * @param k         第k个
     * @return 第k个排列
     */
    private String dfs(int[] nums, List<String> levelList, boolean[] used, int depth, int n, int k) {
        if (depth == n) {//触发出口条件，开始收集结果集
            StringBuilder res = new StringBuilder();
            for (String s : levelList) res.append(s);
            return res.toString();
        }
        int cur = factorial(n - depth - 1);//当前的depth也就是索引，有多少排列数
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;//当前元素被使用过，做剪枝
            if (cur < k) {//当前的排列组合数小于k，说明就算这一层排完了，也到不了第k个，剪枝
                k -= cur;
                continue;
            }
            levelList.add(nums[i] + "");//list收的是string类型
            used[i] = true;//当前元素被使用过标记
            return dfs(nums, levelList, used, depth + 1, n, k);
        }
        return null;
    }


    //返回n的阶乘，如3!=3*2*1=6
    private int factorial(int n) {
        int res = 1;
        while (n > 0) {
            res *= n--;
        }
        return res;
    }
```

### 推荐阅读







