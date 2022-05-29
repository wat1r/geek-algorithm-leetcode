# 单调栈

> 











## [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

![](/imgs/leetcode/classify/image-20211125095100544.png)

### 方法1：单调栈

![](/imgs/leetcode/classify/image-20211125205259368.png)

![](/imgs/leetcode/classify/image-20211125205324964.png)

![](/imgs/leetcode/classify/image-20211125205341817.png)

![](/imgs/leetcode/classify/image-20211125205357204.png)

![](/imgs/leetcode/classify/image-20211125205417160.png)

![](/imgs/leetcode/classify/image-20211125205438330.png)



```java
        public int trap(int[] height) {
            int res = 0;
            Stack<Integer> stk = new Stack<>();//存数组的下标索引
            int cur = 0; //当前位置的下标
            while (cur < height.length) {
                //栈不为空  当前位置的值，比栈顶的值（上一个入栈的值，最靠近当前位置的下标索引）要大,入栈
                while (!stk.isEmpty() && height[cur] > height[stk.peek()]) {
                    int m = height[stk.pop()];//记录下这个值，做这一轮计算的底
                    if (stk.isEmpty()) break;//前探一个位置，没有位置跳出
                    //计算： 当前位置cur 和 栈顶位置的最小值，组成一个封闭空间，和m这个底相减（木桶原理）, 组成高度
                    //  下标的相减得到宽度
                    res += (Math.min(height[cur], height[stk.peek()]) - m) * (cur - stk.peek() - 1);
                }
                stk.push(cur++);//当前元素比栈顶元素小，入栈，指针后移
            }
            return res;
        }
```

**另外一种写法，大同小异**:

```java
        public int trap(int[] height) {
            Stack<Integer> stk = new Stack<>();
            int res = 0, cur = 0;
            while (cur < height.length) {
                if (stk.isEmpty() || height[cur] <= height[stk.peek()]) {
                    stk.push(cur++);
                } else {
                  //前一个栈弹出的节点
                    int pre = stk.pop();
                    if (!stk.isEmpty()) {
                      //木桶原理，取最小高度
                        int m = Math.min(height[stk.peek()], height[cur]);
                        res += (m - height[pre]) * (cur - stk.peek() - 1);
                    }
                }
            }
            return res;
        }

```





### 方法2：双指针

![](/imgs/leetcode/classify/image-20211125212946599.png)

![](/imgs/leetcode/classify/image-20211125213002483.png)

```java
public int trap(int[] height) {
    //左右索引
    int l = 0, r = height.length - 1;
    //左右两侧都不能形成一个封闭的区域
    //从左侧往右找，一直递增地找
    //从右侧往左找，一直递增地找
    while (l < r && height[l] <= height[l + 1]) l++;
    while (r > l && height[r] <= height[r - 1]) r--;
    int res = 0;//结果
    while (l < r) {
        //左右索引所在的柱子的高度
        int left = height[l], right = height[r];
        //优先左段
        if (left <= right) {
            //如果基准的left高度比其右侧的l的高度大，是可以形成雨水的，因为right比left大
            //++l精髓，强制向右滑动
            while (l < r && left >= height[++l]) {
                res += left - height[l];
            }
        } else {
            //如果基准的right高度比其左侧的l的高度大，是可以形成雨水的，因为left比right大
            //--r精髓，强制向左滑动
            //这里可能会出现相等高度的柱子，体积是0
            while (r > l && right >= height[--r]) {
                res += right - height[r];
            }
        }
    }
    return res;
}
```

**另外一种写法，也很巧妙：**

```java
public int trap(int[] height) {
    int res = 0;
    //左右侧的索引
    int l = 0, r = height.length - 1;
    //l r 对应的height，初始值是MIN
    int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
    while (l < r) {
        //获取当前索引 l r的最大高度
        left = Math.max(left, height[l]);
        right = Math.max(right, height[r]);
        //优先低的高度进行计算
        if (left < right) {
            //l 要强制向右滑动 计算雨水的面积，更新左侧的最大高度left
            res += left - height[l++];
            left = Math.max(left, height[l]);
        } else {
            //r 要强制向左滑动 计算雨水的面积，更新右侧的最大高度right
            res += right - height[r--];
            right = Math.max(right, height[r]);
        }
    }
    return res;
}
```

