package com.github.rviehmann.aoc2019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Math.toIntExact;

public class Intcode {

    private static final int POSITION_MODE = 0;
    private static final int IMMEDIATE_MODE = 1;
    private static final int RELATIVE_MODE = 2;
    private static final List<Integer> MODES = new ArrayList<>();

    public static final int BLOCKING_INPUT = 0;
    public static final int NONBLOCKING_INPUT = 1;
    public static final int NO_INPUT_AVAILABLE = -1;

    static {
        MODES.add(POSITION_MODE);
        MODES.add(IMMEDIATE_MODE);
        MODES.add(RELATIVE_MODE);
    }

    public static class ShutdownRequest {
        private volatile boolean mustShutdown = false;

        public void setMustShutdown(boolean mustShutdown) {
            this.mustShutdown = mustShutdown;
        }

        public boolean isMustShutdown() {
            return mustShutdown;
        }
    }

    public static class Memory {
        private long[] memory;

        public Memory(long[] memory) {
            // Make sure that outside actors can't ever manipulate our internal memory.
            long[] newMemory = new long[memory.length];
            System.arraycopy(memory, 0, newMemory, 0, memory.length);
            this.memory = newMemory;
        }

        public long getRaw(int rawAddress) {
            growIfNecessary(rawAddress);
            return memory[rawAddress];
        }

        public long[] getRaw() {
            // Use with lots of caution (and only if it can't easily be avoided).
            return memory;
        }

        public Memory getClone() {
            // The constructor already makes sure that the clone is independent.
            return new Memory(memory);
        }

        public void setRaw(int rawAddress, long value) {
            growIfNecessary(rawAddress);
            memory[rawAddress] = value;
        }

        public long read(int pc, int numParam, int mode, int relativeBase) {
            int address;
            switch (mode) {
                case POSITION_MODE:
                    growIfNecessary(pc + numParam);
                    address = toIntExact(memory[pc + numParam]);
                    growIfNecessary(address);
                    return memory[address];
                case IMMEDIATE_MODE:
                    growIfNecessary(pc + numParam);
                    return memory[pc + numParam];
                case RELATIVE_MODE:
                    growIfNecessary(pc + numParam);
                    address = toIntExact((memory[pc + numParam] + relativeBase));
                    growIfNecessary(address);
                    return memory[address];
            }
            throw new IllegalArgumentException("Invalid mode: position=" + pc + ", mode=" + mode);
        }

        public void write(int pc, int numParam, int mode, int relativeBase, long value) {
            int address;
            switch (mode) {
                case POSITION_MODE:
                    growIfNecessary(pc + numParam);
                    address = toIntExact(memory[pc + numParam]);
                    growIfNecessary(address);
                    memory[address] = value;
                    return;
                case IMMEDIATE_MODE:
                    throw new IllegalArgumentException("Immediate mode not allowed for output: position=" + pc + ", mode=" + mode);
                case RELATIVE_MODE:
                    growIfNecessary(pc + numParam);
                    address = toIntExact((memory[pc + numParam] + relativeBase));
                    growIfNecessary(address);
                    memory[address] = value;
                    return;
            }
            throw new IllegalArgumentException("Invalid mode: position=" + pc + ", mode=" + mode);
        }

        private void growIfNecessary(int address) {
            if (memory.length < (address + 1)) {
                long[] newMemory = new long[address + 1];
                System.arraycopy(memory, 0, newMemory, 0, memory.length);
                memory = newMemory;
            }
        }
    }

    public static Long[] interpretIntcode(Memory memory, long[] inputs) throws InterruptedException {
        BlockingQueue<Long>[] queues = new BlockingQueue[2];
        for (int i = 0; i < 2; i++) {
            queues[i] = new LinkedBlockingQueue<>();
        }
        for (long input : inputs) {
            queues[0].put(input);
        }
        interpretIntcode(memory, queues[0], queues[1], BLOCKING_INPUT, null, null);
        List<Long> outputs = new ArrayList<>();
        while (!queues[1].isEmpty()) {
            outputs.add(queues[1].take());
        }
        // Memory has been modified by this method. But we also return the outputs (if any).
        return outputs.toArray(new Long[0]);
    }

