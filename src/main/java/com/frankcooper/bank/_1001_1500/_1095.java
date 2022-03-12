package com.frankcooper.bank._1001_1500;

import com.frankcooper.struct.pri.MountainArray;

public class _1095 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] nums = {1, 2, 3, 4, 5, 3, 1};
            int target = 3;
//            handler.getTop(nums);
//            handler.process(nums, target);

            nums = new int[]{0, 1, 2, 4, 2, 1};
            target = 3;
//            handler.process(nums, target);


        }

        static class MountainArrayImpl implements MountainArray {
            private int[] arr;
            private int size;

            public MountainArrayImpl(int[] arr, int size) {
                this.arr = arr;
                this.size = size;
            }

            @Override
            public int get(int index) {
                return this.arr[index];
            }

            @Override
            public int length() {
                return this.size;
            }
        }


        public int findInMountainArray(int target, MountainArray mountainArr) {
            int size = mountainArr.length();
            int top = getTop(mountainArr, 0, size - 1);
            System.out.printf("%d\n", top);
            int ans = findIncrease(mountainArr, target, 0, top);
            if (ans != -1) return ans;
            System.out.printf("size:%d\n", size);
            ans = findDecrease(mountainArr, target, top, size - 1);
            return ans;
        }


        private int getTop(MountainArray arr, int l, int r) {
            while (l < r) {
                int m = l + (r - l) / 2;
                if (arr.get(m + 1) > arr.get(m)) l = m + 1;
                else r = m;
            }
            return l;
        }

        private int findIncrease(MountainArray arr, int target, int l, int r) {
            while (l < r) {
                int m = l + (r - l) / 2;
                if (arr.get(m) == target) return m;
                else if (arr.get(m) < target) {
                    l = m + 1;
                } else if (arr.get(m) > target) {
                    r = m;
                }
            }
            return -1;
        }

        private int findDecrease(MountainArray arr, int target, int l, int r) {
            while (l < r) {
                int m = l + (r - l) / 2;
                System.out.printf("m: %d\n", m);
                if (arr.get(m) == target) return m;
                else if (arr.get(m) > target) {
                    l = m + 1;
                } else if (arr.get(m) < target) {
                    r = m;
                }
            }
            return arr.get(l) == target ? l : -1;//注意这里的
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


