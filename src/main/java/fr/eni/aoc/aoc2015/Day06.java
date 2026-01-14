package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

public class Day06 extends BaseDay {

    public Day06() {
        day = "06";
    }

    private static class Operation {
        int[] from;
        int[] to;

        public Operation(int[] from, int[] to) {
            this.from = from;
            this.to = to;
        }

        public void apply(int[][] grid) {
            for (int i = (from[0]); i < (to[0] + 1); i++) {
                for (int j = (from[1]); j < (to[1] + 1); j++) {
                    grid[i][j] = process(grid[i][j]);
                }
            }
        }

        protected void parse(String line) {
            line = line.replace(" through ", ",");

            int[] parts = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            from[0] = parts[0];
            from[1] = parts[1];
            to[0] = parts[2];
            to[1] = parts[3];
        }

        protected int process(int value) {
            return value;
        }
    }

    private static class TurnOn extends Operation {
        TurnOn(String line) {
            super(new int[2], new int[2]);

            parse(line.replace("turn on ", ""));
        }

        @Override
        protected int process(int value) {
            return 1;
        }
    }

    private static class TurnOff extends Operation {
        TurnOff(String line) {
            super(new int[2], new int[2]);

            parse(line.replace("turn off ", ""));
        }

        @Override
        protected int process(int value) {
            return 0;
        }
    }

    private static class Toggle extends Operation {
        Toggle(String line) {
            super(new int[2], new int[2]);

            parse(line.replace("toggle ", ""));
        }

        @Override
        protected int process(int value) {
            return value == 1 ? 0 : 1;
        }
    }

    private static class TurnOnV2 extends Operation {
        TurnOnV2(String line) {
            super(new int[2], new int[2]);

            parse(line.replace("turn on ", ""));
        }

        @Override
        protected int process(int value) {
            return value + 1;
        }
    }

    private static class TurnOffV2 extends Operation {
        TurnOffV2(String line) {
            super(new int[2], new int[2]);

            parse(line.replace("turn off ", ""));
        }

        @Override
        protected int process(int value) {
            return Math.max(value - 1, 0);
        }
    }

    private static class ToggleV2 extends Operation {
        ToggleV2(String line) {
            super(new int[2], new int[2]);

            parse(line.replace("toggle ", ""));
        }

        @Override
        protected int process(int value) {
            return value + 2;
        }
    }

    @Override
    protected void answerOne() throws Exception {
        BufferedReader reader = getInputContent();

        int[][] grid = new int[1000][1000];

        String line;
        while ((line = reader.readLine()) != null) {
            Operation op = null;

            if (line.startsWith("turn on")) {
                op = new TurnOn(line);
            } else if (line.startsWith("turn off")) {
                op = new TurnOff(line);
            } else if (line.startsWith("toggle")) {
                op = new Toggle(line);
            }

            Objects.requireNonNull(op);
            op.apply(grid);
        }

        int total = 0;

        for (int[] row : grid) {
            for (int light : row) {
                if (light == 1) {
                    total++;
                }
            }
        }

        System.out.println("Answer 1 : " + total);

        // dumpGrid(grid);
    }

    @Override
    protected void answerTwo() throws Exception {
        BufferedReader reader = getInputContent();

        int[][] grid = new int[1000][1000];

        String line;
        while ((line = reader.readLine()) != null) {
            Operation op = null;

            if (line.startsWith("turn on")) {
                op = new TurnOnV2(line);
            } else if (line.startsWith("turn off")) {
                op = new TurnOffV2(line);
            } else if (line.startsWith("toggle")) {
                op = new ToggleV2(line);
            }

            Objects.requireNonNull(op);
            op.apply(grid);
        }

        int total = 0;

        for (int[] row : grid) {
            for (int light : row) {
                total += light;
            }
        }

        System.out.println("Answer 2 : " + total);

        // dumpGrid(grid);
    }

    private void dumpGrid(int[][] grid) {
        File f = new File("D:\\TCI\\projets\\aoc\\grid.txt");
        try (OutputStream osw = new FileOutputStream(f)) {
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    osw.write((grid[i][j] + " ").getBytes());
                }

                osw.write("\n".getBytes());
            }
        } catch (Exception ignored) {
        }
    }
}
