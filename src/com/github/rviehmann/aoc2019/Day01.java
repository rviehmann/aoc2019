package com.github.rviehmann.aoc2019;

import java.util.Arrays;

public class Day01 {

    // From: https://adventofcode.com/2019/day/1/input
    private static final long[] INPUT = {
            141107,
            119016,
            145241,
            72264,
            116665,
            81420,
            88513,
            128809,
            145471,
            81570,
            124798,
            75370,
            84988,
            71634,
            135275,
            96992,
            53376,
            62414,
            148277,
            135418,
            82475,
            137707,
            105051,
            83450,
            102673,
            88390,
            100849,
            94528,
            135709,
            63945,
            126413,
            70107,
            84734,
            119176,
            85769,
            115276,
            137511,
            61806,
            92892,
            121640,
            93726,
            146526,
            95812,
            132556,
            103885,
            78776,
            55826,
            120257,
            61131,
            79179,
            130698,
            97153,
            121985,
            61159,
            103585,
            148674,
            84067,
            110085,
            138473,
            105495,
            112393,
            144411,
            73328,
            125955,
            58075,
            136147,
            124106,
            81185,
            138847,
            69814,
            127104,
            86090,
            67666,
            102333,
            99546,
            98280,
            99062,
            129433,
            125353,
            77609,
            71240,
            71791,
            146046,
            113685,
            121381,
            122715,
            147789,
            53981,
            140926,
            81528,
            121789,
            106627,
            73745,
            67509,
            144140,
            119238,
            82417,
            129215,
            75663,
            106842
    };

    private static long getFuelForModule(long massOfModule) {
        return (massOfModule / 3) - 2;
    }

    private static long getFuelForModuleIncludingOwnMass(long massOfModule) {
        long fuel = getFuelForModule(massOfModule);

        if (fuel <= 0) {
            return 0;
        }

        return fuel + getFuelForModuleIncludingOwnMass(fuel);
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 01: Examples for puzzle 1 ###");
        System.out.println("This should yield 2: " + getFuelForModule(12));
        System.out.println("This should yield 2: " + getFuelForModule(14));
        System.out.println("This should yield 654: " + getFuelForModule(1969));
        System.out.println("This should yield 33583: " + getFuelForModule(100756));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 01: Examples for puzzle 2 ###");
        System.out.println("This should yield 2: " + getFuelForModuleIncludingOwnMass(14));
        System.out.println("This should yield 966: " + getFuelForModuleIncludingOwnMass(1969));
        System.out.println("This should yield 50346: " + getFuelForModuleIncludingOwnMass(100756));
    }

    public static long doPuzzle1() {
        return Arrays.stream(INPUT)
                     .map(Day01::getFuelForModule)
                     .reduce(0, Long::sum);
    }

    public static long doPuzzle2() {
        return Arrays.stream(INPUT)
                     .map(Day01::getFuelForModuleIncludingOwnMass)
                     .reduce(0, Long::sum);
    }
}
