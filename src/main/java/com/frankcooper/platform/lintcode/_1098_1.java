package com.frankcooper.platform.lintcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/7/10
 * @Author Frank Cooper
 * @Description
 */
public class _1098_1 {

    static _1098_1 handler = new _1098_1();

    public static void main(String[] args) {
        handler.pathSumIV(new int[]{113, 215, 221});
//        handler.pathSumIV(new int[]{110, 211, 227, 318, 328, 331, 349, 431, 448, 457, 478, 489});
    }


    public int pathSumIV(int[] nums) {
        Arrays.sort(nums);
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Queue<Node> q = new LinkedList<>();
        // 第一个元素入队
        q.offer(new Node(1, getSeq(nums[0]), getVal(nums[0])));
        int i = 1, size = nums.length;
        int currLevel = 2;
        int sum = 0;
        while (i < size) {
            // 逐层遍历
            while (i < size && getLevel(nums[i]) == currLevel) {
                int num = nums[i];
                //只把上一层无孩子的点计入sum
                while (q.peek().seq != (getSeq(num) + 1) / 2) {// 举例：{110,211,227,318,328,331,349,431,448,457,478,489} 211->331
                    if (q.peek().hasChild) {
                        q.poll();
                        continue;
                    }
                    sum += q.poll().sum;
                }
                Node n = q.peek();
                q.offer(new Node(currLevel, getSeq(num), n.sum + getVal(num)));
                n.hasChild = true;
                i++;
            }
            // q中还有可能残存上一层的点，把上一层清理干净
            while (q.peek().level == currLevel - 1) {
                if (q.peek().hasChild) {
                    q.poll();
                    continue;
                }
                sum += q.poll().sum;
            }
            currLevel++;
        }
        // 坑：最后q中还会残存最后一层的点，全部计入sum
        while (!q.isEmpty()) {
            sum += q.poll().sum;
        }
        return sum;
    }

    // 取左边第1位
    private int getLevel(int num) {
        return num / 100;
    }

    // 取左边第2位
    private int getSeq(int num) {
        return (num % 100) / 10;
    }

    // 取左边第3位
    private int getVal(int num) {
        return num % 10;
    }

    static class Node {
        int level;
        int sum;
        int seq;
        boolean hasChild;

        public Node(int level, int seq, int sum) {
            this.level = level;
            this.seq = seq;
            this.sum = sum;
        }
    }
}
