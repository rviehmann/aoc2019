package com.github.rviehmann.aoc2020;

public class Day12 {

    private static final String EXAMPLE1 =
            "F10\n" +
                    "N3\n" +
                    "F7\n" +
                    "R90\n" +
                    "F11";

    // From: https://adventofcode.com/2020/day/12/input
    private static final String INPUT =
            "S3\n" +
                    "R90\n" +
                    "E1\n" +
                    "S3\n" +
                    "E5\n" +
                    "S4\n" +
                    "E5\n" +
                    "N3\n" +
                    "W1\n" +
                    "N3\n" +
                    "F91\n" +
                    "W1\n" +
                    "F49\n" +
                    "W1\n" +
                    "F7\n" +
                    "R90\n" +
                    "F13\n" +
                    "S1\n" +
                    "F87\n" +
                    "L90\n" +
                    "N5\n" +
                    "R90\n" +
                    "E1\n" +
                    "F69\n" +
                    "N4\n" +
                    "F80\n" +
                    "E2\n" +
                    "F15\n" +
                    "N5\n" +
                    "F15\n" +
                    "N4\n" +
                    "E3\n" +
                    "N2\n" +
                    "R90\n" +
                    "E4\n" +
                    "N5\n" +
                    "F71\n" +
                    "W3\n" +
                    "S4\n" +
                    "R270\n" +
                    "F39\n" +
                    "N1\n" +
                    "F100\n" +
                    "R90\n" +
                    "F52\n" +
                    "S2\n" +
                    "W3\n" +
                    "L90\n" +
                    "S1\n" +
                    "F79\n" +
                    "E5\n" +
                    "R90\n" +
                    "F94\n" +
                    "E3\n" +
                    "F87\n" +
                    "N5\n" +
                    "R90\n" +
                    "F50\n" +
                    "W5\n" +
                    "N4\n" +
                    "W4\n" +
                    "S1\n" +
                    "W2\n" +
                    "R180\n" +
                    "W4\n" +
                    "F6\n" +
                    "W2\n" +
                    "N1\n" +
                    "W4\n" +
                    "F89\n" +
                    "L90\n" +
                    "E3\n" +
                    "R90\n" +
                    "F73\n" +
                    "W1\n" +
                    "N4\n" +
                    "F77\n" +
                    "E4\n" +
                    "F42\n" +
                    "N3\n" +
                    "E5\n" +
                    "R180\n" +
                    "F51\n" +
                    "E2\n" +
                    "F22\n" +
                    "N4\n" +
                    "F95\n" +
                    "L90\n" +
                    "E2\n" +
                    "F41\n" +
                    "L90\n" +
                    "F61\n" +
                    "L180\n" +
                    "E1\n" +
                    "R90\n" +
                    "N2\n" +
                    "F32\n" +
                    "E5\n" +
                    "L90\n" +
                    "L180\n" +
                    "E3\n" +
                    "F86\n" +
                    "S3\n" +
                    "L180\n" +
                    "E3\n" +
                    "F76\n" +
                    "W1\n" +
                    "F62\n" +
                    "S2\n" +
                    "F23\n" +
                    "R90\n" +
                    "F60\n" +
                    "E2\n" +
                    "F47\n" +
                    "L90\n" +
                    "S1\n" +
                    "L270\n" +
                    "N3\n" +
                    "N2\n" +
                    "W1\n" +
                    "S4\n" +
                    "S1\n" +
                    "E5\n" +
                    "F43\n" +
                    "S4\n" +
                    "R90\n" +
                    "S5\n" +
                    "N3\n" +
                    "F100\n" +
                    "W5\n" +
                    "R90\n" +
                    "S2\n" +
                    "F30\n" +
                    "E1\n" +
                    "R180\n" +
                    "R90\n" +
                    "F1\n" +
                    "R90\n" +
                    "F32\n" +
                    "N1\n" +
                    "W3\n" +
                    "R180\n" +
                    "F9\n" +
                    "N1\n" +
                    "L90\n" +
                    "E1\n" +
                    "R90\n" +
                    "W5\n" +
                    "L180\n" +
                    "N4\n" +
                    "F57\n" +
                    "F53\n" +
                    "N4\n" +
                    "E5\n" +
                    "R90\n" +
                    "E1\n" +
                    "F32\n" +
                    "R90\n" +
                    "E5\n" +
                    "L180\n" +
                    "E1\n" +
                    "L90\n" +
                    "W4\n" +
                    "S2\n" +
                    "F77\n" +
                    "N2\n" +
                    "R90\n" +
                    "S2\n" +
                    "F68\n" +
                    "S4\n" +
                    "R90\n" +
                    "E3\n" +
                    "F66\n" +
                    "R90\n" +
                    "F85\n" +
                    "S1\n" +
                    "F47\n" +
                    "F25\n" +
                    "R90\n" +
                    "N1\n" +
                    "F65\n" +
                    "R270\n" +
                    "L270\n" +
                    "F90\n" +
                    "S3\n" +
                    "F33\n" +
                    "W5\n" +
                    "N1\n" +
                    "F19\n" +
                    "E1\n" +
                    "L90\n" +
                    "F72\n" +
                    "L90\n" +
                    "F67\n" +
                    "E3\n" +
                    "R90\n" +
                    "E4\n" +
                    "R90\n" +
                    "F6\n" +
                    "S2\n" +
                    "W5\n" +
                    "F43\n" +
                    "W1\n" +
                    "F3\n" +
                    "L270\n" +
                    "N3\n" +
                    "L90\n" +
                    "E5\n" +
                    "N3\n" +
                    "F29\n" +
                    "E4\n" +
                    "R90\n" +
                    "E4\n" +
                    "L90\n" +
                    "F32\n" +
                    "E5\n" +
                    "F52\n" +
                    "N4\n" +
                    "L270\n" +
                    "N1\n" +
                    "W5\n" +
                    "R90\n" +
                    "W5\n" +
                    "N5\n" +
                    "E3\n" +
                    "N5\n" +
                    "W2\n" +
                    "L180\n" +
                    "N2\n" +
                    "E1\n" +
                    "S4\n" +
                    "N1\n" +
                    "W4\n" +
                    "F8\n" +
                    "N2\n" +
                    "W2\n" +
                    "F80\n" +
                    "N3\n" +
                    "F98\n" +
                    "L90\n" +
                    "W3\n" +
                    "L90\n" +
                    "S2\n" +
                    "L90\n" +
                    "F98\n" +
                    "L180\n" +
                    "F12\n" +
                    "E3\n" +
                    "N2\n" +
                    "F60\n" +
                    "N5\n" +
                    "W1\n" +
                    "F54\n" +
                    "W3\n" +
                    "F74\n" +
                    "E4\n" +
                    "S3\n" +
                    "R90\n" +
                    "F94\n" +
                    "L90\n" +
                    "S2\n" +
                    "L90\n" +
                    "N4\n" +
                    "E2\n" +
                    "F83\n" +
                    "R90\n" +
                    "N2\n" +
                    "E1\n" +
                    "F86\n" +
                    "E5\n" +
                    "N1\n" +
                    "R90\n" +
                    "E5\n" +
                    "R180\n" +
                    "F72\n" +
                    "L180\n" +
                    "W5\n" +
                    "L90\n" +
                    "E4\n" +
                    "L90\n" +
                    "E1\n" +
                    "F26\n" +
                    "W4\n" +
                    "F4\n" +
                    "L90\n" +
                    "W4\n" +
                    "L90\n" +
                    "E5\n" +
                    "F92\n" +
                    "R90\n" +
                    "N1\n" +
                    "L90\n" +
                    "N5\n" +
                    "F21\n" +
                    "L90\n" +
                    "L180\n" +
                    "W3\n" +
                    "F76\n" +
                    "L90\n" +
                    "F14\n" +
                    "L90\n" +
                    "E5\n" +
                    "F50\n" +
                    "S5\n" +
                    "F18\n" +
                    "W5\n" +
                    "F27\n" +
                    "S5\n" +
                    "F52\n" +
                    "L180\n" +
                    "N3\n" +
                    "L180\n" +
                    "W2\n" +
                    "N5\n" +
                    "W3\n" +
                    "S2\n" +
                    "F48\n" +
                    "W2\n" +
                    "F57\n" +
                    "R90\n" +
                    "E3\n" +
                    "R180\n" +
                    "E4\n" +
                    "F42\n" +
                    "W2\n" +
                    "F21\n" +
                    "E2\n" +
                    "S4\n" +
                    "E2\n" +
                    "N4\n" +
                    "W4\n" +
                    "S4\n" +
                    "E3\n" +
                    "F23\n" +
                    "S1\n" +
                    "W5\n" +
                    "F70\n" +
                    "S5\n" +
                    "F77\n" +
                    "W5\n" +
                    "S3\n" +
                    "L180\n" +
                    "E4\n" +
                    "L90\n" +
                    "W2\n" +
                    "L90\n" +
                    "E1\n" +
                    "F15\n" +
                    "S4\n" +
                    "F38\n" +
                    "N1\n" +
                    "L90\n" +
                    "F27\n" +
                    "N1\n" +
                    "F34\n" +
                    "L90\n" +
                    "E1\n" +
                    "S2\n" +
                    "E2\n" +
                    "L90\n" +
                    "N3\n" +
                    "F71\n" +
                    "W4\n" +
                    "S4\n" +
                    "F14\n" +
                    "R90\n" +
                    "F58\n" +
                    "R90\n" +
                    "F8\n" +
                    "R90\n" +
                    "E1\n" +
                    "L270\n" +
                    "F12\n" +
                    "E2\n" +
                    "L90\n" +
                    "F9\n" +
                    "R90\n" +
                    "S5\n" +
                    "F48\n" +
                    "S5\n" +
                    "L90\n" +
                    "E1\n" +
                    "F7\n" +
                    "W2\n" +
                    "F97\n" +
                    "S1\n" +
                    "R90\n" +
                    "N5\n" +
                    "E2\n" +
                    "N4\n" +
                    "F20\n" +
                    "R90\n" +
                    "N1\n" +
                    "R90\n" +
                    "F84\n" +
                    "R90\n" +
                    "E3\n" +
                    "S3\n" +
                    "F34\n" +
                    "S3\n" +
                    "F41\n" +
                    "R90\n" +
                    "F15\n" +
                    "L180\n" +
                    "N1\n" +
                    "F4\n" +
                    "L180\n" +
                    "W3\n" +
                    "F50\n" +
                    "S4\n" +
                    "W3\n" +
                    "F45\n" +
                    "W1\n" +
                    "S4\n" +
                    "E2\n" +
                    "L90\n" +
                    "E5\n" +
                    "R270\n" +
                    "S3\n" +
                    "L90\n" +
                    "S4\n" +
                    "F59\n" +
                    "W4\n" +
                    "R180\n" +
                    "F10\n" +
                    "E1\n" +
                    "R90\n" +
                    "S4\n" +
                    "W5\n" +
                    "L90\n" +
                    "F45\n" +
                    "S4\n" +
                    "F92\n" +
                    "R90\n" +
                    "E5\n" +
                    "F36\n" +
                    "S1\n" +
                    "L90\n" +
                    "W1\n" +
                    "S5\n" +
                    "F30\n" +
                    "W5\n" +
                    "F80\n" +
                    "N4\n" +
                    "E2\n" +
                    "R90\n" +
                    "F98\n" +
                    "F7\n" +
                    "S5\n" +
                    "W2\n" +
                    "F1\n" +
                    "N3\n" +
                    "L180\n" +
                    "N2\n" +
                    "F49\n" +
                    "E3\n" +
                    "S5\n" +
                    "S5\n" +
                    "W4\n" +
                    "E5\n" +
                    "E3\n" +
                    "S1\n" +
                    "W1\n" +
                    "F10\n" +
                    "L90\n" +
                    "F40\n" +
                    "E4\n" +
                    "N4\n" +
                    "F25\n" +
                    "R180\n" +
                    "W5\n" +
                    "N5\n" +
                    "W1\n" +
                    "S4\n" +
                    "L90\n" +
                    "F79\n" +
                    "L180\n" +
                    "S1\n" +
                    "F100\n" +
                    "S1\n" +
                    "F34\n" +
                    "S2\n" +
                    "E4\n" +
                    "L90\n" +
                    "N2\n" +
                    "F36\n" +
                    "E1\n" +
                    "N3\n" +
                    "L90\n" +
                    "F42\n" +
                    "R90\n" +
                    "W5\n" +
                    "L270\n" +
                    "F45\n" +
                    "E4\n" +
                    "F95\n" +
                    "N5\n" +
                    "F22\n" +
                    "L90\n" +
                    "F67\n" +
                    "R180\n" +
                    "S5\n" +
                    "E5\n" +
                    "R90\n" +
                    "N1\n" +
                    "R180\n" +
                    "W3\n" +
                    "F80\n" +
                    "E4\n" +
                    "F19\n" +
                    "W5\n" +
                    "N4\n" +
                    "R90\n" +
                    "N5\n" +
                    "F28\n" +
                    "F86\n" +
                    "R180\n" +
                    "R90\n" +
                    "S2\n" +
                    "E1\n" +
                    "N5\n" +
                    "W1\n" +
                    "S3\n" +
                    "W5\n" +
                    "S3\n" +
                    "F51\n" +
                    "W5\n" +
                    "S3\n" +
                    "R90\n" +
                    "R90\n" +
                    "F35\n" +
                    "W2\n" +
                    "F51\n" +
                    "N1\n" +
                    "R180\n" +
                    "S2\n" +
                    "F37\n" +
                    "R270\n" +
                    "W2\n" +
                    "S2\n" +
                    "L90\n" +
                    "L180\n" +
                    "E4\n" +
                    "N1\n" +
                    "L180\n" +
                    "E2\n" +
                    "S1\n" +
                    "F38\n" +
                    "R90\n" +
                    "N2\n" +
                    "W1\n" +
                    "R90\n" +
                    "L180\n" +
                    "E4\n" +
                    "S3\n" +
                    "R180\n" +
                    "E3\n" +
                    "S4\n" +
                    "F65\n" +
                    "E2\n" +
                    "R90\n" +
                    "N1\n" +
                    "E4\n" +
                    "L180\n" +
                    "E5\n" +
                    "R90\n" +
                    "W2\n" +
                    "F22\n" +
                    "W5\n" +
                    "N1\n" +
                    "R90\n" +
                    "E1\n" +
                    "F34\n" +
                    "S1\n" +
                    "R90\n" +
                    "N3\n" +
                    "F62\n" +
                    "F48\n" +
                    "E5\n" +
                    "F85\n" +
                    "E5\n" +
                    "S4\n" +
                    "R90\n" +
                    "E3\n" +
                    "R90\n" +
                    "E1\n" +
                    "F54\n" +
                    "S4\n" +
                    "W3\n" +
                    "W2\n" +
                    "R90\n" +
                    "N5\n" +
                    "W4\n" +
                    "N3\n" +
                    "R180\n" +
                    "E1\n" +
                    "F88\n" +
                    "W5\n" +
                    "F49\n" +
                    "E5\n" +
                    "N4\n" +
                    "W3\n" +
                    "F81\n" +
                    "R90\n" +
                    "F58\n" +
                    "N4\n" +
                    "L90\n" +
                    "F88\n" +
                    "N3\n" +
                    "F87\n" +
                    "E2\n" +
                    "N4\n" +
                    "L90\n" +
                    "N5\n" +
                    "N1\n" +
                    "F87\n" +
                    "E1\n" +
                    "L90\n" +
                    "N3\n" +
                    "W1\n" +
                    "F13\n" +
                    "F3\n" +
                    "N4\n" +
                    "L90\n" +
                    "E3\n" +
                    "S1\n" +
                    "F35\n" +
                    "S4\n" +
                    "F1\n" +
                    "L90\n" +
                    "E1\n" +
                    "N4\n" +
                    "R90\n" +
                    "F60\n" +
                    "N2\n" +
                    "W1\n" +
                    "N1\n" +
                    "R180\n" +
                    "L180\n" +
                    "S3\n" +
                    "R90\n" +
                    "E4\n" +
                    "R180\n" +
                    "W3\n" +
                    "N1\n" +
                    "W1\n" +
                    "F3\n" +
                    "L90\n" +
                    "F8\n" +
                    "W1\n" +
                    "R90\n" +
                    "S1\n" +
                    "F84\n" +
                    "L90\n" +
                    "S3\n" +
                    "F63\n" +
                    "F11\n" +
                    "E1\n" +
                    "F38\n" +
                    "R90\n" +
                    "S3\n" +
                    "R270\n" +
                    "F50\n" +
                    "R90\n" +
                    "F15\n" +
                    "L270\n" +
                    "E1\n" +
                    "S1\n" +
                    "E5\n" +
                    "L180\n" +
                    "N4\n" +
                    "L90\n" +
                    "F37\n" +
                    "N3\n" +
                    "E5\n" +
                    "F13\n" +
                    "L180\n" +
                    "E5\n" +
                    "F88\n" +
                    "E2\n" +
                    "F9\n" +
                    "W3\n" +
                    "F6\n" +
                    "N5\n" +
                    "F75\n" +
                    "N5\n" +
                    "W5\n" +
                    "S5\n" +
                    "E3\n" +
                    "R90\n" +
                    "N5\n" +
                    "E4\n" +
                    "N3\n" +
                    "L180\n" +
                    "F1\n" +
                    "R90\n" +
                    "S3\n" +
                    "E1\n" +
                    "S4\n" +
                    "E5\n" +
                    "N3\n" +
                    "R270\n" +
                    "E3\n" +
                    "L90\n" +
                    "N3\n" +
                    "S2\n" +
                    "L90\n" +
                    "S5\n" +
                    "F90\n" +
                    "R90\n" +
                    "N1\n" +
                    "R90\n" +
                    "F50\n" +
                    "F33\n" +
                    "W3\n" +
                    "F23\n" +
                    "E3\n" +
                    "R90\n" +
                    "F81\n" +
                    "E1\n" +
                    "N3\n" +
                    "R180\n" +
                    "F16\n" +
                    "S2\n" +
                    "F35\n" +
                    "R90\n" +
                    "W5\n" +
                    "F31\n" +
                    "R90\n" +
                    "S2\n" +
                    "L180\n" +
                    "E5\n" +
                    "R180\n" +
                    "F89\n" +
                    "N1\n" +
                    "L90\n" +
                    "F68\n" +
                    "W3\n" +
                    "R90\n" +
                    "W3\n" +
                    "N1\n" +
                    "L90\n" +
                    "N4\n" +
                    "L180\n" +
                    "S5\n" +
                    "R180\n" +
                    "N4\n" +
                    "R90\n" +
                    "W5\n" +
                    "N5\n" +
                    "L180\n" +
                    "N2\n" +
                    "E4\n" +
                    "N1\n" +
                    "W4\n" +
                    "F98\n" +
                    "S5\n" +
                    "F70\n" +
                    "W5\n" +
                    "F76";

