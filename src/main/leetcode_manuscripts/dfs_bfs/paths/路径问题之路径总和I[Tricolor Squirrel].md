## 路径问题之路径总和I[Tricolor Squirrel]

![squirrel-2245913_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和I[Tricolor Squirrel].assets\squirrel-2245913_640.jpg)



> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。**

![image-20200707191637279](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和I[Tricolor Squirrel].assets\image-20200707191637279.png)



### 





### 方法1：DFS

- 深度优先的做法是沿着一条路径一直走到底，当符合条件时，开始判断返回结果
- 返回时，需要满足几个条件：
  - 当前节点为叶子节点，也即左右子节点均为$null$
  - 上一层留下的值$remain$-当前的节点的值是否为$0$， 如果是$0$，则说明存在这样的路径，反之，则需要找其他的节点

- 如果当前节点的左节点不为$null$，则遍历当前节点的左节点，需要扣减掉当前节点的值$sum-root.val$
- 如果当前节点的右节点不为$null$，则遍历当前节点的右节点，需要扣减掉当前节点的值$sum-root.val$
- 以上两步只要能找到一条路径返回$true$，则总的结果都为$true$，所以是或的关系
- 看下图的$dfs$路径：

![image-20200707194904172](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和I[Tricolor Squirrel].assets\image-20200707194904172.png)

```java
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root.left == null && root.right == null && root.val == sum) return true;
        boolean res = false;
        if (root.left != null) res = res || dfs(root.left, sum - root.val);
        if (root.right != null) res = res || dfs(root.right, sum - root.val);
        return res;
    }
```

### 方法2：递归

> 参照$dfs$的思路写出递归的方式

- 遍历整棵树：如果当前节点不是叶子，对它的所有孩子节点，递归调用$hasPathSum$ 函数，其中$ sum$ 值减去当前节点的权值；
- 如果当前节点是叶子，检查 $sum$ 值是否为 $0$，也就是是否找到了给定的目标和

```java
  public boolean hasPathSum(TreeNode root, int sum) {
  		if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
   }
```

### 方法3：BFS减法

> 下图的动画出自力扣官方

![20200707_200917](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之路径总和I[Tricolor Squirrel].assets\20200707_200917.gif)

- 从包含根节点的栈开始模拟，剩余目标和为 $sum - root.val$。
- 然后开始迭代：弹出当前元素，如果当前剩余目标和为 $0$ 并且在叶子节点上返回 $True$；如果剩余和不为零并且还处在非叶子节点上，将当前节点的所有孩子以及对应的剩余和压入栈中

- 准备两个栈$nodeStack$ 节点栈，$numStack$ 数值栈，两个栈是对应的

```java
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        nodeStack.push(root);
        numStack.push(sum - root.val);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            Integer curNum = numStack.pop();
            if (curNode.left == null && curNode.right == null && curNum == 0) return true;
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                numStack.push(curNum-curNode.left.val);
            }
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                numStack.push(curNum-curNode.right.val);
            }
        }
        return false;
    }
}
```

### 方法4：BFS加法

- 使用了$LinkedList$，效果同方法3

```java
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.pop();
            Integer curNum = numQueue.pop();
            if (curNode.left == null && curNode.right == null && curNum == sum) return true;
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                numQueue.offer(curNum + curNode.left.val);
            }
            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                numQueue.offer(curNum + curNode.right.val);
            }
        }
        return false;
    }
```















