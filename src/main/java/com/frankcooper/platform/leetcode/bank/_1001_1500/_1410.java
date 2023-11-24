package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1410 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String text = "&amp; is an HTML entity but &ambassador; is not.";
            handler.entityParser(text);

        }

        public String entityParser(String text) {
            Map<String, String> map = new HashMap<String, String>() {{
                put("&quot;", "\"");
                put("&apos;", "'");
                put("&amp;", "&");
                put("&gt;", ">");
                put("&lt;", "<");
                put("&frasl;", "/");
            }};
            char[] chas = text.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chas.length; ) {
                if (chas[i] == '&') {
                    int j = i + 1;
                    while (j < chas.length && j < i + 6 && chas[j] != ';') {
                        j++;
                    }
                    String can = text.substring(i, Math.min(j + 1, chas.length));
                    if (map.containsKey(can)) {
                        sb.append(map.get(can));
                        i = j + 1;
                        continue;
                    }
                }
                sb.append(chas[i++]);
            }
            return sb.toString();
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
