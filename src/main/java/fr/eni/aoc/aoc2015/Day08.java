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

        int code = 0;
        int memory = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            int len = line.length();

            code += len;

            // On ne traite pas les guillemets
            for(int i = 1; i < (len - 1); i++) {
                char ch = line.charAt(i);

                if (ch == '\\') {
                    char next = line.charAt(i + 1);

                    if (next == '\\' || next == '\"') {
                        memory++;
                        i += 1;
                    } else if (next == 'x') {
                        memory++;
                        i += 3;
                    }

                } else {
                    memory++;
                }
            }
        }

        System.out.println("Answer 1 : " + (code - memory));
    }

    @Override
    protected void answerTwo() throws Exception {
        BufferedReader reader = getInputContent();

        int code = 0;
        int totalCode = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            totalCode += line.length();
            int escape = escape(line).length() + 2;

            code += escape;
        }

        System.out.println("Answer 2 : " + (code - totalCode));
    }

    public static String escape(String s){
        return s.replace("\\", "\\\\")
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\f", "\\f")
                .replace("\'", "\\'")      // <== not necessary
                .replace("\"", "\\\"");
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
