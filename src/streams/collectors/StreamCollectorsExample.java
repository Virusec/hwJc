package streams.collectors;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Anatoliy Shikin
 */
public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, List<Order>> collect = orders.stream()
                .collect(Collectors
                        .groupingBy(Order::getProduct));
        System.out.println(collect);
        splitter();

        Map<String, Double> totalCostProduct = orders.stream()
                .collect(Collectors
                        .groupingBy(
                                Order::getProduct,
                                Collectors.summingDouble(Order::getCost)
                        ));
        System.out.println(totalCostProduct);
        splitter();

        List<Order> sortedProductsList = orders.stream()
                .sorted(Comparator
                        .comparingDouble(Order::getCost)
                        .reversed()
                )
                .toList();
        sortedProductsList.forEach(System.out::println);
        splitter();

        List<Order> top3 = sortedProductsList.stream().limit(3).toList();
        double sum = top3.stream().mapToDouble(Order::getCost).sum();
        System.out.println(top3 + "\nTotal price: " + sum);
    }

    public static void splitter() {
        System.out.println("*****************************************************************************************");
    }
}