### 方法3：动态规划

```java
       public int trap(int[] height) {
            int n = height.length;
            //leftH[i]表示第i个柱子左边最高的柱子的高度
            int[] leftH = new int[n];
            //rightH[i]表示第i个柱子右边最高的柱子的高度
            //上述的两个数组应该是符合单调性的
            int[] rightH = new int[n];
            //最左边的柱子的左边没有柱子了，leftH[0]=0
            for (int i = 0; i < n - 2; i++) {
                leftH[i + 1] = Math.max(leftH[i], height[i]);
            }
            //最右边的柱子的右边没有柱子了，rightH[n-1]=0
            for (int i = n - 2; i >= 0; --i) {
                rightH[i] = Math.max(rightH[i + 1], height[i + 1]);
            }
            int res = 0;
            //每次取左右两侧的最小值，做高度，每次步进1个长度
            for (int i = 1; i < n - 1; i++) {
                int m = Math.min(leftH[i], rightH[i]);
                if (m > height[i]) {
                    res += (m - height[i]);
                }
            }
            return res;
        }

```

## [316. 去除重复字母](https://leetcode-cn.com/problems/remove-duplicate-letters/)

```java
public String removeDuplicateLetters(String s) {
    int[] cnt = new int[26];//记录s中每个字母出现的次数
    for (char c : s.toCharArray()) cnt[c - 'a']++;
    Set<Character> set = new HashSet<>();//记录当前字符是否已经存在在栈内
    Deque<Character> stk = new ArrayDeque<>();//单调栈
    for (char c : s.toCharArray()) {
        if (!set.contains(c)) {//set没有c字母
            //1.栈顶字符比当前字符字典序大
            //2.栈顶字符在当前字符c后还出现过（栈顶的cnt计数大于1）
            while (!stk.isEmpty() && stk.peek() > c && cnt[stk.peek() - 'a'] > 1) {
                char t = stk.pop();
                cnt[t - 'a']--;
                set.remove(t);
            }
            stk.push(c);
            set.add(c);
        } else {//set中已经有该字符，也就是在栈内已经出现了1次，后面的同时出现该字符时，可以抛弃
            cnt[c - 'a']--;
        }
    }
    StringBuilder sb = new StringBuilder();
    while (!stk.isEmpty()) sb.append(stk.pop());
    return sb.reverse().toString();
}
```

## [321. 拼接最大数](https://leetcode-cn.com/problems/create-maximum-number/)

```java
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums1 = {3, 4, 6, 5};
            int[] nums2 = {9, 1, 2, 5, 8, 3};
            int k = 5;
            //[9, 8, 6, 5, 3]
            handler.maxNumber(nums1, nums2, k);
            nums1 = new int[]{6, 7};
            nums2 = new int[]{6, 0, 4};
            k = 5;
            //[6, 7, 6, 0, 4]
            handler.maxNumber(nums1, nums2, k);
        }


        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int m = nums1.length, n = nums2.length;
            int[] res = null;
            for (int i = 0; i <= k; i++) {
                int[] arr1 = getMaxKArray(nums1, Math.min(i, m));
                int[] arr2 = getMaxKArray(nums2, Math.min(k - i, n));
                int[] arr = null;
                if (arr1.length + arr2.length == k) {
                    arr = merge(arr1, arr2);
                    System.out.println(Arrays.toString(arr));
                }
                if (res == null || arr != null && arr.length == k && compare(arr, 0, res, 0)) {
                    res = arr;
                }
            }
            return res;
        }

        //在数组nums中拿到k个数，该数是单调递减的
        private int[] getMaxKArray(int[] nums, int k) {
            //维护一个单调递减的单调栈
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < nums.length; i++) {
                while (!stk.isEmpty() && stk.peek() < nums[i] && (k - stk.size()) < nums.length - i) {
                    stk.pop();
                }
                if (stk.size() < k) stk.push(nums[i]);
            }

            int[] res = new int[k];
            int idx = k - 1;
            while (!stk.isEmpty() && idx >= 0) res[idx--] = stk.pop();
            return res;
        }

        //合并A和B数组，生成一个新的数组
        private int[] merge(int[] A, int[] B) {
            int m = A.length, n = B.length;
            int[] res = new int[m + n];
            int idx = 0, i = 0, j = 0;
            while (i < m || j < n) {
                res[idx++] = compare(A, i, B, j) ? A[i++] : B[j++];
            }
            return res;
        }



        //比较A和B数组从idx1和idx2分别开始的字典序的大小
        private boolean compare(int[] A, int idx1, int[] B, int idx2) {
            while (idx1 < A.length && idx2 < B.length && A[idx1] == B[idx2]) {
                idx1++;
                idx2++;
            }
            return idx2 == B.length || (idx1 < A.length && A[idx1] > B[idx2]);
        }


    }
```









