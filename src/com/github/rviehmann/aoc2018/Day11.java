package com.github.rviehmann.aoc2018;

public class Day11 {

    private static final int gridSerialNumber = 8772;

    private static int calculateCell(int x, int y) {
        int power = (((x + 10) * y) + gridSerialNumber) * (x + 10);
        int hundrets = (power / 100) % 10;
        return hundrets - 5;
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
        int[][] cells = new int[300][300];
        for (int x = 1; x <= 300; x++) {
            for (int y = 1; y <= 300; y++) {
                cells[x - 1][y - 1] = calculateCell(x, y);
            }
        }
        int bestSquareValue = Integer.MIN_VALUE;
        int bestSquareX = Integer.MIN_VALUE;
        int bestSquareY = Integer.MIN_VALUE;
        for (int x = 1; x <= 298; x++) {
            for (int y = 1; y <= 298; y++) {
                int squareValue = calculateSquare(cells, x, y, 3);
                if (squareValue > bestSquareValue) {
                    bestSquareValue = squareValue;
                    bestSquareX = x;
                    bestSquareY = y;
                }
            }
        }
        System.out.println("bestSquareValue: " + bestSquareValue + ", coordinates: " + bestSquareX + "," + bestSquareY);
        return bestSquareX + "," + bestSquareY;
    }
}
