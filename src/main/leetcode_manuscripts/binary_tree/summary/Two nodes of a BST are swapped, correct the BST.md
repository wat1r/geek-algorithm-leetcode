## Two nodes of a BST are swapped, correct the BST（恢复两个节点被交换的BST）

### Q:

BST的两个节点被交换了，修复它：

```java
Input Tree:
         10
        /  \
       5    8
      / \
     2   20

In the above tree, nodes 20 and 8 must be swapped to fix the tree.  
Following is the output tree
         10
        /  \
       5    20
      / \
     2   8
```

### Solution:

BST的按中序遍历生成排序数组。所以，一个简单的方法是将输入树的中序遍历结果存储在一个辅助数组中。对辅助数组进行排序。最后，将辅助数组元素插入BST，保持BST的结构不变。该方法的时间复杂度为O(nLogn)，所需辅助空间为O(n)。

可以在O（n）时间内解决这个问题，只需对给定的BST进行一次遍历。由于BST的顺序遍历始终是一个排序数组，因此该问题可以简化为一个排序数组的两个元素交换的问题。我们需要处理两种情况：

- 1.交换的节点在BST的排序顺序不相邻。

```java
For example, Nodes 5 and 25 are swapped in {3 5 7 8 10 15 20 25}. 
 The inorder traversal of the given tree is 3 25 7 8 10 15 20 5 
```

![image-20211023085108807](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211023085108807.png)

如果仔细观察，在中序遍历中，会发现节点7比之前访问的节点25小。这里保存节点25（上一个节点）的上下文。同样，我们发现节点5比前一个节点20小。这一次，我们保存节点5（当前节点）的上下文。最后，交换两个节点的值。

- 2.交换的节点在BST的中序遍历中是相邻的。

```java
For example, Nodes 7 and 8 are swapped in {3 5 7 8 10 15 20 25}. 
  The inorder traversal of the given tree is 3 5 8 7 10 15 20 25 
```

![image-20211023085436856](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211023085436856.png)

与case1不同，此处仅存在一个节点值小于前一个节点值的点。节点7比节点8小。

**如何解决？**

- 我们将保持三个指针，first、middle和last。
- 当找到当前节点(current)值小于前一个节点（previous）值的第一个点时，我们用前一个节点（previous）更新first，middle用当前节点（current）更新。
- 当我们找到当前节点(current)值小于前一个节点（previous）值的第二个点时，我们用当前节点(current)更新last。在case2中，我们永远找不到第二点。因此，last不会被更新。
- 处理后，如果last为null，则BST的两个交换节点是相邻的。

```java
    static class _1st_9 {
        public static void main(String[] args) {
            _1st_9 handler = new _1st_9();
            TreeNode root1 = TreeNodeIOUtils.transform("[10,8,20,25,null,15,5,3,7]");
            handler.printInOrder(root1);
            System.out.println();
//            handler.correctBST(root1);
            handler.printInOrder(root1);
            System.out.println();
            TreeNode root2 = TreeNodeIOUtils.transform("[10,7,20,5,null,15,25,3,8]");
            handler.printInOrder(root2);
            System.out.println();
            handler.correctBST(root2);
            handler.printInOrder(root2);
            System.out.println();
        }

        TreeNode first, middle, last;
        TreeNode prev;

        public void correctBST(TreeNode root) {
            correctBSTUtil(root);
            if (first != null && last != null) {//case1
                int t = first.val;
                first.val = last.val;
                last.val = t;
            } else if (first != null && middle != null) {//case2 last is null
                int t = first.val;
                first.val = middle.val;
                middle.val = t;
            }

        }

        public void correctBSTUtil(TreeNode root) {
            if (root == null) return;
            correctBSTUtil(root.left);
            if (prev != null && root.val < prev.val) {
                if (first == null) {
                    first = prev;
                    middle = root;
                } else {
                    last = root;
                }
            }
            prev = root;
            correctBSTUtil(root.right);
        }

        public void printInOrder(TreeNode root) {
            if (root == null) return;
            printInOrder(root.left);
            System.out.printf("%d ", root.val);
            printInOrder(root.right);
        }


    }
```



### Reference

- [恢复二叉搜索树](https://leetcode-cn.com/problems/recover-binary-search-tree/)
- [Two nodes of a BST are swapped, correct the BST](https://www.geeksforgeeks.org/fix-two-swapped-nodes-of-bst/)

