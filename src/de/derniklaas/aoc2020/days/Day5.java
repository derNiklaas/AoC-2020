package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day5 {

    private final List<BoardingPass> boardingPasses = new ArrayList<>();

    public Day5(String[] input) {
        long start = System.currentTimeMillis();
        for (String s : input) {
            int min_row = 0, max_row = 127, min_column = 0, max_column = 7;
            for (char c : s.toCharArray()) {
                if (c == 'F') {
                    max_row = (int) Math.floor((double) (min_row + max_row) / 2f);
                } else if (c == 'B') {
                    min_row = (int) Math.ceil((double) (min_row + max_row) / 2f);
                } else if (c == 'R') {
                    min_column = (int) Math.ceil((double) (min_column + max_column) / 2f);
                } else if (c == 'L') {
                    max_column = (int) Math.floor((double) (min_column + max_column) / 2f);
                }
            }
            if (max_row != min_row || min_column != max_column) {
                System.out.println("Something went wrong?");
            }
            BoardingPass pass = new BoardingPass(max_row, min_column);
            boardingPasses.add(pass);
        }
        long end = System.currentTimeMillis();
        if(Main.debug) {
            System.out.println("[Day 5/Constructor] Time: " + (end - start) + " ms");
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        int highest = 0;
        for (BoardingPass pass : boardingPasses) {
            if (pass.getPassID() > highest) highest = pass.getPassID();
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 5/A] " + highest);
        if (Main.debug) {
            System.out.println("[Day 5/B] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        Set<Integer> ids = new HashSet<>();

        for (BoardingPass pass : boardingPasses) {
            ids.add(pass.getPassID());
        }

        int lastDigit = -1;
        for (int id : ids) {
            if (!ids.contains(id + 1)) {
                lastDigit = id + 1;
            } else if (!ids.contains(id - 1)) {
                if (lastDigit == id - 1) {
                    long end = System.currentTimeMillis();
                    System.out.println("[Day 5/B] " + (id - 1));
                    if (Main.debug) {
                        System.out.println("[Day 5/B] Time: " + (end - start) + " ms");
                    }
                }
                lastDigit = id - 1;
            }
        }
    }

    private static class BoardingPass {

        private final int row;
        private final int column;

        public BoardingPass(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getPassID() {
            return row * 8 + column;
        }
    }
}
