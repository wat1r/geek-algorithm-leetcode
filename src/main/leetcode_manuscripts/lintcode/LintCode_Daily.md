# LintCode_Daily 

## binary_tree

### [68 · 二叉树的后序遍历](https://www.lintcode.com/problem/68/)

#### 方法1：DFS

```java
List<Integer> res = new ArrayList<>();

public List<Integer> postorderTraversal(TreeNode root) {
    dfs(root);
    return res;
}

private void dfs(TreeNode root) {
    if(root ==null) return;
    dfs(root.left);
    dfs(root.right);
    res.add(root.val);
}
```



### [475 · 二叉树的最大路径和 II](https://www.lintcode.com/problem/475/)

#### 方法1：DFS

```java
public int maxPathSum2(TreeNode root) {
    if(root == null) return 0;
    int res = dfs(root);
    return Math.max(res,root.val);
}


private int dfs(TreeNode root) {
    if (root == null) return 0;
    int l = dfs(root.left);
    int r = dfs(root.right);
    return Math.max(l + root.val, r + root.val);
}
```

## two_pointers

### [684 · 缺少的字符串](https://www.lintcode.com/problem/684/description)

```java
public List<String> missingString(String str1, String str2) {
    List<String> res = new ArrayList<>();
    if (str1.length() > str2.length()) {
        String temp = str1;
        str1 = str2;
        str2 = temp;
    }

    String[] arr1 = str1.split(" ");
    String[] arr2 = str2.split(" ");
    Set<String> set = new HashSet<>(Arrays.asList(arr1));
    for (String str : arr2) {
        if (!set.contains(str)) {
            res.add(str);
        }
    }
    return res;
}
```

### [521 · 去除重复元素](https://www.lintcode.com/problem/521/description)

- 使用`set`标记元素是否出现过，当出现不一样的值的时候，`i`,`j`两个指针开始错位

```java
        public int deduplication(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int i = 0, j = 0;
            while (j < nums.length) {
                while (!set.contains(nums[j])) {
                    swap(nums, i, j);
                    set.add(nums[i]);
                    i++;
                }
                j++;
            }
            return i ;

        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
```

### 1824 · [最频繁出现的子串](https://www.lintcode.com/problem/1824/)

```java
public int getMaxOccurrences(String s, int minLength, int maxLength, int maxUnique) {
    int[] arr = new int[26];
    int j = 0;
    int unique_char = 0;
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
        while (j < s.length() && j - i + 1 <= minLength && unique_char <= maxUnique) {
            arr[s.charAt(j) - 'a']++;
            if (arr[s.charAt(j) - 'a'] == 1) unique_char++;
            j++;
        }
        if (j < s.length() && minLength == j - i && unique_char <= maxUnique) {
            String k = s.substring(i, j);
            map.put(k, map.getOrDefault(k, 0) + 1);
        }
        arr[s.charAt(i) - 'a']--;
        if (arr[s.charAt(i) - 'a'] == 0) unique_char--;
    }
    int res= 0;
    for (String k : map.keySet()) {
        res = Math.max(res,map.get(k));
    }
    return res;
}
```



### [404 · 子数组求和 II](https://www.lintcode.com/problem/404/description)



```java
public int subarraySumII(int[] A, int start, int end) {
    int n = A.length;
    int[] preSum = new int[n + 1];
    for (int i = 0; i < n; i++) preSum[i + 1] = preSum[i] + A[i];
    int res = 0;
    int l = 0, r = 0;
    for (int i = 0; i <= n; i++) {
        while (r < i && preSum[i] - preSum[r] >= start) r++;
        while (l <= r && preSum[i] - preSum[l] > end) l++;
        res += r - l;
    }
    return res;
}
```



### [174 · 删除链表中倒数第n个节点](https://www.lintcode.com/problem/174/)

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode fast = head;
    ListNode slow = head;
    ListNode prev = dummy;
    for (int i = 0; i < n; i++) fast = fast.next;
    while (fast != null) {
        prev = prev.next;
        slow = slow.next;
        fast = fast.next;
    }
    prev.next = slow.next;
    slow.next = null;
    return dummy.next;
}
```