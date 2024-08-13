package com.musham.stream;

import java.util.stream.Stream;

public class _17GenerateDemo {
    public static void main(String[] args) {
        // Ex1: Generate a stream of 5 random numbers
        Stream<Double> randomNumbers = Stream.generate(Math::random)
                .limit(5);
        randomNumbers.forEach(System.out::println);

        // Ex2: Generate a stream of constant value "Hello"
        Stream<String> greetings = Stream.generate(() -> "Hello")
                .limit(3);
        greetings.forEach(System.out::println);
    }
}
