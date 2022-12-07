package com.github.rviehmann.aoc2020;

import java.util.Arrays;

public class Day10 {

    private static final String EXAMPLE1 =
            "16\n" +
                    "10\n" +
                    "15\n" +
                    "5\n" +
                    "1\n" +
                    "11\n" +
                    "7\n" +
                    "19\n" +
                    "6\n" +
                    "12\n" +
                    "4";

    private static final String EXAMPLE2 =
            "28\n" +
                    "33\n" +
                    "18\n" +
                    "42\n" +
                    "31\n" +
                    "14\n" +
                    "46\n" +
                    "20\n" +
                    "48\n" +
                    "47\n" +
                    "24\n" +
                    "23\n" +
                    "49\n" +
                    "45\n" +
                    "19\n" +
                    "38\n" +
                    "39\n" +
                    "11\n" +
                    "1\n" +
                    "32\n" +
                    "25\n" +
                    "35\n" +
                    "8\n" +
                    "17\n" +
                    "7\n" +
                    "9\n" +
                    "4\n" +
                    "2\n" +
                    "34\n" +
                    "10\n" +
                    "3";

    // From: https://adventofcode.com/2020/day/10/input
    private static final String INPUT =
            "153\n" +
                    "17\n" +
                    "45\n" +
                    "57\n" +
                    "16\n" +
                    "147\n" +
                    "39\n" +
                    "121\n" +
                    "75\n" +
                    "70\n" +
                    "85\n" +
                    "134\n" +
                    "128\n" +
                    "115\n" +
                    "51\n" +
                    "139\n" +
                    "44\n" +
                    "65\n" +
                    "119\n" +
                    "168\n" +
                    "122\n" +
                    "72\n" +
                    "105\n" +
                    "31\n" +
                    "103\n" +
                    "89\n" +
                    "154\n" +
                    "114\n" +
                    "55\n" +
                    "25\n" +
                    "48\n" +
                    "38\n" +
                    "132\n" +
                    "157\n" +
                    "84\n" +
                    "71\n" +
                    "113\n" +
                    "143\n" +
                    "83\n" +
                    "64\n" +
                    "109\n" +
                    "129\n" +
                    "120\n" +
                    "100\n" +
                    "151\n" +
                    "79\n" +
                    "125\n" +
                    "22\n" +
                    "161\n" +
                    "167\n" +
                    "19\n" +
                    "26\n" +
                    "118\n" +
                    "142\n" +
                    "4\n" +
                    "158\n" +
                    "11\n" +
                    "35\n" +
                    "56\n" +
                    "18\n" +
                    "40\n" +
                    "7\n" +
                    "150\n" +
                    "99\n" +
                    "54\n" +
                    "152\n" +
                    "60\n" +
                    "27\n" +
                    "164\n" +
                    "78\n" +
                    "47\n" +
                    "82\n" +
                    "63\n" +
                    "46\n" +
                    "91\n" +
                    "32\n" +
                    "135\n" +
                    "3\n" +
                    "108\n" +
                    "10\n" +
                    "159\n" +
                    "127\n" +
                    "69\n" +
                    "110\n" +
                    "126\n" +
                    "133\n" +
                    "28\n" +
                    "15\n" +
                    "104\n" +
                    "138\n" +
                    "160\n" +
                    "98\n" +
                    "90\n" +
                    "144\n" +
                    "1\n" +
                    "2\n" +
                    "92\n" +
                    "41\n" +
                    "86\n" +
                    "66\n" +
                    "95\n" +
                    "12";

    private static final String[] EXAMPLE1_AS_ARRAY = EXAMPLE1.split("\\R");
    private static final String[] EXAMPLE2_AS_ARRAY = EXAMPLE2.split("\\R");
    private static final String[] INPUT_AS_ARRAY = INPUT.split("\\R");

    private static long calculateDifferences(String[] input, boolean returnNumCombinations) {
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

        long[] numbers = new long[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Long.parseLong(input[i]);
        }
        Arrays.sort(numbers);

        long count1 = 0;
        long count3 = 0;
        long lengthOf1Series = 0;
        long numCombinations = 1;

        for (int i = 0; i <= input.length; i++) {
            long me;
            long next;

            if (i == 0) {
                me = 0;
            } else {
                me = numbers[i - 1];
            }

            if (i == input.length) {
                next = numbers[i - 1] + 3;
            } else {
                next = numbers[i];
            }

            long diff = next - me;
            switch ((int) diff) {
                case 1:
                    count1++;
                    lengthOf1Series++;
                    break;
                case 3:
                    count3++;
                    if (lengthOf1Series > 0) {
                        // System.out.println("LengthOf1Series: " + lengthOf1Series);
                        long value;
                        switch ((int) lengthOf1Series) {
                            case 1:
                                value = 1;
                                break;
                            case 2:
                                value = 2;
                                break;
                            case 3:
                                value = 4;
                                break;
                            case 4:
                                value = 7;
                                break;
                            default:
                                throw new IllegalStateException("LengthOf1Series not supported: " + lengthOf1Series);
                        }
                        numCombinations *= value;
                    }
                    lengthOf1Series = 0;
                    break;
                default:
                    throw new IllegalStateException("Difference other than one and three detected: " + diff);
            }
        }

        System.out.println("Diff 1: " + count1);
        System.out.println("Diff 3: " + count3);
        System.out.println("NumCombinations: " + numCombinations);
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

        return returnNumCombinations ? numCombinations : count1 * count3;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 10: Examples for puzzle 1 ###");
        calculateDifferences(EXAMPLE1_AS_ARRAY, false);
        calculateDifferences(EXAMPLE2_AS_ARRAY, false);
    }

    public static long doPuzzle1() {
        return calculateDifferences(INPUT_AS_ARRAY, false);
    }

    public static long doPuzzle2() {
        return calculateDifferences(INPUT_AS_ARRAY, true);
    }
}
