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

        return options;
    }

    default String handleUserInput(String s) {
        System.out.println(s);
        String result = "";
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNext()) {
            result = scanner.next();
        }

        return result;
    }
}
