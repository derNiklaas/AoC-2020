package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 {

    private final List<Integer> ratings;
    private final Map<Integer, Long> branches;

    public Day10(String[] input) {
        long start = System.currentTimeMillis();
        List<Integer> ratings = new ArrayList<>();
        branches = new HashMap<>();
        for (String s : input) {
            ratings.add(Integer.parseInt(s));
        }
        this.ratings = ratings.stream().sorted().collect(Collectors.toList());
        long end = System.currentTimeMillis();
        if (Main.debug) {
            System.out.println("[Day10/Constructor] Time: " + (end - start) + " ms");
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.nanoTime();
        int oneDiff = 0;
        int threeDiff = 1;

        int prev = 0;
        for (int i : ratings) {
            if (i - 1 == prev) oneDiff++;
            else if (i - 3 == prev) threeDiff++;
            prev = i;
        }

        System.out.println("[Day 10/A] " + (oneDiff * threeDiff));

        long end = System.nanoTime();
        if (Main.debug) {
            System.out.println("[Day 10/A] Time: " + (end - start) + " ns");
        }
    }

    private void printB() {
        long start = System.nanoTime();
        int combo = 0;
        int prev = 0;
        long total = 1L;
        for (int i : ratings) {
            if (i - 1 == prev) combo++;
            else if (combo != 0) {
                total *= getAmount(combo);
                combo = 0;
            }
            prev = i;
        }
        if (combo != 0) total *= getAmount(combo);
        System.out.println("[Day 10/B] " + total);
        long end = System.nanoTime();
        if (Main.debug) {
            System.out.println("[Day 10/B] Time: " + (end - start) + " ns");
        }
    }

    private long getAmount(int depth) {
        if (!branches.containsKey(depth)) branches.put(depth, count(0, depth));
        return branches.get(depth);
    }

    private long count(int depth, int endDepth) {
        if (depth == endDepth) return 1L;
        if (depth > endDepth) return 0L;
        long count = 0;
        for (int i = 1; i <= 3; i++) count += count(depth + i, endDepth);
        return count;
    }
}

