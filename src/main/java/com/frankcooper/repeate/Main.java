package com.frankcooper.repeate;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
////            System.out.println(processOne(in.nextInt()));
//            int m = in.nextInt(), n = in.nextInt();
//            int[] nums = new int[n];
//            for (int i = 0; i < n; i++) nums[i] = in.nextInt();
////            System.out.println(processTwo(m, n, nums));
//        }


        List<Employee> employees = new ArrayList<>();
//        add some data
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int res = (o2.age >= o1.age) ? 1 : o1.id - o2.id;
                return res;
            }
        });


    }


    public static class Employee implements Comparable<Employee> {
        private Integer age;
        private Integer id;


        @Override
        public int compareTo(Employee o) {
            if (this.getAge() > o.getAge()) {
                return -1;// this-o小于0，表示this在前 o在后
            } else if (this.getAge() == o.getAge()) {
                if (this.getId() > o.getId()) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;// this-o大于0，表示this在后 o在前
            }
        }


        public Employee(Integer age, Integer id) {
            this.age = age;
            this.id = id;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


    }


    /**
     * https://www.cnblogs.com/xuyy-isee/p/10732186.html
     *
     * @param m
     * @param n
     * @param nums
     * @return
     */
    private static int processTwo(int m, int n, int[] nums) {
        Arrays.sort(nums);
        int idx = nums.length % m - 1;
        int ans = 0;
        for (int i = idx; i < nums.length; i += m) {
            ans += nums[i];
        }
        return ans;
    }


    private static String processOne(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            n--;
            sb.append((char) ('a' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }


}
