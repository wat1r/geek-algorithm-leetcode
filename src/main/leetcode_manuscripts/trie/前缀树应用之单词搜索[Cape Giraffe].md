

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

### 2.单词搜索 II

![image-20200727195855364](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\前缀树应用之单词搜索[Cape Giraffe].assets\image-20200727195855364.png)

> 分两步

#### 1.构建$words$的字典前缀树

构建前缀树，可以参考$208$题,区别在于当到达一个单词的末尾时，将单词赋值给$word$

```java
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.word = word;
        }
```

#### 2.$dfs$搜索

![image-20200727201705385](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\前缀树应用之单词搜索[Cape Giraffe].assets\image-20200727201705385.png)

- 推荐一种写法：

```java
int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
 for (int k = 0; k < 4; k++) {
            int nextI = i + directions[k][0];
            int nextJ = j + directions[k][1];
 }
//详细的解释见上图，二维数组，拿行时，取[k][0],拿列时，取[k][1]
```

- 出口条件：(当前节点是一个单词的末尾字符形成的节点)

```java
        if (curr != null && curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
```

- 标记与边界

```java
 board[i][j] = '#'; 
 board[i][j] = c;
//在整个board区域内，且没有走过
if ((nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) || board[nextI][nextJ] == '#') continue;
```

> 当然，也可以使用与board同样大小的访问数组来标记，缺点是浪费空间

`boolean[][] visited = new boolean[m][n];`

#### 方法1

```java
  class TrieNode {
        private TrieNode[] next = new TrieNode[26];
        private String word = null;
    }


    List<String> result = new ArrayList<>();
    int m;//行
    int n;//列
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {

        //1.构造前缀树，遍历words，在里层遍历每一个word
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.word = word;
        }
        //2.对单元格进行回溯
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.next[board[i][j] - 'a'] != null) {
                    backtracking(board, i, j, root);
                }
            }
        }

        return result;
    }

    private void backtracking(char[][] board, int i, int j, TrieNode root) {
        char c = board[i][j];
        TrieNode curr = root.next[c - 'a'];
        if (curr != null && curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
        board[i][j] = '#';
        for (int k = 0; k < 4; k++) {
            int nextI = i + directions[k][0];
            int nextJ = j + directions[k][1];
            if ((nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) || board[nextI][nextJ] == '#') continue;
            if (curr != null) backtracking(board, nextI, nextJ, curr);
        }
        board[i][j] = c;
    }
```

#### 方法2

- 剪枝，准备一个变量count,当叶子节点的时候，也就是count=0时，开始剪枝，叶子节点已无用处，在构造前缀树的过程中，维护好count

```java
 class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word = null;
        int count = 0;
    }


    List<String> result = new ArrayList<>();
    int m;//行
    int n;//列
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        //1.构造前缀树，遍历words，在里层遍历每一个word
        addWords(words);
        //2.对单元格进行回溯
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.next[board[i][j] - 'a'] != null) {
                    backtracking(board, i, j, root);
                }
            }
        }


        return result;
    }

    private void backtracking(char[][] board, int i, int j, TrieNode root) {
        char c = board[i][j];
        TrieNode curr = root.next[c - 'a'];
        if (curr != null && curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
        board[i][j] = '#';
        for (int k = 0; k < 4; k++) {
            int nextI = i + directions[k][0];
            int nextJ = j + directions[k][1];
            if ((nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) || board[nextI][nextJ] == '#') continue;
            if (curr != null) backtracking(board, nextI, nextJ, curr);
        }
        board[i][j] = c;
        if (curr != null && curr.count == 0) {
            root.next[c - 'a'] = null;
            root.count--;
        }
    }

    private void addWords(String[] words) {

        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.count++;
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.word = word;
        }
    }
```







### 推荐阅读

- [数据结构设计之实现 Trie (前缀树)[Sumatran Rhinoceros]](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shu-ju-jie-gou-she-ji-zhi-shi-xian-trie-qian-zhui-/)
- [前缀树应用之单词搜索[Cape Giraffe]](https://leetcode-cn.com/problems/word-search-ii/solution/qian-zhui-shu-ying-yong-zhi-dan-ci-sou-suo-cape-gi/)
- [畅游Trie之实现一个魔法字典](https://leetcode-cn.com/problems/implement-magic-dictionary/solution/chang-you-triezhi-shi-xian-yi-ge-mo-fa-z-d7wa/)

- [畅游Trie之单词替换](https://leetcode-cn.com/problems/replace-words/solution/chang-you-triezhi-dan-ci-ti-huan-by-a-fe-hf6w/)



