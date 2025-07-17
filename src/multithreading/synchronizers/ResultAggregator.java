package multithreading.synchronizers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Anatoliy Shikin
 */
public class ResultAggregator {
    private final Map<Integer, Integer> results = new ConcurrentHashMap<>();

    public void addResult(int taskId, int result) {
        results.put(taskId, result);
    }

    public void printResults() {
        System.out.println("All tasks completed. All results:");
        results.forEach((id, result) ->
                System.out.println("Task " + id + ": " + result)
        );
    }
}
