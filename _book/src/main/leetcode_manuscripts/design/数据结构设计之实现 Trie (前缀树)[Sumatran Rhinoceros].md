## 数据结构设计之实现 Trie (前缀树)[Sumatran Rhinoceros]


> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。**

![Sumatran rhinoceros](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之实现 Trie (前缀树)[Sumatran Rhinoceros].assets\Sumatran rhinoceros.jpg)



![image-20200716213729181](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之实现 Trie (前缀树)[Sumatran Rhinoceros].assets\image-20200716213729181.png)



### 0.前缀树的一些应用

#### 0.1.搜索补全

![image-20200722200339171](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之实现 Trie (前缀树)[Sumatran Rhinoceros].assets\image-20200722200339171.png)



#### 0.2.IP路由的最长前缀匹配机制

- 最长前缀匹配机制（Longest Prefix Match Algorithm）是目前行业内几乎所有路由器都缺省采用的一种路由查询机制，当路由器受到一个IP数据包时，它会将数据包的目的IP地址与自己本地路由表中的所有路由表项进行逐位（Bit-By-Bit）比对，直到找到匹配长度度最长的条目
  ![image-20200722203759596](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之实现 Trie (前缀树)[Sumatran Rhinoceros].assets\image-20200722203759596.png)

### 方法1

![image-20200722205303327](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之实现 Trie (前缀树)[Sumatran Rhinoceros].assets\image-20200722205303327.png)

> 准备一个$26$个字母的映射节点，一个标记当前字符是否是某个单词的结尾的标记变量$isEnd$

- $insert$:拿到当前的字母数组$next$，依次遍历传入的单词的字符
  - 如果当前字符不在$next$中，生成一个新的节点，并将当前的节点指向新生成的节点
  - 如果当前字符在$next$中，将该字符所在的节点指向当前的节点
  - 在单词被遍历结束时，给当前所在的节点设置$isEnd$的标记，表示以当前字符结束可以形成一个字母

```java
 public void insert(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new Trie();
                }
                curr = curr.next[c - 'a'];
            }
            curr.isEnd = true;
        }
```

- $search$和$startsWith$:

```java
    public boolean search(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null) return false;
            curr = curr.next[c - 'a'];
        }
        return curr.isEnd;
    }
```
- 遍历字母的所有字符，如果当前字符所在的节点不存在，返回$false$，说明单词还没搜完，树已经断了
- 不断将遍历到的节点赋值给当前节点
- 注意：$search$和$startsWith$的区别需要判断当前节点是否是$isEnd$，如果是的话，证明是搜索到了，但是$startsWith$则不需要满足此条件

### 完整代码

```java
class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd = false;

        public Trie() {
        }

        public void insert(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new Trie();
                }
                curr = curr.next[c - 'a'];
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) return false;
                curr = curr.next[c - 'a'];
            }
            return curr.isEnd;
        }
   
        public boolean startsWith(String prefix) {
            Trie curr = this;
            for (char c : prefix.toCharArray()) {
                if (curr.next[c - 'a'] == null) return false;
                curr = curr.next[c - 'a'];
            }
            return true;
        }
    }
```

### python版

```python
class TrieNode:

    # Initialize your data structure here.
    def __init__(self):
        self.children = collections.defaultdict(TrieNode)
        self.is_word = False


class Trie:

    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        curr = self.root
        for c in word:
            curr = curr.children[c]
        curr.is_word = True

    def search(self, word):
        current = self.root
        for letter in word:
            current = current.children.get(letter)
            if current is None:
                return False
        return current.is_word

    def startsWith(self, prefix):
        current = self.root
        for c in prefix:
            current = current.children.get(c)
            if current is None:
                return False
        return True
```



### 方法2

- 维护一个$Map<Character, TrieNode> next$的$map$，实现思路类似

```java
class Trie {

        class TrieNode {
            Map<Character, TrieNode> next = new HashMap<>();
            boolean isEnd = false;
        }


        TrieNode root = new TrieNode();

        public Trie() {
        }

        public void insert(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.next.containsKey(c)) {
                    TrieNode tmp = new TrieNode();
                    curr.next.put(c, tmp);
                    curr = tmp;
                } else {
                    curr = curr.next.get(c);
                }
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.next.containsKey(c)) return false;
                curr = curr.next.get(c);
            }
            return curr.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                if (!curr.next.containsKey(c)) return false;
                curr = curr.next.get(c);
            }
            return true;
        }

    }

```



