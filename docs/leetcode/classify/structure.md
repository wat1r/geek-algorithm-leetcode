# 数据结构



## 链表

## [160. 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

![](/imgs/leetcode/image-20220317195629005.png)

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

### 方法1:两次遍历+哈希表

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

### 方法2：记忆化递归

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











## 树

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







