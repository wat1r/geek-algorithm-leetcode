# 数据结构



## 链表



## [23. 合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

### 方法1:优先队列

- 将链表统一地送到优先队列，然后开始一个一个弹出队列的元素开始组装

```java
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((l1,l2)->l1.val-l2.val);
        for(ListNode l:lists){
            if(l!=null) pq.offer(l);
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while(!pq.isEmpty()){
            ListNode tmp = pq.poll();
            curr.next = tmp;
            curr = curr.next;
            if(tmp.next!=null) pq.offer(tmp.next);
        }
        return dummy.next;
    }
```

### 方法2:堆排序

```java
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int len = lists.length;
        return mergeKLists(lists, 0, len - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        int mid = l + (r - l) / 2;
        ListNode l1 = mergeKLists(lists, l, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, r);
        return mergeTwoSortedListNode(l1, l2);
    }

    private ListNode mergeTwoSortedListNode(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoSortedListNode(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoSortedListNode(l1, l2.next);
            return l2;
        }
    }
```







## [83. 删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

### 方法1:迭代

![](/imgs/leetcode/classify/image-20220320181617507.png)

```java
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) return null;
            ListNode cur = head;
            while (cur.next != null) {
              //当前节点与下个节点的值如果相同，则跳下个节点
                if (cur.val == cur.next.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
```

### 方法2:递归

#### 出口条件

- 当`head`节点和`head.next`节点为`null`的时候，开始返回`head`节点

#### 逻辑

- 1.`head`的`next`指针指向递归的结果
- 2.`head`的值和`head.next`的值是否相同，相同则返回`head.next`不同则返回`head`本身

```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) return head;
    head.next = deleteDuplicates(head.next);
    return head.val == head.next.val ? head.next : head;
}
```

### 方法3:双指针

![](/imgs/leetcode/classify/image-20220320203150956.png)

```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode cur = head, nxt = head.next;
    while (nxt != null) {
        if (cur.val != nxt.val) {
            cur = cur.next;
        } else {
            cur.next = nxt.next;
        }
        nxt = nxt.next;
    }
    return head;
}
```

## [82. 删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)

### 方法1:迭代

![](imgs/leetcode/classify/image-20220320205817117.png)

```java
public ListNode deleteDuplicates(ListNode head) {
    //哑结点
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    //prev 和 cur节点
    ListNode prev = dummy, cur;
    while (prev.next != null) {//prev的next指针不为空
        cur = prev.next;//每一轮的cur是prev的后一个
        //当cur的后之后的cur相同跳过之后的
        while (cur.next != null && cur.val == cur.next.val) {
            cur = cur.next;
        }
        //需要判断链接状态，如果是相同的说明上一步没有重复的，不同的话，说明有重复的
        if (prev.next != cur) {
            prev.next = cur.next;
        } else {
            prev = prev.next;
        }
    }
    return dummy.next;
}
```

### 方法2:递归

```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) return head;
    if (head.val == head.next.val) {
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return deleteDuplicates(head.next);
    } else {
        head.next = deleteDuplicates(head.next);
        return head;
    }
}
```





## [86. 分隔链表](https://leetcode-cn.com/problems/partition-list/)

### 方法1:双指针

![](/imgs/leetcode/classify/image-20220328222033702.png)

```java
public ListNode partition(ListNode head, int x) {
    if (head == null) return head;
    ListNode dummyBefore = new ListNode(0);
    ListNode before = dummyBefore;
    ListNode dummyAfter = new ListNode(0);
    ListNode after = dummyAfter;
    while (head != null) {
        if (head.val < x) {
            before.next = head;
            before = before.next;
        } else {
            after.next = head;
            after = after.next;
        }
        head = head.next;
    }
    before.next = dummyAfter.next;
    after.next = null;
    return dummyBefore.next;
}
```





## [160. 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

![](/imgs/leetcode/classify/image-20220317195629005.png)

