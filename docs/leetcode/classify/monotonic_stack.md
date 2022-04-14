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



