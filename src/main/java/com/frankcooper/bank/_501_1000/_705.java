package com.frankcooper.bank._501_1000;

import org.omg.CORBA.INTERNAL;

import java.util.Iterator;
import java.util.LinkedList;

public class _705 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            MyHashSet myHashSet = new MyHashSet();
            /**
             * ["MyHashSet","add","add","contains","contains","add","contains","remove","contains"]
             * [[],[1],[2],[1],[3],[2],[2],[2],[2]]
             */
            myHashSet.add(1);
            myHashSet.add(2);
            myHashSet.contains(1);
            myHashSet.contains(3);
            myHashSet.add(2);
            myHashSet.contains(2);
            myHashSet.remove(2);
            myHashSet.contains(2);

        }


        static class MyHashSet {

            private LinkedList[] data;
            private int CAPACITY = 787;

            public MyHashSet() {
                data = new LinkedList[CAPACITY];
                for (int i = 0; i < CAPACITY; i++) {
                    data[i] = new LinkedList<Integer>();
                }
            }

            public void add(int key) {
                int hash = hash(key);
                Iterator<Integer> it = data[hash].iterator();
                while (it.hasNext()) {
                    int curr = it.next();
                    if (curr == key) return;
                }
                data[hash].offerLast(key);
            }

            public void remove(int key) {
                int hash = hash(key);
                Iterator<Integer> it = data[hash].iterator();
                while (it.hasNext()) {
                    Integer curr = it.next();//用Integer 不用int
                    System.out.printf("curr:%d\n", curr);
                    if (curr == key) {
                        data[hash].remove(curr);
                        return;
                    }
                }
            }

            /**
             * Returns true if this set contains the specified element
             */
            public boolean contains(int key) {
                int hash = hash(key);
                Iterator<Integer> it = data[hash].iterator();
                while (it.hasNext()) {
                    int curr = it.next();
                    if (curr == key) {
                        return true;
                    }
                }
                return false;
            }

            private int hash(int key) {
                return key % CAPACITY;
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
}
