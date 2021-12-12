package com.github.rviehmann.aoc2021;

import java.util.HashMap;
import java.util.Map;

public class Day06 {

    private static final int[] SAMPLE_AGES = {
            3, 4, 3, 1, 2
    };

    private static final int[] INPUT_AGES = {
            3, 5, 2, 5, 4, 3, 2, 2, 3, 5, 2, 3, 2, 2, 2, 2, 3, 5, 3, 5, 5, 2, 2, 3, 4, 2, 3, 5, 5, 3, 3, 5, 2, 4, 5, 4, 3, 5, 3, 2, 5, 4, 1, 1, 1, 5, 1, 4, 1, 4, 3, 5, 2, 3, 2, 2, 2, 5, 2, 1, 2, 2, 2, 2, 3, 4, 5, 2, 5, 4, 1, 3, 1, 5, 5, 5, 3, 5, 3, 1, 5, 4, 2, 5, 3, 3, 5, 5, 5, 3, 2, 2, 1, 1, 3, 2, 1, 2, 2, 4, 3, 4, 1, 3, 4, 1, 2, 2, 4, 1, 3, 1, 4, 3, 3, 1, 2, 3, 1, 3, 4, 1, 1, 2, 5, 1, 2, 1, 2, 4, 1, 3, 2, 1, 1, 2, 4, 3, 5, 1, 3, 2, 1, 3, 2, 3, 4, 5, 5, 4, 1, 3, 4, 1, 2, 3, 5, 2, 3, 5, 2, 1, 1, 5, 5, 4, 4, 4, 5, 3, 3, 2, 5, 4, 4, 1, 5, 1, 5, 5, 5, 2, 2, 1, 2, 4, 5, 1, 2, 1, 4, 5, 4, 2, 4, 3, 2, 5, 2, 2, 1, 4, 3, 5, 4, 2, 1, 1, 5, 1, 4, 5, 1, 2, 5, 5, 1, 4, 1, 1, 4, 5, 2, 5, 3, 1, 4, 5, 2, 1, 3, 1, 3, 3, 5, 5, 1, 4, 1, 3, 2, 2, 3, 5, 4, 3, 2, 5, 1, 1, 1, 2, 2, 5, 3, 4, 2, 1, 3, 2, 5, 3, 2, 2, 3, 5, 2, 1, 4, 5, 4, 4, 5, 5, 3, 3, 5, 4, 5, 5, 4, 3, 5, 3, 5, 3, 1, 3, 2, 2, 1, 4, 4, 5, 2, 2, 4, 2, 1, 4
    };

    private static long calculatePopulationSize(int[] input, int days) {
        Map<Integer, Long> map = buildMap(input);

        for (int day = 1; day <= days; day++) {
            long nrZeroes = map.get(0);

            for (int age = 0; age <= 7; age++) {
                map.put(age, map.get(age + 1));
            }

            map.put(6, map.get(6) + nrZeroes);
            map.put(8, nrZeroes);
        }
        return map.values()
                  .stream()
                  .reduce(0L, Long::sum);
    }

    private static Map<Integer, Long> buildMap(int[] input) {
        // Key: age in days
        // Value: nr. of fishes with this age
        Map<Integer, Long> map = new HashMap<>();

        for (int age = 0; age <= 8; age++) {
            map.put(age, 0L);
        }
        for (int age : input) {
            map.put(age, map.get(age) + 1);
        }
        return map;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 06: Examples for puzzle 1 ###");
        System.out.println("Population size after 80 days: " + calculatePopulationSize(SAMPLE_AGES, 80));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 06: Examples for puzzle 2 ###");
        System.out.println("Population size after 256 days: " + calculatePopulationSize(SAMPLE_AGES, 256));
    }

    public static long doPuzzle1() {
        return calculatePopulationSize(INPUT_AGES, 80);
    }

    public static long doPuzzle2() {
        return calculatePopulationSize(INPUT_AGES, 256);
    }
}
