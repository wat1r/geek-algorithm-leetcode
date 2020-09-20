## 数据结构设计之线段树[White Rhinoceros]

![timg (2)](C:\Users\FrankCooper\Desktop\timg (2).jpg)



### 方法0：种子



![script](C:\Users\FrankCooper\Downloads\script.png)

> 上图中right修改成 right=2*node+2

![1598099267094](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1598099267094.png)

下面为在做$queryTree$时做加速

```
 if (start >= L && end <= R) return tree[node];
```

- 未写上述代码的情况：

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

- 加上后：

```
start:0,end:5
start:0,end:2
start:0,end:1
start:2,end:2
start:3,end:5
```



#### 完整代码

```java
/**
         * @param arr   原始数组
         * @param tree  要生成的线段树数组
         * @param Node  当前线段树节点的下标索引
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
         * @param Node  当前线段树节点的下标索引
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
         * @param Node  当前线段树节点的下标索引
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

### 方法1:构造树

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
                tree = new int[(int) Math.pow(2, n)];//初始化大小
                System.arraycopy(nums, 0, arr, 1, n);//将nums的0-n-1拷贝给arr，1-n
                buildTree(arr, 1, 1, n);
            }

            /**
             * @param arr   源数组nums拷贝后生成的数组arr
             * @param node  tree的当前节点下标索引
             * @param start arr区间的开始下标索引
             * @param end   arr区间的结束下标索引
             */
            private void buildTree(int[] arr, int node, int start, int end) {
                if (start == end) {//区间只有一个元素，开始赋值返回
                    tree[node] = arr[start];
                    return;
                }
                int mid = start + (end - start) / 2;//中点
                int leftNode = 2 * node;//左孩子节点索引
                int rightNode = 2 * node + 1;//右孩子节点索引
                buildTree(arr, leftNode, start, mid);//构造左孩子树
                buildTree(arr, rightNode, mid + 1, end);//构造右孩子树
                tree[node] = tree[leftNode] + tree[rightNode];//当前节点的值等于左右孩子树之和
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

> 上面两个方法存在的问题：图中有大量的的虚线矿标识出来的废弃节点，其占用内存空间，但是实际中用不到这些，为了占索引的位置

### 方法2:优化构造树

#### 思路

- 创建一个$TreeNode$节点，存储如下信息
  - $start$节点计算的左右区间的左边界
  - $end$节点计算的左右区间的右边界
  - $val$节点在左右区间的$sum$值
  - $left$节点的左孩子
  - $right$节点的右孩子
- $buildTree$
  - 构造树，判断$start$与 $end$值，如果相等，创建节点返回，不相等，分别创建左孩子树和右孩子树 

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



### 方法3:数组构造树

![1598173573416](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1598173573416.png)

```java
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
```

#### 复杂度分析

#### $queryTree$

**时间复杂度**：$O(logn)$。因为在算法的每次迭代中，我们会向上移动一层，要么移动到当前节点的父节点，要么移动到父节点的左侧或者右侧的兄弟节点，直到两个边界相交为止。在最坏的情况下，这种情况会在算法进行$log(n)$ 次迭代后发生在根节点。

**空间复杂度**：$O(1)$

#### $updateTree$

**时间复杂度**：$O(logn)$。算法的时间复杂度为$O(logn)$，因为有几个树节点的范围包括第$i$ 个数组元素，每个层上都有一个。共有 $log(n)$ 层
**空间复杂度**：$O(1)$





### Reference

- 视频材料
- 力扣官方题解