package com.frankcooper.bank._1_100;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/8/10
 * @Author Frank Cooper
 * @Description
 */
public class _93 {
    static _93 handler = new _93();

    public static void main(String[] args) {
        String s = "25525511135";
//        handler.restoreIpAddresses1st(s);
        handler.restoreIpAddresses(s);

    }


    List<String> results = new ArrayList<>();

    public List<String> restoreIpAddresses1st(String s) {
        backtrack1st(s, new ArrayList<>(), 0);
        return results;
    }

    private void backtrack1st(String s, ArrayList<String> segment, int index) {
        if (segment.size() == 4 && index == s.length()) {
            results.add(String.join(".", segment));
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length() || segment.size() > 4) break;
            String curr = s.substring(index, index + i);
            if ((i == 3 && Integer.parseInt(curr) > 255) || (curr.startsWith("0") && curr.length() > 1)) continue;
            segment.add(curr);
            backtrack1st(s, segment, index + i);
            segment.remove(segment.size() - 1);
        }
    }


    List<String> results1 = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
//        backtrack(s, 0, "", 0);
        backtrack(s, 0, new StringBuilder(), 0);
        System.out.println(JSON.toJSONString(results1));
        return results1;
    }

    private void backtrack(String s, int count, StringBuilder path, int index) {
        System.out.println(path.toString());
        if (count > 4) {
            path =new StringBuilder();
            return;
        }
        if (count == 4 && index == s.length()) {
            results1.add(path.toString());
        }
        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length()) break;
            String curr = s.substring(index, index + i);
            if ((Integer.parseInt(curr) > 255) || (curr.startsWith("0") && curr.length() > 1)) break;
//            System.out.println(path.toString());
//            backtrack(s, count + 1, path + curr + (count == 3 ? "" : "."), index + i);
            backtrack(s, count + 1, path.append(curr).append(count == 3 ? "" : "."), index + i);
        }

    }


}
