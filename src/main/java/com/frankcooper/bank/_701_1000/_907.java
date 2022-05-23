package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _907 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = {3, 1, 2, 4};
            handler.sumSubarrayMins(arr);

        }


        int MOD = 1000000007;

        public int sumSubarrayMins(int[] A) {
            Stack<Pair> stack = new Stack<>();
            int res = 0, tmp = 0;
            for (int i = 0; i < A.length; i++) {
                int count = 1;
                while (!stack.empty() && stack.peek().val >= A[i]) {
                    Pair pop = stack.pop();
                    count += pop.count;
                    tmp -= pop.val * pop.count;
                }
                stack.push(new Pair(A[i], count));
                tmp += A[i] * count;
                res += tmp;
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
    }


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] arr = {3, 1, 2, 4};
            Assert.assertEquals(17, handler.sumSubarrayMins(arr));

        }


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

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] arr = {3, 1, 2, 4, 1};
            Assert.assertEquals(17, handler.sumSubarrayMins(arr));

        }

        public int sumSubarrayMins(int[] arr) {
            int MOD = (int) 1e9 + 7;
            int n = arr.length;
            int[] left = new int[n], right = new int[n];
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                //栈顶的元素比当前元素大，弹出。维护一个
                while (!stk.isEmpty() && arr[stk.peek()] > arr[i]) {
                    stk.pop();
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);
            }
            stk.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                    stk.pop();
                }
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                res += ((long) (i - left[i]) * (right[i] - i) * arr[i]) % MOD;
                res %= MOD;
            }
            return (int) res;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        int MOD = (int) 1e9 + 7;

        // 重写根据下标取值方法，-1和n返回MIN_VALUE
        private int getElement(int[] arr, int n, int i) {
            if (i == -1 || i == n) {
                return Integer.MIN_VALUE;
            }
            return arr[i];
        }

        public int sumSubarrayMins(int[] arr) {
            // 处理边界情况
            if (arr == null || arr.length == 0) {
                return 0;
            }
            int n = arr.length;
            long ans = 0;
            Deque<Integer> stack = new LinkedList<>();
            // 将下标-1和n作为两个哨兵元素，它们对应的元素为MIN_VALUE
            // -1作为最左边界，n作为最右边界
            for (int i = -1; i <= n; i++) {
                // 向左寻找第一个小于等于A[i]的元素
                while (!stack.isEmpty() && getElement(arr, n, stack.peek()) > getElement(arr, n, i)) {
                    // A[cur]就是之前思路中的A[i]，注意区分和上面代码的区别
                    // 对于每个出栈元素来说，i就是它们的右边界，而栈顶元素就是左边界
                    int cur = stack.pop();
                    // 计算贡献值
                    ans = (ans + (long) (cur - stack.peek()) * (i - cur) * arr[cur]) % MOD;
                }
                stack.push(i);
            }

            return (int) ans;
        }
    }


}
