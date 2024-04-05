package design_pattern.command;

import java.util.ArrayList;
import java.util.List;

public class CommandDemo {

	private static List<Command> produceCommandList() {
		List<Command> commandList = new ArrayList<>();

		Programmer programmer = new Programmer();
		Poltician poltician = new Poltician();

		commandList.add(programmer);
		commandList.add(poltician);

		return commandList;
	}

	private static void executeCommandList(List<Command> commandList) {
		for (Command command : commandList) {
			command.execute();
		}
	}

	public static void main(String[] args) {
		List<Command> commandList = produceCommandList();
		executeCommandList(commandList);
	}
}
