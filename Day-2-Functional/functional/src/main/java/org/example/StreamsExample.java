package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class StreamsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "KJane", "Jack", "Jill");

        // Using Stream to filter and print names that start with 'J'
        names.stream()
             .filter(name -> name.startsWith("J"))
             .forEach(System.out::println);
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        Integer result = binaryOperator.apply(1, 2);
        System.out.println(result);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }
}