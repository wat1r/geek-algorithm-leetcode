package com.frankcooper.bank._101_200;

import java.util.*;
import org.junit.Assert;
public class _187 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public List<String> findRepeatedDnaSequences(String s) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length() - 9; i++) {
                String sequences = s.substring(i, i + 10);
                map.put(sequences, map.getOrDefault(sequences, 0) + 1);
            }
            ArrayList<String> res = new ArrayList<>();
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() > 1) {
                    res.add(e.getKey());
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
