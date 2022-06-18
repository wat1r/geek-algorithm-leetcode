package com.frankcooper.platform.leetcode.bank._401_500;

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


    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            int[][] intervals = new int[][]{{3, 4}, {2, 3}, {1, 2}};
            handler.findRightInterval(intervals);
        }

        public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            //[0]存储原区间的左端点 [1]存储原区间的索引
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                arr[i][0] = intervals[i][0];
                arr[i][1] = i;
            }
            //arr按[0]即左端点从小到大排序
            Arrays.sort(arr, (a, b) -> a[0] - b[0]);
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int l = 0, r = n - 1;
                int t = -1;//终值
                while (l < r) {
                    int mid = l + (r - l) / 2;//上取整
                    //当前的[mid...r]区间比处理区间的右端点要大，说明mid是个可能最终值，mid的这个值需要暂时保留
                    if (arr[mid][0] >= intervals[i][1]) {
                        r = mid;
                        t = arr[i][1];
                    } else {
                        //[l...mid]这个区间的值要比处理区间的右端点小，说明这一段可以完整的排除
                        l = mid + 1;
                    }
                }
                //最后需要拦截下[l,r] 即[l]这个值是不是大于等于当前区间右端点
                if (arr[l][0] >= intervals[i][1]) res[i] = arr[l][1];
                else res[i] = t;
            }
            return res;
        }
    }

    static class _6th {
        public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            Map<int[], Integer> map = new HashMap<>();
            //排序后的idx变掉了，提前存下来
            for (int i = 0; i < n; i++) {
                map.put(intervals[i], i);
            }
            //start值唯一，没有重复，按start排序，二分时单调性
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int l = 0, r = n - 1, t = -1;
                while (l < r) {
                    //下取整：要求j最小化
                    //j满足要求时，说明当前的mid是可能的值，[l,mid]是我们想要的区间，因为要确定mid是否是最小化的j
                    //j不满足要求时，说明当前的mid一定不是我们想要的 [mid+1,r]是我们想要的区间
                    int mid = l + (r - l) / 2;
                    if (intervals[mid][0] >= intervals[i][1]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                int index = map.get(intervals[i]);
                if (intervals[l][0] >= intervals[i][1]) {
                    t = map.get(intervals[l]);
                }
                res[index] = t;
            }
            return res;
        }
    }


    static class _7th {

        public static void main(String[] args) {
            _7th handler = new _7th();
            int[][] intervals = new int[][]{{3, 4}, {2, 3}, {1, 2}};
            handler.findRightInterval(intervals);
        }


        public int[] findRightInterval(int[][] intervals) {
            //[0]是区间的左端点，[1]是区间的索引,按[0]从小到大排序
            TreeSet<int[]> ts = new TreeSet<>((a, b) -> a[0] - b[0]);
            int n = intervals.length;
            for (int i = 0; i < n; i++) {
                ts.add(new int[]{intervals[i][0], i});
            }
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                //找大于等于当前区间的右端点的key
                int[] t = ts.ceiling(new int[]{intervals[i][1], i});
                res[i] = t == null ? -1 : t[1];
            }
            return res;
        }
    }

    static class _8th {
        public int[] findRightInterval(int[][] intervals) {
            int n = intervals.length;
            int[][] start = new int[n][2], end = new int[n][2];
            for (int i = 0; i < n; i++) {
                start[i][0] = intervals[i][0];
                start[i][1] = i;
                end[i][0] = intervals[i][1];
                end[i][1] = i;
            }
            Arrays.sort(start, Comparator.comparingInt(o -> o[0]));
            Arrays.sort(end, Comparator.comparingInt(o -> o[0]));
            int[] res = new int[n];
            for (int i = 0, j = 0; i < n; i++) {
                while (j < n && end[i][0] > start[j][0]) {
                    j++;
                }
                res[end[i][1]] = j < n ? start[j][1] : -1;
            }
            return res;
        }
    }


}
