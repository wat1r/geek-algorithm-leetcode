## 畅游Trie之实现一个魔法字典

![image-20210325082425404](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\skill\畅游Trie之实现一个魔法字典.assets\image-20210325082425404.png)

### 方法1：字典树+候选单词搜索

![image-20200722205303327](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\畅游Trie之实现一个魔法字典.assets\image-20200722205303327.png)

```java
         class TrieNode {
            private TrieNode[] next = new TrieNode[26];
            private boolean isEnd = false;
        }


         class MagicDictionary {

            /**
             * Initialize your data structure here.
             */
            private TrieNode root;

            public MagicDictionary() {
                root = new TrieNode();
            }

            public void buildDict(String[] dictionary) {
                for (String dict : dictionary) {
                    TrieNode curr = root;
                    for (char ch : dict.toCharArray()) {
                        if (curr.next[ch - 'a'] == null) {
                            curr.next[ch - 'a'] = new TrieNode();
                        }
                        curr = curr.next[ch - 'a'];
                    }
                    curr.isEnd = true;
                }
            }

            /**
             * 每个单词候选的单词都搜索了一遍
             * hello -> alleo blleo clleo ...
             *
             * @param searchWord
             * @return
             */
            public boolean search(String searchWord) {
                char[] src = searchWord.toCharArray();
                for (int i = 0; i < searchWord.length(); i++) {
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (searchWord.charAt(i) == c) continue;//本身的单词需要跳过
                        src[i] = c;
                        String candidate = String.valueOf(src);//构造候选单词
                        if (searchSeg(candidate)) return true;
                        src[i] = searchWord.charAt(i);//恢复
                    }
                }
                return false;
            }

            /**
             * 搜索一个单词是否存在在这个Trie上：
             * 1.所有节点都走完
             * 2.最后走完的节点的isEnd是T
             *
             * @param word
             * @return
             */
            public boolean searchSeg(String word) {
                TrieNode curr = root;
                for (char c : word.toCharArray()) {
                    if (curr.next[c - 'a'] == null) return false;
                    curr = curr.next[c - 'a'];
                }
                return curr.isEnd;
            }

        }
```

### 方法2：HashMap

> 思路来自国际站大佬@shawngao

- `map`：

  - `key`存储除当前字符外的字符拼接成的字符串，如`hello` 拿掉`e`  变成`hllo`
  - `value`存储上面被拿掉的字符的下标索引和字符，如上面的`1,e`

  ```
  "hello" -> 
  {"ello":[[0, 'h']], 
  "hllo":[[1, 'e']], 
  "helo":[[2, 'l'],[3, 'l']],
  "hell":[[4, 'o']]}
  ```
  
- 搜索的时候，先找到`key`，再比对`value`

```java
class MagicDictionary {

    HashMap<String, List<int[]>> map = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {

    }

    public void buildDict(String[] dicts) {
        for (String dict : dicts) {
            for (int i = 0; i < dict.length(); i++) {
                String key = dict.substring(0, i) + dict.substring(i + 1);
                List<int[]> value = map.getOrDefault(key, new ArrayList<>());
                value.add(new int[]{i, dict.charAt(i)});
                map.put(key, value);
            }
        }
    }

    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + word.substring(i + 1);
            if (map.containsKey(key)) {
                for (int[] value : map.get(key)) {
                    if (value[0] == i && value[1] != word.charAt(i)) return true;
                }
            }
        }
        return false;
    }
}
```

