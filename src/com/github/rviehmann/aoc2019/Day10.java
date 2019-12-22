package com.github.rviehmann.aoc2019;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;

public class Day10 {

    private final static double EPSILON = 0.00001;

    private static final char ASTEROID = '#';
    private static final char SPACE = ' ';

    private static final String EXAMPLE1 =
            ".#..#\n" +
                    ".....\n" +
                    "#####\n" +
                    "....#\n" +
                    "...##";

    private static final String EXAMPLE2 =
            "......#.#.\n" +
                    "#..#.#....\n" +
                    "..#######.\n" +
                    ".#.#.###..\n" +
                    ".#..#.....\n" +
                    "..#....#.#\n" +
                    "#..#....#.\n" +
                    ".##.#..###\n" +
                    "##...#..#.\n" +
                    ".#....####";

    private static final String EXAMPLE3 =
            "#.#...#.#.\n" +
                    ".###....#.\n" +
                    ".#....#...\n" +
                    "##.#.#.#.#\n" +
                    "....#.#.#.\n" +
                    ".##..###.#\n" +
                    "..#...##..\n" +
                    "..##....##\n" +
                    "......#...\n" +
                    ".####.###.";

    private static final String EXAMPLE4 =
            ".#..#..###\n" +
                    "####.###.#\n" +
                    "....###.#.\n" +
                    "..###.##.#\n" +
                    "##.##.#.#.\n" +
                    "....###..#\n" +
                    "..#.#..#.#\n" +
                    "#..#.#.###\n" +
                    ".##...##.#\n" +
                    ".....#.#..";

    private static final String EXAMPLE5 =
            ".#..##.###...#######\n" +
                    "##.############..##.\n" +
                    ".#.######.########.#\n" +
                    ".###.#######.####.#.\n" +
                    "#####.##.#.##.###.##\n" +
                    "..#####..#.#########\n" +
                    "####################\n" +
                    "#.####....###.#.#.##\n" +
                    "##.#################\n" +
                    "#####.##.###..####..\n" +
                    "..######..##.#######\n" +
                    "####.##.####...##..#\n" +
                    ".#####..#.######.###\n" +
                    "##...#.##########...\n" +
                    "#.##########.#######\n" +
                    ".####.#.###.###.#.##\n" +
                    "....##.##.###..#####\n" +
                    ".#.#.###########.###\n" +
                    "#.#.#.#####.####.###\n" +
                    "###.##.####.##.#..##";

    // From: https://adventofcode.com/2019/day/10/input
    private static final String INPUT =
            "#.#................#..............#......#......\n" +
                    ".......##..#..#....#.#.....##...#.........#.#...\n" +
                    ".#...............#....#.##......................\n" +
                    "......#..####.........#....#.......#..#.....#...\n" +
                    ".....#............#......#................#.#...\n" +
                    "....##...#.#.#.#.............#..#.#.......#.....\n" +
                    "..#.#.........#....#..#.#.........####..........\n" +
                    "....#...#.#...####..#..#..#.....#...............\n" +
                    ".............#......#..........#...........#....\n" +
                    "......#.#.........#...............#.............\n" +
                    "..#......#..#.....##...##.....#....#.#......#...\n" +
                    "...#.......##.........#.#..#......#........#.#..\n" +
                    "#.............#..........#....#.#.....#.........\n" +
                    "#......#.#................#.......#..#.#........\n" +
                    "#..#.#.....#.....###..#.................#..#....\n" +
                    "...............................#..........#.....\n" +
                    "###.#.....#.....#.............#.......#....#....\n" +
                    ".#.....#.........#.....#....#...................\n" +
                    "........#....................#..#...............\n" +
                    ".....#...#.##......#............#......#.....#..\n" +
                    "..#..#..............#..#..#.##........#.........\n" +
                    "..#.#...#.......#....##...#........#...#.#....#.\n" +
                    ".....#.#..####...........#.##....#....#......#..\n" +
                    ".....#..#..##...............................#...\n" +
                    ".#....#..#......#.#............#........##...#..\n" +
                    ".......#.....................#..#....#.....#....\n" +
                    "#......#..###...........#.#....#......#.........\n" +
                    "..............#..#.#...#.......#..#.#...#......#\n" +
                    ".......#...........#.....#...#.............#.#..\n" +
                    "..##..##.............#........#........#........\n" +
                    "......#.............##..#.........#...#.#.#.....\n" +
                    "#........#.........#...#.....#................#.\n" +
                    "...#.#...........#.....#.........#......##......\n" +
                    "..#..#...........#..........#...................\n" +
                    ".........#..#.......................#.#.........\n" +
                    "......#.#.#.....#...........#...............#...\n" +
                    "......#.##...........#....#............#........\n" +
                    "#...........##.#.#........##...........##.......\n" +
                    "......#....#..#.......#.....#.#.......#.##......\n" +
                    ".#....#......#..............#.......#...........\n" +
                    "......##.#..........#..................#........\n" +
                    "......##.##...#..#........#............#........\n" +
                    "..#.....#.................###...#.....###.#..#..\n" +
                    "....##...............#....#..................#..\n" +
                    ".....#................#.#.#.......#..........#..\n" +
                    "#........................#.##..........#....##..\n" +
                    ".#.........#.#.#...#...#....#........#..#.......\n" +
                    "...#..#.#......................#...............#";

