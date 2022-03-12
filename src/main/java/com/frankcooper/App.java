package com.frankcooper;

import com.alibaba.fastjson.JSON;

/**
 * Hello world!
 */
public class App {
    static App handler = new App();

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] arr = new int[]{1, 1, 0, 6};
        arr = new int[]{1, 19, 0, 15, 30, 12, 49, 8, 23, 3};

        handler.process(arr);
    }


    public int process(int[] arr) {
        int n = arr.length, dressTotal = 0;
        for (int m : arr) dressTotal += m;
        if (dressTotal % n != 0) return -1;

        int seg = dressTotal / n;
        for (int i = 0; i < n; i++) arr[i] -= seg;
        int currSum = 0, maxSum = 0, tmpRes = 0, res = 0;
        for (int m : arr) {
            currSum += m;
            maxSum = Math.max(maxSum, Math.abs(currSum));
            tmpRes = Math.max(maxSum, m);
            res = Math.max(res, tmpRes);
        }
        //[-15,3,-16,-1,14,-4,33,-8,7,-13]--->33
        System.out.println(JSON.toJSONString(arr) + "--->" + res);
        return res;
    }


    public static void test() {

        System.out.println("test");
    }
}
