package com.frankcooper;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt();
        int e = sc.nextInt(), f = sc.nextInt(), g = sc.nextInt();
        List<int[]> list = new ArrayList<int[]>() {{
            add(new int[]{e, a});
            add(new int[]{f, b});
            add(new int[]{g, c});
        }};
        list.sort(((o1, o2) -> o2[0] - o1[0]));
        long res = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            int value = cur[0];
            int cnt = Math.min(cur[1], d);
            res += (long) value * cnt;
            d -= cnt;
        }
        System.out.printf("%d", res);
    }

}
