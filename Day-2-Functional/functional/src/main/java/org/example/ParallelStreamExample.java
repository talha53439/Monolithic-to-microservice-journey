package org.example;

import java.time.LocalTime;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("09:00", "10:00", "11:00", "12:00");
        System.out.println("Sequential Stream:");
        long start = System.currentTimeMillis();
//        IntStream.range(1, 10).forEach(System.out::println);
        stream
                .map(LocalTime::parse)
                .forEach(System.out::println);

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");

        System.out.println("\nParallel Stream:");
        start = System.currentTimeMillis();
//        IntStream.range(1, 10).parallel().forEach(System.out::println);
        Stream.of("09:00", "10:00", "11:00", "12:00")
                .parallel()
                .map(LocalTime::parse)
                .forEach(System.out::println);
        end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");
    }
}