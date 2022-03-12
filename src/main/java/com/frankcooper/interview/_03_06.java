package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _03_06 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class AnimalShelf {


            LinkedList<Integer> dog = new LinkedList<>();
            LinkedList<Integer> cat = new LinkedList<>();


            public AnimalShelf() {

            }

            public void enqueue(int[] animal) {
                if (animal[1] == 0) {
                    cat.add(animal[0]);
                }
                if (animal[1] == 1) dog.add(animal[0]);
            }

            public int[] dequeueAny() {
                if (dog.isEmpty() && cat.isEmpty()) return new int[]{-1, -1};
                if (dog.isEmpty()) return new int[]{cat.pollFirst(), 0};
                if (cat.isEmpty()) return new int[]{dog.pollFirst(), 1};
                if (dog.peekFirst() < cat.peekFirst()) return new int[]{dog.pollFirst(), 1};
                return new int[]{cat.pollFirst(), 0};
            }

            public int[] dequeueDog() {
                if (dog.isEmpty()) return new int[]{-1, -1};
                return new int[]{dog.pollFirst(), 1};
            }

            public int[] dequeueCat() {
                if (cat.isEmpty()) return new int[]{-1, -1};
                return new int[]{cat.pollFirst(), 0};
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
