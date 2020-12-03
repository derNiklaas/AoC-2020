package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

public class Day3 {

    private final boolean[][] maze;

    public Day3(String[] input) {
        maze = new boolean[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                maze[i][j] = input[i].charAt(j) == '#';
            }
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    // maze[höhe][breite]
    private void printA() {
        long start = System.nanoTime();
        long output = getHitTrees(3, 1);
        long end = System.nanoTime();
        System.out.println("[Day 3/A] " + output);
        if (Main.debug) {
            System.out.println("[Day 3/A] Time: " + (end - start) + "ns");
        }
    }

    private void printB() {
        long start = System.nanoTime();
        long output = getHitTrees(1, 1) * getHitTrees(3, 1) * getHitTrees(5, 1) * getHitTrees(7, 1) * getHitTrees(1, 2);
        long end = System.nanoTime();

        System.out.println("[Day 3/B] " + output);
        if (Main.debug) {
            System.out.println("[Day 3/B] Time: " + (end - start) + "ns");
        }
    }

    private long getHitTrees(int right, int down) {
        long hit = 0;
        int rightPos = 0;
        for (int i = down; i < maze.length; i += down) {
            rightPos = (rightPos + right) % maze[0].length;
            if (maze[i][rightPos]) hit++;
        }
        return hit;
    }
}
