package com.github.rviehmann.aoc2019;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.abs;

public class Day03 {

    // From: https://adventofcode.com/2019/day/3/input
    private static final String WIRE1 = "R992,U284,L447,D597,R888,D327,R949,U520,R27,U555,L144,D284,R538,U249,R323,U297,R136,U838,L704,D621,R488,U856,R301,U539,L701,U363,R611,D94,L734,D560,L414,U890,R236,D699,L384,D452,R702,D637,L164,U410,R649,U901,L910,D595,R339,D346,R959,U777,R218,D667,R534,D762,R484,D914,L25,U959,R984,D922,R612,U999,L169,D599,L604,D357,L217,D327,L730,D949,L565,D332,L114,D512,R460,D495,L187,D697,R313,U319,L8,D915,L518,D513,R738,U9,R137,U542,L188,U440,R576,D307,R734,U58,R285,D401,R166,U156,L859,U132,L10,U753,L933,U915,R459,D50,R231,D166,L253,U844,R585,D871,L799,U53,R785,U336,R622,D108,R555,D918,L217,D668,L220,U738,L997,D998,R964,D456,L54,U930,R985,D244,L613,D116,L994,D20,R949,D245,L704,D564,L210,D13,R998,U951,L482,U579,L793,U680,L285,U770,L975,D54,R79,U613,L907,U467,L256,D783,R883,U810,R409,D508,L898,D286,L40,U741,L759,D549,R210,U411,R638,D643,L784,U538,L739,U771,L773,U491,L303,D425,L891,U182,R412,U951,L381,U501,R482,D625,R870,D320,L464,U555,R566,D781,L540,D754,L211,U73,L321,D869,R994,D177,R496,U383,R911,U819,L651,D774,L591,U666,L883,U767,R232,U822,L499,U44,L45,U873,L98,D487,L47,U803,R855,U256,R567,D88,R138,D678,L37,U38,R783,U569,L646,D261,L597,U275,L527,U48,R433,D324,L631,D160,L145,D128,R894,U223,R664,U510,R756,D700,R297,D361,R837,U996,L769,U813,L477,U420,L172,U482,R891,D379,L329,U55,R284,U155,L816,U659,L671,U996,R997,U252,R514,D718,L661,D625,R910,D960,L39,U610,R853,U859,R174,U215,L603,U745,L587,D736,R365,U78,R306,U158,L813,U885,R558,U631,L110,D232,L519,D366,R909,D10,R294";
    private static final String WIRE2 = "L1001,D833,L855,D123,R36,U295,L319,D700,L164,U576,L68,D757,R192,D738,L640,D660,R940,D778,R888,U772,R771,U900,L188,D464,L572,U184,R889,D991,L961,U751,R560,D490,L887,D748,R37,U910,L424,D401,L385,U415,L929,U193,R710,D855,L596,D323,L966,D505,L422,D139,L108,D135,R737,U176,R538,D173,R21,D951,R949,D61,L343,U704,R127,U468,L240,D834,L858,D127,R328,D863,R329,U477,R131,U864,R997,D38,R418,U611,R28,U705,R148,D414,R786,U264,L785,D650,R201,D250,R528,D910,R670,U309,L658,U190,R704,U21,R288,D7,R930,U62,R782,U621,R328,D725,R305,U700,R494,D137,R969,U142,L867,U577,R300,U162,L13,D698,R333,U865,R941,U796,L60,U902,L784,U832,R78,D578,R196,D390,R728,D922,R858,D994,L457,U547,R238,D345,R329,D498,R873,D212,R501,U474,L657,U910,L335,U133,R213,U417,R698,U829,L2,U704,L273,D83,R231,D247,R675,D23,L692,D472,L325,D659,L408,U746,L715,U395,L596,U296,R52,D849,L713,U815,R684,D551,L319,U768,R176,D182,R557,U731,R314,D543,L9,D256,R38,D809,L567,D332,R375,D572,R81,D479,L71,U968,L831,D247,R989,U390,R463,D576,R740,D539,R488,U367,L596,U375,L763,D824,R70,U448,R979,D977,L744,D379,R488,D671,L516,D334,L542,U517,L488,D390,L713,D932,L28,U924,L448,D229,L488,D501,R19,D910,L979,D411,R711,D824,L973,U291,R794,D485,R208,U370,R655,U450,L40,D804,L374,D671,R962,D829,L209,U111,L84,D876,L832,D747,L733,D560,L702,D972,R188,U817,L111,U26,L492,U485,L71,D59,L269,D870,L152,U539,R65,D918,L932,D260,L485,U77,L699,U254,R924,U643,L264,U96,R395,D917,R360,U354,R101,D682,R854,U450,L376,D378,R872,D311,L881,U630,R77,D766,R672";

    public static class Position {
        public long x;
        public long y;
        public long stepsFromStart;

        public Position(long x, long y, long stepsFromStart) {
            this.x = x;
            this.y = y;
            this.stepsFromStart = stepsFromStart;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !obj.getClass().equals(this.getClass())) {
                return false;
            }

