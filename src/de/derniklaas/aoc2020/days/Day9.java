package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.Arrays;

public class Day9 {

    private final long[] input;
    private int part1Index = 25;

    public Day9(String[] input) {
        this.input = new long[input.length];
        for (int i = 0; i < input.length; i++) {
            this.input[i] = Long.parseLong(input[i]);
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        for (int i = 25; i < input.length; i++) {
            boolean found = false;
            for (int j = i - 25; j < i; j++) {
                for (int k = i - 25; k < i; k++) {
                    if (input[j] != input[k]) {
                        if (input[j] + input[k] == input[i]) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) break;
            }
            if (!found) {
                long stop = System.currentTimeMillis();
                System.out.println("[Day 9/A] " + input[i]);
                if (Main.debug) {
                    System.out.println("[Day 9/A] Time: " + (stop - start) + " ms");
                }
                part1Index = i;
                break;
            }
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        long sumTo = input[part1Index];
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (Arrays.stream(Arrays.copyOfRange(input, i, j + 1)).sum() == sumTo) {
                    long min = Arrays.stream(Arrays.copyOfRange(input, i, j + 1)).min().orElse(0);
                    long max = Arrays.stream(Arrays.copyOfRange(input, i, j + 1)).max().orElse(0);
                    long stop = System.currentTimeMillis();
                    System.out.println("[Day 9/B] " + (min + max));
                    if (Main.debug) {
                        System.out.println("[Day 9/B] Time: " + (stop - start) + " ms");
                    }
                    return;
                }
            }
        }
    }


}
