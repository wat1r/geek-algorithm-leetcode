package com.frankcooper.platform.leetcode.bank._901_1000;

import java.util.*;

public class _929 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] emails = new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
            handler.numUniqueEmails(emails);
        }


        public int numUniqueEmails(String[] emails) {
            Map<String, Set<String>> map = new HashMap<>();
            for (String email : emails) {
                String[] arr = email.split("@");
                char[] ch = arr[0].toCharArray();
                int i = 0;
                for (int j = 0; j < ch.length; ) {
                    if (ch[j] == '.') {
                        j++;
                        continue;
                    }
                    if (ch[j] == '+') break;
                    char t = ch[i];
                    ch[i++] = ch[j];
                    ch[j++] = t;
                }
                Set<String> set = map.getOrDefault(arr[1], new HashSet<>());
                set.add(String.valueOf(ch).substring(0, i));
                map.put(arr[1], set);
            }
            int res = 0;
            for (String b : map.keySet()) {
                res += map.get(b).size();
            }
            return res;
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
