package com.frankcooper.classify.random;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReservoirSampling {

    static ExecutorService exec = Executors.newFixedThreadPool(4);

    // 抽样任务，用作模拟并行抽样
    private static class SampleTask implements Callable<int[]> {
        // 输入该任务的数据
        private int[] innerData;
        // 蓄水池容量
        private int m;

        SampleTask(int m, int[] innerData) {
            this.innerData = innerData;
            this.m = m;
        }

        @Override
        public int[] call() throws Exception {
            int[] reservoir = sample(this.m, this.innerData);
            return reservoir;
        }

    }

    // 并行抽样
    public static int[] mutiSample(int m, int[] dataStream) throws InterruptedException, ExecutionException {
        Random rand = new Random();


        int[] reservoir = initReservoir(m, dataStream);

        // 生成3个范围内随机数，将数据切成4份
        List<Integer> list = getRandInt(rand, dataStream.length);
        int s1 = list.get(0);
        int s2 = list.get(1);
        int s3 = list.get(2);
        // 每个任务处理的数据量
        double n1 = s1 - 0;
        double n2 = s2 - s1;
        double n3 = s3 - s2;
        double n4 = dataStream.length - s3;

        // 并行抽样
        Future<int[]> f1 = exec.submit(new SampleTask(m, Arrays.copyOfRange(dataStream, 0, s1)));
        Future<int[]> f2 = exec.submit(new SampleTask(m, Arrays.copyOfRange(dataStream, s1, s2)));
        Future<int[]> f3 = exec.submit(new SampleTask(m, Arrays.copyOfRange(dataStream, s2, s3)));
        Future<int[]> f4 = exec.submit(new SampleTask(m, Arrays.copyOfRange(dataStream, s3, dataStream.length)));
        List<Integer> r1 = getList(f1.get());
        List<Integer> r2 = getList(f2.get());
        List<Integer> r3 = getList(f3.get());
        List<Integer> r4 = getList(f4.get());

        // 进行m次抽样
        for (int i = 0; i < m; i++) {
            int p = rand.nextInt(dataStream.length);
            // 根据随机数落在的范围选择元素
            if (p < s1) {
                reservoir[i] = getRandEle(r1, rand.nextInt(r1.size()));
            } else if (p < s2) {
                reservoir[i] = getRandEle(r2, rand.nextInt(r2.size()));
            } else if (p < s3) {
                reservoir[i] = getRandEle(r3, rand.nextInt(r3.size()));
            } else {
                reservoir[i] = getRandEle(r4, rand.nextInt(r4.size()));
            }
        }

        return reservoir;
    }

    // 根据输入返回随机位置的元素，并且删除该元素，模拟不放回
    private static int getRandEle(List<Integer> list, int idx) {
        return list.remove(idx);
    }

    // 获取bound范围内的3个随机数，用来分割数据集
    private static List<Integer> getRandInt(Random rand, int bound) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        while (set.size() < 3) {
            set.add(rand.nextInt(bound));
        }
        for (int e : set) {
            list.add(e);
        }
        return list;
    }

    // 数据转换成List
    private static List<Integer> getList(int[] arr) {
        List<Integer> list = new LinkedList<>();
        for (int a : arr) {
            list.add(a);
        }
        return list;
    }

    // 单机版蓄水池抽样
    public static int[] sample(int m, int[] dataStream) {
        // 随机数生成器，以系统当前nano时间作为种子
        Random rand = new Random();

        int[] reservoir = initReservoir(m, dataStream);

        // init
        for (int i = 0; i < reservoir.length; i++) {
            reservoir[i] = dataStream[i];
        }

        for (int i = m; i < dataStream.length; i++) {
            // 随机获得一个[0, i]内的随机整数
            int d = rand.nextInt(i + 1);
            // 如果随机整数在[0, m-1]范围内，则替换蓄水池中的元素
            if (d < m) {
                reservoir[d] = dataStream[i];
            }
        }
        return reservoir;
    }

    private static int[] initReservoir(int m, int[] dataStream) {
        int[] reservoir;

        if (m > dataStream.length) {
            reservoir = new int[dataStream.length];
        } else {
            reservoir = new int[m];
        }
        return reservoir;
    }

    // 单机版测试
    public void test() {
        // 样本长度
        int len = 1000;
        // 蓄水池容量
        int m = 10;
        // 抽样次数，用作验证抽样的随机性
        int iterTime = 100000;
        // 每个数字被抽到的次数
        int[] freq = new int[len];
        // 样本
        int[] dataStream = new int[len];

        // init dataStream
        for (int i = 0; i < dataStream.length; i++) {
            dataStream[i] = i;
        }

        // count freq
        for (int k = 0; k < iterTime; k++) {
            // 进行抽样
            int[] reservoir = sample(m, dataStream);
            // 计算出现次数
            for (int i = 0; i < reservoir.length; i++) {
                int ele = reservoir[i];
                freq[ele] += 1;
            }
        }

        printStaticInfo(freq);
    }

    // 测试并行抽样
    public void mutiTest() throws InterruptedException, ExecutionException {
        // 样本长度
        int len = 1000;
        // 蓄水池容量
        int m = 10;
        // 抽样次数，用作验证抽样的随机性
        int iterTime = 10_0000;
        // 每个数字被抽到的次数
        int[] freq = new int[len];
        // 样本
        int[] dataStream = new int[len];

        // init dataStream
        for (int i = 0; i < dataStream.length; i++) {
            dataStream[i] = i;
        }

        // count freq
        for (int k = 0; k < iterTime; k++) {
            // 进行抽样
            int[] reservoir = mutiSample(m, dataStream);
            // 计算出现次数
            for (int i = 0; i < reservoir.length; i++) {
                int ele = reservoir[i];
                freq[ele] += 1;
            }
        }
        printStaticInfo(freq);
    }

    // 打印统计信息
    private void printStaticInfo(int[] freq) {
        // 期望、方差和标准差
        double avg = 0;
        double var = 0;
        double sigma = 0;
        // print
        for (int i = 0; i < freq.length; i++) {
            if (i % 10 == 9) System.out.println();
            System.out.print(freq[i] + ", ");
            avg += ((double) (freq[i]) / freq.length);
            var += (double) (freq[i] * freq[i]) / freq.length;
        }

        // 输出统计信息
        System.out.println("\n===============================");
        var = var - avg * avg;
        sigma = Math.sqrt(var);
        System.out.println("Average: " + avg);
        System.out.println("Variance: " + var);
        System.out.println("Standard deviation: " + sigma);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ReservoirSampling rs = new ReservoirSampling();
        rs.mutiTest();
    }
}
