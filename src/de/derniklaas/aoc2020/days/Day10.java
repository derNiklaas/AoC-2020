package de.derniklaas.aoc2020.days;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {

    private final List<Integer> input;

    public Day10(String[] input) {
        this.input = new ArrayList<>();
        for (String s : input) {
            this.input.add(Integer.parseInt(s));
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        int oneDiff = 0;
        int threeDiff = 1;

        List<Integer> ratings = input.stream().sorted().collect(Collectors.toList());

        int prev = 0;
        for (int i : ratings) {
            if (i - 1 == prev) oneDiff++;
            else if (i - 3 == prev) threeDiff++;
            prev = i;
        }

        System.out.println("[Day 10/A] " + (oneDiff * threeDiff));

    }

    private void printB() {

    }
}

