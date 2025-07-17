package multithreading.synchronizers;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Anatoliy Shikin
 */
public class ComplexTask implements Runnable {
    private final int taskId;
    private final CyclicBarrier barrier;
    private final ResultAggregator aggregator;

    public ComplexTask(int taskId, CyclicBarrier barrier, ResultAggregator aggregator) {
        this.taskId = taskId;
        this.barrier = barrier;
        this.aggregator = aggregator;
    }

    @Override
    public void run() {
        try {
            int result = execute();
            aggregator.addResult(taskId, result);
            System.out.println(Thread.currentThread().getName() + " completed task " + taskId + " with result " + result);

            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int execute() {
        Random random = new Random();
        try {
            Thread.sleep(500 + random.nextInt(1000)); // Имитация задержки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return taskId * 10 + random.nextInt(10); // Произвольный результат
    }
}
