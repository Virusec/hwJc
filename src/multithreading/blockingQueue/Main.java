package multithreading.blockingQueue;

/**
 * @author Anatoliy Shikin
 */
public class Main {
    public static void main(String[] args) {
        int capacity = 10;
        BlockingQueue<Integer> queue = new BlockingQueue<>(capacity);

        Runnable producer = () -> {
            int i = 0;
            while (true) {
                try {
                    queue.enqueue(i);
                    System.out.println("Производитель добавил: " + i);
                    i++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    int value = queue.dequeue();
                    System.out.println("Потребитель получил: " + value);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}