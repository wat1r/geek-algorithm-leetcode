package com.frankcooper.bank._501_1000;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;

public class _690 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


//            [[1,2,[2]], [2,3,[]]]
//            2
            handler.testOne();
        }


        public void testOne() {
            List<Employee> employees = new ArrayList<>();
            Employee employee = new Employee(1, 2, new ArrayList<Integer>() {{
                add(2);
            }});
            employees.add(employee);
            employee = new Employee(2, 3, new ArrayList<Integer>() {{
            }});
            employees.add(employee);
            getImportance(employees, 2);
        }


        Map<Integer, List<Integer>> subList = new HashMap<>();
        Map<Integer, Integer> impList = new HashMap<>();
        int res = 0;

        public int getImportance(List<Employee> employees, int id) {
            for (Employee e : employees) {
                subList.put(e.id, e.subordinates);
                impList.put(e.id, e.importance);
            }
            List<Integer> list = subList.get(id);
            res += impList.get(id);
            for (int ele : list) {
                helper(ele);
            }
            return res;
        }

        private void helper(int sub) {
            res += impList.get(sub);
            if (subList.get(sub).isEmpty()) return;
            for (int ele : subList.get(sub)) {
                helper(ele);
            }
        }


        @AllArgsConstructor
        class Employee {
            public int id;
            public int importance;
            public List<Integer> subordinates;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public void testOne() {
            List<Employee> employees = new ArrayList<>();
            Employee employee = new Employee(1, 5, new ArrayList<Integer>() {{
                add(2);
                add(3);
            }});
            employees.add(employee);
            employee = new Employee(2, 3, new ArrayList<Integer>() {{
            }});
            employees.add(employee);
            employee = new Employee(3, 3, new ArrayList<Integer>() {{
            }});
            employees.add(employee);
            int id = 1;
            getImportance(employees, id);

        }


        public void testTwo() {
            List<Employee> employees = new ArrayList<>();
            Employee employee = new Employee(1, 5, new ArrayList<Integer>() {{
                add(2);
                add(3);
            }});
            employees.add(employee);
            employee = new Employee(2, 3, new ArrayList<Integer>() {{
                add(4);
            }});
            employees.add(employee);
            employee = new Employee(3, 4, new ArrayList<Integer>() {{
            }});
            employees.add(employee);
            employee = new Employee(4, 1, new ArrayList<Integer>() {{
            }});
            employees.add(employee);
            int id = 1;
            getImportance(employees, id);

        }


        Map<Integer, List<Integer>> sList = new HashMap<>();
        Map<Integer, Integer> iList = new HashMap<>();
        int res = 0;

        public int getImportance(List<Employee> employees, int id) {
            for (Employee employee : employees) {
                sList.put(employee.id, employee.subordinates);
                iList.put(employee.id, employee.importance);
            }
            List<Integer> subs = sList.get(id);
            res += iList.get(id);
            for (int sub : subs) helper(sub);
            return res;
        }

        private void helper(int sub) {
            res += iList.get(sub);
            if (sList.get(sub).isEmpty()) return;
            for (int s : sList.get(sub)) helper(s);
        }


        class Employee {
            public int id;
            public int importance;
            public List<Integer> subordinates;

            public Employee(int id, int importance, List<Integer> subordinates) {
                this.id = id;
                this.importance = importance;
                this.subordinates = subordinates;
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
