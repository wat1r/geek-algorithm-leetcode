# 剑指Offer

# 01.数据结构

## 链表

### JZ6 从尾到头打印链表

![](/imgs/swordoffer/image-20220307191118762.png)

#### 方法1.遍历

##### 分析

- 简单的遍历，收集每个节点的`val`，然后对结果翻转，返回

##### 代码

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

#### 方法2.递归

##### 分析

- 采用递归的方式，不断进入下一个节点，当到达最后一个节点指向`null`时，开始返回
- 出口条件：当节点是`null`的时候

![image-20220307191100466](/imgs/swordoffer/image-20220307191100466.png)

##### 代码

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





## 树

### JZ77 按之字形顺序打印二叉树

#### 方法1.

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



# 02.算法



## 动态规划

### JZ42连续子数组的最大和

![](/imgs/swordoffer/JZ_42_title.png)

#### 方法1.动态规划

```java
        public int FindGreatestSumOfSubArray(int[] array) {
            if (array == null || array.length == 0) return 0;
            int n = array.length;
            //f[i]表示以array[i-1]这个数为结尾的，连续子数组的最大和
            int[] f = new int[n + 1];
            int res = -101;
            for (int i = 1; i <= n; i++) {
                //当前这个数array[i-1]和前一个数的连续子数组形成一个最大的连续子数组：[...i-2,i-1]
                //当前这个数array[i-1]自己形成一个子数组[i-1]
                //两者取最大值，不断更新全局变量
                f[i] = Math.max(f[i - 1] + array[i - 1], array[i - 1]);
                res = Math.max(res, f[i]);
            }
            return res;
        }
```















---

#### 方法1.

#### 方法2.