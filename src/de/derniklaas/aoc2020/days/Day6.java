package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.List;

public class Day6 {

    private final List<Group> groups;

    public Day6(String[] input) {
        long start = System.currentTimeMillis();
        groups = new ArrayList<>();
        Group currentGroup = new Group();
        for (String s : input) {
            if (s.trim().equalsIgnoreCase("")) {
                groups.add(currentGroup);
                currentGroup = new Group();
            } else {
                List<String> parts = new ArrayList<>();
                for (String part : s.split("")) {
                    if (!part.equalsIgnoreCase(" ")) {
                        if (!currentGroup.contains(part)) currentGroup.add(part);
                        if (!parts.contains(part)) parts.add(part);
                    }
                }
                Person person = new Person(parts);
                currentGroup.addPerson(person);
            }
        }
        groups.add(currentGroup);
        long end = System.currentTimeMillis();
        if (Main.debug) {
            System.out.println("[Day 6/Constructor] Time: " + (end - start) + " ms");
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (Group group : groups) {
            sum += group.size();
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 6/A] " + sum);
        if (Main.debug) {
            System.out.println("[Day 6/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (Group group : groups) {
            sum += group.getUnambiguousVotes();
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 6/B] " + sum);
        if (Main.debug) {
            System.out.println("[Day 6/B] Time: " + (end - start) + " ms");
        }
    }

    private static class Group {

        private final List<String> answers = new ArrayList<>();
        private final List<Person> people = new ArrayList<>();

        public Group() {

        }

        public void add(String answer) {
            answers.add(answer);
        }

        public boolean contains(String answer) {
            return answers.contains(answer);
        }

        public int size() {
            return answers.size();
        }

        public void addPerson(Person person) {
            people.add(person);
        }

        public int getUnambiguousVotes() {
            int votes = 0;
            for (String answer : answers) {
                boolean yes = true;
                for (Person person : people) {
                    if (!person.getAnswers().contains(answer)) {
                        yes = false;
                        break;
                    }
                }
                if (yes) votes++;
            }

            return votes;
        }
    }

    private static class Person {

        private List<String> answers;

        public Person(List<String> answers) {
            this.answers = answers;
        }

        public List<String> getAnswers() {
            return answers;
        }
    }
}
