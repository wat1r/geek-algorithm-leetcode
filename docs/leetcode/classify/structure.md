# 数据结构



## 链表





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







