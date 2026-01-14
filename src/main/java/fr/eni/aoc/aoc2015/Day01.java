package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

public class Day01 extends BaseDay {
    @Override
    protected void answerOne() throws Exception {
        final BufferedReader reader = getInputContent();

        int total = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            byte[] bytes = line.getBytes(StandardCharsets.UTF_8);

            for (byte b : bytes) {
                total += (0x28 == b) ? 1 : -1;
            }
        }

        System.out.println("Answer 1 : " + total);
    }

    @Override
    protected void answerTwo() throws Exception {
        BufferedReader reader = getInputContent();

        int total = 0;
        int position = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            byte[] bytes = line.getBytes(StandardCharsets.UTF_8);

            for (byte b : bytes) {
                position++;
                total += (0x28 == b) ? 1 : -1;

                if (total < 0) {
                    break;
                }
            }
        }

        System.out.println("Answer 2 : " + position);
    }
}
