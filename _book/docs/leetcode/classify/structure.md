# 数据结构



## 链表





## 树

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

