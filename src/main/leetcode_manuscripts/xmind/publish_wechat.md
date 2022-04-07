





## 图解682棒球比赛






> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉，欢评论区提供其他语言的版本**



## [429. N 叉树的层序遍历](https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/)

### 方法1:BFS



```java
public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<Node> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        List<Integer> sub = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Node cur = q.poll();
            sub.add(cur.val);
            for (Node child : cur.children) {
                q.offer(child);
            }
        }
        res.add(new ArrayList<>(sub));
    }
    return res;
}
```







### 方法2:DFS



![](/imgs/leetcode/classify/image-20220408072115052.png)



```java
List<List<Integer>> res = new ArrayList<>();

public List<List<Integer>> levelOrder(Node root) {
    dfs(root, 0);
    return res;
}

/**
 * @param root  当前处理到的节点
 * @param level 层数
 */
private void dfs(Node root, int level) {
    if (root == null) return;
    if (level == res.size()) res.add(new ArrayList<>());
    res.get(level).add(root.val);
    for (Node child : root.children) dfs(child, level + 1);
}
```





### 更多阅读

- 算法题解的链接地址： [gitbook](https://cnwangzhou.gitbook.io/algorithm/)

- [个人主页【阿飞算法】](https://blog.csdn.net/wat1r/article/details/117533156) 加我好友，进群一起交流~
