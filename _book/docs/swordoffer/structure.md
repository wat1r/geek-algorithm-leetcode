# 01.数据结构

## 链表

## JZ6 从尾到头打印链表

![](/imgs/swordoffer/JZ_6_title.png)

### 方法1.遍历

#### 分析

- 简单的遍历，收集每个节点的`val`，然后对结果翻转，返回

#### 代码

```java
        public ArrayList<Integer> printListFromTailToHead(ListNode node) {
            ArrayList<Integer> res = new ArrayList<>();
            //当前节点只要不是null，就一直在循环里绕
            while (node != null) {
                //添加结果
                res.add(node.val);
                //遍历完当前节点后，将当前节点滑动到下一个节点
                node = node.next;
            }
            //翻转结果
            Collections.reverse(res);
            return res;
        }
```

### 方法2.递归

#### 分析

- 采用递归的方式，不断进入下一个节点，当到达最后一个节点指向`null`时，开始返回
- 出口条件：当节点是`null`的时候

![image-20220307191100466](/imgs/swordoffer/image-20220307191100466.png)

#### 代码

```java
  ArrayList<Integer> res = new ArrayList<>();

        public ArrayList<Integer> printListFromTailToHead(ListNode node) {
            dfs(node);
            return res;
        }
        private void dfs(ListNode node) {
            if (node == null) return;
            //进入下一层
            dfs(node.next);
            //收集节点
            res.add(node.val);
        }
```

## **JZ25** **合并两个排序的链表**

![](/imgs/swordoffer/JZ_25_title.png)

### 方法1：递归

```java
    public ListNode Merge(ListNode list1,ListNode list2) {
         if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
            if (list1.val > list2.val) {
                list2.next = Merge(list1, list2.next);
                return list2;
            } else {
                list1.next = Merge(list1.next, list2);
                return list1;
            }
    }
```

### 方法2：迭代

```java
    public ListNode Merge(ListNode l1,ListNode l2) {
             if (l1 == null || l2 == null) {
                return l1 == null ? l2 : l1;
            }
            ListNode head = new ListNode(0);
            ListNode node = head;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }
            if (l1 != null) {
                node.next = l1;
            } else {
                node.next = l2;
            }
            return head.next;
    }
```

## **JZ18** **删除链表的节点**

![](/imgs/swordoffer/JZ_18_title.png)

### 方法1:迭代

```java
        public ListNode deleteNode(ListNode head, int val) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy, cur = head;
            //遍历获取当前节点和当前节点的前一个节点
            while (cur != null) {
                if (cur.val == val) break;
                prev = prev.next;
                cur = cur.next;
            }
            //删除cur节点
            prev.next = prev.next.next;
            return dummy.next;
        }
```







## **JZ23** **链表中环的入口结点**

![](/imgs/swordoffer/JZ_23_title.png)

### 方法1:快慢指针

![](/imgs/swordoffer/JZ_23_1.png)

```java
        public ListNode EntryNodeOfLoop(ListNode pHead) {
            ListNode slow = pHead, fast = pHead;
            boolean f = false;//标志位
            while (fast.next != null && fast.next.next != null) {
                //f的标志位要是true的时候，并且slow==fast
                //初始时，slow和fast是相等的，如果没有标志位会提前退出
                if (slow == fast && f) break;
                slow = slow.next;
                fast = fast.next.next;
                f = true;//设置标志位
            }
            //如果slow和fast不是同一个节点 或者 slow和fast相同，但是是初始时的slow = pHead, fast = pHead
            if (slow != fast || !f) return null;
            slow = pHead;//slow回到起点，slow和fast步调一致，第一个相遇的点就是环形链表入口
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
```

也可以有如下的解法：

![](/imgs/swordoffer/JZ_23_2.png)

```java
        public ListNode EntryNodeOfLoop(ListNode pHead) {
            if (pHead == null || pHead.next == null) return null;
            //开始时 slow和fast错开一个位置
            ListNode slow = pHead, fast = pHead.next;
            while (fast.next != null && fast.next.next != null) {
                if (slow == fast) break;
                slow = slow.next;
                fast = fast.next.next;
            }
            //如果这时候不是第一次相遇，而fast已经到链表的末尾了，说明没有环
            if (slow != fast) return null;
            //头节点开始，且
            ListNode cur = pHead;
            while (cur != slow.next) {
                cur = cur.next;
                slow = slow.next;
            }
            return cur;
        }
    }
```













## 树

## JZ77 按之字形顺序打印二叉树

### 方法1.

```java
        public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            if (pRoot == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(pRoot);
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                ArrayList<Integer> sub = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (level % 2 == 0) sub.add(cur.val);
                    else sub.add(0, cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(new ArrayList<>(sub));
                level++;
            }
            return res;
        }
```





## **JZ82** **二叉树中和为某一值的路径(一)**

![](/imgs/swordoffer/JZ_82_title.png)

### 方法1:DFS

```java
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) return false;
            return dfs(root, sum);
        }

        private boolean dfs(TreeNode root, int sum) {
            //当前节点是叶子节点，开始比较val是否相等
            if (root.left == null && root.right == null && root.val == sum) return true;
            boolean res = false;
            //只要左右子树有一个是true即可
            if (root.left != null) res = res || dfs(root.left, sum - root.val);
            if (root.right != null) res = res || dfs(root.right, sum - root.val);
            return res;
        }
```

### 方法2:递归

```java
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;
    //当前的节点是叶子节点，比较剩下的sum值是否相等
    if (root.left == null && root.right == null) return root.val == sum;
    //左右两棵子树只要一个符合即可
    return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
}
```

### 方法3:BFS

```java
 public boolean hasPathSum4th(TreeNode root, int sum) {
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









## **JZ34** **二叉树中和为某一值的路径(二)**



![](/imgs/swordoffer/JZ_34_title.png)

### 方法1:回溯-递减

```java
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int targetSum) {
            dfs(root, targetSum, new ArrayList<>());
            return res;
        }

        private void dfs(TreeNode root, int sum, ArrayList<Integer> sub) {
            if (root == null) {
                return;
            }
            sub.add(root.val);
            if (root.left == null && root.right == null && sum == root.val) res.add(new ArrayList<>(sub));
            dfs(root.left, sum - root.val, sub);
            dfs(root.right, sum - root.val, sub);
            sub.remove(sub.size() - 1);
        }
```

### 方法2:回溯-累加

```java
   ArrayList<ArrayList<Integer>>  res = new ArrayList<>();

        public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int sum) {
            if (root == null) return res;
            dfs(root,sum,0,new ArrayList<>());
            return res;
        }

        private void dfs(TreeNode root, int sum, int total, ArrayList<Integer> sub) {
            if (root == null) return;
            sub.add(root.val);
            total += root.val;
            if (root.left == null && root.right == null) {
                if (sum == total) {
                    res.add(new ArrayList<>(sub));
                }
                //这一步是为了移除这个叶子节点的值，不管这个叶子节点是否满足条件
                sub.remove(sub.size() - 1);
                return;
            }
            dfs(root.left, sum, total, sub);
            dfs(root.right, sum, total, sub);
            sub.remove(sub.size() - 1);
        }
```







