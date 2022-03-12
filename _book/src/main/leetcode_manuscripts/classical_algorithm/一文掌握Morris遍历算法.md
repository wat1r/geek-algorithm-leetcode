## 一文掌握Morris遍历算法



A

---

> 写的每一篇题解都会配图，其它的基本是动物，这篇我考虑算经典高级算法，没想到合适的，就拉了个凯多的图，希望以后有机会写$KMP$,$Manacher$等用的上其他五皇,本文的题目用的是比较俗套的"一文看懂"，"面试官系列"等，请忽略这种命名方式，我的公众号的风格不是这样的。。。

![timg (1)](C:\Users\FrankCooper\Desktop\timg (1).jpg)

### 背景

$Morris$遍历是二叉树遍历算法的超强进阶算法，跟递归、非递归（栈实现）的空间复杂度，$Morris$遍历可以将非递归遍历中的空间复杂度降为$O(1)$。从而实现时间复杂度为$O(N)$，而空间复杂度为$O(1)$的精妙算法。
$Morris$遍历利用的是树的叶节点左右孩子为空（树的大量空闲指针），实现空间开销的极限缩减。 

>  Morris方法用到了[线索二叉树](http://en.wikipedia.org/wiki/Threaded_binary_tree#The_array_of_Inorder_traversal)（threaded binary tree）的概念。在Morris方法中不需要为每个节点额外分配指针指向其前驱（predecessor）和后继节点（successor），只需要利用叶子节点中的左右空指针指向某种顺序遍历下的前驱节点或后继节点就可以了 。

**对于没有左子树的节点只到达一次，对于有左子树的节点会到达两次** 

### 概念

> 前驱节点（predecessor）

前驱节点：对一棵二叉树进行中序遍历，遍历后的顺序，当前节点的前一个节点为该节点的前驱节点；

> 后继节点（successor）

后继节点：对一棵二叉树进行中序遍历，遍历后的顺序，当前节点的后一个节点为该节点的后继节点；

![1596863814191](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1596863814191.png)

如上述的二叉搜索树，中序遍历后，对于节点$5$来说，$4$是其前驱节点，$6$是其后继节点





### 中序遍历
#### **步骤：**

1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。 $curr$ =$curr.right$

2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点,也就是当前节点的左子树上最右的节点，为$mostRight$

     a) 如果前驱节点（$mostRight$）的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。$curr$=$curr.left$

     b) 如果前驱节点（$mostRight$）的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。$curr$=$curr.right$

3. 重复以上1、2直到当前节点为空。

> 前驱节点（predecessor）的特点

- 第一次找到前驱节点时，其右指针是指向空的

- 如果当前节点$curr$没有左孩子，且是其父节点的左孩子，那么当前节点$curr$没有前驱节点，并且当前节点是首节点，如图中的$1$





#### 举例：

- 如$level1$左图，  当前节点$curr$为$6$，$6$的左孩子为$4$，不为空，根据步骤$2$，找到当前节点$curr$的前驱节点$predecessor$为$5$，根据步骤$2.a$, 将$5$指向当前节点$6$，当前节点$curr$更新为$curr.left$,因此当前节点$curr$进入$4$；
- 如$level1$右图，当前节点$curr$为$4$，$4$的左孩子为$2$，不为空，根据步骤$2$，找到当前节点$curr$的前驱节点$predecessor$为$3$，根据步骤$2.a$，将$3$指向当前节点$4$，当前节点$curr$更新为$curr.left$，，因此当前节点进入$2$；
- 如$level2$左图，当前节点$curr$为$2$，$2$的左孩子为$1$，不为空，根据步骤$2$，找到当前节点$curr$的前驱节点$predecessor$，而此前驱节点恰好是$1$本身，根据步骤$2.a$,将$1$指向当前节点$2$，当前节点$curr$更新为$curr.left$,因此当前节点进入$1$；
- 如$level2$右图，当前节点$curr$为$1$，根据步骤$1$，当前节点$curr$ $1$的左孩子为空，输出当前节点$1$，当时当前节点的右孩子不为空（上一步我们添加了$1$指向$2$），根据步骤$1$，当前节点$curr$更新为$curr.right$,当前节点$curr$进入$2$；
- 如$level3$左图，当前节点$curr$为$2$，根据步骤$2$，当前节点$curr$的前驱节点$predecessor$为$1$，历史又一次来到了同一条河流，只不过这是第二次，根据步骤$2.b$,前驱节点($1$)的右孩子指向了当前节点自己（$2$）,打印当前节点$2$，并且将当前节点的前驱节点$1$的右孩子设置为空，更新当前节点$curr$到$curr.right$,因此当前节点$curr$进入$3$；
- 如$level3$右图，当前节点$curr$为$3$，根据步骤$1$，打印当前节点$3$，并且将当前节点$curr$移动到$curr.right$，因此当前节点$curr$进入$4$；
- 如$level4$左图，当前节点$curr$为$4$，根据步骤$2$，当前节点$curr$的前驱节点$predecessor$为$3$，根据步骤$2.b$,前驱节点（$3$）的右孩子指向了当前节点自己（$4$），打印当前节点$4$，并且将当前节点的前驱节点$3$的右孩子设置为空，更新当前节点$curr$到$curr.right$，因此当前节点$curr$进入$5$；
- 如$level4$右图，当前节点$curr$为$5$，根据步骤$1$，打印当前节点$5$，并且将当前节点$curr$移动到$curr.right$，因此当前节点$curr$进入$6$；

![单词接龙](C:\Users\FrankCooper\Downloads\单词接龙.jpg)

#### 完整代码

```java
   public void inOrderByMorris(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {//步骤1
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {//步骤2
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {//步骤2.a
                    predecessor.right = curr;
                    curr = curr.left;
                } else if (predecessor.right == curr) {//步驟2.b
                    predecessor.right = null;
                    System.out.print(curr + " ");
                    curr = curr.right;//这段的右孩子，其实是我们人为设置的
                }
            }
        }
    }


    private TreeNode getPredecessor(TreeNode curr) {
        TreeNode predecessor = curr;
        if (curr.left != null) {
            predecessor = curr.left;
            while (predecessor.right != null && predecessor.right != curr) {
                predecessor = predecessor.right;
            }
        }
        return predecessor;
    }
```

#### **复杂度分析**:

- 时间复杂度：
- 空间复杂度： 

### 前序遍历

#### **步骤：**

1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。 $curr$ =$curr.right$

2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点,也就是当前节点的左子树上最右的节点，为$mostRight$

   a) 如果前驱节点（$mostRight$）的右孩子为空，将它的右孩子设置为当前节点。**输出当前节点（这里与中序遍历的不同点）**，当前节点更新为当前节点的左孩子。$curr$=$curr.left$

   b) 如果前驱节点（$mostRight$）的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）**这里不用输出节点**。当前节点更新为当前节点的右孩子。$curr$=$curr.right$

3. 重复以上1、2直到当前节点为空。

```java
   public void preOrderByMorris(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    System.out.print(curr.val + " ");
                    curr = curr.left;
                } else if (predecessor.right == curr) {
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    private TreeNode getPredecessor(TreeNode curr) {
        TreeNode predecessor = curr;
        if (curr.left != null) {
            predecessor = curr.left;
            while (predecessor.right != null && predecessor.right != curr) {
                predecessor = predecessor.right;
            }
        }
        return predecessor;
    }
```





### 后续遍历

> TODO



### Reference

 https://zhuanlan.zhihu.com/p/101321696 

 https://www.jianshu.com/p/484f587c967c 

 https://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html 