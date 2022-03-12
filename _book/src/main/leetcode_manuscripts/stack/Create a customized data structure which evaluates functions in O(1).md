## Create a customized data structure which evaluates functions in O(1)（创建自定义的在O(1)复杂度评估函数的数据结构）

创建有如下函数的数据结构（复杂度都是O(1)）：

- GetLastElement()
- RemoveLastElement()
- AddElement() 
- GetMin()

### Code:



```java
    static class _1st_3 {
        public static void main(String[] args) {
            _1st_3 newStack = new _1st_3();
            newStack.addElement(5);
            newStack.addElement(7);
            newStack.addElement(3);
            System.out.println("min element :: " + newStack.getMin());
            newStack.removeLastElement();
            newStack.addElement(2);
            newStack.addElement(9);
            System.out.println("last element :: " + newStack.getLastElement());
            newStack.addElement(0);
            System.out.println("min element :: " + newStack.getMin());
            newStack.removeLastElement();
            newStack.addElement(11);
            System.out.println("min element :: " + newStack.getMin());
        }

        static class Pair {
            int ele;
            int minEle;

            public Pair(int ele, int minEle) {
                this.ele = ele;
                this.minEle = minEle;
            }
        }

        int min = Integer.MAX_VALUE;
        List<Pair> stk = new ArrayList<>();

        public void addElement(int x) {
            if (stk.size() == 0 || x < min) {
                min = x;
            }
            Pair pair = new Pair(x, min);
            stk.add(pair);
            System.out.println(x + " inserted successfully");
        }

        public int getLastElement() {
            if (stk.size() == 0) {
                System.out.println("UnderFlow Error");
                return -1;
            }
            return stk.get(stk.size() - 1).ele;
        }

        public void removeLastElement() {
            if (stk.size() == 0) {
                System.out.println("UnderFlow Error");
            } else if (stk.size() == 1) {
                min = Integer.MAX_VALUE;
            } else {
                min = stk.get(stk.size() - 2).minEle;
            }
            stk.remove(stk.size() - 1);
            System.out.println("removed successfully");
        }


        public int getMin() {
            if (stk.size() == 0) {
                System.out.println("UnderFlow Error");
                return -1;
            }
            return stk.get(stk.size() - 1).minEle;
        }
    }
```



```java
5 inserted successfully
7 inserted successfully
3 inserted successfully
min element :: 3
removed successfully
2 inserted successfully
9 inserted successfully
last element :: 9
0 inserted successfully
min element :: 0
removed successfully
11 inserted successfully
min element :: 2
```



### Reference

- [Create a customized data structure which evaluates functions in O(1)](https://www.geeksforgeeks.org/create-customized-data-structure-evaluates-functions-o1/)

