package com.github.rviehmann.other;

// Very simple requirements, see: https://blog.codinghorror.com/why-cant-programmers-program/
public class FizzBuzz {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                StringBuilder out = new StringBuilder();
                if (i % 3 == 0) {
                    out.append("Fizz");
                }
                if (i % 5 == 0) {
                    out.append("Buzz");
                }
                System.out.println(out);
            } else {
                System.out.println(i);
            }
        }
    }
}