## [402. 移掉 K 位数字](https://leetcode-cn.com/problems/remove-k-digits/)

```java
        public String removeKdigits(String num, int k) {
            //维护一个单调递增的单调栈，该栈的大小是len(num)-k的长度
            Deque<Character> stk = new ArrayDeque<>();
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                while (!stk.isEmpty() && stk.peek() > c && k > 0) {
                    stk.pop();
                    k--;
                }
                stk.push(c);
            }
            //防止k没有完全消耗完
            /*num="9"
              k=1
              exprected:"0"
             */
            while (k-- > 0) stk.pop();
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) sb.append(stk.pop());
            boolean headZero = true;//前导零
            StringBuilder res = new StringBuilder();
            for (char c : sb.reverse().toString().toCharArray()) {
                if (c == '0' && headZero) continue;
                res.append(c);
                headZero = false;
            }
            return res.toString().equals("") ? "0" : res.toString();
        }
```

另

```java
        public String removeKdigits(String num, int k) {
            Deque<Character> stk = new ArrayDeque<>();
            char[] ch = num.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                while (!stk.isEmpty() && stk.peek() > ch[i] && k > 0) {
                    stk.pop();
                    k--;
                }
                stk.push(ch[i]);
            }
            while (k-- > 0) stk.pop();
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) sb.append(stk.pop());
            StringBuilder res = new StringBuilder();
            boolean headZero = true;
            for (char c : sb.reverse().toString().toCharArray()) {
                if (c == '0' && headZero) {
                    continue;
                }
                res.append(c);
                headZero = false;
            }
            return res.toString().equals("") ? "0" : res.toString();
        }
```





## [84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram/)

### 方法1：单调栈+哨兵

```java
public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    int[] nums = new int[n + 2];
    System.arraycopy(heights, 0, nums, 1, heights.length);
    Deque<Integer> stk = new ArrayDeque<>();
    int maxArea = 0;
    for (int i = 0; i < nums.length; i++) {
        //当前的柱子的高度比栈顶的柱子的高度低，计算以弹出的栈顶高度的柱子为高的矩形面积
        while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
            int h = nums[stk.pop()];
            maxArea = Math.max(maxArea, h * (i - stk.peek() - 1));
        }
        stk.push(i);
    }
    return maxArea;
}
```

- 另

```java
        public int largestRectangleArea(int[] heights) {
            Deque<Integer> stk = new ArrayDeque<>();
            //设置哨兵
            stk.push(-1);
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                while (stk.peek() != -1 && heights[i] < heights[stk.peek()]) {
                    int h = heights[stk.pop()];
                    maxArea = Math.max(maxArea, h * (i - stk.peek() - 1));
                }
                stk.push(i);
            }
            //[2,4]case
            //如果栈内还存在合法的下标没有出栈，则以数组的右边界为界限计算最大区域
            while (stk.peek() != -1) {
                int h = heights[stk.pop()];
                maxArea = Math.max(maxArea, h * (heights.length - stk.peek() - 1));
            }
            return maxArea;
        }
```

- 另

```java
public int largestRectangleArea(int[] heights) {
    Deque<Integer> stk = new ArrayDeque<>();
    //最末尾数设置为0，此前的柱子的高度都会比这个高
    int[] nums = new int[heights.length + 1];
    for (int i = 0; i < heights.length; i++) nums[i] = heights[i];
    int maxArea = 0;
    for (int i = 0; i < nums.length; i++) {
        while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
            int h = nums[stk.pop()];
            //如果栈空了，就以当前的下标索引为宽度 即 i -0 数组的左边界
            maxArea = Math.max(maxArea, h * (stk.isEmpty() ? i : i - stk.peek() - 1));
        }
        stk.push(i);
    }
    return maxArea;
}
```



