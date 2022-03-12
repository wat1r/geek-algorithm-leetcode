package com.frankcooper.bank.week;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frankcooper.struct.ListNode;
import com.frankcooper.utils.PrintUtils;
import com.google.common.base.Stopwatch;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

public class Week244 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            int[][] mat = PrintUtils.processSymbol("[[0,0,0],[0,1,0],[1,1,1]]");
            int[][] target = PrintUtils.processSymbol("[[1,1,1],[0,1,0],[0,0,0]]");
//            handler.findRotation(mat, target);
//            mat = PrintUtils.processSymbol("[[1,1],[0,1]]");
//            target = PrintUtils.processSymbol("[[1,1],[1,0]]");
//            Assert.assertTrue(handler.findRotation(mat, target));

            mat = PrintUtils.processSymbol("[[0,0,0],[0,0,1],[0,0,1]]");
            target = PrintUtils.processSymbol("[[0,0,0],[0,0,1],[0,0,1]]");
            Assert.assertTrue(handler.findRotation(mat, target));
        }


        public boolean findRotation(int[][] mat, int[][] target) {
            boolean res = false;
            int n = mat.length;
            for (int k = 0; k < 4; k++) {
                res = true;
                p:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (k == 0 && target[i][j] != mat[n - 1 - j][i]) {
                            res = false;
                            break p;
                        } else if (k == 1 && target[i][j] != mat[n - 1 - i][n - 1 - j]) {
                            res = false;
                            break p;
                        } else if (k == 2 && target[i][j] != mat[j][n - 1 - i]) {
                            res = false;
                            break p;
                        } else if (k == 3 && target[i][j] != mat[i][j]) {
                            res = false;
                            break p;
                        }
                    }
                }
                if (res) break;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{5, 1, 3};
            Assert.assertEquals(3, handler.reductionOperations(nums));
            nums = new int[]{1, 1, 1};
            Assert.assertEquals(0, handler.reductionOperations(nums));
            nums = new int[]{1, 1, 2, 2, 3};
            Assert.assertEquals(4, handler.reductionOperations(nums));

        }


        /**
         * 模拟
         *
         * @param nums
         * @return
         */
        public int reductionOperations(int[] nums) {
            //[0]存重复数据出现的次数，[1]存值，从大到小，值相等，下标从小到大
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums) {
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
            int minx = Integer.MAX_VALUE;
            for (int x : map.keySet()) {
                pq.offer(new int[]{map.get(x), x});
                minx = Math.min(minx, x);
            }
            int res = 0;
            while (!pq.isEmpty() && pq.peek()[1] > minx) {
                int[] c = pq.poll();
                int t = c[0], x = c[1];
                if (!pq.isEmpty() && x > pq.peek()[1]) {
                    res += t;
                    int t1 = pq.peek()[0], x1 = pq.peek()[1];
                    pq.poll();
                    pq.offer(new int[]{t + t1, x1});
                }
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int minFlips(String s) {
            int n = s.length();
            //f[i]表示对于[0...i-1]这个区间上的字符而言，最好执行的操作2的次数，使其变成01相间的字符串
            int[] f = new int[n + 1];


            return 0;
        }
    }

    static class _4th {

        static Stopwatch stopwatch;

        public static void main(String[] args) throws IOException {
            _4th handler = new _4th();
//            int[] nums = new int[]{10, 5, 14};
            int[] packages = {3, 5, 8, 10, 11, 12};
            int[][] boxes = {{12}, {11, 9}, {10, 5, 14}};
//            for (int[] box : boxes) {
//                for (int x : packages) {
//                    System.out.printf("%d-->%d\n", x, handler.find(box, x));
//                }
//            }


            packages = new int[]{2, 3, 5};
            boxes = new int[][]{{1, 4}, {2, 3}, {3, 4}};
//            handler.minWastedSpace(packages, boxes);


            packages = new int[]{2, 1, 5, 4, 4};
            boxes = new int[][]{{2}, {10}, {1, 2}};
//            Assert.assertEquals(34, handler.minWastedSpace(packages, boxes));


            List<String> lists = FileUtils.readLines(new File("src/main/resources/input.txt"));
            for (int i = 0; i < lists.size(); i++) {
                String line = lists.get(i);
                if (i == 0) {
                    List<Integer> t = JSON.parseArray(line, Integer.class);
                    packages = new int[t.size()];
                    for (int j = 0; j < t.size(); ++j) {
                        packages[j] = t.get(j);
                    }
                } else if (i == 1) {
                    List<List> t = JSON.parseArray(line, List.class);
                    boxes = new int[t.size()][];
                    for (int j = 0; j < t.size(); j++) {
                        List<Integer> c = t.get(j);
                        boxes[j] = new int[c.size()];
                        for (int k = 0; k < c.size(); k++) {
                            boxes[j][k] = c.get(k);
                        }
                    }

                }
            }
            System.out.printf("");
            stopwatch = Stopwatch.createStarted();
            System.out.printf("%s\n", stopwatch.toString());
            handler.minWastedSpace(packages, boxes);

        }

        /**
         * 31/41 TLE
         *
         * @param packages
         * @param boxes
         * @return
         */
        public int minWastedSpace(int[] packages, int[][] boxes) {
            int MOD = (int) 1e9 + 7;
            int res = Integer.MAX_VALUE;
            int cnt = 0;
            for (int[] box : boxes) {
                int y = 0;
                boolean f = true;
                for (int x : packages) {
                    int t = find(box, x);
                    if (t == -1) {
                        cnt++;
                        f = false;
                        break;
                    }
                    y += (t - x) % MOD;
                }
                if (f) res = Math.min(res, y);
            }
            if (cnt == boxes.length) return -1;
            System.out.printf("%s\n", stopwatch.toString());
            return res % MOD;
        }


        /**
         * 在nums中找一个最小的元素大于等于target，没有这样的元素，返回-1
         *
         * @param nums
         * @param target
         * @return
         */
        private int find(int[] nums, int target) {
            Arrays.sort(nums);
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] == target) return nums[mid];
                else if (nums[mid] < target) l = mid + 1;
                else r = mid;
            }
            if (nums[l] < target) return -1;
            return nums[l];
        }

    }


    static class _4th_1 {
        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();

            int[] packages = {3, 5, 8, 10, 11, 12};
            int[][] boxes = {{12}, {11, 9}, {10, 5, 14}};
//            Arrays.sort(packages);
//            for (int[] box : boxes) {
//                for (int target : box) {
////                    System.out.printf("%d-->%d\n", target, handler.find(packages, target));
//                }
//            }

            packages = new int[]{2, 3, 5};
            boxes = new int[][]{{4, 8}, {2, 8}};
            Assert.assertEquals(6, handler.minWastedSpace(packages, boxes));
        }

        public int minWastedSpace(int[] packages, int[][] boxes) {
            int MOD = (int) 1e9 + 7;
            int n = packages.length;
            Arrays.sort(packages);
            long[] preSum = new long[n + 1];
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + packages[i - 1];
            long res = Long.MAX_VALUE;
            for (int[] box : boxes) {
                Arrays.sort(box);
                if (packages[n - 1] > box[box.length - 1]) continue;
                int lo = 0;
                long t = 0;
                for (int target : box) {
                    int idx = find(packages, target, lo);
                    t += (idx - lo) * (long) target - (preSum[idx] - preSum[lo]);
                    lo = idx;
                }
                res = Math.min(res, t);
            }
            return res == Long.MAX_VALUE ? -1 : (int) (res % MOD);
        }


        /**
         * @param nums   package 数组， 即包裹的数组
         * @param target 目标查找的数
         * @return 返回nums数组中小于等于target 最接近的那个数的下标
         */
        public int find(int[] nums, int target, int lo) {
            int hi = nums.length;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] <= target) lo = mid + 1;
                else hi = mid;
            }
            return lo;
        }
        private int searchRight(int[] packages, int target, int left) {
            int right = packages.length - 1;

            while (left < right) {
                int mid = (left + right + 1) / 2;
                System.out.printf("%d\n", mid);

                if (packages[mid] <= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }


        private int lastLessOrEquals(int[] arr, int target, int l) {
            int r = arr.length - 1;
            while (l < r) {
                int mid = l + ((r - l + 1) >> 1);
                if (arr[mid] > target) r = mid - 1;
                else l = mid; // 收缩左边界不影响 last equals
            }
            if (arr[l] <= target && (l == arr.length - 1 || arr[l + 1] > target)) return l; // <=
            return -1;
        }

    }


    static class _4th_2 {
        public static void main(String[] args) {
            _4th_2 handler = new _4th_2();
            int[] packages = new int[]{2, 3, 5};
            int[][] boxes = new int[][]{{4, 8}, {2, 8}};
//            Assert.assertEquals(6, handler.minWastedSpace(packages, boxes));
            packages = new int[]{7, 6, 5, 3, 4};
            boxes = new int[][]{{2, 7}, {6}, {10, 5}};
//            Assert.assertEquals(10, handler.minWastedSpace(packages, boxes));

            packages = new int[]{1, 4, 5, 2, 4};
            boxes = new int[][]{{7}, {8, 6}, {6}};
//            Assert.assertEquals(14, handler.minWastedSpace(packages, boxes));
            packages = new int[]{19, 3, 3, 16, 3, 18, 5, 5, 16, 18};
            boxes = new int[][]{{9}, {20, 14, 7}, {7, 10, 13}, {12, 15, 17}, {4, 8}};
            Assert.assertEquals(29, handler.minWastedSpace(packages, boxes));
        }


        public int minWastedSpace(int[] packages, int[][] boxes) {
            int MOD = (int) 1e9 + 7;
            int n = packages.length;
            Arrays.sort(packages);
            long[] preSum = new long[n + 1];
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + packages[i - 1];
            long res = Long.MAX_VALUE;
            for (int[] box : boxes) {
                Arrays.sort(box);
                if (packages[n - 1] > box[box.length - 1]) continue;
                int lo = 0;
                long t = 0;
                for (int target : box) {
                    int idx = lastLessOrEquals(packages, target, lo);
                    if (target < packages[0]) {
                        continue;
                    }
                    int y = idx;
                    if (lo == 0) {
                        y = idx + 1;
                    }
                    if (y != lo) {
                        t += (y - lo) * (long) target - (preSum[idx + 1] - preSum[lo == 0 ? lo : lo + 1]);
                    }
                    lo = idx;

                }
                res = Math.min(res, t);
            }
            return res == Long.MAX_VALUE ? -1 : (int) (res % MOD);
        }


        private int lastLessOrEquals(int[] arr, int target, int l) {
            int r = arr.length - 1;
            while (l < r) {
                int mid = l + ((r - l + 1) >> 1);
                if (arr[mid] > target) r = mid - 1;
                else l = mid; // 收缩左边界不影响 last equals
            }
//            if (arr[l] <= target && (l == arr.length - 1 || arr[l + 1] > target)) return l; // <=
//            return -1;
            return l;
        }
    }

}
