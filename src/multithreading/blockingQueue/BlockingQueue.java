package multithreading.blockingQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Anatoliy Shikin
 */
public class BlockingQueue <T>{
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.poll();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}
