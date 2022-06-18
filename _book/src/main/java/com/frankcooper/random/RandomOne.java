package com.frankcooper.classify.random;


import java.util.Arrays;
import java.util.Random;

public class RandomOne {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
            int n = arr.length;
            handler.randomize(arr, n);

        }


        public void randomize(int[] arr, int n) {
            Random random = new Random();
            for (int i = n - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                swap(arr, i, j);
            }
            System.out.println(Arrays.toString(arr));

        }

        private void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }


    }


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();


        }


    }
}
