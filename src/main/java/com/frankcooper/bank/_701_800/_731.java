package com.frankcooper.bank._701_800;

import java.util.*;

public class _731 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            MyCalendarTwo calendar = new MyCalendarTwo();
            calendar.book(10, 20); // returns true
            calendar.book(50, 60); // returns true
            calendar.book(10, 40); // returns true
            calendar.book(5, 15); // returns false
            calendar.book(5, 10); // returns true
            calendar.book(25, 55); // returns true

        }


        static class MyCalendarTwo {

            List<int[]> calendar;//维护是当前的预订区间
            List<int[]> overlaps;//维护的重叠的区间也就是说2个calendar区间中都出现了这个区间


            public MyCalendarTwo() {
                calendar = new ArrayList<>();
                overlaps = new ArrayList<>();
            }

            public boolean book(int start, int end) {
                //overlaps的区间与[start,end]有交集，说明当前的是三重预订，返回false
                for (int[] item : overlaps) {
                    if (start < item[1] && end > item[0]) return false;
                }
                //遍历calendar 找重叠区间
                for (int[] item : calendar) {
                    if (start < item[1] && end > item[0]) {
                        overlaps.add(new int[]{Math.max(start, item[0]), Math.min(item[1], end)});
                    }
                }

                calendar.add(new int[]{start, end});
                return true;
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            MyCalendarTwo calendar = new MyCalendarTwo();
            calendar.book(10, 20); // returns true
            calendar.book(50, 60); // returns true
            calendar.book(10, 40); // returns true
            calendar.book(5, 15); // returns false
            calendar.book(5, 10); // returns true
            calendar.book(25, 55); // returns true
        }


        static class MyCalendarTwo {
            TreeMap<Integer, Integer> map;


            public MyCalendarTwo() {
                map = new TreeMap<>();
            }

            public boolean book(int start, int end) {
                //思路有点类似差分数组，相当于[start,end)这个区间内的元素出现了一次，都+1
                map.put(start, map.getOrDefault(start, 0) + 1);
                map.put(end, map.getOrDefault(end, 0) - 1);
                int count = 0;
                for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                    count += e.getValue();
                    //大于等于3次的预订次数的区间，需要恢复
                    if (count >= 3) {
                        map.put(start, map.getOrDefault(start, 0) - 1);
                        if (map.get(start) == 0) {
                            map.remove(start);//这一步不移除的也不影响程序正确性
                        }
                        map.put(end, map.getOrDefault(end, 0) + 1);
                        if (map.get(end) == 0) {
                            map.remove(end);
                        }
                        return false;
                    }
                }
                return true;
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        class MyCalendarTwo {

            TreeMap<Integer, Integer> intervals;
            TreeMap<Integer, Integer> overlaps;

            public MyCalendarTwo() {
                intervals = new TreeMap<>();
                overlaps = new TreeMap<>();
            }

            public boolean book(int start, int end) {
                if (hasOverlap(start, end, overlaps)) return false;

                if (intervals.size() == 0) {
                    intervals.put(start, end);
                    return true;
                }

                Integer floorKey = intervals.floorKey(start);
                if (floorKey != null && intervals.get(floorKey) > start) {
                    int floorEnd = intervals.get(floorKey);
                    overlaps.put(start, Math.min(end, floorEnd));
                    start = floorKey;
                    end = Math.max(end, floorEnd);
                    intervals.remove(floorKey);
                }

                Integer ceilingKey = intervals.ceilingKey(start);
                while (ceilingKey != null && end > ceilingKey) {
                    int ceilingEnd = intervals.get(ceilingKey);
                    overlaps.put(ceilingKey, Math.min(end, ceilingEnd));
                    end = Math.max(end, ceilingEnd);
                    intervals.remove(ceilingKey);

                    ceilingKey = intervals.ceilingKey(start);
                }

                intervals.put(start, end);
                return true;

            }

            private boolean hasOverlap(int start, int end, TreeMap<Integer, Integer> map) {
                Integer floorKey = map.floorKey(start);
                Integer ceilingKey = map.ceilingKey(start);
                if (floorKey != null && map.get(floorKey) > start) {
                    return true;
                }
                if (ceilingKey != null && end > ceilingKey) {
                    return true;
                }
                return false;
            }
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        class MyCalendarTwo {

            //然一个比较实用的估点方式可以「尽可能的多开点数」，利用题目给定的空间上界和我们创建的自定义类
            // （结构体）的大小，尽可能的多开（ Java 的 128M 可以开到 5 * 10^6 以上）。

            class Node {
                int ls, rs;//分别代表当前节点的左右子节点在线段树数组 tr 中的下标
                int lazy;//懒标记
                int maxx;//当前区间的最大值
            }

            int N = (int) 1e9 + 10;
            int cnt = 1;//动态开点的下标
            int M = 120010;
            Node[] tr = new Node[M];

            //l,r 为当前节点存储的区间 L,R表示要修改的前，这个区间不会变，设置成大写
            void update(int u, int l, int r, int L, int R, int v) {
                //[l,r]区间被[L,R]覆盖
                if (L <= l && r <= R) {
                    tr[u].maxx += v;
                    tr[u].lazy += v;
                    return;
                }
                lazyCreate(u);
                pushdown(u);
                int mid = l + r >> 1;
                if (L <= mid) update(tr[u].ls, l, mid, L, R, v);
                if (R > mid) update(tr[u].rs, mid + 1, r, L, R, v);
                pushup(u);
            }

            int query(int u, int l, int r, int L, int R) {
                if (L <= l && r <= R) return tr[u].maxx;
                lazyCreate(u);
                pushdown(u);
                int mid = l + r >> 1;
                int res = 0;
                if (L <= mid) res = Math.max(res, query(tr[u].ls, l, mid, L, R));
                if (R > mid) res = Math.max(res, query(tr[u].rs, mid + 1, r, L, R));
                return res;
            }

            void lazyCreate(int u) {
                if (tr[u] == null) {
                    tr[u] = new Node();
                }
                if (tr[u].ls == 0) {
                    tr[u].ls = ++cnt;
                    tr[tr[u].ls] = new Node();
                }
                if (tr[u].rs == 0) {
                    tr[u].rs = ++cnt;
                    tr[tr[u].rs] = new Node();
                }

            }


            void pushup(int u) {
                tr[u].maxx = Math.max(tr[tr[u].ls].maxx, tr[tr[u].rs].maxx);
            }

            void pushdown(int u) {
                tr[tr[u].ls].lazy += tr[u].lazy;
                tr[tr[u].rs].lazy += tr[u].lazy;
                tr[tr[u].ls].maxx += tr[u].lazy;
                tr[tr[u].rs].maxx += tr[u].lazy;
                tr[u].lazy = 0;
            }


            public MyCalendarTwo() {

            }

            public boolean book(int start, int end) {
                int res = query(1, 1, N + 1, start + 1, end);
                if (res >= 2) return false;
                update(1, 1, N + 1, start + 1, end, 1);
                return true;
            }
        }
    }
}
