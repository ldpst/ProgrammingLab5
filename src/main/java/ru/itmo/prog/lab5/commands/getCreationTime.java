package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.Permissions;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды get_creation_time
 *
 * @author ldpst
 */
public class getCreationTime extends Command {
    private final CollectionManager collectionManager;

    public getCreationTime(StreamHandler stream, CommandManager commandManager) {
        super("get_creation_time", "вывести время инициализации коллекции", stream, commandManager, Permissions.ADMIN);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        stream.printSuccess("Время инициализации:\n");
        stream.printf("> %s\n", collectionManager.getCreationTime());
    }
}