### 方法2：双指针

- TLE

```java

        public int largestRectangleArea(int[] heights) {
            int maxArea = 0;
            int n = heights.length;
            int[] nums = new int[n + 2];
            for (int i = 0; i < n; i++) {
                nums[i + 1] = heights[i];
            }
            for (int i = 1; i < nums.length - 1; i++) {
                int l = i, r = i;
                while (l >= 0 && nums[l] >= nums[i]) l--;
                while (r < nums.length && nums[r] >= nums[i]) r++;
                maxArea = Math.max(maxArea, nums[i] * (r - l - 1));
            }
            return maxArea;
        }
```



### 方法3：计算左右索引

- 类似1856、907等题的做法

```java
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] left = new int[n];
            int[] right = new int[n];
            // 记录左边第一个小于等于该柱子的下标
            left[0] = -1;
            for (int i = 1; i < n; i++) {
                int t = i - 1;
                while (t >= 0 && heights[t] >= heights[i]) t = left[t];
                left[i] = t;
            }
            // 记录每个柱子 右边第一个小于等于该柱子的下标
            right[n - 1] = n;
            for (int i = n - 2; i >= 0; i--) {
                int t = i + 1;
                while (t < n && heights[t] >= heights[i]) t = right[t];
                right[i] = t;
            }
            // 计算
            int maxArea = 0;
            for (int i = 0; i < n; i++) {
                int t = heights[i] * (right[i] - left[i] - 1);
                maxArea = Math.max(t, maxArea);
            }
            return maxArea;
        }
```









## [496. 下一个更大元素 I](https://leetcode.cn/problems/next-greater-element-i/)

```java
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Stack<Integer> stk = new Stack<>();
            //k:nums2的当前元素，v:当前元素的next greater element
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums2) {
                while (!stk.isEmpty() && stk.peek() < x) {
                    map.put(stk.pop(), x);
                }
                stk.push(x);
            }
            //如果当前元素没有NGE，返回-1
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.getOrDefault(nums1[i], -1);
            }
            return res;
        }
```



## [503. 下一个更大元素 II](https://leetcode.cn/problems/next-greater-element-ii/)

### 方法1：单调栈+倍增数组

```java
public int[] nextGreaterElements(int[] nums) {
    Deque<Integer> stk = new ArrayDeque<>();
    int n = nums.length;
    int[] res = new int[n];
    Arrays.fill(res, -1);
    for (int i = 0; i < 2 * n; i++) {
        int t = nums[i % n];
        while (!stk.isEmpty() && nums[stk.peek()] < t) {
            res[stk.pop()] = t;
        }
        if (i < n) {
            stk.push(i);
        }
    }
    return res;
}
```

另，数组模拟栈

```java
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            // 使用数组模拟栈，bottom 代表栈底，top 代表栈顶
            int[] d = new int[n * 2];
            int bottom = 0, top = -1;
            for (int i = 0; i < n * 2; i++) {
                while (bottom <= top && nums[i % n] > nums[d[top]]) {
                    int u = d[top--];
                    ans[u] = nums[i % n];
                }
                d[++top] = i % n;
            }
            return ans;
        }
```









## [654. 最大二叉树](https://leetcode-cn.com/problems/maximum-binary-tree/)

```java
public TreeNode constructMaximumBinaryTree(int[] nums) {
    return dfs(nums, 0, nums.length);
}

private TreeNode dfs(int[] nums, int start, int end) {
    if (start == end) return null;
    int rootIdx = start;
    for (int i = start; i < end; i++) {
        if (nums[i] > nums[rootIdx]) rootIdx = i;
    }
    TreeNode root = new TreeNode(nums[rootIdx]);
    root.left = dfs(nums, start, rootIdx);
    root.right = dfs(nums, rootIdx + 1, end);
    return root;

}
```





## [739. 每日温度](https://leetcode-cn.com/problems/daily-temperatures/)

### 方法1:Stack

