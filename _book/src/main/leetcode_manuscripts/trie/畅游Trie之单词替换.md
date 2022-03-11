

## 畅游Trie之单词替换



```java
public String replaceWords(List<String> list, String s) {
    TrieNode root = new TrieNode();
    buildTrie(list, root);
    StringBuilder ans = new StringBuilder();
    String[] arr = s.split("\\s+");
    for (String a : arr) {
        ans.append(getMinRoot(a, root)).append(" ");
    }
    return ans.toString().trim();
}


public String getMinRoot(String a, TrieNode root) {
    StringBuilder ans = new StringBuilder();
    TrieNode curr = root;
    for (char ch : a.toCharArray()) {
        if (curr == null) break;
        if (curr.next[ch - 'a'] != null) {
            ans.append(ch);
        }
        curr = curr.next[ch - 'a'];
        if (curr != null && curr.isEnd) return ans.toString();
    }
    return a;
}


public void buildTrie(List<String> list, TrieNode root) {
    for (String l : list) {
        TrieNode curr = root;
        for (char ch : l.toCharArray()) {
            if (curr.next[ch - 'a'] == null) curr.next[ch - 'a'] = new TrieNode();
            curr = curr.next[ch - 'a'];
        }
        curr.isEnd = true;
    }
}


class TrieNode {
    TrieNode[] next = new TrieNode[26];
    boolean isEnd = false;
}
```