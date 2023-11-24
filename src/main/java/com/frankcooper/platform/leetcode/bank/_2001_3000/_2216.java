package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2216 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

//            int[] nums = {1, 1, 2, 2, 3, 3};
            int[] nums = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
            handler.minDeletion(nums);

        }

        //遍历数组，用栈来模拟这个过程（实际不需要栈，后面会说明）：
        //
        //如果栈大小为偶数，可以随意加入元素；
        //如果栈大小为奇数，那么加入的元素不能和栈顶相同。
        //遍历结束后，若栈大小为奇数，则移除栈顶。
        //
        //最后栈大小就是保留的元素，用数组大小减去栈大小就是删除的元素个数。
        //
        //作者：灵茶山艾府
        //链接：https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int minDeletion(int[] nums) {
            Stack<Integer> st = new Stack<>();
            for (int x : nums) {
                if (st.size() % 2 == 0) {
                    st.push(x);
                } else {
                    if (x != st.peek()) {
                        st.push(x);
                    }
                }
            }
            if (st.size() % 2 == 1) {
                st.pop();
            }
            return nums.length - st.size();
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
