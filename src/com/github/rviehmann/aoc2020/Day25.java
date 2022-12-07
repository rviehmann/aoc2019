package com.github.rviehmann.aoc2020;

public class Day25 {

    /**
     * The subject is given in the assignment for this day, and seems to be the same for everyone.
     */
    private static final long SUBJECT = 7;

    /**
     * The divider is given in the assignment for this day, and seems to be the same for everyone.
     */
    private static final long DIVIDER = 20201227;

    private static long getLoopSize(long publicKey) {
        long loop = 0;
        long value = 1;
        while (true) {
            value *= SUBJECT;
            value %= DIVIDER;
            loop++;
            if (value == publicKey) {
                return loop;
            }
        }
    }

    private static long determineEncryptionKey(long subject, long loopSize) {
        long value = 1;
        for (long l = 0; l < loopSize; l++) {
            value *= subject;
            value %= DIVIDER;
        }
        return value;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 25: Examples for puzzle 1 ###");
        System.out.println("This should be 8 (card): " + getLoopSize(5764801));
        System.out.println("This should be 11 (door): " + getLoopSize(17807724));
        System.out.println("This should be the key (14897079): " + determineEncryptionKey(17807724, 8));
        System.out.println("This should be the key (14897079): " + determineEncryptionKey(5764801, 11));
    }

    public static long doPuzzle1() {
        long loopCard = getLoopSize(1717001);
        long loopDoor = getLoopSize(523731);
        System.out.println("We will need this loop size (card): " + loopCard);
        System.out.println("We will need this loop size (door): " + loopDoor);
        System.out.println("This should be the key: " + determineEncryptionKey(17807724, loopCard));
        System.out.println("This should be the key: " + determineEncryptionKey(1717001, loopDoor));
        return determineEncryptionKey(1717001, loopDoor);
    }

    // There is no puzzle 2 for this day.
}
