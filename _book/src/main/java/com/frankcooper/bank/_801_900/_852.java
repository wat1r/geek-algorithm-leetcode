package com.frankcooper.bank._801_900;

public class _852 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int peakIndexInMountainArray(int[] arr) {
            int l = 1, r = arr.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                //[l,mid]严格递增，排除
                if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int peakIndexInMountainArray(int[] arr) {
            int l = 0, r = arr.length - 2;
            while (l < r) {
                int mid = (1 + r + l) >> 1;
                if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            return l;
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
