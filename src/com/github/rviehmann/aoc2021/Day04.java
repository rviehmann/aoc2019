package com.github.rviehmann.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class Day04 {

    private static final long[] SAMPLE_NUMBERS = {
            7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1
    };

    private static final String SAMPLE_BOARDS =
            "22 13 17 11  0\n" +
                    " 8  2 23  4 24\n" +
                    "21  9 14 16  7\n" +
                    " 6 10  3 18  5\n" +
                    " 1 12 20 15 19\n" +
                    "\n" +
                    " 3 15  0  2 22\n" +
                    " 9 18 13 17  5\n" +
                    "19  8  7 25 23\n" +
                    "20 11 10 24  4\n" +
                    "14 21 16 12  6\n" +
                    "\n" +
                    "14 21 17 24  4\n" +
                    "10 16 15  9 19\n" +
                    "18  8 23 26 20\n" +
                    "22 11 13  6  5\n" +
                    " 2  0 12  3  7";

    private static final long[] INPUT_NUMBERS = {
            59, 91, 13, 82, 8, 32, 74, 96, 55, 51, 19, 47, 46, 44, 5, 21, 95, 71, 48, 60, 68, 81, 80, 14, 23, 28, 26, 78, 12, 22, 49, 1, 83, 88, 39, 53, 84, 37, 93, 24, 42, 7, 56, 20, 92, 90, 25, 36, 34, 52, 27, 50, 85, 75, 89, 63, 33, 4, 66, 17, 98, 57, 3, 9, 54, 0, 94, 29, 79, 61, 45, 86, 16, 30, 77, 76, 6, 38, 70, 62, 72, 43, 69, 35, 18, 97, 73, 41, 40, 64, 67, 31, 58, 11, 15, 87, 65, 2, 10, 99
    };

    private static final String INPUT_BOARDS =
            "42 47 77 49 67\n" +
                    "64 82 32 94 78\n" +
                    "96 62 45 11 43\n" +
                    "55 92 81 66 88\n" +
                    "12 95 19 24 71\n" +
                    "\n" +
                    "96 40 25 11 89\n" +
                    "84 33 10 55 16\n" +
                    "22 90 54 42 86\n" +
                    "73 13 70 32 56\n" +
                    "18 78 41 81 50\n" +
                    "\n" +
                    "58 48 24  3 40\n" +
                    "38 61 95 39 36\n" +
                    "45 21  2 90 57\n" +
                    "42 41 22 83 67\n" +
                    "73 77 59  0 85\n" +
                    "\n" +
                    " 2 11 17 28 22\n" +
                    "93  1 27 85 13\n" +
                    "37 72 54 94 86\n" +
                    "25 40 57 60 71\n" +
                    "38 46 83 30 92\n" +
                    "\n" +
                    "14 88 34 10 87\n" +
                    "31 47 46 72 28\n" +
                    "26  1 50 81 76\n" +
                    "98  2 17 59 39\n" +
                    "80 99 84 62 44\n" +
                    "\n" +
                    "22 80 28 31 27\n" +
                    "21 92  5 64 65\n" +
                    "73 85 35 66 76\n" +
                    "11 29 75 81 37\n" +
                    "90 69 53 97 67\n" +
                    "\n" +
                    "84 89 93  1 37\n" +
                    "99 13 17 52 81\n" +
                    "31  0 28 12 91\n" +
                    "92 20 41 36 35\n" +
                    "40 26 23 51 64\n" +
                    "\n" +
                    "72 50 20 87 82\n" +
                    "28 88 15  9 24\n" +
                    "47 54  3 84 76\n" +
                    "14 34 33 65 78\n" +
                    "53 60 23 21 77\n" +
                    "\n" +
                    "76 17  7 38 25\n" +
                    "72 82 96 83  2\n" +
                    "94 86 56 43 97\n" +
                    "26 93 59 48 55\n" +
                    " 3 85 29 19  4\n" +
                    "\n" +
                    "58 95 50 21 47\n" +
                    "85 33 76 97 62\n" +
                    "39 70 42 25 71\n" +
                    "53 79 87 41 91\n" +
                    "45 27  3 92 88\n" +
                    "\n" +
                    " 9 38 32 18 56\n" +
                    "40  5 62 70 41\n" +
                    " 1 30 39 90 79\n" +
                    "69 84 74 59 35\n" +
                    "65 54 21 27 73\n" +
                    "\n" +
                    "92 85 69  5 29\n" +
                    "91 74  1 26 60\n" +
                    "63 87 37 71 62\n" +
                    "59 15 56 45 95\n" +
                    "86 67 39 34 89\n" +
                    "\n" +
                    "89 30 39 15  3\n" +
                    "71 37 38 56 77\n" +
                    "67 13 41 85 36\n" +
                    "32  7 12 97 87\n" +
                    "50 42 21 33 23\n" +
                    "\n" +
                    "11 40 38 96  6\n" +
                    "88 39 64 33 86\n" +
                    "51 79 63 12  3\n" +
                    "47  7 69 41 80\n" +
                    "10 28 91 37 89\n" +
                    "\n" +
                    "90 14 52 49 93\n" +
                    "62  2 35 46 42\n" +
                    "87  3 85 29 68\n" +
                    "15 96 72 10 59\n" +
                    "50 80  5 20 44\n" +
                    "\n" +
                    "58 92 96 48 74\n" +
                    " 2 17 54 93 32\n" +
                    "39 46 76 91 10\n" +
                    "26 21 52 11 65\n" +
                    "23 36 78 77 43\n" +
                    "\n" +
                    "79  5 10 41 97\n" +
                    "80 45 81 32 87\n" +
                    "18 78 21 92 74\n" +
                    "14 49 94 59 37\n" +
                    " 6 62 76 57 35\n" +
                    "\n" +
                    "52 59  3 78 89\n" +
                    " 6 49 90 54 15\n" +
                    "82 30 64  7 85\n" +
                    "51 50 73 71 60\n" +
                    "17 26 18 98 94\n" +
                    "\n" +
                    "71 86  8  0 34\n" +
                    "77 81 47 13 53\n" +
                    "99 93 29 28 85\n" +
                    "76 49 51  1  4\n" +
                    "90 65 88 16 98\n" +
                    "\n" +
                    "89 32 50 77 71\n" +
                    "35 34 66 60 27\n" +
                    "52 24 57 42 37\n" +
                    "31  1 70 13 62\n" +
                    "33 91 61  7 30\n" +
                    "\n" +
                    "55 10 86 85 46\n" +
                    " 2 96 68 40 24\n" +
                    "44 49  6 13 99\n" +
                    "93 36 31 52 67\n" +
                    "84 81 48 14  1\n" +
                    "\n" +
                    "84 92 66 24 95\n" +
                    "70 26 67 25 32\n" +
                    "52 11 55 76 78\n" +
                    "82 33 83 93 37\n" +
                    "21 68 15 94 10\n" +
                    "\n" +
                    "95 45 58 25 27\n" +
                    "15 93 10 76 86\n" +
                    "59 90 87 99 72\n" +
                    " 4 71 31  3 37\n" +
                    "52 35 83 54 33\n" +
                    "\n" +
                    "73 39 32 89 84\n" +
                    " 5 70 47 61 18\n" +
                    "16 92 78 26  8\n" +
                    "94 37 14 53 27\n" +
                    "44 30 68 77 63\n" +
                    "\n" +
                    "94 24 54  1 49\n" +
                    "27 90 84 79 69\n" +
                    " 6 70 25 36 80\n" +
                    "14 16 53 92 44\n" +
                    "61 33 55 89 23\n" +
                    "\n" +
                    "85 92 37 64 59\n" +
                    "70 67 55 60 27\n" +
                    "69 62 45 48 57\n" +
                    "30 75 99 18 43\n" +
                    "82  7 10 19 38\n" +
                    "\n" +
                    "78 46 32 95 58\n" +
                    "67 66 39 77 62\n" +
                    "50 53 47 55 70\n" +
                    "23 42 38 22 60\n" +
                    "30 29 10 40 84\n" +
                    "\n" +
                    "27 89 46 75 73\n" +
                    "84 48  7 12 82\n" +
                    "34 15 81 98 65\n" +
                    "24 83 87  4 86\n" +
                    " 9 55 47  8 36\n" +
                    "\n" +
                    "30 70  5 21 82\n" +
                    "95 11 49 37 48\n" +
                    "68 74 41 63 85\n" +
                    "93 35  7  8 80\n" +
                    "45 31 53 55 26\n" +
                    "\n" +
                    "98 75 47  9 18\n" +
                    "44 97 26 73 64\n" +
                    "62 99 13 43  0\n" +
                    "51 37 10 74 94\n" +
                    "35 68 22 76 83\n" +
                    "\n" +
                    "35  9 82 95 40\n" +
                    "30 10 99  7 47\n" +
                    "12 77 54 25 34\n" +
                    "73 97 38 11 17\n" +
                    "70 41 87 29 57\n" +
                    "\n" +
                    "95 50 69 58 93\n" +
                    "89 52 23 24  8\n" +
                    "20 53 43 22 84\n" +
                    "60  4 19 64  6\n" +
                    "92 21 10 26 85\n" +
                    "\n" +
                    "37 48 98 80 77\n" +
                    "15 76 12 75 52\n" +
                    "94 41 40 69 63\n" +
                    "92 28 74 14 17\n" +
                    " 3 62 86 19 82\n" +
                    "\n" +
                    "40  1 33 53 64\n" +
                    "60  9 76 50 44\n" +
                    " 0 34 51 98 12\n" +
                    " 6 84 72 86 15\n" +
                    "18 80 13  5 23\n" +
                    "\n" +
                    "40 86 96 61 34\n" +
                    "13  0 39 88 32\n" +
                    "36 91 82 51 97\n" +
                    "83 87 63 94 26\n" +
                    "53 30 23 14 45\n" +
                    "\n" +
                    "93 20 29  9 66\n" +
                    "25 72 10 54 30\n" +
                    "31 51 41 15 47\n" +
                    "71 61 96 55 81\n" +
                    " 3 50 36 94 27\n" +
                    "\n" +
                    " 8 23 14 30 10\n" +
                    "91 36 58 40 92\n" +
                    "74 84 33 44 96\n" +
                    "86 20 82 57  5\n" +
                    "87 11 95 46 31\n" +
                    "\n" +
                    "95 51 73 41 10\n" +
                    "31 15 70 56 39\n" +
                    "83 11 37 38 42\n" +
                    "13 58 53 77 14\n" +
                    "79 71  5 29 93\n" +
                    "\n" +
                    "21 59 62 60 87\n" +
                    "92 90 51 15 57\n" +
                    "38 68 26 78 36\n" +
                    "31 84 97 81 74\n" +
                    "12 48 35 70 93\n" +
                    "\n" +
                    "13 52 95 80 87\n" +
                    "71 77 74 35 61\n" +
                    "37 41 14 73 46\n" +
                    "62 90 54  0 88\n" +
                    "67 20 56 22 85\n" +
                    "\n" +
                    "95 56 68 49 44\n" +
                    "34 18 65 13 71\n" +
                    "52 94 87 90 19\n" +
                    "36 30 45  3 77\n" +
                    "84 89 64 20 41\n" +
                    "\n" +
                    "17  6 13 27 64\n" +
                    "14 34 60 32 40\n" +
                    "51 97  8 57 88\n" +
                    "44 79 82 31  2\n" +
                    "15 99 59 62 50\n" +
                    "\n" +
                    "86 16 33 27 39\n" +
                    " 3 34 42 15 48\n" +
                    "84  2 85 31 87\n" +
                    "97 41 49 13 23\n" +
                    "51  6 71 82 10\n" +
                    "\n" +
                    "82 25 17 28 48\n" +
                    "95  7 29 55 81\n" +
                    "97  3 26 64 11\n" +
                    "98 75 45 89 96\n" +
                    "49 70 84 41 57\n" +
                    "\n" +
                    " 5 51 65 35 27\n" +
                    "89 17 18 66 37\n" +
                    "16 68 56 87 33\n" +
                    "71 86 52 48 23\n" +
                    "47 10 53  7 55\n" +
                    "\n" +
                    "76 88 50  5 83\n" +
                    "23 63 13 26 41\n" +
                    "84 60 36 80 68\n" +
                    "37 79 47 74  1\n" +
                    "59 55 58 99 65\n" +
                    "\n" +
                    "48 51 12 74  9\n" +
                    "31 26 67 95 98\n" +
                    "54 46 56  3 80\n" +
                    "10 57 17 37 92\n" +
                    "78 87 28 82 52\n" +
                    "\n" +
                    "49 85 57 58 30\n" +
                    "77 76 92 97  3\n" +
                    "69 66 14 25 83\n" +
                    "63 98 47 37 78\n" +
                    "22 96 89 91 95\n" +
                    "\n" +
                    " 5 24 38 66 88\n" +
                    "32 18 82 26 23\n" +
                    "41 36 14 91 21\n" +
                    "56 51 99 83 58\n" +
                    "28 27 78  3 43\n" +
                    "\n" +
                    "55 65 60 13 11\n" +
                    "10 41  9 39 62\n" +
                    "75 85 12 61 66\n" +
                    "35 43 64 94 48\n" +
                    "44 28  1 92  2\n" +
                    "\n" +
                    "19 81 21 33 15\n" +
                    "48 55 91 95  7\n" +
                    "34 90 64 50 97\n" +
                    "23 43 60  3 26\n" +
                    "16 22 67 58  9\n" +
                    "\n" +
                    "83 33 88 16 47\n" +
                    "13 98 43 24 34\n" +
                    "45  9 27  2 76\n" +
                    " 4 57 80 77 87\n" +
                    "61 11 66 81 50\n" +
                    "\n" +
                    "17 62 88 23  5\n" +
                    "57 25 84 55 65\n" +
                    "60 85 39 90 99\n" +
                    "83 79 37 34 46\n" +
                    "29 54 92 12 51\n" +
                    "\n" +
                    "28 59 78 96 55\n" +
                    "12 77 46 81 23\n" +
                    "60 54 17 16 45\n" +
                    "42 89 62 63 47\n" +
                    "26 98 31 85  7\n" +
                    "\n" +
                    "86 91  2 39  0\n" +
                    "67 73 62 69 38\n" +
                    " 6 94 25 66  7\n" +
                    "26 41 81 59 79\n" +
                    "71 44 33 46 10\n" +
                    "\n" +
                    "24 22 21 82 62\n" +
                    "48 74 83 17 64\n" +
                    "66 23  4 78 36\n" +
                    "54 80 38 20 14\n" +
                    "47 85 96 39 12\n" +
                    "\n" +
                    "72 56 75 34 59\n" +
                    "20 22 90 87 97\n" +
                    "24 95 61 91 74\n" +
                    "51 38  7 18 21\n" +
                    "73 57 89 80 92\n" +
                    "\n" +
                    "42 23 22  5 37\n" +
                    "72 77 88 63  7\n" +
                    "44 80 62 10 69\n" +
                    "91  1 60 70 25\n" +
                    "67 97 75 45 55\n" +
                    "\n" +
                    " 3 45 91  4 13\n" +
                    "14  6 34 49 82\n" +
                    "31 98 35 23 85\n" +
                    "10 71 16 40 15\n" +
                    "11 99  7 32 28\n" +
                    "\n" +
                    "42 44 52 27 25\n" +
                    "31 28 11 86 81\n" +
                    "35 80 20 65 24\n" +
                    "17  8 93 49 58\n" +
                    "16 92 78 63 61\n" +
                    "\n" +
                    "57 60 34 95 46\n" +
                    "78 25 79 42 36\n" +
                    "65 84 74 82 91\n" +
                    "33 44  9 47 90\n" +
                    "53 69 54  0 76\n" +
                    "\n" +
                    "67 29  7 82 73\n" +
                    "87 49 44 10 14\n" +
                    "40 98 89 41 12\n" +
                    "58 20 27 13 53\n" +
                    "51 78 96 59 80\n" +
                    "\n" +
                    "75  8 73 47  4\n" +
                    "46 67 28 78  5\n" +
                    "29 62 65 19 13\n" +
                    "48 95 21 81 50\n" +
                    "80 38 51 96 91\n" +
                    "\n" +
                    "45 41 80 64 32\n" +
                    "14 93 40 76 70\n" +
                    "15 77 49  0 98\n" +
                    "19 68 16 18 92\n" +
                    "81 83 47 36 44\n" +
                    "\n" +
                    "56  3 88 97 72\n" +
                    "60 46 98 11 51\n" +
                    "52  8 54 89  9\n" +
                    "67 41 78 17 82\n" +
                    "99  2  6  7 77\n" +
                    "\n" +
                    "66 20 48 64  9\n" +
                    "19 52 74 56 59\n" +
                    "42 81 29 93  6\n" +
                    "72 75 83 15 76\n" +
                    "99 23 14 44 50\n" +
                    "\n" +
                    "77 90 79 49  3\n" +
                    "51 76  2 41 74\n" +
                    "86 96 36 84 16\n" +
                    "85  5 95 18 47\n" +
                    "73 33 32 45 58\n" +
                    "\n" +
                    "82 72 86 18 84\n" +
                    "59 60  6 22  7\n" +
                    "31 19 90 47 58\n" +
                    "65 71  4 67 92\n" +
                    "17 95 44 35 26\n" +
                    "\n" +
                    "14 81 55 51 29\n" +
                    "44 64 99 41 43\n" +
                    "82 72 53 33 10\n" +
                    "95 38 76 68 56\n" +
                    "57 88 39  0  5\n" +
                    "\n" +
                    "15 47  2 78  9\n" +
                    "13 36 41 29 97\n" +
                    "86 18 57 69 49\n" +
                    "79 37 91 88 59\n" +
                    "21 23 87  1 30\n" +
                    "\n" +
                    "39 45 56  8 89\n" +
                    " 2 42 66  3 70\n" +
                    "75 80 17 64 97\n" +
                    "32 35 60 23 88\n" +
                    "18 51 99  7 38\n" +
                    "\n" +
                    "30 54 83 88 74\n" +
                    "52 15 91 77 86\n" +
                    " 2 79 12 16 50\n" +
                    "40 14 97 63 35\n" +
                    "33 71 25 21 94\n" +
                    "\n" +
                    "37 85 35 10 22\n" +
                    "52 31 77 48 82\n" +
                    "51  3 49 95 20\n" +
                    "24 75  0 89 97\n" +
                    "78 63 14 46 42\n" +
                    "\n" +
                    "91 26 28 38 31\n" +
                    "55 32  0 95 10\n" +
                    "68 25 45 57 56\n" +
                    "36 14 69 30 15\n" +
                    "81  3 37 13 40\n" +
                    "\n" +
                    "75 30  8 28 44\n" +
                    "60 79 59 74  9\n" +
                    "81 50 66 92 61\n" +
                    "29  2 88 15 99\n" +
                    "87 91 26  7 96\n" +
                    "\n" +
                    "74 75 81 72 89\n" +
                    "44  8 94  3 97\n" +
                    "93 46 37  0 56\n" +
                    "63 21 29 91 55\n" +
                    "41  4 31 49 33\n" +
                    "\n" +
                    "55  8 57 29 63\n" +
                    "85 36 58 89 33\n" +
                    "44 75 67 23 39\n" +
                    "49 46 32 10 84\n" +
                    "15  7 59 56 12\n" +
                    "\n" +
                    " 1 91 65 67  4\n" +
                    "31  5 34 30 37\n" +
                    "93 66 18 79 19\n" +
                    "28  6  0 47 42\n" +
                    "21 98 87 89 81\n" +
                    "\n" +
                    "79 30 10 21 69\n" +
                    "56 19 27 90 72\n" +
                    "77 57 93 92 97\n" +
                    "15 45  4 18 68\n" +
                    "59  2 29 26 83\n" +
                    "\n" +
                    "21 41 53 30 31\n" +
                    "85 18 17 52 39\n" +
                    "49 38  4 57 54\n" +
                    "63 74 59 16  8\n" +
                    "66 62 27 10 37\n" +
                    "\n" +
                    "28 94 59 97  5\n" +
                    "91 17 72 84 80\n" +
                    "21  6 60 77 98\n" +
                    "50 70 44 85 95\n" +
                    "92 61 43 16 93\n" +
                    "\n" +
                    "10 91 27 84 63\n" +
                    "69 97  9 83 89\n" +
                    " 6 47 42 87 77\n" +
                    "20 94 34  4 64\n" +
                    " 0 12 38 39 18\n" +
                    "\n" +
                    " 4 54 55 76 64\n" +
                    "39 15 80 42  9\n" +
                    "57 56 87 61 28\n" +
                    "70 85 36 25 75\n" +
                    "38 53 12 71 47\n" +
                    "\n" +
                    "83 97 32 29  0\n" +
                    "60 30 31 92 61\n" +
                    " 1 42 17 94 67\n" +
                    "20 39 36 91 10\n" +
                    "18 69 47 43 76\n" +
                    "\n" +
                    "63 74 68 71 92\n" +
                    "85 94  8 41 35\n" +
                    "39 32 62 67 87\n" +
                    "70 95 40 84 51\n" +
                    "52 30 53 22  1\n" +
                    "\n" +
                    "17 39 74 50 55\n" +
                    "72  7 88  4 68\n" +
                    "94 91 45 15 77\n" +
                    "48 53 62 46 67\n" +
                    "69 12 70 32 35\n" +
                    "\n" +
                    "56 89 96 51 14\n" +
                    "23 77 62  4 22\n" +
                    "88 87 27  5 94\n" +
                    " 6 28 40 11 83\n" +
                    "33 86 99 80 63\n" +
                    "\n" +
                    " 2 96 16 33 81\n" +
                    "18  9 45 39 49\n" +
                    " 3 21 59 27 58\n" +
                    "48 31 28 42 77\n" +
                    "37 83 61 25 71\n" +
                    "\n" +
                    "13  1  6 34 23\n" +
                    "56 40 66 50 99\n" +
                    "46 28 65 32 27\n" +
                    "60 67 22 17  0\n" +
                    "29 43 21  8 59\n" +
                    "\n" +
                    "90 86 69 22  5\n" +
                    "72 96 29 62 59\n" +
                    "40  1 54 87 55\n" +
                    "41 78 28 43 33\n" +
                    "77 49 82 13 61\n" +
                    "\n" +
                    "82  8 39 68 51\n" +
                    "97 13 22  7 92\n" +
                    "60 88 84 16 30\n" +
                    "86 54 71 50 87\n" +
                    "72 24 41 25 48\n" +
                    "\n" +
                    " 9 35 43 49 77\n" +
                    "24 29  1 70 80\n" +
                    "15  4 93 48  6\n" +
                    "31 17  0 99 95\n" +
                    "28 13 56 41 97\n" +
                    "\n" +
                    "24 75 16 59 98\n" +
                    "86  5  7 93 10\n" +
                    "42 71  6 91 21\n" +
                    "43 69 78 94 33\n" +
                    " 3 45 73 11 20\n" +
                    "\n" +
                    "19  3 17 58 89\n" +
                    "11 54 53 34 14\n" +
                    "66 82 44 46 41\n" +
                    " 1 22 62 69 25\n" +
                    "65 98 76 84 13\n" +
                    "\n" +
                    "37 93 47 15 20\n" +
                    "43 46 82 53 75\n" +
                    "18 90 25 69 92\n" +
                    "54 42 83 41 17\n" +
                    "44 14 22 70  4\n" +
                    "\n" +
                    "78 72 96 86 89\n" +
                    "64 61 69 79 85\n" +
                    "10 59 37 18 46\n" +
                    " 5 22 31 81  7\n" +
                    "40 57 53 66 32\n" +
                    "\n" +
                    " 8 77 33 81 90\n" +
                    "27  2 94 24 59\n" +
                    "79 28 30 97 60\n" +
                    "32 18 10  1 38\n" +
                    "58 12  5 87 17\n" +
                    "\n" +
                    "68 76  4 63 16\n" +
                    "75 66 19 50 14\n" +
                    "73 24 22  6 33\n" +
                    "99 61 87  1 47\n" +
                    "35 83 48 39 20\n" +
                    "\n" +
                    "42 39 28 87 37\n" +
                    "89 12 75 82 84\n" +
                    "17 74 49  3  5\n" +
                    "56 76 66 92 85\n" +
                    "27 18 86  8 58\n" +
                    "\n" +
                    "71 83 21 29 63\n" +
                    "46 38 62  3 67\n" +
                    "56 72 40 96 64\n" +
                    "95 89 91  4 47\n" +
                    " 7 92 80 69 61\n";

    public static class Board {
        private static final int SIZE = 5;
        final long[][] values;
        final boolean[][] marked;

        public Board(String input) {
            values = new long[SIZE][SIZE];
            marked = new boolean[SIZE][SIZE];
            final String[] inputLines = input.split("\\R");
            int row = 0;

            for (String inputLine : inputLines) {
                inputLine = inputLine.trim();
                final String[] inputValues = inputLine.split("\\s+");
                for (int column = 0; column < SIZE; column++) {
                    values[column][row] = parseLong(inputValues[column]);
                }
                row++;
            }
        }

        public void mark(long value) {
            for (int column = 0; column < SIZE; column++) {
                for (int row = 0; row < SIZE; row++) {
                    if (values[column][row] == value) {
                        marked[column][row] = true;
                    }
                }
            }
        }

        public boolean hasWon() {
            return hasWinningRow() || hasWinningColumn();
        }

        public long sumAllUnmarked() {
            long sum = 0;
            for (int column = 0; column < SIZE; column++) {
                for (int row = 0; row < SIZE; row++) {
                    if (!marked[column][row]) {
                        sum += values[column][row];
                    }
                }
            }
            return sum;
        }

        private boolean hasWinningRow() {
            for (int row = 0; row < SIZE; row++) {
                if (isWinningRow(row)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isWinningRow(int row) {
            int markedValues = 0;
            for (int column = 0; column < SIZE; column++) {
                markedValues += marked[column][row] ? 1 : 0;
            }
            return markedValues == SIZE;
        }

        private boolean hasWinningColumn() {
            for (int column = 0; column < SIZE; column++) {
                if (isWinningColumn(column)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isWinningColumn(int column) {
            int markedValues = 0;
            for (int row = 0; row < SIZE; row++) {
                markedValues += marked[column][row] ? 1 : 0;
            }
            return markedValues == SIZE;
        }
    }

    private static long generateFinalScoreForPuzzle1(long[] numbers, String boards) {
        String[] boardsAsStrings = boards.split("\\R\\R");
        List<Board> boardList = Arrays.stream(boardsAsStrings)
                                      .map(Board::new)
                                      .collect(Collectors.toList());
        for (long number : numbers) {
            for (Board board : boardList) {
                board.mark(number);
                if (board.hasWon()) {
                    long sumAllUnmarked = board.sumAllUnmarked();
                    System.out.println("sumAllUnmarked: " + sumAllUnmarked);
                    System.out.println("Number called: " + number);
                    return sumAllUnmarked * number;
                }
            }
        }
        throw new IllegalArgumentException("Sequence of numbers did not yield a winning board.");
    }

    private static long generateFinalScoreForPuzzle2(long[] numbers, String boards) {
        String[] boardsAsStrings = boards.split("\\R\\R");
        List<Board> boardList = Arrays.stream(boardsAsStrings)
                                      .map(Board::new)
                                      .collect(Collectors.toList());

        Board lastToWin = null;
        long lastNumber = -1;

        for (long number : numbers) {
            for (Board board : boardList) {
                if (!board.hasWon()) {
                    board.mark(number);
                    if (board.hasWon()) {
                        lastToWin = board;
                        lastNumber = number;
                    }
                }
            }
        }

        if (lastToWin == null) {
            throw new IllegalArgumentException("Sequence of numbers did not yield a winning board.");
        }

        long sumAllUnmarked = lastToWin.sumAllUnmarked();
        System.out.println("sumAllUnmarked: " + sumAllUnmarked);
        System.out.println("Number called: " + lastNumber);
        return sumAllUnmarked * lastNumber;
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 04: Examples for puzzle 1 ###");
        System.out.println("Final score: " + generateFinalScoreForPuzzle1(SAMPLE_NUMBERS, SAMPLE_BOARDS));
    }

    public static void testWithExamplesForPuzzle2() {
        System.out.println("### Day 04: Examples for puzzle 2 ###");
        System.out.println("Final score: " + generateFinalScoreForPuzzle2(SAMPLE_NUMBERS, SAMPLE_BOARDS));
    }

    public static long doPuzzle1() {
        return generateFinalScoreForPuzzle1(INPUT_NUMBERS, INPUT_BOARDS);
    }

    public static long doPuzzle2() {
        return generateFinalScoreForPuzzle2(INPUT_NUMBERS, INPUT_BOARDS);
    }
}
