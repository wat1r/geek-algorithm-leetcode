package com.frankcooper.bank._401_500;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _436 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] intervals = PrintUtils.processSymbol("[[3,4],[2,3],[1,2]]");
            Assert.assertArrayEquals(new int[]{-1, 0, 1}, handler.findRightInterval(intervals));
        }


        public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            int[] res = new int[n];
            int[][] arr = Arrays.copyOf(intervals, n);
            Map<int[], Integer> map = new HashMap<>();
            //排序后的idx变掉了，提前存下来
            for (int i = 0; i < n; i++) map.put(arr[i], i);
            //start值唯一，没有重复，按start排序，二分时单调性
            Arrays.sort(arr, (a, b) -> a[0] - b[0]);
            for (int i = 0; i < n; i++) {
                int endI = intervals[i][1];
                int l = 0, r = n - 1;
                //下取整：要求j最小化
                //j满足要求时，说明当前的mid是可能的值，[l,mid]是我们想要的区间，因为要确定mid是否是最小化的j
                //j不满足要求时，说明当前的mid一定不是我们想要的 [mid+1,r]是我们想要的区间
                while (l < r) {
                    int mid = (l + r) / 2;
                    int startJ = arr[mid][0];
                    if (startJ >= endI) r = mid;
                    else l = mid + 1;
                }
                int idx = -1;//l 在0~n-1的区间范围内，不会越界
                if (arr[l][0] >= endI) idx = map.get(arr[l]);
                res[i] = idx;
                System.out.printf("%d\n", idx);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int[] findRightInterval(int[][] intervals) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            int n = intervals.length;
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                treeMap.put(intervals[i][0], i);
            }
            for (int i = 0; i < n; i++) {
                //`Object higherKey（Object key）`：**返回严格大于指定键的最小键。
                Integer k = treeMap.higherKey(intervals[i][1] - 1);
                res[i] = k == null ? -1 : treeMap.get(k);
            }
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int[] findRightInterval(int[][] intervals) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            int n = intervals.length;
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                treeMap.put(intervals[i][0], i);
            }
            for (int i = 0; i < n; i++) {
                //返回大于或等于给定键的最小键，如果没有这样的键则返回null。
                Integer k = treeMap.ceilingKey(intervals[i][1]);
                res[i] = k == null ? -1 : treeMap.get(k);
            }
            return res;
        }

    }


    /**
     * WA
     */
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[][] intervals = PrintUtils.processSymbol("[[3,4],[2,3],[1,2]]");
            Assert.assertArrayEquals(new int[]{-1, 0, 1}, handler.findRightInterval(intervals));
        }


        public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            int[] res = new int[n];
            Map<int[], Integer> map = new HashMap<>();
            //排序后的idx变掉了，提前存下来
            for (int i = 0; i < n; i++) map.put(intervals[i], i);
            //start值唯一，没有重复，按start排序，二分时单调性
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            for (int i = 0; i < n; i++) {
                int endI = intervals[i][1];
                int l = i, r = n - 1;
                //下取整：要求j最小化
                //j满足要求时，说明当前的mid是可能的值，[l,mid]是我们想要的区间，因为要确定mid是否是最小化的j
                //j不满足要求时，说明当前的mid一定不是我们想要的 [mid+1,r]是我们想要的区间
                while (l < r) {
                    int mid = (l + r) / 2;
                    int startJ = intervals[mid][0];
                    if (startJ >= endI) r = mid;
                    else l = mid + 1;
                }
                int t = -1;//l 在0~n-1的区间范围内，不会越界
                int idx = map.get(intervals[i]);
                if (intervals[l][0] >= endI) t = map.get(intervals[l]);
                res[idx] = t;
                System.out.printf("%d\n", t);
            }
            return res;
        }
    }
}
