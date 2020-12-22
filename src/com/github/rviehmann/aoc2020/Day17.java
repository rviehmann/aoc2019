package com.github.rviehmann.aoc2020;

public class Day17 {

    // From: https://adventofcode.com/2020/day/17/input
    private static final String INPUT =
            ".##..#.#\n" +
                    "#...##.#\n" +
                    "##...#.#\n" +
                    ".##.##..\n" +
                    "...#.#.#\n" +
                    ".##.#..#\n" +
                    "...#..##\n" +
                    "###..##.";

    private static final String[] INPUT_AS_ARRAY = INPUT.split("\\R");

    private static final int ACTIVE = 1;
    private static final int INACTIVE = 0;

    // The actual value is quite arbitrary, but should be high enough that we don't run out of space. Should be even.
    private static final int CELLS_PER_DIMENSION = 30;

    private static int[][][] generateStartState(String[] input) {
        int[][][] startState = new int[CELLS_PER_DIMENSION][CELLS_PER_DIMENSION][CELLS_PER_DIMENSION];
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                int z = 0;
                if (input[y].charAt(x) == '#') {
                    startState[logicalOrdinateToArrayIndex(x)][logicalOrdinateToArrayIndex(y)][logicalOrdinateToArrayIndex(z)] = ACTIVE;
                }
            }
        }
        return startState;
    }

    private static int[][][] tick(int[][][] state) {
        int[][][] newState = new int[CELLS_PER_DIMENSION][CELLS_PER_DIMENSION][CELLS_PER_DIMENSION];
        for (int x = 1; x < CELLS_PER_DIMENSION - 1; x++) {
            for (int y = 1; y < CELLS_PER_DIMENSION - 1; y++) {
                for (int z = 1; z < CELLS_PER_DIMENSION - 1; z++) {
                    int neighbors = countActiveNeighbors(state, x, y, z);
                    if (state[x][y][z] == ACTIVE) {
                        if (neighbors == 2 || neighbors == 3) {
                            newState[x][y][z] = ACTIVE;
                        } else {
                            newState[x][y][z] = INACTIVE;
                        }
                    } else {
                        if (neighbors == 3) {
                            newState[x][y][z] = ACTIVE;
                        } else {
                            newState[x][y][z] = INACTIVE;
                        }
                    }
                }
            }
        }
        return newState;
    }

    private static int countActiveNeighbors(int[][][] state, int x, int y, int z) {
        int accu = 0;
        for (int a = x - 1; a <= x + 1; a++) {
            for (int b = y - 1; b <= y + 1; b++) {
                for (int c = z - 1; c <= z + 1; c++) {
                    if (!(a == x && b == y && c == z)) {
                        if (state[a][b][c] == ACTIVE) {
                            accu++;
                        }
                    }
                }
            }
        }
        return accu;
    }

    private static int countActiveCells(int[][][] state) {
        int accu = 0;
        for (int x = 0; x < CELLS_PER_DIMENSION; x++) {
            for (int y = 0; y < CELLS_PER_DIMENSION; y++) {
                for (int z = 0; z < CELLS_PER_DIMENSION; z++) {
                    if (state[x][y][z] == ACTIVE) {
                        accu++;
                    }
                }
            }
        }
        return accu;
    }

    private static int logicalOrdinateToArrayIndex(int logicalOrdinate) {
        return logicalOrdinate + CELLS_PER_DIMENSION / 2;
    }

    private static int arrayIndexToLogicaOrdinate(int arrayIndex) {
        return arrayIndex - CELLS_PER_DIMENSION / 2;
    }

    public static long doPuzzle1() {
        int[][][] state = generateStartState(INPUT_AS_ARRAY);
        for (int i = 0; i < 6; i++) {
            state = tick(state);
        }
        return countActiveCells(state);
    }
}
