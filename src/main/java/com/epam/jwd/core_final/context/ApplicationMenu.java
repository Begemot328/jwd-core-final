package com.epam.jwd.core_final.context;

import java.util.Scanner;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default String printAvailableOptions() {
        String options;
        options = "Type __  for:\n"
                + "1. View all spaceships \n" +
                "2. View all crewmembers\n" +
                "3. Create mission\n" +
                "4. Print all crewmembers to JSON\n" +
                "5. Print all spaceships to JSON\n" +
                "0. Exit";
        System.out.println(options);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            return scanner.next();
        }
        return options;
    }

    default String handleUserInput(String s) {
        String result = "";

        switch (s) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "9":
                System.exit(0);
                break;
            default:
                result = printAvailableOptions();
        }
        return result;
    }
}
