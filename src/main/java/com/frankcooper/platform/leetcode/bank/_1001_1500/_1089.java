package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1089 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public void duplicateZeros(int[] arr) {
            int n = arr.length;
            int i = 0, j = 0;
            while (j < n) {
                if (arr[i] == 0) j++;
                i++;
                j++;
            }
            i--;
            j--;
            while (i >= 0) {
                if (j < n) arr[j] = arr[i];
                if (arr[i] == 0) {
                    arr[--j] = arr[i];
                }
                i--;
                j--;
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
            handler.duplicateZeros(arr);
        }

        public void duplicateZeros(int[] arr) {
            int countZero = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) countZero++;
            }
            int len = arr.length + countZero;
            //We just need O(1) space if we scan from back
            //i point to the original array, j point to the new location
            for (int i = arr.length - 1, j = len - 1; i < j; i--, j--) {
                if (arr[i] != 0) {
                    if (j < arr.length) arr[j] = arr[i];
                } else {// 为0的情况下
                    if (j < arr.length) arr[j] = arr[i];
                    j--;
                    if (j < arr.length) arr[j] = arr[i]; //copy twice when hit '0'
                }
            }
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
