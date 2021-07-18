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

### [1315. 祖父节点值为偶数的节点和](https://leetcode-cn.com/problems/sum-of-nodes-with-even-valued-grandparent/)

#### 方法1：DFS

- 传递三个节点，`grandfather` ,`father`,`cur`

```java
int res = 0;

public int sumEvenGrandparent(TreeNode root) {
    dfs(null, null, root);
    return res;
}


private void dfs(TreeNode grandfather, TreeNode father, TreeNode cur) {
    if (cur == null) return;
    if (grandfather != null && grandfather.val % 2 == 0) res += cur.val;
    dfs(father, cur, cur.left);
    dfs(father, cur, cur.right);
}
```

#### 方法2：BFS

```java
int res = 0;

public int sumEvenGrandparent(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        TreeNode cur = q.poll();
        boolean isEven = (cur.val % 2 == 0);
        if (cur.left != null) {
            q.offer(cur.left);
            if (isEven) res +=cal(cur.left);
        }
        if(cur.right!=null){
            q.offer(cur.right);
            if(isEven) res+=cal(cur.right);
        }
    }
    return res;
}

private int cal(TreeNode node) {
    int sum =0;
    if(node.left!=null)sum+=node.left.val;
    if(node.right!=null)sum+=node.right.val;
    return sum;
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

### [905. 按奇偶排序数组](https://leetcode-cn.com/problems/sort-array-by-parity/)

- 控制好边界

```java
public int[] sortArrayByParity(int[] nums) {
    int i = 0, j = 0;
    while (j < nums.length) {
        while (j < nums.length && nums[j] % 2 == 1) j++;
        if (j > nums.length - 1) break;
        swap(nums, i, j);
        i++;
        j++;
    }
    return nums;
}

private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}
```

### [917. 仅仅反转字母](https://leetcode-cn.com/problems/reverse-only-letters/)

- 调用库函数判断一个字符是否是字母`Character.isLettee(char c)`

```java
public String reverseOnlyLetters(String s) {

    char[] ch = s.toCharArray();
    int l = 0, r = ch.length - 1;
    while (l < r) {
        if (Character.isLetter(ch[l]) && Character.isLetter(ch[r])) {
            char t = ch[l];
            ch[l++] = ch[r];
            ch[r--] = t;
        } else if (!Character.isLetter(ch[l])) l++;
        else if (!Character.isLetter(ch[r])) r--;
    }
    return String.valueOf(ch);
}
```

### [922. 按奇偶排序数组 II](https://leetcode-cn.com/problems/sort-array-by-parity-ii/)

- 定好奇偶下标的索引，跳步2个

```java
public int[] sortArrayByParityII(int[] nums) {
    int n = nums.length;
    int even = 0, odd = 1;//偶数， 奇数指针
    for (; even < n; even += 2) {
        if (nums[even] % 2 == 1) {
            while (nums[odd] % 2 == 1) odd += 2;
            swap(nums,odd,even);
        }
    }
    return nums;
}

private void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
}
```





### [942. 增减字符串匹配](https://leetcode-cn.com/problems/di-string-match/)

- 我们首先考虑字符串中的第一个字母。如果 S[0] == 'I'，那么我们只要令 A[0] = 0，就一定能满足 A[0] < A[1]。如果 S[0] == 'D'，同样我们只要令 A[0] = N，就一定能满足 A[0] > A[1]。
- 接下来，当我们考虑 S 中剩下的 N - 1 个字母时，还剩下 N 个数可以使用，这 N 个数为 [0 .. N - 1] 或 [1 .. N]。可以发现，由于 S[0] 的值已经确定，那么剩下 S 中的 N - 1 个字母和 N 个可用的数变成了一个和原问题相同，但规模为 N - 1 的问题。即如果 S[1] == 'I'，我们就令 A[1] 为剩下数中最小的那个数；如果 S[1] == 'D'，我们就令 A[1] 为剩下数中最大的那个数


```java
public int[] diStringMatch(String s) {
    int n = s.length();
    int[] arr = new int[n + 1];
    int l = 0, r = n;
    int i = 0;
    while (i < n) {
        if (s.charAt(i) == 'I') arr[i] = l++;
        else if (s.charAt(i) == 'D') arr[i] = r--;
        i++;
    }
    arr[i] = l;
    return arr;
}
```



### [1385. 两个数组间的距离值](https://leetcode-cn.com/problems/find-the-distance-value-between-two-arrays/)

- 数据范围小，暴力模拟能过

```java
public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
    int res = 0;
    for (int t : arr1) {
        if (check(t, d, arr2)) res++;
    }
    return res;
}


private boolean check(int t, int d, int[] arr2) {
    for (int x : arr2) {
        if (Math.abs(x - t) <= d) return false;
    }
    return true;
}
```

### [1768. 交替合并字符串](https://leetcode-cn.com/problems/merge-strings-alternately/)

```java
public String mergeAlternately(String w1, String w2) {
    StringBuilder res = new StringBuilder();
    int i = 0, j = 0;
    while (i < w1.length() && j < w2.length()) {
        res.append(w1.charAt(i++));
        res.append(w2.charAt(j++));
    }
    while (i < w1.length()) res.append(w1.charAt(i++));
    while (j < w2.length()) res.append(w2.charAt(j++));
    return res.toString();
}
```





#### 方法1：DFS





