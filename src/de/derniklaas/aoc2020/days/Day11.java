package de.derniklaas.aoc2020.days;

public class Day11 {

    private char[][] map;

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
        char[][] prevMap = new char[map.length][map[0].length];
        int steps = 0;
        while (!isSame(map, prevMap)) {
            prevMap = map;
            map = simulateStepA(map);
            steps++;
        }
        int seats = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '#') seats++;
            }
        }
        System.out.println("[Day 11/A] " + seats);
    }

    private void printB() {

    }

    private void print2DArray(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            String line = "";
            for (char c : map[i]) {
                line += c;
            }
            System.out.println(line);
        }
    }


    private boolean isSame(char[][] a, char[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
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
}
