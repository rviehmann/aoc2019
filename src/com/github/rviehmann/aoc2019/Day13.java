package com.github.rviehmann.aoc2019;

import com.github.rviehmann.aoc2019.Intcode.Memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.github.rviehmann.aoc2019.Intcode.BLOCKING_INPUT;
import static com.github.rviehmann.aoc2019.Intcode.interpretIntcode;
import static java.lang.Math.toIntExact;

public class Day13 {

    // From: https://adventofcode.com/2019/day/13/input
    private static final long[] MEMORY = {
            1, 380, 379, 385, 1008, 2751, 248387, 381, 1005, 381, 12, 99, 109, 2752, 1102, 1, 0, 383, 1101, 0, 0, 382, 21001, 382, 0, 1, 20101, 0, 383, 2, 21102, 1, 37, 0, 1105, 1, 578, 4, 382, 4, 383, 204, 1, 1001, 382, 1, 382, 1007, 382, 44, 381, 1005, 381, 22, 1001, 383, 1, 383, 1007, 383, 24, 381, 1005, 381, 18, 1006, 385, 69, 99, 104, -1, 104, 0, 4, 386, 3, 384, 1007, 384, 0, 381, 1005, 381, 94, 107, 0, 384, 381, 1005, 381, 108, 1105, 1, 161, 107, 1, 392, 381, 1006, 381, 161, 1102, -1, 1, 384, 1106, 0, 119, 1007, 392, 42, 381, 1006, 381, 161, 1102, 1, 1, 384, 21001, 392, 0, 1, 21101, 0, 22, 2, 21102, 1, 0, 3, 21101, 0, 138, 0, 1105, 1, 549, 1, 392, 384, 392, 20101, 0, 392, 1, 21102, 22, 1, 2, 21102, 3, 1, 3, 21101, 0, 161, 0, 1106, 0, 549, 1102, 0, 1, 384, 20001, 388, 390, 1, 20101, 0, 389, 2, 21102, 180, 1, 0, 1106, 0, 578, 1206, 1, 213, 1208, 1, 2, 381, 1006, 381, 205, 20001, 388, 390, 1, 21001, 389, 0, 2, 21101, 0, 205, 0, 1106, 0, 393, 1002, 390, -1, 390, 1101, 1, 0, 384, 20102, 1, 388, 1, 20001, 389, 391, 2, 21102, 228, 1, 0, 1105, 1, 578, 1206, 1, 261, 1208, 1, 2, 381, 1006, 381, 253, 20102, 1, 388, 1, 20001, 389, 391, 2, 21102, 1, 253, 0, 1105, 1, 393, 1002, 391, -1, 391, 1102, 1, 1, 384, 1005, 384, 161, 20001, 388, 390, 1, 20001, 389, 391, 2, 21102, 1, 279, 0, 1105, 1, 578, 1206, 1, 316, 1208, 1, 2, 381, 1006, 381, 304, 20001, 388, 390, 1, 20001, 389, 391, 2, 21101, 0, 304, 0, 1106, 0, 393, 1002, 390, -1, 390, 1002, 391, -1, 391, 1102, 1, 1, 384, 1005, 384, 161, 21002, 388, 1, 1, 21002, 389, 1, 2, 21102, 0, 1, 3, 21101, 0, 338, 0, 1106, 0, 549, 1, 388, 390, 388, 1, 389, 391, 389, 21001, 388, 0, 1, 20102, 1, 389, 2, 21101, 4, 0, 3, 21101, 0, 365, 0, 1105, 1, 549, 1007, 389, 23, 381, 1005, 381, 75, 104, -1, 104, 0, 104, 0, 99, 0, 1, 0, 0, 0, 0, 0, 0, 412, 20, 19, 1, 1, 22, 109, 3, 22102, 1, -2, 1, 22101, 0, -1, 2, 21101, 0, 0, 3, 21101, 0, 414, 0, 1105, 1, 549, 21201, -2, 0, 1, 21201, -1, 0, 2, 21102, 429, 1, 0, 1106, 0, 601, 2101, 0, 1, 435, 1, 386, 0, 386, 104, -1, 104, 0, 4, 386, 1001, 387, -1, 387, 1005, 387, 451, 99, 109, -3, 2105, 1, 0, 109, 8, 22202, -7, -6, -3, 22201, -3, -5, -3, 21202, -4, 64, -2, 2207, -3, -2, 381, 1005, 381, 492, 21202, -2, -1, -1, 22201, -3, -1, -3, 2207, -3, -2, 381, 1006, 381, 481, 21202, -4, 8, -2, 2207, -3, -2, 381, 1005, 381, 518, 21202, -2, -1, -1, 22201, -3, -1, -3, 2207, -3, -2, 381, 1006, 381, 507, 2207, -3, -4, 381, 1005, 381, 540, 21202, -4, -1, -1, 22201, -3, -1, -3, 2207, -3, -4, 381, 1006, 381, 529, 21202, -3, 1, -7, 109, -8, 2106, 0, 0, 109, 4, 1202, -2, 44, 566, 201, -3, 566, 566, 101, 639, 566, 566, 2101, 0, -1, 0, 204, -3, 204, -2, 204, -1, 109, -4, 2105, 1, 0, 109, 3, 1202, -1, 44, 594, 201, -2, 594, 594, 101, 639, 594, 594, 20102, 1, 0, -2, 109, -3, 2105, 1, 0, 109, 3, 22102, 24, -2, 1, 22201, 1, -1, 1, 21101, 0, 541, 2, 21102, 695, 1, 3, 21102, 1056, 1, 4, 21102, 1, 630, 0, 1105, 1, 456, 21201, 1, 1695, -2, 109, -3, 2105, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 2, 0, 0, 2, 2, 0, 2, 0, 2, 0, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 1, 1, 0, 0, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 2, 0, 1, 1, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 2, 0, 2, 0, 2, 2, 0, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 0, 0, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 2, 0, 0, 0, 1, 1, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 1, 1, 0, 0, 2, 0, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 2, 2, 0, 2, 0, 0, 2, 2, 2, 0, 2, 0, 1, 1, 0, 2, 2, 2, 0, 0, 2, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 2, 0, 1, 1, 0, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 0, 2, 2, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 1, 1, 0, 2, 0, 0, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 0, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 0, 2, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2, 0, 0, 2, 0, 0, 0, 1, 1, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 0, 2, 2, 0, 2, 2, 2, 0, 1, 1, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0, 2, 0, 2, 2, 2, 0, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 2, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 1, 1, 0, 2, 0, 2, 2, 0, 2, 2, 0, 0, 2, 2, 0, 2, 2, 0, 2, 0, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 0, 0, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 2, 0, 1, 1, 0, 2, 2, 2, 2, 0, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 2, 0, 0, 2, 0, 2, 0, 1, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 2, 2, 0, 2, 2, 0, 2, 0, 0, 0, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 0, 1, 1, 0, 0, 2, 2, 0, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 43, 34, 19, 38, 49, 68, 88, 56, 54, 52, 29, 1, 43, 97, 67, 20, 16, 38, 59, 54, 42, 95, 82, 45, 46, 6, 37, 75, 44, 45, 68, 23, 64, 81, 23, 20, 23, 7, 16, 74, 92, 42, 42, 95, 63, 5, 59, 5, 48, 76, 2, 35, 18, 58, 7, 86, 31, 20, 12, 37, 9, 44, 81, 62, 26, 39, 1, 28, 2, 10, 34, 9, 83, 73, 16, 43, 88, 47, 40, 54, 29, 77, 56, 97, 98, 94, 62, 84, 16, 88, 74, 91, 41, 43, 63, 88, 93, 37, 54, 44, 64, 64, 96, 76, 70, 47, 26, 97, 18, 23, 87, 62, 27, 64, 82, 34, 28, 64, 69, 9, 44, 64, 27, 39, 70, 9, 27, 12, 9, 85, 69, 96, 54, 28, 47, 72, 1, 87, 63, 94, 3, 70, 28, 95, 3, 54, 82, 13, 39, 18, 13, 3, 73, 7, 46, 98, 72, 96, 31, 61, 60, 6, 50, 96, 32, 94, 59, 34, 32, 87, 70, 34, 48, 19, 78, 7, 94, 42, 6, 40, 69, 72, 44, 76, 84, 60, 66, 86, 74, 40, 40, 86, 48, 48, 58, 31, 46, 96, 4, 94, 51, 76, 2, 39, 89, 52, 58, 4, 88, 26, 31, 74, 5, 53, 97, 81, 38, 41, 93, 90, 11, 8, 89, 92, 39, 71, 33, 64, 9, 70, 6, 71, 89, 66, 8, 2, 48, 78, 4, 22, 61, 25, 51, 79, 62, 97, 87, 22, 62, 95, 17, 98, 50, 45, 87, 41, 8, 44, 75, 26, 65, 3, 96, 78, 10, 74, 52, 55, 66, 88, 10, 17, 9, 15, 60, 79, 6, 11, 87, 36, 61, 48, 36, 56, 85, 95, 87, 97, 67, 78, 81, 69, 71, 26, 65, 54, 69, 89, 72, 9, 83, 17, 97, 59, 75, 40, 90, 6, 17, 69, 84, 97, 69, 39, 67, 49, 97, 11, 37, 48, 51, 97, 37, 48, 41, 26, 93, 82, 5, 29, 63, 33, 28, 55, 95, 1, 9, 85, 72, 52, 11, 8, 77, 68, 6, 56, 45, 55, 59, 35, 98, 24, 93, 3, 82, 32, 13, 79, 31, 64, 45, 18, 37, 4, 13, 96, 24, 52, 61, 94, 29, 48, 17, 36, 63, 22, 95, 8, 75, 31, 81, 49, 4, 80, 78, 19, 85, 15, 92, 29, 31, 33, 70, 60, 59, 71, 25, 23, 70, 23, 26, 35, 60, 60, 11, 20, 31, 8, 37, 23, 48, 93, 67, 56, 95, 6, 76, 41, 91, 14, 63, 44, 97, 67, 11, 54, 40, 52, 56, 49, 56, 64, 33, 84, 86, 14, 80, 4, 69, 15, 85, 83, 84, 59, 68, 92, 39, 25, 30, 15, 45, 59, 48, 12, 25, 39, 59, 81, 62, 31, 66, 90, 41, 95, 5, 60, 68, 37, 92, 51, 47, 89, 73, 68, 40, 65, 63, 87, 77, 4, 74, 45, 2, 79, 45, 19, 50, 35, 50, 51, 18, 65, 21, 91, 56, 20, 58, 89, 18, 12, 21, 59, 52, 56, 69, 69, 81, 12, 93, 89, 43, 96, 43, 27, 23, 65, 64, 37, 23, 17, 55, 1, 20, 45, 62, 2, 50, 2, 67, 46, 73, 32, 93, 79, 8, 22, 23, 87, 70, 52, 42, 98, 73, 94, 2, 23, 14, 70, 16, 86, 88, 21, 11, 25, 16, 14, 80, 84, 17, 40, 83, 17, 3, 8, 16, 70, 37, 76, 89, 68, 34, 37, 37, 48, 77, 93, 17, 47, 7, 77, 49, 27, 48, 6, 65, 84, 81, 57, 96, 90, 94, 31, 17, 46, 59, 8, 69, 92, 79, 91, 14, 60, 91, 49, 83, 11, 65, 11, 96, 90, 84, 21, 40, 8, 94, 8, 23, 79, 20, 2, 88, 42, 86, 80, 4, 85, 51, 34, 77, 75, 78, 94, 26, 83, 16, 70, 45, 31, 70, 96, 48, 32, 76, 93, 66, 9, 73, 63, 42, 84, 41, 1, 19, 79, 28, 81, 71, 76, 26, 76, 74, 35, 39, 52, 18, 17, 81, 23, 76, 14, 94, 98, 72, 67, 62, 39, 54, 30, 8, 24, 43, 53, 69, 7, 92, 65, 93, 71, 58, 26, 73, 52, 75, 93, 8, 10, 71, 87, 12, 4, 56, 4, 70, 72, 83, 24, 55, 53, 34, 89, 75, 45, 95, 29, 96, 4, 61, 76, 3, 69, 10, 36, 10, 79, 41, 18, 74, 94, 96, 32, 39, 12, 32, 6, 32, 93, 69, 52, 75, 76, 22, 74, 45, 11, 51, 22, 79, 96, 71, 68, 9, 72, 48, 77, 11, 67, 50, 56, 61, 63, 74, 81, 48, 31, 72, 35, 59, 41, 4, 85, 86, 3, 23, 64, 79, 10, 85, 97, 67, 66, 87, 41, 48, 17, 70, 82, 8, 95, 42, 36, 30, 94, 57, 94, 34, 88, 95, 64, 64, 17, 13, 92, 9, 53, 52, 53, 98, 21, 87, 13, 34, 83, 2, 72, 74, 23, 87, 61, 16, 20, 28, 46, 57, 82, 37, 83, 74, 79, 7, 15, 15, 59, 28, 31, 16, 6, 22, 66, 41, 93, 82, 71, 96, 41, 32, 4, 30, 50, 52, 30, 17, 92, 71, 10, 59, 73, 91, 39, 20, 19, 53, 15, 24, 39, 79, 34, 29, 41, 91, 57, 49, 39, 24, 12, 79, 24, 63, 66, 41, 15, 54, 94, 90, 37, 4, 65, 82, 54, 28, 46, 50, 64, 22, 28, 60, 58, 8, 41, 68, 41, 28, 2, 19, 9, 2, 11, 87, 76, 10, 63, 45, 72, 12, 84, 12, 35, 50, 9, 46, 28, 40, 29, 80, 42, 39, 80, 77, 37, 6, 11, 13, 24, 95, 60, 65, 18, 97, 86, 6, 64, 67, 53, 18, 40, 91, 98, 48, 49, 47, 31, 76, 68, 74, 93, 14, 45, 25, 29, 68, 2, 49, 53, 56, 87, 9, 87, 87, 64, 14, 69, 7, 55, 55, 63, 48, 78, 86, 73, 45, 31, 61, 83, 93, 64, 68, 22, 58, 80, 10, 98, 63, 11, 38, 64, 82, 61, 8, 61, 51, 39, 5, 14, 69, 65, 64, 90, 61, 55, 37, 41, 31, 40, 64, 32, 27, 15, 19, 58, 33, 38, 90, 39, 6, 13, 14, 89, 52, 84, 76, 1, 96, 45, 5, 61, 69, 44, 43, 98, 80, 1, 72, 63, 23, 10, 77, 57, 50, 74, 93, 7, 15, 37, 34, 40, 88, 7, 248387
    };

