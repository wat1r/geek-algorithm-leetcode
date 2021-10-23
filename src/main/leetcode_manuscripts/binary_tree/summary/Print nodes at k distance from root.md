## Print nodes at k distance from root（打印从根节点出发的距离为K的节点）

### Q:

给定树的根节点和整数k。打印距离根节点k处的所有节点。例如，在下面的树中，4、5和8与根节点的距离为2。

```java
            1
          /   \
        2      3
      /  \    /
    4     5  8 
```

### Code:

```java
 static class _1st_6 {
        public static void main(String[] args) {
            _1st_6 handler = new _1st_6();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,8,null]");
            int K = 2;
            handler.printKDistant(root, K);

        }

        public void printKDistant(TreeNode root, int K) {
            if (root == null || K < 0) return;//出口条件
            if (K == 0) {
              //打印结果集
                System.out.printf("%d ", root.val);
                return;
            }
          //递归左右子树，K值-1
            printKDistant(root.left, K - 1);
            printKDistant(root.right, K - 1);
        }
    }
//output
//4,5,8
```



