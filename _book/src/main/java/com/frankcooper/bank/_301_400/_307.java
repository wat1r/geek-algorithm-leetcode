package com.frankcooper.bank._301_400;

/**
 * Created by FrankCooper
 * Date 2020/8/22 18:09
 * Description
 */
public class _307 {
    static _307 handler = new _307();


    public static void main(String[] args) {
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

//    private void test_3rd() {
//
//        //[[0, 9, 5, 7, 3]]
//        NumArray numArray = new NumArray(new int[]{0, 9, 5, 7, 3});
//        String[] opertaion = new String[]{"sumRange", "sumRange", "sumRange", "update", "update", "update", "sumRange", "update", "sumRange", "update"};
//        int[][] values = new int[][]{{4, 4}, {2, 4}, {3, 3}, {4, 5}, {1, 7}, {0, 8}, {1, 2}, {1, 9}, {4, 4}, {3, 4}};
//        for (int i = 0; i < opertaion.length; i++) {
//            System.out.println(String.format("i:%d", i));
//            if (opertaion[i].equals("sumRange")) {
//                System.out.println(numArray.sumRange(values[i][0], values[i][1]));
//            } else if (opertaion[i].equals("update")) {
//                numArray.update(values[i][0], values[i][1]);
//            }
//            System.out.println("------------");
//            System.out.println(String.format("i:%d--->%s", i, opertaion[i]));
////            System.out.println();
//            System.out.println(JSON.toJSONString(numArray.root.val));
//            System.out.println("------------");
//        }
//    }


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


    class _2nd {
        class NumArray {

            int n;
            int[] tree;
            int[] arr;


            public NumArray(int[] nums) {
                n = nums.length;
                if (n == 0) return;
                arr = new int[n + 1];
                tree = new int[(int) Math.pow(2, n)];
                System.arraycopy(nums, 0, arr, 1, n);
                buildTree(arr, 1, 1, n);
            }

            /**
             * @param arr   源数组nums拷贝后生成的数组arr
             * @param node  tree的当前节点下标索引
             * @param start arr区间的开始下标索引
             * @param end   arr区间的结束下标索引
             */
            private void buildTree(int[] arr, int node, int start, int end) {
                if (start == end) {
                    tree[node] = arr[start];
                    return;
                }
                int mid = start + (end - start) / 2;
                int leftNode = 2 * node;
                int rightNode = 2 * node + 1;
                buildTree(arr, leftNode, start, mid);
                buildTree(arr, rightNode, mid + 1, end);
                tree[node] = tree[leftNode] + tree[rightNode];
            }


            /**
             * @param i   要修改的nums的下标索引
             * @param val 要修改成为的目标值
             */
            public void update(int i, int val) {
                //nums的下标索引与arr的下标索引相差1位
                updateTree(1, 1, n, i + 1, val);
            }


            /**
             * @param node  tree的当前节点下标索引
             * @param start arr区间的开始下标索引
             * @param end   arr区间的结束下标索引
             * @param i     要修改的arr的下标索引
             * @param val   要修改成为的目标值
             */
            private void updateTree(int node, int start, int end, int i, int val) {
                if (start == end) {
                    tree[node] = val;
                    arr[i] = val;
                    return;
                }
                int mid = start + (end - start) / 2;
                int leftNode = 2 * node, rightNode = 2 * node + 1;
                if (i >= start && i <= mid) {
                    updateTree(leftNode, start, mid, i, val);
                } else {
                    updateTree(rightNode, mid + 1, end, i, val);
                }
                tree[node] = tree[leftNode] + tree[rightNode];
            }

            public int sumRange(int i, int j) {
                //nums的下标索引与arr的下标索引相差1位
                return queryTree(1, 1, n, i + 1, j + 1);
            }

            /**
             * @param node  tree的当前节点下标索引
             * @param start arr区间的开始下标索引
             * @param end   arr区间的结束下标索引
             * @param i     要查找的arr区间左闭下标索引
             * @param j     要查找的arr区间右闭下标索引
             * @return
             */
            private int queryTree(int node, int start, int end, int i, int j) {
                if (start > j || end < i) return 0;
                if (start >= i && end <= j) return tree[node];
                int mid = start + (end - start) / 2;
                int leftNode = 2 * node;
                int rightNode = 2 * node + 1;
                int sumLeft = queryTree(leftNode, start, mid, i, j);
                int sumRight = queryTree(rightNode, mid + 1, end, i, j);
                return sumLeft + sumRight;
            }
        }
    }


    class _3rd {
        class NumArray {

            class TreeNode {
                int val;
                int start;
                int end;
                TreeNode left;
                TreeNode right;

                public TreeNode(int start, int end) {
                    this.start = start;
                    this.end = end;
                }
            }

            TreeNode root = null;


            private TreeNode buildTree(int[] nums, int start, int end) {
                if (start > end) return null;
                TreeNode curr = new TreeNode(start, end);
                if (start == end) curr.val = nums[start];
                else {
                    int mid = start + (end - start) / 2;
                    curr.left = buildTree(nums, start, mid);
                    curr.right = buildTree(nums, mid + 1, end);
                    curr.val = curr.left.val + curr.right.val;
                }
                return curr;
            }


            public NumArray(int[] nums) {
                root = buildTree(nums, 0, nums.length - 1);
            }

            public void update(int i, int val) {
                updateTree(root, i, val);
            }

            public void updateTree(TreeNode node, int i, int val) {
                if (node.start == node.end) {
                    node.val = val;
                } else {
                    int mid = node.start + (node.end - node.start) / 2;
                    if (i <= mid) updateTree(node.left, i, val);
                    else updateTree(node.right, i, val);
                    node.val = node.left.val + node.right.val;
                }
            }

            public int sumRange(int i, int j) {
                return queryTree(root, i, j);
            }

            public int queryTree(TreeNode node, int i, int j) {
                System.out.println(String.format("i:%d,j:%d", i, j));
                System.out.println(node);
                System.out.println(String.format("Node.val:%d", node.val));
                if (node.start == i && node.end == j) return node.val;
                else {
                    int mid = node.start + (node.end - node.start) / 2;
                    if (j <= mid) {
                        return queryTree(node.left, i, j);
                    } else if (i >= mid + 1) {
                        return queryTree(node.right, i, j);
                    } else {
                        return queryTree(node.left, i, mid) + queryTree(node.right, mid + 1, j);
                    }
                }
            }
        }

    }


    static class _4th {

        class NumArray {

            int n;
            int[] tree;


            public NumArray(int[] nums) {
                int n = nums.length;
                tree = new int[2 * n];
                buildTree(nums);
            }

            private void buildTree(int[] nums) {
                //构造tree的n - 2n-1部分
                for (int i = n, j = 0; i < 2 * n; i++, j++) {
                    tree[i] = nums[j];
                }
                //构造tree的1-n-1部分
                for (int i = n - 1; i > 0; i--) {
                    tree[i] = tree[i * 2] + tree[i * 2 + 1];
                }
            }

            public void update(int i, int val) {
                i += n;//nums的索引与tree的索引相差n
                tree[i] = val;
                while (i > 0) {
                    int left = i;
                    int right = i;
                    if (i % 2 == 0) right = i + 1;//i为左孩子
                    else left = i - 1;//i为右孩子
                    tree[i / 2] = tree[left] + tree[right];
                    i /= 2;
                }
            }

            public int sumRange(int i, int j) {
                //nums的索引与tree的索引相差n
                i += n;
                j += n;
                int sum = 0;
                while (i <= j) {
                    //目的是维持[i,j]我左右孩子，或者一个节点本身
                    if (i % 2 == 1) {//i为右孩子
                        sum += tree[i];
                        i++;
                    }
                    if (j % 2 == 0) {//j为左孩子
                        sum += tree[j];
                        j--;
                    }
                    i /= 2;
                    j /= 2;
                }
                return sum;
            }
        }
    }


    //树状数组 BinaryIndexedTree
    static class _5th {

        class NumArray {

            int[] tree;
            int n;
            int[] nums;

            public NumArray(int[] nums) {
                n = nums.length;
                tree = new int[n + 1];
                this.nums = nums;
                for (int i = 0; i < n; i++) add(i + 1, nums[i]);

            }

            public void update(int index, int val) {
                // 原有的值是 nums[i]，要使得修改为 val，需要增加 val - nums[i]
                add(index + 1, val - nums[index]);
                nums[index] = val;
            }

            public int sumRange(int left, int right) {
                return query(right + 1) - query(left);
            }


            private int query(int x) {
                int sum = 0;
                for (int i = x; i > 0; i -= lowbit(i)) sum += tree[i];
                return sum;
            }


            private void add(int x, int v) {
                for (int i = x; i <= n; i += lowbit(i)) {
                    tree[i] += v;
                }
            }


            private int lowbit(int x) {
                return -x & x;
            }


        }

    }

    static class _6th {
        public static void main(String[] args) {

        }


        class NumArray {

            int[] tree;
            int n;


            public NumArray(int[] nums) {
                n = nums.length;
                tree = new int[n * 4];
                build(nums, 0, 0, n - 1);
            }


            public void update(int index, int val) {
                update(0, 0, n - 1, index, val);
            }

            public int sumRange(int L, int R) {
                return query(0, 0, n - 1, L, R);
            }


            private void build(int[] nums, int node, int start, int end) {
                if (start == end) {
                    tree[node] = nums[start];
                    return;
                }
                int mid = start + (end - start) / 2;
                int left_node = node * 2 + 1;
                int right_node = node * 2 + 2;
                build(nums, left_node, start, mid);
                build(nums, right_node, mid + 1, end);
                tree[node] = tree[left_node] + tree[right_node];
            }

            private void update(int node, int start, int end, int idx, int val) {
                if (start == end) {
                    tree[node] = val;
                    return;
                }
                int mid = start + (end - start) / 2;
                int left_node = node * 2 + 1;
                int right_node = node * 2 + 2;
                if (idx <= mid) {
                    update(left_node, start, mid, idx, val);
                } else {
                    update(right_node, mid + 1, end, idx, val);
                }
                tree[node] = tree[left_node] + tree[right_node];
            }

            private int query(int node, int start, int end, int L, int R) {

                if (R < start || L > end) {
                    return 0;
                } else if (L <= start && end <= R) {
                    return tree[node];
                } else if (start == end) {
                    return tree[node];
                }
                int mid = start + (end - start) / 2;
                int left_node = node * 2 + 1;
                int right_node = node * 2 + 2;
                int sum_left = query(left_node, start, mid, L, R);
                int sum_right = query(right_node, mid + 1, end, L, R);
                return sum_left + sum_right;
            }
        }

    }

    static class _7th {

        class NumArray {
            Node[] tr;

            class Node {
                int l, r, v;

                public Node(int l, int r) {
                    this.l = l;
                    this.r = r;
                }
            }


            void pushup(int u) {
                tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
            }

            void build(int u, int l, int r) {
                tr[u] = new Node(l, r);
                if (l == r) return;
                int mid = l + r >> 1;
                build(u << 1, l, mid);
                build(u << 1 | 1, mid + 1, r);
            }

            void update(int u, int index, int value) {
                if (tr[u].l == index && tr[u].r == index) {
                    tr[u].v += value;
                    return;
                }
                int mid = tr[u].l + tr[u].r >> 1;
                if (index <= mid) update(u << 1, index, value);
                else update(u << 1 | 1, index, value);
                pushup(u);
            }

            int query(int u, int L, int R) {
                if (L <= tr[u].l && tr[u].r <= R) return tr[u].v;
                int mid = tr[u].l + tr[u].r >> 1;
                int res = 0;
                if (L <= mid) res += query(u << 1, L, R);
                if (R > mid) res += query(u << 1 | 1, L, R);
                return res;
            }

            int[] nums;

            public NumArray(int[] nums) {
                int N = nums.length;
                this.nums = nums;
                tr = new Node[4 * N];
                build(1, 1, N);
                for (int i = 0; i < N; i++) {
                    update(1, i + 1, nums[i]);
                }
            }

            public void update(int index, int val) {
                update(1, index + 1, val - nums[index]);
                nums[index] = val;
            }

            public int sumRange(int left, int right) {
                return query(1, left + 1, right + 1);
            }
        }
    }

}
