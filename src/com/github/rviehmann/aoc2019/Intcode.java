package com.github.rviehmann.aoc2019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Intcode {

    private static final int POSITION_MODE = 0;
    private static final int IMMEDIATE_MODE = 1;

    public static Integer[] interpretIntcode(int[] memory, int[] inputs) throws InterruptedException {
        BlockingQueue<Integer>[] queues = new BlockingQueue[2];
        for (int i = 0; i < 2; i++) {
            queues[i] = new LinkedBlockingQueue<>();
        }
        for (int input : inputs) {
            queues[0].put(input);
        }
        interpretIntcode(memory, queues[0], queues[1]);
        List<Integer> outputs = new ArrayList<>();
        while (!queues[1].isEmpty()) {
            outputs.add(queues[1].take());
        }
        // Memory has been modified by this method. But we also return the outputs (if any).
        return outputs.toArray(new Integer[outputs.size()]);
    }

    public static void interpretIntcode(int[] memory, BlockingQueue<Integer> inputQueue, BlockingQueue<Integer> outputQueue) throws InterruptedException {
        int pc = 0;
        boolean shouldHalt = false;

        while (!shouldHalt) {
            int opcode = memory[pc] % 100;
            int param1Mode = (memory[pc] / 100) % 10;
            int param2Mode = (memory[pc] / 1000) % 10;
            int param3Mode = (memory[pc] / 10000) % 10;

            if (param1Mode != POSITION_MODE && param1Mode != IMMEDIATE_MODE) {
                throw new IllegalArgumentException("Invalid param1Mode: param1Mode=" + param1Mode + ", position=" + pc);
            }
            if (param2Mode != POSITION_MODE && param2Mode != IMMEDIATE_MODE) {
                throw new IllegalArgumentException("Invalid param2Mode: param2Mode=" + param2Mode + ", position=" + pc);
            }
            if (param3Mode != POSITION_MODE && param3Mode != IMMEDIATE_MODE) {
                throw new IllegalArgumentException("Invalid param3Mode: param3Mode=" + param3Mode + ", position=" + pc);
            }

            int input1;
            int input2;
            int output;

            switch (opcode) {
                case 1: // add
                    input1 = (param1Mode == POSITION_MODE) ? memory[memory[pc + 1]] : memory[pc + 1];
                    input2 = (param2Mode == POSITION_MODE) ? memory[memory[pc + 2]] : memory[pc + 2];
                    output = input1 + input2;
                    memory[memory[pc + 3]] = output;
                    pc += 4;
                    break;

                case 2: // multiply
                    input1 = (param1Mode == POSITION_MODE) ? memory[memory[pc + 1]] : memory[pc + 1];
                    input2 = (param2Mode == POSITION_MODE) ? memory[memory[pc + 2]] : memory[pc + 2];
                    output = input1 * input2;
                    memory[memory[pc + 3]] = output;
                    pc += 4;
                    break;

                case 3: // read input
                    input1 = inputQueue.take();
                    memory[memory[pc + 1]] = input1;
                    pc += 2;
                    break;

                case 4: // write output
                    input1 = (param1Mode == POSITION_MODE) ? memory[memory[pc + 1]] : memory[pc + 1];
                    outputQueue.put(input1);
                    pc += 2;
                    break;

                case 5: // jump-if-true
                    input1 = (param1Mode == POSITION_MODE) ? memory[memory[pc + 1]] : memory[pc + 1];
                    input2 = (param2Mode == POSITION_MODE) ? memory[memory[pc + 2]] : memory[pc + 2];
                    if (input1 != 0) {
                        pc = input2;
                    } else {
                        pc += 3;
                    }
                    break;

                case 6: // jump-if-false
                    input1 = (param1Mode == POSITION_MODE) ? memory[memory[pc + 1]] : memory[pc + 1];
                    input2 = (param2Mode == POSITION_MODE) ? memory[memory[pc + 2]] : memory[pc + 2];
                    if (input1 == 0) {
                        pc = input2;
                    } else {
                        pc += 3;
                    }
                    break;

                case 7: // less than
                    input1 = (param1Mode == POSITION_MODE) ? memory[memory[pc + 1]] : memory[pc + 1];
                    input2 = (param2Mode == POSITION_MODE) ? memory[memory[pc + 2]] : memory[pc + 2];
                    output = input1 < input2 ? 1 : 0;
                    memory[memory[pc + 3]] = output;
                    pc += 4;
                    break;

                case 8: // equals
                    input1 = (param1Mode == POSITION_MODE) ? memory[memory[pc + 1]] : memory[pc + 1];
                    input2 = (param2Mode == POSITION_MODE) ? memory[memory[pc + 2]] : memory[pc + 2];
                    output = input1 == input2 ? 1 : 0;
                    memory[memory[pc + 3]] = output;
                    pc += 4;
                    break;

                case 99: // halt
                    shouldHalt = true;
                    break;

                default:
                    throw new IllegalArgumentException("Invalid opcode: position=" + pc + ", opcode=" + opcode);
            }
        }

        // Memory has been modified by this method.
    }
}
