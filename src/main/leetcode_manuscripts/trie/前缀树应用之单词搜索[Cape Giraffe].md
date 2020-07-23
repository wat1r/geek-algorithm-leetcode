

## 前缀树应用之单词搜索[Cape Giraffe]



![giraffe-1582343_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\前缀树应用之单词搜索[Cape Giraffe].assets\giraffe-1582343_640-1595467550676.png)



### 1.添加与搜索单词

![image-20200723193938453](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\前缀树应用之单词搜索[Cape Giraffe].assets\image-20200723193938453.png)

> 本题植根于前缀树的设计结构

#### a.设计数据结构

```java
            private TrieNode[] next;
            private boolean isEnd;
```

- 准备数据结构$TrieNode$, 有两个参数：$next$是一个$26$大小的节点数组，$isEnd$表示当前的字符组成的一个节点是否是单单词的结束

可以组成下面的结构,类似链表的结构，$bad$

```java
root.next['b'-'a'].next['a'-'a'].next['d'-'a'].isEnd
```

#### b.$addWord$

- 这个方法，是构建单词的前缀树结构，思路与前缀树的$insert$方法一致，

#### c.$search$

- 此处的$search$方法区别于前缀树的$search$方法，是字符可以为`.`,来表示任何一个字符，如$.ad$,此处的`.`可表示$26$个小写字母中的任何一个
- 处理思路是准备一个函数$dfs(root, word, index)$，其中$index$表示，当前搜索到的$word$的字符的下标
  - **出口条件**，当$index$到单词$word$的结尾时，返回当前的节点是否是个单词的结尾，因为是$search$,需要完整匹配
  - 当前字符是普通字符，如果字符不在前缀树中，返回$false$，如果在前缀树中，接着搜下一个索引$index+1$的字符，并将当前的节点传到下一层
  - 当前字符非普通字符，为符号"."，$26$个字母都有可能，如果当前字符在前缀树中，接着搜下一个索引$index+1$的字符，并将当前的节点传到下一层，如果$26$个字母都搜索结束，返回$false$

```java
class WordDictionary {


        static class TrieNode {
            private TrieNode[] next;
            private boolean isEnd;

            public TrieNode() {
                this.next = new TrieNode[26];
                this.isEnd = false;
            }
        }


        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode curr = this.root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.isEnd = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return dfs(root, word, 0);
        }

        /**
         * @param root
         * @param word
         * @param index
         * @return
         */
        private boolean dfs(TrieNode root, String word, int index) {
            if (index == word.length()) {
                return root.isEnd;
            }
            char c = word.charAt(index);
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (root.next[i] != null && dfs(root.next[i], word, index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (root.next[c - 'a'] == null) return false;
                return dfs(root.next[c - 'a'], word, index + 1);
            }
        }
    }
```

