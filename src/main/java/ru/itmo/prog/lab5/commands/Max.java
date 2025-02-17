package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.Permissions;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды max
 *
 * @author ldpst
 */
public class Max extends Command {
    private final CollectionManager collectionManager;

    public Max(StreamHandler stream, CommandManager commandManager) {
        super("max", "вывести максимальный элемент в коллекции", stream, commandManager, Permissions.ADMIN);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        stream.printSuccess("Максимальный элемент:\n");
        if (collectionManager.isEmpty()) {
            stream.print("Коллекция пуста\n");
            return;
        }
        stream.print(collectionManager.getMax().toString() + "\n");
    }
}
