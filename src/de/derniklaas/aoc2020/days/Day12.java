package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

public class Day12 {

    private final String[] input;

    public Day12(String[] input) {
        this.input = input;
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        int direction = 90;
        int x = 0;
        int y = 0;
        for (String part : input) {
            char operation = part.charAt(0);
            int amount = Integer.parseInt(part.substring(1));
            switch (operation) {
                case 'N': {
                    y += amount;
                    break;
                }
                case 'S': {
                    y -= amount;
                    break;
                }
                case 'E': {
                    x += amount;
                    break;
                }
                case 'W': {
                    x -= amount;
                    break;
                }
                case 'L': {
                    direction -= amount;
                    while (direction < 0) {
                        direction += 360;
                    }
                    break;
                }
                case 'R': {
                    direction += amount;
                    while (direction >= 360) {
                        direction -= 360;
                    }
                    break;
                }
                case 'F': {
                    if (direction == 0) {
                        y += amount;
                    } else if (direction == 90) {
                        x += amount;
                    } else if (direction == 180) {
                        y -= amount;
                    } else if (direction == 270) {
                        x -= amount;
                    }
                    break;
                }
            }
        }
        int sum = Math.abs(x) + Math.abs(y);
        long end = System.currentTimeMillis();
        System.out.println("[Day 12/A] " + sum);
        if (Main.debug) {
            System.out.println("[Day 12/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();

        int waypointY = 1;
        int waypointX = 10;
        int direction = 90;
        int x = 0;
        int y = 0;
        for (String part : input) {
            char operation = part.charAt(0);
            int amount = Integer.parseInt(part.substring(1));
            switch (operation) {
                case 'N': {
                    waypointY += amount;
                    break;
                }
                case 'S': {
                    waypointY -= amount;
                    break;
                }
                case 'E': {
                    waypointX += amount;
                    break;
                }
                case 'W': {
                    waypointX -= amount;
                    break;
                }
                case 'L': {
                    int oldDirection = direction;
                    direction -= amount;
                    while (direction < 0) {
                        direction += 360;
                    }
                    int tempX = calcWaypointDirectionX(waypointX, waypointY, direction, oldDirection);
                    int tempY = calcWaypointDirectionY(waypointX, waypointY, direction, oldDirection);

                    waypointX = tempX;
                    waypointY = tempY;
                    break;
                }
                case 'R': {
                    int oldDirection = direction;
                    direction += amount;
                    while (direction >= 360) {
                        direction -= 360;
                    }
                    int tempX = calcWaypointDirectionX(waypointX, waypointY, direction, oldDirection);
                    int tempY = calcWaypointDirectionY(waypointX, waypointY, direction, oldDirection);

                    waypointX = tempX;
                    waypointY = tempY;
                    break;
                }
                case 'F': {
                    x += amount * waypointX;
                    y += amount * waypointY;
                    break;
                }
            }
        }
        int sum = Math.abs(x) + Math.abs(y);
        long end = System.currentTimeMillis();
        System.out.println("[Day 12/B] " + sum);
        if (Main.debug) {
            System.out.println("[Day 12/A] Time: " + (end - start) + " ms");
        }
    }

    private int calcWaypointDirectionX(int waypointX, int waypointY, int direction, int oldDirection) {
        switch (direction) {
            case 0: {
                if (oldDirection == 0) return waypointX;
                if (oldDirection == 180) return -waypointX;
                if (oldDirection == 90) return -waypointY;
                return waypointY;
            }
            case 90: {
                if (oldDirection == 0) return waypointY;
                if (oldDirection == 90) return waypointX;
                if (oldDirection == 270) return -waypointX;
                return -waypointY;
            }
            case 180: {
                if (oldDirection == 0) return -waypointX;
                if (oldDirection == 180) return waypointX;
                if (oldDirection == 90) return waypointY;
                return -waypointY;
            }
            case 270: {
                if (oldDirection == 0) return -waypointY;
                if (oldDirection == 90) return -waypointX;
                if (oldDirection == 270) return waypointX;
                return waypointY;
            }
        }
        throw new IllegalStateException("Something went wrong.");
    }


    private int calcWaypointDirectionY(int waypointX, int waypointY, int direction, int oldDirection) {
        switch (direction) {
            case 0: {
                if (oldDirection == 0) return waypointY;
                if (oldDirection == 90) return waypointX;
                if (oldDirection == 180) return -waypointY;
                return -waypointX;
            }
            case 180: {
                if (oldDirection == 0) return -waypointY;
                if (oldDirection == 90) return -waypointX;
                if (oldDirection == 180) return waypointY;
                return waypointX;
            }
            case 90: {
                if (oldDirection == 0) return -waypointX;
                if (oldDirection == 90) return waypointY;
                if (oldDirection == 270) return -waypointY;
                return waypointX;
            }
            case 270: {
                if (oldDirection == 0) return waypointX;
                if (oldDirection == 90) return -waypointY;
                if (oldDirection == 270) return waypointY;
                return -waypointX;
            }
        }
        throw new IllegalStateException("Something went wrong.");
    }
}
