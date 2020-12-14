package de.derniklaas.aoc2020.days;

public class Day14 {

    private final String[] input;

    public Day14(String[] input) {
        this.input = input;
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        String[] memory = parseA(input);
        long sum = 0;
        for (String mem : memory) {
            if (mem == null) continue;
            sum += Long.parseUnsignedLong(mem, 2);
        }
        System.out.println("[Day 14/A] " + sum);
    }

    private void printB() {

    }

    private String[] parseA(String[] input) {
        String[] memory = new String[100000];
        String mask = "";
        for (String s : input) {
            if (s.startsWith("mask")) {
                mask = s.split(" ")[2];
            } else if (s.startsWith("mem")) {
                String pos = s.split("\\[")[1].split("]")[0];
                int position = Integer.parseInt(pos);
                int number = Integer.parseInt(s.split(" ")[2]);
                String binNumber = Integer.toBinaryString(number);
                while (binNumber.length() != 36) {
                    binNumber = "0" + binNumber;
                }
                String replacement = "";
                for (int i = 0; i < mask.length(); i++) {
                    if (mask.charAt(i) == 'X') {
                        replacement += binNumber.charAt(i);
                    } else {
                        replacement += mask.charAt(i);
                    }
                }
                memory[position] = replacement;
            }
        }
        return memory;
    }
}