```java
public int[] dailyTemperatures(int[] T) {
    int n = T.length;
    int[] res = new int[n];
    //存的下标索引
    Stack<Integer> stk = new Stack<>();
    for (int i = 0; i < n; i++) {
        //弹栈 while循环， 如果栈顶的温度比当前的温度小，这个当前的温度是满足题意的首次出现的最高温度
        while (!stk.isEmpty() && T[stk.peek()] < T[i]) {
            int idx = stk.pop();//之前的那个索引
            res[idx] = i - idx;//间隔的天数
        }
        stk.push(i);//入栈
    }
    return res;
}
```

### 方法2:Deque

- 详细参考[基础与技巧](/docs/leetcode/classify/basic_skill.md#Deque的主要使用方式)

```java
        public int[] dailyTemperatures(int[] T) {
            int n = T.length;
            int[] res = new int[n];
            //存的下标索引
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                //弹栈 while循环， 如果栈顶的温度比当前的温度小，这个当前的温度是满足题意的首次出现的最高温度
                while (!stk.isEmpty() && T[stk.peekFirst()] < T[i]) {
                    int idx = stk.pollFirst();//之前的那个索引
                    res[idx] = i - idx;//间隔的天数
                }
                stk.addFirst(i);//入栈
            }
            return res;
        }
```



## [907. 子数组的最小值之和](https://leetcode.cn/problems/sum-of-subarray-minimums/)

### 方法1

```java
        public int sumSubarrayMins(int[] arr) {
            int MOD = (int) 1e9 + 7;
            int n = arr.length;
            long sum = 0;
            Stack<Integer> stk = new Stack<>();
            int j, k;
            for (int i = 0; i <= n; i++) {
                while (!stk.isEmpty() && arr[stk.peek()] > (i == n ? Integer.MIN_VALUE : arr[i])) {
                    j = stk.pop();
                    k = stk.isEmpty() ? -1 : stk.peek();
                    sum += (long) arr[j] * (i - j) * (j - k);
                }
                stk.push(i);
            }
            return (int) (sum % MOD);
        }
```

- 另外

```java
       public int sumSubarrayMins(int[] arr) {
            int MOD = (int) 1e9 + 7;
            //存的是当前元素的下标索引
            Deque<Integer> stk = new ArrayDeque<>();
            int[] A = new int[arr.length + 1];
            //最后一个元素作为哨兵
            for (int i = 0; i < arr.length; i++) A[i] = arr[i];
//            A[A.length-1] = 0;
            int N = A.length;
            long res = 0;
            for (int i = 0; i < N; i++) {
                //遍历到的元素比栈顶的元素（遍历到的元素最近的一个元素）要小
                while (!stk.isEmpty() && A[i] <= A[stk.peek()]) {
                    //设置弹出的元素是当前元素
                    int index = stk.pop();
                    //前一个元素
                    int prev = -1;
                    if (!stk.isEmpty()) prev = stk.peek();
                    int m = index - prev - 1;//
                    int n = i - index - 1;
                    res += (long) (A[index]) * (m + 1) * (n + 1) % MOD;
                    res %= MOD;
                }
                stk.push(i);
            }
            return (int) res;
        }
```



### 方法2：计算左右的贡献值

```java
        public int sumSubarrayMins(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            Deque<Integer> stk = new ArrayDeque<>();
            int[] left = new int[n], right = new int[n];
            //找到左侧第一个比nums[i]小的下标，维护一个单调递增栈
            for (int i = 0; i < n; i++) {
                //栈顶元素不小于(即大于等于)当前元素，此时有递减的趋势，弹出栈顶元素
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    stk.pop();
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);

            }
            stk.clear();
            //栈顶元素大于(此处没有等于，此处与上一处只能一处有等于，防止重复计算)当前元素，此时有递减的趋势，弹出栈顶元素
            for (int i = n - 1; i >= 0; --i) {
                while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                    stk.pop();
                }
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }

            long res = 0;
            for (int i = 0; i < n; i++) {
                res += 1L * (i - left[i]) * (right[i] - i) * nums[i];
                res %= MOD;
            }
            return (int) res;

        }
```



### 方法3

```java
       int MOD = (int) 1e9 + 7;

        public int sumSubarrayMins(int[] A) {
            Stack<Pair> stack = new Stack<>();
            int res = 0, t = 0;
            for (int i = 0; i < A.length; i++) {
                int count = 1;
                while (!stack.empty() && stack.peek().val >= A[i]) {
                    Pair pair = stack.pop();
                    count += pair.count;
                    t -= pair.val * pair.count;
                }
                stack.push(new Pair(A[i], count));
                t += A[i] * count;
                res += t;
                res %= MOD;
            }
            return res;
        }

        class Pair {
            public int val;
            public int count;

            public Pair(int val, int count) {
                this.val = val;
                this.count = count;
            }

        }
```















## [1019. 链表中的下一个更大节点](https://leetcode-cn.com/problems/next-greater-node-in-linked-list/)

```java
public int[] nextLargerNodes(ListNode head) {
    List<Integer> list = new ArrayList<>();
    while (head != null) {
        list.add(head.val);
        head = head.next;
    }
    Stack<Integer> stk = new Stack<>();
    int[] res = new int[list.size()];
    for (int i = 0; i < list.size(); ++i) {
        while (!stk.isEmpty() && list.get(stk.peek()) < list.get(i)) {
            int idx = stk.pop();
            res[idx] = list.get(i);
        }
        stk.push(i);
    }
    return res;
}
```

## [1856. 子数组最小乘积的最大值](https://leetcode.cn/problems/maximum-subarray-min-product/)

### 方法1

```java
        //AC
        public int maxSumMinProduct(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            long[] s = new long[n + 1];
            for (int i = 0; i < n; i++) {
                s[i + 1] = s[i] + nums[i];
            }
            int[] left = new int[n], right = new int[n];
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                    stk.pop();
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);
            }
            stk.clear();
            for (int i = n - 1; i >= 0; --i) {
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    stk.pop();
                }
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                int l = left[i], r = right[i];
                res = Math.max(res, 1L * (s[r] - s[l + 1]) * nums[i]);
            }
            return (int) (res % MOD);
        }
```



- 另，设置哨兵

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220528194208.png)

