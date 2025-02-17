package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды clear
 *
 * @author ldpst
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(StreamHandler stream, CommandManager commandManager) {
        super("clear", "очистить коллекцию", stream, commandManager);
        this.collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        collectionManager.clear();
        stream.printSuccess("Коллекция успешно очищена\n");
    }
}
