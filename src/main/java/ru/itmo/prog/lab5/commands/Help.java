package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды help
 *
 * @author ldpst
 */
public class Help extends Command {
    public Help(StreamHandler stream, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам", stream, commandManager);
    }

    @Override
    public void run(String[] args) {
        stream.printSuccess("Справка по доступным командам:\n");
        for (Command command : commandManager.getCommands().values()) {
            if (commandManager.getUserPermission().compareTo(command.getPermission()) <= 0) {
                stream.printf("> %s : %s\n", command.getName(), command.getDescription());
            }
        }
    }
}
