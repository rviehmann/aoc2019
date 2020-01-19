package com.github.rviehmann.aoc2019;

import com.github.rviehmann.aoc2019.Intcode.Memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.github.rviehmann.aoc2019.Intcode.BLOCKING_INPUT;
import static com.github.rviehmann.aoc2019.Intcode.interpretIntcode;

public class Day11 {

    // From: https://adventofcode.com/2019/day/11/input
    private static final long[] MEMORY = {
            3, 8, 1005, 8, 338, 1106, 0, 11, 0, 0, 0, 104, 1, 104, 0, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1002, 8, 1, 29, 2, 105, 19, 10, 1006, 0, 52, 1, 1009, 7, 10, 1006, 0, 6, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1001, 8, 0, 64, 2, 1002, 19, 10, 1, 8, 13, 10, 1, 1108, 16, 10, 2, 1003, 1, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1002, 8, 1, 103, 1006, 0, 10, 2, 109, 16, 10, 1, 102, 11, 10, 2, 6, 13, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 140, 2, 102, 8, 10, 2, 4, 14, 10, 1, 8, 19, 10, 1006, 0, 24, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1001, 8, 0, 177, 1006, 0, 16, 1, 1007, 17, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 101, 0, 8, 205, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 102, 1, 8, 228, 1, 1005, 1, 10, 1, 9, 1, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1002, 8, 1, 258, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1, 8, 279, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1, 8, 301, 1, 3, 17, 10, 2, 7, 14, 10, 2, 6, 18, 10, 1, 1001, 17, 10, 101, 1, 9, 9, 1007, 9, 1088, 10, 1005, 10, 15, 99, 109, 660, 104, 0, 104, 1, 21102, 1, 48092525312L, 1, 21101, 355, 0, 0, 1106, 0, 459, 21102, 665750184716L, 1, 1, 21102, 366, 1, 0, 1106, 0, 459, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 21102, 1, 235324768296L, 1, 21101, 0, 413, 0, 1105, 1, 459, 21101, 3263212736L, 0, 1, 21102, 424, 1, 0, 1106, 0, 459, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 0, 21102, 1, 709496824676L, 1, 21101, 447, 0, 0, 1105, 1, 459, 21102, 988220904204L, 1, 1, 21102, 1, 458, 0, 1106, 0, 459, 99, 109, 2, 21201, -1, 0, 1, 21102, 40, 1, 2, 21102, 490, 1, 3, 21102, 1, 480, 0, 1105, 1, 523, 109, -2, 2106, 0, 0, 0, 1, 0, 0, 1, 109, 2, 3, 10, 204, -1, 1001, 485, 486, 501, 4, 0, 1001, 485, 1, 485, 108, 4, 485, 10, 1006, 10, 517, 1101, 0, 0, 485, 109, -2, 2105, 1, 0, 0, 109, 4, 2101, 0, -1, 522, 1207, -3, 0, 10, 1006, 10, 540, 21102, 0, 1, -3, 22101, 0, -3, 1, 22102, 1, -2, 2, 21102, 1, 1, 3, 21101, 559, 0, 0, 1106, 0, 564, 109, -4, 2105, 1, 0, 109, 5, 1207, -3, 1, 10, 1006, 10, 587, 2207, -4, -2, 10, 1006, 10, 587, 22102, 1, -4, -4, 1105, 1, 655, 22101, 0, -4, 1, 21201, -3, -1, 2, 21202, -2, 2, 3, 21102, 606, 1, 0, 1105, 1, 564, 21202, 1, 1, -4, 21101, 0, 1, -1, 2207, -4, -2, 10, 1006, 10, 625, 21102, 0, 1, -1, 22202, -2, -1, -2, 2107, 0, -3, 10, 1006, 10, 647, 22101, 0, -1, 1, 21101, 647, 0, 0, 105, 1, 522, 21202, -2, -1, -2, 22201, -4, -2, -4, 109, -5, 2106, 0, 0
    };

    private static final int BLACK = 0;
    private static final int WHITE = 1;

