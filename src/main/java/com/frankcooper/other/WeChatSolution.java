package com.frankcooper.other;

import com.alibaba.fastjson.JSON;
import com.frankcooper.swordoffer.utils.PrintUtils;

import java.sql.Timestamp;
import java.util.*;

public class WeChatSolution {


    static class _1st {

        static _1st handler = new _1st();

        double[] scores;
        double[][] userScores;
        int N, M;

        public static void main(String[] args) {
            handler.autoProcess();
            handler.dfs(new ArrayList<>());
            handler.find();
        }


        private void autoProcess() {
            Scanner in = new Scanner(System.in);
            N = in.nextInt();
            M = in.nextInt();
            scores = new double[N];
            for (int i = 0; i < N; i++) {
                scores[i] = in.nextDouble();
            }
            userScores = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    userScores[i][j] = in.nextDouble();
                }
            }

            PrintUtils.printMatrix(userScores);
        }


        List<int[]> resList = new ArrayList<>();

        private void dfs(List<Integer> list) {
            if (list.size() == M) {
                resList.add(new ArrayList<>(list).stream().mapToInt(Integer::intValue).toArray());
                return;
            }
            for (int i = 0; i < N; i++) {
                if (list.contains(i)) continue;
                list.add(i);
                dfs(list);
                list.remove(list.size() - 1);
            }
        }


        double max = 0.0;

        private void find() {
            for (int[] arr : resList) {
                Map<Integer, List<Integer>> vis = new HashMap<>();
                double tmp = 0.0;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        tmp += scores[arr[i]];
                        tmp += scores[arr[j]];
                        if (vis.get(arr[i]) == null || !vis.get(arr[i]).contains(arr[j])) {
                            tmp += userScores[arr[i]][arr[j]];
                            List<Integer> orDefault = vis.getOrDefault(arr[i], new ArrayList<Integer>());
                            orDefault.add(arr[j]);
                            vis.put(arr[i], orDefault);
                        }
                    }
                }
                if (tmp > max) {
                    max = tmp;
                    System.out.println(tmp);
                    System.out.println(JSON.toJSONString(arr));
                }
            }
        }

    }


    static class _2nd {
        public static void main(String[] args) {

            Long res = (long) Integer.MAX_VALUE * 60;
            System.out.println(res);

//            Timestamp timestamp = new Timestamp();
//            timestamp.getTime()
            System.out.println(Long.MAX_VALUE & 0xffffffffL);
            System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(Integer.MAX_VALUE), 50));
            System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(0xffffffff), 50));
            System.out.println(PrintUtils.addZeroForNum(Long.toBinaryString(0xffffffffL), 50));
            System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(-1), 50));

            /**
             * (int)(timestamp.gettime/1000+endtime*60)&0xffffffff
             */
        }
    }

}
