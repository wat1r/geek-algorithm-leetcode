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