    public static class Asteroid {

        public long x;
        public long y;

        public Asteroid(long x, long y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Calculates the distance between this and another asteroid.
         *
         * @param other
         * @return
         */
        public double distance(Asteroid other) {
            double q1 = pow((double) (other.x - x), 2);
            double q2 = pow((double) (other.y - y), 2);
            return sqrt(q1 + q2);
        }

        /**
         * Calculates the angle between this and another asteroid in degrees (360 degrees == 1 circle).
         *
         * @param other
         * @return
         */
        public double angle(Asteroid other) {
            double angle = toDegrees(atan2(other.y - y, other.x - x));
            if (angle < 0) {
                angle += 360;
            }
            return angle;
        }

        public static boolean equalsRespectingEpsilon(double a, double b) {
            return a == b || abs(a - b) < EPSILON;
        }

        /**
         * Determines whether this asteroid can detect the other one, given the whole field.
         *
         * @param other
         * @param asteroidField
         * @return
         */
        public boolean canDetect(Asteroid other, List<Asteroid> asteroidField) {
            if (other.equals(this)) {
                // We can only detect OTHER asteroids, not ourselves (per definition).
                return false;
            }
            double distanceToCounterpart = distance(other);
            double angleToCounterpart = angle(other);
            for (Asteroid asteroid : asteroidField) {
                if (asteroid.equals(this) || asteroid.equals(other)) {
                    // Is equal to me or my counterpart, therefore can't obstruct view.
                    continue;
                }
                if (distance(asteroid) >= distanceToCounterpart) {
                    // Is further away from me than my counterpart, therefore can't obstruct view.
                    continue;
                }
                double angle = angle(asteroid);
                if (equalsRespectingEpsilon(angleToCounterpart, angle)) {
                    // Nearer to me than my counterpart, and on a line between me and my counterpart.
                    return false;
                }
            }
            return true;
        }

        public long countVisibleAsteroids(List<Asteroid> asteroidField) {
            long count = 0;
            for (Asteroid asteroid : asteroidField) {
                if (canDetect(asteroid, asteroidField)) {
                    count++;
                }
            }
            return count;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !obj.getClass().equals(this.getClass())) {
                return false;
            }

            Asteroid other = (Asteroid) obj;
            return x == other.x && y == other.y;
        }
    }

    private static List<Asteroid> parseAsteroidField(String input) {
        String[] lines = input.split("\n");
        List<Asteroid> asteroidField = new ArrayList<>();
        long y = 0;
        for (String line : lines) {
            line = line.trim();
            if (!(line.length() == 0)) {
                long x = 0;
                for (char c : line.toCharArray()) {
                    if (c == ASTEROID) {
                        asteroidField.add(new Asteroid(x, y));
                    }
                    x++;
                }
            }
            y++;
        }
        return asteroidField;
    }

    private static long findBestLocation(List<Asteroid> asteroidField) {
        long bestVis = 0;
        Asteroid bestLocation = null;
        for (Asteroid asteroid : asteroidField) {
            long vis = asteroid.countVisibleAsteroids(asteroidField);
            if (vis > bestVis) {
                bestVis = vis;
                bestLocation = asteroid;
            }
        }
        System.out.println("Asteroid with best visibility: x, y, visible: " + bestLocation.x + ", " + bestLocation.y + ", " + bestVis);
        return bestVis;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 10: Examples for puzzle 1 ###");

        System.out.println("This should yield '3, 4, 8':");
        findBestLocation(parseAsteroidField(EXAMPLE1));

        System.out.println("This should yield '5, 8, 33':");
        findBestLocation(parseAsteroidField(EXAMPLE2));

        System.out.println("This should yield '1, 2, 35':");
        findBestLocation(parseAsteroidField(EXAMPLE3));

        System.out.println("This should yield '6, 3, 41':");
        findBestLocation(parseAsteroidField(EXAMPLE4));

        System.out.println("This should yield '11, 13, 210':");
        findBestLocation(parseAsteroidField(EXAMPLE5));
    }

    public static long doPuzzle1() {
        return findBestLocation(parseAsteroidField(INPUT));
    }

    public static long doPuzzle2() {
        // TODO
        return 0;
    }
}
