package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

public class _497 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[][] _rects = {{-2, -2, 1, 1}, {2, 2, 4, 6}};
            Solution solution = new Solution(_rects);
            solution.pick();
            solution.pick();

        }

        static class Solution {
            Random random = new Random();
            int[][] rects;
            List<Integer> arr;//标记每个rect的开始的位置
            int n;

            public Solution(int[][] _rects) {
                rects = _rects;
                arr = new ArrayList<>();
                arr.add(0);
                for (int[] p : rects) {
                    int a = p[0], b = p[1], x = p[2], y = p[3];
                    //上个位置+当前rect中整数点的个数
                    arr.add(arr.get(arr.size() - 1) + (x - a + 1) * (y - b + 1));
                }
                n = arr.size();
            }

            public int[] pick() {
                int k = random.nextInt(arr.get(n - 1));//获取[0...n-1]之间的一个随机数
                //k+1的写法 如果没有 +1 上面的 k可能会是0 这样返回的rectIndex=-1 会越界
                int rectIndex = binarySearch(arr, k + 1) - 1;//获取k应该在的rects中的索引位置
                k -= arr.get(rectIndex);//将k变成相对量,当前rectIndex上的增量
                int[] p = rects[rectIndex];//rectIndex的信息
                int a = p[0], b = p[1], x = p[2], y = p[3];
                int col = y - b + 1;//列上有多少个点，算上边框
                int delta_a = k / col;//在a这个点的增量
                int delta_b = k - col * delta_a;//剩下的是b的增量
                return new int[]{a + delta_a, b + delta_b};//在[a,b]点上增加增量返回
            }


            public int binarySearch(List<Integer> arr, int target) {
                int lo = 0, hi = n - 1;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;//下取整
                    if (arr.get(mid) == target) return mid;
                    else if (arr.get(mid) > target) hi = mid - 1;
                    else lo = mid + 1;
                }
                return lo;//返回是 lo = hi+1
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        class Solution {
            int[][] rects;
            Random random = new Random();


            public Solution(int[][] _rects) {
                rects = _rects;
            }

            public int[] pick() { //等效从一堆点中抽一个点，若在某个矩形中包含被抽到的点，则等效抽到这个矩形
                int index = -1, n = 0; //分别记录抽到的矩形下标、当前点的总数
                for (int i = 0; i < rects.length; i++) {
                    int a = rects[i][0], b = rects[i][1], x = rects[i][2], y = rects[i][3];
                    int k = (x - a + 1) * (y - b + 1); //当前矩形包含的点数量
                    n += k;
                    if (random.nextInt(n) < k) index = i; //当前矩形有k/n的概率被保留
                }
                int[] rect = rects[index]; //抽到矩形后，再从这个矩形中随机抽取x、y的值
                int x1 = rect[0], x2 = rect[2], y1 = rect[1], y2 = rect[3];
                return new int[]{x1 + random.nextInt(x2 - x1 + 1), y1 + random.nextInt(y2 - y1 + 1)};
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
