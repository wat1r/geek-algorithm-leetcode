package com.frankcooper.bank._701_1000;

import java.util.Iterator;
import java.util.LinkedList;

public class _706 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class Pair {
            private int key;
            private int val;

            public Pair(int key, int val) {
                this.key = key;
                this.val = val;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
            }

            public int getVal() {
                return val;
            }

            public void setVal(int val) {
                this.val = val;
            }
        }


        class MyHashMap {

            private LinkedList<Pair>[] data;
            private int CAPACITY = 787;

            /**
             * Initialize your data structure here.
             */
            public MyHashMap() {
                data = new LinkedList[CAPACITY];
                for (int i = 0; i < CAPACITY; i++) data[i] = new LinkedList<>();
            }

            /**
             * value will always be non-negative.
             */
            public void put(int key, int value) {
                int hash = hash(key);
                Iterator<Pair> it = data[hash].iterator();
                while (it.hasNext()) {
                    Pair curr = it.next();
                    if (curr.getKey() == key) {
                        curr.setVal(value);
                        return;
                    }
                }
                data[hash].offerLast(new Pair(key, value));
            }

            /**
             * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
             */
            public int get(int key) {
                int hash = hash(key);
                Iterator<Pair> it = data[hash].iterator();
                while (it.hasNext()) {
                    Pair curr = it.next();
                    if (curr.getKey() == key) {
                        return curr.getVal();
                    }
                }
                return -1;
            }

            /**
             * Removes the mapping of the specified value key if this map contains a mapping for the key
             */
            public void remove(int key) {
                int hash = hash(key);
                Iterator<Pair> it = data[hash].iterator();
                while (it.hasNext()) {
                    Pair curr = it.next();
                    if (curr.getKey() == key) {
                        data[hash].remove(curr);
                        return;
                    }
                }

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
