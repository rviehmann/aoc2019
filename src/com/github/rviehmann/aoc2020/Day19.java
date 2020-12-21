package com.github.rviehmann.aoc2020;

public class Day19 {

    // From: https://adventofcode.com/2020/day/19/input
    private static final String RULES =
            "26: 97 126 | 123 57\n" +
                    "122: 84 97 | 92 123\n" +
                    "82: 97 138 | 123 130\n" +
                    "80: 131 97\n" +
                    "3: 123 107 | 97 66\n" +
                    "101: 1 123 | 95 97\n" +
                    "10: 97 138 | 123 107\n" +
                    "83: 123 27 | 97 12\n" +
                    "54: 97 126 | 123 107\n" +
                    "61: 97 50 | 123 114\n" +
                    "123: \"a\"\n" +
                    "86: 123 18 | 97 36\n" +
                    "70: 97 76 | 123 69\n" +
                    "34: 66 97 | 110 123\n" +
                    "68: 97 93 | 123 86\n" +
                    "131: 123 97 | 97 123\n" +
                    "126: 97 97 | 97 123\n" +
                    "93: 59 97 | 9 123\n" +
                    "51: 78 21\n" +
                    "94: 97 90 | 123 39\n" +
                    "49: 88 97 | 66 123\n" +
                    "104: 75 97 | 33 123\n" +
                    "114: 82 123 | 116 97\n" +
                    "45: 123 13 | 97 62\n" +
                    "36: 123 81 | 97 65\n" +
                    "58: 53 97 | 132 123\n" +
                    "135: 97 103 | 123 105\n" +
                    "15: 123 2 | 97 49\n" +
                    "85: 130 78\n" +
                    "124: 138 123 | 130 97\n" +
                    "105: 97 16 | 123 6\n" +
                    "66: 78 97 | 123 123\n" +
                    "76: 123 45 | 97 55\n" +
                    "138: 97 123\n" +
                    "20: 97 107 | 123 13\n" +
                    "38: 97 110\n" +
                    "16: 110 123 | 130 97\n" +
                    "97: \"b\"\n" +
                    "117: 13 97 | 66 123\n" +
                    "19: 123 138 | 97 131\n" +
                    "106: 123 35 | 97 13\n" +
                    "59: 97 5 | 123 74\n" +
                    "7: 97 21 | 123 66\n" +
                    "35: 97 123 | 123 78\n" +
                    "0: 8 11\n" +
                    "103: 97 71 | 123 128\n" +
                    "95: 123 13 | 97 138\n" +
                    "109: 123 136 | 97 60\n" +
                    "96: 123 43 | 97 52\n" +
                    "12: 6 97 | 137 123\n" +
                    "65: 123 138 | 97 138\n" +
                    "50: 123 7 | 97 26\n" +
                    "11: 42 31\n" +
                    "128: 21 123 | 107 97\n" +
                    "79: 35 123\n" +
                    "125: 123 130 | 97 126\n" +
                    "108: 123 85 | 97 26\n" +
                    "9: 23 123 | 20 97\n" +
                    "137: 123 126 | 97 57\n" +
                    "33: 97 28 | 123 43\n" +
                    "57: 123 97 | 123 123\n" +
                    "32: 26 97 | 80 123\n" +
                    "90: 123 44 | 97 4\n" +
                    "48: 110 97 | 130 123\n" +
                    "67: 57 123 | 13 97\n" +
                    "69: 97 67 | 123 30\n" +
                    "37: 88 123 | 107 97\n" +
                    "41: 111 123 | 72 97\n" +
                    "42: 119 97 | 58 123\n" +
                    "110: 78 123 | 97 97\n" +
                    "100: 97 131 | 123 57\n" +
                    "127: 24 97 | 41 123\n" +
                    "98: 32 97 | 134 123\n" +
                    "119: 123 22 | 97 129\n" +
                    "112: 13 97 | 107 123\n" +
                    "5: 138 97 | 126 123\n" +
                    "75: 123 121 | 97 10\n" +
                    "29: 110 123 | 13 97\n" +
                    "40: 97 89 | 123 46\n" +
                    "111: 97 5 | 123 44\n" +
                    "56: 123 133 | 97 96\n" +
                    "30: 57 123 | 62 97\n" +
                    "132: 98 123 | 70 97\n" +
                    "89: 107 97 | 13 123\n" +
                    "44: 97 131 | 123 13\n" +
                    "25: 15 123 | 108 97\n" +
                    "78: 123 | 97\n" +
                    "74: 110 123\n" +
                    "134: 123 113 | 97 54\n" +
                    "47: 131 123 | 21 97\n" +
                    "116: 130 97 | 35 123\n" +
                    "91: 115 97 | 102 123\n" +
                    "6: 57 78\n" +
                    "99: 123 68 | 97 120\n" +
                    "62: 123 123\n" +
                    "84: 123 110 | 97 62\n" +
                    "118: 78 97 | 97 123\n" +
                    "121: 97 62 | 123 107\n" +
                    "22: 25 97 | 61 123\n" +
                    "28: 97 131 | 123 110\n" +
                    "102: 117 123 | 29 97\n" +
                    "73: 126 97 | 130 123\n" +
                    "72: 97 124 | 123 47\n" +
                    "120: 56 97 | 109 123\n" +
                    "115: 97 3 | 123 38\n" +
                    "24: 97 14 | 123 101\n" +
                    "55: 130 97 | 131 123\n" +
                    "81: 107 123 | 126 97\n" +
                    "39: 123 112 | 97 79\n" +
                    "60: 106 123 | 100 97\n" +
                    "136: 97 64 | 123 48\n" +
                    "130: 97 97 | 123 97\n" +
                    "133: 97 74 | 123 34\n" +
                    "92: 123 130 | 97 110\n" +
                    "2: 97 138 | 123 88\n" +
                    "71: 110 97 | 131 123\n" +
                    "53: 123 91 | 97 17\n" +
                    "14: 97 125 | 123 87\n" +
                    "43: 97 62 | 123 21\n" +
                    "21: 97 97 | 123 123\n" +
                    "23: 130 123 | 66 97\n" +
                    "17: 97 122 | 123 40\n" +
                    "8: 42\n" +
                    "1: 13 123 | 57 97\n" +
                    "129: 97 94 | 123 135\n" +
                    "107: 78 78\n" +
                    "18: 97 37 | 123 51\n" +
                    "88: 97 97\n" +
                    "113: 57 97 | 126 123\n" +
                    "46: 97 13 | 123 138\n" +
                    "27: 19 123 | 73 97\n" +
                    "87: 107 123 | 118 97\n" +
                    "52: 97 130 | 123 126\n" +
                    "63: 123 77 | 97 127\n" +
                    "64: 97 118 | 123 88\n" +
                    "13: 123 97\n" +
                    "31: 99 123 | 63 97\n" +
                    "4: 57 97 | 131 123\n" +
                    "77: 123 104 | 97 83";

