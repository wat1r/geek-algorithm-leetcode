package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _84 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] heights = new int[]{2, 1, 5, 6, 2, 3};
            Assert.assertEquals(10, handler.largestRectangleArea(heights));

        }


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


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] heights = new int[]{2, 4};
            Assert.assertEquals(4, handler.largestRectangleArea(heights));
        }

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
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] heights = new int[]{2, 1, 5, 6, 2, 3};
            Assert.assertEquals(10, handler.largestRectangleArea(heights));
        }

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
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

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
    }

    static class _5th {
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

    }
}
