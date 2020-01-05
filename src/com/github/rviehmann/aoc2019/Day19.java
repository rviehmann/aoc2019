package com.github.rviehmann.aoc2019;

import com.github.rviehmann.aoc2019.Intcode.Memory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.github.rviehmann.aoc2019.Intcode.interpretIntcode;

public class Day19 {

    // From: https://adventofcode.com/2019/day/19/input
    private static final long[] MEMORY = {
            109, 424, 203, 1, 21101, 0, 11, 0, 1106, 0, 282, 21102, 18, 1, 0, 1106, 0, 259, 2101, 0, 1, 221, 203, 1, 21102, 1, 31, 0, 1105, 1, 282, 21101, 0, 38, 0, 1106, 0, 259, 20102, 1, 23, 2, 22101, 0, 1, 3, 21102, 1, 1, 1, 21101, 57, 0, 0, 1105, 1, 303, 2102, 1, 1, 222, 20101, 0, 221, 3, 21002, 221, 1, 2, 21101, 0, 259, 1, 21102, 1, 80, 0, 1105, 1, 225, 21102, 125, 1, 2, 21102, 1, 91, 0, 1106, 0, 303, 2101, 0, 1, 223, 21002, 222, 1, 4, 21102, 1, 259, 3, 21102, 225, 1, 2, 21102, 225, 1, 1, 21101, 0, 118, 0, 1106, 0, 225, 20102, 1, 222, 3, 21101, 0, 69, 2, 21102, 1, 133, 0, 1106, 0, 303, 21202, 1, -1, 1, 22001, 223, 1, 1, 21102, 148, 1, 0, 1106, 0, 259, 1201, 1, 0, 223, 20101, 0, 221, 4, 21001, 222, 0, 3, 21102, 1, 22, 2, 1001, 132, -2, 224, 1002, 224, 2, 224, 1001, 224, 3, 224, 1002, 132, -1, 132, 1, 224, 132, 224, 21001, 224, 1, 1, 21102, 195, 1, 0, 106, 0, 108, 20207, 1, 223, 2, 20101, 0, 23, 1, 21102, -1, 1, 3, 21101, 0, 214, 0, 1105, 1, 303, 22101, 1, 1, 1, 204, 1, 99, 0, 0, 0, 0, 109, 5, 1202, -4, 1, 249, 21202, -3, 1, 1, 22102, 1, -2, 2, 21201, -1, 0, 3, 21101, 250, 0, 0, 1106, 0, 225, 22102, 1, 1, -4, 109, -5, 2105, 1, 0, 109, 3, 22107, 0, -2, -1, 21202, -1, 2, -1, 21201, -1, -1, -1, 22202, -1, -2, -2, 109, -3, 2106, 0, 0, 109, 3, 21207, -2, 0, -1, 1206, -1, 294, 104, 0, 99, 22101, 0, -2, -2, 109, -3, 2106, 0, 0, 109, 5, 22207, -3, -4, -1, 1206, -1, 346, 22201, -4, -3, -4, 21202, -3, -1, -1, 22201, -4, -1, 2, 21202, 2, -1, -1, 22201, -4, -1, 1, 22102, 1, -2, 3, 21101, 0, 343, 0, 1106, 0, 303, 1105, 1, 415, 22207, -2, -3, -1, 1206, -1, 387, 22201, -3, -2, -3, 21202, -2, -1, -1, 22201, -3, -1, 3, 21202, 3, -1, -1, 22201, -3, -1, 2, 22102, 1, -4, 1, 21101, 384, 0, 0, 1106, 0, 303, 1106, 0, 415, 21202, -4, -1, -4, 22201, -4, -3, -4, 22202, -3, -2, -2, 22202, -2, -4, -4, 22202, -3, -2, -3, 21202, -4, -1, -2, 22201, -3, -2, 1, 21202, 1, 1, -4, 109, -5, 2105, 1, 0
    };

    private static final Character CHAR_BEAM = '#';
    private static final Character CHAR_NOBEAM = '.';

    public static long doPuzzle1() throws InterruptedException {
        int affected = 0;
        for (long y = 0; y < 50; y++) {
            for (long x = 0; x < 50; x++) {
                Memory memory = new Memory(MEMORY);
                BlockingQueue<Long>[] queues = new BlockingQueue[2];
                for (int i = 0; i < 2; i++) {
                    queues[i] = new LinkedBlockingQueue<>();
                }
                queues[0].put(x);
                queues[0].put(y);

                try {
                    interpretIntcode(memory, queues[0], queues[1], null);
                } catch (InterruptedException e) {
                    System.err.println("InterruptedException caught: " + e);
                }

                long beam = queues[1].take();
                switch ((int) beam) {
                    case 0:
                        System.out.print(CHAR_NOBEAM);
                        break;
                    case 1:
                        System.out.print(CHAR_BEAM);
                        affected++;
                        break;
                    default:
                        throw new IllegalArgumentException("Value does not make sense for beam: " + beam);
                }
            }
            System.out.print("\n");
        }
        return affected;
    }
}
