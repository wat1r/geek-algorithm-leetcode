package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

public class Week290 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] nums = {{3, 1, 2, 4, 5}, {1, 2, 3, 4}, {3, 4, 5, 6}};
            handler.intersection(nums);


        }


        public List<Integer> intersection(int[][] nums) {
            List<Integer> res = new ArrayList<>();
            int n = nums.length;
            int[] cnt = new int[1005];
            for (int[] num : nums) {
                for (int x : num) {
                    cnt[x]++;
                }
            }
            for (int i = 1; i < cnt.length; i++) {
                if (cnt[i] == n) res.add(i);
            }
//            res.sort(Comparator.comparingInt(a -> a));
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int countLatticePoints(int[][] circles) {
            int N = 310;
            int res = 0;
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    if (check(new int[]{i, j}, circles)) res++;
                }
            }
            return res;
        }

        public boolean check(int[] p, int[][] circles) {
            for (int[] circle : circles) {
                if (distance(p, new int[]{circle[0], circle[1]}) <= circle[2] * circle[2]) {
                    return true;
                }
            }
            return false;
        }


        public int distance(int[] p, int[] q) {
            return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int[] countRectangles(int[][] rectangles, int[][] points) {
            //
            Arrays.sort(rectangles, (a, b) -> {
                if (a[1] == b[1]) return a[0] - b[0];
                else return a[1] - b[1];
            });

            int n = points.length;
            int[] ans = new int[n];


            return ans;
        }
    }

    static class _3rd_1 {
        public static void main(String[] args) {
            _3rd_1 handler = new _3rd_1();
        }

        public int[] countRectangles(int[][] rectangles, int[][] points) {
            //k: 矩形的高h v:矩形的宽l
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int[] r : rectangles) {
                int l = r[0], h = r[1];
                List<Integer> list = map.getOrDefault(h, new ArrayList<>());
                list.add(l);
                map.put(h, list);
            }
            //接下来要进行二分，需要将每个h的list进行排序
            for (int h : map.keySet()) {
                Collections.sort(map.get(h));
            }
            int n = points.length;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                int[] p = points[i];
                int x = p[0], y = p[1];
                int cnt = 0;
//                for (int h = 100; h >= 1; --h) {
//                    if (h < y) break;
//                    if (!map.containsKey(h)) continue;
//                    cnt += binarySearch(map.get(h), x);
//                }
                for (int h = y; h <= 100; h++) {
                    if (!map.containsKey(h)) continue;
                    cnt += binarySearch(map.get(h), x);
                }
                ans[i] = cnt;
            }
            return ans;
        }

        //找list中有多少个元素大等于k的，list是自小到大排序的
        public int binarySearch(List<Integer> list, int k) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (list.get(mid) < k) l = mid + 1;
                else r = mid;
            }
            //判断[r]的元素是不是比k大 n-r这一段即比大于等于k的个数
            return list.get(r) >= k ? list.size() - r : 0;
        }
    }

    static class _3rd_2 {
        public int[] countRectangles(int[][] rectangles, int[][] points) {
            int[] count = new int[points.length];

            Arrays.sort(rectangles, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1] == o2[1]) return o1[0] - o2[0];
                    return o1[1] - o2[1];
                }
            });
            //先将`y`的取值存放到一个线性表中
            //这里我是为了省去判重的步骤，直接使用treeset，后面再将值存入线性表
            Set<Integer> set = new TreeSet<>();

            //哈希表中存储具有y高度的，x的取值
            Map<Integer, List<Integer>> map = new HashMap<>();
            //循环按上面所说的要求存储
            for (int[] rect : rectangles) {
                int x = rect[0], y = rect[1];
                map.putIfAbsent(y, new ArrayList<>());

                set.add(y);
                map.get(y).add(x);
            }
            //set中的值入表
            List<Integer> height = new ArrayList<>(set);

            int p = 0;

            for (int[] point : points) {
                int x = point[0], y = point[1];
                int startIdx = 0, sum = 0;
                //对于pinots中的每一个点，我们都先从先前的线性表height中遍历取得大于等于y的最小值
                //这里最多循环100次，因此直接线性查找即可
                while (startIdx < height.size() && height.get(startIdx) < y) startIdx++;

                while (startIdx < height.size()) {
                    //获得以y为高度的，所有x的取值的表
                    int h = height.get(startIdx);
                    List<Integer> list = map.get(h);

                    //二分查找符合要求的x的下标
                    int q = binarySearch(list, x);
                    //sum加上符合要求的个数
                    sum += list.size() - q;
                    //继续向后遍历
                    startIdx++;
                }
                count[p] = sum;
                p++;
            }
            return count;
        }

        //这里我们的二分查找，是要找到大于等于point[0]的最小值
        int binarySearch(List<Integer> list, int x) {
            //表中的最大值都小于x，则不可能符合要求，直接返回
            if (list.get(list.size() - 1) < x) return list.size();
            //确定区间
            int left = 0, right = list.size() - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                //小于x，不符合要求，区间左移
                if (list.get(mid) < x) {
                    left = mid + 1;
                } else right = mid; //否则右移
            }
            return left;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[][] flowers = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
            int[] persons = {2, 3, 7, 11};
            handler.fullBloomFlowers(flowers, persons);
