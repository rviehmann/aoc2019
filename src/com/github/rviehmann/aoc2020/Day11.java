package com.github.rviehmann.aoc2020;

public class Day11 {

    private static final String EXAMPLE1 =
            "L.LL.LL.LL\n" +
                    "LLLLLLL.LL\n" +
                    "L.L.L..L..\n" +
                    "LLLL.LL.LL\n" +
                    "L.LL.LL.LL\n" +
                    "L.LLLLL.LL\n" +
                    "..L.L.....\n" +
                    "LLLLLLLLLL\n" +
                    "L.LLLLLL.L\n" +
                    "L.LLLLL.LL";

    // From: https://adventofcode.com/2020/day/11/input
    private static final String INPUT =
            "LLLLLLLLLLLL.LLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLL.LLLL.LLLLLLL.LL.LLLLLLLL.LLL.LLLLLLLLLLL.LLLLLLL\n" +
                    "LLLLLLLLLLL.LLLL.L..LLL.LL.LLLLLLLLLLL.LLLLLL.LLLLLLLL.L.LLLLL.LLLLLL..LLL.LLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLL.L.LLLL.LLL.LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL..LLLLLLLLLLLLLLLLLLLLLLLLLLL.LLL\n" +
                    "LLLLLLLLLLL.LL.LL..LLLL.LLLL.L.LLLLLLL.LLLLLL.LLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLL.L.L.L..LLLLLLLLLL..LLL..LLLL.LLLL.LLLLLLLL.LLLLLL.LLLLLL.LLLLLLLLLLLLLLL.L.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLL.LLLL.LL.LLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLL.LLLL.LLLLLLL.LLLLLL\n" +
                    ".L.L....LL......LL..LL.....LLLLL.......L....LL......L.L..L..L..L.L.LL.....LL.......L.L....L..L\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLL.LLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLL.L.LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL..LLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLL..LLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLL.LL.LLLL\n" +
                    "LLLLLL.LLLL.LLLLLL.L.LL.LLLL.LLLLLLLLL.LLLLLLLLLLLL.LL.LLLLLLLLLL..LLLLLL.LLLLLLLLLLLLLLLLLLLL\n" +
                    "LL..LL.LLLL.LLLLLL.LLLLLL.LL.LLLLL.LLLLLLLLLL.LLLLLLLLLLLLLLL.LL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "L......LLLLL.L....LL...L..........L..L...L.......LL.L.L.L........L......L..L...L...LL.LL...LL.\n" +
                    "LLLLLL.L.LL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLL..LLL.LLL.LLLLLLLLLLLLLLLLLLL.LLL\n" +
                    ".LLL.L.LLLL.L.LLLLL.LLL.LLLL.LLLLLLL.LLLLLLLL.LLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLL.LL.LLL.LLLL.LLL.LLLLLL..LLL.LLL.LL.LLL.L..L.LLLL.LLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LL\n" +
                    "LLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL..LLLLL.LL.LLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLL\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLL.LLL.L.LL.LLLLLL..LLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLL..LL.L.LLLLLL.LLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLL.LL.LLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLL.LLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL\n" +
                    ".LL.......L.LL.L.L.........L..L...L..LL..L.....LL...LLLL.......L.........L.L..L...L.LL.LL...L.\n" +
                    "LLLLLL.LLLLLLLLLL..LLLLLLLLL.LLLLLLLLL.LL.LLLLLLLLL.LL.LLLLLLL.LLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLL.LL.LLL..LLLLLL.LLLLLLLLL.LLLLLLLLL.LL.LLL.LLLLLLLL.LLLLLL.LLLLLLLL.LL.LLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLLLLLLLLL.LLLL.LLLLLLLLLLLLLL..LLL.L.LLLLLLLL.LLLLLLLLLLLLLLL.L...LLLL.LLLLLLLLLL.LLL\n" +
                    "LLLLLLLLLLLLLLLLLL.LLLL..L.L.LLLLL.LL..LLLLLL.LLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLL\n" +
                    "LLLLLLLLLLL.LLLLLLL.LLLLLLLL.LLLLLLLLL.LLL.LL.LLLL.LLL.LLLLLL.LLLLLLL..LLLLLLLLLLLLLLLLLL.LLLL\n" +
                    "LLLLLLLLLLL.LLLLLL.LLLLLLLLL.L.LLLLLLLLL.LLLL.LLLLLLL..LLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLLLLL.L.LLLLLLLLLLLLLLLL.LL.LLL.LL.L.LLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLLLLL.L.LLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLL\n" +
                    "LLLLLL.L.LL.LLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLL..LLLLLLL.LL.LLL.LLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLL\n" +
                    ".L........LL..L..LLL.LL.LL..L.....L..L........LL.LL.L..L.L..LL.L......L..L.L..L.L...L..L....L.\n" +
                    "LLLLLL.LLL.LLLL.LLLLLLL.LLL.L.LL.LLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "LLLL.LLLLL.LLLLLLL.LLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLL.LLLLLLL.LLLLLL\n" +
                    "LLLLLL..LLLLLLLLLL.LLLLLLLLL.L.LLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLL.LLLLL...LLL.LLLLLLLLLLLLLLLLL\n" +
                    "LLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLL.LLLL..LLLLLLLL.LLLLLLLLLLLLLLL.L.LLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLLLLLLL.LLLLLLLLLLL.L.LL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLLLLLL..LLLLLL.LLLL.LLLLLLLLLLLLLL.LL.LLL.LL.LLLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLL.LLLLLL\n" +
                    "LLL.LL.LLLLLLLL.LL.LLLL.LLLL.L.LLLLLLLLLLLLLL.LLLLLLLL.L.LLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "....LL.....L..L.L..L.....LLL....LL.LL.L............L..LL.....LL..LL..L....L..L.L.L........LL..\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLLL.LLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLL.LLLLLLLL.L.LLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLL.LLLLLLL.LLLLLLLLLLL.LL.LLLLLL.LLL.LLLLLLLLLLL.LLLLLLLL.LLLL.LLLLLLLLLLLLL.LLLL\n" +
                    "LLLL.LLLLLLLLLLL.L..LLLLLLLLLLLL.LLLLL.LLLLLL.LLLLLLLL.LLLLLL.LLLL.LL..LLLLLLLL.LLLLLLLLLLL.LL\n" +
                    "LLLLLL.LLLLLLLLLLL.LLLL.LLLL.LLLLLLLL..LLLLL..LLLLLLLLLL.LLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLL..L.LL.LLL.LLLLLLL.LLLLLLLL.LLLLLLL.LLL.LLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.L.LLLL.LLLLLLL.L.LLLL.LLLLLLL.LL..LLLLLLLL.LLLLLL.LLL.LLLL.LL..LLLL.LLLLLLLLLLL.LL\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLLLLL.L.L.LLLLL.L.LLLLLLLLLLLLLLLLLLLLLL.LLLLLL.L.LL.LLLLL.LLLL.LLLLLLLLL\n" +
                    ".LLL....L.LLLL..L.LL.......LL.L...LL.L...LL....L..L..L..LLLL....L....L..L...L....L...L.LL.....\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLLLLLLLLLLL.LLLLL..LLLLL.LLLLLL.LLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLL\n" +
                    "LLLLL..LLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLLL.LLLLLLLLLLLLL.LLL.LLLLLLLLLLLLLL\n" +
                    "..LL.LLLL.LL.LLLLL.L.LL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLL..LLLLLLLL.LLLLLLLLL.LL.L\n" +
                    "LLLLLL..LLL.LLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLL.LLLL.LLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLLL.LL.LLL.LLLLLLLL.LLLLLL.LLLLLLLL.LLLL.L.LLLLLLLLLLLL.LLL\n" +
                    "LLLLLLLLLLLL.LL.LL.LLLL.LLLL.L.LL.L.LLLLLLL.L.LLLLLLLL.LLLLLL.LLLL.LLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "......L...L....L..............L.L....L..L.LL.L..LL....LLL........L..LL.L..L.L.LLL.........L.L.\n" +
                    "LLLLLL.LLLL.LLLLLL.L.LL.LLLL.LLLL.LLLLLL.LLLL.LLLLLLLL.LLL.LL.LLLLLLLL.LL.LLLLLLLLLLLLLLLLLLLL\n" +
                    "LL.LLLLLLLLL.LLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLLL.L.LLLLLLLL.LLLLLLLLLLLLLL.LLLLLL.L\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLL.LLLL.LLLLLLLLL.L.LLLL.LLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "L.LLLLLLLLLLLLLLLL.LLLLL.LLLLLLLL.LLLL.LLLLLL.LLL.LLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLL..LLLL..LLLL\n" +
                    "LL.LLL.LLLLLLLLLLL.LLLLLLLLL..LLLLLLLL.LLLLLL.LLLL..LL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LL\n" +
                    "LLLLLLLLLLLLLLLLLLLLLL..LLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLLLLL.L..LLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLL.LLLL.LLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLL.L.LLLL.LLLL.L.L.LLLLLLL.LLLLLLLLLLLL.LLL.LLLLLLLL..LLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLL\n" +
                    "LLL.LL.LLLL.LLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLL.LLL.LLLLLLLLLLLLLL\n" +
                    ".L.......L..L...L..LL......L..L..L..L...L.L........LLL......L......LL.....L.L......L.L.LL....L\n" +
                    "LLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLL.LLLLLL\n" +
                    "LLLL.LLLLLLLLLLLLLLLL..LLLLL.LLLLLLLLL..LLLLL.LLLLLLLL.LLLLLLLLLLLLL.L.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    ".LLLLL.LLLL.LL.LLL.LLLL.LLLL.LLLLLLLLL.LLLL.L.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLLLLLLLL.LLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLL...LLLLLLLL.L.LLLLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLL.LLLL.LL.LLL.LLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLL.LLLLL\n" +
                    "......L.LLLL...LLL.L..L......L..L.L.L....L.......LLL....L..LLLL.LLL..LL..L.L.L........LLL.L...\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLL.LLLL..LLLLLLLL.LLLLLL.LLLLLLLL..LLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLL.LL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLL.LLLLL.LLLLLLLL\n" +
                    "LLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LL.LLLLLLLLLLLLLLLL.LL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL....L.LLLLLL.LLLL.LLL.LLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLL.LLLLL..L.LLLLLLLLLLLLLLLLLLLLL.L\n" +
                    "LLLLLLLL.LL.LLLLLLLLLLLLLL.L.LLLLLLLLL.LLLLLL..LLLLLLL.LLLLLLLL.LLLLLLLLLL.LLLL.LLLLLLLLLLL.LL\n" +
                    "LLLLLL.LLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLL...LLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    ".L..L....L.L..L..LL...L.L.LLLLLL...LLL.LL......LLL...L..LLLL.LLLLL.L...LLL...L.LL..L.L...L.L.L\n" +
                    "LLLLLL.LLL..LLLLLL.LLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLLLL..LLLLL.LLLLLLLL.LLLLLLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLL..LLLLLLLL.LLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLL.LLLLLLLL.LLLLLLL..LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLL.L.L.LLLL.LLLLLLLLLLLLLLL.LL.LLLLLLLLLLLLLL..LLLLLLLLLLLLL\n" +
                    "L.LLLL.LLLL.LLL.LLLLLLLLLLLL.LLLLLLLLL.LLLLL..LLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLL.LLLLL.LLLL.L.LLLL.LLLL.LLLLL.LLL.LLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL\n" +
                    "LLLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLL.LLLLLL.LLLLLLLL.LLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLL.\n" +
                    "L....L.....L..L......L....L.L...L.L.....L.L...LL......L....LL...L....LL...LL.......LLL...L.L.L\n" +
                    "LLLLLLLLLLL.LLLLLLLL.LL.LLL..LLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLLLLLLLLLLL.LLL.LLLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLLLLLLLLLLLLLLLLLLLLLLL..LLLLLLLLL.LLLLLL..LLLLLLL.LLLLLL.L.L.LLLLLLLLL.LLL.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLL.LLLL.LL.LLLLLLLLLLL.LLLLLLLL.LLLLLL.LLLLL...LLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.\n" +
                    "LLLLLL.LLLL.LLLLLL.LL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLL.LLLLLL.L.LLLLLLLLLLLLLL\n" +
                    "LLLLLL.LLLL.LLLLLLLLLLL.LLLL.LLLLL.LLLLLLLLLL.LLLLLLLL..LLLLL.LL.LLLLL.LLL.LL.L.LLLLLLLLLLLLLL";

