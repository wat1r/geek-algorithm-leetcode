

> 



## [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

> 这一题是leetcode的NO.3题，曾经被面到过，很经典的一道题

- **注意：子串与子序列的区别**
  - 子串：不可跳跃，如 `pwwkew`中`wke`是子串
  - 子序列：可以跳跃，如`pwwkew`中`pkw`是子序列

#### 方法1:粗糙版滑动窗口

- 用`Set`来维护重复字符与否的问题
  - 出现新的字符，很好，`right`指针向右扩展，并将当前字符加入`Set`中，更新最长无重复子串长度
  - 出现旧的字符，将左边界`left`指针向右收缩，移除当前的`left`指针指向的字符

```java
    public int lengthOfLongestSubstring1st(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();
        while (right < n && left < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                res = Math.max(res, right - left);
            } else {
                set.remove(s.charAt(left++));
            }
        }
        return res;
    }
```

#### 方法2:优化版滑动窗口

- 用一个`hashmap`来建立字符和其出现位置之间的映射。
- 维护一个滑动窗口，窗口内的都是没有重复的字符，去尽可能的扩大窗口的大小，窗口不停的向右滑动。
- （1）如果当前遍历到的字符从未出现过，那么直接扩大右边界；
- （2）如果当前遍历到的字符出现过，则缩小窗口（左边索引向右移动），然后继续观察当前遍历到的字符；
- （3）重复（1）（2），直到左边索引无法再移动；
- （4）维护一个结果`res`，每次用出现过的窗口大小来更新结果`res`，最后返回res获取结果。
  - 用一个`mapper`记录出现过并且没有被删除的字符
  - 用一个滑动窗口记录当前`index`开始的最大的不重复的字符序列
  - 用`res`去记录目前位置最大的长度，每次滑动窗口更新就去决定是否需要更新`res`

```java
  public int lengthOfLongestSubstring2nd(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        while (right < n) {
             //使用hashmap作为存储，每次left的值取最大的
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)));
            }
            //计算下标的距离，记录下标的绝对物理index
            res = Math.max(res, right - left + 1);
            map.put(s.charAt(right), 1 + right++);
        }
        return res;
    }
```

#### 方法3:再优化版滑动窗口

- 常用的表如下所示：

```python
int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
int [128] 用于ASCII码
int [256] 用于扩展ASCII码
```

- 准备一个`helper`数组，每次记录`right`指针的绝对位置，
- 更新`res`,更新`left`(拿到其最大位置)

```java
    public int lengthOfLongestSubstring3rd(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        int[] helper = new int[128];
        while (right < n) {
            left = Math.max(left, helper[s.charAt(right)]);
            res = Math.max(res, right - left + 1);
            helper[s.charAt(right)] = 1 + right++;
        }
        return res;
    }
```













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





## 159.最多有两个不同字符的最长子串

### 方法1：Map统计数量+滑动窗口

```java
public int lengthOfLongestSubstringTwoDistinct(String s) {
    return helper(s, 2);
}

public int helper(String s, int k) {
    int res = 0;
    Map<Character, Integer> map = new HashMap<>();//k:字符，v:字符出现的次数
    int l = 0;//左边窗口的位置
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        map.put(c, map.getOrDefault(c, 0) + 1);
        while (map.size() > k) {//字符的个数已经超过k个了，开始缩小左边窗口
            char lc = s.charAt(l++);//left char
            int t = map.get(lc) - 1;//left char的数量
            if (t == 0) {//数量为0的时候，key被移除
                map.remove(lc);
            } else {
                map.put(lc, t);//-1后数量再次更新进去
            }
        }
        res = Math.max(res, i - l + 1);//计算长度
    }
    return res;
}
```

### 方法2：Map标记位置+滑动窗口

```java
public int lengthOfLongestSubstringTwoDistinct(String s) {
    return helper(s, 2);
}

public int helper(String s, int k) {
    int res = 0;
    Map<Character, Integer> map = new HashMap<>();//k:字符，v:该字符最近一次出现的位置
    int l = 0;//左窗口
    for (int i = 0; i < s.length(); i++) {
        map.put(s.charAt(i), i);//将当前字符和字符的位置关系记录下来
        while (map.size() > k) {//总的字符开始超过k个
            //如果 s[l]的 位置和l 不同，说明在[l+1 ... i ]之间又出现了字符s[l]，这是不能移除s[l],反之则移除s[l]
            if (map.get(s.charAt(l)) == l) map.remove(s.charAt(l));
            ++l;
        }
        res = Math.max(res, i - l + 1);
    }
    return res;
}
```

### 方法3：滑动窗口+指针

```java
        //1. 若当前字符和前一个字符相同，继续循环。
        //2. 若不同，看当前字符和 right 指的字符是否相同
        //    (1) 若相同，left 不变，右边跳到 i - 1
        //    (2) 若不同，更新结果，left 变为 right+1，right 变为 i - 1
        //最后需要注意在循环结束后，还要比较结果 res 和 s.size() - left 的大小，返回大的，这是由于如果字符串是 "ecebaaa"，
        // 那么当 left=3 时，i=5,6 的时候，都是继续循环，当i加到7时，跳出了循环，而此时正确答案应为 "baaa" 这4个字符，而我们的结果 res 只更新到了 "ece" 这3个字符，所以最后要判断 s.size() - left 和结果 res 的大小。
        int lengthOfLongestSubstringTwoDistinct(String s) {
            int left = 0, right = -1, res = 0;
            char[] ch = s.toCharArray();
            for (int i = 1; i < s.length(); ++i) {
                if (ch[i] == ch[i - 1]) continue;
                if (right >= 0 && ch[right] != ch[i]) {
                    res = Math.max(res, i - left);
                    left = right + 1;
                }
                right = i - 1;
            }
            return Math.max(s.length() - left, res);
        }
```





