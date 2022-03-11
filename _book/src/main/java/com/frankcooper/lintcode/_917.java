package com.frankcooper.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2020/6/16
 * @Author Frank Cooper
 * @Description
 */
public class _917 {
    static _917 handler = new _917();

    public static void main(String[] args) {
//        handler.generatePalindromes("aabb");
//        handler.generatePalindromes("aaaa");
        handler.generatePalindromes("aabb");
    }


    public List<String> generatePalindromes(String s) {
        List<String> resList = new ArrayList<>();
        if (s == null || s.length() == 0) return resList;
        Map<Character, Integer> counterMap = new HashMap<>();
        char[] chas = s.toCharArray();
        for (char c : chas) counterMap.put(c, counterMap.getOrDefault(c, 0) + 1);
        int odd = 0;
        String mid = "";
        for (char c : counterMap.keySet()) {
            if ((counterMap.get(c) & 1) == 1) {//奇数时
                if (++odd > 1) return resList;
                mid = String.valueOf(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : counterMap.keySet()) {
            int num = counterMap.get(c);
            for (int i = 0; i < (num / 2); i++) sb.append(c);
        }
        dfs(sb, 0, resList, mid);
        return resList;
    }

    /**
     *
     * @param sb 种子字符
     * @param index 当前的索引
     * @param resList 结果集
     * @param mid 奇回文中间的字符，偶回文为空字符
     */
    private void dfs(StringBuilder sb, int index, List<String> resList, String mid) {
        if (index + 1 == sb.length()) {
            String res = sb + mid + sb.reverse();
            resList.add(res);
            sb.reverse();
            System.out.println(res);
            return;
        }
        for (int i = index; i < sb.length(); i++) {
            if (i != index && sb.charAt(i) == sb.charAt(index)) continue;
            swap(sb, index, i);
            dfs(sb, index + 1, resList, mid);
            swap(sb, index, i);

        }

    }

    //交换sb的i与j位置的字符
    private void swap(StringBuilder sb, int i, int j) {
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);
    }
}
