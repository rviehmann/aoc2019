package com.github.rviehmann.aoc2019;

import com.github.rviehmann.aoc2019.Intcode.Memory;

import java.util.Arrays;

import static com.github.rviehmann.aoc2019.Intcode.interpretIntcode;

public class Day02 {

    // From: https://adventofcode.com/2019/day/2/input
    private static final long[] MEMORY = {
            1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 10, 19, 1, 19, 5, 23, 2, 23, 9, 27, 1, 5, 27, 31, 1, 9, 31, 35, 1, 35, 10, 39, 2, 13, 39, 43, 1, 43, 9, 47, 1, 47, 9, 51, 1, 6, 51, 55, 1, 13, 55, 59, 1, 59, 13, 63, 1, 13, 63, 67, 1, 6, 67, 71, 1, 71, 13, 75, 2, 10, 75, 79, 1, 13, 79, 83, 1, 83, 10, 87, 2, 9, 87, 91, 1, 6, 91, 95, 1, 9, 95, 99, 2, 99, 10, 103, 1, 103, 5, 107, 2, 6, 107, 111, 1, 111, 6, 115, 1, 9, 115, 119, 1, 9, 119, 123, 2, 10, 123, 127, 1, 127, 5, 131, 2, 6, 131, 135, 1, 135, 5, 139, 1, 9, 139, 143, 2, 143, 13, 147, 1, 9, 147, 151, 1, 151, 2, 155, 1, 9, 155, 0, 99, 2, 0, 14, 0
    };

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 02: Examples for puzzle 1 ###");
        try {
            Memory memory = new Memory(new long[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50});
            interpretIntcode(memory, new long[0]);
            System.out.println("This should yield '3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50': " + Arrays.toString(memory.getRaw()));

            memory = new Memory(new long[]{1, 0, 0, 0, 99});
            interpretIntcode(memory, new long[0]);
            System.out.println("This should yield '2, 0, 0, 0, 99': " + Arrays.toString(memory.getRaw()));

            memory = new Memory(new long[]{2, 3, 0, 3, 99});
            interpretIntcode(memory, new long[0]);
            System.out.println("This should yield '2, 3, 0, 6, 99': " + Arrays.toString(memory.getRaw()));

            memory = new Memory(new long[]{2, 4, 4, 5, 99, 0});
            interpretIntcode(memory, new long[0]);
            System.out.println("This should yield '2, 4, 4, 5, 99, 9801': " + Arrays.toString(memory.getRaw()));

            memory = new Memory(new long[]{1, 1, 1, 4, 99, 5, 6, 0, 99});
            interpretIntcode(memory, new long[0]);
            System.out.println("This should yield '30, 1, 1, 4, 2, 5, 6, 0, 99': " + Arrays.toString(memory.getRaw()));
        } catch (InterruptedException e) {
            System.err.println("InterruptedException caught: " + e);
        }
    }

    public static long doPuzzle1() throws InterruptedException {
        Memory memory = new Memory(MEMORY);
        memory.setRaw(1, 12);
        memory.setRaw(2, 2);
        interpretIntcode(memory, new long[0]);
        return memory.getRaw(0);
    }

    public static long doPuzzle2() throws InterruptedException {
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                Memory memory = new Memory(MEMORY);
                memory.setRaw(1, noun);
                memory.setRaw(2, verb);
                interpretIntcode(memory, new long[0]);
                if (memory.getRaw(0) == 19690720) {
                    return 100 * noun + verb;
                }
            }
        }

        throw new IllegalStateException("Could not find noun and verb that yield correct output.");
    }
}
