package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_15 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] words = new String[]{""};
//            handler.longestWord(words);
            words = new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
            Assert.assertEquals("dogwalker", handler.longestWord(words));


        }


        public String longestWord(String[] words) {
            String res = "";
            if (words.length == 0) return res;
            List<String> base = Arrays.asList(words);
            for (String target : words) {
                List<String> list = new ArrayList<>();
                //复制一份单词列表，并且移除这个目标单词本身
                Collections.addAll(list, new String[base.size()]);
                Collections.copy(list, base);
                list.remove(target);
                if (check(target, list)) {
                    //有更长的，选更长的
                    if (target.length() > res.length()) res = target;
                        //单词长度相等，选字典序小的
                    else if (target.length() == res.length() && target.compareTo(res) < 0) {
                        res = target;
                    }
                }
            }
            return res;
        }

        /**
         * @param target 目标单词
         * @param list   单词列表(已经排除了目标单词本身)
         * @return
         */
        public boolean check(String target, List<String> list) {
            if (target.length() == 0) return true;//每次都剩下单词，是否完美切分出来
            for (int i = 0; i <= target.length(); i++) {//注意 =号 可以取到末尾 substring 取头不取尾 ，取到len-1位置，取完整个字符
//                System.out.printf("%s--->%s\n", target, target.substring(0, i));
                //当前切出来的单词在单词列表中&&剩下的单词也能在单词列表中找到（可能需要再切分）
                if (list.contains(target.substring(0, i)) && check(target.substring(i), list)) return true;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String[] words = new String[]{""};
//            handler.longestWord(words);
            words = new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
//            Assert.assertEquals("dogwalker", handler.longestWord(words));
            words = new String[]{"ttaaaa", "pp", "tpa", "kpaqkt", "tktpqq", "aqppatp"};//自身重复的case
            Assert.assertEquals("", handler.longestWord(words));
        }


        public String longestWord(String[] words) {
            String res = "";
            List<String> wordList = Arrays.asList(words);
            //按字符长度从大到小排列，相同长度的字符，按字典序正序排列，这样第一个返回的是满足题意要求的字符
            wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
            for (String target : wordList) {
                if (dfs(target, 0, wordList)) return target;
            }
            return res;

        }

        /**
         * @param target   待处理的目标单词
         * @param start    该目标单词目前处理到的的下标索引，初始化的时候是0，从该单词的第一个字符开始
         * @param wordList 包含这个目标单词的所有单词的列表
         * @return
         */
        private boolean dfs(String target, int start, List<String> wordList) {
            if (start == target.length()) return true;//当下标到达字符的结尾时，说明这个是满足条件的
            for (int end = start; end < target.length(); end++) {
                //下面这一行是为了排除目标单词target本身，题意要求由其他的至少两个单词组成
                //当遍历的时候只有一轮，一直没找到其他的目标单词，这个目标单词做为一个候选词，需要被排除掉
                if (end - start + 1 == target.length()) continue;
                String prev = target.substring(start, end + 1);//切出来[start,end]之间的字符作为一个候选单词进入下一轮递归
                //这个切出来的单词是在单词列表&&剩下的单词也在单词列表（可能需要再切）
                if (wordList.contains(prev) && dfs(target, end + 1, wordList)) return true;
            }
            return false;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String[] words = new String[]{""};
//            handler.longestWord(words);
            words = new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
            Assert.assertEquals("dogwalker", handler.longestWord(words));
        }


        public String longestWord(String[] words) {
            String res = "";
            List<String> wordList = Arrays.asList(words);
            wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
            for (String target : wordList) {
                if (check(target, wordList)) return target;
            }
            return res;
        }

        private boolean check(String target, List<String> wordList) {
            int n = target.length();
            if (n == 0) return false;
            System.out.println(target);
            boolean[] f = new boolean[n];
            for (int i = 0; i < n; i++) {
                //排除自生的结果，先切这部分
                if (i < n - 1 && wordList.contains(target.substring(0, i + 1))) {
                    System.out.printf("1:%s\n", target.substring(0, i + 1));
                    f[i] = true;
                    continue;
                }
                for (int j = i - 1; j >= 0; j--) {
                    System.out.printf("2:%s\n", target.substring(j + 1, i + 1));
                    //如dogwalker时， f[2] = dog 为true  [j+1,i+1] = walk => f[6] = true 同理=> f[8] = true
                    if (f[j] && wordList.contains(target.substring(j + 1, i + 1))) {
                        f[i] = true;
                        break;
                    }
                }

            }
            return f[n - 1];
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        TrieNode root;

        public String longestWord(String[] words) {
            this.root = new TrieNode();
            String res = "";
            List<String> wordList = Arrays.asList(words);
            //排序好，第一个返回的即是结果
            wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
            //构造字典树
            for (String word : wordList) insert(word);
            for (String word : wordList) {
                TrieNode cur = root;
                int n = word.length();
                for (int i = 0; i < n; i++) {
                    char c = word.charAt(i);
                    //排除掉自己组成自己，当前遍历到的字符是个单词，且剩余部分可以再次被切分
                    if (i < n - 1 && cur.children[c - 'a'].isEnd && canSplitToWord(word.substring(i + 1))) {
                        return word;
                    }
                    cur = cur.children[c - 'a'];
                }
            }
            return res;
        }

        /**
         * 当前的单词可以被切分，在wordList中找到
         *
         * @param remain
         * @return
         */
        private boolean canSplitToWord(String remain) {
            //当没有可以切分的了 返回True
            if (remain.equals("")) return true;
            TrieNode cur = root;
            for (int i = 0; i < remain.length(); i++) {
                char c = remain.charAt(i);//拿到当前的字符
                if (cur.children[c - 'a'] == null) return false;//这个节点找不到
                //当前的节点是个单词，且剩余部分可以再次被切分
                if (cur.children[c - 'a'].isEnd && canSplitToWord(remain.substring(i + 1))) {
                    return true;
                }
                cur = cur.children[c - 'a'];
            }
            return false;
        }

        /**
         * Trie树插入一个单词
         *
         * @param word
         */
        private void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.isEnd = true;
        }

        /**
         * 构建字典树的结构
         */
        class TrieNode {
            private TrieNode[] children;
            private boolean isEnd;//当前的字符是否是一个单词的结尾

            public TrieNode() {
                this.children = new TrieNode[26];
                this.isEnd = false;
            }
        }


    }


    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
        }

        TrieNode root;

        public String longestWord(String[] words) {
            this.root = new TrieNode();
            List<String> wordList = Arrays.asList(words);
            //排序好，第一个返回的即是结果
            wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
            //构造字典树
            for (String word : wordList) insert(word);
            for (String word : wordList) {
                TrieNode cur = root;
                int n = word.length();
                for (int i = 0; i < n; i++) {
                    char c = word.charAt(i);
                    //排除掉自己组成自己，当前遍历到的字符是个单词，且剩余部分可以再次被切分
                    if (i < n - 1 && cur.next.get(c).isEnd && canSplitToWord(word.substring(i + 1))) {
                        return word;
                    }
                    cur = cur.next.get(c);
                }
            }
            return "";
        }


        /**
         * 当前的单词可以被切分，在wordList中找到
         *
         * @param remain
         * @return
         */
        private boolean canSplitToWord(String remain) {
            //当没有可以切分的了 返回True
            if (remain.equals("")) return true;
            TrieNode cur = root;
            for (int i = 0; i < remain.length(); i++) {
                char c = remain.charAt(i);//拿到当前的字符
                if (!cur.next.containsKey(c)) return false;//这个节点找不到
                //当前的节点是个单词，且剩余部分可以再次被切分
                if (cur.next.get(c).isEnd && canSplitToWord(remain.substring(i + 1))) {
                    return true;
                }
                cur = cur.next.get(c);
            }
            return false;
        }


        private void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (!cur.next.containsKey(c)) {
                    TrieNode t = new TrieNode();
                    cur.next.put(c, t);
                }
                cur = cur.next.get(c);
            }
            cur.isEnd = true;
        }


        class TrieNode {
            Map<Character, TrieNode> next = new HashMap<>();
            boolean isEnd = false;
        }
    }


}
