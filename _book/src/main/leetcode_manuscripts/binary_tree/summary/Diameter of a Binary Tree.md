## Diameter of a Binary Tree(二叉树的直径)

树的直径（有时称为宽度）是两个端点节点之间最长路径上的节点数。下图显示了两棵树，每棵树的直径为9，形成最长路径，末端的叶子为阴影（请注意，长度为9的每棵树中有多条路径，但路径长度不超过9个节点）。

![image-20211022100742801](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\summary\Diameter of a Binary Tree.assets\image-20211022100742801.png)

- 树的直径T是以下属性中的最大值：
  - T的左子树的直径。
  - T的右子树的直径。
  - 经过T的根节点的叶节点间最长路径（可根据T子树的高度计算）

### Code:

