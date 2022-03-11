package com.frankcooper.bank._601_700;

import java.util.*;

public class _692 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<String> topKFrequent(String[] words, int k) {
            List<String> resList = new ArrayList<>();
            if (words == null || words.length == 0) {
                return resList;
            }
            Map<String, Integer> map = new HashMap<>();
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            List<String>[] bucket = new List[words.length + 1];
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                if (bucket[count] == null) {
                    bucket[count] = new ArrayList<>();
                }
                bucket[count].add(entry.getKey());
            }
            for (List<String> list : bucket) {
                if (list != null) {
                    Collections.sort(list);
                }
            }
            flag:
            for (int i = bucket.length - 1; i >= 0 && resList.size() < k; i--) {
                if (bucket[i] != null) {
                    for (String item : bucket[i]) {
                        if (resList.size() == k) {
                            break flag;
                        }
                        resList.add(item);
                    }
                }
            }
            return resList;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> map = new HashMap<>();
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            PriorityQueue<Object[]> pq = new PriorityQueue<Object[]>(k, (o1, o2) -> {
                int freq1 = (Integer) o1[0], freq2 = (Integer) o2[0];
                if (freq1 != freq2) return freq1 - freq2;
                String word1 = (String) o1[1], word2 = (String) o2[1];
                return word2.compareTo(word1);
            });
            for (String word1 : map.keySet()) {
                int freq1 = map.get(word1);
                if (pq.size() < k) {
                    pq.add(new Object[]{freq1, word1});
                } else {
                    Object[] cur = pq.peek();
                    int freq2 = (Integer) cur[0];
                    String word2 = (String) cur[1];
                    if (freq1 > freq2) {
                        pq.poll();
                        pq.add(new Object[]{freq1, word1});
                    } else if (freq1 == freq2) {
                        if (word1.compareTo(word2) < 0) {
                            pq.poll();
                            pq.add(new Object[]{freq1, word1});
                        }
                    }
                }
            }
            List<String> res = new ArrayList<>();
            while (!pq.isEmpty()) res.add((String)pq.poll()[1]);
            Collections.reverse(res);
            return res;
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