    private static final Long TILE_EMPTY = 0L;
    private static final Long TILE_WALL = 1L;
    private static final Long TILE_BLOCK = 2L;
    private static final Long TILE_HOR_PADDLE = 3L;
    private static final Long TILE_BALL = 4L;

    private static final Character CHAR_EMPTY = ' ';
    private static final Character CHAR_WALL = '█';
    private static final Character CHAR_BLOCK = '#';
    private static final Character CHAR_HOR_PADDLE = '-';
    private static final Character CHAR_BALL = '*';

    private static final Map<Long, Character> MAP_TILE_ID_TO_CHAR = new HashMap<>();

    private static final Long JOYSTICK_LEFT = -1L;
    private static final Long JOYSTICK_CENTER = 0L;
    private static final Long JOYSTICK_RIGHT = 1L;

    // Not in the requirements, just to get the Arcade to shut itself down cleanly.
    private static final long SHUT_DOWN_COMMAND = Long.MAX_VALUE;

    static {
        MAP_TILE_ID_TO_CHAR.put(TILE_EMPTY, CHAR_EMPTY);
        MAP_TILE_ID_TO_CHAR.put(TILE_WALL, CHAR_WALL);
        MAP_TILE_ID_TO_CHAR.put(TILE_BLOCK, CHAR_BLOCK);
        MAP_TILE_ID_TO_CHAR.put(TILE_HOR_PADDLE, CHAR_HOR_PADDLE);
        MAP_TILE_ID_TO_CHAR.put(TILE_BALL, CHAR_BALL);
    }

