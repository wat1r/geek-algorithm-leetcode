package com.frankcooper.platform.leetcode.bank._301_400;


import com.frankcooper.struct.pri.NestedInteger;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.*;

public class _341 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public class NestedIterator implements Iterator<Integer> {
            List<Integer> list;
            int size;


            public NestedIterator(List<NestedInteger> nestedList) {
                size = 0;
                list = new ArrayList<>();
                for (NestedInteger ni : nestedList) dfs(ni);
            }

            private void dfs(NestedInteger ni) {
                if (ni.isInteger()) list.add(ni.getInteger());
                else {
                    for (NestedInteger i : ni.getList()) dfs(i);
                }
            }


            @Override
            public Integer next() {
                return list.get(size++);
            }

            @Override
            public boolean hasNext() {
                return size < list.size();
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public class NestedIterator implements Iterator<Integer> {
            // 存储列表的当前遍历位置
            Deque<Iterator<NestedInteger>> stack;


            public NestedIterator(List<NestedInteger> nestedList) {
                stack = new LinkedList<>();
                stack.push(nestedList.iterator());
            }

            @Override
            public Integer next() {
                return stack.peek().next().getInteger();
            }

            @Override
            public boolean hasNext() {
                while (!stack.isEmpty()) {
                    Iterator<NestedInteger> it = stack.peek();
                    if (!it.hasNext()) {
                        stack.pop();// 遍历到当前列表末尾，出栈
                        continue;
                    }
                    // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
                    NestedInteger ni = it.next();
                    if (ni.isInteger()) {
                        List<NestedInteger> list = new ArrayList<>();
                        list.add(ni);
                        stack.push(list.iterator());
                        return true;
                    }
                    stack.push(ni.getList().iterator());
                }
                return false;
            }
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
