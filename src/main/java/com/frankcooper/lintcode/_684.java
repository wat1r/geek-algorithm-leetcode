package com.frankcooper.lintcode;

import java.util.*;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Assert;

public class _684 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<String> missingString(String str1, String str2) {
            // Write your code here
            List<String> res = new ArrayList<>();
            if (str1.length() > str2.length()) {
                String temp = str1;
                str1 = str2;
                str2 = temp;
            }

            String[] arr1 = str1.split(" ");
            String[] arr2 = str2.split(" ");
            Set<String> set = new HashSet<>(Arrays.asList(arr1));
            for (String str : arr2) {
                if (!set.contains(str)) {
                    res.add(str);
                }
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
