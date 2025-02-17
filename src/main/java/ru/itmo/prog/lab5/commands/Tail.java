package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.Permissions;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды tail
 *
 * @author ldpst
 */
public class Tail extends Command {
    private final CollectionManager collectionManager;

    public Tail(StreamHandler stream, CommandManager commandManager) {
        super("tail", "вывести значение последнего элемента", stream, commandManager, Permissions.ADMIN);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        stream.printSuccess("Последний элемент коллекции:\n");
        if (collectionManager.isEmpty()) {
            stream.print("Коллекция пуста\n");
            return;
        }
        stream.print(collectionManager.getTail().toString() + "\n");
    }
}
