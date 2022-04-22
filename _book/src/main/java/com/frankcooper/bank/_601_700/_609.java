package com.frankcooper.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _609 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public List<List<String>> findDuplicate(String[] paths) {
            Map<String, List<String>> map = new HashMap<>();
            for (String path : paths) {
                String[] values = path.split(" ");
                for (int i = 1; i < values.length; i++) {
                    String[] name_cont = values[i].split("\\(");
                    name_cont[1] = name_cont[1].replace(")", "");
                    List<String> list = map.getOrDefault(name_cont[1], new ArrayList<String>());
                    list.add(values[0] + "/" + name_cont[0]);
                    map.put(name_cont[1], list);
                }
            }
            List<List<String>> res = new ArrayList<>();
            for (String key : map.keySet()) {
                if (map.get(key).size() > 1) res.add(map.get(key));
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
