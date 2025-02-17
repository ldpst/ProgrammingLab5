package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.object.Person;
import ru.itmo.prog.lab5.object.builders.PersonBuilder;
import ru.itmo.prog.lab5.utils.StreamHandler;

public class CountByOperator extends Command {
    private final CollectionManager collectionManager;

    public CountByOperator(StreamHandler stream, CommandManager commandManager) {
        super("count_by_operator operator", "вывести количество элементов, значение поля operator которых равно заданному", stream, commandManager);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        Person operator;
        if (args.length == 2 && args[1].equals("null")) {
            operator = null;
        } else {
            operator = new PersonBuilder(stream, commandManager.getScanner(), commandManager.getInputFormat()).build();
        }
        stream.printSuccess("Количество элементов, значение поля operator которых равно заданному:\n");
        stream.printf("> %s\n", collectionManager.countByOperator(operator));
    }
}
