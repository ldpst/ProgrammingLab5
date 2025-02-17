package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды max_by_operator
 *
 * @author ldpst
 */
public class MaxByOperator extends Command {
    private final CollectionManager collectionManager;

    public MaxByOperator(StreamHandler stream, CommandManager commandManager) {
        super("max_by_operator", "вывести любой объект из коллекции, значение поля operator которого является максимальным", stream, commandManager);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        if (collectionManager.isEmpty()) {
            stream.printSuccess("Коллекция пуста\n");
            return;
        }
        stream.printSuccess("Максимальный элемент по полю оператор:\n");
        stream.print(collectionManager.getMaxByOperator().toString() + "\n");
    }
}
