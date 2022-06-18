package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _691 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] stickers = {"with", "example", "science"};
            String target = "thehat";
            handler.minStickers(stickers, target);
        }

        public int minStickers(String[] stickers, String target) {
            int n = target.length();
            //dp[state]表示二进制状态为state时的最小贴纸数量，当每一位都为1的时候，为最终要返回的结果
            //返回dp[(1<<n)-1]
            int[] dp = new int[1 << n];
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int k = 0; k < size; k++) {
                    int base = q.poll();
                    //每一轮从stickers中摘取字符
                    for (String s : stickers) {
                        int state = base;//当前层是以base这一层作为基准开始扩散
//                        System.out.printf("level:%d ", level);
//                        PrintUtils.toBinaryString(state, 6);
                        int[] cnt = new int[26];
                        //统计s字符串中字母出现的次数
                        for (char c : s.toCharArray()) cnt[c - 'a']++;
                        //遍历target字符串
                        for (int i = 0; i < n; i++) {
                            char c = target.charAt(i);
                            //当前字符在s中出现过且target所在的位没有被填充
                            if (cnt[c - 'a'] != 0 && (state & 1 << i) == 0) {
                                cnt[c - 'a']--;
                                state |= 1 << i;//填充
                            }
                        }
                        //dp[state]不为0说明这个state之前已经被访问过
                        if (dp[state] != 0 || state == 0) continue;
                        //当前的state推入queue中保持等待下一层弹出
                        q.offer(state);
                        //该贴纸可以被选用，即用了该贴纸一次
                        dp[state] = dp[base] + 1;
                        //终止条件判断
                        if (state == (1 << n) - 1) return dp[state];
                    }
                }
                level++;
            }
            return -1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String[] stickers = {"with", "example", "science"};
            String target = "thehat";
            handler.minStickers(stickers, target);
        }


        //            String[] stickers = {"with", "example", "science"};
        //            String target = "thehat";


        Map<String, Integer> memo = new HashMap<>();
        int[][] dict;
        int n;


        public int minStickers(String[] stickers, String target) {
            n = stickers.length;
            dict = new int[n][26];
            for (int i = 0; i < n; i++) {
                for (char c : stickers[i].toCharArray()) dict[i][c - 'a']++;
            }
            memo.put("", 0);
            return dfs(target);
        }

        private int dfs(String target) {
            if (memo.containsKey(target)) return memo.get(target);
            int ans = Integer.MAX_VALUE;
            int[] cnt = new int[26];
            //统计当前target中字符的出现次数
            for (char c : target.toCharArray()) cnt[c - 'a']++;
            for (int i = 0; i < n; i++) {
                if (dict[i][target.charAt(0) - 'a'] == 0) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (cnt[j] > 0) {
                        //dict[i]中有哪些字符可以对当前的target字符串有贡献，移除掉这部分贡献，拼接剩下的字符
                        //举例 thahat->aeht->th->""
                        for (int k = 0; k < Math.max(0, cnt[j] - dict[i][j]); k++) {
                            sb.append((char) ('a' + j));
                        }
                    }
                }
                int t = dfs(sb.toString());
                if (t != -1) ans = Math.min(ans, t + 1);
            }
            memo.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
            return memo.get(target);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String[] stickers = {"with", "example", "science"};
            String target = "thehat";
            handler.minStickers(stickers, target);
        }


        public int minStickers(String[] stickers, String target) {
            int n = target.length();
            //dp[state]表示二进制表示为state时的最小贴纸数量
            int[] dp = new int[1 << n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;//当空的字符，需要的贴纸数量为0
            //遍历每一位获取每一个子集
            for (int bit = 0; bit < (1 << n); bit++) {
                if (dp[bit] == Integer.MAX_VALUE) {
                    continue;
                }
                for (String s : stickers) {
                    int state = bit;//当前位从bit开始出发
                    for (char c : s.toCharArray()) {
                        for (int i = 0; i < n; i++) {
                            //target中有该字符且该字符没有被处理到
                            if (target.charAt(i) == c && ((state >> i) & 1) == 0) {
                                state |= 1 << i;
                                break;
                            }
                        }
                    }
                    //从bit转移到state 只需要一步也就是一次贴纸
                    dp[state] = Math.min(dp[state], dp[bit] + 1);
                }
            }
            return dp[(1 << n) - 1] == Integer.MAX_VALUE ? -1 : dp[(1 << n) - 1];
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
