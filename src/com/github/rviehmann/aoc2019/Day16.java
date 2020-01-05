package com.github.rviehmann.aoc2019;

import java.util.Arrays;

import static java.lang.Math.abs;

public class Day16 {

    private static final String EXAMPLE1 = "12345678";
    private static final String EXAMPLE2 = "80871224585914546619083218645595";
    private static final String EXAMPLE3 = "19617804207202209144916044189917";
    private static final String EXAMPLE4 = "69317163492948606335995924319873";

    // From: https://adventofcode.com/2019/day/16/input
    private static final String INPUT = "59728776137831964407973962002190906766322659303479564518502254685706025795824872901465838782474078135479504351754597318603898249365886373257507600323820091333924823533976723324070520961217627430323336204524247721593859226704485849491418129908885940064664115882392043975997862502832791753443475733972832341211432322108298512512553114533929906718683734211778737511609226184538973092804715035096933160826733751936056316586618837326144846607181591957802127283758478256860673616576061374687104534470102346796536051507583471850382678959394486801952841777641763547422116981527264877636892414006855332078225310912793451227305425976335026620670455240087933409";

    private static final int[] BASE_PATTERN = {0, 1, 0, -1};

    private static int[] toIntArray(String input) {
        int[] output = new int[input.length()];
        int index = 0;
        for (char c : input.toCharArray()) {
            output[index] = c - '0';
            index++;
        }
        return output;
    }

    private static int[] doRepeatedly(int[] input, int phases) {
        for (int phase = 0; phase < phases; phase++) {
            input = doOnePhase(input);
        }
        return input;
    }

    private static int[] doOnePhase(int[] input) {
        int[] output = new int[input.length];
        for (int position = 0; position < input.length; position++) {
            output[position] = generateOneOutputDigit(input, position);
        }
        return output;
    }

    private static int generateOneOutputDigit(int[] input, int outputPosition) {
        long buffer = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            int patternValue = getPatternValue(i, outputPosition);
            buffer += input[i] * patternValue;
            if (sb.length() > 0) {
                sb.append(" + ");
            }
            sb.append(input[i]).append("*").append(patternValue);
        }
        int result = (int) (abs(buffer) % 10);
        sb.append(" = ").append(result);
        // System.out.println(sb.toString());
        return result;
    }

    private static int getPatternValue(int pointer, int outputPosition) {
        // Position starts with 0, repetitions start with 1.
        int repetition = outputPosition + 1;
        int lengthOfRepeatedPattern = BASE_PATTERN.length * repetition;

        // We have to skip the very first position in the repeated pattern.
        pointer = (pointer + 1) % lengthOfRepeatedPattern;
        int positionInBasePattern = pointer / repetition;
        return BASE_PATTERN[positionInBasePattern];
    }

    private static long getFirst8AfterSomePhases(String input, int phases) {
        int[] array = doRepeatedly(toIntArray(input), phases);
        StringBuilder sb = new StringBuilder();
        for (int position = 0; position < 8; position++) {
            sb.append(array[position]);
        }
        return Long.parseLong(sb.toString());
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 16: Examples for puzzle 1 ###");
        System.out.println("This should yield '48226158': " + Arrays.toString(doRepeatedly(toIntArray(EXAMPLE1), 1)));
        System.out.println("This should yield '34040438': " + Arrays.toString(doRepeatedly(toIntArray(EXAMPLE1), 2)));
        System.out.println("This should yield '03415518': " + Arrays.toString(doRepeatedly(toIntArray(EXAMPLE1), 3)));
        System.out.println("This should yield '01029498': " + Arrays.toString(doRepeatedly(toIntArray(EXAMPLE1), 4)));
        System.out.println("This should yield '24176176': " + getFirst8AfterSomePhases(EXAMPLE2, 100));
        System.out.println("This should yield '73745418': " + getFirst8AfterSomePhases(EXAMPLE3, 100));
        System.out.println("This should yield '52432133': " + getFirst8AfterSomePhases(EXAMPLE4, 100));
    }

    public static long doPuzzle1() {
        return getFirst8AfterSomePhases(INPUT, 100);
    }

    public static long doPuzzle2() {
        // TODO
        return 0;
    }
}
