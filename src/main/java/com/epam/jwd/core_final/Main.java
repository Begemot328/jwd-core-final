package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting");
        try {
            ApplicationMenu menu = Application.start();
            menu.printAvailableOptions();
            menu.handleUserInput(menu.printAvailableOptions());
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
    }
}