    // As per requirements
    // private static final char BLACK_CHAR = '#';
    // private static final char WHITE_CHAR = '.';

    // My choice
    private static final char BLACK_CHAR = '█';
    private static final char WHITE_CHAR = ' ';

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    // Not in the requirements, just to get the HullPaintingRobot to shut itself down cleanly.
    private static final long SHUT_DOWN_COMMAND = Long.MAX_VALUE;

    public static class RobotControlComputer implements Runnable {

        private final Memory memory;
        private final BlockingQueue<Long> inputQueue;
        private final BlockingQueue<Long> outputQueue;

        public RobotControlComputer(Memory memory, BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue) {
            this.memory = memory;
            this.inputQueue = inputQueue;
            this.outputQueue = outputQueue;
        }

        @Override
        public void run() {
            try {
                interpretIntcode(memory, inputQueue, outputQueue, BLOCKING_INPUT, null);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException caught: " + e);
            }
        }
    }

    public static class PaintedPanel {

        public final long x;
        public final long y;
        public final long color;

        public PaintedPanel(long x, long y, long color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public boolean hasPosition(long x, long y) {
            return this.x == x && this.y == y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !obj.getClass().equals(this.getClass())) {
                return false;
            }

            PaintedPanel other = (PaintedPanel) obj;
            // Yes, it is intentional that we only compare the coordinates, but not the color. Otherwise, getPaintedPanelsMinified() would not work at all.
            return x == other.x && y == other.y;
        }
    }

    public static class HullPaintingRobot implements Runnable {

        private long positionX = 0; // Higher value == further to the right
        private long positionY = 0; // Higher value == further down
        private int heading = NORTH;

        private long leftMost = 0;
        private long rightMost = 0;
        private long topMost = 0;
        private long bottomMost = 0;

        private final List<PaintedPanel> paintedPanels = new ArrayList<>();
        private final BlockingQueue<Long> inputQueue;
        private final BlockingQueue<Long> outputQueue;

        public HullPaintingRobot(BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue, long startingPanelColor) {
            this.inputQueue = inputQueue;
            this.outputQueue = outputQueue;

            if (startingPanelColor != BLACK) {
                paintCurrentField(startingPanelColor);
            }
        }

        public long getPositionX() {
            return positionX;
        }

        public long getPositionY() {
            return positionY;
        }

        public int getHeading() {
            return heading;
        }

        public long getLeftMost() {
            return leftMost;
        }

        public long getRightMost() {
            return rightMost;
        }

        public long getTopMost() {
            return topMost;
        }

        public long getBottomMost() {
            return bottomMost;
        }

