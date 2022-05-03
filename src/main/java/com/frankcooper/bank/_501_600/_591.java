package com.frankcooper.bank._501_600;

import java.util.*;

import org.junit.Assert;

public class _591 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            Assert.assertFalse(handler.isValid("<![CDATA[wahaha]]]><![CDATA[]> wahaha]]>"));
//            Assert.assertFalse(handler.isValid("<A></A><B></B>"));
            Assert.assertFalse(handler.isValid("<AAAAAAAAAA></AAAAAAAAAA>"));
        }


        public boolean isValid(String code) {
            //<![CDATA[    ]]>     </
            final int CDATA_START_LENGTH = 9, CDATA_END_LENGTH = 3, END_TAG_LENGTH = 2;
            int len = code.length();
            Deque<String> stk = new ArrayDeque<>();
            int index = 0;
            while (index < len) {
                //case: <A></A><B></B> 该情况下说明还没有遍历完，标签已经找到闭合的
                if (index > 0 && stk.isEmpty()) {
                    return false;
                }
                if (index + CDATA_START_LENGTH <= len && code.substring(index, index + CDATA_START_LENGTH).equals("<![CDATA[")) {
                    index += CDATA_START_LENGTH;
                    while (index + CDATA_END_LENGTH <= len && !code.substring(index, index + CDATA_END_LENGTH).equals("]]>")) {
                        index++;
                    }
                    index += CDATA_END_LENGTH;
                    if (index + CDATA_END_LENGTH > len) {
                        return false;
                    }
                } else if (index + END_TAG_LENGTH <= len && code.substring(index, index + END_TAG_LENGTH).equals("</")) {
                    index += END_TAG_LENGTH;
                    int start = index;
                    while (index < len && code.charAt(index) != '>') {
                        index++;
                    }
                    if (index >= len) return false;
                    String endTag = code.substring(start, index);
                    if (stk.isEmpty() || !stk.peek().equals(endTag)) return false;
                    stk.pop();
                    index++;
                } else if (code.charAt(index) == '<') {
                    index++;
                    int start = index;
                    while (index < len && code.charAt(index) != '>') {
                        index++;
                    }
                    //case1: <DIV><></></DIV>
                    //case2: <AAAAAAAAAA></AAAAAAAAAA>
                    if (index >= len || index == start || index - start > 9) {
                        return false;
                    }
                    for (int i = start; i < index; i++) {
                        char c = code.charAt(i);
                        if (c < 'A' || c > 'Z') {
                            return false;
                        }
                    }
                    String beginTag = code.substring(start, index);
                    stk.push(beginTag);
                    index++;
                } else {
                    index++;
                }

            }
            return stk.isEmpty();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertFalse(handler.isValid("<![CDATA[wahaha]]]><![CDATA[]> wahaha]]>"));
//            Assert.assertFalse(handler.isValid("<A></A><B></B>"));
            Assert.assertFalse(handler.isValid("<DIV><></></DIV>"));
        }


        public boolean isValid(String code) {
            final int CDATA_START_LENGTH = 9, CDATA_END_LENGTH = 3, END_TAG_LENGTH = 2;
            Deque<String> stack = new ArrayDeque<String>();
            int length = code.length();
            int index = 0;
            while (index < length) {
                if (index > 0 && stack.isEmpty()) {
                    return false;
                }
                if (index + CDATA_START_LENGTH <= length && code.substring(index, index + CDATA_START_LENGTH).equals("<![CDATA[")) {
                    index += CDATA_START_LENGTH;
                    while (index <= length - CDATA_END_LENGTH && !code.substring(index, index + CDATA_END_LENGTH).equals("]]>")) {
                        index++;
                    }
                    if (index > length - CDATA_END_LENGTH) {
                        return false;
                    }
                    index += CDATA_END_LENGTH;
                } else if (index + END_TAG_LENGTH <= length && code.substring(index, index + END_TAG_LENGTH).equals("</")) {
                    index += END_TAG_LENGTH;
                    int start = index;
                    while (index < length && code.charAt(index) != '>') {
                        index++;
                    }
                    if (index >= length) {
                        return false;
                    }
                    String tag = code.substring(start, index);
                    if (stack.isEmpty() || !stack.peek().equals(tag)) {
                        return false;
                    }
                    stack.pop();
                    index++;
                } else if (code.charAt(index) == '<') {
                    index++;
                    int start = index;
                    while (index < length && code.charAt(index) != '>') {
                        index++;
                    }
                    if (index >= length || index == start || index - start > 9) {
                        return false;
                    }
                    for (int i = start; i < index; i++) {
                        char c = code.charAt(i);
                        if (c < 'A' || c > 'Z') {
                            return false;
                        }
                    }
                    String tag = code.substring(start, index);
                    stack.push(tag);
                    index++;
                } else {
                    index++;
                }
            }
            return stack.isEmpty();
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
