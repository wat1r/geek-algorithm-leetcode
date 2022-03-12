package com.frankcooper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] arr = reader.readLine().split(" ");
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);
        int n = x + y;
        if (x == y) {
            for (int i = 0; i < x; i++) writer.write("A");
            for (int i = 0; i < y; i++) writer.write("B");
        } else {
            List<int[]> vals = new ArrayList<>();
            arr = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                int t = Integer.parseInt(arr[i]);
                vals.add(new int[]{t, i});
            }
            vals.sort((Comparator.comparingInt(o -> o[0])));
            int[] res = new int[n];
            for (int i = 0; i < n; i++) res[vals.get(i)[1]] = i;
            for (int i = 0; i < n; i++) {
                if (x > y) {
                    if (res[i] >= y) writer.write("A");
                    else writer.write("B");
                } else {
                    if (res[i] < x) writer.write("A");
                    else writer.write("B");
                }
            }
        }
        writer.flush();
        reader.close();
        writer.close();
    }

}
