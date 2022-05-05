package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _388 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            System.out.println("dir/subdir1/file1.ext".length());
        }


        public int lengthLongestPath(String input) {
            List<String> res = new ArrayList<>();
            dfs(input, 0, res);
            return 0;
        }

        private void dfs(String input, int index, List<String> res) {

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//            handler.lengthLongestPath(input);//20

            input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
            Assert.assertEquals(32, handler.lengthLongestPath(input));

        }

        public int lengthLongestPath(String input) {
            int n = input.length();
            int[] sum = new int[n];//
            int res = 0;
            for (String s : input.split("\n")) {
                int level = s.lastIndexOf('\t') + 2;
                int len = s.length() - (level - 1);
                if (s.contains(".")) {//文件
                    res = Math.max(res, sum[level - 1] + len);
                } else {
                    //后面的 +1 是给文件分隔符 / 占用的
                    sum[level] = sum[level - 1] + len + 1;
                }
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//            handler.lengthLongestPath(input);//20
            input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
            Assert.assertEquals(32, handler.lengthLongestPath(input));
        }

        public int lengthLongestPath(String input) {
            Deque<Integer> stk = new ArrayDeque<>();
            stk.push(0);
            int res = 0;
            for (String s : input.split("\n")) {
                //当前有多少个\t 表示属于哪一层，第一次根目录没有 \t lastIndexOf返回-1 表示第0层
                int level = s.lastIndexOf("\t") + 1;
                //当前的level 与stk 的大小需要同步， 栈的深度和 level是同步的
                while (level + 1 < stk.size()) {
                    stk.pop();
                }
                //len是加了 / 分隔符的长度
                int len = stk.peek() + s.length() - (level - 1);
                System.out.printf("%d->%s\n", len, s);
                stk.push(len);
                if (s.contains(".")) {
                    //文件的末尾多了一个/分隔符，len-1
                    res = Math.max(res, len - 1);
                }
            }
            return res;
        }
    }

    //DFS  don't analysis deeply
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        private int maxPath;
        private int index;

        public int lengthLongestPath(String input) {
            // This will be a DFS like traversal
            // for every encountered file we should keep our current path
            // when we backtrack, we should remove one element from the path
            // the way the format looks like is - we will exhaust a path before we continue to paths
            // with the same prefix

            maxPath = 0;
            List<String> currentPath = new ArrayList<>();
            this.index = 0;
            dfs(input, currentPath);
            return maxPath;
        }


        private int getNumOfTabs(String input) {
            if (index >= input.length()) {
                return -1;
            }

            int result = 0;
            while (input.charAt(index) == '\t') {
                result++;
                index++;
            }
            return result;
        }


        private void dfs(String input, List<String> currentPath) {
            while (true) {
                int tempIndex = index;
                int numOfTabs = getNumOfTabs(input);
                if (numOfTabs < currentPath.size()) {
                    index = tempIndex; //back tracking
                    return;
                }

                int endOfNodeIndex = input.indexOf('\n', index);
                if (endOfNodeIndex == -1) {
                    endOfNodeIndex = input.length();
                }
                int dotIndex = input.indexOf(".", index);
                String node = input.substring(index, endOfNodeIndex);
                index = endOfNodeIndex + 1;

                if (dotIndex > 0 && (dotIndex < endOfNodeIndex)) {
                    handleFile(currentPath, node);
                } else {
                    currentPath.add(node);
                    dfs(input, currentPath);
                    currentPath.remove(currentPath.size() - 1); //back tracking
                }
            }
        }

        private void handleFile(List<String> currentPath, String file) {
            int pathLen = currentPath.stream().mapToInt(String::length).sum();
            int numOfSlashes = currentPath.size();
            //path len holds the path characters, we need also to add "/"
            maxPath = Math.max(maxPath, numOfSlashes + pathLen + file.length());
        }
    }
}
