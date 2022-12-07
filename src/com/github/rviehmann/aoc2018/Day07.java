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
        final Map<Character, List<Character>> conditions;
        final Set<Character> possibleSteps;

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

    public static class Worker {

        final int additionalSecondsPerTask;

        boolean hasTask;
        int workFirstSecond;
        int workLastSecond;
        char task;

        public Worker(int additionalSecondsPerTask) {
            this.additionalSecondsPerTask = additionalSecondsPerTask;
        }

        public void startTask(int globalSeconds, char task) {
            this.hasTask = true;
            this.workFirstSecond = globalSeconds;
            this.workLastSecond = globalSeconds + additionalSecondsPerTask + (task - 'A');
            this.task = task;
        }

        public boolean hasTask() {
            return this.hasTask;
        }

        public boolean isStillWorking(int globalSeconds) {
            return hasTask && globalSeconds <= workLastSecond;
        }

        public char printWhatYouWorkOn(int globalSeconds) {
            if (this.isStillWorking(globalSeconds)) {
                return task;
            } else {
                return '.';
            }
        }

        public void deleteTask() {
            this.hasTask = false;
        }
    }

    private static String determineOrderForPuzzle1(Conditions conditions) {
        List<Character> done = new ArrayList<>();

        do {
            for (char c = 'A'; c <= 'Z'; c++) {
                if (canBeDone(conditions, done, new ArrayList<>(), c)) {
                    done.add(c);
                    break;
                }
            }
        } while (done.size() < conditions.possibleSteps.size());

        StringBuilder output = new StringBuilder();
        done.forEach(output::append);
        return output.toString();
    }

    private static Worker getFirstFreeWorker(List<Worker> workers, int globalSeconds) {
        for (Worker worker : workers) {
            if (!worker.isStillWorking(globalSeconds)) {
                return worker;
            }
        }
        return null;
    }

    private static long determineTimeForPuzzle2(Conditions conditions, int numWorkers, int additionalSecondsPerTask) {
        List<Character> done = new ArrayList<>();
        List<Character> inProgress = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < numWorkers; i++) {
            workers.add(new Worker(additionalSecondsPerTask));
        }

        int globalSeconds = 0;
        do {
            for (Worker worker : workers) {
                if (!worker.isStillWorking(globalSeconds) && worker.hasTask) {
                    done.add(worker.task);
                    inProgress.remove((Character) worker.task);
                    worker.deleteTask();
                }
            }
            for (char c = 'A'; c <= 'Z'; c++) {
                if (canBeDone(conditions, done, inProgress, c)) {
                    Worker firstFreeWorker = getFirstFreeWorker(workers, globalSeconds);
                    if (firstFreeWorker != null) {
                        firstFreeWorker.startTask(globalSeconds, c);
                        inProgress.add(c);
                    } else {
                        break;
                    }
                }
            }
            printCurrentSituation(globalSeconds, workers, done);
            globalSeconds++;
        } while (done.size() < conditions.possibleSteps.size());

        // Time has been incremented exactly one time too many at the end of the loop
        return globalSeconds - 1;
    }

    private static void printCurrentSituation(int globalSeconds, List<Worker> workers, List<Character> done) {
        StringBuilder sb = new StringBuilder();
        sb.append(globalSeconds);
        sb.append("\t\t");
        for (Worker worker : workers) {
            sb.append(worker.printWhatYouWorkOn(globalSeconds));
            sb.append("\t\t");
        }
        done.forEach(sb::append);
        System.out.println(sb);
    }

    private static boolean canBeDone(Conditions conditions, List<Character> done, List<Character> inProgress, char c) {
        // Not an allowed step
        if (!conditions.possibleSteps.contains(c)) {
            return false;
        }

        // Has been done already
        if (done.contains(c)) {
            return false;
        }

        // Is already being done by someone
        if (inProgress.contains(c)) {
            return false;
        }

        // Step does not have any conditions, so can be done at any time
        if (conditions.conditions.get(c) == null) {
            return true;
        }

        // Check if all preconditions have been met already
        List<Character> necessarySteps = conditions.conditions.get(c);
        for (Character necessaryStep : necessarySteps) {
            if (!done.contains(necessaryStep)) {
                return false;
            }
        }
        return true;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 07: Examples for puzzle 1 ###");
        Conditions conditions = Conditions.fromString(SAMPLE_AS_CONDITION_ARRAY);
        String order = determineOrderForPuzzle1(conditions);
        System.out.println("Determined order for example: " + order);
    }

    public static String doPuzzle1() {
        Conditions conditions = Conditions.fromString(INPUT_AS_CONDITION_ARRAY);
        return determineOrderForPuzzle1(conditions);
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 07: Examples for puzzle 2 ###");
        Conditions conditions = Conditions.fromString(SAMPLE_AS_CONDITION_ARRAY);
        long time = determineTimeForPuzzle2(conditions, 2, 0);
        System.out.println("Determined time for example: " + time);
    }

    public static long doPuzzle2() {
        Conditions conditions = Conditions.fromString(INPUT_AS_CONDITION_ARRAY);
        return determineTimeForPuzzle2(conditions, 5, 60);
    }
}
