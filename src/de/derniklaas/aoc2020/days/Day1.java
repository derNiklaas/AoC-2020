package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.HashSet;

public class Day1 {

    private final HashSet<Integer> input;

    public Day1(String[] input) {
        this.input = new HashSet<>();
        for (String part : input) {
            part = part.trim();
            this.input.add(Integer.parseInt(part));
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.nanoTime();
        for (int a : input) {
            if (input.contains(2020 - a)) {
                long end = System.nanoTime();
                System.out.println("[Day 1/A] " + a * (2020 - a));
                if (Main.debug) {
                    System.out.println("[Day 1/A] Time: " + (end - start) + " ns");
                }
                return;
            }
        }
    }

    private void printB() {
        long start = System.nanoTime();
        for (int a : input) {
            for (int b : input) {
                if (input.contains(2020 - a - b)) {
                    long end = System.nanoTime();
                    System.out.println("[Day 1/B] " + a * b * (2020 - a - b));
                    if (Main.debug) {
                        System.out.println("[Day 1/B] Time: " + (end - start) + " ns");
                    }
                    return;
                }
            }
        }
    }
}
