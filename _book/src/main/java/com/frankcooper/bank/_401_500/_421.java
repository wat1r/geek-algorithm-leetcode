package com.frankcooper.platform.leetcode.bank._401_500;


import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Objects;

public class _421 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//            handler.findMaximumXOR(nums);
            nums = new int[]{3, 10, 5, 25, 2, 8};
            Assert.assertEquals(handler.findMaximumXOR(nums), 28);
        }


        HashMap<Pair, Integer> memo = new HashMap<>();

        public int findMaximumXOR(int[] nums) {
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    int u = nums[i], v = nums[j];
                    if (memo.containsKey(new Pair(u, v))) {
                        System.out.printf("i:%d,j:%d\n", i, j);
                        continue;
                    }
                    int sub = u ^ v;
                    ans = Math.max(ans, sub);
                    memo.put(new Pair(u, v), sub);
                }
            }
            return ans;
        }


        class Pair {
            int u, v;

            public Pair(int u, int v) {
                this.u = u;
                this.v = v;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return (u == pair.u &&
                        v == pair.v) || (u == pair.v &&
                        v == pair.u);
            }

            @Override
            public int hashCode() {
                return Objects.hash(u, v);
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{3, 10, 5, 25, 2, 8};
            Assert.assertEquals(handler.findMaximumXOR(nums), 28);
        }

        class Trie {
            Trie[] children;

            public Trie() {
                children = new Trie[2];
            }
        }

        public int findMaximumXOR(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            // Init Trie.
            Trie root = new Trie();
            for (int num : nums) {
                Trie curNode = root;
                for (int i = 30; i >= 0; i--) {
                    int curBit = (num >> i) & 1;
                    if (curNode.children[curBit] == null) {
                        curNode.children[curBit] = new Trie();
                    }
                    curNode = curNode.children[curBit];
                }
            }
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                Trie curNode = root;
                int curSum = 0;
                for (int i = 30; i >= 0; i--) {
                    int curBit = (num >> i) & 1;
                    if (curNode.children[curBit ^ 1] != null) {
                        curSum += (1 << i);
                        curNode = curNode.children[curBit ^ 1];
                    } else {
                        curNode = curNode.children[curBit];
                    }
                }
                max = Math.max(curSum, max);
            }
            return max;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = new int[]{3, 10, 5, 25, 2, 8};
            for (int num : nums) System.out.println(PrintUtils.toBinaryString(num, 6));


            Assert.assertEquals(handler.findMaximumXOR(nums), 28);

        }

        class Trie {
            Trie[] children;

            public Trie() {
                this.children = new Trie[2];
            }
        }

        public int findMaximumXOR(int[] nums) {
            Trie root = new Trie();
            for (int num : nums) {
                Trie curr = root;
                for (int i = 30; i >= 0; i--) {
                    int bit = (num >> i) & 1;
//                    System.out.printf("num:%d,bit:%d\n", num, bit);
                    if (curr.children[bit] == null) {
                        curr.children[bit] = new Trie();
                    }
                    curr = curr.children[bit];
                }
            }
            int ans = Integer.MIN_VALUE;
            for (int num : nums) {
                Trie curr = root;
                int currSum = 0;
                for (int i = 30; i >= 0; --i) {
                    int bit = (num >> i) & 1;
//                    System.out.printf("num:%d,bit:%d\n", num, bit);
                    if (curr.children[bit ^ 1] != null) {
                        curr = curr.children[bit ^ 1];
                        currSum += (1 << i);
                    } else {
                        curr = curr.children[bit];
                    }
                }
                System.out.println(currSum);
                ans = Math.max(ans, currSum);
            }

            return ans;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int findMaximumXOR(int[] nums) {
            for (int x : nums) insert(x);
            int maxx = 0;
            for (int x : nums) {
                maxx = Math.max(maxx, query(x));
            }
            return maxx;
        }


        Trie root = new Trie();

        public void insert(int x) {
            Trie cur = root;
            for (int i = 30; i >= 0; i--) {
                int u = x >> i & 1;//找打二进制的第i位上判断是0还是1
                if (cur.children[u] == null) {
                    cur.children[u] = new Trie();
                }
                cur = cur.children[u];
            }
        }


        public int query(int x) {
            Trie cur = root;
            int res = 0;
            for (int i = 30; i >= 0; i--) {
                int u = x >> i & 1;//找打二进制的第i位上判断是0还是1
                if (cur.children[u ^ 1] != null) {//如果当前位u的另外一个分支可以走的，那就走这个分支
                    res += (1 << i);//相当于当前的值左移i位叠加到res上
                    cur = cur.children[u ^ 1];
                } else {//另外一个分支是空，只能和当前的分支一起前行
                    cur = cur.children[u];
                }
            }
            return res;
        }


        class Trie {
            Trie[] children = new Trie[2];
        }
    }
}
