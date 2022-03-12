## 【重温经典】二叉搜索树中第K小的元素

这是一道求kth的问题

题目描述：给定一个二叉搜索树的根节点 `root` ，和一个整数 `k` ，请你设计一个算法查找其中第 `k` 个最小元素（从 1 开始计数）。

![image-20210830192316014](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210830192316014.png)

![image-20210830192327619](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210830192327619.png)



### 方法1：DFS

```java
        int res = -1;
        int k;

        public int kthSmallest(TreeNode root, int k) {
            this.k = k;
            dfs(root);
            return res;
        }

        private void dfs(TreeNode root) {
            if (root.left != null) dfs(root.left);
            if (--k == 0) {
                res = root.val;
                return;
            }
            if (root.right != null) dfs(root.right);
        }
```

### 方法2：DFS+压栈

```java
public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> stk = new Stack<>();
    int cnt = 0;
    while (!stk.isEmpty() || root != null) {
        if (root != null) {
            stk.push(root);
            root = root.left;
        } else {
            TreeNode cur = stk.pop();
            if (++cnt == k) return cur.val;
            root = cur.right;
        }
    }
    return -1;
}
```

