package com.frankcooper.bank;

/**
 * @Date 2020/8/3
 * @Author Frank Cooper
 * @Description
 */
public class _415 {

    static _415 handler = new _415();

    public static void main(String[] args) {
        handler.addStrings("1", "9");


    }

    public String addStrings(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int i = m - 1, j = n - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            int tmp = 0;
            int first = i < 0 ? 0 : num1.charAt(i) - '0';
            int second = j < 0 ? 0 : num2.charAt(j) - '0';
            tmp = first + second + carry;
            carry = tmp / 10;
            int remain = tmp % 10;
            res.append(remain);
            i--;
            j--;
        }
        return res.reverse().toString();
    }

}
