package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

public class Day11 {

    private final char[][] map;

    public Day11(String[] input) {
        map = new char[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                map[i][j] = input[i].charAt(j);
            }
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        char[][] map = copyArray(this.map);
        char[][] prevMap = new char[map.length][map[0].length];
        while (!isSame(map, prevMap)) {
            prevMap = map;
            map = simulateStepA(map);
        }
        int seats = countSeats(map);
        long end = System.currentTimeMillis();
        System.out.println("[Day 11/A] " + seats);
        if (Main.debug) {
            System.out.println("[Day 11/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        char[][] map = copyArray(this.map);
        char[][] prevMap = new char[map.length][map[0].length];
        while (!isSame(map, prevMap)) {
            prevMap = map;
            map = simulateStepB(map);
        }
        int seats = countSeats(map);
        long end = System.currentTimeMillis();
        System.out.println("[Day 11/B] " + seats);
        if (Main.debug) {
            System.out.println("[Day 11/B] Time: " + (end - start) + " ms");
        }
    }

    private int countSeats(char[][] map) {
        int seats = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '#') seats++;
            }
        }
        return seats;
    }

    private boolean isSame(char[][] a, char[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    private char[][] copyArray(char[][] map) {
        char[][] copyMap = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    private char[][] simulateStepA(char[][] map) {
        char[][] step = new char[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '.') {
                    int near = 0;
                    if (i != 0) {
                        if (j != 0) {
                            if (map[i - 1][j - 1] == '#') near++;
                        }
                        if (map[i - 1][j] == '#') near++;
                        if (j != map[0].length - 1) {
                            if (map[i - 1][j + 1] == '#') near++;
                        }
                    }
                    if (j != 0) {
                        if (map[i][j - 1] == '#') near++;
                    }
                    if (j != map[0].length - 1) {
                        if (map[i][j + 1] == '#') near++;
                    }
                    if (i + 1 != map.length) {
                        if (j != 0) {
                            if (map[i + 1][j - 1] == '#') near++;
                        }
                        if (map[i + 1][j] == '#') near++;
                        if (j != map[0].length - 1) {
                            if (map[i + 1][j + 1] == '#') near++;
                        }
                    }
                    if (map[i][j] == '#' && near >= 4) {
                        step[i][j] = 'L';
                    } else if (map[i][j] == 'L' && near == 0) {
                        step[i][j] = '#';
                    } else {
                        step[i][j] = map[i][j];
                    }
                } else {
                    step[i][j] = '.';
                }
            }
        }
        return step;
    }

    private char[][] simulateStepB(char[][] map) {
        char[][] step = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '.') {
                    step[i][j] = '.';
                    continue;
                }
                int hits = 0;
                if (hit(map, i, j, -1, 1)) hits++;
                if (hit(map, i, j, -1, 0)) hits++;
                if (hit(map, i, j, -1, -1)) hits++;
                if (hit(map, i, j, 0, 1)) hits++;
                if (hit(map, i, j, 0, -1)) hits++;
                if (hit(map, i, j, 1, 1)) hits++;
                if (hit(map, i, j, 1, 0)) hits++;
                if (hit(map, i, j, 1, -1)) hits++;
                if (map[i][j] == '#' && hits >= 5) {
                    step[i][j] = 'L';
                } else if (map[i][j] == 'L' && hits == 0) {
                    step[i][j] = '#';
                } else {
                    step[i][j] = map[i][j];
                }
            }
        }
        return step;
    }

    private boolean hit(char[][] map, int x, int y, int changeX, int changeY) {
        try {
            while (true) {
                x += changeX;
                y += changeY;
                if (map[x][y] == '#') return true;
                if (map[x][y] == 'L') return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
}
