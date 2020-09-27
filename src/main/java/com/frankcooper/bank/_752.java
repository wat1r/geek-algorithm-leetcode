package com.frankcooper.bank;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @Date 2020/9/1
 * @Author Frank Cooper
 * @Description
 */
public class _752 {

    static _752 handler = new _752();


    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
//        handler.openLock1ST(deadends, target);

        handler.openLock(deadends, target);
    }


    public int openLock(String[] deadends, String target) {
        //当前处理的转盘字符
        Queue<String> queue = new LinkedList<>();
        //死亡转盘字符
        Set<String> deads = new HashSet<>();
        //字符被访问过的列表
        Set<String> vis = new HashSet<>();
        for (String d : deadends) deads.add(d);
        //单个源点触发
        queue.offer("0000");
        vis.add("0000");
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                //跳过死亡转盘字符
                if (deads.contains(curr)) continue;
                //找到了
                if (target.equals(curr)) {
                    return dist;
                }
                //四个数每个位置两种选择up down 4*2 =8 种
                for (int j = 0; j < 4; j++) {
                    String up = getUp(curr, j);
                    //要没被访问过的
                    if (!vis.contains(up)) {
                        queue.offer(up);
                        vis.add(up);
                    }
                    String down = getDown(curr, j);
                    if (!vis.contains(down)) {
                        queue.offer(down);
                        vis.add(down);
                    }
                }
            }
            //层数+1，当前层结束
            dist++;
        }
        return -1;
    }

    /**
     * 生成当前字符往上递增的字符 如 9000-->1000  2000->3000
     * @param base
     * @param idx
     * @return
     */
    private String getUp(String base, int idx) {
        char[] chas = base.toCharArray();
        if (chas[idx] == '9') chas[idx] = '0';
        else chas[idx]++;
        return String.valueOf(chas);
    }

    /**
     * 生成当前字符往下递增的字符 如 9000-->8000  1000->9000
     * @param base
     * @param idx
     * @return
     */
    private String getDown(String base, int idx) {
        char[] chas = base.toCharArray();
        if (chas[idx] == '0') chas[idx] = '9';
        else chas[idx]--;
        return String.valueOf(chas);
    }


    public int openLock1ST(String[] deadends, String target) {
        Set<String> dead = new HashSet();
        for (String d : deadends) dead.add(d);

        Queue<String> queue = new LinkedList();
        queue.offer("0000");
        queue.offer(null);

        Set<String> seen = new HashSet();
        seen.add("0000");

        int depth = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node == null) {
                depth++;
                if (queue.peek() != null)
                    queue.offer(null);
            } else if (node.equals(target)) {
                return depth;
            } else if (!dead.contains(node)) {
                for (int i = 0; i < 4; ++i) {
                    for (int d = -1; d <= 1; d += 2) {
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0, i) + ("" + y) + node.substring(i + 1);
                        System.out.println(String.format("Node:%s,y:%d,nei:%s", node, y, nei));
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
                System.out.println("------------------");
            }
        }
        return -1;
    }


}
