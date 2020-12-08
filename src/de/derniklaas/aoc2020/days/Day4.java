package de.derniklaas.aoc2020.days;

import de.derniklaas.aoc2020.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {

    private List<Passport> passports;

    public Day4(String[] input) {
        long start = System.currentTimeMillis();
        passports = new ArrayList<>();
        Passport currentPassport = new Passport();
        for (String s : input) {
            if (s.trim().equalsIgnoreCase("")) {
                passports.add(currentPassport);
                currentPassport = new Passport();
            } else {
                String[] parts = s.split(" ");
                for (String part : parts) {
                    String key = part.split(":")[0];
                    String value = part.split(":")[1];
                    inputKeyValue(currentPassport, key, value);
                }
            }
        }
        passports.add(currentPassport);
        long end = System.currentTimeMillis();
        if (Main.debug) {
            System.out.println("[Day 4/Constructor] Time: " + (end - start) + " ms");
        }
    }

    public void printAnswers() {
        printA();
        printB();
    }

    private void printA() {
        long start = System.currentTimeMillis();
        int valid = 0;
        for (Passport port : passports) {
            if (port.isValid_A()) valid++;
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 4/A] " + valid);
        if (Main.debug) {
            System.out.println("[Day 4/A] Time: " + (end - start) + " ms");
        }
    }

    private void printB() {
        long start = System.currentTimeMillis();
        int valid = 0;
        for (Passport port : passports) {
            if (port.isValid_B()) valid++;
        }
        long end = System.currentTimeMillis();
        System.out.println("[Day 4/B] " + valid);
        if (Main.debug) {
            System.out.println("[Day 4/A] Time: " + (end - start) + " ms");
        }
    }

    private void inputKeyValue(Passport passport, String key, String value) {
        switch (key) {
            case "byr": {
                passport.setBirthYear(value);
                break;
            }
            case "iyr": {
                passport.setIssueYear(value);
                break;
            }
            case "eyr": {
                passport.setExpirationYear(value);
                break;
            }
            case "hgt": {
                passport.setHeight(value);
                break;
            }
            case "hcl": {
                passport.setHairColor(value);
                break;
            }
            case "ecl": {
                passport.setEyeColor(value);
                break;
            }
            case "pid": {
                passport.setPassportID(value);
                break;
            }
            case "cid": {
                passport.setCountryID(value);
                break;
            }
        }
    }

    private static class Passport {
        private String birthYear = null; // byr
        private String issueYear = null; // iyr
        private String expirationYear = null; // eyr
        private String height = null; //hgt
        private String hairColor = null; // hcl
        private String eyeColor = null; // ecl
        private String passportID = null; // pid
        private String countryID = null; // cid

        public Passport() {
        }

        public void setBirthYear(String birthYear) {
            this.birthYear = birthYear;
        }

        public void setCountryID(String countryID) {
            this.countryID = countryID;
        }

        public void setEyeColor(String ecl) {
            this.eyeColor = ecl;
        }

        public void setExpirationYear(String expirationYear) {
            this.expirationYear = expirationYear;
        }

        public void setHairColor(String hairColor) {
            this.hairColor = hairColor;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setIssueYear(String issueYear) {
            this.issueYear = issueYear;
        }

        public void setPassportID(String passportID) {
            this.passportID = passportID;
        }

        public boolean hasCountryID() {
            return countryID != null;
        }

        public boolean isValid_A() {
            int skipped = 0;

            if (birthYear == null) skipped++;
            if (issueYear == null) skipped++;
            if (expirationYear == null) skipped++;
            if (height == null) skipped++;
            if (hairColor == null) skipped++;
            if (eyeColor == null) skipped++;
            if (passportID == null) skipped++;

            return skipped == 0;
        }

        public boolean isValid_B() {
            int skipped = 0;
            if (birthYear == null) skipped++;
            else {
                int year = Integer.parseInt(birthYear);
                if (!(year >= 1920 && year <= 2002)) skipped++;
            }
            if (issueYear == null) skipped++;
            else {
                int year = Integer.parseInt(issueYear);
                if (!(year >= 2010 && year <= 2020)) skipped++;
            }
            if (expirationYear == null) skipped++;
            else {
                int year = Integer.parseInt(expirationYear);
                if (!(year >= 2020 && year <= 2030)) skipped++;
            }
            if (height == null) skipped++;
            else {
                if (height.contains("cm")) {
                    height = height.replace("cm", "");
                    int temp = Integer.parseInt(height);
                    if (!(temp >= 150 && temp <= 193)) skipped++;
                } else if (height.contains("in")) {
                    height = height.replace("in", "");
                    int temp = Integer.parseInt(height);
                    if (!(temp >= 59 && temp <= 76)) skipped++;
                } else {
                    skipped++;
                }
            }
            if (hairColor == null) skipped++;
            else {
                if (!hairColor.matches("#[0-9a-f]{6}")) skipped++;
            }
            if (eyeColor == null) skipped++;
            else {
                List<String> validColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                if (!validColors.contains(eyeColor)) skipped++;
            }
            if (passportID == null) skipped++;
            else if (!passportID.matches("[0-9]{9}")) skipped++;

            return skipped == 0;
        }
    }
}
