package de.derniklaas.aoc2020.days;

public class Day2 {

    private final String[] inputs;

    public Day2(String[] input) {
        this.inputs = input;
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        int valid = 0;
        for (String input : inputs) {
            String[] parts = input.split(" ");
            String amount = parts[0];
            int min = Integer.parseInt(amount.split("-")[0]);
            int max = Integer.parseInt(amount.split("-")[1]);
            char wantedChar = parts[1].charAt(0);
            String password = parts[2];

            int count = countChars(password, wantedChar);
            if (count >= min && count <= max) {
                valid++;
            }
        }

        System.out.println("[Day 2/A] " + valid);
    }

    private void printB() {
        int valid = 0;
        for (String input : inputs) {
            String[] parts = input.split(" ");
            String amount = parts[0];
            int firstPos = Integer.parseInt(amount.split("-")[0]);
            int secondPos = Integer.parseInt(amount.split("-")[1]);
            char wantedChar = parts[1].charAt(0);
            String password = parts[2];
            if (password.charAt(firstPos - 1) == wantedChar && password.charAt(secondPos - 1) != wantedChar) {
                valid++;
            } else if (password.charAt(firstPos - 1) != wantedChar && password.charAt(secondPos - 1) == wantedChar) {
                valid++;
            }
        }

        System.out.println("[Day 2/B] " + valid);
    }

    private int countChars(String input, char wanted) {
        int chars = 0;
        if (!input.contains(wanted + "")) return 0;
        for (char c : input.toCharArray()) {
            if (c == wanted) chars++;
        }
        return chars;
    }
}
