package com.frankcooper.bank.week;

import java.util.*;
//import com.frankcooper.swordoffer.utils.PrintUtils;

import com.frankcooper.utils.PrintUtils;

public class Week236 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {51, 38, 73, 21, 27, 55, 18, 15, 79, 29, 13, 45, 8, -73, -92, -20, -50, -60, -70};
            handler.arraySign(nums);

        }


        public int arraySign(int[] nums) {
//            int sign = 1;
            int postive = 0, negtive = 0;
            for (int x : nums) {
                if (x == 0) return 0;
                if (x > 0) postive++;
                else negtive++;
            }
            return negtive % 2 == 0 ? 1 : -1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.findTheWinner(6, 5);
        }


        public int findTheWinner(int n, int k) {

            List<Integer> list = new LinkedList<>();
            for (int i = 1; i <= n; i++) list.add(i);
            int t = n;
            int i = 0;
            while (t > 1) {
                int next = (i + k) % t - 1;
                list.remove((i - 1 + k) % t);
                i = next == -1 ? 0 : next;
                t--;
            }
            return list.get(0);
        }
    }


    /**
     * 约瑟夫环  1823
     */
    class _2nd_1 {
        public int findTheWinner(int n, int k) {
            return fun(n, k) + 1;
        }

        public int fun(int n, int k) {
            return n == 1 ? 0 : (fun(n - 1, k) + k) % n;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] o = {0, 1, 0, 1, 3, 1, 1, 1, 0, 2, 0};
            o = new int[]{0, 1, 2, 3, 0};
            /**
             * [0,1,2,3,0]  //2
             * [0,1,1,3,3,0]  //0
             * [0,2,1,0,3,0]  //2
             * [0,1,0,1,3,1,1,1,0,2,0] //1
             * [0,3,3,0,3,2,2,0,0,3,0] //1
             * [0,0,3,1,0,1,0,2,3,1,0] //2
             */
            o = new int[]{0, 0, 3, 1, 0, 1, 0, 2, 3, 1, 0};
            o = new int[]{0, 1, 2, 3, 0};
            handler.minSideJumps(o);
        }


        public int minSideJumps(int[] obstacles) {
            int n = obstacles.length;
            int[][] dp = new int[n][3];//dp[i][j]表示处在i位置，j赛道，最少侧跳数量
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            dp[0][0] = dp[0][2] = 1;
            dp[0][1] = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    if (obstacles[i] == j + 1) continue;
                    dp[i][j] = dp[i - 1][j];
                }
                PrintUtils.printMatrix(dp);
                for (int j = 0; j < 3; j++) {
                    if (obstacles[i] == j + 1) continue;
                    int x = (j + 1) % 3, y = (j + 2) % 3;
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i][x], dp[i][y]) + 1);
                }
            }
            int res = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
            return res;

        }







      /*  public int minSideJumps(int[] obstacles) {
            Set<Integer> set = new HashSet<>();
            int cnt = 0, last = 0, two = 0;
            for (int i = 0; i < obstacles.length; i++) {
                if (set.size() == 3) {
                    if (last == 2) {
                        cnt++;
                    } else {
                        cnt += 2;
                    }
                    set.clear();
                    continue;
                }
                if (obstacles[i] != 0) {
                    set.add(obstacles[i]);
                    last = obstacles[i];
                    if (obstacles[i] == 2) two = 1;
                }
            }
            if (cnt == 0 && two == 1) cnt = 1;
//            if (last != 2) cnt++;
            return cnt;
        }*/
    }

    static class _3rd_1 {
        public static void main(String[] args) {

        }

        public int minSideJumps(int[] obstacles) {


            int res = 0;
            int i = 0, n = obstacles.length;
            int o = 2;
            for (; i < n; i++) {
                if (obstacles[i + 1] != o) {
                    i++;
                    continue;
                }
                int other = (o + 1) % 3, another = (o + 2) % 3;

                int a = i, b = i;
                while (a < n && obstacles[a] != other) {

                }

            }
            return 0;

        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class MKAverage {

            int size;
            int m, k;
            int[] arr;

            public MKAverage(int m, int k) {
                this.m = m;
                this.k = k;
                this.size = 0;
                this.arr = new int[m];
            }

            public void addElement(int num) {
                arr[size % m] = num;
                size++;
            }

            public int calculateMKAverage() {
                if (size < m) return -1;
                //这一步是暴力枚举方法的关键，不能直接用arr去sort。因为calculateMKAverage()函数可能调用2次以上
                int[] tmp = Arrays.copyOfRange(arr, 0, m);
                Arrays.sort(tmp);
                long sum = 0;
                for (int j = k; j < m - k; j++) {
                    sum += tmp[j];
                }
                return (int) (sum / (m - 2 * k));
            }
        }
    }

    static class _4th_1 {
        public static void main(String[] args) {

        }
    }

    static class _4th_2 {
        public static void main(String[] args) {

        }

        class MKAverage {
            int m, k, size;
            int midLen;
            long midSum = 0;

            Deque<Integer> q = new LinkedList<>();
            PriorityQueue<Integer> maxPart = new PriorityQueue<>();//小根堆
            PriorityQueue<Integer> minPart = new PriorityQueue<>((o1, o2) -> o2 - o1);//大根堆
            PriorityQueue<Integer> midPartMIN = new PriorityQueue<>();//小根堆
            PriorityQueue<Integer> midPartMAX = new PriorityQueue<>((o1, o2) -> o2 - o1);//大根堆


            public MKAverage(int m, int k) {
                this.m = m;
                this.k = k;
                this.midLen = m - 2 * k;
            }

            public void addElement(int num) {
                if (size == m) {
                    int t = q.removeLast();
                    if (t > midPartMAX.peek()) {
                        maxPart.remove(t);
                    } else if (t < midPartMIN.peek()) {
                        minPart.remove(t);
                    } else {
                        midPartMIN.remove(t);
                        midPartMAX.remove(t);
                        midSum -= t;
                    }
                } else {
                    size++;
                }
                q.offerFirst(num);
                //当前元素num大于maxPart的栈顶元素，因为maxPart是小根堆，弹出栈顶元素往midPart部分转移
                if (maxPart.size() > 0 && num > maxPart.peek()) {
                    maxPart.offer(num);
                    if (maxPart.peek() > k) maxToMid();
                }
                //当前元素num小于minPart的栈顶元素，因为minPart是大根堆，弹出栈顶元素往midPart部分转移
                else if (minPart.size() > 0 && num < minPart.peek()) {
                    minPart.offer(num);
                    if (minPart.peek() > k) minToMid();
                }
                //往midPart送
                else {
                    midPartMAX.offer(num);
                    midPartMIN.offer(num);
                    midSum += num;
                }

                if (midPartMIN.size() > midLen) {
                    if (minPart.size() < k) {
                        midToMin();
                    } else {
                        midToMax();
                    }
                }
            }

            private void midToMin() {
                int t = midPartMIN.poll();
                midPartMAX.remove(t);
                minPart.offer(t);
                midSum -= t;
            }

            private void midToMax() {
                int t = midPartMAX.poll();
                midPartMIN.remove(t);
                maxPart.offer(t);
                midSum -= t;
            }

            private void maxToMid() {
                int t = maxPart.poll();
                midPartMAX.offer(t);
                midPartMIN.offer(t);
                midSum += t;
            }

            private void minToMid() {
                int t = minPart.poll();
                midPartMIN.offer(t);
                midPartMAX.offer(t);
                midSum += t;
            }

            public int calculateMKAverage() {
                if (size < m) return -1;
                return (int) (midSum / midLen);
            }


        }


    }


    static class _4th_3 {
        public static void main(String[] args) {

        }

        class MKAverage {

            int m, k;
            Deque<Integer> queue;
            Fenwick idxs, vals;//

            public MKAverage(int m, int k) {
                this.m = m;
                this.k = k;
                this.queue = new LinkedList<>();
                this.idxs = new Fenwick(10005);
                this.vals = new Fenwick(10005);
            }

            public void addElement(int num) {
                queue.offerFirst(num);
                idxs.update(num, 1);
                vals.update(num, num);
                if (queue.size() > m) {
                    int t = queue.removeLast();
                    idxs.update(num, -1);
                    vals.update(num, -t);
                }
            }

            private int getIdx(int k) {//查找第k大的元素
                int lo = 0, hi = 10005;
                while (lo < hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (idxs.sum(mid) < k) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }
                return lo;
            }


            public int calculateMKAverage() {
                if (queue.size() < m) return -1;
                int lo = getIdx(k);
                int hi = getIdx(m - k);
                int res = vals.sum(hi) - vals.sum(lo);
                res += (idxs.sum(lo) - k) * lo;
                res -= (idxs.sum(hi) - (m - k)) * hi;
                return res / (m - 2 * k);

            }

            /**
             * ex. m = 6, k = 2, nums = [1,2,2,3,3,4]
             * index_presum(0-index) = [0,1,3,5,6]
             * -> index_presum[lo=2]=3>=k1=2
             * -> index_presum[hi=3]=5>=k2=4
             * ->self.value.sum(hi)-self.value.sum(lo) = sum([1,2,2,3,3]) - sum([1,2,2]) = sum([3,3])
             * But the actual solution here is sum([2,3]), we need to remove a 3 and add back a 2.
             *
             *
             * self.index.sum(x) stores counts of numbers >= x, and self.index.sum(x) >= k since we may have multiple the same x number but we only need self.index.sum(x) = k
             */
        }


        class Fenwick {
            private int[] tree;
            private int len;

            public Fenwick(int n) {
                this.len = n;
                tree = new int[n + 1];
            }

            // 单点更新：将 index 这个位置 + 1
            public void update(int i, int delta) {
                // 从下到上，最多到 size，可以等于 size
                while (i <= this.len) {
                    tree[i] += delta;
                    i += lowbit(i);
                }
            }


            // 区间查询：查询小于等于 index 的元素个数
            // 查询的语义是"前缀和"
            public int sum(int i) {
                // 从右到左查询
                int sum = 0;
                while (i > 0) {
                    sum += tree[i];
                    i -= lowbit(i);
                }
                return sum;
            }

            public int lowbit(int x) {
                return x & (-x);
            }

        }
    }


    /**
     * treemap解法
     * https://leetcode.com/problems/finding-mk-average/discuss/1152742/Clean-Java-with-3-TreeMaps
     */
    static class _4th_4 {
        public static void main(String[] args) {

        }


        class MKAverage {

            int m, k;
            TreeMap<Integer, Integer> top = new TreeMap<>();
            TreeMap<Integer, Integer> middle = new TreeMap<>();
            TreeMap<Integer, Integer> bottom = new TreeMap<>();
            Queue<Integer> q = new LinkedList<>();
            long middleSum = 0;
            int countTop, countBottom;

            public MKAverage(int m, int k) {
                this.m = m;
                this.k = k;
            }

            public void addElement(int num) {
                if (q.size() == m) {
                    int t = q.poll();
                    if (top.containsKey(t)) {
                        remove(top, t);
                        countTop--;
                    } else if (middle.containsKey(t)) {
                        remove(middle, t);
                        middleSum -= t;
                    } else {
                        remove(bottom, t);
                        countBottom--;
                    }
                }
                add(middle, num);
                q.offer(num);
                middleSum += num;
                while (countTop < k && !middle.isEmpty()) {
                    countTop++;
                    middleSum -= middle.lastKey();
                    add(top, remove(middle, middle.lastKey()));
                }
                while (!middle.isEmpty() && !top.isEmpty() && top.firstKey() < middle.lastKey()) {
                    middleSum += top.firstKey();
                    add(middle, remove(top, top.firstKey()));
                    middleSum -= middle.lastKey();
                    add(top, remove(middle, middle.lastKey()));
                }
                while (countBottom < k && !middle.isEmpty()) {
                    countBottom++;
                    middleSum -= middle.firstKey();
                    add(bottom, remove(middle, middle.firstKey()));
                }
                while (!middle.isEmpty() && !bottom.isEmpty() && bottom.lastKey() > middle.firstKey()) {
                    middleSum += bottom.lastKey();
                    add(middle, remove(bottom, bottom.lastKey()));
                    middleSum -= middle.firstKey();
                    add(bottom, remove(middle, middle.firstKey()));
                }

            }

            public int calculateMKAverage() {
                return q.size() == m ? (int) (middleSum / (m - 2 * k)) : -1;
            }


            private int remove(TreeMap<Integer, Integer> treeMap, int target) {
                treeMap.put(target, treeMap.get(target) - 1);
                if (treeMap.get(target) == 0) treeMap.remove(target);
                return target;
            }

            private void add(TreeMap<Integer, Integer> treeMap, int target) {
                treeMap.put(target, treeMap.getOrDefault(target, 0) + 1);
            }
        }


    }

}
