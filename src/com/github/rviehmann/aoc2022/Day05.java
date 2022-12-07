package com.github.rviehmann.aoc2022;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Day05 {

    private static final String EXAMPLE_MOVES =
            "move 1 from 2 to 1\n" +
                    "move 3 from 1 to 3\n" +
                    "move 2 from 2 to 1\n" +
                    "move 1 from 1 to 2";

    private static final String MOVES =
            "move 2 from 2 to 8\n" +
                    "move 2 from 1 to 6\n" +
                    "move 8 from 7 to 1\n" +
                    "move 7 from 5 to 4\n" +
                    "move 1 from 6 to 4\n" +
                    "move 1 from 6 to 3\n" +
                    "move 6 from 3 to 5\n" +
                    "move 9 from 8 to 1\n" +
                    "move 3 from 6 to 7\n" +
                    "move 14 from 4 to 1\n" +
                    "move 6 from 1 to 7\n" +
                    "move 16 from 1 to 9\n" +
                    "move 6 from 1 to 4\n" +
                    "move 1 from 8 to 6\n" +
                    "move 4 from 1 to 5\n" +
                    "move 11 from 9 to 7\n" +
                    "move 2 from 1 to 8\n" +
                    "move 1 from 6 to 7\n" +
                    "move 1 from 8 to 7\n" +
                    "move 1 from 8 to 3\n" +
                    "move 7 from 4 to 3\n" +
                    "move 14 from 7 to 6\n" +
                    "move 8 from 6 to 9\n" +
                    "move 19 from 9 to 2\n" +
                    "move 1 from 1 to 2\n" +
                    "move 2 from 9 to 7\n" +
                    "move 9 from 7 to 8\n" +
                    "move 2 from 2 to 8\n" +
                    "move 16 from 2 to 9\n" +
                    "move 4 from 8 to 2\n" +
                    "move 1 from 7 to 9\n" +
                    "move 3 from 9 to 6\n" +
                    "move 3 from 3 to 6\n" +
                    "move 11 from 9 to 2\n" +
                    "move 7 from 5 to 3\n" +
                    "move 2 from 5 to 9\n" +
                    "move 6 from 6 to 4\n" +
                    "move 1 from 6 to 4\n" +
                    "move 4 from 6 to 8\n" +
                    "move 5 from 9 to 1\n" +
                    "move 4 from 1 to 7\n" +
                    "move 3 from 2 to 6\n" +
                    "move 3 from 4 to 1\n" +
                    "move 1 from 4 to 1\n" +
                    "move 2 from 1 to 3\n" +
                    "move 4 from 3 to 7\n" +
                    "move 1 from 5 to 2\n" +
                    "move 3 from 1 to 6\n" +
                    "move 15 from 2 to 5\n" +
                    "move 3 from 6 to 3\n" +
                    "move 13 from 3 to 8\n" +
                    "move 2 from 4 to 2\n" +
                    "move 9 from 5 to 4\n" +
                    "move 2 from 2 to 5\n" +
                    "move 5 from 7 to 5\n" +
                    "move 10 from 8 to 6\n" +
                    "move 1 from 2 to 5\n" +
                    "move 10 from 4 to 6\n" +
                    "move 4 from 8 to 6\n" +
                    "move 3 from 7 to 1\n" +
                    "move 3 from 1 to 9\n" +
                    "move 1 from 2 to 1\n" +
                    "move 8 from 5 to 2\n" +
                    "move 3 from 6 to 9\n" +
                    "move 6 from 8 to 5\n" +
                    "move 6 from 9 to 2\n" +
                    "move 1 from 1 to 9\n" +
                    "move 10 from 2 to 1\n" +
                    "move 4 from 8 to 5\n" +
                    "move 10 from 5 to 9\n" +
                    "move 11 from 9 to 7\n" +
                    "move 5 from 7 to 9\n" +
                    "move 1 from 9 to 2\n" +
                    "move 3 from 2 to 9\n" +
                    "move 2 from 2 to 8\n" +
                    "move 4 from 9 to 5\n" +
                    "move 4 from 1 to 9\n" +
                    "move 5 from 5 to 2\n" +
                    "move 5 from 1 to 4\n" +
                    "move 21 from 6 to 9\n" +
                    "move 3 from 2 to 9\n" +
                    "move 2 from 8 to 1\n" +
                    "move 25 from 9 to 6\n" +
                    "move 4 from 5 to 7\n" +
                    "move 1 from 4 to 6\n" +
                    "move 6 from 6 to 4\n" +
                    "move 3 from 4 to 6\n" +
                    "move 7 from 7 to 3\n" +
                    "move 4 from 9 to 1\n" +
                    "move 3 from 7 to 8\n" +
                    "move 2 from 9 to 8\n" +
                    "move 2 from 2 to 8\n" +
                    "move 4 from 1 to 3\n" +
                    "move 9 from 6 to 2\n" +
                    "move 13 from 6 to 4\n" +
                    "move 13 from 4 to 5\n" +
                    "move 1 from 5 to 8\n" +
                    "move 2 from 2 to 3\n" +
                    "move 6 from 5 to 3\n" +
                    "move 19 from 3 to 6\n" +
                    "move 1 from 4 to 9\n" +
                    "move 2 from 8 to 1\n" +
                    "move 5 from 2 to 3\n" +
                    "move 5 from 1 to 9\n" +
                    "move 7 from 5 to 4\n" +
                    "move 1 from 8 to 3\n" +
                    "move 1 from 2 to 6\n" +
                    "move 8 from 6 to 3\n" +
                    "move 1 from 9 to 8\n" +
                    "move 11 from 4 to 2\n" +
                    "move 1 from 4 to 6\n" +
                    "move 1 from 2 to 8\n" +
                    "move 5 from 3 to 4\n" +
                    "move 4 from 9 to 6\n" +
                    "move 1 from 6 to 8\n" +
                    "move 9 from 3 to 1\n" +
                    "move 7 from 2 to 9\n" +
                    "move 1 from 2 to 6\n" +
                    "move 3 from 1 to 8\n" +
                    "move 2 from 2 to 3\n" +
                    "move 3 from 9 to 7\n" +
                    "move 3 from 4 to 7\n" +
                    "move 2 from 4 to 3\n" +
                    "move 2 from 3 to 5\n" +
                    "move 8 from 6 to 4\n" +
                    "move 6 from 8 to 6\n" +
                    "move 2 from 9 to 4\n" +
                    "move 5 from 8 to 6\n" +
                    "move 3 from 7 to 5\n" +
                    "move 1 from 5 to 8\n" +
                    "move 1 from 8 to 2\n" +
                    "move 1 from 5 to 1\n" +
                    "move 11 from 4 to 9\n" +
                    "move 2 from 6 to 3\n" +
                    "move 2 from 2 to 4\n" +
                    "move 6 from 1 to 2\n" +
                    "move 6 from 2 to 1\n" +
                    "move 3 from 7 to 3\n" +
                    "move 2 from 4 to 7\n" +
                    "move 4 from 6 to 5\n" +
                    "move 7 from 3 to 7\n" +
                    "move 5 from 9 to 6\n" +
                    "move 22 from 6 to 8\n" +
                    "move 2 from 6 to 5\n" +
                    "move 2 from 8 to 4\n" +
                    "move 14 from 8 to 7\n" +
                    "move 11 from 7 to 4\n" +
                    "move 3 from 8 to 1\n" +
                    "move 9 from 7 to 8\n" +
                    "move 10 from 1 to 4\n" +
                    "move 1 from 7 to 4\n" +
                    "move 4 from 8 to 7\n" +
                    "move 6 from 4 to 9\n" +
                    "move 7 from 4 to 1\n" +
                    "move 3 from 4 to 8\n" +
                    "move 1 from 5 to 8\n" +
                    "move 8 from 5 to 3\n" +
                    "move 4 from 3 to 9\n" +
                    "move 7 from 8 to 9\n" +
                    "move 3 from 8 to 3\n" +
                    "move 2 from 8 to 2\n" +
                    "move 7 from 9 to 1\n" +
                    "move 2 from 2 to 8\n" +
                    "move 8 from 9 to 1\n" +
                    "move 8 from 1 to 7\n" +
                    "move 7 from 1 to 5\n" +
                    "move 7 from 7 to 1\n" +
                    "move 11 from 9 to 8\n" +
                    "move 9 from 8 to 5\n" +
                    "move 2 from 8 to 5\n" +
                    "move 3 from 1 to 8\n" +
                    "move 2 from 3 to 7\n" +
                    "move 6 from 4 to 1\n" +
                    "move 6 from 1 to 6\n" +
                    "move 5 from 7 to 1\n" +
                    "move 2 from 4 to 6\n" +
                    "move 1 from 3 to 5\n" +
                    "move 4 from 7 to 4\n" +
                    "move 2 from 8 to 7\n" +
                    "move 10 from 5 to 6\n" +
                    "move 9 from 6 to 1\n" +
                    "move 8 from 1 to 6\n" +
                    "move 1 from 7 to 2\n" +
                    "move 9 from 6 to 4\n" +
                    "move 2 from 4 to 3\n" +
                    "move 3 from 8 to 1\n" +
                    "move 1 from 2 to 4\n" +
                    "move 4 from 4 to 1\n" +
                    "move 7 from 4 to 3\n" +
                    "move 3 from 3 to 2\n" +
                    "move 1 from 7 to 6\n" +
                    "move 9 from 6 to 7\n" +
                    "move 6 from 7 to 4\n" +
                    "move 2 from 7 to 2\n" +
                    "move 6 from 4 to 7\n" +
                    "move 2 from 2 to 9\n" +
                    "move 1 from 2 to 4\n" +
                    "move 1 from 7 to 4\n" +
                    "move 4 from 7 to 6\n" +
                    "move 4 from 5 to 4\n" +
                    "move 1 from 2 to 5\n" +
                    "move 1 from 7 to 5\n" +
                    "move 1 from 2 to 6\n" +
                    "move 6 from 4 to 3\n" +
                    "move 9 from 3 to 9\n" +
                    "move 4 from 6 to 2\n" +
                    "move 7 from 3 to 8\n" +
                    "move 22 from 1 to 7\n" +
                    "move 1 from 1 to 7\n" +
                    "move 2 from 8 to 3\n" +
                    "move 4 from 5 to 6\n" +
                    "move 2 from 3 to 2\n" +
                    "move 6 from 2 to 8\n" +
                    "move 3 from 8 to 6\n" +
                    "move 1 from 4 to 8\n" +
                    "move 1 from 1 to 8\n" +
                    "move 8 from 6 to 7\n" +
                    "move 7 from 8 to 9\n" +
                    "move 22 from 7 to 4\n" +
                    "move 3 from 5 to 6\n" +
                    "move 1 from 8 to 1\n" +
                    "move 2 from 8 to 2\n" +
                    "move 3 from 6 to 4\n" +
                    "move 1 from 1 to 3\n" +
                    "move 15 from 9 to 1\n" +
                    "move 5 from 1 to 5\n" +
                    "move 3 from 7 to 6\n" +
                    "move 5 from 5 to 6\n" +
                    "move 4 from 4 to 3\n" +
                    "move 6 from 6 to 9\n" +
                    "move 7 from 7 to 6\n" +
                    "move 5 from 6 to 7\n" +
                    "move 4 from 1 to 9\n" +
                    "move 3 from 7 to 4\n" +
                    "move 2 from 9 to 7\n" +
                    "move 5 from 3 to 5\n" +
                    "move 3 from 6 to 3\n" +
                    "move 5 from 4 to 6\n" +
                    "move 10 from 9 to 5\n" +
                    "move 1 from 2 to 9\n" +
                    "move 1 from 3 to 5\n" +
                    "move 1 from 2 to 9\n" +
                    "move 3 from 1 to 6\n" +
                    "move 2 from 9 to 2\n" +
                    "move 7 from 6 to 5\n" +
                    "move 15 from 4 to 9\n" +
                    "move 2 from 4 to 5\n" +
                    "move 1 from 3 to 4\n" +
                    "move 9 from 9 to 1\n" +
                    "move 1 from 9 to 2\n" +
                    "move 2 from 9 to 4\n" +
                    "move 11 from 5 to 4\n" +
                    "move 1 from 9 to 3\n" +
                    "move 1 from 6 to 8\n" +
                    "move 4 from 7 to 8\n" +
                    "move 4 from 8 to 9\n" +
                    "move 15 from 4 to 7\n" +
                    "move 1 from 6 to 7\n" +
                    "move 1 from 3 to 7\n" +
                    "move 6 from 9 to 6\n" +
                    "move 1 from 3 to 7\n" +
                    "move 1 from 2 to 1\n" +
                    "move 1 from 9 to 5\n" +
                    "move 3 from 6 to 1\n" +
                    "move 11 from 1 to 4\n" +
                    "move 6 from 5 to 1\n" +
                    "move 2 from 2 to 5\n" +
                    "move 1 from 5 to 7\n" +
                    "move 2 from 6 to 1\n" +
                    "move 7 from 5 to 7\n" +
                    "move 3 from 5 to 6\n" +
                    "move 4 from 6 to 1\n" +
                    "move 11 from 4 to 3\n" +
                    "move 1 from 8 to 5\n" +
                    "move 23 from 7 to 6\n" +
                    "move 18 from 6 to 9\n" +
                    "move 1 from 5 to 9\n" +
                    "move 1 from 4 to 2\n" +
                    "move 3 from 3 to 7\n" +
                    "move 3 from 3 to 8\n" +
                    "move 17 from 1 to 8\n" +
                    "move 5 from 6 to 5\n" +
                    "move 2 from 7 to 1\n" +
                    "move 20 from 8 to 2\n" +
                    "move 4 from 7 to 2\n" +
                    "move 3 from 9 to 5\n" +
                    "move 7 from 9 to 7\n" +
                    "move 6 from 9 to 2\n" +
                    "move 1 from 1 to 8\n" +
                    "move 3 from 9 to 4\n" +
                    "move 7 from 5 to 2\n" +
                    "move 6 from 7 to 1\n" +
                    "move 1 from 1 to 8\n" +
                    "move 3 from 2 to 6\n" +
                    "move 1 from 7 to 6\n" +
                    "move 2 from 8 to 9\n" +
                    "move 35 from 2 to 4\n" +
                    "move 3 from 3 to 2\n" +
                    "move 1 from 5 to 7\n" +
                    "move 2 from 3 to 9\n" +
                    "move 3 from 1 to 6\n" +
                    "move 2 from 2 to 1\n" +
                    "move 32 from 4 to 7\n" +
                    "move 3 from 4 to 8\n" +
                    "move 3 from 9 to 5\n" +
                    "move 1 from 1 to 2\n" +
                    "move 21 from 7 to 5\n" +
                    "move 2 from 2 to 1\n" +
                    "move 3 from 1 to 2\n" +
                    "move 15 from 5 to 1\n" +
                    "move 3 from 6 to 7\n" +
                    "move 3 from 4 to 6\n" +
                    "move 3 from 8 to 5\n" +
                    "move 1 from 9 to 3\n" +
                    "move 8 from 7 to 2\n" +
                    "move 6 from 5 to 2\n" +
                    "move 9 from 1 to 6\n" +
                    "move 4 from 7 to 1\n" +
                    "move 2 from 5 to 4\n" +
                    "move 2 from 4 to 3\n" +
                    "move 3 from 5 to 4\n" +
                    "move 17 from 2 to 7\n" +
                    "move 3 from 3 to 5\n" +
                    "move 2 from 4 to 8\n" +
                    "move 1 from 4 to 3\n" +
                    "move 5 from 7 to 9\n" +
                    "move 1 from 3 to 6\n" +
                    "move 4 from 1 to 7\n" +
                    "move 4 from 6 to 7\n" +
                    "move 2 from 5 to 2\n" +
                    "move 1 from 1 to 3\n" +
                    "move 10 from 6 to 4\n" +
                    "move 1 from 3 to 7\n" +
                    "move 20 from 7 to 8\n" +
                    "move 8 from 4 to 8\n" +
                    "move 1 from 2 to 8\n" +
                    "move 4 from 9 to 1\n" +
                    "move 3 from 7 to 4\n" +
                    "move 2 from 4 to 9\n" +
                    "move 2 from 6 to 3\n" +
                    "move 1 from 2 to 8\n" +
                    "move 1 from 7 to 6\n" +
                    "move 1 from 9 to 5\n" +
                    "move 3 from 5 to 9\n" +
                    "move 4 from 9 to 2\n" +
                    "move 1 from 4 to 5\n" +
                    "move 1 from 5 to 3\n" +
                    "move 3 from 2 to 4\n" +
                    "move 1 from 9 to 7\n" +
                    "move 1 from 2 to 1\n" +
                    "move 1 from 7 to 1\n" +
                    "move 11 from 1 to 2\n" +
                    "move 3 from 1 to 7\n" +
                    "move 25 from 8 to 5\n" +
                    "move 1 from 6 to 3\n" +
                    "move 1 from 6 to 2\n" +
                    "move 7 from 8 to 2\n" +
                    "move 9 from 2 to 8\n" +
                    "move 2 from 4 to 7\n" +
                    "move 2 from 5 to 7\n" +
                    "move 2 from 5 to 2\n" +
                    "move 5 from 5 to 1\n" +
                    "move 7 from 5 to 1\n" +
                    "move 2 from 4 to 9\n" +
                    "move 3 from 5 to 6\n" +
                    "move 1 from 1 to 8\n" +
                    "move 1 from 5 to 6\n" +
                    "move 1 from 4 to 7\n" +
                    "move 1 from 9 to 2\n" +
                    "move 3 from 5 to 2\n" +
                    "move 2 from 6 to 9\n" +
                    "move 3 from 9 to 8\n" +
                    "move 1 from 5 to 4\n" +
                    "move 3 from 3 to 9\n" +
                    "move 10 from 1 to 5\n" +
                    "move 4 from 2 to 8\n" +
                    "move 2 from 6 to 1\n" +
                    "move 3 from 9 to 7\n" +
                    "move 1 from 1 to 9\n" +
                    "move 1 from 4 to 3\n" +
                    "move 1 from 9 to 2\n" +
                    "move 9 from 8 to 2\n" +
                    "move 2 from 3 to 7\n" +
                    "move 2 from 7 to 6\n" +
                    "move 3 from 5 to 6\n" +
                    "move 4 from 8 to 6\n" +
                    "move 4 from 8 to 3\n" +
                    "move 4 from 3 to 2\n" +
                    "move 4 from 6 to 8\n" +
                    "move 1 from 7 to 9\n" +
                    "move 2 from 1 to 8\n" +
                    "move 2 from 8 to 3\n" +
                    "move 1 from 9 to 2\n" +
                    "move 13 from 2 to 4\n" +
                    "move 6 from 5 to 7\n" +
                    "move 2 from 5 to 7\n" +
                    "move 10 from 2 to 4\n" +
                    "move 11 from 7 to 8\n" +
                    "move 1 from 6 to 4\n" +
                    "move 4 from 6 to 7\n" +
                    "move 24 from 4 to 9\n" +
                    "move 11 from 7 to 4\n" +
                    "move 1 from 3 to 8\n" +
                    "move 1 from 3 to 5\n" +
                    "move 4 from 4 to 2\n" +
                    "move 5 from 4 to 2\n" +
                    "move 9 from 2 to 5\n" +
                    "move 4 from 9 to 5\n" +
                    "move 1 from 5 to 1\n" +
                    "move 2 from 5 to 7\n" +
                    "move 2 from 2 to 5\n" +
                    "move 1 from 1 to 7\n" +
                    "move 2 from 2 to 3\n" +
                    "move 18 from 9 to 6\n" +
                    "move 9 from 8 to 1\n" +
                    "move 2 from 9 to 5\n" +
                    "move 5 from 1 to 8\n" +
                    "move 2 from 8 to 7\n" +
                    "move 4 from 8 to 4\n" +
                    "move 5 from 8 to 7\n" +
                    "move 10 from 5 to 1\n" +
                    "move 10 from 7 to 4\n" +
                    "move 4 from 5 to 8\n" +
                    "move 14 from 1 to 9\n" +
                    "move 6 from 9 to 8\n" +
                    "move 1 from 5 to 1\n" +
                    "move 12 from 6 to 9\n" +
                    "move 4 from 6 to 8\n" +
                    "move 11 from 8 to 5\n" +
                    "move 1 from 6 to 1\n" +
                    "move 19 from 9 to 7\n" +
                    "move 2 from 3 to 5\n" +
                    "move 13 from 7 to 5\n" +
                    "move 3 from 7 to 1\n" +
                    "move 4 from 8 to 9\n" +
                    "move 2 from 7 to 6\n" +
                    "move 7 from 4 to 8\n" +
                    "move 5 from 8 to 1\n" +
                    "move 1 from 1 to 3\n" +
                    "move 1 from 7 to 2\n" +
                    "move 6 from 1 to 6\n" +
                    "move 1 from 2 to 5\n" +
                    "move 1 from 8 to 1\n" +
                    "move 1 from 8 to 2\n" +
                    "move 2 from 4 to 8\n" +
                    "move 5 from 6 to 1\n" +
                    "move 2 from 4 to 7\n" +
                    "move 2 from 9 to 6\n" +
                    "move 1 from 6 to 5\n" +
                    "move 4 from 6 to 2\n" +
                    "move 1 from 9 to 5\n" +
                    "move 2 from 4 to 5\n" +
                    "move 4 from 2 to 4\n" +
                    "move 2 from 8 to 3\n" +
                    "move 3 from 3 to 2\n" +
                    "move 4 from 1 to 2\n" +
                    "move 2 from 4 to 7\n" +
                    "move 4 from 2 to 3\n" +
                    "move 4 from 1 to 2\n" +
                    "move 13 from 5 to 1\n" +
                    "move 1 from 6 to 2\n" +
                    "move 1 from 1 to 8\n" +
                    "move 15 from 5 to 2\n" +
                    "move 4 from 3 to 1\n" +
                    "move 5 from 4 to 3\n" +
                    "move 1 from 3 to 6\n" +
                    "move 1 from 8 to 7\n" +
                    "move 1 from 9 to 8\n" +
                    "move 1 from 7 to 8\n" +
                    "move 3 from 3 to 2\n" +
                    "move 1 from 8 to 2\n" +
                    "move 1 from 3 to 7\n" +
                    "move 13 from 1 to 4\n" +
                    "move 3 from 5 to 3\n" +
                    "move 1 from 1 to 2\n" +
                    "move 1 from 8 to 5\n" +
                    "move 5 from 7 to 2\n" +
                    "move 1 from 6 to 5\n" +
                    "move 2 from 3 to 4\n" +
                    "move 10 from 2 to 5\n" +
                    "move 1 from 9 to 5\n" +
                    "move 3 from 1 to 8\n" +
                    "move 3 from 8 to 3\n" +
                    "move 11 from 4 to 5\n" +
                    "move 12 from 2 to 8\n" +
                    "move 4 from 4 to 7\n" +
                    "move 10 from 8 to 5\n" +
                    "move 2 from 8 to 1\n" +
                    "move 1 from 7 to 3\n" +
                    "move 1 from 7 to 9\n" +
                    "move 5 from 3 to 7\n" +
                    "move 1 from 9 to 4\n" +
                    "move 7 from 7 to 6\n" +
                    "move 13 from 5 to 8\n" +
                    "move 6 from 6 to 7\n" +
                    "move 5 from 7 to 4\n" +
                    "move 1 from 6 to 4\n" +
                    "move 2 from 4 to 9\n" +
                    "move 1 from 7 to 9\n" +
                    "move 3 from 4 to 3\n" +
                    "move 1 from 3 to 6\n" +
                    "move 4 from 5 to 7";

    private static final String[] EXAMPLE_MOVES_ARRAY = EXAMPLE_MOVES.split("\\R");
    private static final String[] MOVES_ARRAY = MOVES.split("\\R");

    private static final String REGEX = "^move (\\d+) from (\\d+) to (\\d+)$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private static List<Deque<Character>> getStacksForExample() {
        List<Deque<Character>> stacks = new ArrayList<>();

        Deque<Character> stack1 = new ArrayDeque<>();
        stack1.push('Z');
        stack1.push('N');

        Deque<Character> stack2 = new ArrayDeque<>();
        stack2.push('M');
        stack2.push('C');
        stack2.push('D');

        Deque<Character> stack3 = new ArrayDeque<>();
        stack3.push('P');

        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        return stacks;
    }

    private static List<Deque<Character>> getStacksForReal() {
        List<Deque<Character>> stacks = new ArrayList<>();

        Deque<Character> stack1 = new ArrayDeque<>();
        stack1.push('F');
        stack1.push('C');
        stack1.push('P');
        stack1.push('G');
        stack1.push('Q');
        stack1.push('R');

        Deque<Character> stack2 = new ArrayDeque<>();
        stack2.push('W');
        stack2.push('T');
        stack2.push('C');
        stack2.push('P');

        Deque<Character> stack3 = new ArrayDeque<>();
        stack3.push('B');
        stack3.push('H');
        stack3.push('P');
        stack3.push('M');
        stack3.push('C');

        Deque<Character> stack4 = new ArrayDeque<>();
        stack4.push('L');
        stack4.push('T');
        stack4.push('Q');
        stack4.push('S');
        stack4.push('M');
        stack4.push('P');
        stack4.push('R');

        Deque<Character> stack5 = new ArrayDeque<>();
        stack5.push('P');
        stack5.push('H');
        stack5.push('J');
        stack5.push('Z');
        stack5.push('V');
        stack5.push('G');
        stack5.push('N');

        Deque<Character> stack6 = new ArrayDeque<>();
        stack6.push('D');
        stack6.push('P');
        stack6.push('J');

        Deque<Character> stack7 = new ArrayDeque<>();
        stack7.push('L');
        stack7.push('G');
        stack7.push('P');
        stack7.push('Z');
        stack7.push('F');
        stack7.push('J');
        stack7.push('T');
        stack7.push('R');

        Deque<Character> stack8 = new ArrayDeque<>();
        stack8.push('N');
        stack8.push('L');
        stack8.push('H');
        stack8.push('C');
        stack8.push('F');
        stack8.push('P');
        stack8.push('T');
        stack8.push('J');

        Deque<Character> stack9 = new ArrayDeque<>();
        stack9.push('G');
        stack9.push('V');
        stack9.push('Z');
        stack9.push('Q');
        stack9.push('H');
        stack9.push('T');
        stack9.push('C');
        stack9.push('W');

        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
        stacks.add(stack5);
        stacks.add(stack6);
        stacks.add(stack7);
        stacks.add(stack8);
        stacks.add(stack9);
        return stacks;
    }

    private static List<Deque<Character>> executeCommands(List<Deque<Character>> stacks, String[] commands, boolean preserveOrder) {
        for (String command : commands) {
            Matcher matcher = PATTERN.matcher(command);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Input does not match pattern.");
            }
            int count = parseInt(matcher.group(1));
            int a = parseInt(matcher.group(2));
            int b = parseInt(matcher.group(3));
            Deque<Character> A = stacks.get(a - 1);
            Deque<Character> B = stacks.get(b - 1);

            if (!preserveOrder) {
                for (int i = 0; i < count; i++) {
                    B.push(A.pop());
                }
            } else {
                Deque<Character> temp = new ArrayDeque<>();
                for (int i = 0; i < count; i++) {
                    temp.push(A.pop());
                }
                for (int i = 0; i < count; i++) {
                    B.push(temp.pop());
                }
            }
        }
        return stacks;
    }

    private static String getHighestFromEachStack(List<Deque<Character>> stacks) {
        StringBuilder sb = new StringBuilder();
        for (Deque<Character> stack : stacks) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 05: Examples for puzzle 1 ###");
        System.out.println("Highest from each stack: " + getHighestFromEachStack(executeCommands(getStacksForExample(), EXAMPLE_MOVES_ARRAY, false)));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 05: Examples for puzzle 2 ###");
        System.out.println("Highest from each stack: " + getHighestFromEachStack(executeCommands(getStacksForExample(), EXAMPLE_MOVES_ARRAY, true)));
    }

    public static String doPuzzle1() {
        return getHighestFromEachStack(executeCommands(getStacksForReal(), MOVES_ARRAY, false));
    }

    public static String doPuzzle2() {
        return getHighestFromEachStack(executeCommands(getStacksForReal(), MOVES_ARRAY, true));
    }
}