            Position other = (Position) obj;
            // Yes, it is intentional that we only compare the coordinates, but not the steps. Otherwise, minimizeTrace() would not work at all.
            return this.x == other.x && this.y == other.y;
        }

        public boolean isInCentralPosition() {
            return x == 0 && y == 0;
        }
    }

    public static class Intersection extends Position {
        public Intersection(Position pos1, Position pos2) {
            super(pos1.x, pos1.y, pos1.stepsFromStart + pos2.stepsFromStart);
        }
    }

    private static List<Position> traceWire(String wirePath) {
        long x = 0;
        long y = 0;
        long stepsFromStart = 0;
        String[] commands = wirePath.split(",");
        List<Position> positions = new ArrayList<>();

        for (String command : commands) {
            command = command.trim().toUpperCase();
            String direction = command.substring(0, 1);
            long distance = Long.parseLong(command.substring(1));

            switch (direction) {
                case "U":
                    for (long i = 0; i < distance; i++) {
                        y++;
                        stepsFromStart++;
                        positions.add(new Position(x, y, stepsFromStart));
                    }
                    break;

                case "D":
                    for (long i = 0; i < distance; i++) {
                        y--;
                        stepsFromStart++;
                        positions.add(new Position(x, y, stepsFromStart));
                    }
                    break;

                case "L":
                    for (long i = 0; i < distance; i++) {
                        x--;
                        stepsFromStart++;
                        positions.add(new Position(x, y, stepsFromStart));
                    }
                    break;

                case "R":
                    for (long i = 0; i < distance; i++) {
                        x++;
                        stepsFromStart++;
                        positions.add(new Position(x, y, stepsFromStart));
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Invalid direction: command=" + command);
            }
        }

        return positions;
    }

    private static List<Position> minimizeTrace(List<Position> trace) {
        List<Position> positions = new ArrayList<>();
        for (Position pos : trace) {
            if (!positions.contains(pos)) {
                positions.add(pos);
            }
        }
        return positions;
    }

    private static List<Intersection> findIntersections(List<Position> trace1, List<Position> trace2) {
        List<Intersection> intersections = new ArrayList<>();
        for (Position pos1 : trace1) {
            for (Position pos2 : trace2) {
                if (pos1.equals(pos2) && !pos1.isInCentralPosition()) {
                    intersections.add(new Intersection(pos1, pos2));
                }
            }
        }
        return intersections;
    }

    private static long manhattanDistance(Intersection pos) {
        return abs(pos.x) + abs(pos.y);
    }

    private static long signalDelay(Intersection pos) {
        return pos.stepsFromStart;
    }

    private static long findIntersectionWithLowestDistance(List<Intersection> intersections) {
        return intersections.stream()
                            .map(Day03::manhattanDistance)
                            .min(Comparator.comparing(Long::valueOf))
                            .orElseThrow(() -> new IllegalStateException("Could not find intersection with minimum distance."));
    }

    private static long findIntersectionWithLowestDelay(List<Intersection> intersections) {
        return intersections.stream()
                            .map(Day03::signalDelay)
                            .min(Comparator.comparing(Long::valueOf))
                            .orElseThrow(() -> new IllegalStateException("Could not find intersection with minimum delay."));
    }

    private static long findBestIntersection(String wire1, String wire2, Function<List<Intersection>, Long> finderFunction) {
        List<Position> trace1 = traceWire(wire1);
        System.out.println("Size of trace1: " + trace1.size());
        List<Position> trace1min = minimizeTrace(trace1);
        System.out.println("Size of trace1min: " + trace1min.size());

        List<Position> trace2 = traceWire(wire2);
        System.out.println("Size of trace2: " + trace2.size());
        List<Position> trace2min = minimizeTrace(trace2);
        System.out.println("Size of trace2min: " + trace2min.size());

        List<Intersection> intersections = findIntersections(trace1min, trace2min);
        System.out.println("Intersections: " + intersections.size());

        return finderFunction.apply(intersections);
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 03: Examples for puzzle 1 ###");
        System.out.println("This should yield 6: " + findBestIntersection("R8,U5,L5,D3", "U7,R6,D4,L4", Day03::findIntersectionWithLowestDistance));
        System.out.println("This should yield 159: " + findBestIntersection("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", Day03::findIntersectionWithLowestDistance));
        System.out.println("This should yield 135: " + findBestIntersection("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", Day03::findIntersectionWithLowestDistance));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 03: Examples for puzzle 2 ###");
        System.out.println("This should yield 30: " + findBestIntersection("R8,U5,L5,D3", "U7,R6,D4,L4", Day03::findIntersectionWithLowestDelay));
        System.out.println("This should yield 610: " + findBestIntersection("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83", Day03::findIntersectionWithLowestDelay));
        System.out.println("This should yield 410: " + findBestIntersection("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", Day03::findIntersectionWithLowestDelay));
    }

    public static long doPuzzle1() {
        return findBestIntersection(WIRE1, WIRE2, Day03::findIntersectionWithLowestDistance);
    }

    public static long doPuzzle2() {
        return findBestIntersection(WIRE1, WIRE2, Day03::findIntersectionWithLowestDelay);
    }
}
