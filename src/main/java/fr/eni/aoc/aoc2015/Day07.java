package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.io.BufferedReader;
import java.util.*;

public class Day07 extends BaseDay {

    public Day07() {
        day = "07";
    }

    private static class Wire {
        private final String name;

        private int value;

        private boolean isInitialized;

        public Wire(String name) {
            if (isInteger(Objects.requireNonNull(name))) {
                this.name = "__" + name + "__";
                this.value = Integer.parseInt(name);
                this.isInitialized = true;
            } else {
                this.name = name;
                this.value = 0;
                this.isInitialized = false;
            }
        }

        public Wire(String name, int value) {
            this(name);

            this.value = value;
        }

        public Wire(String name, int value, boolean initialized) {
            this(name);

            this.value = value;
            this.isInitialized = initialized;
        }

        private boolean isInteger(String s) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }

        public String getName() {
            return name;
        }

        public boolean isInitialized() {
            return isInitialized;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
            this.isInitialized = true;
        }

        @Override
        public String toString() {
            return "Wire(pointer=" + Integer.toHexString(hashCode()) + ", name=" + name + ", init=" + isInitialized + ", value=" + value + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Wire wire = (Wire) o;
            return Objects.equals(name, wire.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static class Operation {
        private Wire leftWire;

        private Wire rightWire;

        private String operator;

        private Wire destination;

        boolean isEmitted;

        Operation() {
            this.isEmitted = false;
        }

        Operation(Wire leftWire, Wire rightWire, String operator, Wire destination) {
            this.leftWire = leftWire;
            this.rightWire = rightWire;
            this.operator = operator;
            this.destination = destination;
            this.isEmitted = false;
        }

        public boolean canSignal() {
            return leftWire.isInitialized() && rightWire.isInitialized();
        }

        public void emit() {
            int signal = leftWire.getValue() + rightWire.getValue();

            destination.setValue(signal);
        }

        @Override
        public String toString() {
            return "Operation(\n\tleft=" + leftWire + ", \n\toperator=\"" + operator + "\", \n\tright=" + rightWire + ", \n\tdestination=" + destination + ", \n\temitted=" + isEmitted + ")";
        }
    }


    private List<String> parseTokens(String line) {
        StringBuilder buffer = new StringBuilder();

        List<String> tokens = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == ' ') {
                tokens.add(buffer.toString());
                buffer = new StringBuilder();
                continue;
            }

            buffer.append(c);
        }

        if (!buffer.isEmpty()) {
            tokens.add(buffer.toString());
        }

        return tokens;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void answerOne() throws Exception {
        BufferedReader reader = getInputContent();

        Map<String, Wire> wires = new HashMap<>();
        List<Operation> operations = new ArrayList<>();

        String line;
        int i = 0;

        while ((line = reader.readLine()) != null) {
            List<String> tokens = parseTokens(line);

            Operation operation = new Operation();

            int pos;
            for (pos = 0; pos < tokens.size(); pos++) {
                String token = tokens.get(pos);

                switch (token) {
                    case "->":
                        token = tokens.get(++pos);

                        if (wires.containsKey(token)) {
                            operation.destination = wires.get(token);
                        } else {
                            Wire w = new Wire(token);
                            wires.put(token, w);
                            operation.destination = w;
                        }

                        // Part 2
                        if (token.equals("b")) {
                            operation.leftWire.setValue(16076);
                        }

                        if (null == operation.operator) {
                            operation.operator = "=";
                        }

                        continue;
                    case "NOT":
                        operation.leftWire = new Wire("iconst" + i, 0xFFFF, true);
                        operation.operator = "^";
                        break;
                    case "AND":
                        operation.operator = "&";
                        break;
                    case "OR":
                        operation.operator = "|";
                        break;
                    case "RSHIFT":
                        operation.operator = ">>";
                        break;
                    case "LSHIFT":
                        operation.operator = "<<";
                        break;
                    default:
                        Wire w;
                        if (isInteger(token)) {
                            w = new Wire("ivalue" + i, Integer.parseInt(token), true);
                        } else {
                            w = new Wire(token);

                            if (wires.containsKey(token)) {
                                w = wires.get(token);
                            }
                        }

                        wires.put(token, w);

                        if (null == operation.leftWire) {
                            operation.leftWire = w;
                        } else {
                            operation.rightWire = w;
                        }
                }
            }

            operations.add(operation);

            i++;
        }

        // Run the circuit
        int nbOperations = operations.size();
        int emittedOperations = 0;

        while (emittedOperations < nbOperations) {
            for (Operation operation : operations) {
                if (operation.isEmitted) {
                    continue;
                }

                if (!operation.leftWire.isInitialized()) {
                    continue;
                }

                if (null != operation.rightWire && !operation.rightWire.isInitialized()) {
                    continue;
                }

                if (!operation.operator.equals("=")) {
                    Objects.requireNonNull(operation.rightWire);
                }

                switch (operation.operator) {
                    case "=":
                        operation.destination.setValue(operation.leftWire.getValue());
                        break;
                    case "&":
                        operation.destination.setValue(operation.leftWire.getValue() & operation.rightWire.getValue());
                        break;
                    case "|":
                        operation.destination.setValue(operation.leftWire.getValue() | operation.rightWire.getValue());
                        break;
                    case "<<":
                        operation.destination.setValue(operation.leftWire.getValue() << operation.rightWire.getValue());
                        break;
                    case ">>":
                        operation.destination.setValue(operation.leftWire.getValue() >> operation.rightWire.getValue());
                        break;
                    case "^":
                        operation.destination.setValue(operation.leftWire.getValue() ^ operation.rightWire.getValue());
                        break;
                }

                operation.isEmitted = true;
            }

            emittedOperations++;
        }

        System.out.println("Answer 1 : " + wires.get("a").getValue());
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
}
