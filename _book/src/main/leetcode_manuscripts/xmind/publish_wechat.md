





## 图解804唯一摩尔斯密码词






> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉，欢评论区提供其他语言的版本**

## [386. 字典序排数](https://leetcode-cn.com/problems/lexicographical-numbers/)

### 方法1:O(1)空间迭代

```java
public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    int x = 1;
    //list的大小=n
    while (res.size() < n) {
        //每次将当前层 *10进入下一层
        while (x <= n) {
            res.add(x);
            x *= 10;
        }
        //如果当前层的元素已经从9跃升到10这个阶梯或者当前层元素比n大，返回上一层
        while (x % 10 == 9 || x > n) {
            x /= 10;
        }
        //当前层遍历完，递进1
        x++;
    }
    return res;
}
```

### 方法2:DFS 

```java
List<Integer> res = new ArrayList<>();

public List<Integer> lexicalOrder(int n) {
    dfs(0, n);
    return res;
}

private void dfs(int prev, int n) {
    int cur = prev * 10;
    if (prev > n) return;
    for (int i = 0; i < 10; i++) {
        int next = cur + i;
        if (next <= n && next != 0) {
            res.add(next);
            dfs(next, n);
        }
    }
}
```

- 另外一种写法

```java
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                dfs(i, n, res);
            }
            return res;
        }

        private void dfs(int i, int n, List<Integer> res) {
            if (i > n) return;
            res.add(i);
            for (int j = 0; j <= 9; j++) {
                dfs(i * 10 + j, n, res);
            }
        }
```





### 方法3:Trie+DFS

- Trie的写法也很有意思

```java
class TrieNode {
    boolean isNum;
    Map<Character, TrieNode> map;

    public TrieNode() {
        this.isNum = false;
        this.map = new HashMap<>();
    }


    public void insert(int num) {
        TrieNode cur = this;
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new TrieNode());
            }
            cur = cur.map.get(c);
        }
        cur.isNum = true;
    }

    public void lexicalOrder(TrieNode root, StringBuilder sb, List<Integer> res) {
        if (root.isNum) res.add(Integer.parseInt(sb.toString()));
        for (Map.Entry<Character, TrieNode> e : root.map.entrySet()) {
            sb.append(e.getKey());
            lexicalOrder(e.getValue(), sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}


public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    TrieNode root = new TrieNode();
    for (int i = 1; i <= n; i++) root.insert(i);
    root.lexicalOrder(root, new StringBuilder(), res);
    return res;
}
```



### 总结

- 只有方法1是符合题意的O(1)空间复杂度





### 更多阅读

- 算法题解的链接地址： [gitbook](https://cnwangzhou.gitbook.io/algorithm/)

- [个人主页【阿飞算法】](https://blog.csdn.net/wat1r/article/details/117533156) 加我好友，进群一起交流~
