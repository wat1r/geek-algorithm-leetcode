

## 4268.性感素数

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        process(N);
    }

    private static boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    private static void process(int N) {
        for (int i = N - 6; i <= N + 6; i += 12) {
            if (isPrime(i) && isPrime(N)) {
                System.out.println("Yes\n" + i);
                return;
            }
        }
        for (int i = N + 1; ; i++) {
            if (isPrime(i) && (isPrime(i - 6) || isPrime(i + 6))) {
                System.out.println("No\n" + i);
                return;
            }
        }
    }

}
```









## 4274.后缀表达式







## 4275.Dijkstra序列