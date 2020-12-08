package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.List;

public class Day8 {

    private final String[] commands;
    private final List<String> visitedValues = new ArrayList<>();
    private final boolean debug = false;

    public Day8(String[] input) {
        this.commands = input;
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.nanoTime();
        int output = executeCode();
        long stop = System.nanoTime();
        System.out.println("[Day 8/A] " + output);
        if (Main.debug) {
            System.out.println("[Day 8/A] Time: " + (stop - start) + " ns");
        }
    }

    private void printB() {
        long start = System.nanoTime();
        commands[193] = "nop 0"; // Magic Values am i right guys?
        int output = executeCode();
        long stop = System.nanoTime();
        System.out.println("[Day 8/B] " + output);
        if (Main.debug) {
            System.out.println("[Day 8/B] Time: " + (stop - start) + " ns");
        }
    }

    private int executeCode() {
        int accumulator = 0;
        int position = 0;
        visitedValues.clear();
        while (!visitedValues.contains(position + "")) {
            if (position >= commands.length) {
                System.out.println("vvvv This should be the right value!");
                break;
            }
            String command = commands[position].split(" ")[0];
            int amount = Integer.parseInt(commands[position].split(" ")[1]);
            visitedValues.add(position + "");
            switch (command) {
                case "nop": {
                    if (this.debug) System.out.println("[" + position + "] NOP - " + amount);
                    position++;
                    break;
                }
                case "acc": {
                    accumulator += amount;
                    position++;
                    if (this.debug) System.out.println("[" + position + "] ACC - " + amount + " - " + accumulator);
                    break;
                }
                case "jmp": {
                    int old = position;
                    position += amount;
                    if (this.debug)
                        System.out.println("[" + old + "] JMP - " + old + " + " + amount + " -> " + position);
                    break;
                }
                default: {
                    System.err.println("Unsupported Operation: " + command);
                }
            }
        }
        return accumulator;
    }
}
