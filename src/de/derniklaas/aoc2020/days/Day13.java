package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    private final int earliestTime;
    private final List<Bus> buses = new ArrayList<>();

    public Day13(String[] input) {
        earliestTime = Integer.parseInt(input[0]);
        int offset = -1;
        for (String part : input[1].split(",")) {
            offset++;
            if (part.equalsIgnoreCase("x")) continue;
            buses.add(new Bus(Integer.parseInt(part), offset));
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        int minDiff = 100000;
        int minID = 0;
        for (Bus bus : buses) {
            int timestamp = 0;
            while (timestamp < earliestTime) {
                timestamp += bus.getId();
            }
            if (minDiff > (timestamp - earliestTime)) {
                minDiff = timestamp - earliestTime;
                minID = bus.getId();
            }
        }
        int out = minDiff * minID;
        long end = System.currentTimeMillis();
        System.out.println("[Day 13/A] " + out);
        if (Main.debug) {
            System.out.println("[Day 13/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        long product = 1;
        for (Bus bus : buses) {
            product *= bus.getId();
        }
        long sum = 0;
        for (Bus bus : buses) {
            sum += bus.getOffset() * (product / bus.getId()) * inverseModulo(product / bus.getId(), bus.getId());
        }

        long output = product - sum % product;

        long end = System.currentTimeMillis();
        System.out.println("[Day 13/B] " + output);
        if (Main.debug) {
            System.out.println("[Day 13/B] Time: " + (end - start) + " ms");
        }
    }

    private long inverseModulo(long a, long b) {
        if (a != 0) {
            long modulo = b % a;
            return modulo == 0 ? 1 : b - inverseModulo(modulo, a) * b / a;
        }
        return 0;
    }

    private static class Bus {
        private final int offset;
        private final int id;

        public Bus(int id, int offset) {
            this.id = id;
            this.offset = offset;
        }

        public int getId() {
            return id;
        }

        public int getOffset() {
            return offset;
        }
    }
}
