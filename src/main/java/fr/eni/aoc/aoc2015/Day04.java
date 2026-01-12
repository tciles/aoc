package fr.eni.aoc.aoc2015;

import fr.eni.aoc.BaseDay;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Day04 extends BaseDay {

    public Day04() {
        day = "04";
    }

    @Override
    protected void answerOne() throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");

        String result = "";
        int resultInt = 0;

        for (int i = 0; i < 1000000; i++) {
            byte[] hash = ("ckczppom" + i).getBytes(StandardCharsets.UTF_8);
            byte[] messageDigest = md.digest(hash);
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(no.toString(16));

            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }

            if (hashtext.substring(0, 5).equals("00000")) {
                result = hashtext.toString();
                resultInt = i;
                break;
            }
        }

        System.out.println(result + " : " +  resultInt);
    }

    @Override
    protected void answerTwo() throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");

        String result = "";
        int resultInt = 0;

        for (int j = 0; j < 6; j++) {
            int start = j * 5_000_000;
            int end = start + 5_000_000;

            System.out.println(start + " : " + end);

            for (int i = start; i < end; i++) {
                byte[] hash = ("ckczppom" + i).getBytes(StandardCharsets.UTF_8);
                byte[] messageDigest = md.digest(hash);
                BigInteger no = new BigInteger(1, messageDigest);
                StringBuilder hashtext = new StringBuilder(no.toString(16));

                while (hashtext.length() < 32) {
                    hashtext.insert(0, "0");
                }

                if (hashtext.substring(0, 6).equals("000000")) {
                    result = hashtext.toString();
                    resultInt = i;
                    break;
                }
            }
        }

        System.out.println(result + " : " +  resultInt);
    }
}
