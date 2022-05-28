package com.frankcooper.bank._901_1000;

public class _925 {

    static _925 handler = new _925();

    public static void main(String[] args) {
        String name = "kikcxmvzi";
        String typed = "kiikcxxmmvvzz";
        handler.isLongPressedName(name, typed);

    }


    public boolean isLongPressedName(String name, String typed) {
        int i, j;
        for (i = 0, j = 0; i < name.length() && j < typed.length(); ) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) return false;
                while (j < typed.length() && typed.charAt(j) == typed.charAt(j - 1)) j++;
                if (j < typed.length() && name.charAt(i) == typed.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return false;
                }

            }
        }
        if (i < name.length()) return false;
        while (j < typed.length()) {
            if (typed.charAt(j) == typed.charAt(j - 1)) j++;
            else return false;
        }
        return true;
    }


}
