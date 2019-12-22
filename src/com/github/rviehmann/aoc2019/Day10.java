package com.github.rviehmann.aoc2019;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

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

        public boolean hasPosition(long x, long y) {
            return this.x == x && this.y == y;
        }

        /**
         * Calculates the manhattan distance between this and another asteroid.
         *
         * @param other
         * @return
         */
        public long distance(Asteroid other) {
            return abs(other.x - this.x) + abs(other.y - this.y);
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
            long distance = other.distance(this);
            long distanceX = other.x - this.x;
            long distanceY = other.y - this.y;
            for (Asteroid asteroid : asteroidField) {
                if (asteroid.equals(this) || asteroid.equals(other)) {
                    // Is equal to me or my counterpart, therefore can't obstruct view.
                    continue;
                }
                if (other.distance(asteroid) >= distance) {
                    // Is further away from my counterpart than me, therefore can't obstruct view.
                    continue;
                }
                long dstX = other.x - asteroid.x;
                long dstY = other.y - asteroid.y;
                double factorX = distanceX != 0 ? (double) distanceX / (double) dstX : 0;
                double factorY = distanceY != 0 ? (double) distanceY / (double) dstY : 0;
                if (equalsRespectingEpsilon(factorX, factorY)) {
                    // Nearer to my counterpart than me, and on a line between me and my counterpart.
                    return false;
                }
            }
            return true;
        }

        public long countVisibleAsteroids(List<Asteroid> asteroidField) {
            long count = 0;
            for (Asteroid asteroid : asteroidField) {
                if (this.canDetect(asteroid, asteroidField)) {
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
            return this.x == other.x && this.y == other.y;
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
        long visible = 0;
        Asteroid bestLocation = null;
        for (Asteroid asteroid : asteroidField) {
            long vis = asteroid.countVisibleAsteroids(asteroidField);
            if (vis > visible) {
                visible = vis;
                bestLocation = asteroid;
            }
        }
        System.out.println("X: " + bestLocation.x);
        System.out.println("Y: " + bestLocation.y);
        System.out.println("Visible: " + visible);
        return visible;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 10: Examples for puzzle 1 ###");
        findBestLocation(parseAsteroidField(EXAMPLE1));
        findBestLocation(parseAsteroidField(EXAMPLE2));
        findBestLocation(parseAsteroidField(EXAMPLE3));
        findBestLocation(parseAsteroidField(EXAMPLE4));
        findBestLocation(parseAsteroidField(EXAMPLE5));
    }

    public static long doPuzzle1() {
        // TODO
        return 0;
    }

    public static long doPuzzle2() {
        // TODO
        return 0;
    }
}
