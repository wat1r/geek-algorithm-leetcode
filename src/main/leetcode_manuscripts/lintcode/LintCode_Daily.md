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



### [58 · 四数之和](https://www.lintcode.com/problem/58/description)

```java
public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    int n = nums.length;
    for (int i = 0; i < n - 3; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;//三个continue去重
        for (int j = i + 1; j < n - 2; j++) {
            if (j > i + 1 && nums[j] == nums[j - 1]) continue;
            for (int l = j + 1, r = n - 1; l < r; l++) {
                if (l > j + 1 && nums[l] == nums[l - 1]) continue;
                while (l < r && nums[i] + nums[j] + nums[l] + nums[r] > target) {
                    r--;
                }
                if (l >= r) break;//当l比r大的时候，提桥break掉
                if (nums[i] + nums[j] + nums[l] + nums[r] == target) {
                    List<Integer> tList = new ArrayList<Integer>();
                    tList.add(nums[i]);
                    tList.add(nums[j]);
                    tList.add(nums[l]);
                    tList.add(nums[r]);
                    res.add(tList);
                }
            }
        }
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



### [309 · 交叉数组](https://www.lintcode.com/problem/309/)

```java
    public int[] interleavedArray(int[] A, int[] B) {
        int m = A.length, n= B.length;
        int i = 0, j = 0;
        int[] res = new int[m+n];
        int idx = 0;
        while(i<m){
            res[idx++] = A[i++];
            res[idx++] =  B[j++];
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







### [415 · 有效回文串](https://www.lintcode.com/problem/415)

- 注意数组下标越界：
- 转大小写利用位运算：[位运算操作常见技巧](https://blog.csdn.net/wat1r/article/details/114298873)

```java
public boolean isPalindrome(String s) {
    int n = s.length(), l = 0, r = n - 1;
    while (l < r) {
        while (l < r && !(Character.isLetter(s.charAt(l)) || Character.isDigit(s.charAt(l)))) l++;
        while (r > l && !(Character.isLetter(s.charAt(r)) || Character.isDigit(s.charAt(r)))) r--;
        if ((s.charAt(l) & '_') != (s.charAt(r) & '_')) return false;
        l++;
        r--;
    }
    return true;
}
```



### 



### [547 · 两数组的交集](https://www.lintcode.com/problem/547/description)

```java
public int[] intersection(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    List<Integer> resList = new ArrayList<>();
    int i = 0, j = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] == nums2[j]) {
            resList.add(nums1[i]);
            i++;
            j++;
        } else if (nums1[i] > nums2[j]) j++;
        else i++;
    }
    int[] res = new int[resList.size()];

    for (int k = 0; k < resList.size(); k++) {
        res[k] = resList.get(k);
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



### [868 · 子数组的最大平均值](https://www.lintcode.com/problem/868/description)

```java
public double findMaxAverage(int[] nums, int k) {
    int i = 0, j = 0;
    int sum = 0;
    double res = 0.0;
    while (j < nums.length) {
        while (j - i + 1 <= k) {
            sum += nums[j++];
        }
        res = Math.max(res, sum * 1.0 / k);
        sum -= nums[i++];
    }
    return res;
}
```



### [891 · 有效回文 II](https://www.lintcode.com/problem/891/description)

- 左右各去掉一个判断

```java
public boolean validPalindrome(String s) {
    int n = s.length(), l = 0, r = n - 1;
    while (l < r) {
        if (s.charAt(l) != s.charAt(r)) {
            return check(s, l + 1, r) || check(s, l, r - 1);
        }
        l++;
        r--;
    }
    return true;

}

private boolean check(String s, int l, int r) {
    while (l < r) {
        if (s.charAt(l++) != s.charAt(r--)) return false;
    }
    return true;
}
```



### [928 · 最多有两个不同字符的最长子串](https://www.lintcode.com/problem/928/description)

#### 方法1

```java
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int i = 0, j = 0;
            int[] dict = new int[256];
            int cnt = 0;
            int res = 0;
            for (; i < s.length(); i++) {
                while (j < s.length() && cnt <= 2) {
                    dict[s.charAt(j)]++;
                    if (dict[s.charAt(j)] == 1) cnt++;//第一次从0到1
                    if (cnt <= 2) res = Math.max(res, j - i + 1);
                    j++;
                }
                dict[s.charAt(i)]--;
                if (dict[s.charAt(i)] == 0) cnt--;
            }
            return res;
        }
```

#### 方法2

```java
public int lengthOfLongestSubstringTwoDistinct(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int l = 0, r = 0, n = s.length(), ans = 0;
    char[] chas = s.toCharArray();
    while (r < n) {
        map.put(chas[r], map.getOrDefault(chas[r], 0) + 1);
        if (map.keySet().size() <= 2) ans = Math.max(ans, r - l + 1);
        while (map.keySet().size() == 3) {
            map.put(chas[l], map.get(chas[l]) - 1);
            if (map.get(chas[l]) == 0) map.remove(chas[l]);
            l++;
        }
        r++;
    }
    return ans;
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