    public static class ArcadeControlComputer implements Runnable {

        private final Memory memory;
        private final BlockingQueue<Long> inputQueue;
        private final BlockingQueue<Long> outputQueue;
        private final BlockingQueue<Long> inputRequestQueue;

        public ArcadeControlComputer(Memory memory, BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue, BlockingQueue<Long> inputRequestQueue) {
            this.memory = memory;
            this.inputQueue = inputQueue;
            this.outputQueue = outputQueue;
            this.inputRequestQueue = inputRequestQueue;
        }

        @Override
        public void run() {
            try {
                interpretIntcode(memory, inputQueue, outputQueue, BLOCKING_INPUT, inputRequestQueue, null);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException caught: " + e);
            }
        }
    }

    public static class PaintedTile {

        public final long x;
        public final long y;
        public final long tileId;

        public PaintedTile(long x, long y, long tileId) {
            this.x = x;
            this.y = y;
            this.tileId = tileId;
        }

        public boolean hasPosition(long x, long y) {
            return this.x == x && this.y == y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !obj.getClass().equals(this.getClass())) {
                return false;
            }

            PaintedTile other = (PaintedTile) obj;
            // Yes, it is intentional that we only compare the coordinates, but not the tileId. Otherwise, getPaintedTilesMinified() would not work at all.
            return x == other.x && y == other.y;
        }
    }

    public static class Arcade implements Runnable {

        private long score = 0;

        private long paddleX = -1;
        private long paddleY = -1;

        private long ballX = -1;
        private long ballY = -1;

        private long leftMost = 0;
        private long rightMost = 0;
        private long topMost = 0;
        private long bottomMost = 0;

        private final List<PaintedTile> paintedTiles = new ArrayList<>();
        private final BlockingQueue<Long> inputQueue;
        private final BlockingQueue<Long> outputQueue;

        public Arcade(BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue) {
            this.inputQueue = inputQueue;
            this.outputQueue = outputQueue;
        }

        public long getScore() {
            return score;
        }

        public long getPaddleX() {
            return paddleX;
        }

        public long getPaddleY() {
            return paddleY;
        }

        public long getBallX() {
            return ballX;
        }

        public long getBallY() {
            return ballY;
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
                    long tileId = getTileIdOfTile(x, y);
                    Character charForTile = MAP_TILE_ID_TO_CHAR.get(tileId);
                    if (charForTile != null) {
                        sb.append(charForTile);
                    } else {
                        throw new IllegalArgumentException("Invalid tileId: tileId=" + tileId);
                    }
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }

        private void paintTile(long x, long y, long tileId) {
            if (x == -1 && y == 0) {
                score = tileId;
            } else {
                paintedTiles.add(new PaintedTile(x, y, tileId));
                if (x < leftMost) {
                    leftMost = x;
                }
                if (x > rightMost) {
                    rightMost = x;
                }
                if (y < topMost) {
                    topMost = y;
                }
                if (y > bottomMost) {
                    bottomMost = y;
                }
                if (tileId == TILE_HOR_PADDLE) {
                    paddleX = x;
                    paddleY = y;
                }
                if (tileId == TILE_BALL) {
                    ballX = x;
                    ballY = y;
                }
            }
        }

        private long getTileIdOfTile(long x, long y) {
            if (paintedTiles.size() != 0) {
                // Iterate from the end, since if we paint the same tile more than once, only the last color will be visible.
                for (int index = paintedTiles.size() - 1; index >= 0; index--) {
                    PaintedTile tile = paintedTiles.get(index);
                    if (tile.hasPosition(x, y)) {
                        return tile.tileId;
                    }
                }
            }
            return TILE_EMPTY;
        }

        public List<PaintedTile> getPaintedTiles() {
            return paintedTiles;
        }

        public List<PaintedTile> getPaintedTilesMinified() {
            List<PaintedTile> uniqueTiles = new ArrayList<>();

            // Iterate from the end, since if we paint the same tile more than once, only the last color will be visible.
            for (int index = paintedTiles.size() - 1; index >= 0; index--) {
                PaintedTile tile = paintedTiles.get(index);
                if (!uniqueTiles.contains(tile)) {
                    uniqueTiles.add(tile);
                }
            }

            Collections.reverse(uniqueTiles);
            return uniqueTiles;
        }

        public long[] getCountForEachPanelId() {
            long[] count = new long[5];
            List<PaintedTile> paintedTilesMinified = getPaintedTilesMinified();
            paintedTilesMinified.forEach(tile -> count[toIntExact(tile.tileId)]++);
            return count;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    long posX = inputQueue.take();
                    if (posX == SHUT_DOWN_COMMAND) {
                        return;
                    }

                    long posY = inputQueue.take();
                    if (posY == SHUT_DOWN_COMMAND) {
                        return;
                    }

                    long tileId = inputQueue.take();
                    if (tileId == SHUT_DOWN_COMMAND) {
                        return;
                    }

                    paintTile(posX, posY, tileId);
                } catch (InterruptedException e) {
                    System.err.println("InterruptedException caught: " + e);
                }
            }
        }
    }

    public static class ArcadeJoystick implements Runnable {

        private final Arcade arcade;
        private final BlockingQueue<Long> inputRequestQueue;
        private final BlockingQueue<Long> outputQueue;

        public ArcadeJoystick(Arcade arcade, BlockingQueue<Long> inputRequestQueue, BlockingQueue<Long> outputQueue) {
            this.arcade = arcade;
            this.inputRequestQueue = inputRequestQueue;
            this.outputQueue = outputQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // The value (if unequal to SHUT_DOWN_COMMAND) is not relevant, it only signifies that the interpreter will be waiting for input.
                    long inputRequest = inputRequestQueue.take();
                    if (inputRequest == SHUT_DOWN_COMMAND) {
                        return;
                    }

                    // Make sure that Arcade is also completely finished with everything.
                    Thread.sleep(50);

                    long ballX = arcade.getBallX();
                    long paddleX = arcade.getPaddleX();

                    if (ballX < paddleX) {
                        // arcade.debugWholePlayingArea();
                        System.out.println("score: " + arcade.getScore());
                        System.out.println("Setting joystick to left, ballX: " + ballX + ", paddleX: " + paddleX);
                        outputQueue.put(JOYSTICK_LEFT);
                    } else if (ballX > paddleX) {
                        // arcade.debugWholePlayingArea();
                        System.out.println("score: " + arcade.getScore());
                        System.out.println("Setting joystick to right, ballX: " + ballX + ", paddleX: " + paddleX);
                        outputQueue.put(JOYSTICK_RIGHT);
                    } else {
                        // arcade.debugWholePlayingArea();
                        System.out.println("score: " + arcade.getScore());
                        System.out.println("Setting joystick to center, ballX: " + ballX + ", paddleX: " + paddleX);
                        outputQueue.put(JOYSTICK_CENTER);
                    }
                } catch (InterruptedException e) {
                    System.err.println("InterruptedException caught: " + e);
                }
            }
        }
    }

    private static Arcade runArcade(Memory memory) throws InterruptedException {
        BlockingQueue<Long>[] queues = new BlockingQueue[3];
        for (int i = 0; i < 3; i++) {
            queues[i] = new LinkedBlockingQueue<>();
        }
        Thread[] threads = new Thread[3];
        ArcadeControlComputer arcadeControlComputer = new ArcadeControlComputer(memory, queues[0], queues[1], queues[2]);
        Arcade arcade = new Arcade(queues[1], queues[0]);
        ArcadeJoystick arcadeJoystick = new ArcadeJoystick(arcade, queues[2], queues[0]);
        threads[0] = new Thread(arcadeControlComputer);
        threads[1] = new Thread(arcade);
        threads[2] = new Thread(arcadeJoystick);

        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }

        // Wait until ArcadeControlComputer is finished.
        threads[0].join();

        // Make sure that Arcade is also completely finished with everything.
        Thread.sleep(50);

        // Afterwards, shut it down.
        queues[1].put(SHUT_DOWN_COMMAND);
        queues[2].put(SHUT_DOWN_COMMAND);

        System.out.println("leftMost: " + arcade.getLeftMost());
        System.out.println("rightMost: " + arcade.getRightMost());
        System.out.println("topMost: " + arcade.getTopMost());
        System.out.println("bottomMost: " + arcade.getBottomMost());
        System.out.println("paintedTiles: " + arcade.getPaintedTiles().size());
        System.out.println("paintedTilesMinified: " + arcade.getPaintedTilesMinified().size());
        System.out.println("score: " + arcade.getScore());

        long[] count = arcade.getCountForEachPanelId();
        long tileId = 0;
        for (Long c : count) {
            System.out.println("tileId: " + tileId + ", char: " + MAP_TILE_ID_TO_CHAR.get(tileId) + ", count: " + c);
            tileId++;
        }
        arcade.debugWholePlayingArea();
        return arcade;
    }

    public static long doPuzzle1() throws InterruptedException {
        Memory memory = new Memory(MEMORY);
        Arcade arcade = runArcade(memory);
        long[] count = arcade.getCountForEachPanelId();
        return count[toIntExact(TILE_BLOCK)];
    }

    public static long doPuzzle2() throws InterruptedException {
        Memory memory = new Memory(MEMORY);
        // Memory address 0 represents the number of quarters that have been inserted; set it to 2 to play for free.
        memory.setRaw(0, 2);
        Arcade arcade = runArcade(memory);
        return arcade.getScore();
    }
}
