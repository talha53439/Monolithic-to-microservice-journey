package org.example;

import java.util.function.Function;

public class MethodRefExample {
    public static void main(String[] args) {
        // Using Lambda Expression
        Function<String, Integer> lambdaFunction = (str) -> str.length();

        // Using Method Reference
        Function<String, Integer> methodRefFunction = String::length;

        System.out.println(lambdaFunction.apply("Java"));  // Output: 4
        System.out.println(methodRefFunction.apply("Method Reference")); // Output: 16
    }
}