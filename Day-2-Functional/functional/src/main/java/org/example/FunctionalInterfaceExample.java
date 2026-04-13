package org.example;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Predicate: Check if a number is even
        Predicate<Integer> isEven = (num) -> num % 2 == 0;
        System.out.println(isEven.test(10)); // Output: true

        // Function: Convert a String to Upper Case
        Function<String, String> toUpperCase = String::toUpperCase;
        System.out.println(toUpperCase.apply("hello")); // Output: HELLO

        Supplier<String> supplier = () -> "hello supplier";

        System.out.println(supplier.get());//return

        Consumer<String> upper = String::toUpperCase;
        Consumer<String> print= System.out::println;
//        consumer.accept("hello");//no return
        upper.andThen(print).accept("Java Spring");
        LocalDate.now();
    }
    public String function(String str) {
        return str.toUpperCase();
    }
    public String supplier(){
        return "supplier";
    }
    public void consumer(String str){
        System.out.println(str);
    }
}