package com.frankcooper.bank;

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


}
