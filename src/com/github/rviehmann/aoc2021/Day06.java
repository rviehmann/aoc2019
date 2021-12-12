package com.github.rviehmann.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 {

    private static final int[] SAMPLE_AGES = {
            3, 4, 3, 1, 2
    };

    private static final int[] INPUT_AGES = {
            3, 5, 2, 5, 4, 3, 2, 2, 3, 5, 2, 3, 2, 2, 2, 2, 3, 5, 3, 5, 5, 2, 2, 3, 4, 2, 3, 5, 5, 3, 3, 5, 2, 4, 5, 4, 3, 5, 3, 2, 5, 4, 1, 1, 1, 5, 1, 4, 1, 4, 3, 5, 2, 3, 2, 2, 2, 5, 2, 1, 2, 2, 2, 2, 3, 4, 5, 2, 5, 4, 1, 3, 1, 5, 5, 5, 3, 5, 3, 1, 5, 4, 2, 5, 3, 3, 5, 5, 5, 3, 2, 2, 1, 1, 3, 2, 1, 2, 2, 4, 3, 4, 1, 3, 4, 1, 2, 2, 4, 1, 3, 1, 4, 3, 3, 1, 2, 3, 1, 3, 4, 1, 1, 2, 5, 1, 2, 1, 2, 4, 1, 3, 2, 1, 1, 2, 4, 3, 5, 1, 3, 2, 1, 3, 2, 3, 4, 5, 5, 4, 1, 3, 4, 1, 2, 3, 5, 2, 3, 5, 2, 1, 1, 5, 5, 4, 4, 4, 5, 3, 3, 2, 5, 4, 4, 1, 5, 1, 5, 5, 5, 2, 2, 1, 2, 4, 5, 1, 2, 1, 4, 5, 4, 2, 4, 3, 2, 5, 2, 2, 1, 4, 3, 5, 4, 2, 1, 1, 5, 1, 4, 5, 1, 2, 5, 5, 1, 4, 1, 1, 4, 5, 2, 5, 3, 1, 4, 5, 2, 1, 3, 1, 3, 3, 5, 5, 1, 4, 1, 3, 2, 2, 3, 5, 4, 3, 2, 5, 1, 1, 1, 2, 2, 5, 3, 4, 2, 1, 3, 2, 5, 3, 2, 2, 3, 5, 2, 1, 4, 5, 4, 4, 5, 5, 3, 3, 5, 4, 5, 5, 4, 3, 5, 3, 5, 3, 1, 3, 2, 2, 1, 4, 4, 5, 2, 2, 4, 2, 1, 4
    };

    private static List<Integer> calculateAges(int[] input, int days) {
        List<Integer> list = Arrays.stream(input)
                                   .boxed()
                                   .collect(Collectors.toList());

        for (int day = 1; day <= days; day++) {
            long nrZeroes = nrZeroes(list);
            list = list.stream()
                       .map(age -> age == 0 ? 6 : age - 1)
                       .collect(Collectors.toList());
            for (int zero = 0; zero < nrZeroes; zero++) {
                list.add(8);
            }
        }

        return list;
    }

    private static long nrZeroes(List<Integer> list) {
        return list.stream()
                   .filter(age -> age == 0)
                   .count();
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 06: Examples for puzzle 1 ###");
        System.out.println("Population size after 80 days: " + calculateAges(SAMPLE_AGES, 80).size());
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 06: Examples for puzzle 2 ###");
        System.out.println("Population size after 256 days: " + calculateAges(SAMPLE_AGES, 256).size());
    }

    public static long doPuzzle1() {
        return calculateAges(INPUT_AGES, 80).size();
    }
}
