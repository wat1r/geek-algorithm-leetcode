package com.frankcooper.bank._1001_1500;

import java.util.*;

public class _1169 {

    static _1169 handler = new _1169();

    public static void main(String[] args) {
        handler.invalidTransactions(new String[]{"alice,20,800,mtv", "alice,50,1200,mtv"});
    }

    public List<String> invalidTransactions(String[] transactions) {
        List<String> result = new ArrayList<>();
        Map<String, List<TradeInfo>> map = new HashMap<>();
        for (String transaction : transactions) {
            String[] arr = transaction.split(",");
            map.put(arr[0], map.getOrDefault(arr[0], new ArrayList<>()));
            map.get(arr[0]).add(new TradeInfo(arr[0], Integer.valueOf(arr[1]), Integer.valueOf(arr[2]), arr[3]));
        }
        for (Map.Entry<String, List<TradeInfo>> entry : map.entrySet()) {
            List<TradeInfo> tradeInfos = entry.getValue();
            tradeInfos.sort(Comparator.comparing(e -> e.time));
            for (int i = 0; i < tradeInfos.size(); i++) {
                TradeInfo cur = tradeInfos.get(i);
                boolean isAdd = false;
                if (i < tradeInfos.size() - 1) {
                    TradeInfo next = tradeInfos.get(i + 1);
                    if ((next.time - cur.time <= 60)
                            && !next.city.equals(cur.city)) {
                        result.add(cur.toString());
                        result.add(next.toString());
                        isAdd = true;
                    }
                }
                if (!isAdd && cur.amount > 1000) {
                    result.add(cur.toString());
                }
            }

        }
        return result;
    }

    class TradeInfo {
        String name;
        Integer time;
        Integer amount;
        String city;

        public TradeInfo(String name, Integer time, Integer amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }

        @Override
        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }
    }
}