> 只要是对的人，就算开始错过了，最终还是会再次相遇在一起的

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode lA = headA, lB = headB;
    while (lA != lB) {
        lA = (lA == null) ? headB : lA.next;
        lB = (lB == null) ? headA : lB.next;
    }
    return lA;
}
```



## [138. 复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)

### 方法1.一遍遍历+哈希表

```java
public Node copyRandomList(Node head) {
    if (head == null) return null;
    HashMap<Node, Node> map = new HashMap<>();
    Node newHead = new Node(head.val);
    map.put(head, newHead);
    while (head != null) {
        Node mirror = map.get(head);
        if (head.next != null) {
            map.putIfAbsent(head.next, new Node(head.next.val));
            mirror.next = map.get(head.next);
        }
        if (head.random != null) {
            map.putIfAbsent(head.random, new Node(head.random.val));
            mirror.random = map.get(head.random);
        }
        head = head.next;
    }
    return newHead;
}
```



### 方法2:两次遍历+哈希表

```java
   public Node copyRandomList(Node head) {
            HashMap<Node, Node> map = new HashMap<>();
            Node cur = head;
            while (cur != null) {
                map.put(cur, new Node(cur.val, cur.next, cur.random));
                cur = cur.next;
            }
            cur = head;
            while (cur != null) {
                map.get(cur).next = map.get(cur.next);
                map.get(cur).random = map.get(cur.random);
                cur = cur.next;
            }
            return map.get(head);
        }
```

### 方法3:记忆化递归

- 1.从`head`节点开始`dfs`遍历整个链表
- 2.创建一个和当前节点`cur`相同的节点`mirror`，并建立映射
- 3.递归调用当前节点`cur`的`next`节点和`random`节点，进行复制，让`mirror`节点的`next`和`random`指针分别指向这个复制的节点
- 4返回复制的`mirror`节点

```java
        Map<Node, Node> map = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) return null;
            return dfs(head);
        }


        private Node dfs(Node cur) {
            if (cur == null) return null;
            if (map.containsKey(cur)) return map.get(cur);
            //复制一个节点
            Node mirror = new Node(cur.val);
            //记忆化
            map.put(cur, mirror);
            //next 和 random 指针处理
            mirror.next = dfs(cur.next);
            mirror.random = dfs(cur.random);
            return mirror;
        }
```



### 方法4:连接-恢复

![](/imgs/leetcode/classify/image-20220318215940345.png)

![](/imgs/leetcode/classify/image-20220318215955302.png)



- 2.遍历整个链表，处理`random`指针
- 3.定义一哑结点，然后让原来的`cur`节点指向的`mirror`节点断开，恢复到原来的状态

```java
public Node copyRandomList(Node head) {
    Node cur = head;
    while (cur != null) {
        Node mirror = new Node(cur.val);
        mirror.next = cur.next;
        cur.next = mirror;
        cur = cur.next.next;
    }
    cur = head;
    while (cur != null) {
        if (cur.random != null) {
            cur.next.random = cur.random.next;
        }
        cur = cur.next.next;
    }
    Node dummy = new Node(-1);
    Node p = dummy;
    cur = head;
    while (cur != null) {
        Node mirror = cur.next;
        p.next = mirror;
        p = p.next;
        cur.next = mirror.next;
        cur = cur.next;
    }
    return dummy.next;

}
```



## [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)

### 方法1:快慢指针

```java
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next ==null) return false;
        ListNode slow = head ,fast =head.next;
        while(fast.next!=null && fast.next.next!=null){
          	//如果已经相等，说明已经相遇
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
```



## [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

### 方法1:双指针

```java
public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null) return null;
    ListNode slow = head, fast = head.next;
    //第一次相遇点
    while (fast.next != null && fast.next.next != null) {
        if (slow == fast) break;
        slow = slow.next;
        fast = fast.next.next;
    }
    if (slow != fast) return null;
    //回到起始点
    ListNode cur = head;
    //第二次相遇
    while (cur != slow.next) {
        cur = cur.next;
        slow = slow.next;
    }
    return cur;
}
```



## [143. 重排链表](https://leetcode-cn.com/problems/reorder-list/)

### 方法1:快慢指针+翻转

![i](/imgs/leetcode/classify/image-20220323085414473.png)

![](/imgs/leetcode/classify/image-20220323085442753.png)

```java
public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode slow = head, fast = head;
    //快慢指针找整个链表的中点，准备切分
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode tmp = slow.next;
    slow.next = null;
    //翻转第二段链表，并返回第二段链表的第一个节点
    ListNode second = reverse(tmp);
    //前一段链表的第一个节点
    ListNode first = dummy.next;
    //串联两段链表
    while (second != null) {
        ListNode l2 = second.next;
        second.next = first.next;
        first.next = second;
        first = second.next;
        second = l2;
    }
}


