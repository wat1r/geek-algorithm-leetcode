# 结构设计

>

## [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable/)

### 方法1:二分

```java
class NumArray {

    class TreeNode {
        int val;
        int start;
        int end;
        TreeNode left;
        TreeNode right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    TreeNode root = null;


    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        TreeNode curr = new TreeNode(start, end);
        if (start == end) curr.val = nums[start];
        else {
            int mid = start + (end - start) / 2;
            curr.left = buildTree(nums, start, mid);
            curr.right = buildTree(nums, mid + 1, end);
            curr.val = curr.left.val + curr.right.val;
        }
        return curr;
    }


    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    public void update(int i, int val) {
        updateTree(root, i, val);
    }

    public void updateTree(TreeNode node, int i, int val) {
        if (node.start == node.end) {
            node.val = val;
        } else {
            int mid = node.start + (node.end - node.start) / 2;
            if (i <= mid) updateTree(node.left, i, val);
            else updateTree(node.right, i, val);
            node.val = node.left.val + node.right.val;
        }
    }

    public int sumRange(int i, int j) {
        return queryTree(root, i, j);
    }

    public int queryTree(TreeNode node, int i, int j) {
        System.out.println(String.format("i:%d,j:%d", i, j));
        System.out.println(node);
        System.out.println(String.format("Node.val:%d", node.val));
        if (node.start == i && node.end == j) return node.val;
        else {
            int mid = node.start + (node.end - node.start) / 2;
            if (j <= mid) {
                return queryTree(node.left, i, j);
            } else if (i >= mid + 1) {
                return queryTree(node.right, i, j);
            } else {
                return queryTree(node.left, i, mid) + queryTree(node.right, mid + 1, j);
            }
        }
    }
}
```

### 方法2:线段树

