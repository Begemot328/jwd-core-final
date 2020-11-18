package com.epam.jwd.core_final.controller;


import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.controller.commands.CreateMissionCommand;
import com.epam.jwd.core_final.controller.commands.ViewAllCrewMemebersCommand;
import com.epam.jwd.core_final.controller.commands.ViewAllMissionsCommand;
import com.epam.jwd.core_final.controller.commands.ViewAllSpaceShipsCommand;
import com.epam.jwd.core_final.controller.commands.ViewCrewMembersByRankCommand;
import com.epam.jwd.core_final.controller.commands.ViewCrewMembersByRoleCommand;
import com.epam.jwd.core_final.controller.commands.WriteCrewMembersToJacksonCommand;
import com.epam.jwd.core_final.controller.commands.WriteMissionsToJacksonCommand;
import com.epam.jwd.core_final.controller.commands.WriteSpaceshipsToJacksonCommand;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.NoResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.function.Supplier;


public class Controller implements IController {
    private final static String ANYKEY = "Press any key to continue...";
    private final static String EMPTY = "is empty";
    private final static String ENTER_CORRECT_DATA = "Enter valid data!";
    private final static String COMMAND_DESCRIPTION = "Enter COMMAND to DO_SOMETHING";
    private final static String COMMAND = "COMMAND";
    private final static String DO_SOMETHING = "DO_SOMETHING";
    private final static String NO_SUCH_COMMAND = "No such comand";
    private final static String DATA_DESCRIPTION = "Enter DATA";
    private final static String DATA = "DATA";
    private final static String NO_RESULTS = "No notes found";
    private final static String FILENAME = "filename";
    private final static String WRONG_DATA_TYPE = "Wrong data type!";
    private final static String WRONG_COMMAND = "Wrong command!";
    private final static String FILE_NOT_FOUND = "File not found!";
    private final static String DEFAULT_FILENAME = "default";
    private static final String GO_TO_MAIN_MENU = "go to main menu";
    private static final String GO_TO_JSON_MENU = "go to JSON menu";
    private static final String EXIT = "exit";
    private static final String NEW_LINE = "\n";
    public static final String MAIN_MENU = "Main menu";
    private static final String JSON_MENU = "JSON menu";
    public static final String STARS = "***";
    private static final String SPACE = "  ";

    private Scanner scanner;
    private Menu currentMenu;
    private Menu mainMenu;
    private Menu JSONMenu;

    private static IController INSTANCE = new Controller();

    private Controller() {
        scanner = new Scanner(System.in);

        mainMenu = new Menu(MAIN_MENU);
        mainMenu.setCommand(new CreateMissionCommand(), 1);
        mainMenu.setCommand(new JSONMenuCommand(), 2);
        mainMenu.setCommand(new ViewAllCrewMemebersCommand(System.out::println), 3);

        mainMenu.setCommand(new ViewCrewMembersByRoleCommand(System.out::println, getSupplierShort("Role")), 4);
        mainMenu.setCommand(new ViewCrewMembersByRankCommand(System.out::println, getSupplierShort("Rank")), 5);
        mainMenu.setCommand(new ViewAllMissionsCommand(System.out::println), 6);
        mainMenu.setCommand(new ViewAllSpaceShipsCommand(System.out::println), 7);
        mainMenu.setCommand(new ExitCommand(), 9);

        JSONMenu = new Menu(JSON_MENU);
        JSONMenu.setCommand(new WriteSpaceshipsToJacksonCommand(), 1);
        JSONMenu.setCommand(new WriteCrewMembersToJacksonCommand(), 2);
        JSONMenu.setCommand(new WriteMissionsToJacksonCommand(), 3);
        JSONMenu.setCommand(new MainMenuCommand(), 8);
        JSONMenu.setCommand(new ExitCommand(), 9);

        currentMenu = mainMenu;
    }

    public static IController getInstance() {
        return INSTANCE;
    }

    public void run() {
        while (true) {
            readCommand();
        }
    }

    private void readCommand() {
        ICommand command = null;
        writeMenu(currentMenu);
        int commandCode = 0;

        if (scanner.hasNext()) {
            try {
                commandCode = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                ApplicationContext.getLoggerInstance().error(WRONG_COMMAND);
                System.out.println(WRONG_COMMAND);
            }
            command = currentMenu.getCommand(commandCode);
            if (command == null) {
                ApplicationContext.getLoggerInstance().warn(NO_SUCH_COMMAND);
                System.out.println(NO_SUCH_COMMAND);
                return;
            }
        }
        try {
            command.execute();
        } catch (NoResourceException e) {
            System.out.println(e.getMessage());
            ApplicationContext.getLoggerInstance().warn(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            ApplicationContext.getLoggerInstance().error(e.getMessage());
        }
    }

    private void writeMenu(Menu menu) {
        ICommand[] commands = new ICommand[menu.getCommands().length];
        commands = menu.getCommands().clone();

        System.out.println(NEW_LINE + STARS + SPACE
                +  menu.getName() + SPACE + STARS + NEW_LINE);

        for (int i = 1; i < commands.length; i++) {
            if (commands[i] == null) {
                continue;
            }
            System.out.println(COMMAND_DESCRIPTION.replace(COMMAND, i + "").replace(
                    DO_SOMETHING, commands[i].getText()));
        }
    }

    @Override
    public Object getParameter(Type type, String name) {
        return readParameter(type, name);
    }

    public Supplier<Short> getSupplierShort(String data) {
        return new Supplier<Short>() {
            @Override
            public Short get() {
                return (Short) readParameter(Type.SHORT, data);
            }
        };
    }

    private Object readParameter(Type type, String data) {
        while (true) {
            System.out.println(DATA_DESCRIPTION.replace(DATA, data));
            switch (type) {
                case STRING:
                    if (scanner.hasNext()) {
                        return scanner.next();
                    }
                case DOUBLE:
                    if (scanner.hasNextDouble()) {
                        return scanner.nextDouble();
                    } else {
                        System.out.println(WRONG_DATA_TYPE);
                        ApplicationContext.getLoggerInstance().warn(WRONG_DATA_TYPE);
                        scanner.next();
                    }
                case SHORT:
                    if (scanner.hasNextShort()) {
                        return scanner.nextShort();
                    } else {
                        System.out.println(WRONG_DATA_TYPE);
                        ApplicationContext.getLoggerInstance().warn(WRONG_DATA_TYPE);
                        scanner.next();
                    }
            }
        }
    }

    // Commands- inner classes of commands
    public class MainMenuCommand implements ICommand {

        @Override
        public String getText() {
            return GO_TO_MAIN_MENU;
        }

        @Override
        public void execute() {
            Controller.this.currentMenu = mainMenu;
        }
    }

    public class JSONMenuCommand implements ICommand {

        @Override
        public String getText() {
            return GO_TO_JSON_MENU;
        }

        @Override
        public void execute() {
            Controller.this.currentMenu = JSONMenu;
        }
    }

    public class ExitCommand implements ICommand {

        @Override
        public String getText() {
            return EXIT;
        }

        @Override
        public void execute() {
            System.exit(0);
        }
    }
}