        public void debugWholePlayingArea() {
            StringBuilder sb = new StringBuilder();
            for (long y = topMost; y <= bottomMost; y++) {
                for (long x = leftMost; x <= rightMost; x++) {
                    long color = getColorOfPanel(x, y);
                    if (color == BLACK) {
                        sb.append(BLACK_CHAR);
                    } else if (color == WHITE) {
                        sb.append(WHITE_CHAR);
                    } else {
                        throw new IllegalArgumentException("Invalid color: color=" + color);
                    }
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());
        }

        private void paintCurrentField(long color) {
            paintedPanels.add(new PaintedPanel(positionX, positionY, color));
        }

        private void rotateCounterClockwise() {
            heading = (heading + 3) % 4;
        }

        private void rotateClockwise() {
            heading = (heading + 1) % 4;
        }

        private void moveForward() {
            switch (heading) {
                case NORTH:
                    positionY--;
                    if (positionY < topMost) {
                        topMost = positionY;
                    }
                    break;
                case EAST:
                    positionX++;
                    if (positionX > rightMost) {
                        rightMost = positionX;
                    }
                    break;
                case SOUTH:
                    positionY++;
                    if (positionY > bottomMost) {
                        bottomMost = positionY;
                    }
                    break;
                case WEST:
                    positionX--;
                    if (positionX < leftMost) {
                        leftMost = positionX;
                    }
                    break;
            }
        }

        private long getColorOfCurrentPanel() {
            return getColorOfPanel(positionX, positionY);
        }

        private long getColorOfPanel(long x, long y) {
            if (paintedPanels.size() == 0) {
                return BLACK;
            } else {
                // Iterate from the end, since if we paint the same panel more than once, only the last color will be visible.
                for (int index = paintedPanels.size() - 1; index >= 0; index--) {
                    PaintedPanel panel = paintedPanels.get(index);
                    if (panel.hasPosition(x, y)) {
                        return panel.color;
                    }
                }
                return BLACK;
            }
        }

        public List<PaintedPanel> getPaintedPanels() {
            return paintedPanels;
        }

        public List<PaintedPanel> getPaintedPanelsMinified() {
            List<PaintedPanel> uniquePanels = new ArrayList<>();

            // Iterate from the end, since if we paint the same panel more than once, only the last color will be visible.
            for (int index = paintedPanels.size() - 1; index >= 0; index--) {
                PaintedPanel panel = paintedPanels.get(index);
                if (!uniquePanels.contains(panel)) {
                    uniquePanels.add(panel);
                }
            }

            Collections.reverse(uniquePanels);
            return uniquePanels;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    outputQueue.put(getColorOfCurrentPanel());

                    long color = inputQueue.take();
                    if (color == SHUT_DOWN_COMMAND) {
                        return;
                    }

                    paintCurrentField(color);

                    long direction = inputQueue.take();
                    if (direction == SHUT_DOWN_COMMAND) {
                        return;
                    }

                    if (direction == 0) {
                        rotateCounterClockwise();
                    } else if (direction == 1) {
                        rotateClockwise();
                    } else {
                        throw new IllegalArgumentException("Invalid direction: direction=" + direction);
                    }

                    moveForward();
                } catch (InterruptedException e) {
                    System.err.println("InterruptedException caught: " + e);
                }
            }
        }
    }

    private static long runRobotAndCountPanelsMinified(long startingPanelColor) throws InterruptedException {
        BlockingQueue<Long>[] queues = new BlockingQueue[2];
        for (int i = 0; i < 2; i++) {
            queues[i] = new LinkedBlockingQueue<>();
        }
        Thread[] threads = new Thread[2];
        Memory memory = new Memory(MEMORY);
        RobotControlComputer robotControlComputer = new RobotControlComputer(memory, queues[0], queues[1]);
        HullPaintingRobot hullPaintingRobot = new HullPaintingRobot(queues[1], queues[0], startingPanelColor);
        threads[0] = new Thread(robotControlComputer);
        threads[1] = new Thread(hullPaintingRobot);

        for (int i = 0; i < 2; i++) {
            threads[i].start();
        }

        // Wait until RobotControlComputer is finished.
        threads[0].join();

        // Make sure that HullPaintingRobot is also completely finished with everything.
        Thread.sleep(50);

        // Afterwards, shut it down.
        queues[1].put(SHUT_DOWN_COMMAND);

        List<PaintedPanel> paintedPanelsMinified = hullPaintingRobot.getPaintedPanelsMinified();

        System.out.println("positionX: " + hullPaintingRobot.getPositionX());
        System.out.println("positionY: " + hullPaintingRobot.getPositionY());
        System.out.println("heading: " + hullPaintingRobot.getHeading());
        System.out.println("leftMost: " + hullPaintingRobot.getLeftMost());
        System.out.println("rightMost: " + hullPaintingRobot.getRightMost());
        System.out.println("topMost: " + hullPaintingRobot.getTopMost());
        System.out.println("bottomMost: " + hullPaintingRobot.getBottomMost());
        System.out.println("paintedPanels: " + hullPaintingRobot.getPaintedPanels().size());
        System.out.println("paintedPanelsMinified: " + paintedPanelsMinified.size());
        hullPaintingRobot.debugWholePlayingArea();

        return paintedPanelsMinified.size();
    }

    public static long doPuzzle1() throws InterruptedException {
        return runRobotAndCountPanelsMinified(BLACK);
    }

    public static long doPuzzle2() throws InterruptedException {
        return runRobotAndCountPanelsMinified(WHITE);
    }
}
