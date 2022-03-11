package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _03_03 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
    /*        StackOfPlates one = new StackOfPlates(1);
            one.push(1);
            one.push(2);
            one.popAt(1);
            one.pop();
            one.pop();*/

//            StackOfPlates two = new StackOfPlates(2);
//            two.push(1);
//            two.push(2);
//            two.push(3);
//            two.popAt(0);
//            two.popAt(0);
//            two.popAt(0);

//            StackOfPlates three =  new StackOfPlates()
            String[] ops = {"StackOfPlates", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop", "pop"};
            int[][] vals = new int[][]{{0}, {2}, {8}, {56}, {1}, {39}, {40}, {44}, {63}, {11}, {38}, {20}, {55}, {25}, {14}, {11}, {1}, {20}, {16}, {6}, {18}, {3}, {39}, {45}, {2}, {22}, {64}, {6}, {30}, {39}, {3}, {19}, {63}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};

            ops = new String[]{
                    "StackOfPlates", "pop", "popAt", "push", "popAt", "popAt", "pop", "pop", "push", "popAt", "pop", "push", "push", "pop", "popAt", "popAt", "push", "push", "push", "popAt", "pop", "pop", "pop", "popAt", "pop", "push", "popAt", "push", "push", "popAt", "push", "push", "pop", "popAt", "push", "pop", "popAt", "push", "pop", "push", "pop", "popAt", "popAt", "pop", "push", "push", "pop", "popAt", "push", "push", "pop", "pop", "popAt"
            };
            vals = new int[][]{{3}, {}, {1}, {1}, {2}, {2}, {}, {}, {9}, {3}, {}, {51}, {20}, {}, {2}, {0}, {35}, {1}, {19}, {3}, {}, {}, {}, {1}, {}, {36}, {1}, {19}, {3}, {3}, {15}, {44}, {}, {3}, {46}, {}, {0}, {42}, {}, {31}, {}, {0}, {2}, {}, {10}, {49}, {}, {1}, {14}, {50}, {}, {}, {3}};
            StackOfPlates three = null;
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                int val = vals[i].length == 0 ? -10 : vals[i][0];
                switch (op) {
                    case "StackOfPlates":
                        three = new StackOfPlates(val);
                        break;
                    case "push":
                        three.push(val);
                        System.out.printf("push:%d\n", val);
                        break;
                    case "pop":
                        int pop = three.pop();
                        System.out.printf("pop:%d\n", pop);
                        break;
                    case "popAt":
                        three.popAt(val);
                        break;
                    default:
                        break;
                }
            }

        }


        static class StackOfPlates {

            List<Stack<Integer>> list = new ArrayList<>();
            int cap;

            public StackOfPlates(int cap) {
                this.cap = cap;
            }

            public void push(int val) {
                if (cap <= 0) return;
                int n = list.size();
                if (list.isEmpty() || list.get(n - 1).size() == cap) {
                    Stack<Integer> stk = new Stack<>();
                    stk.push(val);
                    list.add(stk);
                    return;
                }
                list.get(n - 1).push(val);
            }

            public int pop() {
                return popAt(list.size() - 1);

            }

            public int popAt(int index) {
                int n = list.size();
                if (index < 0 || index > n - 1) return -1;
                Stack<Integer> stk = list.get(index);
                int res = stk.pop();
                if (stk.isEmpty()) list.remove(index);
                return res;
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
