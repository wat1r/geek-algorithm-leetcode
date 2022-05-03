package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _937 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
            handler.reorderLogFiles(logs);


        }


        //a和b排序，如果return -1（或其他负数)，那么排序后a在b左边。如果return 1（或其他整数），那么排序后b在a左边。
        public String[] reorderLogFiles(String[] logs) {
            Arrays.sort(logs, (a, b) -> {
                //limit表示分割的份数 dig1 8 1 5 1 -> "dig1"与"8 1 5 1"
                //let1 art can -> "let1"与"art can"
                String[] splitA = a.split(" ", 2);
                String[] splitB = b.split(" ", 2);
                //判断日志的类型
                boolean aIsDigit = Character.isDigit(splitA[1].charAt(0));
                boolean bIsDigit = Character.isDigit(splitB[1].charAt(0));
                //a和b都是数字日志，保留原来的相对顺序 return 0;
                if (aIsDigit && bIsDigit) return 0;
                    //a是字母日志，b是数字日志 字母日志在数字日志之前 return -1;
                else if (!aIsDigit && bIsDigit) return -1;
                    //a是数字日志，b是字母日志 字母日志在数字日志之前 return 1;
                else if (aIsDigit & !bIsDigit) return 1;
                    //a和b都是字母日志 内容不同，按内容排序
                    //内容相同，按标识符来排序
                else if (!aIsDigit && !bIsDigit) {
                    return !splitA[1].equals(splitB[1]) ? splitA[1].compareTo(splitB[1])
                            : splitA[0].compareTo(splitB[0]);
                }
                return 0;

            });
            return logs;

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
