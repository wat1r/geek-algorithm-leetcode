package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FrankCooper
 * Date 2020/8/29 17:59
 * Description
 */
public class _690 {

    static _690 handler = new _690();


    public static void main(String[] args) {

//        {{1, 5, {2, 3}}, {2, 3, {}}, {3, 3, {}}}

        handler.testOne();
//        handler.testTwo();

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
