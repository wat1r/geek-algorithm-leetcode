## 是真的懂LCA吗？

### 最近公共祖先(LCA|Lowest Common Ancestor)

> **一棵有根的树T。两个节点n1和n2之间的最低共同祖先被定义为T中具有n1和n2作为后代的最低节点（允许一个节点是其自身的后代）。**
> **T中n1和n2的LCA是距离根最远的n1和n2的共同祖先。例如，作为确定树中节点对之间距离的过程的一部分，计算最低共同祖先可能是有用的：从n1到n2的距离可以计算为从根到n1的距离，加上从根到n2的距离，减去从根到其最低共同祖先的距离的两倍。**

![image-20211028092327375](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211028092327375.png)

#### 方法1：DFS

##### 思路：

1.$left$和$right$都为空，说明$root$的左右子树中都不包含$p$和$q$节点，返回$null$即可

2.$left$不为空，$right$为空，说明$p$和$q$不在右子树中（因为右子树为空了），这时，返回$left$，这里面有下面的两种情况：

- $p$和$q$都在$left$即左子树上，而$root$节点恰好指向了$p$或者$q$
- $p$和$q$都在$left$即左子树上，而$root$节点未指向了$p$或者$q$，指向的是最近公共祖先节点

3.$right$不为空，$left$为空，说明$p$和$q$不在左子树中（因为左子树为空了），这时，返回$right$，这里面有下面的两种情况：

- $p$和$q$都在$right$即右子树上，而$root$节点恰好指向了$p$或者$q$
- $p$和$q$都在$right$即右子树上，而$root$节点未指向了$p$或者$q$，指向的是最近公共祖先节点

4.$left$不为空，并且$right$不为空，说明$p$和$q$分布在$root$节点的左右子树的两侧，这时$root$为$p$和$q$的最近公共祖先节点，返回

![在这里插入图片描述](https://img-blog.csdnimg.cn/a253c9a2405d47a5b674f13166e374e2.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zi_6aOe566X5rOV,size_20,color_FFFFFF,t_70,g_se,x_16)
![在这里插入图片描述](https://img-blog.csdnimg.cn/a9d7ab1901f1476c80270fa8ca5a5a5d.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zi_6aOe566X5rOV,size_20,color_FFFFFF,t_70,g_se,x_16)

![在这里插入图片描述](https://img-blog.csdnimg.cn/f5374536188e440aaafd880d230a5fbe.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zi_6aOe566X5rOV,size_20,color_FFFFFF,t_70,g_se,x_16)


```java
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root ==p || root== q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q );
        TreeNode right = lowestCommonAncestor(root.right, p,q );
        if(left !=null && right !=null) return root;
        if(left !=null) return left;
        if(right!=null) return right;
        return null;
    }
```

##### 测试

```java
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            TreeNode root = TreeNodeIOUtils.transform("[3,5,1,6,2,0,8,null,null,7,4]");
            TreeNode p = root.left;//5
            TreeNode q = root.right;//1
//            TreeNode ancestor = handler.lowestCommonAncestor(root, p, q);
//            System.out.printf("%d\n", ancestor.val);
            root = TreeNodeIOUtils.transform("[3,5,1,6,null,null,null,7,2,null,null,null,4]");
            p = root.left.left.left;
            q = root.left.left.right.right;
            TreeNode ancestor = handler.lowestCommonAncestor(root, p, q);
            System.out.printf("%d\n", ancestor.val);
        }
```

#### 方法2：迭代

![在这里插入图片描述](https://img-blog.csdnimg.cn/53e49860e1394ab0a07b18d4e1b67ea0.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA6Zi_6aOe566X5rOV,size_20,color_FFFFFF,t_70,g_se,x_16)


```java
 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //存当前节点的父节点，k:当前节点，v:当前节点的父节点，root节点的父节点为null
            Map<TreeNode, TreeNode> parent = new HashMap<>();
            parent.put(root, null);
            Deque<TreeNode> stk = new ArrayDeque<>();
            stk.push(root);
            //step1:率先找到p或者q，先左节点，后右节点，然后出栈的时候，选右节点优先
            while (!parent.containsKey(p) || !parent.containsKey(q)) {
                TreeNode cur = stk.pop();
                if (cur.left != null) {
                    parent.put(cur.left, cur);
                    stk.push(cur.left);
                }
                if (cur.right != null) {
                    parent.put(cur.right, cur);
                    stk.push(cur.right);
                }
            }
            //step2：set收集的是p节点的所有祖先节点，包括p节点的直系父节点
            Set<TreeNode> set = new HashSet<>();
            while (p != null) {
                set.add(p);
                p = parent.get(p);
            }
            //step3:找q的父节点，第一个出现在set集合中的即是LCA
            while (!set.contains(q)) {
                q = parent.get(q);
            }
            return q;
        }
```

### 如果这棵二叉树是一棵二叉搜索树(BST)呢？

对于BST，当从上到下遍历树时，位于两个数字n1和n2之间的第一个节点是LCA，即位于n1和n2（n1<=n<=n2）之间的具有最低深度的第一个节点n。所以只要递归地遍历BST，如果节点的值大于n1和n2，那么LCA位于节点的左侧，如果它小于n1和n2，那么LCA位于右侧。否则，根为LCA（假设BST中同时存在n1和n2)

#### 方法1：DFS

##### 思路：

- 1.创建一个递归函数，该函数接受一个节点和两个值n1和n2。
- 2.如果当前节点的值小于n1和n2，则LCA位于右侧子树中。调用右子树的递归函数。
- 3.如果当前节点的值大于n1和n2，则LCA位于左子树中。调用左子树的递归函数。
- 4.如果上述两种情况均为false，则将当前节点作为LCA返回

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

##### 复杂度分析：

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

##### 复杂度分析：

- 时间复杂度：O(h)，h为树的高度
- 空间复杂度：O(1)，间复杂度是常数级别。

### Reference

- [Lowest Common Ancestor in a Binary Search Tree(二叉搜索树的最近公共祖先节点)](https://blog.csdn.net/wat1r/article/details/120959546)

- [Lowest Common Ancestor in a Binary Tree | Set 2 (Using Parent Pointer)（二叉树的最近公共祖先节点（使用父节点））](https://blog.csdn.net/wat1r/article/details/120964544)

- [【重温经典】二叉树的最近公共祖先](https://blog.csdn.net/wat1r/article/details/120301718)





