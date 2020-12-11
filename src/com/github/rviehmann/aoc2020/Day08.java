package com.github.rviehmann.aoc2020;

public class Day08 {

    // From: https://adventofcode.com/2020/day/8/input
    private static final String INPUT =
            "acc +17\n" +
                    "acc +37\n" +
                    "acc -13\n" +
                    "jmp +173\n" +
                    "nop +100\n" +
                    "acc -7\n" +
                    "jmp +447\n" +
                    "nop +283\n" +
                    "acc +41\n" +
                    "acc +32\n" +
                    "jmp +1\n" +
                    "jmp +585\n" +
                    "jmp +1\n" +
                    "acc -5\n" +
                    "nop +71\n" +
                    "acc +49\n" +
                    "acc -18\n" +
                    "jmp +527\n" +
                    "jmp +130\n" +
                    "jmp +253\n" +
                    "acc +11\n" +
                    "acc -11\n" +
                    "jmp +390\n" +
                    "jmp +597\n" +
                    "jmp +1\n" +
                    "acc +6\n" +
                    "acc +0\n" +
                    "jmp +588\n" +
                    "acc -17\n" +
                    "jmp +277\n" +
                    "acc +2\n" +
                    "nop +163\n" +
                    "jmp +558\n" +
                    "acc +38\n" +
                    "jmp +369\n" +
                    "acc +13\n" +
                    "jmp +536\n" +
                    "acc +38\n" +
                    "acc +39\n" +
                    "acc +6\n" +
                    "jmp +84\n" +
                    "acc +11\n" +
                    "nop +517\n" +
                    "acc +48\n" +
                    "acc +47\n" +
                    "jmp +1\n" +
                    "acc +42\n" +
                    "acc +0\n" +
                    "acc +2\n" +
                    "acc +24\n" +
                    "jmp +335\n" +
                    "acc +44\n" +
                    "acc +47\n" +
                    "jmp +446\n" +
                    "nop +42\n" +
                    "nop +74\n" +
                    "acc +45\n" +
                    "jmp +548\n" +
                    "jmp +66\n" +
                    "acc +1\n" +
                    "jmp +212\n" +
                    "acc +18\n" +
                    "jmp +1\n" +
                    "acc +4\n" +
                    "acc -16\n" +
                    "jmp +366\n" +
                    "acc +0\n" +
                    "jmp +398\n" +
                    "acc +45\n" +
                    "jmp +93\n" +
                    "acc +40\n" +
                    "acc +38\n" +
                    "acc +21\n" +
                    "nop +184\n" +
                    "jmp -46\n" +
                    "nop -9\n" +
                    "jmp +53\n" +
                    "acc +46\n" +
                    "acc +36\n" +
                    "jmp +368\n" +
                    "acc +16\n" +
                    "acc +8\n" +
                    "acc -9\n" +
                    "acc -4\n" +
                    "jmp +328\n" +
                    "acc -15\n" +
                    "acc -5\n" +
                    "acc +21\n" +
                    "jmp +435\n" +
                    "acc -5\n" +
                    "acc +36\n" +
                    "jmp +362\n" +
                    "acc +26\n" +
                    "jmp +447\n" +
                    "jmp +1\n" +
                    "jmp +412\n" +
                    "acc +11\n" +
                    "acc +41\n" +
                    "nop -32\n" +
                    "acc +17\n" +
                    "jmp -63\n" +
                    "jmp +1\n" +
                    "nop +393\n" +
                    "jmp +62\n" +
                    "acc +18\n" +
                    "acc +30\n" +
                    "nop +417\n" +
                    "jmp +74\n" +
                    "acc +29\n" +
                    "acc +23\n" +
                    "jmp +455\n" +
                    "jmp +396\n" +
                    "jmp +395\n" +
                    "acc +33\n" +
                    "nop +137\n" +
                    "nop +42\n" +
                    "jmp +57\n" +
                    "jmp +396\n" +
                    "acc +7\n" +
                    "acc +0\n" +
                    "jmp +354\n" +
                    "acc +15\n" +
                    "acc +50\n" +
                    "jmp -12\n" +
                    "jmp +84\n" +
                    "nop +175\n" +
                    "acc +5\n" +
                    "acc -2\n" +
                    "jmp -82\n" +
                    "acc +1\n" +
                    "acc +26\n" +
                    "jmp +288\n" +
                    "nop -113\n" +
                    "nop +366\n" +
                    "acc +45\n" +
                    "jmp +388\n" +
                    "acc +21\n" +
                    "acc +38\n" +
                    "jmp +427\n" +
                    "acc +33\n" +
                    "jmp -94\n" +
                    "nop -118\n" +
                    "nop +411\n" +
                    "jmp +472\n" +
                    "nop +231\n" +
                    "nop +470\n" +
                    "acc +48\n" +
                    "jmp -124\n" +
                    "jmp +1\n" +
                    "acc +5\n" +
                    "acc +37\n" +
                    "acc +42\n" +
                    "jmp +301\n" +
                    "acc -11\n" +
                    "acc -17\n" +
                    "acc +14\n" +
                    "jmp +357\n" +
                    "acc +6\n" +
                    "acc +20\n" +
                    "acc +13\n" +
                    "jmp +361\n" +
                    "jmp -65\n" +
                    "acc +29\n" +
                    "jmp +26\n" +
                    "jmp +329\n" +
                    "acc +32\n" +
                    "acc +32\n" +
                    "acc +17\n" +
                    "jmp -102\n" +
                    "acc -6\n" +
                    "acc +33\n" +
                    "acc +9\n" +
                    "jmp +189\n" +
                    "acc +3\n" +
                    "jmp -128\n" +
                    "jmp -142\n" +
                    "acc +24\n" +
                    "acc -5\n" +
                    "jmp +403\n" +
                    "acc +28\n" +
                    "jmp +310\n" +
                    "acc +34\n" +
                    "acc +4\n" +
                    "acc +33\n" +
                    "acc +18\n" +
                    "jmp +227\n" +
                    "acc -8\n" +
                    "acc -15\n" +
                    "jmp +112\n" +
                    "jmp +54\n" +
                    "acc +21\n" +
                    "acc +23\n" +
                    "acc +20\n" +
                    "jmp +320\n" +
                    "acc +13\n" +
                    "jmp -77\n" +
                    "acc +15\n" +
                    "nop +310\n" +
                    "nop +335\n" +
                    "jmp +232\n" +
                    "acc -3\n" +
                    "nop +50\n" +
                    "acc +41\n" +
                    "jmp +112\n" +
                    "nop -10\n" +
                    "acc +29\n" +
                    "acc +27\n" +
                    "jmp +52\n" +
                    "acc +40\n" +
                    "nop -132\n" +
                    "acc -16\n" +
                    "acc +27\n" +
                    "jmp +309\n" +
                    "acc -8\n" +
                    "nop +147\n" +
                    "acc +20\n" +
                    "acc +46\n" +
                    "jmp +202\n" +
                    "acc +27\n" +
                    "jmp -43\n" +
                    "jmp +1\n" +
                    "acc +33\n" +
                    "acc -13\n" +
                    "jmp +300\n" +
                    "acc +1\n" +
                    "jmp -202\n" +
                    "acc -17\n" +
                    "acc +0\n" +
                    "acc +34\n" +
                    "jmp -5\n" +
                    "nop +335\n" +
                    "acc -16\n" +
                    "acc -17\n" +
                    "jmp -120\n" +
                    "acc -19\n" +
                    "acc -13\n" +
                    "acc +4\n" +
                    "jmp +368\n" +
                    "jmp +21\n" +
                    "acc +39\n" +
                    "acc +39\n" +
                    "acc -18\n" +
                    "jmp -157\n" +
                    "nop +280\n" +
                    "acc +33\n" +
                    "nop -37\n" +
                    "jmp +32\n" +
                    "acc -16\n" +
                    "acc +18\n" +
                    "acc +46\n" +
                    "jmp -121\n" +
                    "acc -19\n" +
                    "jmp +195\n" +
                    "acc +28\n" +
                    "jmp +124\n" +
                    "jmp +331\n" +
                    "jmp -228\n" +
                    "jmp -146\n" +
                    "jmp +85\n" +
                    "jmp +60\n" +
                    "acc +20\n" +
                    "acc -9\n" +
                    "jmp +303\n" +
                    "jmp -122\n" +
                    "jmp +111\n" +
                    "acc +32\n" +
                    "acc +0\n" +
                    "acc +39\n" +
                    "acc +29\n" +
                    "jmp -31\n" +
                    "nop +320\n" +
                    "jmp -63\n" +
                    "jmp +223\n" +
                    "nop -149\n" +
                    "acc -12\n" +
                    "acc -11\n" +
                    "acc +32\n" +
                    "jmp +309\n" +
                    "jmp -13\n" +
                    "acc -19\n" +
                    "jmp -123\n" +
                    "acc +21\n" +
                    "acc +18\n" +
                    "acc +49\n" +
                    "jmp +175\n" +
                    "acc -14\n" +
                    "nop -129\n" +
                    "acc -2\n" +
                    "acc +31\n" +
                    "jmp +79\n" +
                    "acc +23\n" +
                    "acc +50\n" +
                    "acc +39\n" +
                    "acc +7\n" +
                    "jmp -235\n" +
                    "jmp -166\n" +
                    "acc +9\n" +
                    "jmp +293\n" +
                    "acc -11\n" +
                    "jmp +76\n" +
                    "acc +44\n" +
                    "acc +3\n" +
                    "acc +37\n" +
                    "jmp +123\n" +
                    "nop -104\n" +
                    "jmp -157\n" +
                    "acc +14\n" +
                    "acc +10\n" +
                    "acc +28\n" +
                    "jmp +25\n" +
                    "acc +37\n" +
                    "jmp +188\n" +
                    "jmp -49\n" +
                    "acc -11\n" +
                    "jmp -90\n" +
                    "acc -8\n" +
                    "jmp +197\n" +
                    "acc +5\n" +
                    "jmp +115\n" +
                    "acc +44\n" +
                    "jmp -228\n" +
                    "nop -2\n" +
                    "acc +46\n" +
                    "jmp +130\n" +
                    "nop +183\n" +
                    "nop +106\n" +
                    "acc +27\n" +
                    "acc +37\n" +
                    "jmp -309\n" +
                    "acc +28\n" +
                    "acc -4\n" +
                    "acc -12\n" +
                    "acc +38\n" +
                    "jmp +93\n" +
                    "acc +8\n" +
                    "acc +23\n" +
                    "acc -9\n" +
                    "acc +6\n" +
                    "jmp -42\n" +
                    "acc +10\n" +
                    "acc +35\n" +
                    "acc +4\n" +
                    "jmp -231\n" +
                    "acc +19\n" +
                    "acc +7\n" +
                    "acc +23\n" +
                    "acc +11\n" +
                    "jmp -90\n" +
                    "acc +0\n" +
                    "nop +158\n" +
                    "nop -150\n" +
                    "acc +33\n" +
                    "jmp +107\n" +
                    "acc +48\n" +
                    "acc -2\n" +
                    "jmp -104\n" +
                    "acc +6\n" +
                    "nop -57\n" +
                    "nop +172\n" +
                    "acc -11\n" +
                    "jmp -7\n" +
                    "acc +6\n" +
                    "acc +50\n" +
                    "acc -9\n" +
                    "acc +12\n" +
                    "jmp -171\n" +
                    "acc +3\n" +
                    "jmp +26\n" +
                    "acc +42\n" +
                    "acc +31\n" +
                    "acc +20\n" +
                    "acc +32\n" +
                    "jmp -48\n" +
                    "acc +13\n" +
                    "jmp -6\n" +
                    "jmp +178\n" +
                    "acc +47\n" +
                    "jmp -153\n" +
                    "acc +28\n" +
                    "nop +74\n" +
                    "jmp -162\n" +
                    "acc -15\n" +
                    "nop -104\n" +
                    "acc -9\n" +
                    "jmp -227\n" +
                    "acc +49\n" +
                    "acc -19\n" +
                    "acc +41\n" +
                    "jmp -318\n" +
                    "acc +9\n" +
                    "acc +12\n" +
                    "acc +7\n" +
                    "jmp +34\n" +
                    "jmp +137\n" +
                    "nop -143\n" +
                    "acc -8\n" +
                    "acc +5\n" +
                    "acc +31\n" +
                    "jmp -20\n" +
                    "jmp -237\n" +
                    "acc +39\n" +
                    "acc +0\n" +
                    "jmp -298\n" +
                    "acc +45\n" +
                    "acc -19\n" +
                    "acc +11\n" +
                    "jmp -151\n" +
                    "acc +40\n" +
                    "acc +27\n" +
                    "nop +150\n" +
                    "nop -391\n" +
                    "jmp -341\n" +
                    "acc +1\n" +
                    "acc +11\n" +
                    "acc +18\n" +
                    "nop -234\n" +
                    "jmp +77\n" +
                    "nop +104\n" +
                    "jmp -65\n" +
                    "acc +32\n" +
                    "jmp -27\n" +
                    "nop -317\n" +
                    "nop +159\n" +
                    "acc +14\n" +
                    "acc -10\n" +
                    "jmp -348\n" +
                    "acc +29\n" +
                    "jmp +32\n" +
                    "acc +48\n" +
                    "acc -19\n" +
                    "jmp +17\n" +
                    "jmp -201\n" +
                    "jmp -224\n" +
                    "nop +26\n" +
                    "acc -7\n" +
                    "acc +23\n" +
                    "acc +46\n" +
                    "jmp -6\n" +
                    "acc +22\n" +
                    "acc +39\n" +
                    "acc +9\n" +
                    "acc +23\n" +
                    "jmp -30\n" +
                    "jmp -243\n" +
                    "acc +47\n" +
                    "acc -15\n" +
                    "jmp -298\n" +
                    "jmp -393\n" +
                    "jmp +1\n" +
                    "acc +3\n" +
                    "nop -24\n" +
                    "acc +7\n" +
                    "jmp -59\n" +
                    "acc -6\n" +
                    "acc +26\n" +
                    "jmp -102\n" +
                    "acc +34\n" +
                    "acc +24\n" +
                    "jmp -207\n" +
                    "acc +36\n" +
                    "acc +40\n" +
                    "acc +41\n" +
                    "jmp +1\n" +
                    "jmp -306\n" +
                    "jmp +57\n" +
                    "jmp +1\n" +
                    "nop +99\n" +
                    "acc +28\n" +
                    "jmp -391\n" +
                    "acc +50\n" +
                    "jmp -359\n" +
                    "acc -5\n" +
                    "jmp +9\n" +
                    "jmp -355\n" +
                    "acc +5\n" +
                    "acc +2\n" +
                    "jmp -77\n" +
                    "acc +40\n" +
                    "acc +28\n" +
                    "acc +22\n" +
                    "jmp -262\n" +
                    "nop -287\n" +
                    "acc +34\n" +
                    "acc -4\n" +
                    "nop +112\n" +
                    "jmp -195\n" +
                    "acc +29\n" +
                    "nop -94\n" +
                    "nop -418\n" +
                    "jmp +24\n" +
                    "jmp -190\n" +
                    "acc +2\n" +
                    "jmp -311\n" +
                    "jmp -178\n" +
                    "jmp -276\n" +
                    "acc -12\n" +
                    "acc -18\n" +
                    "jmp +62\n" +
                    "jmp -174\n" +
                    "nop +31\n" +
                    "acc +33\n" +
                    "nop -158\n" +
                    "jmp -417\n" +
                    "acc +3\n" +
                    "acc +21\n" +
                    "acc +47\n" +
                    "jmp +87\n" +
                    "acc +45\n" +
                    "jmp -77\n" +
                    "acc +6\n" +
                    "acc -10\n" +
                    "jmp +1\n" +
                    "jmp -240\n" +
                    "acc +7\n" +
                    "acc +47\n" +
                    "jmp -379\n" +
                    "acc -14\n" +
                    "acc +50\n" +
                    "nop -75\n" +
                    "acc +30\n" +
                    "jmp +70\n" +
                    "jmp -392\n" +
                    "jmp -430\n" +
                    "acc +22\n" +
                    "acc -2\n" +
                    "jmp -492\n" +
                    "jmp +1\n" +
                    "acc -6\n" +
                    "acc +38\n" +
                    "jmp -36\n" +
                    "nop -336\n" +
                    "jmp -32\n" +
                    "jmp +61\n" +
                    "acc +20\n" +
                    "acc -9\n" +
                    "acc +2\n" +
                    "jmp -175\n" +
                    "acc +21\n" +
                    "acc -2\n" +
                    "jmp -6\n" +
                    "jmp -527\n" +
                    "acc +11\n" +
                    "acc +16\n" +
                    "jmp -262\n" +
                    "jmp +1\n" +
                    "nop -327\n" +
                    "acc +29\n" +
                    "jmp -114\n" +
                    "acc +11\n" +
                    "acc +17\n" +
                    "acc +26\n" +
                    "nop -104\n" +
                    "jmp -428\n" +
                    "nop -178\n" +
                    "nop -242\n" +
                    "acc +29\n" +
                    "acc +5\n" +
                    "jmp -245\n" +
                    "jmp -417\n" +
                    "jmp -278\n" +
                    "acc +35\n" +
                    "acc +21\n" +
                    "jmp +1\n" +
                    "nop -263\n" +
                    "jmp +8\n" +
                    "acc +42\n" +
                    "jmp -95\n" +
                    "nop -312\n" +
                    "acc -11\n" +
                    "acc +34\n" +
                    "acc +0\n" +
                    "jmp +19\n" +
                    "acc +8\n" +
                    "acc -13\n" +
                    "acc +32\n" +
                    "acc +21\n" +
                    "jmp -208\n" +
                    "acc +15\n" +
                    "acc +39\n" +
                    "nop -194\n" +
                    "jmp -280\n" +
                    "jmp +24\n" +
                    "nop -516\n" +
                    "acc +21\n" +
                    "acc +48\n" +
                    "jmp -367\n" +
                    "jmp -121\n" +
                    "acc +49\n" +
                    "acc -16\n" +
                    "jmp -136\n" +
                    "acc +0\n" +
                    "jmp -148\n" +
                    "jmp -85\n" +
                    "jmp -103\n" +
                    "nop -446\n" +
                    "jmp -242\n" +
                    "acc -12\n" +
                    "acc +13\n" +
                    "acc +31\n" +
                    "acc -1\n" +
                    "jmp -435\n" +
                    "nop -420\n" +
                    "acc +22\n" +
                    "acc -5\n" +
                    "jmp -567\n" +
                    "nop -354\n" +
                    "acc +11\n" +
                    "acc +33\n" +
                    "acc +45\n" +
                    "jmp -76\n" +
                    "acc -2\n" +
                    "acc +0\n" +
                    "acc +25\n" +
                    "acc +46\n" +
                    "jmp -555\n" +
                    "acc +0\n" +
                    "acc +11\n" +
                    "nop -2\n" +
                    "jmp -394\n" +
                    "jmp -395\n" +
                    "acc +8\n" +
                    "acc +14\n" +
                    "acc +47\n" +
                    "acc +22\n" +
                    "jmp +1";

