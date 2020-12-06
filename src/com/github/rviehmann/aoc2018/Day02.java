package com.github.rviehmann.aoc2018;

import java.util.HashMap;
import java.util.Map;

public class Day02 {

    // From: https://adventofcode.com/2018/day/2/input
    private static final String INPUT =
            "bpacnmelhhzpygfsjoxtvkwuor\n" +
                    "biacnmelnizqygfsjoctvkwudr\n" +
                    "bpaccmllhizyygfsjoxtvkwudr\n" +
                    "rpacnmelhizqsufsjoxtvkwudr\n" +
                    "bfacnmelhizqygfsjoxtvwwudp\n" +
                    "bpacnmelhizqynfsjodtvkyudr\n" +
                    "bpafnmelhizqpgfsjjxtvkwudr\n" +
                    "bpackmelhizcygfsjoxtvkwudo\n" +
                    "bmacnmilhizqygfsjoltvkwudr\n" +
                    "bpafnmelhizuygfsjoxtvkwsdr\n" +
                    "boacnmylhizqygfsjoxtvxwudr\n" +
                    "bpbcjmelhizqygfsjoxtgkwudr\n" +
                    "bpacnmglhizqygfsjixtlkwudr\n" +
                    "bpacnmclhizqygfsjoxtvkwtqr\n" +
                    "bpacnmelhczqygtsjoptvkwudr\n" +
                    "bpacnmelhizqywfsaoxtvkbudr\n" +
                    "apacnmelhizqygcsjoxtvkwhdr\n" +
                    "bpacnmelrizqygfsbpxtvkwudr\n" +
                    "tpkcnmelpizqygfsjoxtvkwudr\n" +
                    "bpacnmelhizqlgfsjobtmkwudr\n" +
                    "npacnmelhizqygffjoxtvkwudf\n" +
                    "bpacnmeehqzqygqsjoxtvkwudr\n" +
                    "bpecnmelhizqigfsjvxtvkwudr\n" +
                    "bpacnmelhizqysfsjoxtvkdfdr\n" +
                    "bpacnfelhkzqygfsjoxtvkwfdr\n" +
                    "bpacnbelvizqygfsjoxthkwudr\n" +
                    "bpacnoelhizqygfejoxtvkwudn\n" +
                    "bpacnmelhizqygfzpkxtvkwudr\n" +
                    "bpahnmelhizqyufsjoxmvkwudr\n" +
                    "bpacnmelhizqygfsnoxtvkwmmr\n" +
                    "bpacnmelhizqygfsjoatvkludf\n" +
                    "bpacnmylhizqygfsjlxtvksudr\n" +
                    "bpacnmekhpzqygysjoxtvkwudr\n" +
                    "bpacnselhizqogfswoxtvkwudr\n" +
                    "bpacnmelhizqprfsjoxwvkwudr\n" +
                    "bpatnmelhinqygfsjoctvkwudr\n" +
                    "bpacnqelhqzqygfsxoxtvkwudr\n" +
                    "bpabnmelhiyqygfsjoxtykwudr\n" +
                    "bpacnivlhizqygfsjoxtviwudr\n" +
                    "bpkcnmylhizqygfsjoxtvkwcdr\n" +
                    "bpafnmflhizqygtsjoxtvkwudr\n" +
                    "bpachmelhizqygfsjixtvkwudg\n" +
                    "bpacymelhizqygfsjoxtykwuar\n" +
                    "bpacnkelhizqdgfsjoxtskwudr\n" +
                    "bpacnmezhizqggbsjoxtvkwudr\n" +
                    "bpacnmqlhizqygrsjoxzvkwudr\n" +
                    "bpaczmelhizqyhfsjoxfvkwudr\n" +
                    "bdacnmelhyzqygusjoxtvkwudr\n" +
                    "bpacbmelhizqywfsjostvkwudr\n" +
                    "bpacnmelhihzygfstoxtvkwudr\n" +
                    "bpactmelhizqygfsjcxtvkwydr\n" +
                    "bkacnmethizqytfsjoxtvkwudr\n" +
                    "bpacnmalhizqydfskoxtvkwudr\n" +
                    "spacnmelbizqygfsjoxdvkwudr\n" +
                    "lpalnmelhizoygfsjoxtvkwudr\n" +
                    "bpacjmeghizqygfsjoxtviwudr\n" +
                    "bpacnmeqhizxygfsjoxgvkwudr\n" +
                    "bpacnmelhizqygosjoxtvkkuhr\n" +
                    "bpacnmelhiznbxfsjoxtvkwudr\n" +
                    "bgacnmelhizqygfsjbxivkwudr\n" +
                    "bpacnmelhizqygfjjowtvswudr\n" +
                    "bpacnmelhizqygfsjovtgkmudr\n" +
                    "bpacnmelcmzqygfspoxtvkwudr\n" +
                    "bpvcnmelhizqyvfcjoxtvkwudr\n" +
                    "bpacnmeahizqjgfsjoxtvkwukr\n" +
                    "bpacnoelwizqygfsjoxtvkaudr\n" +
                    "xpacnmelhizqygfsjoxdvkwedr\n" +
                    "mpacnmelqizqygfsjoxtvkwudx\n" +
                    "bppcnmelhizqygfsjfxtvkhudr\n" +
                    "bpacnmclhizqyhfsjaxtvkwudr\n" +
                    "opacsmelhizqygfsjmxtvkwudr\n" +
                    "bpafnmelhizqjgfsjoxtvkrudr\n" +
                    "bpdcnmilhizqygfsjoxtvkludr\n" +
                    "bpainmelhizqygfsjtntvkwudr\n" +
                    "bradnmelhizqygfsjextvkwudr\n" +
                    "bpacnmelhizqygfmsoxtvkwudg\n" +
                    "bpacneelhizqygvrjoxtvkwudr\n" +
                    "bpacnpelhizqygfsjoxyvkwudf\n" +
                    "bpacnmelhizqygfsqoqtvkwodr\n" +
                    "bpacnmelhizjyghsjoxcvkwudr\n" +
                    "bpacnmelmibqygfsjoxtvnwudr\n" +
                    "jpacnmelaizqygfwjoxtvkwudr\n" +
                    "zpachmelhizqygfsjsxtvkwudr\n" +
                    "bpacnmelfizqykfsjomtvkwudr\n" +
                    "bpacnmllwizqygfsjoxtvkwusr\n" +
                    "bpaynmelhizqygfsjoxtvowcdr\n" +
                    "jpacnmqlhizqygfsjoxtvknudr\n" +
                    "bpacxmelhizqyffsjoxtvkwugr\n" +
                    "apawnmelhizqygfsjtxtvkwudr\n" +
                    "mpacnmelhitqigfsjoxtvkwudr\n" +
                    "bpacnmelhhzqygfsjoxtvkyzdr\n" +
                    "gpacnmelhizqynfsjoxtvkwudm\n" +
                    "bnacnkelhizqygfsjoxtpkwudr\n" +
                    "bpacnmelfizqygfsumxtvkwudr\n" +
                    "bpacnmelhisqygfsjohtvowudr\n" +
                    "bpacnmelhimqygxsjoxtvkwudn\n" +
                    "bpscnmeliizqygfsjoxtvkwunr\n" +
                    "qpacnmelhizqycfsjoxtvkwndr\n" +
                    "bpacnmelhijqygfsjohtvkyudr\n" +
                    "bpacnmelhizqykfsjkxtvknudr\n" +
                    "bpacnqilhizqygfsjoxtvkoudr\n" +
                    "bpacnmelhizqzgmsjoxtvkwurr\n" +
                    "bpdcnmelhizqygfsjoutukwudr\n" +
                    "bpecnmeghizqygfsjoxgvkwudr\n" +
                    "bpicnmelhizqygfrjoxtvlwudr\n" +
                    "bpacnmelhizfygfsroxtvkwodr\n" +
                    "buacnmelhizqygjsjoxtvkvudr\n" +
                    "bpacnmelhixqykfsjoxtvrwudr\n" +
                    "bpacnmelhizqygvejcxtvkwudr\n" +
                    "bpacnmjlhizqylfsjoxtvkwuor\n" +
                    "qpacnmelhizqygfsjoxfdkwudr\n" +
                    "bpfcnmemhizqygfsjoxtvknudr\n" +
                    "bpacnmelhizqoffsjqxtvkwudr\n" +
                    "hpacnielhiqqygfsjoxtvkwudr\n" +
                    "gpacnmelhizqygfsewxtvkwudr\n" +
                    "bpacnmellizqylxsjoxtvkwudr\n" +
                    "bpacnmenhizqymfsjoxtvkmudr\n" +
                    "bpacnfelhizqygcsjoltvkwudr\n" +
                    "bpacnmelhqqqygfsjoxtvkuudr\n" +
                    "bplgnmelhiqqygfsjoxtvkwudr\n" +
                    "bpacnzelhizqygfgjoxtvnwudr\n" +
                    "bpacnmelhizqygfsjoktvknunr\n" +
                    "bpacnmdlhioqygfnjoxtvkwudr\n" +
                    "epacnmelwizqyjfsjoxtvkwudr\n" +
                    "bpacxmelhazfygfsjoxtvkwudr\n" +
                    "bpacnmejhezqygfsjoxtskwudr\n" +
                    "bpacnqelhihqyzfsjoxtvkwudr\n" +
                    "bpacnbelhizqyrfsjoxtvkmudr\n" +
                    "bpacnmelhizqygfsjoxtylwzdr\n" +
                    "bpacnmelwizqygfsjodtvkhudr\n" +
                    "bpacnnelhizqygfsjoxtwkwadr\n" +
                    "bpacimelhizqygfsnoxtvkwuor\n" +
                    "bpacnmelhizqyaasjoxtlkwudr\n" +
                    "bpacnmelhizqyeffjoxtvkwuds\n" +
                    "bpacnmenhizqygxscoxtvkwudr\n" +
                    "bpacnmelhidqygfsjowtskwudr\n" +
                    "bpacnmeliizqygfsjoxhvkwucr\n" +
                    "bpacimelhizqygfsjoxtvktuwr\n" +
                    "bpainmelhhzqygfsjzxtvkwudr\n" +
                    "bpacamelhizqygfsjogtvkwbdr\n" +
                    "bpccnmelgizqygfsjoxtykwudr\n" +
                    "bpacnmelhizwegfsjoxtvkwadr\n" +
                    "bpackmelhbzqygqsjoxtvkwudr\n" +
                    "bpacymeihizqyffsjoxtvkwudr\n" +
                    "bpacnielhczqygfsjoxtvkwudk\n" +
                    "bpacnmejhizqygffjoxjvkwudr\n" +
                    "ppacnmelhizqygfsjoxtigwudr\n" +
                    "bpjcnmolhizqygfsjoxtvkwndr\n" +
                    "bpacnmelcizqygrsjoxtakwudr\n" +
                    "cpawnmelhizqygfsjoxmvkwudr\n" +
                    "bwacnmelhizqygesjoxtakwudr\n" +
                    "bpacnmelhizqygfsjexsvkwddr\n" +
                    "bpaunmelhiuqygfsjoxtvkwtdr\n" +
                    "bpacnmellimqygfsjextvkwudr\n" +
                    "bpacnmerhizqygfsaoxvvkwudr\n" +
                    "bpacnmglhizqygfsjixtukwudr\n" +
                    "ppacnmelhizqygfsjoxtvkdudp\n" +
                    "bpacnmedhizqygukjoxtvkwudr\n" +
                    "bpccnmelhizqngfsjoxtvkwadr\n" +
                    "bgacnmeldizqygfscoxtvkwudr\n" +
                    "bpacngelhizsygfsjoxtvkwkdr\n" +
                    "bpacnpelhizqygfsjoxctkwudr\n" +
                    "bpacnmylhizqygfcjoxtvkwmdr\n" +
                    "npacnmelhizqygfsjoxtwkwuds\n" +
                    "bpaxnmelhizqydfsjoxyvkwudr\n" +
                    "bpacnhelhizjygfsjoxtvkmudr\n" +
                    "bpacnkelhczqygfnjoxtvkwudr\n" +
                    "bfacnmelhizrygfsjoxtvkwodr\n" +
                    "bpycnmelhizqygfofoxtvkwudr\n" +
                    "qpacpselhizqygfsjoxtvkwudr\n" +
                    "bpvcnmelhezqygfsjoxttkwudr\n" +
                    "bpacnmwlhizqygfijoxtmkwudr\n" +
                    "bsacnmelhikqygfsjoxttkwudr\n" +
                    "bpccnxelhizqyafsjoxtvkwudr\n" +
                    "bpacnmelhizqygfswhxtvewudr\n" +
                    "vpacnmzlhizqygfsvoxtvkwudr\n" +
                    "bpacnmelhihqygfsjoxtvkqurr\n" +
                    "bpacnmelhixqygazjoxtvkwudr\n" +
                    "bpavnmelhizqygfsjozpvkwudr\n" +
                    "bpacnmclhizuygfsjoxmvkwudr\n" +
                    "bpacnmelhizryufsjoxtkkwudr\n" +
                    "bpacnmelhtzqygfsjobtvkwufr\n" +
                    "bpacnmelhizqmlfsjoxtvkwudq\n" +
                    "bpaaneelhizqygfsjlxtvkwudr\n" +
                    "bpacnmelhxzqygfsjoxthkwuhr\n" +
                    "bpacnmeshizqygfcjoxtvkwude\n" +
                    "bpacnzqlhizqygfsxoxtvkwudr\n" +
                    "bgaanmelhizqycfsjoxtvkwudr\n" +
                    "bpacnmexhizqygfsroxtvkwudn\n" +
                    "bpmmnmelhizqygfajoxtvkwudr\n" +
                    "bpacnmelhizqylfsjoxtckwhdr\n" +
                    "bpicnmelhizqyrfsjoxtvkwudi\n" +
                    "zpacnmelhizvycfsjoxtvkwudr\n" +
                    "bpamnmkllizqygfsjoxtvkwudr\n" +
                    "bpacnmelhrzqyrfsjoxgvkwudr\n" +
                    "bpadnmelhczqygfsjoxtlkwudr\n" +
                    "bpacrmelhizqygrsjoxtvkiudr\n" +
                    "lpacnmelhizqygfsjoxtgkwxdr\n" +
                    "fpacnmalhiuqygfsjoxtvkwudr\n" +
                    "bpacnmelhizqygfsjixtvfwcdr\n" +
                    "bpccnmelhxzqygfkjoxtvkwudr\n" +
                    "bpacnmepaizqygfsjoctvkwudr\n" +
                    "tpacnmelhivqygfsxoxtvkwudr\n" +
                    "kpacnfelhitqygfsjoxtvkwudr\n" +
                    "baacnzelhizqygfsjoxtvkwudx\n" +
                    "bcycnmeghizqygfsjoxtvkwudr\n" +
                    "wpacotelhizqygfsjoxtvkwudr\n" +
                    "bpacnmsshizqygrsjoxtvkwudr\n" +
                    "blacnmelhizqygfsjoxtykwvdr\n" +
                    "bkacnmelhizqygfsjoxuvkludr\n" +
                    "bpacnmelhizaugfsjoxtvhwudr\n" +
                    "fpavnmelhizqygfsgoxtvkwudr\n" +
                    "bpachmelnizqygfsjextvkwudr\n" +
                    "bpacnmelhizqpgfsjoxtvkwldu\n" +
                    "bpacnmelhizqygfsloftvywudr\n" +
                    "bpacntelhvzqygfejoxtvkwudr\n" +
                    "bpacnmeldizqygfsjmxtvkdudr\n" +
                    "byacnmelhizqygfsjsxtvkwudh\n" +
                    "bpacnmellizqygssxoxtvkwudr\n" +
                    "bpacnmelhizqygfsjootvknuir\n" +
                    "bpacnmelhitqjgfsjoxivkwudr\n" +
                    "bpacnmelhazaygfsjoxtvfwudr\n" +
                    "bpacnzenhizqygfsjzxtvkwudr\n" +
                    "bpacnmelhizqypfsdoxtvkwuar\n" +
                    "bpannmelhizqygnsjoxtvkwndr\n" +
                    "bracnmeldizsygfsjoxtvkwudr\n" +
                    "bpacnmelhizwygfsjugtvkwudr\n" +
                    "bpatnmelhizqygfsjoytvkwulr\n" +
                    "upacnmelhizqygfsjurtvkwudr\n" +
                    "bpaenmezhizqygfsjostvkwudr\n" +
                    "bpacnmelhizpygfsjodhvkwudr\n" +
                    "bpacnmelhizqygfsjogtvkguwr\n" +
                    "bpacnmelhisqygfsjoxtpkuudr\n" +
                    "bxacnmelhizqygfsjdxtvkfudr\n" +
                    "bpacnmelhizqygfsjohqvkwudu\n" +
                    "bzacnmtlhizqygfsjoxsvkwudr\n" +
                    "bpacnmplhixrygfsjoxtvkwudr\n" +
                    "bpacnmelhizqhgfsjomtvkwudg\n" +
                    "bpacnmezhizqygfsjxxtykwudr\n" +
                    "bpacnmwlhizqygfujoxtzkwudr\n" +
                    "tpacnmelhizqygfsjoxkvpwudr\n" +
                    "bpawsmenhizqygfsjoxtvkwudr\n" +
                    "bpacnmelhizqtgfsjoxttkwuqr\n" +
                    "bpkcbmelhizqygfsjoxtvkwucr\n" +
                    "bpacfmekhizqygfsjoxtvkwuds\n" +
                    "bpacnmethizqynfajoxtvkwudr\n" +
                    "bpocnmclhizqygfsjoxtvkwukr\n" +
                    "zpacnmwlhizqygfsjoxzvkwudr\n" +
                    "bpacpoelhqzqygfsjoxtvkwudr\n" +
                    "bpacnlelhizqyzfsjoxtvkwukr";

