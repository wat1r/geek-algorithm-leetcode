package com.frankcooper.classic.hierholzer;

import java.util.*;

public class Hierholzer {


    public static void main(String[] args) {
        Hierholzer handler = new Hierholzer();
        handler.testOne();

    }


    public void testOne() {
        List<List<Integer>> adj1 = new ArrayList<>();
        adj1.add(new ArrayList<Integer>() {{
            add(1);
        }});
        adj1.add(new ArrayList<Integer>() {{
            add(2);
        }});
        adj1.add(new ArrayList<Integer>() {{
            add(0);
        }});
        printCircuit(adj1);
        List<List<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<Integer>() {{
            add(1);
            add(6);
        }});
        adj2.add(new ArrayList<Integer>() {{
            add(2);
        }});
        adj2.add(new ArrayList<Integer>() {{
            add(0);
            add(3);
        }});
        adj2.add(new ArrayList<Integer>() {{
            add(4);
        }});
        adj2.add(new ArrayList<Integer>() {{
            add(2);
            add(5);
        }});
        adj2.add(new ArrayList<Integer>() {{
            add(0);
        }});
        adj2.add(new ArrayList<Integer>() {{
            add(4);
        }});
        printCircuit(adj2);
    }

    public void printCircuit(List<List<Integer>> adj) {
        //edgeCount记录记录有向图的边的条数，从当前节点v出发
        Map<Integer, Integer> edgeCount = new HashMap<>();
        for (int i = 0; i < adj.size(); i++) {
            edgeCount.put(i, adj.get(i).size());
        }
        if (edgeCount.isEmpty()) return;
        //栈用来记录节点
        Stack<Integer> currPath = new Stack<>();
        //存储欧拉回路
        List<Integer> circuit = new ArrayList<>();
        //可以从任何点出发
        currPath.push(0);
        int currV = 0; //当前的节点
        while (!currPath.isEmpty()) {
            //当前还有没有走完的边
            if (edgeCount.containsKey(currV) && edgeCount.get(currV) > 0) {
                currPath.push(currV);//存储当前节点
                int nextV = adj.get(currV).get(adj.get(currV).size() - 1);//找下个节点  也可以remove 掉第一个 0
                //删边与删除节点
                edgeCount.put(currV, edgeCount.get(currV) - 1);
                adj.get(currV).remove(adj.get(currV).size() - 1);
                //移动到下一个节点
                currV = nextV;
            } else {//回溯找circuit
                circuit.add(currV);
                currV = currPath.get(currPath.size() - 1);
                currPath.pop();
            }
        }
        for (int i = circuit.size() - 1; i >= 0; i--) {
            System.out.print(circuit.get(i));
            if (i != 0) System.out.print(" -> ");
        }
//        System.out.print("\n");
    }


}
