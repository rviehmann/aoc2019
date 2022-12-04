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

    private static int countPlantationValue(State state) {
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
        printState(currentState, 0);
        for (int generation = 1; generation <= generations; generation++) {
            currentState = shortenState(deriveState(currentState, rules));
            printState(currentState, generation);
        }
        return currentState;
    }

    private static void printState(State state, int generation) {
        System.out.println(generation + ": " + state.stateString + " (potNumberOfLeftmostCharacter: " + state.potNumberOfLeftmostCharacter + ", value: " + countPlantationValue(state) + ")");
    }

    private static State deriveState(State startState, String[] rules) {
        // We assume that for each generation, the state could grow one pot to the left and one to the right.
        char[] newState = new char[startState.stateString.length() + 2];
        int potNumber = startState.potNumberOfLeftmostCharacter - 1;
        for (int pos = 0; pos < newState.length; pos++) {
            newState[pos] = derivePlantAtPot(startState, rules, potNumber++);
        }
        return new State(new String(newState), startState.potNumberOfLeftmostCharacter - 1);
    }

    private static State shortenState(State state) {
        String stateString = state.stateString.replace('.', ' ').trim().replace(' ', '.');
        int potNumberOfLeftmostCharacter = state.potNumberOfLeftmostCharacter + state.stateString.indexOf('#');
        return new State(stateString, potNumberOfLeftmostCharacter);
    }

    private static char derivePlantAtPot(State startState, String[] rules, int potNumber) {
        for (String rule : rules) {
            if (ruleMatches(startState, rule, potNumber)) {
                // Return the last char of the string (which is either '#' (plant) or '.' (no plant)).
                return rule.toCharArray()[rule.length() - 1];
            }
        }
        return '.'; // No plant
    }

    private static boolean ruleMatches(State startState, String rule, int potNumber) {
        int ruleLength = rule.indexOf(' ');
        String longerStartState = "..." + startState.stateString + "...";
        // Leftmost char in longerStartState has pot number (startState.potNumberOfLeftmostCharacter - 3).
        // Leftmost char we are interested in has pot number (potNumber - 2).
        int start = (potNumber - 2) - (startState.potNumberOfLeftmostCharacter - 3);
        int end = start + ruleLength;
        String relevantStartStatePart = longerStartState.substring(start, end);
        String relevantRulePart = rule.substring(0, ruleLength);
        return relevantStartStatePart.equals(relevantRulePart);
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 12: Examples for puzzle 1 ###");
        System.out.println("Sum of number of pots after 20 generations: " + countPlantationValue(deriveStateAfterSeveralGenerations(
                new State(SAMPLE_INITIAL_STATE), SAMPLE_AS_RULE_ARRAY, 20)));
    }

    public static long doPuzzle1() {
        return countPlantationValue(deriveStateAfterSeveralGenerations(
                new State(REAL_INITIAL_STATE), INPUT_AS_RULE_ARRAY, 20));
    }

    public static long doPuzzle2() {
        /*
        We find out the following:
        * Generation 96 is the same as generation 95, just shifted one position to the right.
        * Therefore, the difference in value between g96 and g95 is 91.
        * Therefore, each further iteration will be identical, just shifted to the right each time.
        * The g95 has a value of 10756.
        * Therefore, we can find out the value for a generation G with this formula (if G>=95): ((G-95) * 91) + 10756
        */
        long generation = 50000000000L;
        return ((generation - 95) * 91) + 10756;
    }
}
