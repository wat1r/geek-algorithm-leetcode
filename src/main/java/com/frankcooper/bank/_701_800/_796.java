package com.frankcooper.bank._701_800;

public class _796 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean rotateString(String s, String goal) {
            String t = "";
            for (int i = 0; i < s.length(); i++) {
                t = s.substring(i + 1) + s.substring(0, i + 1);
                if (t.equals(goal)) return true;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean rotateString(String s, String goal) {
            return s.length() == goal.length() && (s + s).contains(goal);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        //WA
        public boolean rotateString(String s, String goal) {
            if (s.length() != goal.length()) return false;
            if (s.equals(goal)) return true;
            int n = s.length();
            while (n-- > 0) {
                System.out.printf("%s ", s);
                if (s.equals(goal)) return true;
                s = s.charAt(0) + s.substring(1);
            }
            return false;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public boolean rotateString(String s, String goal) {
            if (s.length() != goal.length()) return false;
            for (int i = 0; i < s.length(); i++) {
                if (rotate(s, goal, i)) return true;
            }
            return false;
        }


        private boolean rotate(String s, String goal, int rotate) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != goal.charAt((i + rotate) % goal.length())) return false;
            }
            return true;
        }

    }
}
