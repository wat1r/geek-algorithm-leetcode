## Lowest Common Ancestor in a Binary Tree | Set 2 (Using Parent Pointer)（二叉搜索树的最近公共祖先节点（使用父节点））

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

### Algorithm：
1.创建一个空哈希表。
2.在哈希表中插入n1及其所有祖先。
3.检查哈希表中是否存在n2或其任何祖先，如果是，则返回第一个现有祖先。
 
 
```java
 public ParentTreeNode insert(ParentTreeNode node, int key) {
            /* If the tree is empty, return a new node */
            if (node == null) return new ParentTreeNode(key);
            /* Otherwise, recur down the tree */
            if (key < node.key) {
                node.left = insert(node.left, key);
                node.left.parent = node;
            } else if (key > node.key) {
                node.right = insert(node.right, key);
                node.right.parent = node;
            }
            /* return the (unchanged) node pointer */
            return node;
        }


        public ParentTreeNode lca(ParentTreeNode node1, ParentTreeNode node2) {
            Map<ParentTreeNode, Boolean> ancestors = new HashMap<>();
            while (node1 != null) {
                ancestors.put(node1, true);
                node1 = node1.parent;
            }
            while (node2 != null) {
                if (ancestors.containsKey(node2)) {
                    return node2;
                }
                node2 = node2.parent;
            }
            return null;
        }
```











### Reference

- [Lowest Common Ancestor in a Binary Tree | Set 2 (Using Parent Pointer)](https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-tree-set-2-using-parent-pointer/)

  

