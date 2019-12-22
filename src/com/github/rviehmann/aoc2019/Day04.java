package com.github.rviehmann.aoc2019;

public class Day04 {

    // From: https://adventofcode.com/2019/day/4
    private static final long LOWER_BOUND = 307237;
    private static final long UPPER_BOUND = 769058;

    private static boolean doesMeetAllCriteriaForPart1(long number) {
        /*
        - It is a six-digit number.
        - The value is within the range given in your puzzle input.
        - Two adjacent digits are the same (like 22 in 122345).
        - Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
         */

        String asString = Long.toString(number);
        if (asString.length() != 6) {
            return false;
        }

        boolean hasAdjacent = false;
        for (int i = 0; i < (asString.length() - 1); i++) {
            int thisDigit = Integer.parseInt(asString.substring(i, i + 1));
            int nextDigit = Integer.parseInt(asString.substring(i + 1, i + 2));
            if (thisDigit == nextDigit) {
                hasAdjacent = true;
                break;
            }
        }
        if (!hasAdjacent) {
            // System.out.println("Does not have at least two adjacent identical digits for: " + number);
            return false;
        }

        for (int i = 0; i < (asString.length() - 1); i++) {
            int thisDigit = Integer.parseInt(asString.substring(i, i + 1));
            int nextDigit = Integer.parseInt(asString.substring(i + 1, i + 2));
            if (nextDigit < thisDigit) {
                // System.out.println("Does have decreasing digits for: " + number + ", thisDigit: " + thisDigit + ", nextDigit: " + nextDigit);
                return false;
            }
        }

        return true;
    }

    private static boolean doesMeetAllCriteriaForPart2(long number) {
        if (!doesMeetAllCriteriaForPart1(number)) {
            return false;
        }

        char lastChar = 0;
        int currentLength = 0;

        for (char c : Long.toString(number).toCharArray()) {
            if (c == lastChar) {
                currentLength++;
            } else {
                if (currentLength == 2) {
                    return true;
                }
                lastChar = c;
                currentLength = 1;
            }
        }

        return currentLength == 2;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 04: Examples for puzzle 1 ###");
        System.out.println("This should yield true: " + doesMeetAllCriteriaForPart1(111111));
        System.out.println("This should yield false: " + doesMeetAllCriteriaForPart1(223450));
        System.out.println("This should yield false: " + doesMeetAllCriteriaForPart1(123789));
        System.out.println("This should yield true: " + doesMeetAllCriteriaForPart1(111122));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 04: Examples for puzzle 2 ###");
        System.out.println("This should yield true: " + doesMeetAllCriteriaForPart2(112233));
        System.out.println("This should yield false: " + doesMeetAllCriteriaForPart2(123444));
        System.out.println("This should yield true: " + doesMeetAllCriteriaForPart2(111122));
    }

    public static long doPuzzle1() {
        int count = 0;
        for (long i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            if (doesMeetAllCriteriaForPart1(i)) {
                count++;
            }
        }
        return count;
    }

    public static long doPuzzle2() {
        int count = 0;
        for (long i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            if (doesMeetAllCriteriaForPart2(i)) {
                count++;
            }
        }
        return count;
    }
}
