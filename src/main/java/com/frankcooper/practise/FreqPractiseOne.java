package com.frankcooper.practise;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FreqPractiseOne {
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
            int target = 9;
            handler.search(nums, target);

        }

        public int search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] == target) return mid;
                else if (nums[mid] > target) r = mid;
                else l = mid + 1;
            }
            return nums[l] == target ? l : -1;
        }


        public int mySqrt(int x) {
            long l = 1, r = x / 2;
            while (l < r) {
                long mid = l + r >> 1;
                if (mid * mid == x) return (int) mid;
                else if (mid * mid > x) r = mid;
                else l = mid + 1;
            }
            return (int) (l * l == x ? l : l - 1);
        }


        public int firstBadVersion(int n) {
            int l = 1, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                System.out.printf("%d ", mid);
                if (isBadVersion(mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            return l;
        }

        private boolean isBadVersion(int mid) {
            return false;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int mySqrt(int x) {
            long l = 0, r = x;
            while (l < r) {
                long mid = l + r + 1 >> 1;
                if (mid * mid <= x) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return (int) l;
        }

        /**
         * [1]
         * 1
         * []
         * 0
         * 注意上面的两个边界case
         **/
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) return new int[]{-1, -1};
            int[] res = new int[2];
            res[0] = getFirst(nums, target);
            res[1] = getLast(nums, target);
            return res;
        }


        private int getFirst(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] >= target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return nums[l] == target ? l : -1;
        }

        private int getLast(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (nums[mid] <= target) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return nums[l] == target ? l : -1;

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

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            int[] values = {5, 4, 3, 2, 1};
            int[] labels = {1, 1, 2, 2, 3};
            int numWanted = 3;
            int useLimit = 1;
            handler.largestValsFromLabels(values, labels, numWanted, useLimit);
        }

        public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
            int n = values.length;
            Integer[] id = new Integer[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            Arrays.sort(id, (a, b) -> values[b] - values[a]);
            Map<Integer, Integer> map = new HashMap<>();
            int choose = 0;
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (choose >= numWanted) {
                    break;
                }
                int label = labels[id[i]];
                if (map.getOrDefault(label, 0) == useLimit) {
                    continue;
                }
                choose++;
                res += values[id[i]];
                map.put(label, map.getOrDefault(label, 0) + 1);
            }
            return res;
        }
    }

    static class _6th {
        public String oddString(String[] words) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                char[] ch = words[i].toCharArray();
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < ch.length - 1; j++) {
                    sb.append(ch[j + 1] - ch[j]).append("_");
                }
                if (map.containsKey(sb.toString())) {
                    map.put(sb.toString(), -1);
                } else {
                    map.put(sb.toString(), i);
                }
            }
            for (String k : map.keySet()) {
                System.out.println(k);
                if (map.get(k) != -1) {
                    return words[map.get(k)];
                }
            }
            return "";
        }
    }


}
