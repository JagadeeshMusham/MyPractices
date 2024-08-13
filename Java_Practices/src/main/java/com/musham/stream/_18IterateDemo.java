package com.musham.stream;

import java.util.stream.Stream;

public class _18IterateDemo {
    public static void main(String[] args) {
        // Ex1:  Generate a stream of the first 10 natural numbers
        Stream<Integer> naturalNumbers = Stream.iterate(1, n -> n + 1)
                .limit(10);
        naturalNumbers.forEach(n -> System.out.print(n + " "));

        System.out.println("\n");

        // Ex2: Generate the first 10 Fibonacci numbers
        Stream<int[]> fibonacci = Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .limit(10);
        fibonacci.map(f -> f[0])  // Extract the first element of each pair
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\n");

        // Ex3: Generate a stream of even numbers less than 20
        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2)
                .limit(10);


        //foloving is similar to above post Java9
//        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n < 20, n -> n + 2);

        evenNumbers.forEach(n -> System.out.print(n + " "));
    }
}
