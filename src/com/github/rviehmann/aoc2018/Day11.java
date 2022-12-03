package com.github.rviehmann.aoc2018;

public class Day11 {

    private static final int gridSerialNumber = 8772;

    private static int[][] calculateCells() {
        int[][] cells = new int[300][300];
        for (int x = 1; x <= 300; x++) {
            for (int y = 1; y <= 300; y++) {
                cells[x - 1][y - 1] = calculateCell(x, y);
            }
        }
        return cells;
    }

    private static int calculateCell(int x, int y) {
        int power = (((x + 10) * y) + gridSerialNumber) * (x + 10);
        int hundrets = (power / 100) % 10;
        return hundrets - 5;
    }

    /**
     * What is returned:
     * Index 0: bestSquareValue
     * Index 1: bestSquareX
     * Index 2: bestSquareY
     * Index 3: size
     */
    private static int[] calculateBestSquareForSize(int[][] cells, int size) {
        int bestSquareValue = Integer.MIN_VALUE;
        int bestSquareX = Integer.MIN_VALUE;
        int bestSquareY = Integer.MIN_VALUE;
        for (int x = 1; x <= 300 - size + 1; x++) {
            for (int y = 1; y <= 300 - size + 1; y++) {
                int squareValue = calculateSquare(cells, x, y, size);
                if (squareValue > bestSquareValue) {
                    bestSquareValue = squareValue;
                    bestSquareX = x;
                    bestSquareY = y;
                }
            }
        }
        return new int[]{
                bestSquareValue,
                bestSquareX,
                bestSquareY,
                size
        };
    }

    private static int calculateSquare(int[][] cells, int x_top_left, int y_top_left, int size) {
        // A square is always size*size cells.
        int sum = 0;
        for (int x = x_top_left; x <= x_top_left + (size - 1); x++) {
            for (int y = y_top_left; y <= y_top_left + (size - 1); y++) {
                sum += cells[x - 1][y - 1];
            }
        }
        return sum;
    }

    public static String doPuzzle1() {
        int[][] cells = calculateCells();
        int[] bestSquare = calculateBestSquareForSize(cells, 3);
        System.out.println("Size 3: bestSquareValue: " + bestSquare[0] + ", coordinates: " + bestSquare[1] + "," + bestSquare[2]);
        return bestSquare[1] + "," + bestSquare[2];
    }

    public static String doPuzzle2() {
        int[][] cells = calculateCells();
        int bestSquareValue = Integer.MIN_VALUE;
        int bestSquareX = Integer.MIN_VALUE;
        int bestSquareY = Integer.MIN_VALUE;
        int bestSquareSize = Integer.MIN_VALUE;
        for (int size = 1; size <= 300; size++) {
            int[] bestSquare = calculateBestSquareForSize(cells, size);
            if (bestSquare[0] > bestSquareValue) {
                bestSquareValue = bestSquare[0];
                bestSquareX = bestSquare[1];
                bestSquareY = bestSquare[2];
                bestSquareSize = bestSquare[3];
            }
        }
        return bestSquareX + "," + bestSquareY + "," + bestSquareSize;
    }
}
