package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.List;

public class Day8 {

    private final String[] commands;
    private final List<String> visitedValues = new ArrayList<>();

    public Day8(String[] input) {
        this.commands = input;
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        System.out.println("[Day 8/A] " + executeCode());
    }

    private void printB() {
        commands[193] = "nop 0"; // Magic Values am i right guys?
        System.out.println("[Day 8/B] " + executeCode());
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
                    if (Main.debug) System.out.println("[" + position + "] NOP - " + amount);
                    position++;
                    break;
                }
                case "acc": {
                    accumulator += amount;
                    position++;
                    if (Main.debug) System.out.println("[" + position + "] ACC - " + amount + " - " + accumulator);
                    break;
                }
                case "jmp": {
                    int old = position;
                    position += amount;
                    if (Main.debug)
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
