package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day17 {

    private final Set<Pos4D> initialState;

    public Day17(String[] input) {
        initialState = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                Pos4D position = new Pos4D(i, j, 0, 0);
                if (input[i].charAt(j) == '#') initialState.add(position);
            }
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        int output = runSixSteps(false);
        long end = System.currentTimeMillis();
        System.out.println("[Day 17/A] " + output);
        if (Main.debug) {
            System.out.println("[Day 17/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        int output = runSixSteps(true);
        long end = System.currentTimeMillis();
        System.out.println("[Day 17/B] " + output);
        if (Main.debug) {
            System.out.println("[Day 17/A] Time: " + (end - start) + " ms");
        }
    }

    private int runSixSteps(boolean useForthDimension) {
        Set<Pos4D> clone = new HashSet<>(initialState);
        for (int i = 0; i < 6; i++) {
            clone = simulateStep(clone, useForthDimension);
        }

        return clone.size();
    }

    private Set<Pos4D> simulateStep(Set<Pos4D> currentStep, boolean useForthDimension) {
        Set<Pos4D> nextStep = new HashSet<>();
        Set<Pos4D> missingPositions = new HashSet<>();
        for (Pos4D activePosition : currentStep) {
            int near = getActiveNeighbors(currentStep, activePosition, missingPositions, useForthDimension);
            if (near == 2 || near == 3) {
                nextStep.add(activePosition);
            }
        }
        for (Pos4D inactivePosition : missingPositions) {
            if (currentStep.contains(inactivePosition)) continue;
            int near = getActiveNeighbors(currentStep, inactivePosition, null, useForthDimension);
            if (near == 3) {
                nextStep.add(inactivePosition);
            }
        }
        return nextStep;
    }

    private int getActiveNeighbors(Set<Pos4D> set, Pos4D position, Set<Pos4D> missingPositions, boolean useForthDimension) {
        int active = 0;
        for (int z = -1; z <= 1; z++) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int w = -1; w <= 1; w++) {
                        if (!useForthDimension) {
                            if (w == -1 || w == 1) continue;
                        }
                        if (x == 0 && y == 0 && z == 0 && w == 0) continue;
                        Pos4D pos = position.getRelativePosition(x, y, z, w);
                        if (set.contains(pos)) active++;
                        if (missingPositions != null) missingPositions.add(pos);
                    }
                }
            }
        }
        return active;
    }

    private static class Pos4D {
        private final int x;
        private final int y;
        private final int z;

        private final int w;

        public Pos4D(int x, int y, int z, int w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }

        public Pos4D getRelativePosition(int xOffset, int yOffset, int zOffset, int wOffset) {
            return new Pos4D(x + xOffset, y + yOffset, z + zOffset, w + wOffset);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos4D cube = (Pos4D) o;
            return x == cube.x && y == cube.y && z == cube.z && w == cube.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, w);
        }
    }
}
