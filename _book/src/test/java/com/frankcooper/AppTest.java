package com.frankcooper;

import static org.junit.Assert.assertTrue;

import com.frankcooper.utils.PrintUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    /**
     * https://www.cnblogs.com/JohnTeslaaa/p/12573075.html
     */
    @Test
    public void testList2Array() {
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        int[] arr = new int[list2.size()];
//        arr=list2.toArray(new int[0]);
        Integer[] ans = list2.toArray(new Integer[list2.size()]);
        Arrays.stream(ans).forEach(System.out::println);

        System.out.println("------------------------------");
        List<int[]> list3 = new ArrayList<>();
        list3.add(new int[]{0, 111});
        list3.add(new int[]{1, 222});
        int[][] result = list3.toArray(new int[0][0]);
        for (int[] r : result)
            System.out.println(r[0] + "-->" + r[1]);
    }


    @Test
    public void testWheelTimer() {
        int cap = 4096;
        formatSize(cap);
    }


    public static int formatSize(int cap) {
        PrintUtils.toBinaryString(cap, 16);
        int n = cap - 1;
        PrintUtils.toBinaryString(n, 16);
        n |= n >>> 1;
        PrintUtils.toBinaryString(n, 16);
        n |= n >>> 2;
        PrintUtils.toBinaryString(n, 16);
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }


}
