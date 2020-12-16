package de.derniklaas.aoc2020;

import de.derniklaas.aoc2020.days.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static boolean debug = false;

    public static void main(String[] args) {
        int day = 16;
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
        boolean timeAll = false;
        if (timeAll) {
            long start = System.currentTimeMillis();
            for (int i = 1; i < 16; i++) {
                loadDay(i, loadInputFile(i));
            }
            long end = System.currentTimeMillis();
            System.out.println("[All days] " + (end - start) + " ms");
        }
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
            case 3: {
                new Day3(content).printAnswers();
                break;
            }
            case 4: {
                new Day4(content).printAnswers();
                break;
            }
            case 5: {
                new Day5(content).printAnswers();
                break;
            }
            case 6: {
                new Day6(content).printAnswers();
                break;
            }
            case 7: {
                new Day7(content).printAnswers();
                break;
            }
            case 8: {
                new Day8(content).printAnswers();
                break;
            }
            case 9: {
                new Day9(content).printAnswers();
                break;
            }
            case 10: {
                new Day10(content).printAnswers();
                break;
            }
            case 11: {
                new Day11(content).printAnswers();
                break;
            }
            case 12: {
                new Day12(content).printAnswers();
                break;
            }
            case 13: {
                new Day13(content).printAnswers();
                break;
            }
            case 14: {
                new Day14(content).printAnswers();
                break;
            }
            case 15: {
                new Day15(content).printAnswers();
                break;
            }
            case 16: {
                new Day16(content).printAnswers();
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
