package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day03 extends BaseDay {

    private final static byte EAST = 0x3C;
    private final static byte WEST = 0x3E;
    private final static byte NORTH = 0x5E;
    private final static byte SOUTH = 0x76;

    public Day03() {
        day = "03";
    }

    @Override
    protected void answerOne() throws Exception {
        final BufferedReader reader = getInputContent();
        final Set<Point> positions = new HashSet<>();

        String line;
        while ((line = reader.readLine()) != null) {
            byte[] bytes = line.getBytes();

            int x = 0;
            int y = 0;

            for (byte aByte : bytes) {
                if (aByte == EAST) {
                    x += 1;
                } else if (aByte == WEST) {
                    x -= 1;
                } else if (aByte == NORTH) {
                    y -= 1;
                } else if (aByte == SOUTH) {
                    y += 1;
                }

                positions.add(new Point(x, y));
            }
        }

        System.out.printf("Answer 1 : %d\n", positions.size());
    }

    @Override
    protected void answerTwo() throws Exception {
        final BufferedReader reader = getInputContent();

        final Set<Point> santaPositions = new HashSet<>();
        final Set<Point> robotPositions = new HashSet<>();

        final Point santaPosition = new Point(0, 0);
        final Point robotPosition = new Point(0, 0);

        System.out.println("Initial position : " + santaPosition);

        String line;
        while ((line = reader.readLine()) != null) {
            byte[] bytes = line.getBytes();
            // byte[] bytes = "^v".getBytes();
            // byte[] bytes = "^>v<".getBytes();
            // byte[] bytes = "^v^v^v^v^v".getBytes();

            Point position;
            Set<Point> positions;

            for (int i = 0; i < bytes.length; i++) {
                byte aByte = bytes[i];

                if (i % 2 == 0) {
                    position = santaPosition;
                    positions = santaPositions;
                } else {
                    position = robotPosition;
                    positions = robotPositions;
                }

                if (aByte == EAST) {
                    position.x += 1;
                } else if (aByte == WEST) {
                    position.x -= 1;
                } else if (aByte == NORTH) {
                    position.y -= 1;
                } else if (aByte == SOUTH) {
                    position.y += 1;
                }

                Point p = new Point(position.x, position.y);

                if (!positions.contains(p)) {
                    if (i % 2 == 0) {
                        System.out.println("Santa : " + p.x + " " + p.y);
                    } else {
                        System.out.println("Robot : " + p.x + " " + p.y);
                    }
                }

                positions.add(p);
            }
        }

        final Set<Point> mergedPositions = new HashSet<>();
        mergedPositions.add(new Point(0, 0));
        mergedPositions.addAll(santaPositions);
        mergedPositions.addAll(robotPositions);

        System.out.printf("Answer 2 : %d\n", mergedPositions.size());
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point p)) return false;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
