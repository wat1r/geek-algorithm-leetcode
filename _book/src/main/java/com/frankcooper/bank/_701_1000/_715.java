package com.frankcooper.bank._901_1000;

import java.util.*;

public class _715 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            //["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
            //[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
            //输出
            //[null, null, null, true, false, true]
            String[] ops = {"RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"};
            int[][] vals = {{}, {10, 20}, {14, 16}, {10, 14}, {13, 15}, {16, 17}};
//            ops = new String[]{"RangeModule", "addRange", "addRange", "addRange", "addRange"};
//            vals = new int[][]{{}, {10, 20}, {32, 40}, {20, 30}, {35, 50}};
            RangeModule rm = new RangeModule();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("RangeModule")) {
                    rm = new RangeModule();
                } else if (op.equals("addRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    rm.addRange(left, right);
                } else if (op.equals("removeRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    rm.removeRange(left, right);
                } else if (op.equals("queryRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    boolean f = rm.queryRange(left, right);
                }
            }

        }


        static class RangeModule {

            TreeMap<Integer, Integer> map;

            public RangeModule() {
                map = new TreeMap<>();
            }

            public void addRange(int left, int right) {
                if (right <= left) return;
                //返回小于等于key的第一个键
                Integer start = map.floorKey(left);
                Integer end = map.floorKey(right);
                if (start == null && end == null) {
                    map.put(left, right);
                } else if (start != null && map.get(start) >= left) {
                    Integer val = Math.max(map.get(end), Math.max(map.get(start), right));
                    map.put(start, val);
                } else {
                    Integer val = Math.max(map.get(end), right);
                    map.put(left, val);
                }
                //(left,right] 左开右闭
                Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
                Set<Integer> set = new HashSet<>(subMap.keySet());
                map.keySet().removeAll(set);
            }

            public boolean queryRange(int left, int right) {
                Integer start = map.floorKey(left);
                if (start == null) {
                    return false;
                }
                return map.get(start) >= right;
            }

            public void removeRange(int left, int right) {
                if (right <= left) return;
                Integer start = map.floorKey(left);
                Integer end = map.floorKey(right);
                if (end != null && map.get(end) > right) {
                    map.put(right, map.get(end));
                }
                if (start != null && map.get(start) > left) {
                    map.put(start, left);
                }
                Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
                Set<Integer> set = new HashSet<>(subMap.keySet());
                map.keySet().removeAll(set);
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            //["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
            //[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
            //输出
            //[null, null, null, true, false, true]
            String[] ops = {"RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"};
            int[][] vals = {{}, {10, 20}, {14, 16}, {10, 14}, {13, 15}, {16, 17}};
            ops = new String[]{"RangeModule", "addRange", "addRange", "addRange", "addRange"};
            vals = new int[][]{{}, {10, 20}, {32, 40}, {20, 30}, {35, 50}};
            RangeModule rm = new RangeModule();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("RangeModule")) {
                    rm = new RangeModule();
                } else if (op.equals("addRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    rm.addRange(left, right);
                } else if (op.equals("removeRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    rm.removeRange(left, right);
                } else if (op.equals("queryRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    boolean f = rm.queryRange(left, right);
                }
            }

        }

        static class RangeModule {

            //    返回小于等于key的第一个键：
            //    K floorKey(K key);
            //    返回大于或者等于key的第一个键：
            //    K ceilingKey(K key);

            //k:left v:right range的左右边界
            TreeMap<Integer, Integer> map;

            public RangeModule() {
                map = new TreeMap<>();
            }

            public void addRange(int left, int right) {
                if (left >= right) return;
                Integer start = map.floorKey(left);
                if (start == null) start = map.ceilingKey(left);
                while (start != null && start <= right) {
                    int end = map.get(start);
                    if (end >= left) {//存在overlap
                        map.remove(start);
                        if (start < left) left = start;
                        if (end > right) right = end;
                    }
                    start = map.ceilingKey(end);
                }
                map.put(left, right);
            }

            public boolean queryRange(int left, int right) {
                Integer start = map.floorKey(left);
                if (start == null) {
                    return false;
                }
                return map.get(start) >= right;
            }

            public void removeRange(int left, int right) {
                if (left >= right) return;
                Integer start = map.floorKey(left);
                if (start == null) start = map.ceilingKey(left);
                while (start != null && start < right) {
                    int end = map.get(start);
                    if (end >= left) {
                        map.remove(start);
                        if (start < left) map.put(start, left);
                        if (end > right) map.put(right, end);
                    }
                    start = map.ceilingKey(end);
                }
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        class RangeModule {
            TreeMap<Integer, Boolean> map;

            public RangeModule() {
                map = new TreeMap<>();
                map.put(0, false);
            }

            public void addRange(int left, int right) {
                boolean from = map.get(map.lowerKey(left));
                boolean to = map.get(map.floorKey(right));
                map.subMap(left, true, right, true).clear();
                if (!from) map.put(left, true);
                if (!to) map.put(right, false);
            }

            public boolean queryRange(int left, int right) {
                int lower = map.lowerKey(right);
                return lower <= left && map.get(lower);
            }

            public void removeRange(int left, int right) {
                boolean from = map.get(map.lowerKey(left));
                boolean to = map.get(map.floorKey(right));
                map.subMap(left, true, right, true).clear();
                if (from) map.put(left, false);
                if (to) map.put(right, true);
            }
        }
    }

    //segment_tree
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class RangeModule {

            SegmentNode root;
            int max = (int) Math.pow(10, 9);


            public RangeModule() {
                root = new SegmentNode(0, max, false);
            }

            public void addRange(int l, int r) {
                update(root, l, r, true);
            }


            private boolean update(SegmentNode node, int l, int r, boolean state) {
                if (l <= node.l && node.r <= r) {
                    node.state = state;
                    node.left = null;
                    node.right = null;
                    return node.state;
                }
                if (l >= node.r || r <= node.l) return node.state;
                int mid = node.l + (node.r - node.l) / 2;
                if (node.left == null) {
                    node.left = new SegmentNode(node.l, mid, node.state);
                    node.right = new SegmentNode(mid, node.r, node.state);
                }
                boolean lflag = update(node.left, l, r, state);
                boolean rflag = update(node.right, l, r, state);
                node.state = lflag && rflag;
                return node.state;
            }

            public boolean queryRange(int l, int r) {
                return query(root, l, r);
            }

            private boolean query(SegmentNode node, int l, int r) {
                if (l >= node.r || r <= node.l) return true;
                if ((l <= node.l && r >= node.r) || node.left == null) return node.state;
                int mid = node.l + (node.r - node.l) / 2;
                if (r <= mid) {
                    return query(node.left, l, r);
                } else if (l >= mid) {
                    return query(node.right, l, r);
                } else {
                    return query(node.left, l, r) && query(node.right, l, r);
                }
            }


            public void removeRange(int l, int r) {
                update(root, l, r, false);
            }


        }


        class SegmentNode {
            public int l;
            public int r;
            public boolean state;
            public SegmentNode left;
            public SegmentNode right;

            public SegmentNode(int l, int r, boolean state) {
                this.l = l;
                this.r = r;
                this.state = state;
            }
        }

    }
}
