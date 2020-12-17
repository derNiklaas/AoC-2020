package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 {

    private final Map<String, TicketField> ticketFields;
    private String myTicket;
    private final List<String> nearbyTickets;
    private final List<String> validTickets;
    private final HashMap<Integer, String> ticketParts;

    public Day16(String[] input) {
        int partOfInput = 0;
        ticketFields = new HashMap<>();
        nearbyTickets = new ArrayList<>();
        validTickets = new ArrayList<>();
        ticketParts = new HashMap<>();
        for (String s : input) {
            if (s.trim().equalsIgnoreCase("")) {
                partOfInput++;
            } else if (partOfInput == 0) {
                String name = s.split(":")[0];
                String[] rangeSplits = s.split(":")[1].split(" or ");
                int rangeOneMin = Integer.parseInt(rangeSplits[0].split("-")[0].trim());
                int rangeOneMax = Integer.parseInt(rangeSplits[0].split("-")[1].trim());
                int rangeTwoMin = Integer.parseInt(rangeSplits[1].split("-")[0].trim());
                int rangeTwoMax = Integer.parseInt(rangeSplits[1].split("-")[1].trim());
                ticketFields.put(name, new TicketField(rangeOneMin, rangeOneMax, rangeTwoMin, rangeTwoMax));
            } else if (partOfInput == 1) {
                if (s.contains("your")) continue;
                myTicket = s.trim();
            } else if (partOfInput == 2) {
                if (s.contains("nearby")) continue;
                nearbyTickets.add(s);
            }
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        long sum = 0;
        String firstField = ticketFields.keySet().stream().findFirst().orElse("duration");
        for (String ticket : nearbyTickets) {
            String[] fields = ticket.split(",");
            boolean ticketOK = true;
            for (String field : fields) {
                int number = Integer.parseInt(field);
                boolean ok = false;
                if (!ticket.startsWith(field + ",")) {
                    for (String fieldName : ticketFields.keySet()) {
                        if (fieldName.equalsIgnoreCase(firstField)) continue;
                        TicketField ticketField = ticketFields.get(fieldName);
                        if (ticketField.isInRange(number)) {
                            ok = true;
                        }
                    }
                } else {
                    TicketField ticketField = ticketFields.get(firstField);
                    if (ticketField.isInRange(number)) {
                        ok = true;
                    }
                }
                if (!ok) {
                    sum += number;
                    ticketOK = false;
                }
            }
            if (ticketOK) {
                validTickets.add(ticket);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 16/A] " + sum);
        if (Main.debug) {
            System.out.println("[Day 16/A] Time: " + (end - start) + "ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        while (calculatePartB()) {}

        long product = 1;
        String[] myTicketParts = myTicket.split(",");

        for (int i = 0; i < 20; i++) {
            if (ticketParts.containsKey(i)) {
                if (ticketParts.get(i).startsWith("departure")) {
                    product *= Long.parseLong(myTicketParts[i]);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 16/B] " + product);
        if (Main.debug) {
            System.out.println("[Day 16/B] Time: " + (end - start) + "ms");
        }
    }

    private boolean calculatePartB() {
        String exampleTicket = validTickets.get(0);
        for (int i = 1; i < exampleTicket.split(",").length; i++) {
            int times = 0;
            String lastValidName = "";
            for (String fieldName : ticketFields.keySet()) {
                if (ticketParts.containsValue(fieldName)) continue;
                boolean ok = true;
                TicketField field = ticketFields.get(fieldName);
                for (String ticket : validTickets) {
                    int number = Integer.parseInt(ticket.split(",")[i]);
                    if (!field.isInRange(number)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    times++;
                    lastValidName = fieldName;
                }
            }
            if (times == 1) {
                ticketParts.put(i, lastValidName);
                return true;
            }
        }
        return false;
    }

    private static class TicketField {
        private final int minRangeOne;
        private final int minRangeTwo;
        private final int maxRangeOne;
        private final int maxRangeTwo;

        public TicketField(int minRangeOne, int maxRangeOne, int minRangeTwo, int maxRangeTwo) {
            this.minRangeOne = minRangeOne;
            this.minRangeTwo = minRangeTwo;
            this.maxRangeOne = maxRangeOne;
            this.maxRangeTwo = maxRangeTwo;
        }

        public boolean isInRange(int number) {
            if (minRangeOne <= number && number <= maxRangeOne) return true;
            return minRangeTwo <= number && number <= maxRangeTwo;
        }
    }
}
