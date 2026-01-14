package fr.eni.aoc.aoc2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    @Test
    public void testReplaceHexadecimalCharacters() {
        Day08 d = new Day08();

        String result = d.replaceHexadecimalCharacters("\\x27");
        assertEquals("'", result);

        result = d.replaceHexadecimalCharacters("\\x2");
        assertEquals("\\x2", result);

        result = d.replaceHexadecimalCharacters("\\x27\\x27");
        assertEquals("''", result);

        result = d.replaceHexadecimalCharacters("\\x2z\\x27");
        assertEquals("\\x2z'", result);

        result = d.replaceHexadecimalCharacters("\\\\x27");
        assertEquals("\\'", result);

        result = d.replaceHexadecimalCharacters("aaa\\\"aaa");
        assertEquals("aaa\\\"aaa", result);
    }

}
