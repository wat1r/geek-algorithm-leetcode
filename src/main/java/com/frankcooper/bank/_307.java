package com.frankcooper.bank;

/**
 * Created by FrankCooper
 * Date 2020/8/22 18:09
 * Description
 */
public class _307 {
    static _307 handler = new _307();


    public static void main(String[] args) {
        handler.testSegmentTree();
    }

    private void testSegmentTree() {
        int MAX_SIZE = 1000;
        SegmentTree segmentTree = new SegmentTree();
        int[] arr = {1, 3, 5, 7, 9, 11};
        int size = arr.length;
        int[] tree = new int[MAX_SIZE];

        segmentTree.buildTree(arr, tree, 0, 0, size - 1);
//        handler.testSegmentTree();
        for (int i = 0; i < 15; i++) {
            System.out.println(String.format("tree[%d]=%d", i, tree[i]));
        }
        System.out.println("-------------------------");
        segmentTree.updateTree(arr, tree, 0, 0, size - 1, 4, 6);
        for (int i = 0; i < 15; i++) {
            System.out.println(String.format("tree[%d]=%d", i, tree[i]));
        }
        System.out.println("-------------------------");
        int res = segmentTree.queryTree(arr, tree, 0, 0, size - 1, 2, 5);
        System.out.println(res);

    }


    class SegmentTree extends _307 {


        public void update() {

        }

        /**
         * @param arr   原始数组
         * @param tree  要生成的线段树数组
         * @param node  当前线段树节点的下标索引
         * @param start arr区间的开始下标索引
         * @param end   arr区间的结束下标索引
         */
        public void buildTree(int[] arr, int[] tree, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = (start + end) / 2;
            int leftNode = 2 * node + 1;//当前node左孩子节点
            int rightNode = 2 * node + 2;//当前node右孩子节点
            buildTree(arr, tree, leftNode, start, mid);
            buildTree(arr, tree, rightNode, mid + 1, end);
            tree[node] = tree[leftNode] + tree[rightNode];

        }


        /**
         * @param arr   原始数组
         * @param tree  要生成的线段树数组
         * @param node  当前线段树节点的下标索引
         * @param start arr区间的开始下标索引
         * @param end   arr区间的结束下标索引
         * @param idx   要修改的arr的下标索引
         * @param val   要修改的arr的下标索引的目标值
         */
        public void updateTree(int[] arr, int[] tree, int node, int start, int end, int idx, int val) {
            if (start == end) {
                arr[idx] = val;
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            int leftNode = node * 2 + 1;
            int rightNode = node * 2 + 2;
            if (idx >= start && idx <= mid) {
                updateTree(arr, tree, leftNode, start, mid, idx, val);
            } else if (idx > mid && idx <= end) {
                updateTree(arr, tree, rightNode, mid + 1, end, idx, val);
            }
            tree[node] = tree[leftNode] + tree[rightNode];
        }

        /**
         * @param arr   原始数组
         * @param tree  要生成的线段树数组
         * @param node  当前线段树节点的下标索引
         * @param start arr区间的开始下标索引
         * @param end   arr区间的结束下标索引
         * @param L     要计算的arr的区间[L,R]的左闭区间索引
         * @param R     要计算的arr的区间[L,R]的右闭区间索引
         */
        public int queryTree(int[] arr, int[] tree, int node, int start, int end, int L, int R) {
            System.out.println(String.format("start:%d,end:%d", start, end));
            if (R < start || L > end) return 0;
            if (start >= L && end <= R) return tree[node];
            if (start == end) return tree[node];
            int mid = (start + end) / 2;
            int leftNode = node * 2 + 1;
            int rightNode = node * 2 + 2;
            int sumLeft = queryTree(arr, tree, leftNode, start, mid, L, R);
            int sumRight = queryTree(arr, tree, rightNode, mid + 1, end, L, R);
            return sumLeft + sumRight;
        }
    }

    class NumArray {

        public NumArray(int[] nums) {

        }

        public void update(int i, int val) {

        }

        public int sumRange(int i, int j) {

        }
    }
}
