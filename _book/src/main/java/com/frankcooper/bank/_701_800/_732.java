package com.frankcooper.bank._701_800;

import java.util.*;

public class _732 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class MyCalendarThree {

            TreeMap<Integer, Integer> tm;

            public MyCalendarThree() {
                tm = new TreeMap<>();
            }

            public int book(int start, int end) {
                tm.put(start, tm.getOrDefault(start, 0) + 1);
                tm.put(end, tm.getOrDefault(end, 0) - 1);
                int maxx = 0, count = 0;
                for (int t : tm.keySet()) {
                    count += tm.get(t);
                    maxx = Math.max(maxx, count);
                }
                return maxx;
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        class MyCalendarThree {
            Node root;

            public MyCalendarThree() {
                root = new Node(0, (int) 1e9);

            }

            public int book(int start, int end) {
                update(root, start, end - 1, 1);
                return root.val;
            }

            class Node {
                int l;
                int r;
                int lazy;
                int val;
                Node leftNode;
                Node rightNode;

                public Node(int l, int right) {
                    this.l = l;
                    this.r = right;
                }

                public Node getLeft() {
                    if (leftNode == null) {
                        leftNode = new Node(l, l + (r - l) / 2);
                    }
                    return leftNode;
                }

                public Node getRight() {
                    if (rightNode == null) {
                        rightNode = new Node(l + (r - l) / 2 + 1, r);
                    }
                    return rightNode;
                }
            }

            private void pushDown(Node node) {
                if (node.lazy > 0) {
                    node.getLeft().val += node.lazy;
                    node.leftNode.lazy += node.lazy;
                    node.getRight().val += node.lazy;
                    node.rightNode.lazy += node.lazy;
                    node.lazy = 0;
                }
            }


            public void update(Node node, int lo, int hi, int val) {
                if (node.l > hi || node.r < lo) {
                    return;
                }
                if (node.l >= lo && node.r <= hi) {
                    node.val += val;
                    node.lazy += val;
                } else {
                    pushDown(node);
                    update(node.getLeft(), lo, hi, val);
                    update(node.getRight(), lo, hi, val);
                    node.val = Math.max(node.leftNode.val, node.rightNode.val);
                }
            }

            public int query(Node node, int lo, int hi) {
                if (node == null || node.l > hi || node.r < lo) {
                    return 0;
                }
                if (node.l >= lo && node.r <= hi) {
                    return node.val;
                }
                pushDown(node);
                return Math.max(query(node.getLeft(), lo, hi), query(node.getRight(), lo, hi));
            }

        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        class MyCalendarThree {

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


            public MyCalendarThree() {

            }

            public int book(int start, int end) {
                update(1, 1, N + 1, start + 1, end, 1);
                int res = query(1, 1, N + 1, 1, N + 1);
                return res;
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
