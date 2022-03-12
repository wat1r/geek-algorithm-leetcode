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







## **JZ23** **链表中环的入口结点**

![](/imgs/swordoffer/JZ_23_title.png)

### 方法1

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

