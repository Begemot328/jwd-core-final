package com.epam.jwd.core_final.controller;



import java.util.Scanner;


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
	private final static String LOGIN = "login";
	private final static String PASSWORD = "password";
	private final static String WELCOME = "Welcome, USER FIRSTNAME LASTNAME!";
	private final static String FIRSTNAME = "FIRSTNAME";
	private final static String LASTNAME = "LASTNAME";
	private static final String GO_TO_MAIN_MENU = "go to main menu";
	private static final String EXIT = "exit";
	public static final String OPEN_NOTEBOOK = "open default notebook";
	public static final String SAVE_NOTEBOOK = "save notebook to default";
	public static final String OPEN_NOTEBOOK_FROM_FILE = "open notebook from file";
	public static final String NOTEBOOK_MENU = "work with notebook";
	public static final String SAVE_NOTEBOOK_TO_FILE = "save notebook to file";
	public static final String GO_TO_SEARCH_MENU = "go to search menu";
	public static final String NULL_NOTEBOOK = "notebook not found";
	public static final String SEARCH_RESET = "start a new search";
	public static final String SEARCH_BY_THEME = "search a note by theme";
	public static final String THEME = "theme";
	public static final String SEARCH_BY_MAIL = "search a note by mail adress";
	public static final String TEXT = "text";
	public static final String SEARCH_BY_TEXT = "search a note by text";
	public static final String DATE = "date";
	public static final String SEARCH_BY_DATE = "search a note by date";
	public static final String MAIL = "mail adress";
	public static final String ADD_NOTE = "add new note";
	public static final String MAIN_MENU = "Main menu";
	
	private Scanner scanner;
	private Menu currentMenu;
	private Menu mainMenu;

	private static IController INSTANCE = new Controller();

	private Controller() {
		scanner = new Scanner(System.in);
		
		mainMenu = new Menu(MAIN_MENU);
		mainMenu.setCommand(new CreateMissionCommand(), 1);
		mainMenu.setCommand(new ExitCommand(), 6);

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
				System.out.println(WRONG_COMMAND);
			}
			command = currentMenu.getCommand(commandCode);
			if (command == null) {
				System.out.println(NO_SUCH_COMMAND);
				return;
			}
		}
		command.execute();
	}

	private void writeMenu(Menu menu) {
		ICommand[] commands = new ICommand[menu.getCommands().length];
		commands = menu.getCommands().clone();
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

	private Object readParameter(Type type, String data) {
		while (true) {
		System.out.println(DATA_DESCRIPTION.replace(DATA, data));	
		switch (type) {
			case STRING:
				if(scanner.hasNext()) {
					return scanner.next();
				}
			case DOUBLE:
				try {
					if(scanner.hasNext()) {
						return Double.parseDouble(scanner.next());
					}
					
				} catch (NumberFormatException e) {
					System.out.println(WRONG_DATA_TYPE);
				}
				
				if(scanner.hasNextDouble()) {
					return scanner.nextDouble();
				} else {
					System.out.println(WRONG_DATA_TYPE);
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
