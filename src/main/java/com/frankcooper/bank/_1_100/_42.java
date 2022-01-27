package com.frankcooper.bank._1_100;

import org.junit.Assert;

import java.util.Stack;

public class _42 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
            Assert.assertEquals(handler.trap(height), 6);
        }

        /**
         * 42. 接雨水 Hard
         *
         * @param height
         * @return 维护一个单调栈，从栈底到栈顶是从大到小的顺序
         */
        public int trap(int[] height) {
            if (height == null || height.length == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            int result = 0;
            stack.push(0);
            for (int i = 1; i < height.length; i++) {
                //如果当前值大于栈顶值，需要弹出栈顶的值，并在弹出的时候执行计算雨水的逻辑
                if (height[i] >= height[stack.peek()]) {
                    int bottom = height[stack.pop()];
                    //当前栈有元素，且当前值，大于bottom后面的值
                    while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                        int leftEdge = stack.pop();
                        result += (height[leftEdge] - bottom) * (i - leftEdge - 1);
                        bottom = height[leftEdge];//将bottom移动到左边的边界处
                    }
                    //上面的while循环跑完后，判断栈顶的值比当前值大的话，进下面的逻辑
                    if (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                        result += (height[i] - bottom) * (i - stack.peek() - 1);
                    }
                }
                stack.push(i);
            }
            return result;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
            Assert.assertEquals(handler.trap(height), 6);
//            height = new int[]{4, 2, 3};
//            Assert.assertEquals(handler.trap(height), 1);

        }

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

    }


    static class _2nd_1 {
        public static void main(String[] args) {

        }

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
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
            Assert.assertEquals(handler.trap(height), 6);
        }


        public int trap(int[] A) {
            if (A.length < 3) return 0;

            int ans = 0;
            int l = 0, r = A.length - 1;

            // find the left and right edge which can hold water
            while (l < r && A[l] <= A[l + 1]) l++;
            while (l < r && A[r] <= A[r - 1]) r--;

            while (l < r) {
                int left = A[l];
                int right = A[r];
                if (left <= right) {
                    // add volum until an edge larger than the left edge
                    while (l < r && left >= A[++l]) {
                        ans += left - A[l];
                    }
                } else {
                    // add volum until an edge larger than the right volum
                    while (l < r && A[--r] <= right) {
                        ans += right - A[r];
                    }
                }
            }
            return ans;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


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

    }

    static class _4th_1 {
        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();
            int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
            Assert.assertEquals(handler.trap(height), 6);
        }

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
    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
            Assert.assertEquals(handler.trap(height), 6);
        }


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

    }
}
