package com.github.rviehmann.aoc2019;

import static com.github.rviehmann.aoc2019.Intcode.interpretIntcode;

public class Day07 {

    // From: https://adventofcode.com/2019/day/7/input
    private static final int[] MEMORY = {
            3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 38, 63, 80, 105, 118, 199, 280, 361, 442, 99999, 3, 9, 102, 5, 9, 9, 1001, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1001, 9, 4, 9, 102, 4, 9, 9, 101, 4, 9, 9, 102, 2, 9, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 5, 9, 102, 4, 9, 9, 1001, 9, 4, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 1002, 9, 5, 9, 101, 3, 9, 9, 102, 5, 9, 9, 101, 3, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 1001, 9, 4, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99
    };
    private static final int[] EXAMPLE1 = {
            3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0
    };
    private static final int[] EXAMPLE2 = {
            3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23,
            101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0
    };
    private static final int[] EXAMPLE3 = {
            3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
            1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0
    };

    private static final int NUM_AMPLIFIERS = 5;
    private static final int MIN_PHASE = 0;
    private static final int MAX_PHASE = 4;

    private static int runAmplifier(int[] memory, int phase, int input) {
        int[] copiedMemory = new int[memory.length];
        System.arraycopy(memory, 0, copiedMemory, 0, memory.length);
        Integer[] outputs = interpretIntcode(copiedMemory, new int[]{phase, input});
        return outputs[0];
    }

    private static int runAmplifiers(int[] memory, int[] phases) {
        int output = 0;
        for (int i = 0; i < NUM_AMPLIFIERS; i++) {
            output = runAmplifier(memory, phases[i], output);
        }
        return output;
    }

    private static boolean hasOnlyUniquePhaseSettings(int[] phases) {
        for (int i = 0; i < phases.length; i++) {
            for (int j = 0; j < phases.length; j++) {
                if (i != j && phases[i] == phases[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 07: Examples for puzzle 1 ###");
        System.out.println("This should yield 43210: " + runAmplifiers(EXAMPLE1, new int[]{4, 3, 2, 1, 0}));
        System.out.println("This should yield 54321: " + runAmplifiers(EXAMPLE2, new int[]{0, 1, 2, 3, 4}));
        System.out.println("This should yield 65210: " + runAmplifiers(EXAMPLE3, new int[]{1, 0, 4, 3, 2}));
    }

    public static int doPuzzle1() {
        int bestOutput = Integer.MIN_VALUE;
        for (int a = MIN_PHASE; a <= MAX_PHASE; a++) {
            for (int b = MIN_PHASE; b <= MAX_PHASE; b++) {
                for (int c = MIN_PHASE; c <= MAX_PHASE; c++) {
                    for (int d = MIN_PHASE; d <= MAX_PHASE; d++) {
                        for (int e = MIN_PHASE; e <= MAX_PHASE; e++) {
                            int[] phases = new int[]{a, b, c, d, e};
                            if (hasOnlyUniquePhaseSettings(phases)) {
                                int output = runAmplifiers(MEMORY, phases);
                                if (output > bestOutput) {
                                    bestOutput = output;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bestOutput;
    }
}