    private static final String[] INPUT_AS_INSTRUCTION_ARRAY = INPUT.split("\\R");

    public static class Instruction {

        final String operation;
        final long argument;

        public Instruction(String operation, long argument) {
            this.operation = operation;
            this.argument = argument;
        }

        public static Instruction fromString(String input) {
            String[] parts = input.split(" +");
            String operation = parts[0];
            long argument = Long.parseLong(parts[1]);
            return new Instruction(operation, argument);
        }
    }

    public static class InfiniteLoopException extends RuntimeException {

        final long accumulator;
        final int pc;

        public InfiniteLoopException(String message, long accumulator, int pc) {
            super(message);
            this.accumulator = accumulator;
            this.pc = pc;
        }
    }

    public static long interpret(String[] instructionStrings, int flipInstruction) {
        Instruction[] instructions = new Instruction[instructionStrings.length];
        int[] executionCount = new int[instructionStrings.length];

        for (int i = 0; i < instructionStrings.length; i++) {
            instructions[i] = Instruction.fromString(instructionStrings[i]);
            executionCount[i] = 0;
        }

        long accumulator = 0;
        int pc = 0;

        while (true) {
            if (executionCount[pc] > 0) {
                // Abnormal termination
                throw new InfiniteLoopException("Infinite loop detected.", accumulator, pc);
            }

            Instruction instr = instructions[pc];
            executionCount[pc]++;
            String op = instr.operation;

            if (pc == flipInstruction) {
                if (op.equals("nop")) {
                    op = "jmp";
                } else if (op.equals("jmp")) {
                    op = "nop";
                }
            }

            switch (op) {
                case "acc":
                    accumulator += instr.argument;
                    pc++;
                    break;

                case "jmp":
                    pc += instr.argument;
                    break;

                case "nop":
                    pc++;
                    break;
            }

            if (pc >= instructionStrings.length) {
                // Normal termination
                return accumulator;
            }
        }
    }

    public static long doPuzzle1() {
        try {
            // -1 -> do not flip anything
            interpret(INPUT_AS_INSTRUCTION_ARRAY, -1);
        } catch (InfiniteLoopException e) {
            return e.accumulator;
        }

        throw new IllegalStateException("This line should never get executed.");
    }

    public static long doPuzzle2() {
        for (int i = 0; i < INPUT_AS_INSTRUCTION_ARRAY.length; i++) {
            try {
                return interpret(INPUT_AS_INSTRUCTION_ARRAY, i);
            } catch (InfiniteLoopException e) {
                // That was wrong
            }
        }

        throw new IllegalStateException("This line should never get executed.");
    }
}
