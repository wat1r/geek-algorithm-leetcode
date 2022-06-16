package com.frankcooper.repeate;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.ListNode;
import com.frankcooper.struct.TreeNode;
import com.frankcooper.utils.ListNodeUtils;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/6/13 21:47
 * Description
 */
public class RepeateOne {

    static RepeateOne handler = new RepeateOne();

    public static void main(String[] args) {


        RepeateOne handler = new RepeateOne();

    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        queue.offer(beginWord);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                List<String> candidates = transform(curWord, words);
                for (String candidate : candidates) {
                    if (candidate.equals(endWord)) {
                        return res;
                    }
                    queue.offer(candidate);
                }
            }

        }
        return 0;
    }


    public List<String> transform(String word, Set<String> words) {
        List<String> candidates = new ArrayList<>();
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < sb.length(); i++) {
            char tmp = sb.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (tmp == c) continue;
                sb.setCharAt(i, c);
                String potential = new String(sb);
                if (words.remove(potential)) {
                    candidates.add(potential);
                }
            }
            sb.setCharAt(i, tmp);
        }
        return candidates;
    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.mySqrt(9);
            handler.mySqrt(2147395599);
//            handler.mySqrt1(2);
        }

        public int mySqrt(int x) {
            long l = 0, r = x;
            while (l < r) {
                long m = l + (r - l) / 2;
                long t = m * m;
                if (t == x) return (int) m;
                else if (t > x) r = m;
                else if (t < x) l = m + 1;
            }
            return (int) (l * l == x ? l : l - 1);
        }

        public int mySqrt1(int x) {
            long l = 0, r = x;
            while (l < r) {
                long m = l + (r - l) / 2;
                if ((int) m * m >= x) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            return (int) (l * l == x ? l : l - 1);
        }
    }

    //887
    static class _2nd {
        int INF = Integer.MAX_VALUE >> 1;

        public int superEggDrop(int K, int N) {
            //f[i][j]：i层楼，j个鸡蛋的时候，找到临界楼层的操作次数
            int[][] f = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    f[i][j] = INF;
                }
            }
            for (int i = 1; i <= N; i++) f[i][1] = i;
            for (int j = 1; j <= K; j++) f[1][j] = 1;
            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    int l = 1, r = i;
                    while (l < r) {
                        int mid = l + (r - l + 1) / 2;
                        int t1 = f[mid - 1][j - 1];
                        int t2 = f[i - mid][j];
                        if (t1 > t2) {
                            r = mid - 1;
                        } else {
                            l = mid;
                        }
                    }
                    f[i][j] = Math.min(f[i][j], Math.max(f[l - 1][j - 1], f[i - l][j]) + 1);
                }
            }

            return f[N][K];
        }
    }

    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "A man, a plan, a canal: Panama";
            s = "OP";
            s = "0P";
            handler.isPalindrome(s);
        }

        public boolean isPalindrome(String s) {
            char[] ch = s.toCharArray();
            int l = 0, r = ch.length - 1;
            while (l < r) {
                while (l < r && !check(ch[l])) l++;
                while (r > l && !check(ch[r])) r--;
                int lc = ch[l] | ' ';
                int rc = ch[r] | ' ';
                if (lc != rc) return false;
                else {
                    l++;
                    r--;
                }
            }
            return true;
        }

        private boolean check(char c) {
            c = (char) (c | ' ');
            return (c >= '0' && c <= '9') || c >= 'a' && c <= 'z';
        }
    }

    static class _3nd {

        public static void main(String[] args) {
            _3nd handler = new _3nd();
            TreeNode root = TreeNodeIOUtils.transform("[10,16,5,null,-3,6,11]");
            List<Integer> res = new ArrayList<>();
            int sum = 26;
            handler.rootToLeafSum(root, sum, res);
            res.forEach(System.out::println);
        }

        boolean rootToLeafSum(TreeNode root, int sum, List<Integer> res) {
            if (root == null) return false;
            if (root.left == null && root.right == null) {
                if (root.val == sum) {
                    res.add(root.val);
                    return true;
                } else {
                    return false;
                }
            }
            if (rootToLeafSum(root.left, sum - root.val, res)) {
                res.add(root.val);
                return true;
            }
            if (rootToLeafSum(root.right, sum - root.val, res)) {
                res.add(root.val);
                return true;
            }
            return false;
        }

        public int sizeOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            int l = sizeOfBinaryTree(root.left);
            int r = sizeOfBinaryTree(root.right);
            return l + r + 1;
        }
    }

    static class _4th {

        public static void main(String[] args) {
            _4th handler = new _4th();
            ListNode head = ListNodeUtils.buildListNodeList(new int[]{1, 2, 3, 4, 5});
//            handler.middleNode(head);
            int n = 2;
            handler.removeNthFromEnd(head, n);
        }

        public ListNode middleNode(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.next;
        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode fast = dummy;
            for (int i = 0; i <= n; i++) fast = fast.next;
            ListNode slow = dummy;
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
            return dummy.next;

        }
    }

    static class _1st_1 {
        List<String> res = new ArrayList<>();

        public List<String> letterCasePermutation(String s) {
            int n = s.length();
            char[] chs = new char[n];
            dfs(chs, 0, n, s);
            return res;
        }


        private void dfs(char[] chs, int idx, int n, String s) {
            if (idx == n) {
                res.add(new String(chs));
                return;
            }
            chs[idx] = s.charAt(idx);
            dfs(chs, idx + 1, n, s);
            if (Character.isLetter(chs[idx])) {
                chs[idx] = (char) (s.charAt(idx) ^ ' ');
                dfs(chs, idx + 1, n, s);
            }
        }

    }

    static class _1st_2 {
        public static void main(String[] args) {

        }

        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) return new int[]{-1, -1};
            int[] ans = new int[2];
            ans[0] = findFirst(nums, target);
            ans[1] = findLast(nums, target);
            return ans;
        }


        private int findFirst(int[] nums, int t) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;//左
                if (nums[mid] >= t) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return nums[l] == t ? l : -1;
        }


        private int findLast(int[] nums, int t) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (nums[mid] <= t) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return nums[l] == t ? l : -1;
        }
    }

    static class _1st_3 {

        List<String> wordDict;
        //存字符s的下标索引，这些索引例如idx, [idx,s.length)这个范围内的单词都不能由wordDict的单词组成
        Set<Integer> set = new HashSet<>();

        public boolean wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            return dfs(s, 0);
        }

        private boolean dfs(String s, int i) {
            if (set.contains(i)) return false;
            if (i == s.length()) return true;
            for (int j = i + 1; j <= s.length(); j++) {
                String candidate = s.substring(i, j);
                if (!wordDict.contains(candidate)) continue;
                if (dfs(s, j)) return true;
                else set.add(j);
            }
            set.add(i);
            return false;
        }


    }

    static class _1st_4 {
        Boolean[] cache;
        List<String> wordDict;

        public boolean wordBreak(String s, List<String> wordDict) {
            cache = new Boolean[s.length() + 1];//多放一个位置
            this.wordDict = wordDict;
            return dfs(s, 0);
        }

        /**
         * 下标索引从i开始到len(s)结束，能否由wordDict字典形成这个单词
         *
         * @param s
         * @param i
         * @return
         */
        private boolean dfs(String s, int i) {
            if (i == s.length()) return true;
            if (cache[i] != null) return cache[i];
            for (int j = i + 1; j <= s.length(); j++) {
                //[i,j)是取头不取尾，如leetcode取[0,4)取的是leet
                String candidate = s.substring(i, j);
                //当前这个候选单词没有出现在wordDict里
                if (!wordDict.contains(candidate)) continue;
                //从j这个索引出发，继续找，如果找到了，则将j的索引结果存到cache返回 true
                //从j这个索引出发，继续找，如果没到了，则将j的索引结果存到cache[false] 这时候不需要返回 当前没找到还可以找其他的索引开始的
                if (dfs(s, j)) {
                    return cache[j] = true;
                } else {
                    cache[j] = false;
                }
            }
            return cache[i] = false;
        }
    }

    static class _1st_5 {
        public boolean wordBreak(String s, List<String> wordDict) {
            //记录当前处理到的索引
            Queue<Integer> q = new LinkedList<>();
            //当前索引idx [idx,s.length())不能由wordDict里的单词形成
            Set<Integer> vis = new HashSet<>();
            q.offer(0);
            while (!q.isEmpty()) {
                int i = q.poll();
                for (int j = i + 1; j <= s.length(); j++) {
                    if (vis.contains(j)) continue;
                    String can = s.substring(i, j);
                    if (!wordDict.contains(can)) continue;
                    if (j == s.length()) return true;
                    q.offer(j);
                    vis.add(j);
                }
            }
            return false;
        }
    }

    static class _1st_6 {

        public static void main(String[] args) {
            _1st_6 handler = new _1st_6();
            String s = "leetcode";
            List<String> wordDict = Arrays.asList("leet", "code");
//            s = "catsandog";
//            wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
            handler.wordBreak(s, wordDict);
        }


        public boolean wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            //f[i]表示以s[i-1]结尾的字符串能否拆分成wordDict
            boolean[] f = new boolean[n + 1];
            f[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    String can = s.substring(j, i);
//                    System.out.println(can);
                    if (f[j] && wordDict.contains(can)) {
                        f[i] = true;
                        break;
                    }
                }
            }
            return f[n];
        }
    }

    static class _1st_7 {

        public static void main(String[] args) {
            _1st_7 handler = new _1st_7();
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            handler.spiralOrder(matrix);
        }


        //方向 右 下 左 上
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


        public List<Integer> spiralOrder(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            int r = 0, c = 0;//起始的位置
            int d = 0;//控制方向
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < R * C; i++) {
                res.add(matrix[r][c]);
                matrix[r][c] = -102;//标记这个点已经走过了
                int nr = r + dirs[d][0], nc = c + dirs[d][1];//下一个点
                if (!(nr >= 0 && nr < R && nc >= 0 && nc < C) || matrix[nr][nc] == -102) {
                    d = (d + 1) % 4;
                    nr = r + dirs[d][0];
                    nc = c + dirs[d][1];
                }
                r = nr;
                c = nc;
            }
            return res;
        }


    }

    static class _1st_8 {

        public static void main(String[] args) {
            _1st_8 handler = new _1st_8();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4]");
            System.out.println(handler.preorderTraversal(root));
        }

        public String preorderTraversal(TreeNode root) {
            StringBuilder res = new StringBuilder();
            Stack<TreeNode> stk = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stk.isEmpty()) {
                if (cur != null) {
                    if (cur != root) res.append("(");
                    res.append(cur.val);
                    stk.push(cur);
                    cur = cur.left;
                } else {
                    TreeNode tmp = stk.pop();
                    cur = tmp.right;
                    if (cur != root) res.append(")");

                }
            }
            return res.toString();
        }
    }

    static class _1st_9 {
        public static void main(String[] args) {
            _1st_9 handler = new _1st_9();
            int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
            handler.duplicateZeros(arr);
        }


        public void duplicateZeros(int[] arr) {
            int n = arr.length;
            int i = 0, j = 0;
            while (j < n) {
                if (arr[i] == 0) j++;
                i++;
                j++;
            }
            i--;
            j--;
            while (i >= 0) {
                if (j < n) arr[j] = arr[i];
                if (arr[i] == 0) {
                    arr[--j] = arr[i];
                }
                i--;
                j--;
            }
        }

    }




}
