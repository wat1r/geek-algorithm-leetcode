package com.frankcooper.platform.leetcode.bank._401_500;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.*;

/**
 * @Date 2020/9/9
 * @Author Frank Cooper
 * @Description
 */
public class _402 {

    static _402 handler = new _402();

    public static void main(String[] args) {
        String num = "100204";
        int k = 1;
        num = "1234567890";
        k = 9;
        handler.removeKdigits(num, k);

    }


    public String removeKdigits(String num, int k) {
        String res = "";
        for (char c : num.toCharArray()) {
            while (!res.equals("") && res.charAt(res.length() - 1) > c && k > 0) {
                res = res.substring(0, res.length() - 1);
                k--;
            }
            res += c;
        }
        while (k > 0) {
            res = res.substring(0, res.length() - 1);
            k--;
        }
        int i = 0;
        while (i < res.length() && res.charAt(i) == '0') i++;
        if (i == res.length()) return "0";
        return res.substring(i);
    }


    public String removeKdigits2nd(String num, int k) {
        LinkedList<Character> list = new LinkedList<>();
        for (char c : num.toCharArray()) {
            while (!list.isEmpty() && list.peekLast() > c && k > 0) {
                list.removeLast();
                k--;
            }
            list.addLast(c);
        }
        while (k > 0) {
            list.removeLast();
            k--;
        }
        StringBuilder res = new StringBuilder();
        boolean headZero = true;
        for (char c : list) {
            if (c == '0' && headZero) continue;
            res.append(c);
            headZero = false;
        }
        return res.toString().equals("") ? "0" : res.toString();
    }

    //    基于上述分析，我们可以得出「删除一个数字」的贪心策略：
//
//给定一个长度为 n 的数字序列 [D0,D1D2D3…Dn-1]，从左往右找到第一个位置 i（i>0）使得 Di<Di-1，并删去 Di-1；如果不存在，说明整个数字序列单调不降，删去最后一个数字即可。
//
//基于此，我们可以每次对整个数字序列执行一次这个策略；删去一个字符后，剩下的 n-1 长度的数字序列就形成了新的子问题，可以继续使用同样的策略，直至删除 k 次。
//
//然而暴力的实现复杂度最差会达到 O(nk)（考虑整个数字序列是单调不降的），因此我们需要加速这个过程。
//
//考虑从左往右增量的构造最后的答案。我们可以用一个栈维护当前的答案序列，栈中的元素代表截止到当前位置，删除不超过 k 次个数字后，所能得到的最小整数。根据之前的讨论：在使用 k 个删除次数之前，栈中的序列从栈底到栈顶单调不降。
//
//因此，对于每个数字，如果该数字小于栈顶元素，我们就不断地弹出栈顶元素，直到
//
//栈为空
//或者新的栈顶元素不大于当前数字
//或者我们已经删除了 k 位数字
    public String removeKdigits1st(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        StringBuilder res = new StringBuilder();
        boolean headZero = true;
        for (char c : sb.reverse().toString().toCharArray()) {
            if (c == '0' && headZero) {
                continue;
            }
            res.append(c);
            headZero = false;
        }
        return res.toString().equals("") ? "0" : res.toString();
    }


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String num = "1432219";
            int k = 3;
//            handler.removeKdigits(num, k);
            num = "9";
            k = 1;
            Assert.assertEquals("0", handler.removeKdigits(num, k));
        }

        public String removeKdigits(String num, int k) {
            //维护一个单调递增的单调栈，该栈的大小是len(num)-k的长度
            Deque<Character> stk = new ArrayDeque<>();
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                while (!stk.isEmpty() && stk.peek() > c && k > 0) {
                    stk.pop();
                    k--;
                }
                stk.push(c);
            }
            //防止k没有完全消耗完
            /*num="9"
              k=1
              exprected:"0"
             */
            while (k-- > 0) stk.pop();
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) sb.append(stk.pop());
            boolean headZero = true;//前导零
            StringBuilder res = new StringBuilder();
            for (char c : sb.reverse().toString().toCharArray()) {
                if (c == '0' && headZero) continue;
                res.append(c);
                headZero = false;
            }
            return res.toString().equals("") ? "0" : res.toString();
        }
    }


    static class _2nd_1 {
        public String removeKdigits(String num, int k) {
            Deque<Character> stk = new ArrayDeque<>();
            char[] ch = num.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                while (!stk.isEmpty() && stk.peek() > ch[i] && k > 0) {
                    stk.pop();
                    k--;
                }
                stk.push(ch[i]);
            }
            while (k-- > 0) stk.pop();
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) sb.append(stk.pop());
            StringBuilder res = new StringBuilder();
            boolean headZero = true;
            for (char c : sb.reverse().toString().toCharArray()) {
                if (c == '0' && headZero) {
                    continue;
                }
                res.append(c);
                headZero = false;
            }
            return res.toString().equals("") ? "0" : res.toString();
        }
    }

    //https://leetcode.cn/problems/remove-k-digits/solutions/484940/yi-diao-kwei-shu-zi-by-leetcode-solution/?envType=study-plan-v2&envId=2024-spring-sprint-100
    static class _3rd_1 {
        public String removeKdigits(String num, int k) {
            Stack<Character> stk = new Stack<>();
            for (char c : num.toCharArray()) {
                while (!stk.isEmpty() && c < stk.peek() && k > 0) {
                    stk.pop();
                    k--;
                }
                stk.push(c);
            }
            while (k > 0) {
                stk.pop();
                k--;
            }
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty()) {
                sb.append(stk.pop());
            }
            boolean headZero = true;
            StringBuilder res = new StringBuilder();
            for (char c : sb.reverse().toString().toCharArray()) {
                if (c == '0' && headZero) {
                    continue;
                }
                res.append(c);
                headZero = false;
            }
            return res.toString().equals("") ? "0" : res.toString();
        }
    }

    static class _4th_1 {
        public String removeKdigits(String num, int k) {
            Deque<Character> deque = new LinkedList<Character>();
            int length = num.length();
            for (int i = 0; i < length; ++i) {
                char digit = num.charAt(i);
                while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                    deque.pollLast();
                    k--;
                }
                deque.offerLast(digit);
            }

            for (int i = 0; i < k; ++i) {
                deque.pollLast();
            }

            StringBuilder ret = new StringBuilder();
            boolean leadingZero = true;
            while (!deque.isEmpty()) {
                char digit = deque.pollFirst();
                if (leadingZero && digit == '0') {
                    continue;
                }
                leadingZero = false;
                ret.append(digit);
            }
            return ret.length() == 0 ? "0" : ret.toString();
        }

    }

}
