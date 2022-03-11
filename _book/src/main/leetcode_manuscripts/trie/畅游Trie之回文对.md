## 畅游Trie之回文对







![2021-04-14_080402](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\畅游Trie之回文对.assets\2021-04-14_080402.jpg)



![image-20210326095659777](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\trie\畅游Trie之回文对.assets\image-20210326095659777.png)

### 方法1：Tire

```java
        class TrieNode {
            TrieNode[] next = new TrieNode[26]; //当前节点的下一层节点
            int idx = -1;//当前节点是否是一个单词的索引，默认是-1 表示不是一个单词最后一个节点，不是单词的索引
            List<Integer> restIsPalindrome;//跑出这个当前节点，一直往后撸的节点，是否形成了一个回文，记录下当前的这个单词的索引
            char c;//辅助的，调试时打印当前的节点
        }
```





```java
        TrieNode root = new TrieNode();
        int N;
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> palindromePairs(String[] words) {
            N = words.length;

            for (int i = 0; i < N; i++) {
                insert(words[i], i);
            }
            System.out.println(JSONObject.toJSONString(root));
            for (int i = 0; i < N; i++) {
                search(words[i], i);
            }
            return res;
        }

        private void search(String word, int idx) {
            char[] chas = word.toCharArray();
            TrieNode curr = root;
            for (int i = 0; i < chas.length; i++) {
                //当前不是单词的结尾，且当前位置到这个单词的结尾部分是回文 加入
                if (curr.idx != -1 && isPalindrome(chas, i, chas.length - 1)) {//注意这里的回文区间
                    res.add(Arrays.asList(idx, curr.idx));
                }
                //如果当前当初已经搜不到下一个节点了，这个树的路径已经没有意义
                if (curr.next[chas[i] - 'a'] == null) return;
                //跳到下一个节点
                curr = curr.next[chas[i] - 'a'];
            }
            //在外层循环，当前节点是一个单词的结束节点，且不是当前当前单词，加入
            if (curr.idx != -1 && curr.idx != idx) {
                res.add(Arrays.asList(idx, curr.idx));
            }
            //找到当前节点可以形成回文的节点，加入
            for (int rest : curr.restIsPalindrome) {
                res.add(Arrays.asList(idx, rest));
            }
        }

        private void insert(String word, int idx) {
            char[] chas = word.toCharArray();
            TrieNode curr = root;
            for (int i = chas.length - 1; i >= 0; i--) {
                if (isPalindrome(chas, 0, i)) {//注意这里的回文区间
                    curr.restIsPalindrome.add(idx);
                }
                if (curr.next[chas[i] - 'a'] == null) curr.next[chas[i] - 'a'] = new TrieNode();
                curr = curr.next[chas[i] - 'a'];
            }
            curr.idx = idx;//末尾节点，该节点是个单词
        }


        private boolean isPalindrome(char[] chas, int l, int r) {
            while (l < r) if (chas[l++] != chas[r--]) return false;
            return true;
        }
```