    private static final String MESSAGES =
            "bababbbbbbbaabbabbbbaaab\n" +
                    "baaaabbbabaaabbbaabbabbbabbbbbaa\n" +
                    "bbabbabaabbaabbaabbabbaa\n" +
                    "abbbabbbabaabababaaabbbb\n" +
                    "abbaaabaaaaaababababbaab\n" +
                    "babaabbaabaaaabbbbbbabababaaabbabbaabbbb\n" +
                    "aaabbaabbbbaaabbabbbabaa\n" +
                    "abbaaaaabbaabaaabbabaaaa\n" +
                    "aaaaaabababaabbabbababaabbbaaaaababbabbabaabbaba\n" +
                    "abbbbaabbababaaaabbaabbb\n" +
                    "aaaaabaaaabbaaabbbbabbaa\n" +
                    "abbbbaababbabbbabbababbb\n" +
                    "abbbbaababaabbbaababaabbaabaabbaaabaababbbabbbaaaabbbbaa\n" +
                    "bbbbabbbbabaaaabbababbabbabbbaaa\n" +
                    "abbbbaababbabbbaabaaabaabaababaaabababab\n" +
                    "abababaababababaabbaaaabbaabaaaaabbbaaba\n" +
                    "abbbababbbaaababbababaaaabbbabaabbbbabaa\n" +
                    "babbbaabbaaaaaaabaabbaaa\n" +
                    "aaaaaabaaabaabababbbaaba\n" +
                    "abaabbabbabaabbbaaabaaaabababaabbbbbabba\n" +
                    "aaaabaabaabaababbaaabaaa\n" +
                    "bbaaabbbababaabbabaaabbbbbaaababbbbbbbbaaaaaaaab\n" +
                    "bbabababbbbabaabbabbabaabababbabaabbbaaaabababbaabbaaaaaaaabaabbabbaaabbaababbbbbaabbaba\n" +
                    "aabbaabbbaaaaabbaaaaabbb\n" +
                    "abbaaaabbaabaaaaaabaaabb\n" +
                    "abbbbbbbaaababaabbaababaabbaabbbaababbabaabaabbbbabbbaaaabbbabba\n" +
                    "aaabababbbbabbabbaaaaaaaaaababba\n" +
                    "babaabbbabbbabbbbaaabbbb\n" +
                    "bbbbbaaaabbbbbababbbabba\n" +
                    "babaaaabaabbaababababbbbbaabbbba\n" +
                    "abaaaaabbbbbabababbabbaa\n" +
                    "abaabbbabaaaababaaabaaba\n" +
                    "abbbbbbbaabbbbbbabbbbbabaaababbbbaaaaabaaaaabbba\n" +
                    "aaababaabaaaabbbabbaaaaabbabaaabbaababbbaaaabbba\n" +
                    "babbaabbaaabbbbbabbbbbabababbaaa\n" +
                    "babaaaabbabababbbbaababb\n" +
                    "bbababaaababbbaabbbbabababbbbbabbbbabbabbaabbaaa\n" +
                    "abbbbbbaabbabaaaabaaabbaabbaabbbaaaabbba\n" +
                    "babaaaabbabababbbbabaaababaababb\n" +
                    "aabbbbbbbbbabbabbababbaa\n" +
                    "bbaaaaaaaaababaaabbbbbaaabbbabbbabbabaaaabaabaabbabaabbabbbbaabbbbaabaaaababbaabaababaab\n" +
                    "abbbbaabbbabaaabaabababb\n" +
                    "baabbbababbaaabaabbbabbbbabbbaaa\n" +
                    "aaababaaaabbbaaababbbbbababbbaba\n" +
                    "aaabbbbbabbaaababababbaa\n" +
                    "aaabbbabaaaabbaaaababbbbaabbaabaaaaaaabb\n" +
                    "bbabbabababbabbaabababbabbabbbaaaaaaaaaa\n" +
                    "bbaababaaabbbbbbabbbbaba\n" +
                    "abbbbbbaaabbaaabbbbaabbaaaaaaababbbbaabaaabbabab\n" +
                    "baaaaabbbabaaaaabbaaabbbbaabbabbaaaaaabb\n" +
                    "aaaaabaabbabbabaabbbaaba\n" +
                    "baabbaabaabbbbabaabbabab\n" +
                    "bbabbabababbababaabbabbaabababbb\n" +
                    "bababbabaabbbaaaabbaaaabbbabbabbbbbabbbb\n" +
                    "ababbbabbabbbabbaabababa\n" +
                    "bababbbababbbaabbaabbbabaabbbbabbbaabbbaaaaabbab\n" +
                    "bababaaaabaaaaabaaabbbabbaababab\n" +
                    "abbaabbababbaaaabbaaaaababbbabbbbbabbababaaaabaabaaabaababbabbbb\n" +
                    "aaaaaababaaaaabbbaaabaaa\n" +
                    "abbaaaaaabaaaaaabbbbabbbbaaaababbbbabbabababababbaaabbbbababbaba\n" +
                    "bbabbaaaabaaaaabbabbabbb\n" +
                    "bbbaaaaaabaaabaababaabaaaabbbaab\n" +
                    "abbaababbbbaabbabbbaabba\n" +
                    "baabaabaabaaaabaaaababbbbbaaaaaabbbbaaaaaabbabaaabbababababbaaba\n" +
                    "babbbabbbabbaaaaaaaaaabb\n" +
                    "bbaaababbbaabbabaaaabaaa\n" +
                    "bbbaaaaaabbbbbbbbbabaabb\n" +
                    "abaabbbbaaababbbabbbbabbaabbbabb\n" +
                    "bbbaaaaaaabbaaababbbabbbbaabbbba\n" +
                    "abbaababaaaaaababbbbbaaaabaabaaaababaaaa\n" +
                    "aabaababbbaabababaabaaabaaababbbabaababbababbabbbbbabbaa\n" +
                    "bbbbbabaaabbbbbbbabbbbaa\n" +
                    "aaaaabababaaaabbbaababab\n" +
                    "bbbbbaaabaaaaaabaaaaaaba\n" +
                    "bbaababaaaabaaaaabbbbaabbbaaabba\n" +
                    "aaaabababbabaaabbaaaabba\n" +
                    "ababbbababababbabbabbbab\n" +
                    "abbaaababababbaaaabbaaaababaaaabbabbbaba\n" +
                    "babbaabbabbabaaaababbbababbaabaa\n" +
                    "abaaabbabbabbababaababaa\n" +
                    "aaaaababaabbbbabbbbabaab\n" +
                    "bababbbaabaaaaabbaaaaaabbaaaabba\n" +
                    "ababaaabababaabbbbaaaabb\n" +
                    "bababbbababaabaaabababbb\n" +
                    "abbaabbaabbbabbbaababbaa\n" +
                    "baaababaabbaabbaaaabaaaaaaabbabbabbaaabaabbaabbb\n" +
                    "bbbaaaaabaaabababababaaabbbaaaaaaaabbbba\n" +
                    "bbbbaabbabbbbbbbbbbbabbbabababaabaaabbbb\n" +
                    "aaaaababaaaaababbaabbaba\n" +
                    "babbababaabbaaabbabbbbaa\n" +
                    "baaaaaaaaabbbbababaabaaa\n" +
                    "bbaaabbaabbbbbbaababbbababababaaaaaabababbabbbaaaaabbbba\n" +
                    "babbbabbababbbbababbbbbaabaaabbbaababaababbaaaababaaaaabaababbaa\n" +
                    "bbaaaaabbbaaabbbbbababbb\n" +
                    "aaaabababbabbabaababbaaa\n" +
                    "aaabbabbaaabbaabababbaaa\n" +
                    "abbaaaaabbabbbaaaaaaababaaabbaaaabbbaaaabbabbabb\n" +
                    "abbaabababbbabbbaaababba\n" +
                    "aabbbbbbababbbaabbaabbba\n" +
                    "baaabbaaabaabbbbababbbaaaaaaabababaabbbabbaabaaa\n" +
                    "abbbabbbbabbbaaaaaaabbbbbbaababbaabaaaaa\n" +
                    "aabaaababaabbaabbabababbabbbbbaa\n" +
                    "baabbbbbaaaabaabababbbabbbbaabbb\n" +
                    "abaaabaabbbbababaabaababbabbababaabbabaabaababab\n" +
                    "abababaaaaabbaabbbabaaaa\n" +
                    "aaaaabababaabbbbaaabbaaababbbbabbaaabbba\n" +
                    "bbababaabaaaaabbbbaaaaaa\n" +
                    "bbaabbbbabaabaaabbbaaaaabaabbaaabbbbabba\n" +
                    "aaabbabbbaaaaaababaaaabbaaaabaabbabbbbbbababbaab\n" +
                    "abaaabaaabbabbbaaaaaaaab\n" +
                    "aaabbabbbaabbbababaaabbabbbbbabaabbbabab\n" +
                    "aaaabaaaaabbbbbabbaababbaababbbabbaabbaabbaabaab\n" +
                    "bbabaaababaabababbabbaaaabbbabbbbaaaabab\n" +
                    "abaaabbbbaaaabbbbaaabbab\n" +
                    "aaaabababbabbbaaaaaaaabb\n" +
                    "baaaaaabbababaaaaabbbababbabbbaababbbaabbabbbbbbbaabaabb\n" +
                    "abbbbbababbaabbaabbbbabbbaaabbaaabbbaaabbbaabbbaabaabaaa\n" +
                    "abbaaaabaaabaaaaaaabaabb\n" +
                    "bbbbaabbbbbaaaaaaabbabaa\n" +
                    "aaabbaaabbabbabaabaaababbbbbbbbb\n" +
                    "ababbbbbbabaabaabbababbaaaaababaaaaabaabbbbabbabbaaaabaabaaabbaabababbab\n" +
                    "aaaaabaaabaaabbabbbaabbb\n" +
                    "abbaaabaabaababaabaabbabaabbbaababbbabaa\n" +
                    "aabbaaaaaaabbaaaabaababb\n" +
                    "abbaabbaaabaabbabaababab\n" +
                    "aabbbbbbbbaabbabaaabbabbaabbbabbbbbaabbb\n" +
                    "bbbababbaabbbaabaaabaaaababbababaaabbbabaaabaaabaaababaaababaaab\n" +
                    "aabaabababbaaaaabbababababbaabbaabbababaaabababa\n" +
                    "bbbaaabaabaaaaabbbaaaaabaaaaabaabaaaaaba\n" +
                    "bababbbaabbabaaaabbabbbb\n" +
                    "bababbabbaaabababbabaaabababbaba\n" +
                    "baabbbbbbabbabababaaaaba\n" +
                    "babababaabbbbbbbbaababaa\n" +
                    "abaaabbabbaabaabbaaabbaaababbbabbabaaabbaaabaaba\n" +
                    "bbaaaaabaaabbabbababbbbb\n" +
                    "bbabbaabaabaabbbaaaababbabaababbbbaaabbbabbbaabbabbbbbaa\n" +
                    "abbaaaaaabbbbaababbaaaaaabbaababbaabbbaa\n" +
                    "bbbbaabbaabbbbbaaabbaabababbbaabbbbabaab\n" +
                    "aababbabbbabaaaabaaabbbbbbabbbbb\n" +
                    "babbbabbabaabbbbbababaab\n" +
                    "bbbbbaaaaabbaababbabbaaabaaaabba\n" +
                    "bbaaabbaaaabbbabbaabaabb\n" +
                    "baabbbbbbbaabaabbbabbabababbaaba\n" +
                    "aabbabbaaaabbaabbabaabaaabbbaaab\n" +
                    "aabaaabababbbbbaababaaba\n" +
                    "baaabbaabbaaabbabaabbbba\n" +
                    "ababaabbbbababababbbbabbbbabbbaaaaabbabaaabaaaab\n" +
                    "abbbbaabbbbaabaabaaaaaaabbaaabbaabbabbbb\n" +
                    "aabaabbabaababbaaabaaaab\n" +
                    "abbbbaababbaaaababababab\n" +
                    "ababaabaaabbbbababbaaaabbaababbabaaaaabb\n" +
                    "babaaaabbbabbaabbbbaaaab\n" +
                    "aaababbaabaaaabbbaababbbaabbbbaaaabbbbaabbaababaaababaab\n" +
                    "abbaaaabaabbbababaabbaba\n" +
                    "bbbbaabbaaabbabbbabbbaabaababbbabbbbbabb\n" +
                    "babbabbaaaabbabbbababaababbbabab\n" +
                    "aaaaababbbabbabaaaaaaababbbbabaa\n" +
                    "aaababaaabaaababbaabbaabbbbaaabbbaabbbbbaaabaaaaaabaaaaabbabaaba\n" +
                    "bbbbbabaaabaaabaabaaabbaaabaabaaabaabaaa\n" +
                    "bbbaaaaaaaaabbbabaabbbaabbbabaaaabbabbbb\n" +
                    "aabbabbabbbaaabbabaaabbaaaabbaaaabbaaabb\n" +
                    "abaabbaaabaababaabbbbabbabbabbbaaaababaaaaabbbbabababaab\n" +
                    "bbaabababbabbbaaabbabaab\n" +
                    "abaaabbaaabaababbabbbaba\n" +
                    "aaaabababbababaababbaaba\n" +
                    "aaaaababbbbbaabaabaabaaa\n" +
                    "bbbababbbbbaaaaabbaaaababaabaabb\n" +
                    "baabaaaabbbbaaaaaabaababbaaaabba\n" +
                    "aabbaaababbbbabbaabaabbb\n" +
                    "baababbaaaaabbaaaababbba\n" +
                    "bbbaabbaabbaabababaaaaba\n" +
                    "aaaaaababaaaaaaaaabbbbbaaabaaaaa\n" +
                    "bbbbbbaaabaaabbbbbaaaaababbbabba\n" +
                    "babbababbabababbabbbabaaaaabbbbbaababbaabbbbbbbabbbbbabaabbaabab\n" +
                    "bbaabbababbbbaaaaaabbbaaabaaaaaaabababaaabbabbbababbbbbbbbabbbaaabaabbab\n" +
                    "aaaabababbabbaaaaaaaabaabbaaaaaaaabababa\n" +
                    "abaabbbbbbbbababbbbaaababbaababaabbaaaaabbabbbbbaaaaabba\n" +
                    "abbbbbaabbbabbbbbbabbaaaaababababaabaababaaabbbaabababab\n" +
                    "aabbaaaabbabbbbaaabbaabbabbbbaabababbabbbabbbaba\n" +
                    "bababbbabbabbaaabbaaabaa\n" +
                    "bbabbaaabbbbaabaaabbbbabaabbaaaaabbbaabbbbbbbabb\n" +
                    "ababbbabbbabbbbaabaaabbaabbbbbbaaaaaabbb\n" +
                    "aaabaaaababbababbbaabbbbaaababba\n" +
                    "bbaabbababaaaaaaababbbabbbabababbbaaabbbbababaababbbaaababaabbaa\n" +
                    "babbbaabbabbaaaabbaaaaba\n" +
                    "bbaababaabaaaaababaaabaaaaaabababaababbaaaababbabbbbabba\n" +
                    "abaaaaaabbaabaabaababaaa\n" +
                    "abbabaaaaaababababaaabbbaaabaaab\n" +
                    "aaaaabaaaaaaaabaaaabbaaa\n" +
                    "babababbbaaaaabbabbbbbaa\n" +
                    "aabbaaaaabbbabbbbabbabaa\n" +
                    "bbbbbaabbbaaabbbbaabbabb\n" +
                    "baaaabbbbaababbbaabbaaabbaabaabbababaabaaaabbbbabaabaababaabbaaa\n" +
                    "babbaaaabbabaabbbaabaaaabaabaaba\n" +
                    "abaaaaabaaaaabaabababbbaaaaabaaabbabaaba\n" +
                    "babaabbaabbbaabbabbbbaba\n" +
                    "abbbaabbababaaababbbbbaa\n" +
                    "bbaaaaabaababbaabaababab\n" +
                    "aabbbbbabbbbbaabbbabaaababbabbbaaabaaaab\n" +
                    "babbabbaabaaaabbbbabbaaabbababbbaabaabaa\n" +
                    "abbabaaabaaaaaaabaabbaabbbbbaaab\n" +
                    "baaaaaababababaaabaabbbabbbaabaabbbaaaabaabababb\n" +
                    "babbbaabbbbaabbabbbababbbbbbaababbbbabaa\n" +
                    "abababaababababbaaabaabb\n" +
                    "abbaababbbbbbbaabaaaaaababbaabbababaaabbaabbaabbbbbabaaababaaaba\n" +
                    "ababaaababaaaabbabaaabaaaabbaaaaabaaababbbaabbbaaabaabbb\n" +
                    "abbaaabaabbaabbabaaaabaa\n" +
                    "abaabbbabbbabbabaaaaaaab\n" +
                    "babbbbbbbbaabababbbbabaa\n" +
                    "aabaabbabababbbaabaababb\n" +
                    "abbaaabaabaaabaabbbaaaab\n" +
                    "bbbbaabbaabbaaabbabbbbaa\n" +
                    "bababbabbbaaababbaaababb\n" +
                    "bbbbbaabbabbbbbbaabbbbaaaaabbaba\n" +
                    "bbbaabaaaabbbabababbaabbbabbbbbbbbabbaaaababbbabaaabbaba\n" +
                    "bbbabaabbbbbabbababaababaababbaabaabaaabbabaabbabbbaaabbbabaababbaaabbbbababaabbbabbbabb\n" +
                    "abbaaabaaaabbaabbababbbbaaababbbaaababbaaaabbbaa\n" +
                    "aabbbaaaaaaabbabaabbaaabaaaabbbbaabaabbbaabaaabaaaaabbabbaaaababbababbbb\n" +
                    "bbaaabbbbbbabbabaabaabbb\n" +
                    "babaaaaaaaabbbbbaaabaaab\n" +
                    "abaabbbaabababbaaabaabababaaabababaabaaaaaaabbbaaababbab\n" +
                    "aaaabaababaabbabbaababbbbbaabbbb\n" +
                    "babbbbbaabbbbbbbbaaababb\n" +
                    "babbaaaabbbbaaaaabaabbabbbbababbaabbbbabaaabbaba\n" +
                    "babbbabbbabbbbbbbbbabaaa\n" +
                    "bababbbbabaaabaaaaabbbaa\n" +
                    "bbbaaabbaabaabababaabbababbabbbaaaabbabbbbaababbbaaaabba\n" +
                    "bbabababbabbabbaabbbbbaa\n" +
                    "bbababababbaababaaaabaabababbbbb\n" +
                    "aaabbabbbabbaabbbbaaabaa\n" +
                    "babbaaaababbaabbbaabaabb\n" +
                    "baaababaaabbaabbabbbabaa\n" +
                    "bbbaabaabbbaaaaabbbbbbba\n" +
                    "baaaaaabbbbbbaabbabaaaabaaababaaabbbbbaaababbabbbaababab\n" +
                    "bbabababaabbaabbaabbbbbbabbaaaababbbbbaaaaaaabba\n" +
                    "abaaaabbbabababbbabbbbaa\n" +
                    "babbabbbababaabbaabaaababbbabbabbababbaabbaabababbabbbbbbaaaaabb\n" +
                    "baabbbabbbabbaabaaaabbbb\n" +
                    "ababaaabaaabbaabbaabbaabababaabaababbabb\n" +
                    "abbbabbbbbababaabaabaabb\n" +
                    "babababbbbbaabaabbababbabbababbb\n" +
                    "babaabbaabababbabbbbaaaaabbaababbbababbaabbbbaaa\n" +
                    "bbaababababaabaabaababaa\n" +
                    "ababaaabbbbbbababababaaabaababbaababaaaa\n" +
                    "babbababbbaaababbabbaaabbbbababa\n" +
                    "aaabaaaabbbbaabbbbaaababbaaabbaaabbbababaaaabbab\n" +
                    "bbbaaabbabaaababbbabaaaa\n" +
                    "abbaabbaaaaabaabaaabbbba\n" +
                    "aabbbbbbaabaabababaaaaba\n" +
                    "baababbaaaaabbaaabbbaaab\n" +
                    "babbbaabaabbaaaabaaabaab\n" +
                    "babbbbbaaaabbbababbbaaaa\n" +
                    "bababbbbababbbaaabbbaabb\n" +
                    "abaaaaaaaaabababbbabababaaaabbba\n" +
                    "aabbbbbababbabbabbabaaaa\n" +
                    "bbbaaaaabbabbaabbbbbbaabbabaabaaabbbaaaaabbabaab\n" +
                    "abaabababaaababababbbbbbbaaaabaa\n" +
                    "aabaaabababbbabbaaaaaabb\n" +
                    "abbabaaabbbbbababaaabbaabbbbaaab\n" +
                    "abbbbbababaabbbabbbabbabbbabbabbbbaabbba\n" +
                    "abaabababbaabbabababaaaa\n" +
                    "aaaaabbbabbbabbaaabbbbaaababaaba\n" +
                    "baaaaaabaabbaaababbbbbbabababbbabbbbabaa\n" +
                    "bbaabbabaaabbbbbbaaabbba\n" +
                    "bbabbbbababaabaabaaaabaa\n" +
                    "abaaaaaaaabbbbabbaaaaabbbbbbbbabbaabbbaa\n" +
                    "bbbbaaaaababbbabaaabbaaa\n" +
                    "aaabbaaabbabbbbaaaabbaaabbbaabbbaabaabbb\n" +
                    "babababaaaaaaababbabbbaaababaabaaaaaabbb\n" +
                    "aaabbbabaaababaabbabaabb\n" +
                    "bbabbbbabaaaabbbbbbaabbb\n" +
                    "abbbbabbbaabaaabababaaba\n" +
                    "aabbbaaabbabbaaaaabababb\n" +
                    "bbbbbbaabbbaaabbbbbabaaa\n" +
                    "aaabbabbabaabbbabbabbaabbbbbaabaabbbaaabbbbbaaabbabbbbaa\n" +
                    "aabaaabaabaabbabaabbbbbaaabbbaaaaaabaabbbaabbbaa\n" +
                    "abaaaaaaabbbabbbbbbbbbaaaaaaaaaa\n" +
                    "ababbbaabaaaababbaaaaaaabaaaaaaaaaaabaabbbababbb\n" +
                    "bbabbbbbabaababababaaaabaabaaaaaaaabbaaaabababbaaabbaabbbabbaabababbabaaababbbba\n" +
                    "baaababababaaaaaaabaabbaaaababaaabbaabaa\n" +
                    "bbabbaaabbabbbaaabbaabbaababbbbb\n" +
                    "babbbbbbbabaabbbabbabaab\n" +
                    "bbaaaaabaabaababaabbabbabaabbaababaabbbbbbbbabbaabbbabbaaabaaaab\n" +
                    "babaabbbababbbaabbaaabba\n" +
                    "bbaaababbbabbbaabbabaabb\n" +
                    "abaaaabbbabaaaaaababbaba\n" +
                    "bbbaaabbbaabababbbababbabaabbaaaabaaaababaabbbbbbbbaabbb\n" +
                    "bbaaabbababbbbbbbbaaaabb\n" +
                    "ababbbabbaaaababbbabaaba\n" +
                    "abbaaaaaabaabbbbababbaaa\n" +
                    "aaaaaabbbbaabbbabaaabbba\n" +
                    "babbbaabaaabbbabbbbbaabbaaaaaaabbaabbaba\n" +
                    "babaaabaababbaaabbaaababbbbaabbbaabbaaabaaaaabbababbaaabaabaabbaaabaaabbabbbbaaa\n" +
                    "bababbbbababaaabaababaab\n" +
                    "bbbbbaaabbbabbabbbabbbbaaaabaaabbbaaaaba\n" +
                    "baabbbbbbbabbaabbbbaaaab\n" +
                    "bbbbaaaababbbbbababbabaa\n" +
                    "abbbbbbbbabbbaabbaaababbbbabbbbb\n" +
                    "babbbabbbbbbbabbbbbabaaaabbbbbaababbbbabbabbaaabaabbabaa\n" +
                    "babaabbaaabbabbbaabaabbb\n" +
                    "bbbbabbbbbbaaaaaabbaabaa\n" +
                    "babbabababbabaaaabababab\n" +
                    "baabbaababbbbaabababbaba\n" +
                    "aabbabbaaabbbbbbbaaabbbb\n" +
                    "aabbabbaabbaaaaababaabbaabaabaaaaabababa\n" +
                    "bbabaaabaaaaababbabbabbaaabbbabb\n" +
                    "abababbabbbbabbbabbbabba\n" +
                    "bababaaababaaaaabbbababbbababababababaaa\n" +
                    "abbbbbabbababbbaabaaabbbabbaaaabbbbbabbbaaaabaaaabbbbaba\n" +
                    "babbbbbabaaaaaaabbabababaabbaabaaabbabaa\n" +
                    "aabbaaababababaaabaaaaaabbaaababaaabaaab\n" +
                    "aaaaaabaabbbbaabbbabaabb\n" +
                    "abbabababbaaabbbbabaabab\n" +
                    "aabaaababababbbaabaaaaba\n" +
                    "aaaabaabbabababaabababbaaabbbaabbaaaabba\n" +
                    "babaabaaabbabababbbbbbba\n" +
                    "babaabbabbbbaaaaaabbabbaabbabbbabaabaaba\n" +
                    "aaaabaababaaaabbbaabbbba\n" +
                    "aaaababababbaabbaaaabbab\n" +
                    "aaabbabbbbbbaababaabbbabbbaaabbbaababbbbbbaabbba\n" +
                    "bbaaabbbaaabbaaabbbababbabbbbaabaaabbbbbbabbaabaabbbabbabaabbbbaaaaaaaaa\n" +
                    "aaaaabbbabaabbbbbaaababbbbbbabbaabbababababaaabb\n" +
                    "baabbbabaabaabbabbabaabb\n" +
                    "abaaabbbaababaaaaababbba\n" +
                    "bbababbaaaaabbaaaaaaaabaaaaabbab\n" +
                    "aabaababbabababbabbbbaaa\n" +
                    "abbaaaabbaaabbaaaaaabbba\n" +
                    "babaababbaabbbbabbaababa\n" +
                    "babaabbbaaaabaababaabbbabaaaaabbbbabbbbbbaabaaba\n" +
                    "abbbbaabababbbaabaabbaaa\n" +
                    "bbbabbababaaaaaaabbbbbbbabbabaab\n" +
                    "baababbbbaaaababbabbbbab\n" +
                    "bbbaabaabbabababaaabaaabaababbbbbbbbabba\n" +
                    "abbbaabbababbbbaabbaaabaaabaaaaababbaaabbbbbbababbbbbbaaabaabbbaabaabbabbbabbabababababb\n" +
                    "bbabbaababbaabbabaaaabaa\n" +
                    "bbabbaaaaabaababababbbbb\n" +
                    "bbabbaaabbabbabbbbbabaaaabbbbabb\n" +
                    "aabbbbbbbababaaabaaaabba\n" +
                    "baabaaaaaabbbaaabaaaaabbabaaabababbbabba\n" +
                    "abaabbababbaaaabbbbababbbabbababbbbaaaab\n" +
                    "abbaaaabaaaabbaabbabaaabababbaaa\n" +
                    "aaaaaabababbbbbbbabbbbbaaaabababbaabbbababbaabbb\n" +
                    "babababbaabbabbabaaaabab\n" +
                    "bbaaaaaaaababbabbbababbabbaabbabbbababbabaaaabaaaaaabbab\n" +
                    "abaabbbabbbaabaaabaabbaa\n" +
                    "abbbbbbbbabaabbababaaaabaaaaaabaabbabbbababababbaababbbbababaaaa\n" +
                    "babababaaaabbbbbbbbaaababababbbbabaaaaaaaaabbbba\n" +
                    "aabbbbbbbaaaaaabaabbaaaabababbababbbabbabaababaa\n" +
                    "babaaaaabaaaabbbbaaabbbb\n" +
                    "babaaaabbbbbaaaaaaabbbaa\n" +
                    "abbaaaaaabbaaaababaabbaa\n" +
                    "abbbbbbabbbaabaabbbbaaaaaaabbbabaaaaabba\n" +
                    "bbbbabbbbbbbabbaabbaabbbbbbabbbbaaabbbabaababbbaaabbbaabbaababba\n" +
                    "babbaaaaababbbbaaababbbb\n" +
                    "aaaaababbababbabababbbababaaabaaaaaaabba\n" +
                    "bbbbaabaabbbbbbbaaaabaabbbaabbabbbaababbababbaaa\n" +
                    "bbaabaabbaabbbbbbabbbaba\n" +
                    "bababbbaaaaaababbbaaababaaabbaaaabbbbbaa\n" +
                    "bbbbaabbaaaabbaaabbbbaabaabbabbaabbbabbbaabaabaa\n" +
                    "bbbaaababbbbaaaabbaaabaa\n" +
                    "bbbbbbaaabaaabbbabaaaaba\n" +
                    "abbbbbabbabaabaabbabbbab\n" +
                    "aaaababaababaaabbabaabaabbaaabaa\n" +
                    "abbabaaabbbbbaabbababbaa\n" +
                    "bbbbabbbababbbbaaabaabaa\n" +
                    "abbaabbaaabbbbabbaaabbbb\n" +
                    "bbbbabbbababbbbaababaabbaabbabaa\n" +
                    "abbabbbaabbbbabbababaaaa\n" +
                    "abaabbbbbabababbaaabbbaa\n" +
                    "bbbababaabaabaabbaabaababbbabbaa\n" +
                    "aabbabbbbaababbababaabbbabbbbaba\n" +
                    "aaabaaaaaabbaaabbbbabbbb\n" +
                    "aaaabbaaabbbbaabaaaabaaa\n" +
                    "baaaababaabbbababbbbbbaababbbaabbbabbbaaaabbbbababbbaaba\n" +
                    "bbbbabbbaabbaabbbabbbaba\n" +
                    "bbabbababaaabababbbaaaba\n" +
                    "abbbbbbabaabbaabaaaaaabb\n" +
                    "babbbbabaaabaabbaaabaaaabbababbbbabbabaabaabababbaabbaaababaabaa\n" +
                    "abbbbaabbaaaaabbabaabaab\n" +
                    "abbbaabbbaababbbbaaababababbabbb\n" +
                    "aabaabbaaabbaaaaabbbabba\n" +
                    "bbbabaaaababababbabbbbababbabaaaaabbbbbbaaaabbbbabaabbbbbbbbbaaa\n" +
                    "bababaaababaaaababaaaabbabbaaaaaabbabbaaabbbabba\n" +
                    "babbaabbbaaaababbbbabbba\n" +
                    "bbbabababbabbbbbabaaabbaaababbbbaaababbbaaaabaaabbbbbaababbabbaabbaaaabb\n" +
                    "baaaabbbabaaaaabbbbbaababbaabbbbaaabbaba\n" +
                    "aabbabbabbbababbbaaaaaaaaabababbbaabbaaa\n" +
                    "bbaaaaabbbbbaaaabaaabbaabababaabaaaaabbb\n" +
                    "aaabbaababaaaabbbbbababbbbbbbabaabbbbbbbbbabbbbb\n" +
                    "aaabbaaaabbbabbbababbaaa\n" +
                    "abababbabaabbaabbababbabaabaabbabbbbaabbbabbbbaa\n" +
                    "aaababbbaabaaabaabababbaababbbabbabbaaba\n" +
                    "bbbaabbaabbaabababbbbaaa\n" +
                    "abbbaabbaaababaabbbabaabbaabbababaaabbbb\n" +
                    "abbbbbabaabaaabababbbbab\n" +
                    "abbaaaababaabbbaabaabbaa\n" +
                    "aaabbabbaaabbaaaababbaab\n" +
                    "abbaaaabaaabbaaababababaabaaaabbbaabbabaaaaabaaa\n" +
                    "baabaaabbaababbababbbbbbababbaababbaaabb\n" +
                    "abbbbbabbaaaaabbbabbaabbabaaabbbabaababaabbaaaabbaaaaaaaabbbabaabbbaabaa\n" +
                    "bbbababbbbbbabababbaabbaabaaaaaababaabbbaaaabaabaabababb\n" +
                    "babbaabbaaaaabaaaaaababb\n" +
                    "babaaabbaaaaaababbabbabb\n" +
                    "bbbbababbabaabbbaabaaabaaaabaaab\n" +
                    "bababaaaabbaaaaaabbabaab\n" +
                    "bababbbabbabbbaabbaabaaa\n" +
                    "aaaabbaabaabbaabababbaaa\n" +
                    "bbbbbaaababbaaabbbbaabbb\n" +
                    "babbbabbbababbbbabbaaabb\n" +
                    "baababbbabbbbaabbbabbbbb\n" +
                    "abbbbaabbababbbabbaaaaaa\n" +
                    "abababaabbbaabaaaabbbbbabaaaabaa\n" +
                    "bbabbabababaabbabaaaabaa\n" +
                    "babbbabbbaabaaabaababbbbbaabaaab\n" +
                    "aaabbbabbaaaabbbbbabababbaabbbbbbbababbbbabbabbb\n" +
                    "bbabbaabbabbbbbabbabbaabbbabbbabbbabbabbaaabaaba\n" +
                    "bbababababbababaaababbaa\n" +
                    "baaababababbabbabaaaaabbbbbaabbbbaabbbba\n" +
                    "aabbbababaababbbbaababbaabbbbaababbbabaa\n" +
                    "babbababaaabababbbabbabaaaabababaaaaababbabbbaaaaaababbaaaabbabaaababaab\n" +
                    "babbabbaaabbbbbabbabbaabbbbabaaaaabaabaa\n" +
                    "bbaabaaababbbbaaabbbbbbbaaabbaabbaabaaaaabaaabbbbabbabababaaaaaababaabab\n" +
                    "abbbbabbbbaaababaaabbaba\n" +
                    "abbabaaaabaabbbbbbbaaaab\n" +
                    "abaaabaaaaababaabbabbbab\n" +
                    "bbbbbaaabbabbbaaabbbbabbaaaababbabbababb\n" +
                    "bbbaaaaaaabaababbbaaabaa\n" +
                    "abbbbabbabababbabaabbbaa\n" +
                    "abaaabbaababaaabbbbbaabaaaabaaab\n" +
                    "abaabbbabbabbaabbababbbbbbabbbab\n" +
                    "abaabbbabbbbababbabaaaba\n" +
                    "bbabbaababaaaaaabbaabbabbabaabbbabbabbaa\n" +
                    "baaaaaaaabababbababababababbabababbabaaabbaabbbb\n" +
                    "aabbbbbababbabbabaaaaaababbbbbabaaabbbbaaaaabbba\n" +
                    "aaabbaabbbabbaababbbabbbabbabbbbbbbbbbab\n" +
                    "babaabaabbbaaabbbbaabbba\n" +
                    "abaabbbaabbaaababbaabaaa\n" +
                    "bbbbabbbaabaaabaabbaaabababbababaabbaaaaaabbbbbbbaaababbaaaaaabbbbaaaababbaaaaaa\n" +
                    "aaaaababbabaabbaabaabbaa\n" +
                    "abaabababbbbbabababbabbaaaabbbba\n" +
                    "baaaababbbabbbbaaababbab\n" +
                    "bbbabbabaabbaabbabbaaabb\n" +
                    "abaaaababaabbbbabbbbbbbbaaaaaaaababaaaabbbaaaaaabbbbaaabbbbababb";

    private static final String[] RULES_AS_LINE_ARRAY = RULES.split("\\R");
    private static final String[] MESSAGES_AS_LINE_ARRAY = MESSAGES.split("\\R");

    private static String transformRulesIntoRegex(String[] rules) {
        StringBuilder regex = new StringBuilder();

        return regex.toString();
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 19: Examples for puzzle 1 ###");
        System.out.println("Generated regex for rules: " + transformRulesIntoRegex(RULES_AS_LINE_ARRAY));
    }
}
