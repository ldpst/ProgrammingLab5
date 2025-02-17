package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.StreamHandler;

import java.util.Map;

/**
 * Класс команды info
 *
 * @author ldpst
 */
public class Info extends Command {
    public Info(StreamHandler stream, CommandManager commandManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", stream, commandManager);
    }

    @Override
    public void run(String[] args) {
        stream.printSuccess("Информация о коллекции:\n");
        Map<String, Command> commands = commandManager.getCommands();
        commands.get("type").run(null);
        commands.get("size").run(null);
        commands.get("get_creation_time").run(null);
        commands.get("head").run(null);
        commands.get("tail").run(null);
        commands.get("max").run(null);
        commands.get("min").run(null);
    }
}
