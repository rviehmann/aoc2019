package com.github.rviehmann.aoc2019;

public class Main {

    public static void main(String[] args) {
        /*
        Day01.testWithExamplesForPuzzle1();
        Day01.testWithExamplesForPuzzle2();
        System.out.println("### Solutions ###");
        System.out.println("Day 01, puzzle 1: " + Day01.doPuzzle1());
        System.out.println("Day 01, puzzle 2: " + Day01.doPuzzle2());
        */

        Day02.testWithExamplesForPuzzle1();
        System.out.println("### Solutions ###");
        System.out.println("Day 02, puzzle 1: " + Day02.doPuzzle1());
        System.out.println("Day 02, puzzle 2: " + Day02.doPuzzle2());

        /*
        Day03.testWithExamplesForPuzzle1();
        Day03.testWithExamplesForPuzzle2();
        System.out.println("### Solutions ###");
        System.out.println("Day 03, puzzle 1: " + Day03.doPuzzle1());
        System.out.println("Day 03, puzzle 2: " + Day03.doPuzzle2());

        Day04.testWithExamplesForPuzzle1();
        Day04.testWithExamplesForPuzzle2();
        System.out.println("### Solutions ###");
        System.out.println("Day 04, puzzle 1: " + Day04.doPuzzle1());
        System.out.println("Day 04, puzzle 2: " + Day04.doPuzzle2());
        */

        Day05.testWithExamplesForPuzzle1();
        System.out.println("### Solutions ###");
        System.out.println("Day 05, puzzle 1: " + Day05.doPuzzle1());
        System.out.println("Day 05, puzzle 2: " + Day05.doPuzzle2());

        Day07.testWithExamplesForPuzzle1();
        Day07.testWithExamplesForPuzzle2();
        System.out.println("### Solutions ###");
        try {
            System.out.println("Day 07, puzzle 1: " + Day07.doPuzzle1());
            System.out.println("Day 07, puzzle 2: " + Day07.doPuzzle2());
        } catch (InterruptedException e) {
            System.err.println("InterruptedException caught: " + e);
        }
    }
}
