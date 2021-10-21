## Check if a binary tree is subtree of another binary tree | Set 1(判断一棵二叉树是否是另外一棵二叉树的子树)

### Q：

给定两棵二叉树，检查第一棵树是否是第二棵树的子树。树T的子树是一棵树S满足下面的条件：由T中的一个节点和T中的所有子节点组成。与根节点对应的子树是整棵树；与任何其他节点对应的子树称为符合条件的子树。

例如，在以下情况下，树S(Tree2)是树T(Tree1)的子树。

```java
        Tree 2
          10  
        /    \ 
      4       6
       \
        30
          
       Tree 1
              26
            /   \
          10     3
        /    \     \
      4       6      3
       \
        30
```

### Solution：

对Tree T 先序遍历。对于遍历中访问的每个节点，查看以该节点为根的子树是否与S相同。

### Code:

```java
   static class _1st_4 {

        public static void main(String[] args) {
            _1st_4 handler = new _1st_4();
            TreeNode r1 = TreeNodeIOUtils.transform("[26,10,3,4,6,null,3,null,30,null,null,null,null]");
            // TREE 1
//              26
//             /   \
//            10     3
//           /    \     \
//          4      6      3
//           \
//            30
            
//            // TREE 2
//           10
//         /    \
//         4      6
//          \
//          30
            TreeNode r2 = TreeNodeIOUtils.transform("[10,4,6,null,30,null,null]");
            if (handler.isSubTree(r1, r2))
                System.out.println("Tree 2 is subtree of Tree 1 ");
            else
                System.out.println("Tree 2 is not a subtree of Tree 1");
//
        }

        //判断S是否是T的一棵子树
        public boolean isSubTree(TreeNode T, TreeNode S) {
            if (S == null) return true;//S已经遍历完成
            if (T == null) return false;//T已经遍历完成，但S还有没有走完的节点
            if (areIdentical(T, S)) return true;//是否当前的两棵树是完全一样的
            //T的左子树为根节点和S 对比  || T的右子树为根节点和S 对比
            return isSubTree(T.left, S) || isSubTree(T.right, S);
        }

        //判断r1和r2为根节点的两棵树，是不是完全相同的
        public boolean areIdentical(TreeNode r1, TreeNode r2) {
            if (r1 == null && r2 == null) return true;
            if (r1 == null || r2 == null) return false;
            //判断左右子树是否相同
            return r1.val == r2.val && areIdentical(r1.left, r2.left) && areIdentical(r1.right, r2.right);
        }
    }
```

### Reference

- [Check if a binary tree is subtree of another binary tree | Set 1](https://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/)

