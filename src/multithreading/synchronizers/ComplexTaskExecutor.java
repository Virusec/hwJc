package multithreading.synchronizers;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Anatoliy Shikin
 */
public class ComplexTaskExecutor {
    private final int tasks;

    public ComplexTaskExecutor(int tasks) {
        this.tasks = tasks;
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);
        ResultAggregator aggregator = new ResultAggregator();

        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, aggregator::printResults);

        for (int i = 0; i < numberOfTasks; i++) {
            executorService.submit(new ComplexTask(i, barrier, aggregator));
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