//翻转链表
private ListNode reverse(ListNode head) {
    ListNode cur = head, pre = null, next;
    while (cur != null) {
        next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    return pre;
}
```

### 方法2:头插法

![](/imgs/leetcode/classify/image-20220323094247355.png)



```java
public void reorderList(ListNode head) {
    ListNode cur = head;
    int cnt = 0;//链表的数量
    while (cur != null) {
        cnt++;
        cur = cur.next;
    }
    //头插的次数，偶数个的话-1
    int times = (cnt % 2 == 1) ? cnt / 2 : cnt / 2 - 1;
    cur = head;//重置cur节点
    for (int i = 0; i < times; i++) {
        ListNode t = head;//找到要移动的节点前面的那个节点
        for (int j = 2; j < cnt; j++) {
            t = t.next;
        }
        //头插
        t.next.next = cur.next;
        cur.next = t.next;
        t.next = null;
        if (cur.next != null) cur = cur.next.next;
    }

}
```





## [206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

### 方法1:递归

```java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode newNode = reverseList(head.next);
    //如果链表是 1->2->3->4->5，那么此时的cur就是5
    //而head是4，head的next是5，next.next是空
    //所以head.next.next 就是5->4
    head.next.next = head;
    //head的next需要断开
    head.next = null;
    return newNode;
}
```

### 方法2:迭代

```java
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while(cur!=null){
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }
```

## [92. 反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

### 方法1:头插法

![](/imgs/leetcode/classify/image-20220329125523443.png)

```java
public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null) return null;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    for (int i = 0; i < m - 1; i++) {
        prev = prev.next;
    }
    ListNode start = prev.next, then = start.next;
    for (int i = 0; i < n - m; i++) {
        start.next = then.next;
        then.next = prev.next;
        prev.next = then;
        then = start.next;
    }
    return dummy.next;
}
```



### 方法2:迭代

```java
public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    ListNode cur = pre.next;
    for (int i = 1; i < m; i++) {
        pre = pre.next;
        cur = cur.next;
    }
    for (int i = 0; i < n - m; i++) {
        ListNode tmp = cur.next;
        cur.next = tmp.next;
        tmp.next = pre.next;
        pre.next = tmp;
    }
    return dummy.next;
}
```



## 树



## [96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)

### 方法1:DFS

```java
public int numTrees(int n) {
    return dfs(n);
}
private int dfs(int n) {
    if (n == 0 || n == 1) return 1;
    int cnt = 0;
    for (int i = 0; i <= n - 1; i++) {
        cnt += dfs(i) * dfs(n - 1 - i);
    }
    return cnt;
}
```

### 方法2:DP

```java
public int numTrees(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        for (int j = 1; j <= i; j++) {
            dp[i] += dp[j - 1] * dp[i - j];
        }
    }
    return dp[n];
}
```





## [98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)

![](/imgs/leetcode/classify/image-20220318201515791.png)

```java
        TreeNode prev = null;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            if (!isValidBST(root.left)) {
                return false;
            }
            if (prev != null && prev.val >= root.val) return false;
            prev = root;
            if (!isValidBST(root.right)) {
                return false;
            }
            return true;
        }
```



## [101. 对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/)

```java
public boolean isSymmetric(TreeNode root) {
    if (root == null) return false;
    return helper(root.left, root.right);
}


public boolean helper(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null) return false;
    return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
}
```







## [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

### 方法1:BFS

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        List<Integer> sub = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            TreeNode curr = q.poll();
            sub.add(curr.val);
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
        res.add(new ArrayList<>(sub));
    }
    return res;
}
```





## [104. 二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

### 方法1:DFS

```java
public int maxDepth(TreeNode root) {
    return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

### 方法2:BFS

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





## [111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

```java
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            int res = Integer.MAX_VALUE;
            if (root.left != null) res = Math.min(res, minDepth(root.left));
            if (root.right != null) res = Math.min(res, minDepth(root.right));
            return res + 1;
        }
