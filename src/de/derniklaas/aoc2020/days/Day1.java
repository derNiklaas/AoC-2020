package de.derniklaas.aoc2020.days;

import java.util.ArrayList;
import java.util.List;

public class Day1 {

    private final List<Integer> input;

    public Day1(String[] input) {
        this.input = new ArrayList<>();
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
            for (int b : input) {
                if (a + b == 2020) {
                    System.out.println("[Day 1/A] " + a * b);
                    return;
                }
            }
        }
    }

    private void printB() {
        for (int a : input) {
            for (int b : input) {
                for (int c : input) {
                    if (a + b + c == 2020) {
                        System.out.println("[Day 1/B] " + a * b * c);
                        return;
                    }
                }
            }
        }
    }
}
