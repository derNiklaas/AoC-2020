package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.HashMap;
import java.util.Map;

public class Day15 {

    private Map<Integer, Integer> spoken = new HashMap<>();
    private int turn = 1;
    private int prevNumber = -1;

    public Day15(String[] input) {
        for (String s : input[0].split(",")) {
            int number = Integer.parseInt(s);
            if (prevNumber != -1) spoken.put(prevNumber, turn);
            prevNumber = number;
            turn++;
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        while (turn != 2021) {
            int nextNumber = 0;
            if (spoken.containsKey(prevNumber)) nextNumber = turn - spoken.get(prevNumber);
            spoken.put(prevNumber, turn);
            prevNumber = nextNumber;
            turn++;
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 15/A] " + prevNumber);
        if (Main.debug) {
            System.out.println("[Day 15/A] Time: " + (end - start) + "ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        while (turn != 30000001) {
            int nextNumber = 0;
            if (spoken.containsKey(prevNumber)) nextNumber = turn - spoken.get(prevNumber);
            spoken.put(prevNumber, turn);
            prevNumber = nextNumber;
            turn++;
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 15/B] " + prevNumber);
        if (Main.debug) {
            System.out.println("[Day 15/B] Time: " + (end - start) + "ms");
        }
    }

}
