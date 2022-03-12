## 二叉树之最大最小深度[Yak]

![cattle-145800_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之最大最小深度[Yak].assets\cattle-145800_960_720.png)

### 1.二叉树的最大深度

![image-20200730211101902](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之最大最小深度[Yak].assets\image-20200730211101902.png)

#### 方法1:递归

```java
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
```

#### 方法2:BFS迭代

- 没啥好说的，层次迭代，记录最层数

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

### 2.二叉树的最小深度

![image-20200730200128012](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之最大最小深度[Yak].assets\image-20200730200128012.png)

#### 方法1:递归

**递归函数$minDepth$返回的是以当前节点为根节点的二叉树的最小深度**

- 当一个节点的左右子节点都为$null$时，这个节点是目标节点，返回1，当前层的最小深度找到
- 当一个节点的左子节点不为$null$，找左子树的最小深度
- 当一个节点的右子节点不为$null$，找右子树的最小深度
- 上面两种情况取最小值并+1，进入下一层

```java
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int tmp = Integer.MAX_VALUE;
        if (root.left != null) tmp = Math.min(tmp, minDepth(root.left));
        if (root.right != null) tmp = Math.min(tmp, minDepth(root.right));
        return tmp + 1;
    }
```

#### 方法2:BFS迭代

- 加入队列，准备一个$level$参数，在一开始进$while$循环时就+1,区别于**最大深度**解法，节点的左右孩子节点为$null$时，说明这层当前节点无孩子节点，从$root$节点到当前节点为最小深度，因为是一层一层遍历的

```java
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) return level;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return level;
    }
```

#### 方法3:DFS迭代

$Pair$存储的是当前节点及深度

```java
    public static int minDepth3rd(TreeNode root) {
        if (root == null) return 0;
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.add(new Pair<TreeNode, Integer>(root, 1));
        int depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            if (node.left == null && node.right == null)
                depth = Math.min(depth, pair.getValue());
            if (node.right != null) stack.add(new Pair<TreeNode, Integer>(node.right, pair.getValue() + 1));
            if (node.left != null) stack.add(new Pair<TreeNode, Integer>(node.left, pair.getValue() + 1));
        }
        return depth;
    }
```







