## 畅游Tire之键值映射

### 方法1：Trie

```java
 class TrieNode {
    public TrieNode[] next = new TrieNode[26];
    public int val;

    public TrieNode() {

    }
}


 class MapSum {

    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode curr = root;
        for (char ch : key.toCharArray()) {
            if (curr.next[ch - 'a'] == null) {
                curr.next[ch - 'a'] = new TrieNode();
            }
            curr = curr.next[ch - 'a'];
        }
        curr.val = val;
    }

    public int sum(String prefix) {
        int sum = 0;
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            if (curr.next[ch - 'a'] == null) return sum;
            curr = curr.next[ch - 'a'];
        }
        return dfs(curr);
    }

    /**
     * 计算curr当前节点树上的val
     * @param curr
     * @return
     */
    private int dfs(TrieNode curr) {
        if (curr == null) return 0;
        int sum = curr.val;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            sum += dfs(curr.next[ch - 'a']);
        }
        return sum;
    }
}
```