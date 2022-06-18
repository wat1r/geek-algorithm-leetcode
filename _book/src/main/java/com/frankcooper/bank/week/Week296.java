package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week296 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 3, 5, 2, 4, 8, 2, 2};
            handler.minMaxGame(nums);

        }

        public int minMaxGame(int[] nums) {
            return helper(nums);
        }

        private int helper(int[] arr) {
            if (arr.length == 1) return arr[0];
            int n = arr.length;
            int[] next = new int[n >> 1];
            for (int i = 0; i <= (n >> 1) - 1; i++) {
                if (i % 2 == 0) next[i] = Math.min(arr[2 * i], arr[2 * i + 1]);
                else next[i] = Math.max(arr[2 * i], arr[2 * i + 1]);
            }
            return helper(next);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int partitionArray(int[] nums, int k) {
            Arrays.sort(nums);
            int res = 0;
            int n = nums.length, i = 0, j = 0;
            while (j < n) {
                while (j < n && nums[j] - nums[i] <= k) {
                    j++;
                }
                i = j;
                res++;
            }
            return res;
        }
    }


    //WA
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {1, 2, 4, 6};
            int[][] operations = {{1, 3}, {4, 7}, {6, 1}};
            handler.arrayChange(nums, operations);
        }


        int N = 10010;
        int idx = 0;
        int[] head = new int[N << 1];
        Edge[] edge = new Edge[N << 1];

        class Edge {
            int to;
            int next;
        }

        void add(int u, int v) {
            edge[idx].to = v;
            edge[idx].next = head[u];
            head[u] = idx++;
        }


        public int[] arrayChange(int[] nums, int[][] ops) {
            Arrays.fill(edge, new Edge());
            for (int[] op : ops) {
                int u = op[0], v = op[1];
                add(u, v);
            }
            for (int i = 0; i < nums.length; i++) {
                int j = 0;
                for (j = head[nums[i]]; j != 0; j = edge[nums[i]].next) {
                    System.out.printf("%d ", edge[j].to);
                }
                nums[i] = edge[j].to;
            }
            return nums;
        }
    }
    public int[] arrayChange(int[] nums, int[][] ops) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int[] op : ops) {
            int i = map.get(op[0]);
            nums[i] = op[1];
            map.put(op[1], i);
            map.remove(op[0]);
        }
        return nums;
    }
    static class _3rd_1 {
        public static void main(String[] args) {

        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String[] ops = {"TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"};
            Object[][] obj = {{}, {"leetcode"}, {4}, {"practice"}, {3}, {8}, {10}, {2}, {6}};
            TextEditor textEditor = new TextEditor();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("addText")) {
                    textEditor.addText((String) obj[i][0]);
                } else if (op.equals("deleteText")) {
                    System.out.println(textEditor.deleteText((int) obj[i][0]));
                } else if (op.equals("cursorRight")) {
                    System.out.println(textEditor.cursorRight((int) obj[i][0]));
                } else if (op.equals("cursorLeft")) {
                    System.out.println(textEditor.cursorLeft((int) obj[i][0]));
                }
            }
        }

        //AC
        static class TextEditor {


            int cursor = 0;
            StringBuilder sb = new StringBuilder();

            public TextEditor() {

            }

            public void addText(String text) {
                sb.insert(cursor, text);
                cursor += text.length();
            }

            public int deleteText(int k) {
                int start = Math.max(cursor - k, 0);
                int end = cursor;
                sb.delete(start, end);
                cursor = Math.max(0, cursor - k);
                return Math.max(end - start, 0);
            }

            public String cursorLeft(int k) {
                int n = sb.length();
                cursor = Math.max(cursor - k, 0);
                if (cursor == 0) return "";
                return sb.substring(Math.max(cursor - 10, 0), cursor);
            }

            public String cursorRight(int k) {
                int n = sb.length();
                cursor = Math.min(cursor + k, n);
                return sb.substring(Math.max(cursor - 10, 0), cursor);
            }
        }
    }
}
