package org.example;


public class Main {
    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello and welcome! from r1");
            }
        };
        Runnable r2=()->System.out.println("Hello and welcome! from r2");

        r.run();
        r2.run();
    }
}