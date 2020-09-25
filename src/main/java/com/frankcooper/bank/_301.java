package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _301 {

    static _301 handler = new _301();

    public static void main(String[] args) {
        String s = "()())()";
//        handler.removeInvalidParentheses(s);
        System.out.println("---------------------");
        handler.removeInvalidParentheses1st(s);
    }




    Set<String> resSet = new HashSet<>();

    public List<String> removeInvalidParentheses1st(String s) {
        int[] arr = counter(s);
        int leftRemove = arr[0], rightRemove = arr[1];
        backtracing(s, new StringBuilder(), 0, 0, 0, leftRemove, rightRemove);
        return new ArrayList<>(resSet);
    }

    /**
     * @param s           源字符
     * @param sb          当前收集到的字符
     * @param idx         当前处理到的字符串的索引
     * @param leftCount   已经收集到的左括号的数量
     * @param rightCount  已经收集到的有括号的数量
     * @param leftRemove  当前还剩余的应该要移除的左括号的数量
     * @param rightRemove 当前还剩余的应该要移除的右括号的数量
     */
    public void backtracing(String s, StringBuilder sb, int idx,
                            int leftCount, int rightCount,
                            int leftRemove, int rightRemove
    ) {
        if (idx == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                System.out.println(sb.toString());
                resSet.add(sb.toString());
            }
            return;
        }

        char currChar = s.charAt(idx);
        //当前的字符（ 或者 ） 丢掉，不考虑此字符
        if (currChar == '(' && leftRemove > 0 || currChar == ')' && rightRemove > 0) {
            backtracing(s, sb, idx + 1,
                    leftCount, rightCount,
                    currChar == '(' ? leftRemove - 1 : leftRemove,
                    currChar == ')' ? rightRemove - 1 : rightRemove
            );
//            backtracing(s, sb, idx + 1,
//                    leftCount, rightCount,
//                    currChar == '(' ? --leftRemove : leftRemove,
//                    currChar == ')' ? --rightRemove : rightRemove
//            );
        }
        //添加
        sb.append(currChar);
        System.out.printf("idx:%d--->%s\n", idx, sb.toString());
        //当前字符加入到最终的结果集
        //1.既不是左括号也不是右括号，什么都不做，跳到下一个索引
        if (currChar != '(' && currChar != ')') {
            backtracing(s, sb, idx + 1, leftCount, rightCount, leftRemove, rightRemove);
        }
        //2.左括号，leftCount+1，跳到下一个索引
        else if (currChar == '(') {
            backtracing(s, sb, idx + 1, leftCount + 1, rightCount, leftRemove, rightRemove);
        }
        //3.右括号，并且要求，左边的括号数量大于右边的括号数量，否则当前的表达式无意义，rightCount+1，跳到下一个索引
        else if (currChar == ')' && rightCount < leftCount) {
            backtracing(s, sb, idx + 1, leftCount, rightCount + 1, leftRemove, rightRemove);
        }
        //回溯
        sb.deleteCharAt(sb.length() - 1);
    }

    /**
     * 拿到当前字符中需要删除的左右括号的数量
     * @param s
     * @return
     */
    public int[] counter(String s) {
        int[] arr = new int[2];
        int leftRemove = 0, rightRemove = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') leftRemove++;
            else if (c == ')') {
                if (leftRemove == 0) rightRemove++;
                else if (leftRemove > 0) leftRemove--;
            }
        }
        arr[0] = leftRemove;
        arr[1] = rightRemove;
        return arr;
    }


}
