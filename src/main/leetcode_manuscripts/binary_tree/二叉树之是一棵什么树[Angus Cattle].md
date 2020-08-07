## 二叉树之是一棵什么树?[Angus Cattle]

![cow-1570656_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\cow-1570656_640.jpg)

### 1.相同的树

![image-20200807085059164](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\image-20200807085059164.png)



![表格](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\表格.png)

```java
     public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
```

### 2.对称的二叉树

![image-20200807092439421](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\image-20200807092439421.png)



> TODO

### 3.平衡二叉树



![image-20200807092521622](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之是一棵什么树[Angus Cattle].assets\image-20200807092521622.png)



> TODO