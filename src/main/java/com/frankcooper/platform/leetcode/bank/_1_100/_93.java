package com.frankcooper.platform.leetcode.bank._1_100;

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
            path = new StringBuilder();
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


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "25525511135";
            handler.restoreIpAddresses(s);
        }

        List<String> results = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            backtrack(s, new ArrayList<>(), 0);
            return results;
        }

        private void backtrack(String s, ArrayList<String> segment, int index) {
            if (segment.size() == 4 && index == s.length()) {
                results.add(String.join(".", segment));
                return;
            }
            for (int i = 1; i <= 3; i++) {
                if (index + i > s.length() || segment.size() > 4) break;
                String curr = s.substring(index, index + i);
                if ((i == 3 && Integer.parseInt(curr) > 255) || (curr.startsWith("0") && curr.length() > 1)) continue;
                segment.add(curr);
                backtrack(s, segment, index + i);
                segment.remove(segment.size() - 1);
            }
        }
    }


    static class _2nd {
        List<String> results = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            backtracing(s, "", 0);
            return results;
        }

        private void backtracing(String s, String path, int segs) {
            if (s.isEmpty() || segs == 4) {
                if (s.isEmpty() && segs == 4) results.add(path.substring(1));
                return;
            }
            int n = s.charAt(0) == '0' ? 1 : 3;
            for (int i = 1; i <= n && i <= s.length(); i++) {
                String sub = s.substring(0, i);
                if (Integer.valueOf(sub) <= 255) {
                    backtracing(s.substring(i), path + "." + sub, segs + 1);
                }
            }

        }
    }

    static class _3rd {
        public List<String> restoreIpAddresses(String s) {
            List<String> solutions = new ArrayList<String>();
            restoreIp(s, solutions, 0, "", 0);
            return solutions;
        }

        private void restoreIp(String ip, List<String> solutions, int idx, String restored, int count) {
            if (count > 4) return;
            if (count == 4 && idx == ip.length()) solutions.add(restored);

            for (int i = 1; i < 4; i++) {
                if (idx + i > ip.length()) break;
                String s = ip.substring(idx, idx + i);
                if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) >= 256)) continue;
                restoreIp(ip, solutions, idx + i, restored + s + (count == 3 ? "" : "."), count + 1);
            }
        }
    }


    static class _4th {
        public List<String> restoreIpAddresses(String s) {
            List<String> list = new ArrayList();
            if (s.length() > 12) return list;

            helper(s, list, 0, "", 0);
            return list;
        }

        void helper(String s, List<String> list, int pos, String res, int sec) {
            if (sec == 4 && pos == s.length()) {
                list.add(res);
                return;
            }

            for (int i = 1; i <= 3; i++) {
                if (pos + i > s.length()) break;
                String section = s.substring(pos, pos + i);
                if (section.length() > 1 && section.startsWith("0") || Integer.parseInt(section) >= 256) break;
                helper(s, list, pos + i, sec == 0 ? section : res + "." + section, sec + 1);
            }
        }
    }


}
