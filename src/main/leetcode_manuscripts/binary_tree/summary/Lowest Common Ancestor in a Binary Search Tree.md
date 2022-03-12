## Lowest Common Ancestor in a Binary Search Tree(二叉搜索树的最近公共祖先节点)

![image-20211025194214158](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211025194214158.png)

```java
Input: LCA of 10 and 14
Output:  12
Explanation: 12 is the closest node to both 10 and 14 
which is a ancestor of both the nodes.

Input: LCA of 8 and 14
Output:  8
Explanation: 8 is the closest node to both 8 and 14 
which is a ancestor of both the nodes.

Input: LCA of 10 and 22
Output:  20
Explanation: 20 is the closest node to both 10 and 22 
which is a ancestor of both the nodes.
```

### LCA的定义

一棵有根的树T。两个节点n1和n2之间的最低共同祖先被定义为T中具有n1和n2作为后代的最低节点（允许一个节点是其自身的后代）。
T中n1和n2的LCA是距离根最远的n1和n2的共同祖先。例如，作为确定树中节点对之间距离的过程的一部分，计算最低共同祖先可能是有用的：从n1到n2的距离可以计算为从根到n1的距离，加上从根到n2的距离，减去从根到其最低共同祖先的距离的两倍。 

### Approach：

对于BST，当从上到下遍历树时，位于两个数字n1和n2之间的第一个节点是LCA，即位于n1和n2（n1<=n<=n2）之间的具有最低深度的第一个节点n。所以只要递归地遍历BST，如果节点的值大于n1和n2，那么LCA位于节点的左侧，如果它小于n1和n2，那么LCA位于右侧。否则，根为LCA（假设BST中同时存在n1和n2）。

### Algorithm：

1.创建一个递归函数，该函数接受一个节点和两个值n1和n2。

2.如果当前节点的值小于n1和n2，则LCA位于右侧子树中。调用右子树的递归函数。

3.如果当前节点的值大于n1和n2，则LCA位于左子树中。调用左子树的递归函数。

4.如果上述两种情况均为false，则将当前节点作为LCA返回。

```java
   static class _2nd_3 {
        public static void main(String[] args) {
            _2nd_3 handler = new _2nd_3();
            TreeNode root = TreeNodeIOUtils.transform("[20,8,22,4,12,null,null,null,null,10,14]");
            int n1 = 10, n2 = 14;
            TreeNode res1 = handler.lca(root, n1, n2);
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + res1.val);

            n1 = 14;
            n2 = 8;
            res1 = handler.lca(root, n1, n2);
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + res1.val);

            n1 = 10;
            n2 = 22;
            res1 = handler.lca(root, n1, n2);
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + res1.val);
        }


        public TreeNode lca(TreeNode root, int n1, int n2) {
            if (root == null) return null;
            if (root.val > n1 && root.val > n2) return lca(root.left, n1, n2);
            if (root.val < n1 && root.val < n2) return lca(root.right, n1, n2);
            return root;
        }

    }


//output
│   ┌── 22
└── 20
    │       ┌── 14
    │   ┌── 12
    │   │   └── 10
    └── 8
        └── 4
LCA of 10 and 14 is 12
LCA of 14 and 8 is 8
LCA of 10 and 22 is 20
```

### 复杂度分析：

- 时间复杂度：O(h)，h为树的高度
- 空间复杂度：O(h)，如果忽略递归堆栈空间，则上述解决方案的空间复杂度是常数级别。

**迭代实现**：上述解决方案使用递归。递归解决方案需要函数调用堆栈形式的额外空间。因此，可以实现一个迭代解决方案，它不会以函数调用堆栈的形式占用空间。

```java
       public TreeNode lca_iterate(TreeNode root, int n1, int n2) {
            while (root != null) {
                if (root.val > n1 && root.val > n2) root = root.left;
                else if (root.val < n1 && root.val < n2) root = root.right;
                else break;
            }
            return root;
        }
```

### 复杂度分析：

- 时间复杂度：O(h)，h为树的高度
- 空间复杂度：O(1)，间复杂度是常数级别。



### Reference

- [Lowest Common Ancestor in a Binary Search Tree](https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/)

