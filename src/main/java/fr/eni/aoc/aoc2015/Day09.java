package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day09 extends BaseDay {

    public Day09() {
        day = "09";
    }

    @Override
    protected void answerOne() throws Exception {
        BufferedReader reader = getInputContent();

        Map<String, Integer> dists = new HashMap<>();
        Set<String> places = new HashSet<>();

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);

            String[] split = line.split(" ");

            places.add(split[0]);
            places.add(split[2]);

            dists.put(split[0]+split[2], parseInt(split[4]));
            dists.put(split[2]+split[0], parseInt(split[4]));
        }
    }

    @Override
    protected void answerTwo() throws Exception {
        BufferedReader reader = getInputContent();

        List<Move> moves = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.replace(" to ", "#").replace(" = ", "#");
            List<String> parts = Arrays.stream(line.split("#")).map(String::trim).toList();

            String a = parts.get(0);
            String b = parts.get(1);
            int distance = parseInt(parts.get(2));

            moves.add(new Move(a, b, distance));
            moves.add(new Move(b, a, distance));
        }

        List<Route> routes = new ArrayList<>();

        for (Move start : moves) {
            Route route = new Route();
            route.addMove(start);

            for (Move next : moves) {
                if (route.canAddMove(next)) {
                    route.addMove(next);
                }
            }

            routes.add(route);
        }

        int min = Integer.MAX_VALUE;

        for (Route route : routes) {
            if (route.cities.size() < 8) {
                continue;
            }

            System.out.println("---\nStart");
            for (Move move : route.moves) {
                System.out.println("->\t" + move);
            }
            System.out.println("Cities : " + route.cities.size());
            System.out.println("Total : " + route.distance);
            System.out.println("---");

            if (route.distance < min) {
                min = route.distance;
            }
        }

        System.out.println("Minimal distance : " + min);
    }

    private static class Move {
        String from;
        String to;
        int distance;

        Move(String from, String to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Move(from=" + from + ", to=" + to + ", distance=" + distance + ")";
        }
    }

    private static class Route {
        List<String> cities = new ArrayList<>();
        List<Move> moves = new ArrayList<>();
        String lastCity = "";
        int distance = 0;

        boolean canAddMove(Move move) {
            System.out.println(cities);

            return lastCity.equals(move.from)
                    && !cities.contains(move.to);
        }

        void addMove(Move move) {
            if (!cities.contains(move.from)) {
                cities.add(move.from);
            }

            if (!cities.contains(move.to)) {
                cities.add(move.to);
            }

            this.moves.add(move);
            lastCity = move.to;
            distance += move.distance;
        }
    }
}
