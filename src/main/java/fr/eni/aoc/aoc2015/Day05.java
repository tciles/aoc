package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.util.Arrays;

public class Day05 extends BaseDay {

    public Day05() {
        day = "05";
    }

    @Override
    protected void answerOne() throws Exception {
        BufferedReader reader = getInputContent();

        int total = 0;

        String[] excluded = {"ab", "cd", "pq", "xy"};
        byte[] vowels = {'a', 'e', 'i', 'o', 'u'};


        String line;
        while ((line = reader.readLine()) != null) {
            byte[] bytes = line.getBytes();

            boolean isExcluded = false;

            for (String s : excluded) {
                if (line.contains(s)) {
                    isExcluded = true;
                    break;
                }
            }

            if (isExcluded) {
                continue;
            }

            byte prev = ' ';
            boolean hasDouble = false;

            for (byte cByte : bytes) {
                if (cByte == prev) {
                    hasDouble = true;
                    break;
                }
                prev = cByte;
            }

            if (!hasDouble) {
                continue;
            }

            // Contain least aeiou vowels
            int countVowels = 0;

            for (byte cByte : bytes) {
                for (byte vowel : vowels) {
                    if (cByte == vowel) {
                        countVowels++;
                    }
                }
            }


            if (countVowels < 3) {
                continue;
            }

            total++;
        }

        System.out.println("Answer 1 : " + total);
    }

    @Override
    protected void answerTwo() throws Exception {
        BufferedReader reader = getInputContent();

        int total = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            byte[] bytes = line.getBytes();

            // It contains at least one letter which repeats with exactly one letter between them,
            // like xyx, abcdefeghi (efe), or even aaa.
            boolean isOk = false;

            for (int i = 1; i < (bytes.length - 1); i++) {
                byte previous = bytes[i - 1];
                byte next = bytes[i + 1];

                if (previous == next) {
                    isOk = true;
                    break;
                }
            }

            if (!isOk) {
                continue;
            }

            isOk = false;

            // It contains a pair of any two letters that appears at least twice in
            // the string without overlapping, like xyxy (xy) or aabcdefgaa (aa),
            // but not like aaa (aa, but it overlaps).


            for (int i = 1; i < (bytes.length - 1); i++) {
                byte previous = bytes[i - 1];
                byte current = bytes[i];
                byte next = bytes[i + 1];

                if (previous == next) {
                    continue;
                }

                String str = "";

                if (previous == current) {
                    str += (char) previous;
                    str += (char) current;
                    isOk = true;
                } else if (current == next) {
                    str += (char) current;
                    str += (char) next;
                    isOk = true;
                }

                if (!str.isEmpty()) {
                    line = line.replaceAll(str, "_");
                }
            }

            if (!isOk) {
                continue;
            }

            int l = 0;
            for (char c : line.toCharArray()) {
                if (c == '_') {
                    l++;
                }
            }

            if (l > 1) {
                System.out.println(line);
                total++;
            }



        }

        System.out.println("Answer 2 : " + total);
    }
}
