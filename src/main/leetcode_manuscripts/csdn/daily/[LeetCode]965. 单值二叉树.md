

## [LeetCode]965. 单值二叉树



## 题目

[965. 单值二叉树](https://leetcode.cn/problems/univalued-binary-tree/)

```java
965. 单值二叉树
如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。

只有给定的树是单值二叉树时，才返回 true；否则返回 false。

 

示例 1：



输入：[1,1,1,1,1,null,1]
输出：true
示例 2：



输入：[2,2,2,5,2]
输出：false
 

提示：

给定树的节点数范围是 [1, 100]。
每个节点的值都是整数，范围为 [0, 99] 。
```

## 解法

### 方法1：递归

```java
public boolean isUnivalTree(TreeNode root) {
    if (root == null) return false;
    return dfs(root, root.val);
}

private boolean dfs(TreeNode root, int val) {
    if (root == null) return true;
    if (root.val != val) return false;
    return dfs(root.left, val) && dfs(root.right, val);
}
```

- 不带`helper`函数：

```java
public boolean isUnivalTree(TreeNode root) {
    if (root == null) return true;
    if (root.left != null && root.left.val != root.val) return false;
    if (root.right != null && root.right.val != root.val) return false;
    return isUnivalTree(root.left) && isUnivalTree(root.right);
}
```

### 方法2：迭代

```java
        public boolean isUnivalTree(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur.val != root.val) return false;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            return true;
        }
```