```

### follow up

- 如何求倒数第2个或者倒数第k个最小深度？

```java
        //k:叶子节点， v:叶子节点的最小深度
        Map<TreeNode, Integer> map = new HashMap<>();

        public int getBottomKMinDepth(TreeNode root, int k) {
            //遍历获取map
            helper(root, 0);
            List<Integer> list = new ArrayList<>(map.values());
            int res = getBottomK(list, k);
            return res;
        }

        private int getBottomK(List<Integer> list, int k) {
            //大根堆，从栈顶到栈底 依次从大到小
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int x : list) {
                if (!pq.isEmpty() && pq.size() >= k && pq.peek() > x) pq.poll();
                if (pq.isEmpty() || pq.size() < k) pq.offer(x);
            }
            return pq.isEmpty() ? -1 : pq.peek();
        }


        private int helper(TreeNode root, int depth) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) {
                map.put(root, depth + 1);
                return 1;
            }
            int res = Integer.MAX_VALUE;
            if (root.left != null) res = Math.min(res, helper(root.left, depth + 1));
            if (root.right != null) res = Math.min(res, helper(root.right, depth + 1));
            return res + 1;
        }
```





## [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

### 方法1:迭代

- 1.定义一个`cur`节点，将其入栈
- 2.对`cur`节点的左子树重复步骤1，直到左子树为空
- 3.弹出栈内保存到左子树的节点，开始遍历右子树，重复步骤1
- 4.遍历完整个二叉树，结束

```java
List<Integer> res = new ArrayList<>();

public List<Integer> preorderTraversal(TreeNode root) {
    Stack<TreeNode> stk = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stk.isEmpty()) {
        if (cur != null) {
            res.add(cur.val);
            stk.push(cur);
            cur = cur.left;
        } else {
            TreeNode tmp = stk.pop();
            cur = tmp.right;
        }
    }
    return res;
}
```



## [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stk = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stk.isEmpty()) {
        if (cur != null) {

            stk.push(cur);
            cur = cur.left;
        } else {
            cur = stk.pop();
            res.add(cur.val);
            cur = cur.right;
        }
    }
    return res;
}
```



## [124. 二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)

```java
int res = Integer.MIN_VALUE;

public int maxPathSum(TreeNode root) {
    dfs(root);
    return res;
}

public int dfs(TreeNode root) {
    if (root == null) return 0;
    int lval = Math.max(dfs(root.left), 0);
    int rval = Math.max(dfs(root.right), 0);
    res = Math.max(res, root.val + lval + rval);
    return root.val + Math.max(lval, rval);
}
```









## [145. 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

### 方法1:迭代法

- 前序遍历和后序遍历之间的关系：

  - 前序遍历顺序为：根 -> 左 -> 右

  - 后序遍历顺序为：左 -> 右 -> 根

- 如果1： 我们将前序遍历中节点插入结果链表尾部的逻辑，修改为将节点插入结果链表的头部
  - 那么结果链表就变为了：右 -> 左 -> 根

- 如果2： 我们将遍历的顺序由从左到右修改为从右到左，配合如果1
  - 那么结果链表就变为了：左 -> 右 -> 根

- 这刚好是后序遍历的顺序

- 基于这两个思路，我们想一下如何处理：

  - 修改前序遍历代码中，节点写入结果链表的代码，将插入队尾修改为插入队首

  - 修改前序遍历代码中，每次先查看左节点再查看右节点的逻辑，变为先查看右节点再查看左节点

链接来自：[这里](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/die-dai-jie-fa-shi-jian-fu-za-du-onkong-jian-fu-za/)

```java
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stk = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stk.isEmpty()) {
        if (cur != null) {
            res.add(0, cur.val);
            stk.push(cur);
            cur = cur.right;
        } else {
            TreeNode tmp = stk.pop();
            cur = tmp.left;
        }
    }
    return res;
}
```



## [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

### 方法1:DFS

```java
    public TreeNode invertTree(TreeNode root) {
        if(root ==null) return null;
        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);
        root.left = r;
        root.right = l;
        return root;
    }
```







## [589. N 叉树的前序遍历](https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/)

```java
	List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        
        helper(root);
        return res;
    }

    private void helper(Node root){
        if(root == null) return ;
        res.add(root.val);

        for(Node child: root.children){
            helper(child);
        }
    }
```













## [590. N 叉树的后序遍历](https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/)

```java
        List<Integer> res = new ArrayList<>();

        public List<Integer> postorder(Node root) {
            dfs(root);
            return res;
        }
        
        private void dfs(Node root) {
            if (root == null) {
                return;
            }
            //  遍历root的所有的children
            for (Node child : root.children) {
                dfs(child);
            }
            // 后序遍历开始添加
            res.add(root.val);
        }
```