//            flowers = new int[][]{{19, 37}, {19, 38}, {19, 35}};
//            persons = new int[]{6, 7, 21, 1, 13, 37, 5, 37, 46, 43};
//            handler.fullBloomFlowers(flowers, persons);
        }


        //46/52 TLE
        public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
            Arrays.sort(flowers, (a, b) -> a[0] - b[0]);
//            Arrays.sort(persons);
            int[] ans = new int[persons.length];
            for (int i = 0; i < persons.length; i++) {
                int l = 0, r = flowers.length - 1;
                while (l < r) {
                    int mid = l + (r - l + 1) / 2;
                    if (flowers[mid][0] > persons[i]) {
                        r = mid - 1;
                    } else {
                        l = mid;
                    }
                }
                if (flowers[l][0] > persons[i]) continue;
                int[][] segments = Arrays.copyOfRange(flowers, 0, l + 1);
                Arrays.sort(segments, (a, b) -> a[1] - b[1]);
                l = 0;
                r = segments.length - 1;
                int t = 0;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (segments[mid][1] < persons[i]) {
                        l = mid + 1;
                    } else {
                        t += r - mid;
                        r = mid;
                    }
                }
                ans[i] = t + (segments[l][1] >= persons[i] ? 1 : 0);
            }
            return ans;
        }
    }


    static class _4th_1 {
        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();
            int[][] flowers = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
            int[] persons = {2, 3, 7, 11};
            handler.fullBloomFlowers(flowers, persons);

        }


        //正在开的花的个数 为所有在此时间节点及之前开放的花的个数减去在时间节点之前凋谢的花的个数
        public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
            int[] res = new int[persons.length];
            int N = 50005;
            int[] start = new int[N], end = new int[N];
            int n = flowers.length;
            for (int i = 0; i < n; i++) {
                int[] flower = flowers[i];
                start[i] = flower[0];
                end[i] = flower[1];
            }
            Arrays.sort(start);
            Arrays.sort(end);
            for (int i = 0; i < persons.length; i++) {
                int s = upper_bound(start, 0, start.length, persons[i]);
                int e = upper_bound(end, 0, end.length, persons[i] - 1);
                res[i] = s - e;
            }
            return res;
        }


        //    函数 upper_bound() 在 [begin, end) 进行二分查找，返回 大于 target的第一个元素位置。如果所有元素都小于target，则返回 end.
        public static int upper_bound(int[] arr, int begin, int end, int target) {
            while (begin < end) {
                int mid = begin + (end - begin) / 2;
                if (arr[mid] <= target)
                    begin = mid + 1;
                else if (arr[mid] > target)
                    end = mid;
            }
            return begin;
        }
    }
}
