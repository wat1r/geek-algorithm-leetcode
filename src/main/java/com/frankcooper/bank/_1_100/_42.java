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
    }
}