    private static final String[] EXAMPLE1_AS_ARRAY = EXAMPLE1.split("\\R");
    private static final String[] INPUT_AS_ARRAY = INPUT.split("\\R");

    private static String[] deriveNextState(String[] currentState, boolean simpleRules) {
        int treshold = simpleRules ? 4 : 5;
        String[] nextState = new String[currentState.length];
        for (int row = 0; row < currentState.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int column = 0; column < currentState[0].length(); column++) {
                char myself = currentState[row].charAt(column);
                int numNeighbors = numNeighbors(currentState, column, row, simpleRules);
                char next;
                switch (myself) {
                    case '.': // Floor
                        next = myself;
                        break;
                    case '#': // Occupied
                        next = numNeighbors >= treshold ? 'L' : '#';
                        break;
                    case 'L': // Empty
                        next = numNeighbors == 0 ? '#' : 'L';
                        break;
                    default:
                        throw new IllegalStateException("Seat has strange state: " + myself);
                }
                sb.append(next);
            }
            nextState[row] = sb.toString();
        }
        return nextState;
    }

    private static int numNeighbors(String[] currentState, int x, int y, boolean simpleRules) {
        int numNeighbors = 0;
        for (int column = x - 1; column <= x + 1; column++) {
            for (int row = y - 1; row <= y + 1; row++) {
                char c = charInPos(currentState, column, row);
                if (c == '#') { // Occupied
                    if (!(column == x && row == y)) {
                        numNeighbors++;
                    }
                }
            }
        }
        return numNeighbors;
    }

    private static int numOccupiedSeats(String[] currentState) {
        int occupied = 0;
        for (int row = 0; row < currentState.length; row++) {
            for (int column = 0; column < currentState[0].length(); column++) {
                char c = charInPos(currentState, column, row);
                if (c == '#') { // Occupied
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private static boolean isUnchanged(String[] currentState, String[] nextState) {
        for (int row = 0; row < currentState.length; row++) {
            String currentRow = currentState[row];
            String nextRow = nextState[row];
            if (!currentRow.equals(nextRow)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param currentState
     * @param x            always >=0, 0 is left column, increases to the right
     * @param y            always >=0, 0 is top row, increases to the bottom
     * @return
     */
    private static char charInPos(String[] currentState, int x, int y) {
        if (x < 0 || y < 0 || x >= currentState[0].length() || y >= currentState.length) {
            return '.'; // Floor
        }
        return currentState[y].charAt(x);
    }

    private static boolean placeExists(String[] currentState, int x, int y) {
        return !(x < 0 || y < 0 || x >= currentState[0].length() || y >= currentState.length);
    }

    private static void printState(String[] currentState) {
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        for (int row = 0; row < currentState.length; row++) {
            String currentRow = currentState[row];
            System.out.println(currentRow);
        }
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 11: Examples for puzzle 1 ###");
        String[] currentState = EXAMPLE1_AS_ARRAY;
        printState(currentState);
        while (true) {
            String[] nextState = deriveNextState(currentState, true);
            printState(nextState);
            if (isUnchanged(currentState, nextState)) {
                System.out.println("NumOccupiedSeats: " + numOccupiedSeats(nextState));
                return;
            }
            currentState = nextState;
        }
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 11: Examples for puzzle 2 ###");
        String[] currentState = EXAMPLE1_AS_ARRAY;
        printState(currentState);
        while (true) {
            String[] nextState = deriveNextState(currentState, false);
            printState(nextState);
            if (isUnchanged(currentState, nextState)) {
                System.out.println("NumOccupiedSeats: " + numOccupiedSeats(nextState));
                return;
            }
            currentState = nextState;
        }
    }

    public static long doPuzzle1() {
        String[] currentState = INPUT_AS_ARRAY;
        printState(currentState);
        while (true) {
            String[] nextState = deriveNextState(currentState, true);
            printState(nextState);
            if (isUnchanged(currentState, nextState)) {
                System.out.println("NumOccupiedSeats: " + numOccupiedSeats(nextState));
                return numOccupiedSeats(nextState);
            }
            currentState = nextState;
        }
    }

    public static long doPuzzle2() {
        // todo
        return 0;
    }
}
