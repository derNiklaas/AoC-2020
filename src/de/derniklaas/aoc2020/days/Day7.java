package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.*;

public class Day7 {

    private List<Trade> trades = new ArrayList<>();

    public Day7(String[] input) {
        long start = System.currentTimeMillis();
        for (String s : input) {
            if (!s.contains("no other")) {
                String[] parts = s.split("contain");
                String holdingBag = parts[0].replace("bags", "").trim();
                Bag bag = new Bag(holdingBag, 1);
                List<Bag> results = new ArrayList();
                if (parts[1].contains(",")) {
                    String[] resultingBags = parts[1].split(",");
                    for (String resultingBag : resultingBags) {
                        resultingBag = resultingBag.trim();
                        int amount = Integer.parseInt(resultingBag.split(" ")[0]);
                        String name = resultingBag.replace(amount + "", "").replace("bags", "").replace("bag", "").replace(".", "").trim();
                        results.add(new Bag(name, amount));
                    }
                } else {
                    String resultingBag = parts[1].trim();
                    int amount = Integer.parseInt(resultingBag.split(" ")[0]);
                    String name = resultingBag.replace(amount + "", "").replace("bags", "").replace("bag", "").replace(".", "").trim();
                    results.add(new Bag(name, amount));
                }
                trades.add(new Trade(bag, results));
            }
        }
        long end = System.currentTimeMillis();
        if (Main.debug) {
            System.out.println("[Day 7/Constructor] Time: " + (end - start) + " ms");
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        int size = findBag(new Bag("shiny gold", 1), new HashSet<>()).size() - 1;
        long end = System.currentTimeMillis();
        System.out.println("[Day 7/A] " + size);
        if (Main.debug) {
            System.out.println("[Day 7/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        List<Bag> left = new ArrayList<>();
        left.add(new Bag("shiny gold", 1));
        int amount = findAmountOfBags(left) - 1;
        long end = System.currentTimeMillis();
        System.out.println("[Day 7/B] " + amount);
        if (Main.debug) {
            System.out.println("[Day 7/B] Time: " + (end - start) + " ms");
        }
    }

    private Set<String> findBag(Bag item, Set<String> visitedColors) {
        visitedColors.add(item.getName());
        List<Trade> trades = getTrades(item);
        for (Trade trade : trades) {
            findBag(trade.getIn(), visitedColors);
        }
        return visitedColors;
    }

    private List<Trade> getTrades(Bag bag) {
        List<Trade> trades = new ArrayList<>();

        for (Trade trade : this.trades) {
            for (Bag out : trade.getOut()) {
                if (out.getName().equalsIgnoreCase(bag.getName())) {
                    trades.add(trade);
                    break;
                }
            }
        }

        return trades;
    }

    private Trade getTrade(Bag bag) {
        for (Trade trade : trades) {
            if (trade.getIn().getName().equalsIgnoreCase(bag.getName())) {
                return trade;
            }
        }
        return null;
    }

    private int findAmountOfBags(List<Bag> bags) {
        int amount = 0;
        while (!bags.isEmpty()) {
            Bag bag = bags.remove(0);
            amount += findBagsInside(bag, bags);
        }
        return amount;
    }

    private int findBagsInside(Bag wanted, List<Bag> bags) {
        Trade trade = getTrade(wanted);
        if (trade == null) return wanted.getAmount();
        for (int i = 0; i < wanted.getAmount(); i++) {
            bags.addAll(trade.getOut());
        }
        return wanted.getAmount();
    }

    private static class Bag {
        private final String name;
        private final int amount;

        public Bag(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bag bag = (Bag) o;
            return Objects.equals(name, bag.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static class Trade {
        private final Bag in;
        private final List<Bag> out;

        public Trade(Bag in, List<Bag> output) {
            this.in = in;
            this.out = output;
        }

        public Bag getIn() {
            return in;
        }

        public List<Bag> getOut() {
            return out;
        }
    }
}
