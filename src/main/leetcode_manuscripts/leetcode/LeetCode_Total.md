# LeetCode_Total

2021.07.14

![image-20210714195839976](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\leetcode\LeetCode_Total.assets\image-20210714195839976.png)

## binary_tree

### [919. 完全二叉树插入器](https://leetcode-cn.com/problems/complete-binary-tree-inserter/)

- 找到父节点的下标索引即可

```java
        class CBTInserter {


            List<TreeNode> arr = new ArrayList<>();


            public CBTInserter(TreeNode root) {
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    TreeNode cur = q.poll();
                    arr.add(cur);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
            }

            public int insert(int v) {
                TreeNode node = new TreeNode(v);
                arr.add(node);
                int parent = arr.size() / 2 - 1;
                if (arr.size() % 2 == 0) arr.get(parent).left = node;
                else arr.get(parent).right = node;
                return arr.get(parent).val;
            }

            public TreeNode get_root() {
                return arr.isEmpty() ? null : arr.get(0);
            }
        }
```



### [958. 二叉树的完全性检验](https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/)

#### 方法1：BFS

- 定义一个变量`hasNullNode`，当其第一次遇到空节点，为`true`，第二次不应该有空节点，如果有的话，不符合完全二叉树定义

```java
public boolean isCompleteTree(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    boolean hasNullNode = false;
    while (!q.isEmpty()) {
        TreeNode cur = q.poll();
        if (cur == null) hasNullNode = true;
        else {
            if(hasNullNode) return false;
            q.offer(cur.left);
            q.offer(cur.right);
        }
    }
    return true;
}
```

#### 方法2：BFS

- 类似方法1思路，记录当前节点的上一个节点

```java
public boolean isCompleteTree(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    TreeNode prev = root;
    while (!q.isEmpty()) {
        TreeNode cur = q.poll();
        if(prev == null && cur !=null) return false;
        if(cur!=null){
            q.offer(cur.left);
            q.offer(cur.right);
        }
        prev = cur;
    }
    return true;
}
```

### [979. 在二叉树中分配硬币](https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/)

- 拿走金币和拿来金币是等价的，考察当前节点，只需要其金币-1即可

#### 方法1：DFS

```java
int res = 0;

public int distributeCoins(TreeNode root) {
    dfs(root);
    return res;
}


private int dfs(TreeNode root) {
    if (root == null) return 0;
    int l = dfs(root.left);
    int r = dfs(root.right);
    res += Math.abs(l) + Math.abs(r);
    return root.val + l + r - 1;
}
```

### [1367. 二叉树中的列表](https://leetcode-cn.com/problems/linked-list-in-binary-tree/)

#### 方法1：DFS

```java
public boolean isSubPath(ListNode head, TreeNode root) {
    if (head == null) return true;
    if (root == null) return false;
    //根节点，是不是能找到一条路径
    //根节点的左右子树能不能找到一条路径
    return checkEqual(head, root) ||
            isSubPath(head, root.left) ||
            isSubPath(head, root.right);
}


private boolean checkEqual(ListNode head, TreeNode root) {
    if (head == null) return true;//链表已经遍历结束，说明找到一个路径
    if (root == null) return false;//链表未遍历结束，但树遍历完了，返回F
    if (head.val != root.val) return false;//值不一样，返回F
    //找当前链表的下一个与当前节点的左右子树分别对比
    return checkEqual(head.next, root.left) || checkEqual(head.next, root.right);
}
```







#### 方法1：DFS



## two_pointers

### [541. 反转字符串 II](https://leetcode-cn.com/problems/reverse-string-ii/)

- 注意快要越界时最右边数组下标的取法

```java
        public String reverseStr(String s, int k) {
            char[] ch = s.toCharArray();
            int i = 0, j = 2 * k;
            while (i < ch.length) {
                int end = i + k - 1 >= ch.length ? ch.length - 1 : i + k - 1;
                reverse(ch, i, end);
                i = j;
                j = i + 2 * k;
            }
            return String.valueOf(ch);
        }


        private void reverse(char[] ch, int i, int j) {
            while (i < j) {
                char t = ch[i];
                ch[i++] = ch[j];
                ch[j--] = t;
            }
        }
```







#### 方法1：DFS





