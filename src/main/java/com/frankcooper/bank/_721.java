package com.frankcooper.bank;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/9/19 14:58
 * Description
 */
public class _721 {


    static _721 handler = new _721();

    public static void main(String[] args) {

        handler.testOne();

    }


    private void testOne() {

        _1st first = new _1st();


        List<List<String>> accounts = new ArrayList<List<String>>() {{
            add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
            add(Arrays.asList("John", "johnnybravo@mail.com"));
            add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
            add(Arrays.asList("Mary", "mary@mail.com"));
        }};
        first.accountsMerge(accounts);

    }


    public List<List<String>> accountsMerge(List<List<String>> accounts) {


        dfs(accounts, accounts.get(0), 0);


        return null;
    }

    private void dfs(List<List<String>> accounts, List<String> currList, int currIdx) {

        if (currIdx == currList.size() - 1) {

//            if(currList.)

            return;
        }


        for (int i = currIdx + 1; i < accounts.size(); i++) {
//                dfs(accounts,currList,i+1);
        }


//        if(curr==accounts.get(i))


    }


    class _1st {

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Map<String, String> emailToName = new HashMap();
            Map<String, ArrayList<String>> graph = new HashMap();
            for (List<String> account : accounts) {
                String name = "";
                for (String email : account) {
                    if (name == "") {
                        name = email;
                        continue;
                    }
                    graph.computeIfAbsent(email, x -> new ArrayList<>()).add(account.get(1));
                    graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(email);
                    emailToName.put(email, name);
                }
            }

            Set<String> seen = new HashSet();
            List<List<String>> ans = new ArrayList();
            for (String email : graph.keySet()) {
                if (!seen.contains(email)) {
                    seen.add(email);
                    Stack<String> stack = new Stack();
                    stack.push(email);
                    List<String> component = new ArrayList();
                    while (!stack.empty()) {
                        String node = stack.pop();
                        component.add(node);
                        for (String nei : graph.get(node)) {
                            if (!seen.contains(nei)) {
                                seen.add(nei);
                                stack.push(nei);
                            }
                        }
                    }
                    Collections.sort(component);
                    component.add(0, emailToName.get(email));
                    ans.add(component);
                }
            }
            return ans;
        }

    }


}
