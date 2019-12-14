package com.github.rviehmann.aoc2019;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
    private static final int[] EXAMPLE4 = {
            3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
            27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5
    };
    private static final int[] EXAMPLE5 = {
            3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54,
            -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4,
            53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10
    };

    private static final int NUM_AMPLIFIERS = 5;
    private static final int MIN_PHASE_PUZZLE1 = 0;
    private static final int MAX_PHASE_PUZZLE1 = 4;
    private static final int MIN_PHASE_PUZZLE2 = 5;
    private static final int MAX_PHASE_PUZZLE2 = 9;

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

    public static class Amplifier implements Runnable {

        private final String name;
        private final int[] memory;
        private final int phase;
        private final BlockingQueue<Integer> inputQueue;
        private final BlockingQueue<Integer> outputQueue;

        public Amplifier(String name, int[] memory, int phase, BlockingQueue<Integer> inputQueue, BlockingQueue<Integer> outputQueue) {
            this.name = name;
            int[] copiedMemory = new int[memory.length];
            System.arraycopy(memory, 0, copiedMemory, 0, memory.length);
            this.memory = copiedMemory;
            this.phase = phase;
            this.inputQueue = inputQueue;
            this.outputQueue = outputQueue;
        }

        @Override
        public void run() {
            try {
                // System.out.println("Starting amplifier '" + name + "'.");
                interpretIntcode(memory, inputQueue, outputQueue);
                // System.out.println("Amplifier '" + name + "' finished execution.");
            } catch (InterruptedException e) {
                System.err.println("InterruptedException caught: " + e);
            }
        }
    }

    private static int runAmplifiersInThreads(int[] memory, int[] phases) throws InterruptedException {
        BlockingQueue<Integer>[] queues = new BlockingQueue[NUM_AMPLIFIERS];
        Amplifier[] amplifiers = new Amplifier[NUM_AMPLIFIERS];
        Thread[] threads = new Thread[NUM_AMPLIFIERS];

        for (int i = 0; i < NUM_AMPLIFIERS; i++) {
            queues[i] = new LinkedBlockingQueue<>();
            queues[i].put(phases[i]);
        }

        for (int i = 0; i < NUM_AMPLIFIERS; i++) {
            String name = "Amp " + (char) ('A' + i);
            amplifiers[i] = new Amplifier(name, memory, phases[i], queues[i], queues[(i + 1) % NUM_AMPLIFIERS]);
            threads[i] = new Thread(amplifiers[i]);
        }

        for (int i = 0; i < NUM_AMPLIFIERS; i++) {
            threads[i].start();
        }

        queues[0].put(0);

        for (int i = 0; i < NUM_AMPLIFIERS; i++) {
            // Wait until all amplifiers are finished.
            threads[i].join();
        }

        // The last amplifier should have written a result that has not been consumed, so we can take it here.
        return queues[0].take();
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 07: Examples for puzzle 1 ###");
        System.out.println("This should yield 43210: " + runAmplifiers(EXAMPLE1, new int[]{4, 3, 2, 1, 0}));
        System.out.println("This should yield 54321: " + runAmplifiers(EXAMPLE2, new int[]{0, 1, 2, 3, 4}));
        System.out.println("This should yield 65210: " + runAmplifiers(EXAMPLE3, new int[]{1, 0, 4, 3, 2}));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 07: Examples for puzzle 2 ###");
        try {
            System.out.println("This should yield 139629729: " + runAmplifiersInThreads(EXAMPLE4, new int[]{9, 8, 7, 6, 5}));
        } catch (InterruptedException e) {
            System.err.println("InterruptedException caught: " + e);
        }
        try {
            System.out.println("This should yield 18216: " + runAmplifiersInThreads(EXAMPLE5, new int[]{9, 7, 8, 5, 6}));
        } catch (InterruptedException e) {
            System.err.println("InterruptedException caught: " + e);
        }
    }

    public static int doPuzzle1() {
        int bestOutput = Integer.MIN_VALUE;
        for (int a = MIN_PHASE_PUZZLE1; a <= MAX_PHASE_PUZZLE1; a++) {
            for (int b = MIN_PHASE_PUZZLE1; b <= MAX_PHASE_PUZZLE1; b++) {
                for (int c = MIN_PHASE_PUZZLE1; c <= MAX_PHASE_PUZZLE1; c++) {
                    for (int d = MIN_PHASE_PUZZLE1; d <= MAX_PHASE_PUZZLE1; d++) {
                        for (int e = MIN_PHASE_PUZZLE1; e <= MAX_PHASE_PUZZLE1; e++) {
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

    public static int doPuzzle2() throws InterruptedException {
        int bestOutput = Integer.MIN_VALUE;
        for (int a = MIN_PHASE_PUZZLE2; a <= MAX_PHASE_PUZZLE2; a++) {
            for (int b = MIN_PHASE_PUZZLE2; b <= MAX_PHASE_PUZZLE2; b++) {
                for (int c = MIN_PHASE_PUZZLE2; c <= MAX_PHASE_PUZZLE2; c++) {
                    for (int d = MIN_PHASE_PUZZLE2; d <= MAX_PHASE_PUZZLE2; d++) {
                        for (int e = MIN_PHASE_PUZZLE2; e <= MAX_PHASE_PUZZLE2; e++) {
                            int[] phases = new int[]{a, b, c, d, e};
                            if (hasOnlyUniquePhaseSettings(phases)) {
                                int output = runAmplifiersInThreads(MEMORY, phases);
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
