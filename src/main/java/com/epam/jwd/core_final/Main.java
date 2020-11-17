package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.controller.Controller;
import com.epam.jwd.core_final.controller.IController;
import com.epam.jwd.core_final.exception.InvalidStateException;


import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {


    private static final String START_MESSAGE = "Starting";

    public static void main(String[] args) {


        try {
            ApplicationMenu menu = Application.start();
        } catch (InvalidStateException e) {
            ApplicationContext.getLoggerInstance().error(e.getMessage());
        }

        IController controller = Controller.getInstance();
        controller.run();
    }
}