## 340.最多有K个不同字符的最长子串

### 方法1：Map统计数量+滑动窗口

```java
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int res = 0;
            Map<Character, Integer> map = new HashMap<>();//k:字符，v:字符出现的次数
            int l = 0;//左边窗口的位置
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
                while (map.size() > k) {//字符的个数已经超过k个了，开始缩小左边窗口
                    char lc = s.charAt(l++);//left char
                    int t = map.get(lc) - 1;//left char的数量
                    if (t == 0) {//数量为0的时候，key被移除
                        map.remove(lc);
                    } else {
                        map.put(lc, t);//-1后数量再次更新进去
                    }
                }
                res = Math.max(res, i - l + 1);//计算长度
            }
            return res;
        }
```

### 方法2：Map标记位置+滑动窗口

```java
public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int res = 0;
    Map<Character, Integer> map = new HashMap<>();//k:字符，v:该字符最近一次出现的位置
    int l = 0;//左窗口
    for (int i = 0; i < s.length(); i++) {
        map.put(s.charAt(i), i);//将当前字符和字符的位置关系记录下来
        while (map.size() > k) {//总的字符开始超过k个
            //如果 s[l]的 位置和l 不同，说明在[l+1 ... i ]之间又出现了字符s[l]，这是不能移除s[l],反之则移除s[l]
            if (map.get(s.charAt(l)) == l) map.remove(s.charAt(l));
            ++l;
        }
        res = Math.max(res, i - l + 1);
    }
    return res;
}
```







## [395. 至少有 K 个重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/)

### 方法1:递归

```java
public int longestSubstring(String s, int k) {
    int n = s.length();
    return dfs(s, 0, n - 1, k);
}

public int dfs(String s, int l, int r, int k) {
    int[] cnt = new int[26];
    for (int i = l; i <= r; i++) {
        cnt[s.charAt(i) - 'a']++;
    }

    char split = 0;
    for (int i = 0; i < 26; i++) {
        if (cnt[i] > 0 && cnt[i] < k) {
            split = (char) (i + 'a');
            break;
        }
    }
    if (split == 0) {
        return r - l + 1;
    }

    int i = l;
    int ret = 0;
    while (i <= r) {
        while (i <= r && s.charAt(i) == split) {
            i++;
        }
        if (i > r) {
            break;
        }
        int start = i;
        while (i <= r && s.charAt(i) != split) {
            i++;
        }

        int length = dfs(s, start, i - 1, k);
        ret = Math.max(ret, length);
    }
    return ret;
}
```







## [424. 替换后的最长重复字符](https://leetcode-cn.com/problems/longest-repeating-character-replacement/)



```java
public int characterReplacement(String s, int k) {
    int[] arr = new int[26];
    char[] chas = s.toCharArray();
    int l = 0, r = 0, ans = 0, maxCnt = 0;
    while (r < chas.length) {
        arr[chas[r] - 'A']++;
        maxCnt = Math.max(maxCnt, arr[chas[r] - 'A']);
        r++;
        if ((r - l) > (maxCnt + k)) {
            arr[chas[l] - 'A']--;
            l++;
        }
        ans = r - l;
    }
    return ans;
}
```





## [713. 乘积小于 K 的子数组](https://leetcode-cn.com/problems/subarray-product-less-than-k/)

```java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k <= 1) return 0;
    int n = nums.length, res = 0;
    //统计以nums[r]为右边界的子数组的个数
    for (int t = 1, l = 0, r = 0; r < n; r++) {
        t *= nums[r];
        while (t >= k) t /= nums[l++];
        res += r - l + 1;
    }
    return res;
}
```

- 拿掉边界的处理

```java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    int n = nums.length, res = 0;
    //统计以nums[r]为右边界的子数组的个数
    for (int t = 1, l = 0, r = 0; r < n; ) {
        t *= nums[r++];//这一步执行 ++
        while (l < r && t >= k) t /= nums[l++];
        res += r - l;//长度搞好是r-l不需要+1
    }
    return res;
}
```





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

### Follow up:返回所有符合条件的索引

```java
        static List<int[]> resList = new ArrayList<>();
        int maxx = 0;


        public List<int[]> maxConsecutiveAnswers(String str, int k) {
            getMaxLength(str, 'T', k);
            getMaxLength(str, 'F', k);
            return resList;
        }

        //字符s中可以至多包含k个c的最大长度
        private void getMaxLength(String s, char c, int k) {
            //左右窗口
            int n = s.length(), l = 0, r = 0;
//            int res = 0;
            while (r < n) {
                //[r]如果不是c的话，消耗掉一次k的值 k--
                if (s.charAt(r) != c) k--;
                while (k < 0) {
                    if (s.charAt(l) != c) k++;
                    l++;
                }
                r++;
                if (r - l >= maxx) {
                    if (r - l > maxx) resList.clear();
                    resList.add(new int[]{l, r});
                    maxx = r - l;
                }
            }
        }
```







