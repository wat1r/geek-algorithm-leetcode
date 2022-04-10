package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week288 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int num = 65875;
//            Assert.assertEquals(87655, handler.largestInteger(num));
            num = 1234;
//            Assert.assertEquals(3412, handler.largestInteger(num));
            num = 8745789;
            Assert.assertEquals(8987745, handler.largestInteger(num));


        }


        public int largestInteger(int num) {
            char[] ch = String.valueOf(num).toCharArray();
            int n = ch.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (ch[i] < ch[j] && (ch[j] - ch[i]) % 2 == 0) {
                        swap(ch, i, j);
                    }
                }
            }
            return Integer.parseInt(String.valueOf(ch));
        }

        private void swap(char[] ch, int i, int j) {
            char t = ch[i];
            ch[i] = ch[j];
            ch[j] = t;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String expression = "247+38";
            Assert.assertEquals("2(47+38)", handler.minimizeResult(expression));
            expression = "12+34";
            Assert.assertEquals("1(2+3)4", handler.minimizeResult(expression));
            expression = "999+999";
            Assert.assertEquals("(999+999)", handler.minimizeResult(expression));
            expression = "28947+398";
            Assert.assertEquals("2(8947+398)", handler.minimizeResult(expression));
        }

        public String minimizeResult(String expression) {
            List<String> candidates = build(expression);
            int minn = Integer.MAX_VALUE;
            String res = "";
            for (String can : candidates) {
                int t = calculate(can);
                if (t < minn) {
                    minn = t;
                    res = can;
                }
            }
            return res.replace("*", "");
        }


        private List<String> build(String expr) {
            List<String> res = new ArrayList<>();
            int k = expr.indexOf("+");
            int n = expr.length();
            for (int i = 0; i < k; i++) {
                for (int j = k + 1; j < n; j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(expr, 0, i);
                    if (i > 0) sb.append("*");
                    sb.append("(").append(expr, i, k);
                    sb.append(expr.charAt(k));
                    sb.append(expr, k + 1, j + 1);
                    sb.append(")");
                    if (j < n - 1) sb.append("*");
                    sb.append(expr.substring(j + 1));
                    res.add(sb.toString());
                }
            }
            return res;
        }


        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('-', 1);
            put('+', 1);
            put('*', 2);
            put('/', 2);
            put('%', 2);
            put('^', 3);
        }};

        public int calculate(String s) {
            s = s.replaceAll(" ", "");
            char[] cs = s.toCharArray();
            int n = s.length();
            Deque<Integer> nums = new ArrayDeque<>();
            nums.addLast(0);
            Deque<Character> ops = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                char c = cs[i];
                if (c == '(') {
                    ops.addLast(c);
                } else if (c == ')') {
                    while (!ops.isEmpty()) {
                        if (ops.peekLast() != '(') {
                            calc(nums, ops);
                        } else {
                            ops.pollLast();
                            break;
                        }
                    }
                } else {
                    if (isNumber(c)) {
                        int u = 0;
                        int j = i;
                        while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                        nums.addLast(u);
                        i = j - 1;
                    } else {
                        if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                            nums.addLast(0);
                        }
                        while (!ops.isEmpty() && ops.peekLast() != '(') {
                            char prev = ops.peekLast();
                            if (map.get(prev) >= map.get(c)) {
                                calc(nums, ops);
                            } else {
                                break;
                            }
                        }
                        ops.addLast(c);
                    }
                }
            }
            while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
            return nums.peekLast();
        }

        void calc(Deque<Integer> nums, Deque<Character> ops) {
            if (nums.isEmpty() || nums.size() < 2) return;
            if (ops.isEmpty()) return;
            int b = nums.pollLast(), a = nums.pollLast();
            char op = ops.pollLast();
            int ans = 0;
            if (op == '+') {
                ans = a + b;
            } else if (op == '-') {
                ans = a - b;
            } else if (op == '*') {
                ans = a * b;
            } else if (op == '/') {
                ans = a / b;
            } else if (op == '^') {
                ans = (int) Math.pow(a, b);
            } else if (op == '%') {
                ans = a % b;
            }
            nums.addLast(ans);
        }

        boolean isNumber(char c) {
            return Character.isDigit(c);
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {6, 3, 3, 2, 7, 8, 9, 10, 6, 3, 3, 2, 6, 3, 3};
            int k = 1;
            Assert.assertEquals(761711332, handler.maximumProduct(nums, k));
        }

        public int maximumProduct(int[] nums, int k) {
            int MOD = (int) 1e9 + 7;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int x : nums) pq.offer(x);
            while (k-- > 0) {
                int c = pq.poll();
                pq.offer(++c);
            }
            long res = 1;
            while (!pq.isEmpty()) {
                res = pq.poll() * res % MOD;
            }
            return (int) res % MOD;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] flowers = {1, 3, 1, 1};
            int newFlowers = 7, target = 6, full = 12, partial = 1;
            handler.maximumBeauty(flowers, newFlowers, target, full, partial);
        }

        int full, partial, target;

        public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
            this.full = full;
            this.partial = partial;
            this.target = target;
            Arrays.sort(flowers);
            for (int k = 1; k < flowers.length; k++) {
                cal(flowers, k, newFlowers);
            }
            return 0;
        }


        //给定k个花园，给最多的花园的数量种满花，可以得到的美丽值
        public int cal(int[] flowers, int k, long newFlowers) {
            int cnt = 0;
            int target = this.target;
            int n = flowers.length;
            for (int i = n - 1; i >= n - k - 2; i--) {
                int t = target - flowers[i];
                if (newFlowers - t >= 0) {
                    newFlowers -= t;
                    cnt++;
                } else {
                    break;
                }
            }
            int val = cnt * full + flowers[0] * partial;
            return val;

        }


    }

    static class _4th_1 {

        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();
            int[] flowers = {1, 3, 1, 1};
            int newFlowers = 7, target = 6, full = 12, partial = 1;
            handler.maximumBeauty(flowers, newFlowers, target, full, partial);
        }


        //https://leetcode-cn.com/problems/maximum-total-beauty-of-the-gardens/solution/by-endlesscheng-10i7/
        public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
            Arrays.sort(flowers);
            long n = flowers.length;
            if (flowers[0] >= target) return n * full; // 剪枝，此时所有花园都是完善的

            long leftFlowers = newFlowers - target * n;// 填充后缀后，剩余可以种植的花
            for (int i = 0; i < n; ++i) {
                flowers[i] = Math.min(flowers[i], target);
                leftFlowers += flowers[i];
            }

            long ans = 0L;
            long sumFlowers = 0L;
            for (int i = 0, x = 0; i <= n; ++i) { // 枚举后缀长度 n-i
                if (leftFlowers >= 0) {
                    // 计算最长前缀的长度
                    while (x < i && (long) flowers[x] * x - sumFlowers <= leftFlowers)
                        sumFlowers += flowers[x++]; // 注意 x 只增不减，这部分的时间复杂度为 O(n)
                    long beauty = (n - i) * full; // 计算总美丽值
                    if (x > 0) beauty += Math.min((leftFlowers + sumFlowers) / x, (long) target - 1) * partial;
                    ans = Math.max(ans, beauty);
                }
                if (i < n) leftFlowers += target - flowers[i];
            }
            return ans;
        }

    }

    static class _4th_2 {
        public static void main(String[] args) {
            _4th_2 handler = new _4th_2();
            int[] flowers = {1, 3, 1, 1};
            int newFlowers = 7, target = 6, full = 12, partial = 1;
//            Assert.assertEquals(14, handler.maximumBeauty(flowers, newFlowers, target, full, partial));
            flowers = new int[]{10, 9, 16, 14, 6, 5, 11, 12, 17, 2, 11, 15, 1};
            newFlowers = 80;
            target = 14;
            full = 15;
            partial = 1;
            Assert.assertEquals(14, handler.maximumBeauty(flowers, newFlowers, target, full, partial));
        }

        long[] pre;//前缀和


        public long maximumBeauty(int[] f, long newFlowers, int target, int full, int partial) {
            int n = f.length;
            //flowers数组 从大到小排序
            Integer[] flowers = new Integer[n];
            for (int i = 0; i < n; i++) {
                flowers[i] = f[i];
            }
            //新的arr 数组 相当于 f数组
            Integer[] arr = Arrays.copyOf(flowers, n);
            //从大到小排序flowers
            Arrays.sort(flowers, ((o1, o2) -> o2 - o1));
            long res = 0;
            if (flowers[n - 1] >= target) {
                res = (long) n * full;
                return res;
            }
            Arrays.sort(arr);
            pre = new long[n + 1];
            for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + arr[i];
            for (int i = 0; i < n; i++) {
                //flowers按大到小的排序，前i个花园都是完善的状态
                if (flowers[i] >= target) continue;
                //难点在分配那些不完善的花园的数量 也就是mid
                int r = target - 1;
                int l = flowers[n - 1];
                while (l <= r) {
                    //当前应该给多少个花园分配鲜花
                    int mid = l + (r - l) / 2;
                    //如果mid够分，说明mid小了，可以扩大
                    if (judge(arr, newFlowers, mid, n - i)) {
                        l = mid + 1;
                    } else {//如果mid不够份，说明mid大了，要缩小
                        r = mid - 1;
                    }
                }
                //当前有l-1个是不完善的花园，
                res = Math.max(res, (long) i * full + (long) (l - 1) * partial);
                newFlowers -= Math.max(0, target - flowers[i]);
                if (newFlowers < 0) return res;
            }
            /**
             * [10,9,16,14,6,5,11,12,17,2,11,15,1]
             * 80
             * 14
             * 15
             * 1
             */
            if (newFlowers >= 0) res = Math.max(res, (long) n * full);
            return res;
        }

        /**
         * @param arr        正序数组
         * @param newFlowers 当前剩下的可用花的数量
         * @param mid        给定一个鲜花的数量
         * @param range      查找的范围
         * @return
         */
        private boolean judge(Integer[] arr, long newFlowers, int mid, int range) {
            int p = lower_bound(arr, 0, range, mid);
            long t = (long) p * mid - pre[p];
            return newFlowers >= t;
        }

        //函数 lower_bound() 在 [begin, end) 进行二分查找，返回 大于或等于 target的第一个元素位置。如果所有元素都小于target，则返回 end.
        public static int lower_bound(Integer[] arr, int begin, int end, int target) {
            while (begin < end) {
                int mid = begin + (end - begin) / 2;
                // 当 mid 的元素小于 target 时
                if (arr[mid] < target)
                    // begin 为 mid + 1, arr[begin] 的值会小于或等于 target
                    begin = mid + 1;
                    // 当 mid 的元素大于等于 target 时
                else if (arr[mid] >= target)
                    end = mid;
            }
            return begin;
        }

    }
}
