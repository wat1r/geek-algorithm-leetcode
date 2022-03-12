package com.frankcooper.bank._101_200;

/**
 * @Date 2020/5/19
 * @Author Frank Cooper
 * @Description
 */
public class _125 {


    public boolean isPalindrome(String s) {
        char[] chas = s.toLowerCase().toCharArray();
        StringBuffer ctrlStr = new StringBuffer();
        for (int i = 0; i < chas.length; i++) {
            ctrlStr.append(onlyNumAndAlphabet(chas[i]));
        }
        return validate(ctrlStr.toString().toCharArray(), 0, ctrlStr.toString().length() - 1);
    }

    /**
     * 只去字符和数字
     *
     * @param cha
     * @return
     */
    public String onlyNumAndAlphabet(char cha) {
        StringBuffer sb = new StringBuffer();
        if ((cha >= '0' && cha <= '9') || (cha >= 'a' && cha <= 'z')) {
            sb.append(cha);
        }
        return sb.toString();
    }

    private boolean validate(char[] chas, int l, int r) {
        while (l < r) {
            if (chas[l++] != chas[r--]) return false;
        }
        return true;
    }

    static class _1st {
        public boolean isPalindrome(String s) {
            char[] ch = s.toCharArray();
            int l = 0, r = ch.length - 1;
            while (l < r) {
                while (l < r && !check(ch[l])) l++;
                while (r > l && !check(ch[r])) r--;
                int lc = ch[l] | ' ';
                int rc = ch[r] | ' ';
                if (lc != rc) return false;
                else {
                    l++;
                    r--;
                }
            }
            return true;
        }

        private boolean check(char c) {
            c = (char) (c | ' ');
            return (c >= '0' && c <= '9') || c >= 'a' && c <= 'z';
        }
    }
}



