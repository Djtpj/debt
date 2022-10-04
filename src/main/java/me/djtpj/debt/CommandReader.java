package me.djtpj.debt;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CommandReader {
    public static void readAndRunCommand() {
        Scanner scanner = new Scanner(System.in);

        InputLine input = new InputLine(scanner.nextLine());

        for (Command value : Command.values()) {
            if (Arrays.stream(value.getTriggers()).anyMatch(input.trigger::equals)) {
                value.run(input.getTrigger(), input.getArgs());
                return;
            }
        }

        System.out.println("speak english " + generateInsult());
    }

    private static String generateInsult() {
        Random random = new Random();

        String[] insults = {
                "FOOL",
                "BAFOON",
                "BARF FOR BARF",
                "UNGAMER",
                "UNGROGGER",
                "NERRD",
                "LOSER",
                "SALTY SALTY SALTY SALT",
                "UNPOGGER"
        };

        return insults[random.nextInt(insults.length)];
    }

    private static class InputLine {
        @Getter
        String trigger;
        @Getter
        String[] args;

        InputLine(String line) {
            String[] split = split(line);

            trigger = parsePrefix(split);

            args = parseArgs(split);
        }

        private String[] parseArgs(String[] split) {
            ArrayList<String> results = new ArrayList<>(Arrays.asList(split));

            // Remove the prefix
            results.remove(0);

            return results.toArray(new String[0]);
        }

        private String parsePrefix(String[] split) {
            return split[0];
        }

        private String[] split(String line) {
            return line.split(" ");
        }
    }
}
