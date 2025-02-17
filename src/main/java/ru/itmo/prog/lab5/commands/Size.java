package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.Permissions;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды size
 *
 * @author ldpst
 */
public class Size extends Command {
    private final CollectionManager collectionManager;

    public Size(StreamHandler stream, CommandManager commandManager) {
        super("size", "вывести размер коллекции", stream, commandManager, Permissions.ADMIN);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        stream.printSuccess("Размер коллекции:\n");
        stream.println("> " + collectionManager.getSize());
    }
}