## [606. 根据二叉树创建字符串](https://leetcode-cn.com/problems/construct-string-from-binary-tree/)

### 方法1:迭代-标识节点

```java
public String tree2str(TreeNode t) {
    //标识节点
    TreeNode end = new TreeNode(-1);
    StringBuilder sb = new StringBuilder();
    Deque<TreeNode> stk = new ArrayDeque<>();
    stk.push(t);
    while (!stk.isEmpty()) {
        TreeNode node = stk.pop();
        if (node == end) {//当当前节点是标识节点，开始添加右括号
            sb.append(')');
            continue;
        }
        sb.append('(').append(node.val);
        stk.push(end);
        //当前节点的的左节点空，右节点非空，左节点加"()"
        if (node.left == null && node.right != null) sb.append("()");
        //左节点先出栈，入的时候先入右节点，后入左节点
        if (node.right != null) stk.push(node.right);
        if (node.left != null) stk.push(node.left);
    }
    //去掉首位
    return sb.substring(1, sb.length() - 1);
}
```

### 方法2:迭代-Set

```java
        public String tree2str(TreeNode t) {
            StringBuilder sb = new StringBuilder();
            Deque<TreeNode> stk = new ArrayDeque<>();
            stk.push(t);
            //使用set来标识是否访问过，控制")"边界
            Set<TreeNode> vis = new HashSet<>();
            while (!stk.isEmpty()) {
                TreeNode node = stk.pop();
                if (vis.contains(node)) {
                    sb.append(')');
                    continue;
                }
                sb.append('(').append(node.val);
                stk.push(node);
                //当前节点的的左节点空，右节点非空，左节点加"()"
                if (node.left == null && node.right != null) sb.append("()");
                //左节点先出栈，入的时候先入右节点，后入左节点
                if (node.right != null) stk.push(node.right);
                if (node.left != null) stk.push(node.left);
                vis.add(node);
            }
            //去掉首位
            return sb.substring(1, sb.length() - 1);
        }
```

### 方法3:递归

```java
        public String tree2str(TreeNode t) {
            if (t == null) return "";//当前节点为空，返回""
            if (t.left == null && t.right == null) return t.val + "";//当前节点没有左右孩子节点，即叶子节点，返回这个值
            if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";//当前节点只有左孩子，没有右孩子，给左孩子加上"()",右孩子不加
            return t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";//左孩子有或者没有都加 "()" || "(leftChild)"
        }
```



## [653. 两数之和 IV - 输入 BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)

### 方法1:递归+Set 

```java
	 Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        if(set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left,k) || findTarget(root.right,k);
    }
```

### 方法2:双指针

```java
//[5,3,6,2,4,null,7]
//k =11
public boolean findTarget(TreeNode root, int k) {
    //左右两个子树的queue
    Deque<TreeNode> lq = new ArrayDeque<>();
    Deque<TreeNode> rq = new ArrayDeque<>();
    TreeNode t = root;
    //一直压左子树 [2,3,5]
    while (t != null) {
        lq.addLast(t);
        t = t.left;
    }
    t = root;
    //一直压右子树 [7,6,5]
    while (t != null) {
        rq.addLast(t);
        t = t.right;
    }
    //第一次进来 ln =2 ,rn = 7 一个是lq的最小值， 一个是rq的最大值
    TreeNode ln = lq.peekLast();
    TreeNode rn = rq.peekLast();
    while (ln.val < rn.val) {
        //模拟双指针
        int sum = ln.val + rn.val;
        if (sum == k) return true;
        //如果sum小了，挪动左指针 sum大了 挪动有右指针
        if (sum < k) ln = getNext(lq, true);
        else rn = getNext(rq, false);
    }
    return false;
}

public TreeNode getNext(Deque<TreeNode> q, boolean f) {
    //如果是左子树，拿到左子树的右节点，如果是右子树，拿到右子树的左节点
    TreeNode t = f ? q.pollLast().right : q.pollLast().left;
    //一直添加该节点的左 / 右 子树的节点
    while (t != null) {
        q.addLast(t);
        t = f ? t.left : t.right;
    }
    return q.peekLast();
}
```



