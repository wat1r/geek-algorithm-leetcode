package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _01_02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean CheckPermutation(String s1, String s2) {
            if(s1.length()!=s2.length())return false;
            int[] s = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                s[s1.charAt(i)-'a']++;
                s[s2.charAt(i)-'a']--;
            }
            for(int i =0;i<s.length;i++)
                if(s[i]!=0) return false;
            return true;
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
