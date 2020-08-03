package com.frankcooper.bank;

/**
 * @Date 2020/8/3
 * @Author Frank Cooper
 * @Description
 */
public class _445 {
    static _445 handler = new _445();

    public static void main(String[] args) {
        handler.addDigits(3182);
    }


    public int addDigits(int num) {
        while (num >= 10) {
            int remain = 0;
            while (num > 0) {
                remain += num % 10;
                num /= 10;
            }
            num = remain;
        }
        return num;
    }

}