    /**
     * Runs the interpreter with the given memory.
     *
     * @param memory            Initial memory to use. Will possibly be modified by the running program. Will grow as needed.
     * @param inputQueue        The queue to take the input values from.
     * @param outputQueue       The queue to write the output values to.
     * @param blockingMode      Whether reading on an empty input queue will block until data is available (BLOCKING_INPUT),
     *                          or return the fix value -1 immediately (NONBLOCKING_INPUT).
     * @param inputRequestQueue Only supported when blockingMode is BLOCKING_INPUT, optional in this case, ignored otherwise.
     *                          If provided, a fix value of 0 is written to this queue before attempting to read from the inputQueue, so an input can be generated on demand.
     * @param shutdownRequest   Optional. If provided, whenever true is returned by isMustShutdown(), the interpreter is shut down.
     *                          Typically, the interpreter shuts down on executing the opcode 99 (halt), but with this mechanism, a termination can be requested out-of-band.
     * @throws InterruptedException
     */
    public static void interpretIntcode(Memory memory,
                                        BlockingQueue<Long> inputQueue,
                                        BlockingQueue<Long> outputQueue,
                                        int blockingMode,
                                        BlockingQueue<Long> inputRequestQueue,
                                        ShutdownRequest shutdownRequest) throws InterruptedException {
        int pc = 0;
        int relativeBase = 0;

        while (true) {
            long memAtPc = memory.getRaw(pc);
            int opcode = toIntExact(memAtPc % 100);
            int param1Mode = toIntExact((memAtPc / 100) % 10);
            int param2Mode = toIntExact((memAtPc / 1000) % 10);
            int param3Mode = toIntExact((memAtPc / 10000) % 10);

            if (!MODES.contains(param1Mode)) {
                throw new IllegalArgumentException("Invalid param1Mode: param1Mode=" + param1Mode + ", position=" + pc);
            }
            if (!MODES.contains(param2Mode)) {
                throw new IllegalArgumentException("Invalid param2Mode: param2Mode=" + param2Mode + ", position=" + pc);
            }
            if (!MODES.contains(param3Mode)) {
                throw new IllegalArgumentException("Invalid param3Mode: param3Mode=" + param3Mode + ", position=" + pc);
            }

            long input1;
            long input2;
            long output;

            switch (opcode) {
                case 1: // add
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    input2 = memory.read(pc, 2, param2Mode, relativeBase);
                    output = input1 + input2;
                    memory.write(pc, 3, param3Mode, relativeBase, output);
                    pc += 4;
                    break;

                case 2: // multiply
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    input2 = memory.read(pc, 2, param2Mode, relativeBase);
                    output = input1 * input2;
                    memory.write(pc, 3, param3Mode, relativeBase, output);
                    pc += 4;
                    break;

                case 3: // read input
                    if (shutdownRequest != null && shutdownRequest.isMustShutdown()) {
                        return;
                    }
                    switch (blockingMode) {
                        case BLOCKING_INPUT:
                            if (inputRequestQueue != null) {
                                inputRequestQueue.put(0L);
                            }
                            input1 = inputQueue.take();
                            break;
                        case NONBLOCKING_INPUT:
                            if (inputQueue.peek() != null) {
                                input1 = inputQueue.take();
                            } else {
                                input1 = NO_INPUT_AVAILABLE;
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid blockingMode: blockingMode=" + blockingMode);
                    }
                    memory.write(pc, 1, param1Mode, relativeBase, input1);
                    pc += 2;
                    break;

                case 4: // write output
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    outputQueue.put(input1);
                    pc += 2;
                    break;

                case 5: // jump-if-true
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    input2 = memory.read(pc, 2, param2Mode, relativeBase);
                    if (input1 != 0) {
                        pc = toIntExact(input2);
                    } else {
                        pc += 3;
                    }
                    break;

                case 6: // jump-if-false
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    input2 = memory.read(pc, 2, param2Mode, relativeBase);
                    if (input1 == 0) {
                        pc = toIntExact(input2);
                    } else {
                        pc += 3;
                    }
                    break;

                case 7: // less than
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    input2 = memory.read(pc, 2, param2Mode, relativeBase);
                    output = input1 < input2 ? 1 : 0;
                    memory.write(pc, 3, param3Mode, relativeBase, output);
                    pc += 4;
                    break;

                case 8: // equals
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    input2 = memory.read(pc, 2, param2Mode, relativeBase);
                    output = input1 == input2 ? 1 : 0;
                    memory.write(pc, 3, param3Mode, relativeBase, output);
                    pc += 4;
                    break;

                case 9: // adjust relative base
                    input1 = memory.read(pc, 1, param1Mode, relativeBase);
                    relativeBase += toIntExact(input1);
                    pc += 2;
                    break;

                case 99: // halt
                    // Memory has been modified by this method.
                    return;

                default:
                    throw new IllegalArgumentException("Invalid opcode: position=" + pc + ", opcode=" + opcode);
            }
        }
    }
}
