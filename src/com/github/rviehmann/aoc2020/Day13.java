package com.github.rviehmann.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    private static final long EXAMPLE_DEPART = 939;
    private static final String EXAMPLE_INPUT =
            "7,13,x,x,59,x,31,19";

    // From: https://adventofcode.com/2020/day/13/input
    private static final long DEPART = 1000507;
    private static final String INPUT =
            "29,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,631,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,13,19,x,x,x,23,x,x,x,x,x,x,x,383,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,17";

    private static long calculateBestTime(String input, long depart) {
        String[] busses = input.split(",");
        List<Long> busIds = new ArrayList<>();
        for (String bus : busses) {
            if (!bus.equalsIgnoreCase("x")) {
                Long busId = Long.parseLong(bus);
                busIds.add(busId);
            }
        }
        long minWait = Long.MAX_VALUE;
        long bestBusId = -1;
        for (Long busId : busIds) {
            long waitingMinutes = busId - (depart % busId);
            if (waitingMinutes < minWait) {
                minWait = waitingMinutes;
                bestBusId = busId;
            }
            System.out.println("BusId: " + busId + " waiting time in minutes: " + waitingMinutes);
        }
        System.out.println("Best busId: " + bestBusId + " waiting time in minutes: " + minWait);
        return bestBusId * minWait;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 13: Examples for puzzle 1 ###");
        System.out.println("Result for example: " + calculateBestTime(EXAMPLE_INPUT, EXAMPLE_DEPART));
    }

    public static long doPuzzle1() {
        return calculateBestTime(INPUT, DEPART);
    }
}
