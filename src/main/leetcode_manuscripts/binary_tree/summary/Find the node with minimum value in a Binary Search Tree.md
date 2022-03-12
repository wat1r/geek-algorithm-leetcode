## Find the node with minimum value in a Binary Search Tree(二叉搜索树的最小值)

### Q：

- 找到BST中的最小值

### Code:

```java
    public int minValue(TreeNode node) {
            TreeNode cur = node;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.val;
        }
```

