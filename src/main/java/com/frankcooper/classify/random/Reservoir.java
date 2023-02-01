package com.frankcooper.classify.random;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

public class Reservoir {


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


    // 单机版蓄水池抽样
    public static int[] sample(int m, int[] dataStream) {
        // 随机数生成器，以系统当前nano时间作为种子
        Random random = new Random();
        int[] reservoir = initReservoir(m, dataStream);
        // init
        for (int i = 0; i < reservoir.length; i++) {
            reservoir[i] = dataStream[i];
        }

        for (int i = m; i < dataStream.length; i++) {
            // 随机获得一个[0, i]内的随机整数
            int d = random.nextInt(i + 1);
            // 如果随机整数在[0, m-1]范围内，则替换蓄水池中的元素
            if (d < m) {
                reservoir[d] = dataStream[i];
            }
        }
        return reservoir;
    }

    //初始化
    private static int[] initReservoir(int capacity, int[] dataStream) {
        int[] reservoir;
        if (capacity > dataStream.length) {
            reservoir = new int[dataStream.length];
        } else {
            reservoir = new int[capacity];
        }
        return reservoir;
    }


    // 单机版测试
    public int getId() {
        // 样本长度
        int len = 1024;
        // 蓄水池容量
        int capacity = 1024;
        // 抽样次数，用作验证抽样的随机性
        int[] dataStream = new int[len];

        // init dataStream
        for (int i = 0; i < dataStream.length; i++) {
            dataStream[i] = i;
        }
        int iterTime = 1000;
        // count freq
        for (int k = 0; k < iterTime; k++) {
            // 进行抽样
            int[] reservoir = sample(capacity, dataStream);
            // 计算出现次数
            int index = new Random().nextInt(capacity);
            System.out.printf("%d->%d\n", index, reservoir[index]);
//            index = 200;
//            System.out.printf("%d->%d\n", index, reservoir[index]);
//            System.out.println("================");
        }


//        printStaticInfo(freq);
        return 0;
    }


    public void testLock() {


//        int lockId = obj.hashCode();
//        int index = lockId & mask;
//        Lock lock = locks[index];
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
        Reservoir rs = new Reservoir();
        rs.getId();
    }
}
