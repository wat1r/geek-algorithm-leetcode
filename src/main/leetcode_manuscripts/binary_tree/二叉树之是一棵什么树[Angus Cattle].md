## 二叉树之是一棵什么树?[Angus Cattle]

![cow-1570656_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\cow-1570656_640.jpg)

### 1.相同的树

![image-20200807085059164](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\image-20200807085059164.png)



![表格](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\表格.png)

#### 方法1：递归

```java
     public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
```

#### 方法2:BFS

- 采用迭代的方式，两个$queue$分别装$p$ $q$两棵树的 写法比递归繁琐

```java
//异或运算^
A^B 相同时返回false 因此 false ^ false = false / true ^ true = false
    不同时返回ture  因此 true ^ false = true / false ^ true = true
```

```java
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty()) {
            TreeNode currP = queue1.poll();
            TreeNode currQ = queue2.poll();
            if (currP == null && currQ == null) continue;
            if (currP.val != currQ.val) return false;
            TreeNode currPLeft = currP.left;
            TreeNode currPRight = currP.right;
            TreeNode currQLeft = currQ.left;
            TreeNode currQRight = currQ.right;
            if (currPLeft == null ^ currQLeft == null) return false;
            if (currPRight == null ^ currQRight == null) return false;
            if (currPLeft != null) queue1.offer(currPLeft);
            if (currPRight != null) queue1.offer(currPRight);
            if (currQLeft != null) queue2.offer(currQLeft);
            if (currQRight != null) queue2.offer(currQRight);
        }
        return true;
    }
```



### 2.对称的二叉树

![image-20200807092439421](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\image-20200807092439421.png)

#### 方法1:递归



```java
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return helper(root.left,root.right);
    }
    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
    }
```







> TODO

### 3.平衡二叉树



![image-20200807092521622](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\image-20200807092521622.png)



#### 方法1：递归

```java
    public boolean isBalanced(TreeNode root) {
        if (root != null) {
            if (Math.abs(getHeight(root.left) - Math.abs(getHeight(root.right))) > 1) return false;
            if (!isBalanced(root.left)) return false;
            if (!isBalanced(root.right)) return false;
        }
        return true;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
```

