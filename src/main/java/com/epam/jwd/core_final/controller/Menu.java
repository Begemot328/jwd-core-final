package com.epam.jwd.core_final.controller;

public class Menu {
	
	private com.epam.jwd.core_final.controller.IController controller;
	private com.epam.jwd.core_final.controller.ICommand[] commands = new ICommand[10];
	private String name;
	
	public Menu(String name) {
		this.name = name;
	}
	
	public ICommand getCommand(int i) {
		if (i <= 0 || i >= 10) {
			return null;
		}
		return commands[i];
	} 
	
	public ICommand[] getCommands() {
		return commands;
	}
	
	public void setCommand(ICommand command, int i) {
		if (i <= 0 || i >= 10) {
			return;
		}
		commands[i] = command;
	}

	public String getName() {
		return name;
	}
}
