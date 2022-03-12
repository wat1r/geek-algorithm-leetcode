## 二叉树之展开为链表[Cattle]



![cow-1712150_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之展开为链表[Cattle].assets\cow-1712150_640.png)



### 方法1：递归

采用前序遍历，有个最大的问题是左节点的信息不能被保存，但是需要转成链表的过程中，将其置为$null$

可以采取后序遍历的方式，将当前节点的右指针设置为上个节点，上个节点的左指针在之后的遍历过程中不会用到，设置为$null$没问题

```java
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten2nd(root.right);
        flatten2nd(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
```





### 方法2：栈的前序遍历

![表格](C:\Users\FrankCooper\Downloads\表格.jpg)

栈的特性：$FILO$先进后出，当我们先$push$右孩子节点再$push$左孩子节点的时候，弹出的时候左孩节点就会被先弹出，右孩子节点被后弹出

需要在记录一个前驱节点$pre$,这个节点游走在这些节点上，每次都将$pre$节点的右边节点挂上当前节点的值$curr$，左边节点设置为空，这里这样设置，是应为$pre$节点对于后后续的栈内的节点没什么影响了

```java
pre.right = curr;
pre.left = null;
```

详细过程可参见上图的演示流程

```java
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (pre != null) {
                pre.right = curr;
                pre.left = null;
            }
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
            pre = curr;
        }

    }
```



### 方法3：原地前驱节点遍历

选取一个节点作为作为$curr$的节点，如果左子树不为$null$，找到其左子树上最右的右子节点，作为$pre$节点

将$curr$节点的右节点挂到$pre$节点的右节点上，作为$pre$节点的右侧节点

将$curr$节点的左节点置为$null$，

将当前$curr$节点的左子节点挂到$curr$的右节点上

开始下个周期

![表格](C:\Users\FrankCooper\Downloads\表格.png)

```java
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode pre = next;
                while (pre.right != null) pre = pre.right;
                pre.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
```

还有另外一种写法，省去了$next$与$curr$节点，大同小异

```java
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode pre = root.left;
                while (pre.right != null) pre = pre.right;//找到左子树的最右侧的节点
                pre.right = root.right;//前驱节点接上处理节点的右子树
                root.right = root.left;//处理节点的右节点挂上左子树
                root.left = null;//处理节点左子树设置为null
            }
            root = root.right;//继续转到下一个节点

        }
    }
```

#### 复杂度分析

- 时间复杂度：$O(N)$, $N$为二叉树节点的数量
- 空间复杂度：$O(1)$, 不借助额外空间，基于二叉树本身的指针指向的方式











