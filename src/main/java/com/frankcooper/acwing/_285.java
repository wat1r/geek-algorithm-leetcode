package com.frankcooper.acwing;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Scanner;

public class _285 {


    static _285 handler = new _285();


    static class Main {

        static Main main = new Main();

        public static void main(String[] args) {
            main.process();
        }


        //        int N = 6010; //N名员工
        int N = 8; //N名员工
        int[] happy = new int[N]; //员工的快乐指数 H
        int[] head = new int[N]; //
        int[] edge = new int[N];//边
        int[] neighbour = new int[N];//
        int idx = 0;
        int[][] f = new int[N][2];
        boolean[] hasFather = new boolean[N];

        private void process() {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            for (int i = 1; i <= n; i++) happy[i] = in.nextInt();
            Arrays.fill(head, -1);
            for (int i = 0; i < n - 1; i++) {
                int a = in.nextInt(), b = in.nextInt();
                add(b, a);
                hasFather[a] = true;
            }
            System.out.println(JSON.toJSONString(happy));
            System.out.println(JSON.toJSONString(head));
            System.out.println(JSON.toJSONString(edge));
            System.out.println(JSON.toJSONString(neighbour));
        }

        /**
         * @param a father
         * @param b son
         */
        private void add(int a, int b) {
            edge[idx] = b;
            neighbour[idx] = head[a];
            head[a] = idx++;
        }

    }


}
