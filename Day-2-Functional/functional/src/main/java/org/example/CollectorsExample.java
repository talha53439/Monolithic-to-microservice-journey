package org.example;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;

public class CollectorsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Collecting names into a List
        Predicate<String> p = name -> name.startsWith("A");
        Predicate<String> p2 = name -> name.startsWith("B");
        List<String> filteredNames = names.stream()
                                          .filter(p.or(p2))
                                          .collect(Collectors.toList());

        System.out.println(filteredNames); // Output: [Alice]

        List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "C", "D", "E", "F", "G");
        Map<String, Long> result = list.stream()
                .collect(Collectors.groupingBy(col -> col, Collectors.counting()));
        System.out.println(result);
    }
}