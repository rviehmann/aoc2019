package com.github.rviehmann.aoc2020;

import java.util.HashMap;
import java.util.Map;

public class Day15 {

    private static final long[] EXAMPLE1 =
            {0, 3, 6};

    private static final long[] EXAMPLE2 =
            {1, 3, 2};

    private static final long[] EXAMPLE3 =
            {2, 1, 3};

    private static final long[] EXAMPLE4 =
            {1, 2, 3};

    private static final long[] EXAMPLE5 =
            {2, 3, 1};

    private static final long[] EXAMPLE6 =
            {3, 2, 1};

    private static final long[] EXAMPLE7 =
            {3, 1, 2};

    // From: https://adventofcode.com/2020/day/15
    private static final long[] INPUT =
            {16, 11, 15, 0, 1, 7};

    private static long[] playGame(long[] input, int turns) {
        long[] game = new long[turns];

        // The array always contains two entries, [0] -> the last occurrence of this number, [1] -> the one before the last.
        Map<Long, Integer[]> lastOccurrences = new HashMap<>();

        for (int turn = 0; turn < turns; turn++) {
            if (turn <= (input.length - 1)) {
                game[turn] = input[turn];
            } else {
                long lastSpoken = game[turn - 1];
                int lastOccurrence = -1;
                if (lastOccurrences.containsKey(lastSpoken)) {
                    Integer[] entry = lastOccurrences.get(lastSpoken);
                    lastOccurrence = entry[1];
                }
                // Determine if this number has been spoken before or not.
                if (lastOccurrence >= 0) {
                    game[turn] = (turn - 1) - lastOccurrence; // The last number is a repeat.
                } else {
                    game[turn] = 0; // The last number is new.
                }
            }

            if (lastOccurrences.containsKey(game[turn])) {
                // Update entry
                Integer[] entry = lastOccurrences.get(game[turn]);
                entry[1] = entry[0];
                entry[0] = turn;
            } else {
                // Create new entry
                Integer[] newEntry = new Integer[2];
                newEntry[1] = -1;
                newEntry[0] = turn;
                lastOccurrences.put(game[turn], newEntry);
            }
        }
        return game;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 15: Examples for puzzle 1 ###");
        long[] game1 = playGame(EXAMPLE1, 2020);
        long[] game2 = playGame(EXAMPLE2, 2020);
        long[] game3 = playGame(EXAMPLE3, 2020);
        long[] game4 = playGame(EXAMPLE4, 2020);
        long[] game5 = playGame(EXAMPLE5, 2020);
        long[] game6 = playGame(EXAMPLE6, 2020);
        long[] game7 = playGame(EXAMPLE7, 2020);
        System.out.println("The 2020th number that has been spoken: " + game1[2019]);
        System.out.println("The 2020th number that has been spoken: " + game2[2019]);
        System.out.println("The 2020th number that has been spoken: " + game3[2019]);
        System.out.println("The 2020th number that has been spoken: " + game4[2019]);
        System.out.println("The 2020th number that has been spoken: " + game5[2019]);
        System.out.println("The 2020th number that has been spoken: " + game6[2019]);
        System.out.println("The 2020th number that has been spoken: " + game7[2019]);
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 15: Examples for puzzle 2 ###");
        long[] game1 = playGame(EXAMPLE1, 30000000);
        long[] game2 = playGame(EXAMPLE2, 30000000);
        long[] game3 = playGame(EXAMPLE3, 30000000);
        long[] game4 = playGame(EXAMPLE4, 30000000);
        long[] game5 = playGame(EXAMPLE5, 30000000);
        long[] game6 = playGame(EXAMPLE6, 30000000);
        long[] game7 = playGame(EXAMPLE7, 30000000);
        System.out.println("The 2020th number that has been spoken: " + game1[29999999]);
        System.out.println("The 2020th number that has been spoken: " + game2[29999999]);
        System.out.println("The 2020th number that has been spoken: " + game3[29999999]);
        System.out.println("The 2020th number that has been spoken: " + game4[29999999]);
        System.out.println("The 2020th number that has been spoken: " + game5[29999999]);
        System.out.println("The 2020th number that has been spoken: " + game6[29999999]);
        System.out.println("The 2020th number that has been spoken: " + game7[29999999]);
    }

    public static long doPuzzle1() {
        long[] game1 = playGame(INPUT, 2020);
        return game1[2019];
    }

    public static long doPuzzle2() {
        long[] game2 = playGame(INPUT, 30000000);
        return game2[29999999];
    }
}
