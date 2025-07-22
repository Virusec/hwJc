package streams.fork_join_pool;

import java.util.concurrent.ForkJoinPool;

/**
 * @author Anatoliy Shikin
 */
public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 25; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}
