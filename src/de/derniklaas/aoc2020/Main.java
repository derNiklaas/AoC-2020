package de.derniklaas.aoc2020;

import de.derniklaas.aoc2020.days.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        int day = 2;
        if (args.length == 1) {
            try {
                day = Integer.parseInt(args[0]);
            } catch (Exception exception) {
                System.err.println("You must enter an integer.");
                System.exit(-1);
            }
        }
        String[] content = loadInputFile(day);
        loadDay(day, content);
    }

    private static void loadDay(int day, String[] content) {
        switch (day) {
            case 1: {
                new Day1(content).printAnswers();
                break;
            }
            case 2: {
                new Day2(content).printAnswers();
                break;
            }
            default: {
                System.err.println("This day (" + day + ") hasn't been done yet.");
                System.exit(-3);
            }
        }
    }

    private static String[] loadInputFile(int day) {
        String content = "";
        try {
            InputStream stream = Main.class.getResourceAsStream("../../../inputs/day" + day + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line;

            while ((line = reader.readLine()) != null) {
                content = content + line + "\n";
            }
            content = content.trim();
            stream.close();
        } catch (Exception exception) {
            System.err.println("Could not read file: inputs/day" + day + ".txt");
            System.exit(-2);
        }
        return content.split("\n");
    }
}
