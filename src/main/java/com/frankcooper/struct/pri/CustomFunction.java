package com.frankcooper.struct.pri;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/6/9 9:14
 * @description:
 */
public interface CustomFunction {
    // Returns f(x, y) for any given positive integers x and y.
    // Note that f(x, y) is increasing with respect to both x and y.
    // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
    public int f(int x, int y);
}
