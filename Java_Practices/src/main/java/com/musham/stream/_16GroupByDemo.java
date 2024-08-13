package com.musham.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _16GroupByDemo {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> empList = new ArrayList<>();
        empList.add("Jagadeesh");
        empList.add("Shyamala");
        empList.add("Srinu");

        Map<Character, List<String>> groupByAlphabet = empList.stream().collect(
                Collectors.groupingBy(e -> e.charAt(0)));

        System.out.println("The Names starting from letter 'S': " + groupByAlphabet.get('S'));
        System.out.println("The Names starting from letter 'J': " + groupByAlphabet.get('J'));


    }
}
