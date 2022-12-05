package com.github.rviehmann.aoc2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

    private static final String SAMPLE_TEMPLATE =
            "NNCB";

    private static final String SAMPLE_RULES =
            "CH -> B\n" +
                    "HH -> N\n" +
                    "CB -> H\n" +
                    "NH -> C\n" +
                    "HB -> C\n" +
                    "HC -> B\n" +
                    "HN -> C\n" +
                    "NN -> C\n" +
                    "BH -> H\n" +
                    "NC -> B\n" +
                    "NB -> B\n" +
                    "BN -> B\n" +
                    "BB -> N\n" +
                    "BC -> B\n" +
                    "CC -> N\n" +
                    "CN -> C";

    private static final String REAL_TEMPLATE =
            "OOBFPNOPBHKCCVHOBCSO";

    private static final String REAL_RULES =
            "NS -> H\n" +
                    "NN -> P\n" +
                    "FF -> O\n" +
                    "HF -> C\n" +
                    "KN -> V\n" +
                    "PO -> B\n" +
                    "PS -> B\n" +
                    "FB -> N\n" +
                    "ON -> F\n" +
                    "OK -> F\n" +
                    "OO -> K\n" +
                    "KS -> F\n" +
                    "FN -> F\n" +
                    "KC -> H\n" +
                    "NC -> N\n" +
                    "NB -> C\n" +
                    "KH -> S\n" +
                    "SV -> B\n" +
                    "BC -> S\n" +
                    "KB -> B\n" +
                    "SC -> S\n" +
                    "KP -> H\n" +
                    "FS -> K\n" +
                    "NK -> K\n" +
                    "OC -> H\n" +
                    "NH -> C\n" +
                    "PH -> F\n" +
                    "OS -> V\n" +
                    "BB -> C\n" +
                    "CC -> F\n" +
                    "CF -> H\n" +
                    "CP -> V\n" +
                    "VB -> N\n" +
                    "VC -> F\n" +
                    "PK -> V\n" +
                    "NV -> N\n" +
                    "FO -> S\n" +
                    "CK -> O\n" +
                    "BH -> K\n" +
                    "PN -> B\n" +
                    "PP -> S\n" +
                    "NF -> B\n" +
                    "SF -> K\n" +
                    "VF -> H\n" +
                    "HS -> F\n" +
                    "NP -> F\n" +
                    "SH -> V\n" +
                    "SK -> K\n" +
                    "PC -> V\n" +
                    "BO -> H\n" +
                    "HN -> P\n" +
                    "BK -> O\n" +
                    "BP -> S\n" +
                    "OP -> N\n" +
                    "SP -> N\n" +
                    "KK -> C\n" +
                    "HB -> H\n" +
                    "OF -> C\n" +
                    "VH -> C\n" +
                    "HO -> N\n" +
                    "FK -> V\n" +
                    "NO -> H\n" +
                    "KF -> S\n" +
                    "KO -> V\n" +
                    "PF -> K\n" +
                    "HV -> C\n" +
                    "SO -> F\n" +
                    "SS -> F\n" +
                    "VN -> K\n" +
                    "HH -> B\n" +
                    "OB -> S\n" +
                    "CH -> B\n" +
                    "FH -> B\n" +
                    "CO -> V\n" +
                    "HK -> F\n" +
                    "VK -> K\n" +
                    "CN -> V\n" +
                    "SB -> K\n" +
                    "PV -> O\n" +
                    "PB -> F\n" +
                    "VV -> P\n" +
                    "CS -> N\n" +
                    "CB -> C\n" +
                    "BS -> F\n" +
                    "HC -> B\n" +
                    "SN -> P\n" +
                    "VP -> P\n" +
                    "OV -> P\n" +
                    "BV -> P\n" +
                    "FC -> N\n" +
                    "KV -> S\n" +
                    "CV -> F\n" +
                    "BN -> S\n" +
                    "BF -> C\n" +
                    "OH -> F\n" +
                    "VO -> B\n" +
                    "FP -> S\n" +
                    "FV -> V\n" +
                    "VS -> N\n" +
                    "HP -> B";

    private static final String[] SAMPLE_RULES_ARRAY = SAMPLE_RULES.split("\\R");
    private static final String[] REAL_RULES_ARRAY = REAL_RULES.split("\\R");

    private static final String REGEX = "^([^ ]+) -> ([^ ]+)$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private static Map<String, String> buildRuleMap(String[] lines) {
        Map<String, String> ruleMap = new HashMap<>();
        for (String line : lines) {
            Matcher matcher = PATTERN.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Input does not match pattern.");
            }
            String search = matcher.group(1);
            String insert = matcher.group(2);
            ruleMap.put(search, insert);
        }
        return ruleMap;
    }

    private static String applyRules(String input, Map<String, String> ruleMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (input.length() - 1); i++) {
            String snippet = input.substring(i, i + 2);
            String insert = ruleMap.get(snippet);
            if (insert == null) {
                if (sb.length() == 0) {
                    sb.append(snippet);
                } else {
                    // Omit the first char, since it has been added to the StringBuilder already in the last cycle.
                    sb.append(snippet.charAt(1));
                }
            } else {
                if (sb.length() == 0) {
                    sb.append(snippet.charAt(0));
                    sb.append(insert);
                    sb.append(snippet.charAt(1));
                } else {
                    // Omit the first char, since it has been added to the StringBuilder already in the last cycle.
                    sb.append(insert);
                    sb.append(snippet.charAt(1));
                }
            }
        }
        return sb.toString();
    }

    private static Map<Character, Integer> countQuantities(String input) {
        Map<Character, Integer> qty = new HashMap<>();
        for (char c : input.toCharArray()) {
            qty.merge(c, 1, Integer::sum);
        }
        return qty;
    }

    private static int getMostCommon(Map<Character, Integer> qty) {
        Integer[] values = qty.values().toArray(new Integer[0]);
        Arrays.sort(values);
        return values[values.length - 1];
    }

    private static int getLeastCommon(Map<Character, Integer> qty) {
        Integer[] values = qty.values().toArray(new Integer[0]);
        Arrays.sort(values);
        return values[0];
    }

    public static void testWithExamplesForPuzzle1() {
        System.out.println("### Day 14: Examples for puzzle 1 ###");
        String current = SAMPLE_TEMPLATE;
        Map<String, String> ruleMap = buildRuleMap(SAMPLE_RULES_ARRAY);
        for (int i = 1; i <= 10; i++) {
            current = applyRules(current, ruleMap);
            if (i <= 4) {
                System.out.println("After step " + i + ": " + current);
            }
        }
        System.out.println("Most common: " + getMostCommon(countQuantities(current)));
        System.out.println("Least common: " + getLeastCommon(countQuantities(current)));
    }

    public static long doPuzzle1() {
        String current = REAL_TEMPLATE;
        Map<String, String> ruleMap = buildRuleMap(REAL_RULES_ARRAY);
        for (int i = 1; i <= 10; i++) {
            current = applyRules(current, ruleMap);
        }
        return getMostCommon(countQuantities(current)) - getLeastCommon(countQuantities(current));
    }

    public static long doPuzzle2() {
        //todo
        return 0;
    }
}
