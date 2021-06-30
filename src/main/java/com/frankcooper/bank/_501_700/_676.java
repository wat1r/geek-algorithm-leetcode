package com.frankcooper.bank._501_700;

import java.util.*;

import org.junit.*;


public class _676 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            MagicDictionary magicDictionary = new MagicDictionary();
            magicDictionary.buildDict(new String[]{"hello", "leetcode"});
            Assert.assertFalse(magicDictionary.search("hello"));
            Assert.assertTrue(magicDictionary.search("hhllo"));
            Assert.assertFalse(magicDictionary.search("hell"));
            Assert.assertFalse(magicDictionary.search("leetcoded"));


        }

        static class TrieNode {
            private TrieNode[] next = new TrieNode[26];
            private boolean isEnd = false;
        }


        static class MagicDictionary {

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


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


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


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
