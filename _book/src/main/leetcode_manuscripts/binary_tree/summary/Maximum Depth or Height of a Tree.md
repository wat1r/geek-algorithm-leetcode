##  Maximum Depth or Height of a Tree

![image-20210927163523857](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\summary\Maximum Depth or Height of a Tree.assets\image-20210927163523857.png)

#### 思路

```java
 maxDepth()
1. If tree is empty then return 0
2. Else
     (a) Get the max depth of left subtree recursively  i.e., 
          call maxDepth( tree->left-subtree)
     (a) Get the max depth of right subtree recursively  i.e., 
          call maxDepth( tree->right-subtree)
     (c) Get the max of max depths of left and right 
          subtrees and add 1 to it for the current node.
         max_depth = max(max dept of left subtree,  
                             max depth of right subtree) 
                             + 1
     (d) Return max_depth
```

详细的运行过程

```java
            maxDepth('1') = max(maxDepth('2'), maxDepth('3')) + 1
                               = 1 + 1
                                  /    \
                                /         \
                              /             \
                            /                 \
                          /                     \
               maxDepth('2') = 1                maxDepth('3') = 0
= max(maxDepth('4'), maxDepth('5')) + 1
= 1 + 0   = 1         
                   /    \
                 /        \
               /            \
             /                \
           /                    \
 maxDepth('4') = 0     maxDepth('5') = 0
```

### 代码

#### 方法1：DFS

- 采用dfs深搜的方式，出口条件是叶子节点

```java
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            Assert.assertEquals(3, handler.maxDepth(root));
        }


        public int maxDepth(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
```

#### 方法2：迭代

- 在每一层的末尾加一个null节点，遇到这个节点则将层数+1

```java
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int depth = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            q.offer(null);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur == null) depth++;
                if (cur != null) {
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                } else if (!q.isEmpty()) q.offer(null);
            }
            return depth;
        }
    }
```

#### 方法3：迭代

```java
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curr = queue.poll();
                    if (curr.left != null) queue.offer(curr.left);
                    if (curr.right != null) queue.offer(curr.right);
                }
                level++;
            }
            return level;
        }
```

