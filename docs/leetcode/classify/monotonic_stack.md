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









