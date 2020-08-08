## 二叉树之恢复二搜索树[Cow]



![cow](C:\Users\FrankCooper\Desktop\cow.jpg)

> 二叉搜索树定义

 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的[二叉树](https://baike.baidu.com/item/二叉树/1602879)： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为[二叉排序树](https://baike.baidu.com/item/二叉排序树/10905079)。 

![1596850877563](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1596850877563.png)

### 方法1：展开树+找下降区间+中序遍历

![1596850276380](C:\Users\FrankCooper\AppData\Roaming\Typora\typora-user-images\1596850276380.png)

- 因为二叉搜索树根据其定义，当其按中序遍历（左中右）遍历而来时，其$val$值组成的是一个严格上升的递增数组，如上图中的的搜索二叉树在没有被打乱顺序前，其中序遍历后的结果应该是$[1,2,3,4,5,6,7]$,而打乱顺序后，我们发现，$7$与$3$节点调换位置了，此时的中序遍历的顺序为$[1,2,7,4,5,6,3]$
- 现在难点是如何找到$7$和$3$这两个被调换位置的节点，以恢复二叉搜索树？
- 分析后我们不难发现，当展开的数组，我们发现，大部分相邻的两个数是严格递增的，唯独有两段发送了递减，如上图所示的$[7,4]$与$[6,3]$,我们要找的
  - $first$ 节点是第一段发送下降区域的第一个节点
  - $second$ 节点是第二段发送下降区域的第二个节点
  - 因为$second$节点需要被覆盖，我们找到第二段进行覆盖
- 找到这两个节点后，$swap$即可

```java
   public void recoverTree(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new ArrayList<>();
        //iterator the treenode with mid order
        helper(root, list);
        TreeNode first = null, second = null;
        int n = list.size();
        //find the treenode
        for (int i = 0; i < n - 1; i++) {
            TreeNode pre = list.get(i);
            TreeNode next = list.get(i + 1);
            if (pre.val > next.val) {
                if (first == null) first = pre;
                second = next;
            }
        }
        //swap two treenode value
        swap(first,second);
    }
    private void helper(TreeNode root, List<TreeNode> list) {
        if (root.left != null) helper(root.left, list);
        list.add(root);
        System.out.print(root.val + ",");
        if (root.right != null) helper(root.right, list);
    }
    private void swap(TreeNode m, TreeNode n) {
        int tmp = m.val;
        m.val = n.val;
        n.val = tmp;
    }
```

### 方法2:Morris遍历

参考[一文掌握Morris遍历算法]( https://leetcode-cn.com/problems/recover-binary-search-tree/solution/yi-wen-zhang-wo-morrisbian-li-suan-fa-by-a-fei-8/ )



#### 完整代码

```java
 private void swap(TreeNode m, TreeNode n) {
        int tmp = m.val;
        m.val = n.val;
        n.val = tmp;
    }


    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null;
        TreeNode pre = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {//步骤1
                /************************/
                if (pre != null && pre.val > curr.val) {
                    if (first == null) first = pre;
                    second = curr;
                }
                pre = curr;
                /************************/
                curr = curr.right;
            } else {//步骤2
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {//步骤2.a
                    predecessor.right = curr;
                    curr = curr.left;
                } else if (predecessor.right == curr) {//步驟2.b
                    /************************/
                    if (pre != null && pre.val > curr.val) {
                        if (first == null) first = pre;
                        second = curr;
                    }
                    pre = curr;
                    /************************/
                    predecessor.right = null;
                    curr = curr.right;
                }
            }

        }
        swap(first, second);
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









