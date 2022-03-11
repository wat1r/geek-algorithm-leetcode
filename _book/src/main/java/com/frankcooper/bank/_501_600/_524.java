package com.frankcooper.bank._501_600;

import java.util.*;

import com.google.common.collect.Lists;
import org.junit.Assert;

public class _524 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "abpcplea";
            List<String> dictionary = Lists.newArrayList("ale", "apple", "monkey", "plea");
//            for (String target : dictionary) {
//                System.out.printf("%s->%s\n", target, handler.check(s, target));
//            }
//            Assert.assertEquals("apple", handler.findLongestWord(s, dictionary));
            s = "abpcplea";
            dictionary = Lists.newArrayList("a", "b", "c");
//            Assert.assertEquals("a", handler.findLongestWord(s, dictionary));


            String s1 = "ettphsiunoglotjlccurlre", s2 = "ntgcykxhdfescxxypyew";
            System.out.printf("%d:%d;%s\n", s1.length(), s2.length(), s1.compareTo(s2));

            s = "mjmqqjrmzkvhxlyruonekhhofpzzslupzojfuoztvzmmqvmlhgqxehojfowtrinbatjujaxekbcydldglkbxsqbbnrkhfdnpfbuaktupfftiljwpgglkjqunvithzlzpgikixqeuimmtbiskemplcvljqgvlzvnqxgedxqnznddkiujwhdefziydtquoudzxstpjjitmiimbjfgfjikkjycwgnpdxpeppsturjwkgnifinccvqzwlbmgpdaodzptyrjjkbqmgdrftfbwgimsmjpknuqtijrsnwvtytqqvookinzmkkkrkgwafohflvuedssukjgipgmypakhlckvizmqvycvbxhlljzejcaijqnfgobuhuiahtmxfzoplmmjfxtggwwxliplntkfuxjcnzcqsaagahbbneugiocexcfpszzomumfqpaiydssmihdoewahoswhlnpctjmkyufsvjlrflfiktndubnymenlmpyrhjxfdcq";
            dictionary = Lists.newArrayList("ettphsiunoglotjlccurlre", "ntgcykxhdfescxxypyew");

            Assert.assertEquals("ntgcykxhdfescxxypyew", handler.findLongestWord(s, dictionary));


        }

        public String findLongestWord(String s, List<String> dictionary) {
            String res = "";
            for (String target : dictionary) {
                if (target.length() >= res.length() && check(s, target)) {
                    if (res.equals("") ||
                            (res.length() == target.length() && res.compareTo(target) > 0)
                            || target.length() > res.length()) {
                        res = target;
                    }
                }
            }
            return res;
        }


        private boolean check(String source, String target) {
            int i = 0, j = 0;
            while (i < source.length()) {
                while (i < source.length() && source.charAt(i) != target.charAt(j)) {
                    i++;
                }
                i++;
                j++;
                //i <= source.length() 这个条件时判断source到结尾的位置
                if (i <= source.length() && j == target.length()) return true;
            }
            return false;
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
