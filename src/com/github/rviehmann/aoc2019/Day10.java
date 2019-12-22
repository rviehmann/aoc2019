package com.github.rviehmann.aoc2019;

import java.util.List;

import static java.lang.Math.abs;

public class Day10 {

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
                if (factorX == factorY) {
                    // Nearer to my counterpart than me, and on a line between me and my counterpart.
                    return false;
                }
            }
            return true;
        }

        public long countVisibleAsteroids(Asteroid me, List<Asteroid> asteroidField) {
            long count = 0;
            for (Asteroid asteroid : asteroidField) {
                if (me.canDetect(asteroid, asteroidField)) {
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
}