    private static final String[] INPUT_AS_LINE_ARRAY = INPUT.split("\\R");

    public static class BoxId {

        final String id;
        final Map<Character, Integer> charcount;

        public BoxId(String id, Map<Character, Integer> charcount) {
            this.id = id;
            this.charcount = charcount;
        }

        public static BoxId fromString(String id) {
            Map<Character, Integer> charcount = new HashMap<>();
            for (char c : id.toCharArray()) {
                charcount.merge(c, 1, Integer::sum);
            }
            return new BoxId(id, charcount);
        }

        public boolean hasLetterNTimes(int n) {
            for (Map.Entry<Character, Integer> entry : charcount.entrySet()) {
                if (entry.getValue() == n) return true;
            }
            return false;
        }
    }

    public static long findNrOfDifferentChars(String str1, String str2) {
        if (str1.length() != str2.length()) {
            throw new IllegalArgumentException("Strings don't have the same length, this is currently not supported.");
        }
        long differences = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) differences++;
        }
        return differences;
    }

    public static String removeDifferingChars(String str1, String str2) {
        if (str1.length() != str2.length()) {
            throw new IllegalArgumentException("Strings don't have the same length, this is currently not supported.");
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i)) output.append(str1.charAt(i));
        }
        return output.toString();
    }

    public static long doPuzzle1() {
        long idsWithDoubleLetters = 0;
        long idsWithTripleLetters = 0;
        for (String line : INPUT_AS_LINE_ARRAY) {
            BoxId boxId = BoxId.fromString(line);
            if (boxId.hasLetterNTimes(2)) idsWithDoubleLetters++;
            if (boxId.hasLetterNTimes(3)) idsWithTripleLetters++;
        }
        return idsWithDoubleLetters * idsWithTripleLetters;
    }

    public static String doPuzzle2() {
        for (int i = 0; i < INPUT_AS_LINE_ARRAY.length; i++) {
            for (int j = 0; j < INPUT_AS_LINE_ARRAY.length; j++) {
                if (i != j) {
                    long differences = findNrOfDifferentChars(INPUT_AS_LINE_ARRAY[i], INPUT_AS_LINE_ARRAY[j]);
                    if (differences == 1) {
                        System.out.println("Found very similar box IDs: " + INPUT_AS_LINE_ARRAY[i] + " vs. " + INPUT_AS_LINE_ARRAY[j]);
                        return removeDifferingChars(INPUT_AS_LINE_ARRAY[i], INPUT_AS_LINE_ARRAY[j]);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Did not find two strings that differ in exactly one character.");
    }
}