```java
public int maxSumMinProduct(int[] src) {
    int MOD = (int) 1e9 + 7;
    //设置前后两个哨兵 0 0 
    int[] nums = new int[src.length + 2];
    for (int i = 0; i < src.length; i++) {
        nums[i + 1] = src[i];
    }
    int n = nums.length;
    long[] pre = new long[n + 1];
    for (int i = 0; i < n; i++) {
        pre[i + 1] = pre[i] + nums[i];
    }
    Deque<Integer> stk = new ArrayDeque<>();
    //右边第一个比它小的元素的下标
    int[] right = new int[n];
    for (int i = 0; i < n; i++) {
        while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
            right[stk.pop()] = i;
        }
        stk.push(i);
    }
    stk.clear();
    //左边第一个比它小的元素的下标
    int[] left = new int[n];
    for (int i = n - 1; i >= 0; --i) {
        while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
            left[stk.pop()] = i;
        }
        stk.push(i);
    }
    long res = 0;
    for (int i = 0; i < n; i++) {
        int l = left[i], r = right[i];
        res = Math.max(res, nums[i] * (pre[r] - pre[l + 1]));
    }
    return (int) (res % MOD);
}
```

### 方法2：一次单调栈操作

```java

        public int maxSumMinProduct(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            // left 是严格定义的，left[i] 是左侧最近的严格小于 nums[i] 的元素下标
            // right 是非严格定义的，right[i] 是右侧最近的小于等于 nums[i] 的元素下标
            int[] left = new int[n], right = new int[n];
            Arrays.fill(right, n - 1);
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    right[stk.pop()] = i - 1;
                }
                if (!stk.isEmpty()) {
                    left[i] = stk.peek() + 1;
                }
                stk.push(i);
            }
            long[] pre = new long[n + 1];
            for (int i = 0; i < n; i++) {
                pre[i + 1] = pre[i] + nums[i];
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, 1L * (pre[right[i] + 1] - pre[left[i]]) * nums[i]);
            }
            return (int) (res % MOD);
        }
```













## [2030. 含特定字母的最小子序列](https://leetcode-cn.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter/)



