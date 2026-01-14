package fr.eni.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class BaseDay {
    protected String year = "2015";
    protected String day = "01";

    protected BufferedReader getInputContent() {
        String path = "inputs/" + year + "/" + day + ".txt";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        Objects.requireNonNull(inputStream);

        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public void run() throws Exception {
        System.out.println("======================");
        System.out.println("==== AOC " + day + " / " + year + " ===");
        System.out.println("======================");

        answerOne();
        answerTwo();
    }

    protected void answerOne() throws Exception {
        throw new RuntimeException("Method not implemented");
    }

    protected void answerTwo() throws Exception {
        throw new RuntimeException("Method not implemented");
    }
}