    private static final String[] EXAMPLE1_AS_ARRAY = EXAMPLE1.split("\\R");
    private static final String[] INPUT_AS_ARRAY = INPUT.split("\\R");

    public static class ShipPuzzle1 {
        /**
         * Increases to the East, decreases to the West.
         */
        long posX = 0;

        /**
         * Increases to the South, decreases to the North.
         */
        long posY = 0;

        /**
         * Heading in degrees (>=0, <360, heading increases clockwise, 0==North)
         */
        int heading = 90; // The ship starts by facing east

        public long manhattanDistance() {
            return Math.abs(posX) + Math.abs(posY);
        }

        public void navigateOneStep(String step) {
            char command = step.charAt(0);
            long value = Long.parseLong(step.substring(1));

            switch (command) {
                case 'N':
                    posY -= value;
                    break;

                case 'S':
                    posY += value;
                    break;

                case 'E':
                    posX += value;
                    break;

                case 'W':
                    posX -= value;
                    break;

                case 'L':
                    heading -= value;
                    if (heading < 0) {
                        heading += 360;
                    }
                    break;

                case 'R':
                    heading += value;
                    if (heading >= 360) {
                        heading -= 360;
                    }
                    break;

                case 'F':
                    switch (heading) {
                        case 0:
                            posY -= value;
                            break;

                        case 90:
                            posX += value;
                            break;

                        case 180:
                            posY += value;
                            break;

                        case 270:
                            posX -= value;
                            break;

                        default:
                            throw new IllegalStateException("Heading is not a useful value:" + heading);
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Command not recognized: " + command);
            }
        }
    }

