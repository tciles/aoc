package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08 extends BaseDay {

    public Day08() {
        day = "08";
    }

    @Override
    protected void answerOne() throws Exception {
        BufferedReader reader = getInputContent();

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line + " " + replaceHexadecimalCharacters(line));
        }
    }

    @Override
    protected void answerTwo() throws Exception {
        /*
        BufferedReader reader = getInputContent();

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int total = 0;

        System.out.println("Answer 2 : " + total);
        */
    }

    public String replaceHexadecimalCharacters(String input) {
        Pattern pattern = Pattern.compile("\\\\x([0-9A-Fa-f]{2})");
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            int hex = Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(result, Character.toString((char) hex));
        }

        matcher.appendTail(result);

        return result.toString();
    }
}
