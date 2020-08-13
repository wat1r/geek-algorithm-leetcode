package com.frankcooper.bank;

/**
 * @Date 2020/8/13
 * @Author Frank Cooper
 * @Description
 */
public class _43 {
    static _43 handler = new _43();

    public static void main(String[] args) {
        String num1 = "123", num2 = "456";
        num1 = "9";
        num2 = "9";
        handler.multiply1st(num1, num2);
    }


    public String multiply1st(String num1, String num2) {
        String res = "0";
        if (num1.equals("0") || num2.equals("0")) return res;
        int len1 = num1.length(), len2 = num2.length();
        for (int i = len2 - 1; i >= 0; i--) {
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            int k = len2 - 1 - i;
            while (k > 0) {
                sb.append("0");
                k--;
            }
            int n2 = num2.charAt(i) - '0';
            for (int j = len1 - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int seg = n2 * n1 + carry;
                sb.append(seg % 10);
                carry = seg / 10;
            }
            res = addStrings(res, sb.reverse().toString());
        }
        return res;
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
