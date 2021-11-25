## 【重温经典】接雨水 I

![image-20211125095100544](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125095100544.png)



### 方法1：单调栈

![image-20211125205259368](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125205259368.png)

![image-20211125205324964](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125205324964.png)



![image-20211125205341817](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125205341817.png)

![image-20211125205357204](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125205357204.png)

![image-20211125205417160](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125205417160.png)

![image-20211125205438330](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125205438330.png)



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





### 方法2：双指针

![image-20211125212946599](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125212946599.png)

![image-20211125213002483](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211125213002483.png)



