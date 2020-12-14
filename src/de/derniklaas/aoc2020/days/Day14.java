package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.*;

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
        long start = System.currentTimeMillis();
        Map<Integer, Long> memory = parseA();
        long sum = 0;
        for (long value : memory.values()) {
            sum += value;
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 14/A] " + sum);
        if (Main.debug) {
            System.out.println("[Day 14/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        Map<String, Integer> memory = parseB();
        long sum = 0;
        for (int value : memory.values()) {
            sum += value;
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 14/B] " + sum);
        if (Main.debug) {
            System.out.println("[Day 14/B] Time: " + (end - start) + " ms");
        }
    }

    private Map<Integer, Long> parseA() {
        Map<Integer, Long> memory = new HashMap<>();
        String mask = "";
        for (String s : this.input) {
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
                memory.remove(position);
                memory.put(position, Long.parseUnsignedLong(replacement, 2));
            }
        }
        return memory;
    }

    private Map<String, Integer> parseB() {
        Map<String, Integer> memory = new HashMap<>();
        String mask = "";
        for (String s : this.input) {
            if (s.startsWith("mask")) {
                mask = s.split(" ")[2];
            } else if (s.startsWith("mem")) {
                String pos = s.split("\\[")[1].split("]")[0];
                int position = Integer.parseInt(pos);
                int number = Integer.parseInt(s.split(" ")[2]);
                String binNumber = Integer.toBinaryString(position);
                while (binNumber.length() != 36) {
                    binNumber = "0" + binNumber;
                }
                List<String> addresses = getAddresses(binNumber, mask, 0);
                for (String address : addresses) {
                    memory.remove(address);
                    memory.put(address, number);
                }

            }
        }
        return memory;
    }

    private List<String> getAddresses(String binAddress, String binMask, int position) {
        if (position == binAddress.length()) return Collections.singletonList(binAddress);
        if (binMask.charAt(position) == '0') return getAddresses(binAddress, binMask, position + 1);
        else if (binMask.charAt(position) == '1') {
            String changedBinAddress = "";
            for (int i = 0; i < binAddress.length(); i++) {
                if (i == position) changedBinAddress += "1";
                else changedBinAddress += binAddress.charAt(i);
            }
            return getAddresses(changedBinAddress, binMask, position + 1);
        } else if (binMask.charAt(position) == 'X') {
            String changedBinAddress0 = "";
            String changedBinAddress1 = "";
            for (int i = 0; i < binAddress.length(); i++) {
                if (i == position) {
                    changedBinAddress0 += "0";
                    changedBinAddress1 += "1";
                } else {
                    changedBinAddress0 += binAddress.charAt(i);
                    changedBinAddress1 += binAddress.charAt(i);
                }
            }
            List<String> part0 = getAddresses(changedBinAddress0, binMask, position + 1);
            List<String> part1 = getAddresses(changedBinAddress1, binMask, position + 1);
            List<String> output = new ArrayList<>();
            output.addAll(part0);
            output.addAll(part1);
            return output;
        }
        throw new IllegalStateException("this shouldn't happen");
    }
}
