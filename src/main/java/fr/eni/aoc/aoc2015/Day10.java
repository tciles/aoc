package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.util.*;

public class Day10 extends BaseDay {
    @Override
    protected void answerOne() throws Exception {
        // String output = "111221"; // 312211
        String output = "1113122113";

        for (int i = 0; i < 40; i++) {
            output = transform(parse(output));
        }

        System.out.println(output.length());
    }

    @Override
    protected void answerTwo() throws Exception {
        String output = "1113122113";

        for (int i = 0; i < 50; i++) {
            output = transform(parse(output));
        }

        System.out.println(output.length());
    }

    private List<String> parse(String input) {
        List<String> parts = new ArrayList<>();

        StringBuilder buffer = new StringBuilder();

        char lastChar = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c != lastChar && !buffer.isEmpty()) {
                parts.add(buffer.toString());
                buffer = new StringBuilder();
            }

            buffer.append(c);

            lastChar = c;
        }

        if (!buffer.isEmpty()) {
            parts.add(buffer.toString());
        }

        return parts;
    }

    private String transform(List<String> input) {
        StringBuilder output = new StringBuilder();

        for (String s : input) {
            output.append(s.length()).append(s.charAt(0));
        }

        return output.toString();
    }
}
