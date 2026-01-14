package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Day09 extends BaseDay {

    private int n;
    private boolean[] used;
    private int[][] dist;
    private int minSize = Integer.MAX_VALUE;
    private int maxSize = Integer.MIN_VALUE;

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

        Map<String, Integer> indexes = new HashMap<>();
        List<int[]> edges = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.replace(" to ", "#").replace(" = ", "#");
            List<String> parts = Arrays.stream(line.split("#")).map(String::trim).toList();

            String a = parts.get(0);
            String b = parts.get(1);
            int distance = parseInt(parts.get(2));

            indexes.putIfAbsent(a, indexes.size());
            indexes.putIfAbsent(b, indexes.size());

            edges.add(new int[]{ indexes.get(a), indexes.get(b), distance });
        }

        n = indexes.size();
        dist = new int[n][n];

        for (int[] e : edges) {
            dist[e[0]][e[1]] = e[2];
            dist[e[1]][e[0]] = e[2];
        }

        used = new boolean[n];

        for (int i = 0; i < n; i++) {
            used[i] = true;
            dfs(i, 1, 0);
            used[i] = false;
        }

        System.out.println("Min : " + minSize);
        System.out.println("Max : " + maxSize);
    }

    private void dfs(int current, int count, int total) {
        // Toutes les villes ont été parcourues
        if (count == n) {
            minSize = Math.min(minSize, total);
            maxSize = Math.max(maxSize, total);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(i, count + 1, total + dist[current][i]);
                used[i] = false;
            }
        }
    }
}
