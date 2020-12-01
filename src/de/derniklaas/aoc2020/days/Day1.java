package de.derniklaas.aoc2020.days;

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
        for (int a : input) {
            if (input.contains(2020 - a)) {
                System.out.println("[Day 1/A] " + a * (2020 - a));
                return;
            }
        }
    }

    private void printB() {
        for (int a : input) {
            for (int b : input) {
                if (input.contains(2020 - a - b)) {
                    System.out.println("[Day 1/B] " + a * b * (2020 - a - b));
                    return;
                }
            }
        }
    }
}
