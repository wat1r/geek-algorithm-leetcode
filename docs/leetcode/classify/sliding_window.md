

> 

## [239. 滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/)

### 方法1：双端队列

![](/imgs/leetcode/classify/image-20210831173335524.png)



```java
public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) return new int[]{};
    int n = nums.length;
    Deque<Integer> deque = new LinkedList<>();
    int[] ans = new int[n - k + 1];
    int index = 0;
    for (int i = 0; i < n; i++) {
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }
        deque.addLast(i);
        if ((i - k) == deque.peekFirst()) {
            deque.pollFirst();
        }
        if (i >= k - 1) {
            ans[index++] = nums[deque.peekFirst()];
        }
    }
    return ans;
}
```

### 方法2：双端队列

```java
public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[]{};
            int n = nums.length;
            int[] res = new int[n - k + 1];
            int idx = 0;
            Deque<Integer> q = new LinkedList<>();//存的是元素的下标索引
            for (int i = 0; i < n; i++) {
                //队列中有元素，但是元素的下标已经过期，即不在k的滑窗范围内，开始从进队的First位置移除过期的索引
                while (!q.isEmpty() && q.peek() < i - k + 1) {
                    q.poll();//pollFirst()
                }
                //队列中有元素，但是队列的last位置的索引的值，小于当前的nums[i]的值，last位置的索引是无意义的，可以提前弹出
                while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                    q.pollLast();
                }
                //加入当前索引
                q.offer(i);
                //一定要满足滑窗的条件的时候，开始收集
                if (i >= k - 1) {
                    res[idx++] = nums[q.peek()];//peekFirst()
                }
            }
            return res;
        }
```

### 方法3：辅助数组+贪心

> 国际站看的的一个思路，很赞

![](/imgs/leetcode/classify/image-20210901125713733.png)

如上图:

1.将源数组按k的大小分，最后一组不够k的话，维持现状

```java
2 1 3 4 | 6 3 8 9 | 10 12 56
```

2.1.遍历数组，组装left_max，即从左开始，每个k组从左开始，取最大值

```java
2 2 3 4 | 6 6 8 9 | 10 12 56
```

2.2.遍历数组，组装right_max，即从右开始，每个k组从右开始，取最大值

```java
2 | 6 6 6 6 | 10 10 10 10 | 56 56 
```

3.借助左右辅助数组拼装结果数组，Math.max(right_max[i], left_max[i + k - 1])

```java
4, 6, 6, 8, 9, 10, 12, 56
```

#### 实现

```java
 public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[]{};
            int n = nums.length;
            int[] left_max = new int[n];
            int[] right_max = new int[n];
            left_max[0] = nums[0];
            right_max[n - 1] = nums[n - 1];
            for (int i = 1; i < n; i++) {
                left_max[i] = (i % k == 0) ? nums[i] : Math.max(nums[i], left_max[i - 1]);
                int j = n - i - 1;
                right_max[j] = (j % k == 0) ? nums[j] : Math.max(nums[j], right_max[j + 1]);
            }
            int[] res = new int[n - k + 1];
            for (int i = 0, j = 0; i + k <= n; i++) {
                res[j++] = Math.max(right_max[i], left_max[i + k - 1]);
            }
            return res;
        }

```

#### 上述贪心的证明：

> 思路来源国际站，很赞的一个证明

假设$a_0$,$a_1$,$a_2$ ... $a_n$的窗口宽度是$w$,目标是为了获取一个$d[]=int[n-w+1]$

将上面的数组从左边开始分，每个元素的形式:$i*w+j$,其中$i$是从左边开始数，窗口的$index$，$j$是在这个窗口下的偏移量，其中$0<=i<=n/w$,   $0<=j<=w$

构建如下的结果：

$d[i*w+j]=max(a[i*w+j+x])$这里的$x$满足：$0<=x<=w$,因此，$i*w+j$实际上代表的是要计算的最大值

假设有如下的数组：

$left[i*w+j]=left\_max(a[i*w+k])$​  其中$0<=k<j$​

$right[i*w+j]=right\_max(a[i*w+k])$ 其中$j<=k<=(i+1)*w-1$

数组$left[]$是从左到右的每个窗口最大值

数组$right[]$是从右到左的每个窗口的最大值

![](/imgs/leetcode/classify/image-20210901135208190.png)

有如下的推导：
$d[i*w+j]=max(right[i*w+j], left[(i+1)*w+j-1])$
$d[i*w+j]=max(right[i*w+j], left[(i*w+w+j-1])$
=>
$d[m] = max(right[m], left[m+w-1])$

结果数组$d[]$​的最后一个元素是:$d[n-w]=max(right[n-w], left[n-1])$​



## [2024. 考试的最大困扰度](https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/)

### 方法1:滑动窗口

![](/imgs/leetcode/classify/image-20220329083101268.png)

![](/imgs/leetcode/classify/image-20220329090554204.png)



```java
public int maxConsecutiveAnswers(String str, int k) {
    return Math.max(getMaxLength(str, 'T', k), getMaxLength(str, 'F', k));
}

//字符s中不至多包含k个c的最大长度
private int getMaxLength(String s, char c, int k) {
    int n = s.length();
    int cnt = 0;
    int res = 0;
    //左右窗口，让r自增往右推
    for (int l = 0, r = 0; r < n; r++) {
        //如果[r]=c cnt需要统计+1
        if (s.charAt(r) == c) cnt++;
        //当cnt > k 说明[l,r]当前的窗口内有超过了k个字符c，缩减左窗口
        while (cnt > k) {
            if (s.charAt(l++) == c) cnt--;
        }
        //统计最大长度
        res = Math.max(res, r - l + 1);
    }
    return res;
}
```

另外一种思路：

```java
public int maxConsecutiveAnswers(String str, int k) {
    return Math.max(getMaxLength(str, 'T', k), getMaxLength(str, 'F', k));
}

//字符s中可以至多包含k个c的最大长度
private int getMaxLength(String s, char c, int k) {
    //左右窗口
    int n = s.length(), l = 0, r = 0;
    int res = 0;
    while (r < n) {
        //[r]如果不是c的话，消耗掉一次k的值 k--
        if (s.charAt(r) != c) k--;
        while (k < 0) {
            if (s.charAt(l) != c) k++;
            l++;
        }
        r++;
        //注意已经想又移动过一次 
        res = Math.max(res, r - l);
    }
    return res;
}
```

### 方法2:滑动窗口+统计

思路来自`@lee215`大佬，该解法可以参考424题

```java
      public int maxConsecutiveAnswers(String s, int k) {
            //maxf表示滑窗中，相同字符最大的出现次数，本题只是'T' 和 'F'
            int maxf = 0, l = 0, n = s.length();
            //count数组 可以缩减到[2]个长度
            int[] count = new int[26];
            for (int r = 0; r < n; ++r) {
                maxf = Math.max(maxf, ++count[s.charAt(r) - 'A']);
                //如果当前的滑窗的大小 大于 maxf('F'或者'T')出现的次数+k次替换操作，开始缩减l窗口，移除次数
                if (r - l + 1 > maxf + k) {
                    --count[s.charAt(l++) - 'A'];
                }
            }
            return n - l;
        }
```





