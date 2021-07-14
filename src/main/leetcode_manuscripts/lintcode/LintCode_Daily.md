# LintCode_Daily 

## binary_tree

### [68 · 二叉树的后序遍历](https://www.lintcode.com/problem/68/)

#### 方法1：DFS

```java
List<Integer> res = new ArrayList<>();

public List<Integer> postorderTraversal(TreeNode root) {
    dfs(root);
    return res;
}

private void dfs(TreeNode root) {
    if(root ==null) return;
    dfs(root.left);
    dfs(root.right);
    res.add(root.val);
}
```



