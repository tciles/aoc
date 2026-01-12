package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.util.Arrays;

public class Day02 extends BaseDay {
    public Day02() {
        day = "02";
    }

    @Override
    protected void answerOne() throws Exception {
        final BufferedReader reader = getInputContent();

        int total = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            int[] parts = Arrays.stream(line.split("x")).mapToInt(Integer::parseInt).toArray();
            int l =  parts[0];
            int w = parts[1];
            int h = parts[2];

            int lwSide = l * w;
            int whSide = w * h;
            int hlSide = h * l;

            int minSide = Math.min(Math.min(lwSide, whSide), hlSide);

            int area = 2 * lwSide + 2 * whSide + 2 * hlSide;
            area += minSide;

            total += area;
        }

        System.out.printf("Answer 1 : %d\n", total);
    }

    @Override
    protected void answerTwo() throws Exception {
        final BufferedReader reader = getInputContent();

        int total = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            int[] parts = Arrays.stream(line.split("x")).mapToInt(Integer::parseInt).toArray();
            int l =  parts[0];
            int w = parts[1];
            int h = parts[2];

            int[] sortedParts = Arrays.stream(parts).sorted().toArray();

            int feetOfRibbonForPresent = sortedParts[0] * 2 + sortedParts[1] * 2;
            int feetOfRibbonForBow = l * w * h;

            total += (feetOfRibbonForPresent + feetOfRibbonForBow);
        }

        System.out.printf("Answer 2 : %d\n", total);
    }
}
