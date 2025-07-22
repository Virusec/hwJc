package streams.fork_join_pool;

import java.util.concurrent.RecursiveTask;

/**
 * @author Anatoliy Shikin
 */
public class FactorialTask extends RecursiveTask<Long> {
    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) {
            return 1L;
        }
        FactorialTask subTask = new FactorialTask(n - 1);
        subTask.fork();
        return n * subTask.join();
    }
}
