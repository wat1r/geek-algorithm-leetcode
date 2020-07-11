package com.frankcooper.lintcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FrankCooper
 * Date 2020/7/11 21:41
 * Description
 */
public class _1098 {

    static _1098 handler = new _1098();

    public static void main(String[] args) {
        handler.pathSumIV(new int[]{113, 215, 221});
    }


    public int pathSumIV(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);//sort,set the start number and got it
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(getLevel(nums[0]), getSeq(nums[0]), getValue(nums[0])));//add the root node
        //i: 0...size-1
        int i = 1, size = nums.length;
        int curLevel = 2; //the first level begin with 1 ,then start with curLevel
        int res = 0;
        while (i < size) {
            while (i < size && getLevel(nums[i]) == curLevel) {
                int curNum = nums[i];
                while (queue.peek().seq != (getSeq(curNum) + 1) / 2) {
                    if (queue.peek().hasChild) {
                        queue.poll();
                        continue;
                    }
                    res += queue.poll().value;
                }
                Node tmp = queue.peek();
                queue.offer(new Node(curLevel, getSeq(curNum), tmp.value + getValue(curNum)));
                tmp.hasChild = true;
                i++;
            }
            while (queue.peek().level == (curLevel - 1)) {
                if (queue.peek().hasChild) {
                    queue.poll();
                    continue;
                }
                res += queue.poll().value;
            }
            curLevel++;
        }
        while (!queue.isEmpty()) {
            res += queue.poll().value;
        }
        return res;
    }


    //get level : 1st number
    private int getLevel(int num) {
        return num / 100;
    }

    //get seq : 2nd number
    private int getSeq(int num) {
        return (num % 100) / 10;
    }

    //get value :3rd number
    private int getValue(int num) {
        return num % 10;
    }


    static class Node {

        int level;
        int seq;
        int value;
        boolean hasChild;

        public Node(int level, int seq, int value) {
            this.level = level;
            this.seq = seq;
            this.value = value;
        }
    }


}
