## 数据结构设计之线段树[White Rhinoceros]

![script](C:\Users\FrankCooper\Downloads\script.png)





![1598099267094](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1598099267094.png)





```
start:0,end:5
start:0,end:2
start:0,end:1
start:2,end:2
start:3,end:5
start:3,end:4
start:3,end:3
start:4,end:4
start:5,end:5
```

![1598099791454](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1598099791454.png)

```
start:0,end:5
start:0,end:2
start:0,end:1
start:2,end:2
start:3,end:5
```

### 元方法

```java
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
```

![1598142386162](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1598142386162.png)

#### 方法1:构造树

> 10个case在通过第9个的时候，报超出内存限制

```java
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
```







```java
class NumArray {

            class TreeNode {
                int val;
                int start;
                int end;
                TreeNode left;
                TreeNode right;

                public TreeNode(int start, int end) {
                    left = null;
                    right = null;
                    this.start = start;
                    this.end = end;
                }
            }


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

            TreeNode root = null;

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
                            System.out.println(node);
                if (node.start == i && node.end == j) return node.val;
                else {
                    int mid = node.start + (node.end - node.start) / 2;
                    if (j <= mid) {
                        return queryTree(node.left, i, j);
                    } else if (i >= (mid + 1)) {
                        return queryTree(node.right, i, j);
                    } else {
                        return queryTree(node.left, i, mid) + queryTree(node.right, mid + 1, j);
                    }
                }
            }
        }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
```















