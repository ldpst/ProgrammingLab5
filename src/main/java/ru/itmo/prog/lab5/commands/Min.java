package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.Permissions;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды min
 *
 * @author ldpst
 */
public class Min extends Command {
    private final CollectionManager collectionManager;

    public Min(StreamHandler stream, CommandManager commandManager) {
        super("min", "вывести минимальный элемент в коллекции", stream, commandManager, Permissions.ADMIN);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        stream.printSuccess("Минимальный элемент:\n");
        if (collectionManager.isEmpty()) {
            stream.print("Коллекция пуста\n");
            return;
        }
        stream.print(collectionManager.getMin().toString() + "\n");
    }
}