```java
  static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "leet";
            int k = 3;
            char letter = 'e';
            int repetition = 1;
//            Assert.assertEquals("eet", handler.smallestSubsequence(s, k, letter, repetition));
            s = "aaabbbcccddd";
            k = 3;
            letter = 'b';
            repetition = 2;
            Assert.assertEquals("abb", handler.smallestSubsequence(s, k, letter, repetition));


        }


        public String smallestSubsequence(String s, int k, char letter, int repetition) {

            //letter这个字符出现的次数
            int cnt = 0;
            for (char c : s.toCharArray()) {
                if (c == letter) cnt++;
            }
            //m是s中要删除的字符的数量，留下的字符从长度是k个
            int n = s.length(), m = n - k;
            StringBuilder res = new StringBuilder();
            int p = 0;//目前为止letter已扫描了的次数
            for (int i = 0; i < n; i++) {
                //删除逆序的字母
                while (m > 0 && res.length() > 0 && res.charAt(res.length() - 1) > s.charAt(i)) {
                    if (res.charAt(res.length() - 1) == letter) {
                        if (repetition > cnt - 1 + p) {//后面的letter不够凑成repetition个letter
                            break;
                        }
                        p--;//删除一个letter
                    }
                    res.deleteCharAt(res.length() - 1);
                    m--;
                }
                if (s.charAt(i) == letter) {
                    p++;//扫描letter的次数+1
                    cnt--;//使用一次letter -1
                }
                res.append(s.charAt(i));
            }
            while (res.length() > k) {
                if (res.charAt(res.length() - 1) == letter) p--;
                res.deleteCharAt(res.length() - 1);
            }
            for (int i = k - 1; i >= 0; i--) {
                if (p < repetition && res.charAt(i) != letter) {
                    res.setCharAt(i, letter);
                    p++;
                }
            }
            return res.toString();
        }


    }
```





## [2104. 子数组范围和](https://leetcode.cn/problems/sum-of-subarray-ranges/)

### 方法1：单调栈计算贡献值

- 思路来着leetcode高赞题解

```java
public long subArrayRanges(int[] nums) {
    int n = nums.length;
    //计算nums[i]在 nums中 作为最大值和最小值 所出现的最大区间大小
    //换言之，需要找到nums[i] 作为最大值，找到左边第一个比nums[i]小的索引j 找到右边第一个比nums[i]小的索引k 区间范围为[k-j]
    //同理，需要找到nums[i] 作为最小值，找到左边第一个比nums[i]大的索引j 找到右边第一个比nums[i]大的索引k 区间范围为[k-j]
    //这里因为做了两遍的比较，需要特别注意 在 严格相等的时候，只能取一边，另外一边如果重复取，则会重复
    long[] maxx = getCnt(nums, false);
    long[] minn = getCnt(nums, true);
    long res = 0;
    //计算当前元素作为最大值和最小值时，出现的次数，计算出「贡献值」
    for (int i = 0; i < n; i++) {
        res += nums[i] * (maxx[i] - minn[i]);
    }
    return res;
}

public long[] getCnt(int[] nums, boolean flag) {
    int n = nums.length;
    int[] left = new int[n], right = new int[n];
    Deque<Integer> stk = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        //为true时维持单调递增栈，遇到非严格递减趋势时，弹出栈顶元素「下标」
        //这时候找的是左边比小于等于nums[i]的索引，如果左边没有符合条件的下标则设置为-1，即索引0往左的一个位置
        while (!stk.isEmpty() && (flag ? nums[stk.peek()] >= nums[i] : nums[stk.peek()] <= nums[i])) {
            stk.pop();
        }
        left[i] = stk.isEmpty() ? -1 : stk.peek();
        stk.push(i);
    }
    stk.clear();
    //为true时维持单调递增栈，遇到非严格递减趋势时，弹出栈顶元素「下标」
    //这时候找的是右边比小于(此处没有等于)nums[i]的索引，如果左边没有符合条件的下标则设置为n，即索引「len(nums)」往右的一个位置
    for (int i = n - 1; i >= 0; i--) {
        while (!stk.isEmpty() && (flag ? nums[stk.peek()] > nums[i] : nums[stk.peek()] < nums[i])) {
            stk.pop();
        }
        right[i] = stk.isEmpty() ? n : stk.peek();
        stk.push(i);
    }
    long[] res = new long[n];
    //左侧有 (i - left[i]) 和选择，右侧有(right[i] - i)个选择
    //根据乘法原理,(i - left[i])*(right[i] - i)
    for (int i = 0; i < n; i++) {
        res[i] = 1L * (i - left[i]) * (right[i] - i);
    }
    return res;
}
```

