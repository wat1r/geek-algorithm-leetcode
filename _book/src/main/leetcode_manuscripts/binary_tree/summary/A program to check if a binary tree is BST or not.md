## A program to check if a binary tree is BST or not(判断一棵树是否是BST)



### Q:

- 二叉搜索树（BST）是一种基于节点的二叉树数据结构，具有以下特性:
  - 节点的左子树仅包含小于节点键值的节点。
  - 节点的右子树仅包含大于节点键值的节点。
  - 左子树和右子树也必须是BST树。

- 根据上述特性，自然得出以下结论：
  - 每个节点（树上的节点）都有一个不同的键值。

![image-20211022182930362](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211022182930362.png)



### 方法1(错误的)：

- 下面是一个简单的程序。对于每个节点，检查其左侧节点是否小于该节点，右侧节点是否大于该节点。

```java
        public boolean isBST(TreeNode node) {
            if (node == null) return true;//为空
            //左节点比当前节点值大，右节点比当前节点值小，不是BST，返回false
            if (node.left != null && node.left.val > node.val) return false;
            if (node.right != null && node.right.val < node.val) return false;
            //如果node节点的左右子树都不是BST，返回false，不是BST
            if (!isBST(node.left) || !isBST(node.right)) return false;
            return true;
        }
```

- 下面的二叉树执行上面的代码会返回true，但下面的并不是一棵BST，因为2的右节点4在3的左子树上，而事实应该是在右子树上

![image-20211022183042802](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211022183042802.png)

### 方法2(不高效):

- 需要有一个maxVaule和minValue的辅助函数

```java
        public boolean isBST_II(TreeNode node) {
            if (node == null) return true;//为空
            //左节点下的最大值比当前节点值大，右节点下的最小值比当前节点值小，不是BST，返回false
            if (node.left != null && maxValue(node.left) > node.val) return false;
            if (node.right != null && minValue(node.right) < node.val) return false;
            //如果node节点的左右子树都不是BST，返回false，不是BST
            if (!isBST_II(node.left) || !isBST_II(node.right)) return false;
            return true;
        }
```



### 方法3(高效)：

- 上面的方法2运行效率不高，因为它多次遍历树的某些部分。更好的解决方案是只查看每个节点一次。关键点是编写一个实用工具帮助函数isBSTUtil（struct node*node，int min，int max），该函数沿树向下遍历，跟踪最小值和最大值，只查看每个节点一次。最小值和最大值的初始值应该是INT_最小值和INT_最大值-它们从那里开始变窄。

```java
        public boolean isBST_III(TreeNode node) {
            return isBSTUtil(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean isBSTUtil(TreeNode node, int minn, int maxx) {
            if (node == null) return true;
            if (node.val < minn || node.val > maxx) return false;
            return isBSTUtil(node.left, minn, node.val - 1)
                    && isBSTUtil(node.right, node.val + 1, maxx);

        }
```





### 方法4(中序遍历)：

- 1.按顺序遍历给定的树并将结果存储在临时数组中。

- 2.检查临时数组是否按升序排序，如果是，则树为BST。

> **可以避免使用辅助数组。在进行顺序遍历时，我们可记录前一个访问的节点。如果当前访问的节点的值小于前一个节点，则树不是BST。**

```java
        TreeNode prev = null;

        public boolean isBST_IV(TreeNode node) {
            if (node == null) return true;
          //中序遍历 左子树
            if (!isBST_IV(node.left)) return false;
          //判断：前一个节点的值需要小于当前节点的值
            if (prev != null && node.val <= prev.val) return false;
            prev = node;//更新前一个节点的值
          //中序遍历 右子树
            if (!isBST_IV(node.right)) return false;
            return true;
        }
```



### Reference

- [A program to check if a binary tree is BST or not](https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/)

