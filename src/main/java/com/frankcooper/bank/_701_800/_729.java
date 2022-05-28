package com.frankcooper.bank._701_800;

import java.util.*;

public class _729 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class MyCalendar {

            //然一个比较实用的估点方式可以「尽可能的多开点数」，利用题目给定的空间上界和我们创建的自定义类
            // （结构体）的大小，尽可能的多开（ Java 的 128M 可以开到 5 * 10^6 以上）。


            class Node {
                int ls, rs;//分别代表当前节点的左右子节点在线段树数组 tr 中的下标
                int lazy;//懒标记
                int val;//当前节点有多少个数
            }

            int N = (int) 1e9 + 10;
            int cnt = 1;//动态开点的下标
            int M = 120010;
            Node[] tr = new Node[M];

            //
            void update(int u, int l, int r, int L, int R, int v) {
                //[l,r]区间被[L,R]覆盖
                if (L <= l && r <= R) {
                    tr[u].val += (r - l + 1) * v;
                    tr[u].lazy += v;
                    return;
                }
                lazyCreate(u);
                pushdown(u, r - l + 1);
                int mid = l + r >> 1;
                if (L <= mid) update(tr[u].ls, l, mid, L, R, v);
                if (R > mid) update(tr[u].rs, mid + 1, r, L, R, v);
                pushup(u);
            }

            int query(int u, int l, int r, int L, int R) {
                if (L <= l && r <= R) return tr[u].val;
                lazyCreate(u);
                pushdown(u, r - l + 1);
                int mid = l + r >> 1;
                int res = 0;
                if (L <= mid) res = query(tr[u].ls, l, mid, L, R);
                if (R > mid) res += query(tr[u].rs, mid + 1, r, L, R);
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
                tr[u].val = tr[tr[u].ls].val + tr[tr[u].rs].val;
            }

            void pushdown(int u, int len) {
                tr[tr[u].ls].lazy += tr[u].lazy;
                tr[tr[u].rs].lazy += tr[u].lazy;
                tr[tr[u].ls].val += (len - len / 2) * tr[u].lazy;
                tr[tr[u].rs].val += len / 2 * tr[u].lazy;
                tr[u].lazy = 0;
            }



            public MyCalendar() {

            }

            public boolean book(int start, int end) {
                int res = query(1, 1, N + 1, start + 1, end);
                if (res > 0) return false;
                update(1, 1, N + 1, start + 1, end, 1);
                return true;
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class MyCalendar {

            TreeMap<Integer, Integer> tm;

            public MyCalendar() {
                tm = new TreeMap<>();
            }

            public boolean book(int start, int end) {
                Integer prev = tm.floorKey(start);
                Integer next = tm.ceilingKey(start);
                if ((prev == null || tm.get(prev) <= start) && (next == null || end <= next)) {
                    tm.put(start, end);
                    return true;
                }
                return false;
            }
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        class MyCalendar {

            TreeSet<int[]> ts;

            public MyCalendar() {
                //按区间的左边界从小到大排序
                ts = new TreeSet<>((a, b) -> a[0] - b[0]);
            }

            public boolean book(int start, int end) {
                int[] prev = ts.floor(new int[]{start, end});
                int[] next = ts.ceiling(new int[]{start, end});
                if ((prev == null || prev[1] <= start) && (next == null || end <= next[0])) {
                    ts.add(new int[]{start, end});
                    return true;
                }
                return false;
            }
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String[] ops = {"MyCalendar", "book", "book", "book"};
            int[][] vals = {{}, {10, 20}, {15, 25}, {20, 30}};
            MyCalendar calendar = new MyCalendar();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("book")) {
                    System.out.printf("%s -> %s\n", Arrays.toString(vals[i]), calendar.book(vals[i][0], vals[i][1]));
                }
            }
        }


        static class MyCalendar {

            class Node {
                int start, end;//区间
                Node leftNode, rightNode;//左右孩子节点

                public Node(int s, int e) {
                    start = s;
                    end = e;
                }
            }

            Node root;


            public MyCalendar() {
                root = new Node(0, 0);
            }

            //每次从根节点去找
            public boolean book(int start, int end) {
                return update(root, start, end);
            }


            /**
             * @param root  树的根节点
             * @param start 待插入的区间的左端点
             * @param end   待插入的区间的右端点
             * @return
             */
            private boolean update(Node root, int start, int end) {
                //case1:
                //[start...end]
                //              [root_start.....root.end]
                //case2:
                //                                        [start...end]
                //              [root_start.....root.end]
                //case3.1:
                //                         [start...end]
                //              [root_start.....root.end]
                //case3.2:
                //          [start...end]
                //              [root_start.....root.end]
                Node cur = root;
                while (true) {
                    if (end <= cur.start) {
                        if (cur.leftNode == null) {
                            cur.leftNode = new Node(start, end);
                            return true;
                        }
                        cur = cur.leftNode;
                    } else if (start >= cur.end) {
                        if (cur.rightNode == null) {
                            cur.rightNode = new Node(start, end);
                            return true;
                        }
                        cur = cur.rightNode;
                    } else {
                        return false;
                    }
                }
            }
        }
    }


}