    public static class ShipPuzzle2 {
        /**
         * Increases to the East, decreases to the West.
         */
        long posX = 0;

        /**
         * Increases to the South, decreases to the North.
         */
        long posY = 0;

        /**
         * The waypoint starts 10 units east and 1 unit north relative to the ship.
         */
        long waypointX = 10;

        /**
         * The waypoint starts 10 units east and 1 unit north relative to the ship.
         */
        long waypointY = -1;

        public long manhattanDistance() {
            return Math.abs(posX) + Math.abs(posY);
        }

        public void navigateOneStep(String step) {
            char command = step.charAt(0);
            long value = Long.parseLong(step.substring(1));
            long rotations;
            long tmpWaypointVar;

            switch (command) {
                case 'N':
                    waypointY -= value;
                    break;

                case 'S':
                    waypointY += value;
                    break;

                case 'E':
                    waypointX += value;
                    break;

                case 'W':
                    waypointX -= value;
                    break;

                case 'L':
                    rotations = value / 90;
                    for (int i = 0; i < rotations; i++) {
                        tmpWaypointVar = waypointX;
                        waypointX = waypointY;
                        waypointY = -tmpWaypointVar;
                    }
                    break;

                case 'R':
                    rotations = value / 90;
                    for (int i = 0; i < rotations; i++) {
                        tmpWaypointVar = waypointY;
                        waypointY = waypointX;
                        waypointX = -tmpWaypointVar;
                    }
                    break;

                case 'F':
                    posX += (waypointX * value);
                    posY += (waypointY * value);
                    break;

                default:
                    throw new IllegalArgumentException("Command not recognized: " + command);
            }
        }
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 12: Examples for puzzle 1 ###");
        ShipPuzzle1 shipPuzzle1 = new ShipPuzzle1();
        for (String step : EXAMPLE1_AS_ARRAY) {
            shipPuzzle1.navigateOneStep(step);
        }
        System.out.println("Manhattan distance in example 1: " + shipPuzzle1.manhattanDistance());
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 12: Examples for puzzle 2 ###");
        ShipPuzzle2 shipPuzzle2 = new ShipPuzzle2();
        for (String step : EXAMPLE1_AS_ARRAY) {
            shipPuzzle2.navigateOneStep(step);
        }
        System.out.println("Manhattan distance in example 2: " + shipPuzzle2.manhattanDistance());
    }

    public static long doPuzzle1() {
        ShipPuzzle1 shipPuzzle1 = new ShipPuzzle1();
        for (String step : INPUT_AS_ARRAY) {
            shipPuzzle1.navigateOneStep(step);
        }
        return shipPuzzle1.manhattanDistance();
    }

    public static long doPuzzle2() {
        ShipPuzzle2 shipPuzzle2 = new ShipPuzzle2();
        for (String step : INPUT_AS_ARRAY) {
            shipPuzzle2.navigateOneStep(step);
        }
        return shipPuzzle2.manhattanDistance();
    }
}
