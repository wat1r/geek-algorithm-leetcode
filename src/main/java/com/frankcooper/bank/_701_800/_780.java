package com.frankcooper.bank._701_800;

public class _780 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int sx = 1, sy = 1, tx = 3, ty = 5;
            handler.reachingPoints(sx, sy, tx, ty);

        }


        public boolean reachingPoints(int sx, int sy, int tx, int ty) {
            if (tx < sx || ty < sy) return false;
            if (tx > ty) {
                if (sy == ty) {
                    return sx >= (tx % ty) && (tx - sx) % sy == 0;
                }
                return reachingPoints(sx, sy, tx % ty, ty);
            } else if (tx < ty) {
                if (sx == tx) {
                    return sy >= (ty % tx) && (ty - sy) % sx == 0;
                }
                return reachingPoints(sx, sy, tx, ty % tx);
            } else {
                return tx == sx && ty == sy;
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean reachingPoints(int sx, int sy, int tx, int ty) {
            while (tx > sx && ty > sy && tx != ty) {
                if (tx > ty) tx %= ty;
                else ty %= tx;
            }
            if (tx == sx && ty == sy) return true;
            else if (tx == sx) {
                return ty > sy && (ty - sy) % tx == 0;
            } else if (ty == sy) {
                return tx > sx && (tx - sx) % ty == 0;
            } else {
                return false;
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
