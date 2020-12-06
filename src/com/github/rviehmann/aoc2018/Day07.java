package com.github.rviehmann.aoc2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {

    private static final String SAMPLE =
            "Step C must be finished before step A can begin.\n" +
                    "Step C must be finished before step F can begin.\n" +
                    "Step A must be finished before step B can begin.\n" +
                    "Step A must be finished before step D can begin.\n" +
                    "Step B must be finished before step E can begin.\n" +
                    "Step D must be finished before step E can begin.\n" +
                    "Step F must be finished before step E can begin.";

    // From: https://adventofcode.com/2018/day/7/input
    private static final String INPUT =
            "Step I must be finished before step G can begin.\n" +
                    "Step J must be finished before step A can begin.\n" +
                    "Step L must be finished before step D can begin.\n" +
                    "Step V must be finished before step S can begin.\n" +
                    "Step U must be finished before step T can begin.\n" +
                    "Step F must be finished before step Z can begin.\n" +
                    "Step D must be finished before step A can begin.\n" +
                    "Step E must be finished before step Z can begin.\n" +
                    "Step C must be finished before step Q can begin.\n" +
                    "Step H must be finished before step X can begin.\n" +
                    "Step A must be finished before step Z can begin.\n" +
                    "Step Z must be finished before step M can begin.\n" +
                    "Step P must be finished before step Y can begin.\n" +
                    "Step N must be finished before step K can begin.\n" +
                    "Step R must be finished before step W can begin.\n" +
                    "Step K must be finished before step O can begin.\n" +
                    "Step W must be finished before step S can begin.\n" +
                    "Step G must be finished before step Q can begin.\n" +
                    "Step Q must be finished before step B can begin.\n" +
                    "Step S must be finished before step T can begin.\n" +
                    "Step B must be finished before step M can begin.\n" +
                    "Step T must be finished before step Y can begin.\n" +
                    "Step M must be finished before step O can begin.\n" +
                    "Step X must be finished before step O can begin.\n" +
                    "Step O must be finished before step Y can begin.\n" +
                    "Step C must be finished before step O can begin.\n" +
                    "Step B must be finished before step O can begin.\n" +
                    "Step T must be finished before step O can begin.\n" +
                    "Step S must be finished before step X can begin.\n" +
                    "Step E must be finished before step K can begin.\n" +
                    "Step Q must be finished before step M can begin.\n" +
                    "Step E must be finished before step P can begin.\n" +
                    "Step Q must be finished before step S can begin.\n" +
                    "Step E must be finished before step O can begin.\n" +
                    "Step D must be finished before step P can begin.\n" +
                    "Step X must be finished before step Y can begin.\n" +
                    "Step I must be finished before step U can begin.\n" +
                    "Step B must be finished before step X can begin.\n" +
                    "Step F must be finished before step T can begin.\n" +
                    "Step B must be finished before step T can begin.\n" +
                    "Step V must be finished before step R can begin.\n" +
                    "Step I must be finished before step Q can begin.\n" +
                    "Step I must be finished before step A can begin.\n" +
                    "Step M must be finished before step X can begin.\n" +
                    "Step Z must be finished before step S can begin.\n" +
                    "Step C must be finished before step S can begin.\n" +
                    "Step T must be finished before step M can begin.\n" +
                    "Step K must be finished before step X can begin.\n" +
                    "Step Z must be finished before step P can begin.\n" +
                    "Step V must be finished before step H can begin.\n" +
                    "Step Z must be finished before step B can begin.\n" +
                    "Step M must be finished before step Y can begin.\n" +
                    "Step C must be finished before step K can begin.\n" +
                    "Step W must be finished before step Y can begin.\n" +
                    "Step J must be finished before step Z can begin.\n" +
                    "Step Q must be finished before step O can begin.\n" +
                    "Step T must be finished before step X can begin.\n" +
                    "Step P must be finished before step Q can begin.\n" +
                    "Step P must be finished before step K can begin.\n" +
                    "Step D must be finished before step M can begin.\n" +
                    "Step P must be finished before step N can begin.\n" +
                    "Step S must be finished before step B can begin.\n" +
                    "Step H must be finished before step Y can begin.\n" +
                    "Step R must be finished before step K can begin.\n" +
                    "Step G must be finished before step S can begin.\n" +
                    "Step P must be finished before step S can begin.\n" +
                    "Step C must be finished before step Z can begin.\n" +
                    "Step Q must be finished before step Y can begin.\n" +
                    "Step F must be finished before step R can begin.\n" +
                    "Step N must be finished before step B can begin.\n" +
                    "Step G must be finished before step M can begin.\n" +
                    "Step E must be finished before step X can begin.\n" +
                    "Step D must be finished before step E can begin.\n" +
                    "Step D must be finished before step C can begin.\n" +
                    "Step U must be finished before step O can begin.\n" +
                    "Step H must be finished before step Z can begin.\n" +
                    "Step L must be finished before step C can begin.\n" +
                    "Step L must be finished before step F can begin.\n" +
                    "Step V must be finished before step D can begin.\n" +
                    "Step F must be finished before step X can begin.\n" +
                    "Step V must be finished before step W can begin.\n" +
                    "Step S must be finished before step Y can begin.\n" +
                    "Step K must be finished before step T can begin.\n" +
                    "Step D must be finished before step Z can begin.\n" +
                    "Step C must be finished before step W can begin.\n" +
                    "Step V must be finished before step M can begin.\n" +
                    "Step F must be finished before step H can begin.\n" +
                    "Step A must be finished before step M can begin.\n" +
                    "Step G must be finished before step Y can begin.\n" +
                    "Step H must be finished before step M can begin.\n" +
                    "Step N must be finished before step W can begin.\n" +
                    "Step J must be finished before step K can begin.\n" +
                    "Step C must be finished before step B can begin.\n" +
                    "Step Z must be finished before step Y can begin.\n" +
                    "Step L must be finished before step E can begin.\n" +
                    "Step G must be finished before step B can begin.\n" +
                    "Step Q must be finished before step T can begin.\n" +
                    "Step D must be finished before step W can begin.\n" +
                    "Step H must be finished before step G can begin.\n" +
                    "Step L must be finished before step O can begin.\n" +
                    "Step N must be finished before step O can begin.";

    private static final String[] SAMPLE_AS_CONDITION_ARRAY = SAMPLE.split("\\R");
    private static final String[] INPUT_AS_CONDITION_ARRAY = INPUT.split("\\R");

    private static final String REGEX = "^Step (.) must be finished before step (.) can begin\\.$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static class Conditions {
        // Read like this: Character C has conditions: X, Y, Z.
        Map<Character, List<Character>> conditions;
        Set<Character> possibleSteps;

        public Conditions(Map<Character, List<Character>> conditions, Set<Character> possibleSteps) {
            this.conditions = conditions;
            this.possibleSteps = possibleSteps;
        }

        public static Conditions fromString(String[] conditionsStrings) {
            Map<Character, List<Character>> conditions = new HashMap<>();
            Set<Character> possibleSteps = new HashSet<>();
            for (String conditionsString : conditionsStrings) {
                Matcher matcher = PATTERN.matcher(conditionsString);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("Input does not match pattern.");
                }
                String a = matcher.group(1);
                String b = matcher.group(2);
                List<Character> list = new ArrayList<>();
                list.add(a.toCharArray()[0]);
                conditions.merge(b.toCharArray()[0], list, (oldList, newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                });
                possibleSteps.add(a.toCharArray()[0]);
                possibleSteps.add(b.toCharArray()[0]);
            }
            return new Conditions(conditions, possibleSteps);
        }
    }

    private static String determineOrder(Conditions conditions) {
        List<Character> done = new ArrayList<>();

        do {
            for (char c = 'A'; c <= 'Z'; c++) {
                if (canBeDone(conditions, done, c)) {
                    done.add(c);
                    break;
                }
            }
        } while (done.size() < conditions.possibleSteps.size());

        StringBuilder output = new StringBuilder();
        done.forEach(output::append);
        return output.toString();
    }

    private static boolean canBeDone(Conditions conditions, List<Character> done, char c) {
        // Not an allowed step
        if (!conditions.possibleSteps.contains(c)) return false;

        // Has been done already
        if (done.contains(c)) return false;

        // Step does not have any conditions, so can be done at any time
        if (conditions.conditions.get(c) == null) return true;

        // Check if all preconditions have been met already
        List<Character> necessarySteps = conditions.conditions.get(c);
        for (Character necessaryStep : necessarySteps) {
            if (!done.contains(necessaryStep)) return false;
        }
        return true;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 07: Examples for puzzle 1 ###");
        Conditions conditions = Conditions.fromString(SAMPLE_AS_CONDITION_ARRAY);
        String order = determineOrder(conditions);
        System.out.println("Determined order for example: " + order);
    }

    public static String doPuzzle1() {
        Conditions conditions = Conditions.fromString(INPUT_AS_CONDITION_ARRAY);
        return determineOrder(conditions);
    }
}
