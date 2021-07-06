package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1418 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<List<String>> displayTable(List<List<String>> orders) {
            List<List<String>> res = new ArrayList<>();
            Map<Integer, Map<String, Integer>> map = new HashMap<>();
            Set<Integer> tablesSet = new HashSet<>();
            Set<String> foodsSet = new HashSet<>();
            for (List<String> order : orders) {
                String customerName = order.get(0);
                Integer tableNumber = Integer.valueOf(order.get(1));
                String foodItem = order.get(2);
                tablesSet.add(tableNumber);
                foodsSet.add(foodItem);
                Map<String, Integer> tmap = map.getOrDefault(tableNumber, new HashMap<>());
                tmap.put(foodItem, tmap.getOrDefault(foodItem, 0) + 1);
                map.put(tableNumber, tmap);
            }
            List<Integer> tables = new ArrayList<>(tablesSet);
            Collections.sort(tables);
            List<String> foods = new ArrayList<>(foodsSet);
            Collections.sort(foods);
            List<String> head = new ArrayList<>();
            head.add("Table");
            head.addAll(foods);
            res.add(head);
            for (int i = 0; i < tables.size(); i++) {
                List<String> tl = new ArrayList<>();
                res.add(tl);
                Integer table = tables.get(i);
                tl.add(String.valueOf(table));
                Map<String, Integer> tm = map.get(table);
                for (String food : foods) {
                    System.out.printf("%s\n", food);
                    tl.add(tm.containsKey(food) ? String.valueOf(tm.get(food)) : "0");
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
