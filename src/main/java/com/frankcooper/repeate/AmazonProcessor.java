package com.frankcooper.repeate;

import com.frankcooper._Model;
import org.junit.Assert;

import java.util.Arrays;

public class AmazonProcessor {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int copyBooks(int[] pages, int k) {
            int n = pages.length;
            //f[t][i] 前t个人抄前i本书，最少需要的时间
            //转移方程：f[t][i] =min{max{f[k-1][j],pages[j]+...pages[i-1]}}(0<=j<=i)
            int[][] f = new int[k + 1][n + 1];
            int INF = Integer.MAX_VALUE;
            Arrays.fill(f[0], INF);
            f[0][0] = 0;
            //边界条件：
            //1.0个人抄0本书 f[0][0] = 0;
            //2.0个人抄1...n本书，f[0][1]=f[0][2]=...=f[0][n]=INF
            //3.t个人抄0本书 f[t][0] = 0;
            for (int t = 1; t <= k; t++) {
                f[t][0] = 0;
                for (int i = 1; i <= n; i++) {
                    f[t][i] = INF;
                    int sum = 0;
                    for (int j = i; j >= 0; j--) {
                        f[t][i] = Math.min(f[t][i], Math.max(f[t - 1][j], sum));
                        if (j > 0) sum += pages[j - 1];
                    }
                }
            }
            return f[k][n];
        }

    }

    static class _1st_1 {
        /**
         * @param pages: an array of integers
         * @param k:     An integer
         * @return: an integer
         */
        public int copyBooks(int[] pages, int k) {
            // write your code here
        /*
        /*
        The slowest copier is the person who has most pages. The goal is to let
        the slowest has smallest pages as possible.
        A book must be taken by one person. So we find the book with the max pages.
        The max pages maybe the slowest costs but other person can have more pages with
        multiple books could be slowest too. We need to minimum every copiers page numbers.
        Let's assume the minimum pages is the max pages of a book. Since the copiers take
        books one by one in original order, we can stop a copier if his pages is greater
        than the minimum pages numbers and another copier can continue. If too many
        copiers, we can increase the minimum values to the total pages or until we
        get the copiers number equals k.
        */
            if (pages == null || pages.length == 0) {
                return 0;
            }
            int maxPages = 0;
            int sumPages = 0;
            for (int pageNumber : pages) {
                sumPages += pageNumber;
                maxPages = Math.max(maxPages, pageNumber);
            }
            if (k >= pages.length) return maxPages; // one person one book. someone may not have a book.
            int start = maxPages;
            int end = sumPages;
            while (start + 1 < end) {
                int mid = (start + end) / 2;
                if (getCopierNumber(pages, mid) <= k) {
                    // reduce the minimum pages
                    end = mid;
                } else {
                    // increase the minimum pages
                    start = mid;
                }
            }
            if (getCopierNumber(pages, start) <= k) {
                return start;
            }
            return end;
        }

        private int getCopierNumber(int[] pages, int min) {
            int copiers = 0;
            int copied = 0;
            for (int number : pages) {
                if (copied + number > min) {
                    ++copiers;
                    copied = 0;
                }
                copied += number;
            }
            // last one person is not counted.
            ++copiers;
            return copiers;
        }

        //private int numberOfWorkers(int[] pages, int t) {
        //    int count = 0;
        //    int sum = 0;
        //    for (int i = 0; i < pages.length; i++) {
        //        if (sum + pages[i] > t) {
        //            sum = pages[i];
        //            count++;
        //        } else {
        //            sum += pages[i];
        //        }
        //    }
        //    count++;
        //    return count;
        //}
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertEquals(5, handler.copyBooks(new int[]{3, 2, 4}, 2));
            Assert.assertEquals(2, handler.copyBooks(new int[]{1, 2}, 5));
        }

        //437.书籍复印
        public int copyBooks(int[] pages, int k) {
            //当人数够的时候，恰好有>=len(pages)的人数，只需要每个人抄写一本书即可，这时候最慢的抄书的人花费的时间恰好是最多的那本书的页数
            //当总数为tot时，这时候如果只有一个人抄书，需要这个人抄完整合pages列表
            int maxx = 0, tot = 0;
            for (int x : pages) {
                maxx = Math.max(maxx, x);
                tot += x;
            }
            int start = maxx, end = tot;
            //退出条件是start+1 =end start比end少一个
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (countWorkers(pages, mid) <= k) {//说明当前的mid可能是需要的值(mid...end]排除
                    end = mid;
                } else {
                    start = mid + 1;//当前的count>k，说明该count不满足条件，排除[start...mid]这部分
                }
            }
            //判断下start
            if (countWorkers(pages, start) <= k) return start;
            //start不是目标值的话，就返回end
            return end;
        }

        //给定每个人的工作时长t，每个人都不能超过这个时长，返回需要多少个人能完成所有的工作
        public int countWorkers(int[] pages, int t) {
            int sum = 0;
            int count = 0;
            for (int x : pages) {
                if (x + sum > t) {//如果当前的sum+x比t大，证明这个阶段的人抄书已经到顶了，重新开始并计数
                    sum = x;
                    count++;
                } else {
                    sum += x;
                }
            }
            count++;
            return count;
        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {4, 3, 2, 2, 5, 4, 3, 1};
            int k = 3;
            handler.partitionArray(nums, k);
        }

        public int partitionArray(int[] nums, int k) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {//出口条件是l = r +1
                //返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k
                //当nums[l]<k，说明l这个点不是分割点
                while (l <= r && nums[l] < k) {
                    l++;
                }
                //当nums[r]>=k r点都不会是分隔点
                while (l <= r && nums[r] >= k) {
                    r--;
                }
                //交换 nums[l] 和 nums[r]
                if (l < r) {
                    int t = nums[l];
                    nums[l] = nums[r];
                    nums[r] = t;
                    l++;
                    r--;
                }
            }
            return l;
        }
    }


    static class _4th {


        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "covid2019";
            handler.reformat(s);


        }

        public String reformat(String s) {

            return "";

        }


//        public String reformat(String s) {
//            int sumDigit = 0;
//            for (int i = 0; i < s.length(); i++) {
//                char c = s.charAt(i);
//                if (Character.isDigit(c)) {
//                    sumDigit++;
//                }
//            }
//            int sumAlpha = s.length() - sumDigit;
//            if (Math.abs(sumDigit - sumAlpha) > 1) {
//                return "";
//            }
//            boolean flag = sumDigit > sumAlpha;
//            char[] arr = s.toCharArray();
//            for (int i = 0, j = 1; i < s.length(); i += 2) {
//                if (Character.isDigit(arr[i]) != flag) {
//                    while (Character.isDigit(arr[j]) != flag) {
//                        j += 2;
//                    }
//                    swap(arr, i, j);
//                }
//            }
//            return new String(arr);
//        }
//
//        public void swap(char[] arr, int i, int j) {
//            char c = arr[i];
//            arr[i] = arr[j];
//            arr[j] = c;
//        }


    }
}
