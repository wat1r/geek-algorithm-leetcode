package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _08_13 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] box = new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};

            box = new int[][]{{3, 3, 8}, {3, 6, 5}, {7, 1, 6}};
            handler.pileBox(box);
        }


        public int pileBox(int[][] box) {
            Arrays.sort(box, (a, b) -> a[0] - b[0]);
            int[] f = new int[box.length];//f[i]表示前i个箱子能形成的最大高度
            f[0] = box[0][2];
            int res = f[0];
            for (int i = 1; i < box.length; i++) {
                int iw = box[i][0], id = box[i][1], ih = box[i][2];
                f[i] = ih;
                for (int j = 0; j < i; j++) {
                    int jw = box[j][0], jd = box[j][1], jh = box[j][2];
                    if (iw > jw && id > jd && ih > jh) {
                        f[i] = Math.max(f[i], f[j] + ih);
                    }
                }
                res = Math.max(res, f[i]);
            }
            return res;
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
