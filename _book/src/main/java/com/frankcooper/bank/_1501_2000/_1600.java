package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1600 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class ThroneInheritance {

            Map<String, List<String>> parent2Children = new HashMap<>();//k是父亲节点，v是其孩子，列表
            Set<String> death = new HashSet<>();//死亡的人
            String king;//国王，根节点

            public ThroneInheritance(String kingName) {
                this.king = kingName;
            }

            public void birth(String parentName, String childName) {
                List<String> list = parent2Children.getOrDefault(parentName, new ArrayList<>());
                list.add(childName);
                parent2Children.put(parentName, list);
            }

            public void death(String name) {
                death.add(name);
            }

            public List<String> getInheritanceOrder() {
                List<String> curOrder = new ArrayList<>();
                dfs(king, curOrder);
                return curOrder;
            }


            private void dfs(String x, List<String> curOrder) {//前序遍历
                if (!death.contains(x)) curOrder.add(x);
                for (String child : parent2Children.getOrDefault(x, new ArrayList<>())) {
                    dfs(child, curOrder);
                }
            }

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
