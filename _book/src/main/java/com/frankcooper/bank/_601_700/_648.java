package com.frankcooper.bank._601_700;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class _648 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            List<String> dictionary = Arrays.asList("cat", "bat", "rat");
            String sentence = "the cattle was rattled by the battery";
            Assert.assertEquals(handler.replaceWords(dictionary, sentence), "the cat was rat by the bat");
            dictionary = Arrays.asList("a", "b", "c");
            sentence = "aadsfasf absbs bbab cadsfafs";
            Assert.assertEquals(handler.replaceWords(dictionary, sentence), "a a b c");
            dictionary = Arrays.asList("a", "aa", "aaa", "aaaa");
            sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
            Assert.assertEquals(handler.replaceWords(dictionary, sentence), "a a a a a a a a bbb baba a");
            dictionary = Arrays.asList("catt", "cat", "bat", "rat");
            sentence = "the cattle was rattled by the battery";
            Assert.assertEquals(handler.replaceWords(dictionary, sentence), "the cat was rat by the bat");
        }


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


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
