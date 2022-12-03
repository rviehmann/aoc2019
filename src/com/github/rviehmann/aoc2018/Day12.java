package com.github.rviehmann.aoc2018;

public class Day12 {

    private static final String SAMPLE_INITIAL_STATE =
            "#..#.#..##......###...###";

    private static final String SAMPLE_RULES =
            "...## => #\n" +
                    "..#.. => #\n" +
                    ".#... => #\n" +
                    ".#.#. => #\n" +
                    ".#.## => #\n" +
                    ".##.. => #\n" +
                    ".#### => #\n" +
                    "#.#.# => #\n" +
                    "#.### => #\n" +
                    "##.#. => #\n" +
                    "##.## => #\n" +
                    "###.. => #\n" +
                    "###.# => #\n" +
                    "####. => #";

    // From: https://adventofcode.com/2018/day/12/input
    private static final String REAL_INITIAL_STATE =
            "#...#..##.......####.#..###..#.##..########.#.#...#.#...###.#..###.###.#.#..#...#.#..##..#######.##";

    private static final String REAL_RULES =
            "#..#. => #\n" +
                    "#.#.. => #\n" +
                    "###.. => #\n" +
                    "##..# => .\n" +
                    ".#.## => #\n" +
                    "..... => .\n" +
                    "...#. => #\n" +
                    "##.#. => #\n" +
                    "#.#.# => .\n" +
                    "###.# => #\n" +
                    "....# => .\n" +
                    "####. => #\n" +
                    ".##.. => #\n" +
                    "#.##. => #\n" +
                    "#..## => #\n" +
                    "##... => #\n" +
                    "#...# => .\n" +
                    "##.## => #\n" +
                    ".#... => .\n" +
                    ".#..# => #\n" +
                    "..#.# => #\n" +
                    "##### => .\n" +
                    ".#### => #\n" +
                    "..#.. => #\n" +
                    "#.### => .\n" +
                    "..##. => .\n" +
                    ".##.# => #\n" +
                    ".#.#. => .\n" +
                    "..### => .\n" +
                    ".###. => .\n" +
                    "...## => .\n" +
                    "#.... => .\n";

    private static final String[] SAMPLE_AS_RULE_ARRAY = SAMPLE_RULES.split("\\R");
    private static final String[] INPUT_AS_RULE_ARRAY = REAL_RULES.split("\\R");

    public static class State {
        final String stateString;
        final int potNumberOfLeftmostCharacter;

        public State(String stateString, int potNumberOfLeftmostCharacter) {
            this.stateString = stateString;
            this.potNumberOfLeftmostCharacter = potNumberOfLeftmostCharacter;
        }

        public State(String stateString) {
            this.stateString = stateString;
            this.potNumberOfLeftmostCharacter = 0;
        }
    }

    private static int countPlants(State state) {
        int count = 0;
        int potNumber = state.potNumberOfLeftmostCharacter;
        for (char c : state.stateString.toCharArray()) {
            count += (c == '#' ? potNumber : 0);
            potNumber++;
        }
        return count;
    }

    private static State deriveStateAfterSeveralGenerations(State startState, String[] rules, int generations) {
        State currentState = startState;
        for (int generation = 1; generation <= generations; generation++) {
            currentState = deriveState(currentState, rules);
        }
        return currentState;
    }

    private static State deriveState(State startState, String[] rules) {
        // We assume that for each generation, the state could grow one pot to the left and one to the right.
        char[] newState = new char[startState.stateString.length() + 2];
        int potNumber = startState.potNumberOfLeftmostCharacter - 1;
        for (int pos = 0; pos < newState.length; pos++) {
            newState[pos] = derivePlantAtPotNumber(startState, rules, potNumber++);
        }
        return new State(new String(newState), startState.potNumberOfLeftmostCharacter - 1);
    }

    private static char derivePlantAtPotNumber(State startState, String[] rules, int potNumber) {
        for (String rule : rules) {
            if (ruleMatches()) {
                // Return the last char of the string (which is either '#' (plant) or '.' (no plant)).
                return rule.toCharArray()[rule.length() - 1];
            }
        }
        throw new IllegalArgumentException("Could not derive plant.");
    }

    private static boolean ruleMatches() {
        // todo
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 12: Examples for puzzle 1 ###");
        System.out.println("Sum of number of pots after 20 generations: " + countPlants(deriveStateAfterSeveralGenerations(
                new State(SAMPLE_INITIAL_STATE), SAMPLE_AS_RULE_ARRAY, 20)));
    }

    public static long doPuzzle1() {
        return countPlants(deriveStateAfterSeveralGenerations(
                new State(REAL_INITIAL_STATE), INPUT_AS_RULE_ARRAY, 20));
    }
}
